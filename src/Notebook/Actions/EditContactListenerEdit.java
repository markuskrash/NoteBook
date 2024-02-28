package Notebook.Actions;

import Notebook.Database.DataBase;
import Notebook.MainWindow;
import Notebook.Panels.EditPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

public class EditContactListenerEdit extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
        if (e.getClickCount() == 2){
            EditPanel editPanel = MainWindow.instance.editJPanel;
            try {
                Vector<String> types = DataBase.getContactsTypes();
                JComboBox<String> comboBox = new JComboBox<String>(types);
                String result = JOptionPane.showInputDialog(MainWindow.instance.editJPanel, comboBox);

                int selectedIndex = editPanel.contactsList.getSelectedIndex();
                if(result.length() > 0) {
                    editPanel.contacts.set(selectedIndex, comboBox.getItemAt(comboBox.getSelectedIndex()) + ": " + result);
                    editPanel.contactsList.setListData(editPanel.contacts);
                }else{
                    editPanel.contacts.remove(selectedIndex);
                    editPanel.contactsList.setListData(editPanel.contacts);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
