package src;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Class for rendering the button in the same row as the film's data
 */
public class ButtonRenderer extends JButton implements TableCellRenderer {
    /**
     * Construcot for the ButtonRenderer class
     */
    public ButtonRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object obj, boolean selected, boolean focused, int row, int col) {
        setText((obj == null) ? "" : obj.toString());
        return this;
    }
}