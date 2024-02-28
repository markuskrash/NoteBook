package Notebook.Panels;

import Notebook.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class MainPanel extends JPanel {
    private   JPanel jPanel = new JPanel(new BorderLayout());
    private JButton plusBtn = new JButton("+");
    private JButton editBtn = new JButton("edit");

    private MainWindow MainWindowInstance = MainWindow.instance;
    public  MainPanel(){
        setLayout(new GridBagLayout());
        setSize(500, 300);
        setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 2, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );
        add(jPanel,gbc);
        jPanel.add(MainWindowInstance.jList, BorderLayout.CENTER);

        jPanel.add(new JScrollPane(MainWindowInstance.jList));


        gbc = new GridBagConstraints(
                0, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(plusBtn, gbc);

        gbc = new GridBagConstraints(
                1, 2, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(editBtn, gbc);

        plusBtn.addActionListener(e -> {
            MainWindowInstance.add(MainWindowInstance.plusJPanel);
            MainWindowInstance.remove(MainWindowInstance.mainJPanel);
            MainWindowInstance.revalidate();
            MainWindowInstance.repaint();
        });

        editBtn.addActionListener(e -> {
            if(MainWindowInstance.jList.getSelectedValue() != null) {
                EditPanel editPanel = null;
                try {
                    editPanel = new EditPanel(MainWindowInstance.jList.getSelectedValue().toString());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                MainWindowInstance.editJPanel = editPanel;
                MainWindowInstance.add(editPanel);
                MainWindowInstance.remove(MainWindowInstance.mainJPanel);
                MainWindowInstance.revalidate();
                MainWindowInstance.repaint();
            }
        });
    }
}
