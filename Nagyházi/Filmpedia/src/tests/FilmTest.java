package tests;

import org.junit.Assert;
import org.junit.Test;
import src.AgeRating;
import src.Categories;
import src.Film;

public class FilmTest {
    @Test
    public void filmConstructorTest() {
        Film f = new Film("Test", 2002, 123, "Test", "Test", AgeRating.G, Categories.Action);
        Assert.assertEquals(f.getTitle() + f.getPublication_year() + f.getLength() + f.getProducer() + f.getDescription() + f.getAgeRating() + f.getCategory(), "Test" + 2002 + 123 + "Test" + "Test" + AgeRating.G + Categories.Action);
    }

    @Test
    public void filmGettersAndSetters() {
        Film f = new Film("", 0, 0, "", "", AgeRating.G, Categories.Action);;
        f.setTitle("Test");
        f.setPublication_year(2002);
        f.setLength(123);
        f.setProducer("Test");
        f.setDescription("Test");
        f.setAgeRating(AgeRating.G);
        f.setCategory(Categories.Action);
        Assert.assertEquals(f.getTitle() + f.getPublication_year() + f.getLength() + f.getProducer() + f.getDescription() + f.getAgeRating() + f.getCategory(), "Test" + 2002 + 123 + "Test" + "Test" + AgeRating.G + Categories.Action);
    }
}
