package swingmvclab;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

/*
 * A hallgatók adatait tároló osztály.
 */
public class StudentData extends AbstractTableModel {

    /*
     * Ez a tagváltozó tárolja a hallgatói adatokat.
     * NE MÓDOSÍTSD!
     */
    List<Student> students = new ArrayList<Student>();

    @Override
    public int getRowCount() {
        return students.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Student student = students.get(rowIndex);
        switch(columnIndex) {
            case 0: return student.getName();
            case 1: return student.getNeptun();
            case 2: return student.hasSignature();
            default: return student.getGrade();
        }
    }

    @Override
    public String getColumnName(int i) {
        switch(i) {
            case 0: return "Név";
            case 1: return "Neptun";
            case 2: return "Aláírás";
            default: return "Jegy";
        }
    }

    @Override
    public Class<?> getColumnClass(int i) {
        Student s = students.get(0);
        switch(i) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Boolean.class;
            default: return Integer.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column < 2)
                return false;
        return true;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        Student s = students.get(i);
        if(i1 >= 2 && i1 <= 3) {
            switch(i1) {
                case 2:
                    s.setSignature((Boolean)o);
                    break;
                default:
                    s.setGrade((Integer)o);
                    break;
            }
            students.set(i, s);
            this.fireTableRowsUpdated(i, i);
        }
    }

    public void addStudent(String name, String neptun) {
        students.add(new Student(name, neptun, Boolean.FALSE, 0));
        this.fireTableDataChanged();
    }
}
