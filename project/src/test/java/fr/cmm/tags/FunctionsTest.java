package fr.cmm.tags;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by pomme on 17/12/2015.
 */
public class FunctionsTest {

    @Test
    public void retourLigneTest() {
        assertEquals(Functions.transformText("\n") , "<br>");
        assertEquals(Functions.transformText("a\n") , "a<br>");
        assertEquals(Functions.transformText("&a") , "&amp;a");
    }


}