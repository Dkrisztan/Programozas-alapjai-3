package src;

import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Class for making a JTextfield be limited to a certain amount of characters
 */
public class JTextFieldLimit extends PlainDocument {
    private final int limit;

    /**
     * Constructor of the class JTextFieldLimit
     * @param limit - an integer of the limit in characters
     */
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    /**
     * Prevents the user from inputting any more characters to the textfield
     * @param offset the starting offset &gt;= 0
     * @param str the string to insert; does nothing with null/empty strings
     * @param attr the attributes for the inserted content
     * @throws BadLocationException
     */
    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            super.insertString(offset, str, attr);
        }
    }
}