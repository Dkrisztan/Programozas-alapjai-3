import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class CaesarFrame extends JFrame {
    private static final Object[] engAlph = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private final JFrame f = new JFrame("SwingLab");

    private JPanel pUpper = new JPanel();
    private JPanel pBottom = new JPanel();

    private static final JComboBox cbox = new JComboBox(engAlph);
    private static final JTextField top = new JTextField(20);
    private static final JTextField bottom = new JTextField(20);
    private static final JButton button = new JButton("Code!");
    private static final JLabel label = new JLabel("Output: ");

    private static boolean fromTopToBottom = true;

    public CaesarFrame() {
        //! Layout
        f.setLayout(new GridLayout(2, 1));
        pUpper.setLayout(new FlowLayout(FlowLayout.CENTER));
        pBottom.setLayout(new BorderLayout(0, 0));

        button.addActionListener(new OkButtonActionListener());

        //! 5-os feladat
        DocumentListener changeListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Caesar t = new Caesar();
                bottom.setText(t.caesarCode(top.getText(), cbox.getSelectedItem().toString()));
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                Caesar t = new Caesar();
                bottom.setText(t.caesarCode(top.getText(), cbox.getSelectedItem().toString()));
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                Caesar t = new Caesar();
                bottom.setText(t.caesarCode(top.getText(), cbox.getSelectedItem().toString()));
            }
        };

        top.getDocument().addDocumentListener(changeListener);

        //! 7-es feladat
        FocusListener focusChanger = new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                fromTopToBottom = false;
            }
            @Override
            public void focusLost(FocusEvent focusEvent) { }
        };

        bottom.addFocusListener(focusChanger);

        //! Adding to the Frame
        pUpper.add(cbox);
        pUpper.add(top);
        pUpper.add(button);

        //* Commented because of 7th exercise
        // bottom.setEditable(false);
        pBottom.add(label, BorderLayout.WEST);
        pBottom.add(bottom);

        f.add(pUpper);
        f.add(pBottom);
        f.pack();

        f.setSize(400, 110);

        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.setVisible(true);
    }


    //! Our own actionlistener to use the Caesar ciphering
    static class OkButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Caesar t = new Caesar();
            if (fromTopToBottom) {
                bottom.setText(t.caesarCode(top.getText(), cbox.getSelectedItem().toString()));
            } else {
                top.setText(t.caesarDecode(bottom.getText(), cbox.getSelectedItem().toString()));
            }
        }
    }

    static class InputFieldKeyListener extends KeyAdapter {
        @Override
        public void keyTyped(KeyEvent e) {
            Caesar t = new Caesar();
            bottom.setText(t.caesarCode(top.getText(), cbox.getSelectedItem().toString()));
        }
    }

}
