package src;

import javax.swing.*;

/**
 * Class for putting Edit and Delete button in each row next to the films
 */
public class ButtonColumn extends JFrame {

    /**
     * Constructor for ButtonColumn
     * @param table     - our main JFrame table
     * @param data      - the FilmData list containing all of the films
     */
    public ButtonColumn(JTable table, FilmData data) {
        table.getColumnModel().getColumn(7).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(7).setCellEditor(new ButtonEditor(new JTextField(), table, data));

        table.getColumnModel().getColumn(8).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(8).setCellEditor(new ButtonDeleter(new JTextField(), table, data));
    }
}

