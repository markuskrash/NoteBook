import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;

public class DataBase {
    public static Connection con = null;

    public void connect () throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        String sql = """
                CREATE TABLE IF NOT EXISTS Notes(
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    Name      TEXT    UNIQUE,
                    Contact TEXT
                )""";
        PreparedStatement st = con.prepareStatement(sql);
        st.execute();
    }

//    public String add() throws SQLException {
//        String sql = "SELECT password FROM Notes WHERE login = " + "'" + MainWindow.instance.firstJTF.getText() +  "'";
//        PreparedStatement s = con.prepareStatement(sql);
//        ResultSet resultSet = s.executeQuery();
//        if (!resultSet.next()) {
//            return "ERR";
//        }
//        else if (Objects.equals(resultSet.getString("password"), hex(MainWindow.instance.secondJTF.getText(), 5))){
//            if (MainWindow.instance.firstJTF.getText().equals("admin")){
//                return "admin";
//            }
//            return "OK";
//        }
//        else{
//            return "ERR";
//        }
//    }
}
