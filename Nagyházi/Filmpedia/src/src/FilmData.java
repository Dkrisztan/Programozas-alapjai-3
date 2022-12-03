package src;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for making an AbstractTableModel out of films
 */
public class FilmData extends AbstractTableModel {

    /**
     * The list that contains the films
     */
    public List<Film> films = new ArrayList<Film>();

    /**
     * Function that returns the number of rows in the table
     * @return  - number of rows in the table
     */
    @Override
    public int getRowCount() {
        return films.size();
    }

    /**
     * Function that returns the number of columns in the table
     * @return  - returns 9
     */
    @Override
    public int getColumnCount() {
        return 9;
    }

    /**
     * Returns the value at a specific column and row
     * @param rowIndex        the row whose value is to be queried
     * @param columnIndex     the column whose value is to be queried
     * @return  - returns a specific data about a film
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Film film = films.get(rowIndex);
        switch(columnIndex) {
            case 0: return film.getTitle();
            case 1: return film.getPublication_year();
            case 2: return film.getLength();
            case 3: return film.getProducer();
            case 4: return film.getDescription();
            case 5: return film.getAgeRating();
            case 6: return film.getCategory();
            case 7: return "Edit";
            case 8: return "Delete";

            default: return film.getTitle();
        }
    }

    /**
     * Returns tha class of the given column
     * @param i  the column being queried
     * @return  - a class type
     */
    @Override
    public Class<?> getColumnClass(int i) {
        switch(i) {
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Integer.class;
            case 3: return String.class;
            case 4: return String.class;
            case 5: return AgeRating.class;
            case 6: return Categories.class;
            case 7: return JButton.class;
            case 8: return JButton.class;
            default: return String.class;
        }
    }

    /**
     * Function that returns the name of the column given
     * @param i  the column being queried
     * @return  - returns a String with the name of the column
     */
    @Override
    public String getColumnName(int i) {
        switch(i) {
            case 0: return "Title";
            case 1: return "Publication Year";
            case 2: return "Length";
            case 3: return "Producer";
            case 4: return "Description";
            case 5: return "Age Rating";
            case 6: return "Category";
            case 7: return "Edit";
            case 8: return "Delete";

            default: return "Title";
        }
    }

    /**
     * Adding a new Film to the list
     * @param title                 the film's title
     * @param publication_year      the film's publication year
     * @param length                the film's length
     * @param producer              the film's producer
     * @param description           the film's description
     * @param ageRating             the film's age rating
     * @param category              the film's category
     */
    public void addFilm(String title, Integer publication_year, Integer length, String producer, String description, AgeRating ageRating, Categories category) {
        films.add(new Film(title, publication_year, length, producer, description, ageRating, category));
        this.fireTableDataChanged();
    }

    /**
     * Function that returns if a cell is editable or not
     * @param row  the row being queried
     * @param column the column being queried
     * @return  - returns a boolean -> true, then cell is editable; false -> cell is not editable
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        if (column > 6)
            return true;
        return false;
    }
}