import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class EditWindow extends JFrame {

    public JTextField nameJTF = new JTextField();
    public JTextField contactJTF = new JTextField();
    public JButton confirmBtn = new JButton("Подтвердить");
    public JButton deleteBtn = new JButton("Удалить");
    public EditWindow(String string){
        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Add Note");

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 4, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(nameJTF, gbc);

        gbc = new GridBagConstraints(
                0, 1, 4, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(contactJTF, gbc);

        gbc = new GridBagConstraints(
                0, 2, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(deleteBtn, gbc);
        gbc = new GridBagConstraints(
                2, 2, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(confirmBtn, gbc);

        int i = 0;
        while(string.charAt(i) != ':'){
            i += 1;
        }
        String Name = string.substring(0, i);
        String Contact = string.substring(i + 2, string.length());

        nameJTF.setText(Name);
        contactJTF.setText(Contact);


        confirmBtn.addActionListener(e -> {
            Note note = new Note(nameJTF.getText(), contactJTF.getText());
            Note prev = new Note(Name, Contact);
            try {
                note.update(prev);
                MainWindow wnd = new MainWindow();

                wnd.setVisible(true);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            dispose();
        });
        deleteBtn.addActionListener(e -> {
            Note note = new Note(Name, Contact);
            try {
                note.delete();
                MainWindow wnd = new MainWindow();

                wnd.setVisible(true);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            dispose();
        });
    }
}
