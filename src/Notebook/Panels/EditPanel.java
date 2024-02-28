package Notebook.Panels;

import Notebook.Actions.AddContactListenerEdit;
import Notebook.Actions.EditContactListenerEdit;
import Notebook.Actions.EditContactListenerPlus;
import Notebook.Database.Contact;
import Notebook.Database.DataBase;
import Notebook.MainWindow;
import Notebook.Database.Note;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Vector;

public class EditPanel extends JPanel {

    public  JTextField nameJTF = new JTextField();
    public Vector<String> contacts = new Vector<String>();
    public JList<String>contactsList = new JList<String>();
    private  JPanel jPanel = new JPanel(new BorderLayout());
    private JButton addContactBtn = new JButton("Добавить контакт");
    private JButton backBtn = new JButton("Назад");
    private JButton confirmBtn = new JButton("Подтвердить");
    private JButton deleteBtn = new JButton("Удалить");
    public MainWindow MainWindowInstance = MainWindow.instance;
    public EditPanel(String name) throws SQLException {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(nameJTF, gbc);

        gbc = new GridBagConstraints(
                0, 1, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );


        jPanel.add(contactsList, BorderLayout.CENTER);
        jPanel.add(new JScrollPane(contactsList));

        add(jPanel, gbc);

        gbc = new GridBagConstraints(
                0, 2, 3, 1, 1, 1,
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

        add(deleteBtn, gbc);

        gbc = new GridBagConstraints(
                2, 3, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(confirmBtn, gbc);


        nameJTF.setText(name);

        Note noteOld = new Note(name);
        Vector<Contact> contactsOld = noteOld.getContacts();

        for(int i = 0; i < contactsOld.size(); i++){
            contacts.add(contactsOld.get(i).type + ": " + contactsOld.get(i).contact);
        }

        contactsList.setListData(contacts);

        addContactBtn.addActionListener(new AddContactListenerEdit());

        backBtn.addActionListener(e -> {
            MainWindowInstance.editJPanel.nameJTF.setText("");
            MainWindowInstance.editJPanel.contacts = new Vector<String>();
            MainWindowInstance.editJPanel.contactsList.setListData(MainWindowInstance.editJPanel.contacts);

            MainWindowInstance.add(MainWindowInstance.mainJPanel);
            MainWindowInstance.remove(MainWindowInstance.editJPanel);
            MainWindowInstance.revalidate();
            MainWindowInstance.repaint();
        });

        contactsList.addMouseListener(new EditContactListenerEdit());


        confirmBtn.addActionListener(e -> {
            Note note = new Note(nameJTF.getText());
            Note prev = new Note(name);
            try {
                note.update(prev);

                DataBase.deleteContacts(prev.getID());

                for(int i = 0; i < contactsList.getModel().getSize(); i++){
                    String nameNew =contactsList.getModel().getElementAt(i).toString();
                    Contact contact = new Contact(nameNew.split(":")[0], nameNew.split(":")[1].strip(), note.getID());
                    contact.save();
                }

                MainWindowInstance.add(MainWindowInstance.mainJPanel);
                MainWindowInstance.remove(MainWindowInstance.editJPanel);
                MainWindowInstance.revalidate();
                MainWindowInstance.repaint();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteBtn.addActionListener(e -> {
            Note note = new Note(name);
            try {
                MainWindowInstance.notes.remove(MainWindowInstance.jList.getSelectedIndex());
                MainWindowInstance.jList.setListData(MainWindowInstance.notes);
                DataBase.deleteContacts(note.getID());
                note.delete();
                MainWindowInstance.add(MainWindowInstance.mainJPanel);
                MainWindowInstance.remove(MainWindowInstance.editJPanel);
                MainWindowInstance.revalidate();
                MainWindowInstance.repaint();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}
