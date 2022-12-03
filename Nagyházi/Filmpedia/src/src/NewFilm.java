package src;

import javax.swing.*;
import java.awt.*;

/**
 * Class for adding a new film, this class includes the pop up form/window creation too
 */
public class NewFilm {
    /**
     * Needed for film adding, converts the JTextfield string to an enumeration
     * @param ar - the value from the JTextfield
     * @return   - an Agerating item
     */
    public AgeRating StringToAgeRating(String ar) {
        switch (ar) {
            case "G": return AgeRating.G;
            case "PG":  return AgeRating.PG;
            case "PG_13": return AgeRating.PG_13;
            case "R": return AgeRating.R;
            case "NC_17": return AgeRating.NC_17;
            default: return AgeRating.G;
        }
    }

    /**
     * Needed for film adding, converts the JTextfield string to an enumeration
     * @param cat   - the value from the JTextfield
     * @return      - a Category item
     */
    public Categories StringToCategory(String cat) {
        switch (cat) {
            case "Action": return Categories.Action;
            case "Comedy":  return Categories.Comedy;
            case "Drama": return Categories.Drama;
            case "Fantasy": return Categories.Fantasy;
            case "Horror": return Categories.Horror;
            case "Mystery": return Categories.Mystery;
            case "Romance": return Categories.Romance;
            case "Thriller": return Categories.Thriller;
            case "Western": return Categories.Western;
            default: return Categories.Action;
        }
    }

    /**
     * Function that creates a new window for adding a new film
     * @return - returns a new film added to the list
     */
    public Film display() {
        String[] ageRatings = {"G", "PG", "PG_13", "R", "NC_17"};
        String[] categories = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"};
        JComboBox<String> agr = new JComboBox<>(ageRatings);
        JComboBox<String> cat = new JComboBox<>(categories);

        JTextField title = new JTextField();
        JTextField pubyear = new JTextField();
        JTextField length = new JTextField();
        JTextField producer = new JTextField();
        JTextField description = new JTextField();
        description.setDocument(new JTextFieldLimit(300));

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.setPreferredSize(new Dimension(400, 200));

        panel.add(new JLabel("Title:"));
        panel.add(title);

        panel.add(new JLabel("Publication Year:"));
        panel.add(pubyear);

        panel.add(new JLabel("Length in minutes:"));
        panel.add(length);

        panel.add(new JLabel("Producer:"));
        panel.add(producer);

        panel.add(new JLabel("Description: (Max 300 char)"));
        panel.add(description);

        panel.add(new JLabel("Age Rating:"));
        panel.add(agr);

        panel.add(new JLabel("Category:"));
        panel.add(cat);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add new Film", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            //* Debug reasons only
            System.out.println(
                    title.getText()
                    + " " + pubyear.getText()
                    + " " + length.getText()
                    + " " + producer.getText()
                    + " " + description.getText()
                    + " " + agr.getSelectedItem()
                    + " " + cat.getSelectedItem()
            );
            //! Create film and return it
            return new Film(
                    title.getText(),
                    Integer.parseInt(pubyear.getText()),
                    Integer.parseInt(length.getText()),
                    producer.getText(),
                    description.getText(),
                    StringToAgeRating((String)agr.getSelectedItem()),
                    StringToCategory((String)cat.getSelectedItem())
            );
        } else {
            System.out.println("Cancelled");
        }
        return null;
    }
}