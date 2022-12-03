package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for the Delete button
 */
public class ButtonDeleter extends DefaultCellEditor {
    protected JButton btn;
    private String lbl;
    private Boolean clicked;

    /**
     * Constructor for ButtonDeleter class
     * @param txt   - The name of the button
     * @param table - the JFrame the buttons will be attached to
     * @param data  - the FilmData list containing all of our films
     */
    public ButtonDeleter(JTextField txt, JTable table, FilmData data) {
        super(txt);
        btn = new JButton();
        btn.setOpaque(true);

        /**
         *  The action that happens when the button is clicked
         */
        btn.addActionListener(new ActionListener() {
            /**
             * The action that happens when the button is clicked
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(0, 1));
                panel.setPreferredSize(new Dimension(80, 60));
                panel.add(new Label("Are you sure you want to delete the film?"), SwingConstants.CENTER);
                int result = JOptionPane.showConfirmDialog(null, panel, "Delete Film", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    btn.setVisible(false);
                    data.films.remove(table.getSelectedRow());
                } else {
                    System.out.println("Cancelled");
                }
                data.fireTableDataChanged();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object obj, boolean selected, int row, int col) {
        lbl = (obj == null) ? "" : obj.toString();
        btn.setText(lbl);
        clicked = true;
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        if(clicked) {
            JOptionPane.showMessageDialog(btn, lbl+" Clicked");
        }
        clicked = false;
        return new String(lbl);
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