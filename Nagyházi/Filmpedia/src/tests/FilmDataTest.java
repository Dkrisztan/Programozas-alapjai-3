package tests;

import org.junit.*;
import src.*;

public class FilmDataTest {
    @Test
    public void testIsFilmDataEmpty() {
        FilmData test = new FilmData();
        test.addFilm("Test", 2002, 123, "Test", "Test", AgeRating.G, Categories.Action);
        Assert.assertFalse(test.films.isEmpty());
    }

    @Test
    public void testGetTitle() {
        FilmData test = new FilmData();
        test.addFilm("Test", 2002, 123, "Test", "Test", AgeRating.G, Categories.Action);
        Assert.assertEquals("Test", test.films.get(0).getTitle());
    }

    @Test
    public void testGetCategory() {
        FilmData test = new FilmData();
        test.addFilm("Test", 2002, 123, "Test", "Test", AgeRating.G, Categories.Action);
        Assert.assertEquals(Categories.Action, test.films.get(0).getCategory());
    }

    @Test
    public void testTitleModify() {
        FilmData test = new FilmData();
        test.addFilm("Test", 2002, 123, "Test", "Test", AgeRating.G, Categories.Action);
        Assert.assertEquals("Test", test.films.get(0).getTitle());
        test.films.get(0).setTitle("NotTest");
        Assert.assertNotEquals("Test", test.films.get(0).getTitle());
    }

    @Test
    public void testFilmDelete() {
        FilmData test = new FilmData();
        test.addFilm("Test", 2002, 123, "Test", "Test", AgeRating.G, Categories.Action);
        Assert.assertEquals("Test", test.films.get(0).getTitle());

        test.films.remove(0);
        boolean boo = false;
        try {
            Assert.assertEquals("Test", test.films.get(0).getTitle());
        } catch (Exception err) {
            boo = true;
        }
        Assert.assertTrue(boo);
    }
}
