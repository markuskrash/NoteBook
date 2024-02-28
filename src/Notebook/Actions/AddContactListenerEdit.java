package Notebook.Actions;

import Notebook.Database.DataBase;
import Notebook.MainWindow;
import Notebook.Panels.EditPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class AddContactListenerEdit implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        EditPanel editPanel = MainWindow.instance.editJPanel;
        try {
            Vector<String> types = DataBase.getContactsTypes();
            JComboBox<String> comboBox = new JComboBox<String>(types);
            String result = JOptionPane.showInputDialog(MainWindow.instance.editJPanel, comboBox);
            editPanel.contacts.add(comboBox.getItemAt(comboBox.getSelectedIndex()) + ": " + result);
            editPanel.contactsList.setListData(MainWindow.instance.editJPanel.contacts);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
