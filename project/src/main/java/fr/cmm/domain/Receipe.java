package fr.cmm.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;

public class Receipe {
    @Id
    @ObjectId
    private String id;

    @NotEmpty
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // BUG-4 : Date Failed to convert property value of type java.lang.String to required type java.util.Date for property date; nested exception is org.springframework.core.convert.ConversionFailedException: Failed to convert from type java.lang.String to type @javax.validation.constraints.NotNull @org.springframework.format.annotation.DateTimeFormat java.util.Date for value 2015-04-02; nested exception is java.lang.IllegalArgumentException: Unable to parse 2015-04-02
    private Date date = new Date();

    private String intro;

    private String imageId;

    private List<String> tags = new ArrayList<>();

    private List<Ingredient> ingredients = new ArrayList<>();

    private String text;

    private double[] randomLocation = new double[] {Math.random(), 0};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getTagsAsString() {
        return String.join(",", tags);
    }

    public void setTagsAsString(String tags) {
        setTags(Arrays.asList(tags.split("\\s*,\\s*")));
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double[] getRandomLocation() {
        return randomLocation;
    }

    public void setRandomLocation(double[] randomLocation) {
        this.randomLocation = randomLocation;
    }

    @Override
    public String toString() {
        return "Receipe{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", intro='" + intro + '\'' +
                ", imageId='" + imageId + '\'' +
                ", tags=" + tags +
                ", ingredients=" + ingredients +
                ", text='" + text + '\'' +
                ", randomLocation=" + Arrays.toString(randomLocation) +
                '}';
    }
}