package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the Editing button
 */
public class ButtonEditor extends DefaultCellEditor {
    protected JButton btn;
    private Boolean clicked;

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
     * Needed for film age rating because the combobox setvalue only worked with indexes
     * @param agr   - JTextfield value
     * @return      - index of the agerating item
     */
    public Integer AgeRStringToIndex(String agr) {
        switch (agr) {
            case "G": return 0;
            case "PG":  return 1;
            case "PG_13": return 2;
            case "R": return 3;
            case "NC_17": return 4;
            default: return 0;
        }
    }

    /**
     * Needed for film age rating because the combobox setvalue only worked with indexes
     * @param cat   - JTextfield value
     * @return      - index of the category item
     */
    public Integer CategoryStringToIndex(String cat) {
        switch (cat) {
            case "Action": return 0;
            case "Comedy":  return 1;
            case "Drama": return 2;
            case "Fantasy": return 3;
            case "Horror": return 4;
            case "Mystery": return 5;
            case "Romance": return 6;
            case "Thriller": return 7;
            case "Western": return 8;
            default: return 0;
        }
    }

    /**
     * Constructor of ButtonEditor
     * @param txt   - name of the button
     * @param table - The main Frame we attach the editor button to
     * @param data  - the FilmData list with all of our films
     */
    public ButtonEditor(JTextField txt, JTable table, FilmData data) {
        super(txt);
        btn = new JButton();
        btn.setOpaque(true);

        //! Action when button is clicked -> We get the data of a film and put it into a form
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String[] ageRatings = {"G", "PG", "PG_13", "R", "NC_17"};
                    String[] categories = {"Action", "Comedy", "Drama", "Fantasy", "Horror", "Mystery", "Romance", "Thriller", "Western"};

                    JComboBox<String> agr = new JComboBox<>(ageRatings);
                    agr.setSelectedIndex(AgeRStringToIndex(data.getValueAt(table.getSelectedRow(), 5).toString()));
                    JComboBox<String> cat = new JComboBox<>(categories);
                    cat.setSelectedIndex(CategoryStringToIndex(data.getValueAt(table.getSelectedRow(), 6).toString()));

                    JTextField title = new JTextField((String) data.getValueAt(table.getSelectedRow(), 0));
                    JTextField pubyear = new JTextField(((Integer) data.getValueAt(table.getSelectedRow(), 1)).toString());
                    JTextField length = new JTextField(((Integer) data.getValueAt(table.getSelectedRow(), 2)).toString());
                    JTextField producer = new JTextField((String) data.getValueAt(table.getSelectedRow(), 3));
                    JTextField description = new JTextField();
                    description.setDocument(new JTextFieldLimit(300));
                    description.setText((String) data.getValueAt(table.getSelectedRow(), 4));


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

                    int result = JOptionPane.showConfirmDialog(null, panel, "Modify Film", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
                        //! Create film and delete the other one
                        btn.setVisible(false);
                        data.films.remove(table.getSelectedRow());
                        data.addFilm(
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
                    btn.setVisible(true);
                    data.fireTableDataChanged();
                }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
        clicked = true;
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        clicked = false;
        return "";
    }

    @Override
    public boolean stopCellEditing() {
        clicked = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}