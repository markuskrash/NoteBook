package Notebook.Actions;

import Notebook.Database.DataBase;
import Notebook.MainWindow;
import Notebook.Panels.PlusPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Vector;

public class EditContactListenerPlus extends MouseAdapter {
    public void mouseClicked(MouseEvent e){
        if (e.getClickCount() == 2){
            PlusPanel plusPanel = MainWindow.instance.plusJPanel;
            try {
                Vector<String> types = DataBase.getContactsTypes();
                JComboBox<String> comboBox = new JComboBox<String>(types);
                String result = JOptionPane.showInputDialog(MainWindow.instance.plusJPanel, comboBox);

                int selectedIndex = plusPanel.contactsList.getSelectedIndex();
                if(result.length() > 0) {
                    plusPanel.contacts.set(selectedIndex, comboBox.getItemAt(comboBox.getSelectedIndex()) + ": " + result);
                    plusPanel.contactsList.setListData(plusPanel.contacts);
                }else{
                    plusPanel.contacts.remove(selectedIndex);
                    plusPanel.contactsList.setListData(plusPanel.contacts);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
