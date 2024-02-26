package Notebook;

import Notebook.Database.DataBase;
import Notebook.Panels.MainPanel;
import Notebook.Panels.PlusPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class MainWindow extends JFrame {
    public static MainWindow instance;
    public DataBase dataBase = new DataBase();

    public JList<String> jList = new JList<>();

    public Vector<String> notes = new Vector<String>();


    public MainPanel mainJPanel ;
    public PlusPanel plusJPanel ;


    public MainWindow() throws SQLException {
        instance = this;
        dataBase.connect();

        String sql = "SELECT Name FROM Notes";
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            notes.add(resultSet.getString(1));
        }


        jList = new JList<String>(notes);

        setSize(500, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Notebook");

        mainJPanel = new MainPanel();
        plusJPanel = new PlusPanel();


        add(mainJPanel);


    }



    public static void main(String[] args) throws InterruptedException, SQLException {
        MainWindow wnd = new MainWindow();

        wnd.setVisible(true);
    }

}
