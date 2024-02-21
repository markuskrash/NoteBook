import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class PlusWindow extends JFrame {

    public JTextField nameJTF = new JTextField();
    public JTextField contactJTF = new JTextField();
    public JButton confirmBtn = new JButton("Подтвердить");
    public PlusWindow(){
        setSize(500, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Add Note");

        GridBagConstraints gbc = new GridBagConstraints(
                0, 0, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(nameJTF, gbc);

        gbc = new GridBagConstraints(
                0, 1, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(contactJTF, gbc);

        gbc = new GridBagConstraints(
                0, 2, 3, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0
        );

        add(confirmBtn, gbc);

        confirmBtn.addActionListener(e -> {
            Note note = new Note(nameJTF.getText(), contactJTF.getText());

            try {
                note.save();
                MainWindow wnd = new MainWindow();

                wnd.setVisible(true);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            dispose();
        });
    }
}
