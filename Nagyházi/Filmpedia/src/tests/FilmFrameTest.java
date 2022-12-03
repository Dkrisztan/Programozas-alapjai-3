package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import src.*;
import java.awt.*;
import java.io.*;
import java.util.List;

public class FilmFrameTest {
    FilmData test;
    String probaTitles;
    String testTitles;

    @Before
    public void setUp() {
        test = new FilmData();
        test.addFilm("Test1", 2002, 123, "Test1", "Test1", AgeRating.G, Categories.Action);
        test.addFilm("Test2", 2002, 123, "Test2", "Test2", AgeRating.PG, Categories.Western);
        test.addFilm("Test3", 2002, 123, "Test3", "Test3", AgeRating.PG_13, Categories.Horror);
    }

    @Test
    public void testInits() {
        FilmFrame fr = new FilmFrame();
        Assert.assertEquals("FilmPedia", fr.getTitle());
        Assert.assertEquals(new Dimension(1450, 768), fr.getMinimumSize());
        boolean asdf;
    }

    @Test
    public void testWriteFile() throws IOException {
        boolean boo = false;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("testfilms.dat"));
            oos.writeObject(test.films);
            oos.close();
        } catch (Exception e) {
            boo = true;
        }
        Assert.assertFalse(boo);
    }

    @Test
    public void testReadFile() throws IOException, ClassNotFoundException {
        FilmData proba = new FilmData();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("testfilms.dat"));
        proba.films = (List<Film>)ois.readObject();
        ois.close();

        for (Film a : proba.films) {
            probaTitles += a.getTitle();
        }

        for (Film a : test.films) {
            testTitles += a.getTitle();
        }

        Assert.assertEquals(testTitles, probaTitles);
    }
}