package Notebook.Actions;

import Notebook.Database.DataBase;
import Notebook.MainWindow;
import Notebook.Panels.PlusPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class AddContactListenerPlus implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        PlusPanel plusPanel = MainWindow.instance.plusJPanel;
        try {
            Vector<String> types = DataBase.getContactsTypes();
            JComboBox<String> comboBox = new JComboBox<String>(types);
            String result = JOptionPane.showInputDialog(MainWindow.instance.plusJPanel, comboBox);
            plusPanel.contacts.add(comboBox.getItemAt(comboBox.getSelectedIndex()) + ": " + result);
            plusPanel.contactsList.setListData(MainWindow.instance.plusJPanel.contacts);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
}
