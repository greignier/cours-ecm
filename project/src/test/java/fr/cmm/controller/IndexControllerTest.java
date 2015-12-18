package fr.cmm.controller;

import fr.cmm.domain.Recipe;
import fr.cmm.helper.PageQuery;
import fr.cmm.helper.Pagination;
import fr.cmm.service.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.inject.Inject;

import java.util.ArrayList;

import static org.junit.Assert.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = IndexControllerTestConfig.class)
@WebAppConfiguration
public class IndexControllerTest {
    @Inject
    RecipeService recipeService;

    @Inject
    WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = webAppContextSetup(wac).build();

        Mockito.reset(recipeService);
    }

    @Test
    public void mentionsLegales() throws Exception {
        mockMvc.perform(get("/mentions-legales"))
                .andExpect(view().name("mentions-legales"));
    }

    @Test
    public void contact() throws Exception {
        mockMvc.perform(get("/contact")).andExpect(view().name("contact"));
    }

    @Test
    public void recette() throws Exception {
        String id = "56375619d4c603aa4eb412af";

        Mockito.when(recipeService.findById(id)).thenReturn(new Recipe());

        mockMvc.perform(get("/recette/" + id))
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recette"));
    }

    @Test
    public void recettes() throws Exception {
        PageQuery pageQuery = new PageQuery() ;
        pageQuery.setTag("poulet");

        Mockito.when(recipeService.findByQuery(pageQuery)).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/recettes?tag=poulet"))
                .andExpect(status().is(200))
                .andExpect(model().attributeExists("searchForm"))
                .andExpect(view().name("recettes"));
    }

    @Test
    public void negativeIndex() throws Exception {
        mockMvc.perform(get("/recettes/?pageIndex=-2"))
                .andExpect(status().is(200));
    }

    @Test
    public void pagination() throws Exception {
        Pagination pagination = new Pagination() ;

        pagination.setCount(20);
        pagination.setPageSize(50);
        assertEquals(pagination.getPageCount() , 1);

        pagination.setCount(100);
        pagination.setPageSize(50);
        assertEquals(pagination.getPageCount() , 2);

        pagination.setCount(50);
        pagination.setPageSize(20);
        assertEquals(pagination.getPageCount() , 3);

        pagination.setCount(0);
        pagination.setPageSize(50);
        assertEquals(pagination.getPageCount() , 1);
    }

    @Test
    public void getPagesTest() throws Exception {
        Pagination pagination = new Pagination() ;

        pagination.setCount(30);
        pagination.setPageSize(10);
        assertFalse(pagination.getPages().contains(4));

        pagination.setCount(30);
        pagination.setPageSize(1);
        assertTrue(pagination.getPages().size() == pagination.PAGINATION_SIZE);

        pagination.setPageIndex(15);
        assertFalse(pagination.getPages().contains(10));
        assertFalse(pagination.getPages().contains(21));
        assertTrue(pagination.getPages().size() == pagination.PAGINATION_SIZE);
    }

    @Test
    public void get404() throws Exception{
        mockMvc.perform(get("/recette/56375619d4c603aa4eb412dd"))
                .andExpect(status().is(404));
    }

}