package Notebook.Actions;

import Notebook.Database.Contact;
import Notebook.Database.Note;
import Notebook.MainWindow;
import Notebook.Panels.PlusPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class AddNoteListener implements ActionListener {
    private MainWindow MainWindowInstance = MainWindow.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        Note note = new Note(MainWindowInstance.plusJPanel.nameJTF.getText());
        JList<String> contacts = MainWindowInstance.plusJPanel.contactsList;
        try {
            note.save();
            for(int i = 0; i < contacts.getModel().getSize(); i++){
                String name =contacts.getModel().getElementAt(i).toString();
                Contact contact = new Contact(name.split(":")[0], name.split(":")[1].strip(), note.getID());
                contact.save();
            }
            MainWindowInstance.notes.add(MainWindowInstance.plusJPanel.nameJTF.getText());
            MainWindowInstance.jList.setListData(MainWindowInstance.notes);

            MainWindowInstance.plusJPanel.nameJTF.setText("");
            MainWindowInstance.plusJPanel.contacts = new Vector<String>();
            MainWindowInstance.plusJPanel.contactsList.setListData(MainWindowInstance.plusJPanel.contacts);

            MainWindowInstance.add(MainWindowInstance.mainJPanel);
            MainWindowInstance.remove(MainWindowInstance.plusJPanel);
            MainWindowInstance.revalidate();
            MainWindowInstance.repaint();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
