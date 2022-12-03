package src;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * The class of the window
 */
public class FilmFrame extends JFrame {

    //* Object to store the data of the films
    private FilmData data;

    /**
     * Function to make the buttons show the films in the given category
     * @param cat - a category
     */
    private void showFilms(Categories cat) {
        FilmData newFilms = new FilmData();
        List<String> rows = new ArrayList<String>();
        for (Film d : data.films) {
            if (d.getCategory() == cat) newFilms.addFilm(d.getTitle(), d.getPublication_year(), d.getLength(), d.getProducer(), d.getDescription(), d.getAgeRating(), d.getCategory());
        }
        for (Film d : newFilms.films) {
            rows.add(d.getTitle());
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title", rows.toArray());
        JTable newTable = new JTable(model);
        if (rows.isEmpty()) {
            JPanel err = new JPanel(new GridLayout(0, 1));
            err.add(new Label("There is no film that fits into that category at the moment!"), SwingConstants.CENTER);
            int result = JOptionPane.showConfirmDialog(null, err, "Films that fit in the category", JOptionPane.PLAIN_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(null, newTable, "Films that fit in the category", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Function to make the buttons show the films in the given agerating
     * @param agr - an age rating
     */
    private void showFilms(AgeRating agr) {
        FilmData newFilms = new FilmData();
        List<String> rows = new ArrayList<String>();
        for (Film d : data.films) {
            if (d.getAgeRating() == agr) newFilms.addFilm(d.getTitle(), d.getPublication_year(), d.getLength(), d.getProducer(), d.getDescription(), d.getAgeRating(), d.getCategory());
        }
        for (Film d : newFilms.films) {
            rows.add(d.getTitle());
        }

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Title", rows.toArray());
        JTable newTable = new JTable(model);
        if (rows.isEmpty()) {
            JPanel err = new JPanel(new GridLayout(0, 1));
            err.add(new Label("There is no film that fits into that age rating at the moment!"), SwingConstants.CENTER);
            int result = JOptionPane.showConfirmDialog(null, err, "Films that fit in the age rating", JOptionPane.PLAIN_MESSAGE);
        } else {
            int result = JOptionPane.showConfirmDialog(null, newTable, "Films that fit in the age rating", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Creating the window and adding components to it
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());

        //! Creation of the table
        JTable table = new JTable(data) {
            //* The description is shown with tooltips
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                String parsedtip = null;
                if (colIndex != 4) {
                    parsedtip = null;
                } else {
                    try {
                        tip = getValueAt(rowIndex, colIndex).toString();
                        parsedtip = tip.replaceAll("(.{50})", "$1<br>");
                        parsedtip = "<html>" + parsedtip + "</html>";
                    } catch (Exception err) {
                        System.out.println(" ");
                    }
                }
                return parsedtip;
            }
        };

        //! Searchbar implemented
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        JTextField jtfFilter = new JTextField();
        table.setRowSorter(rowSorter);
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.PINK);
        panel.add(new JLabel("Specify a word to match:"), BorderLayout.WEST);
        panel.add(jtfFilter, BorderLayout.CENTER);
        jtfFilter.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        });

        table.setFillsViewportHeight(rootPaneCheckingEnabled);
        table.getTableHeader().setReorderingAllowed(false);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);

        //! Important for the buttons
        ButtonColumn cl = new ButtonColumn(table, data);

        this.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel top = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add New Film");
        addBtn.setBackground(Color.ORANGE);
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //! The adding of a film
                NewFilm f = new NewFilm();
                Film filmToAdd = f.display();
                if (filmToAdd != null) {
                    data.addFilm(
                            filmToAdd.getTitle(),
                            filmToAdd.getPublication_year(),
                            filmToAdd.getLength(),
                            filmToAdd.getProducer(),
                            filmToAdd.getDescription(),
                            filmToAdd.getAgeRating(),
                            filmToAdd.getCategory()
                    );
                } else {
                    System.out.println("The movie added is NULL");
                }
            }
        });
        top.add(addBtn);

        //! Buttons and their icons and actionlisteners
        // -------------------------------------------------------------------------------
        Icon actionIcon = new ImageIcon("icons/action.png");
        Icon comedyIcon = new ImageIcon("icons/comedy.png");
        Icon dramaIcon = new ImageIcon("icons/drama.png");
        Icon fantasyIcon = new ImageIcon("icons/fantasy.png");
        Icon horrorIcon = new ImageIcon("icons/horror.png");
        Icon mysteryIcon = new ImageIcon("icons/mystery.png");
        Icon romanceIcon = new ImageIcon("icons/romance.png");
        Icon thrillerIcon = new ImageIcon("icons/thriller.png");
        Icon westernIcon = new ImageIcon("icons/western.png");

        Icon gIcon = new ImageIcon("icons/g.png");
        Icon pgIcon = new ImageIcon("icons/pg.png");
        Icon pg_13Icon = new ImageIcon("icons/pg-13.png");
        Icon rIcon = new ImageIcon("icons/r.png");
        Icon nc_17Icon = new ImageIcon("icons/nc-17.png");


        JButton action = new JButton(actionIcon);
        JButton comedy = new JButton(comedyIcon);
        JButton drama = new JButton(dramaIcon);
        JButton fantasy = new JButton(fantasyIcon);
        JButton horror = new JButton(horrorIcon);
        JButton mystery = new JButton(mysteryIcon);
        JButton romance = new JButton(romanceIcon);
        JButton thriller = new JButton(thrillerIcon);
        JButton western = new JButton(westernIcon);

        JButton g = new JButton(gIcon);
        JButton pg = new JButton(pgIcon);
        JButton pg_13 = new JButton(pg_13Icon);
        JButton r = new JButton(rIcon);
        JButton nc_17 = new JButton(nc_17Icon);

        action.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Action);
            }
        });

        comedy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Comedy);
            }
        });

        drama.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Drama);
            }
        });

        fantasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Fantasy);
            }
        });

        horror.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Horror);
            }
        });

        mystery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Mystery);
            }
        });

        romance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Romance);
            }
        });

        thriller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Thriller);
            }
        });

        western.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(Categories.Western);
            }
        });

        g.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(AgeRating.G);
            }
        });

        pg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(AgeRating.PG);
            }
        });

        pg_13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(AgeRating.PG_13);
            }
        });

        r.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(AgeRating.R);
            }
        });

        nc_17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilms(AgeRating.NC_17);
            }
        });

        top.add(action);
        top.add(comedy);
        top.add(drama);
        top.add(fantasy);
        top.add(horror);
        top.add(mystery);
        top.add(romance);
        top.add(thriller);
        top.add(western);

        top.add(g);
        top.add(pg);
        top.add(pg_13);
        top.add(r);
        top.add(nc_17);

        // ---------------------------------------------------------------------------------

        //! Adding to the Frame itself
        this.add(top, BorderLayout.NORTH);
        this.add(panel, BorderLayout.SOUTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * The constructor of the window
     */
    @SuppressWarnings("unchecked")
    public FilmFrame() {
        super("FilmPedia");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Reading data from the file on startup
        try {
            data = new FilmData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("films.dat"));
            data.films = (List<Film>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Saving the data when the window is closed
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("films.dat"));
                    oos.writeObject(data.films);
                    oos.close();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Building the window
        setMinimumSize(new Dimension(1450, 768));
        initComponents();
    }

    /**
     * This is where the program starts
     * @param args Arguments provided to the program. Not needed in our case
     */
    public static void main(String[] args) {
        // Showing the window
        FilmFrame sf = new FilmFrame();
        sf.setVisible(true);
    }
}