package Notebook.Actions;

import Notebook.Database.Note;
import Notebook.MainWindow;
import Notebook.Panels.PlusPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddNoteListener implements ActionListener {
    private MainWindow MainWindowInstance = MainWindow.instance;
    @Override
    public void actionPerformed(ActionEvent e) {
        Note note = new Note(MainWindowInstance.plusJPanel.nameJTF.getText());

        try {
            note.save();

            MainWindowInstance.add(MainWindowInstance.mainJPanel);
            MainWindowInstance.remove(MainWindowInstance.plusJPanel);
            MainWindowInstance.revalidate();
            MainWindowInstance.repaint();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
