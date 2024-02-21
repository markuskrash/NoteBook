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
    public JButton plusBtn = new JButton("+");
    public JButton editBtn = new JButton("edit");

    public  JPanel jPanel = new JPanel();

    public MainWindow() throws SQLException {
        instance = this;
        dataBase.connect();

        String sql = "SELECT Name, Contact FROM Notes";
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()){
            notes.add(resultSet.getString(1) + ": " + resultSet.getString(2));
        }


        jList = new JList<String>(notes);

        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Notebook");



        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 2, 2, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        jPanel.add(jList);

        jPanel.add(new JScrollPane(jList));
        add(jPanel,gbc);

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
            PlusWindow plusWnd = new PlusWindow();
            plusWnd.setVisible(true);
            dispose();
        });

        editBtn.addActionListener(e -> {
            if(jList.getSelectedValue() != null) {
                EditWindow editWnd = new EditWindow(jList.getSelectedValue().toString());
                editWnd.setVisible(true);
                dispose();
            }
        });
    }


    public static void main(String[] args) throws InterruptedException, SQLException {
        MainWindow wnd = new MainWindow();

        wnd.setVisible(true);
    }

}
