package swingmvclab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

/*
 * A megjelenítendõ ablakunk osztálya.
 */
public class StudentFrame extends JFrame {
    
    /*
     * Ebben az objektumban vannak a hallgatói adatok.
     * A program indulás után betölti az adatokat fájlból, bezáráskor pedig kimenti oda.
     * 
     * NE MÓDOSÍTSD!
     */
    private StudentData data;
    private JTextField nameField;
    private JTextField neptunField;

    /*
     * Itt hozzuk létre és adjuk hozzá az ablakunkhoz a különbözõ komponenseket
     * (táblázat, beviteli mezõ, gomb).
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());

        JTable table = new JTable(data);
        table.setFillsViewportHeight(rootPaneCheckingEnabled);
        table.setRowSorter(new TableRowSorter<StudentData>(data));

        table.setDefaultRenderer(String.class, new StudentTableCellRenderer(table.getDefaultRenderer(String.class)));
        table.setDefaultRenderer(Boolean.class, new StudentTableCellRenderer(table.getDefaultRenderer(Boolean.class)));
        table.setDefaultRenderer(Number.class, new StudentTableCellRenderer(table.getDefaultRenderer(Number.class)));


        this.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottom = new JPanel(new FlowLayout());

        JLabel na = new JLabel("Név:");
        JLabel npt = new JLabel("Neptun:");
        nameField = new JTextField(20);
        neptunField = new JTextField(6);

        JButton addBtn = new JButton("Felvesz");
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                data.addStudent(nameField.getText(), neptunField.getText());
            }
        });

        bottom.add(na);
        bottom.add(nameField);
        bottom.add(npt);
        bottom.add(neptunField);
        bottom.add(addBtn);

        this.add(bottom, BorderLayout.SOUTH);
    }

    /*
     * Az ablak konstruktora.
     * 
     * NE MÓDOSÍTSD!
     */
    @SuppressWarnings("unchecked")
    public StudentFrame() {
        super("Hallgatói nyilvántartás");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        // Induláskor betöltjük az adatokat
        try {
            data = new StudentData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
            data.students = (List<Student>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        // Bezáráskor mentjük az adatokat
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
                    oos.writeObject(data.students);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Felépítjük az ablakot
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public static void main(String[] args) {
        // Megjelenítjük az ablakot
        StudentFrame sf = new StudentFrame();
        sf.setVisible(true);
    }

    private class StudentTableCellRenderer implements TableCellRenderer {
        private final TableCellRenderer renderer;
        public StudentTableCellRenderer(TableCellRenderer defRenderer) {
            this.renderer = defRenderer;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            Student actualStudent = data.students.get(table.getRowSorter().convertRowIndexToModel(row));
            Color bg;

            if (actualStudent.getGrade() <= 1) {
                bg = Color.RED;
            } else {
                bg = Color.GREEN;
            }
            component.setBackground(bg);
            return component;
        }

    }
}
