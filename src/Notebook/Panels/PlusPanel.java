package Notebook.Panels;

import Notebook.Actions.AddContactListener;
import Notebook.Actions.AddNoteListener;
import Notebook.MainWindow;
import Notebook.Database.Note;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class PlusPanel extends JPanel {

    public  JTextField nameJTF = new JTextField();
    public Vector<String> contacts = new Vector<String>();
    public JList<String>contactsList = new JList<String>();
    private  JPanel jPanel = new JPanel(new BorderLayout());
    private JButton addContactBtn = new JButton("Добавить контакт");
    private JButton backBtn = new JButton("Назад");
    private JButton confirmBtn = new JButton("Подтвердить");
    public MainWindow MainWindowInstance = MainWindow.instance;
    public PlusPanel(){
        setLayout(new GridBagLayout());
        setSize(500, 300);
        setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(nameJTF, gbc);

        gbc = new GridBagConstraints(
                0, 1, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );


        jPanel.add(contactsList, BorderLayout.CENTER);
        jPanel.add(new JScrollPane(contactsList));

        add(jPanel, gbc);

        gbc = new GridBagConstraints(
                0, 2, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(addContactBtn, gbc);

        gbc = new GridBagConstraints(
                0, 3, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(backBtn, gbc);

        gbc = new GridBagConstraints(
                1, 3, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(confirmBtn, gbc);

        addContactBtn.addActionListener(new AddContactListener());

        backBtn.addActionListener(e -> {
            MainWindowInstance.add(MainWindowInstance.mainJPanel);
            MainWindowInstance.remove(MainWindowInstance.plusJPanel);
            MainWindowInstance.revalidate();
            MainWindowInstance.repaint();
        });

        confirmBtn.addActionListener(new AddNoteListener());
    }
}
