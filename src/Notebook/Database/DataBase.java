package Notebook.Database;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class DataBase {
    public static Connection con = null;

    public void connect () throws SQLException {
        con = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        String sql = """
                CREATE TABLE IF NOT EXISTS Notes(
                    id         INTEGER PRIMARY KEY AUTOINCREMENT,
                    Name      TEXT    UNIQUE
                )""";
        PreparedStatement st = con.prepareStatement(sql);
        st.execute();

        sql = """
               CREATE TABLE IF NOT EXISTS Contacts(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT,
                    contact TEXT,
                    idN INTEGER,
                    FOREIGN KEY (idN) REFERENCES Notes(id)
                )""";
        st = con.prepareStatement(sql);
        st.execute();

        sql = """
                CREATE TABLE IF NOT EXISTS ContactType(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT
                )""";
        st = con.prepareStatement(sql);
        st.execute();

        if( getContactsTypes().size() == 0) addContactsTypes();
    }
    public static void addContactsTypes() throws SQLException {
        String sql = "INSERT INTO ContactType(type) VALUES('Telegram')";

        PreparedStatement st = con.prepareStatement(sql);
        st.execute();
        sql = "INSERT INTO ContactType(type) VALUES('Email')";

        st = con.prepareStatement(sql);
        st.execute();
        sql = "INSERT INTO ContactType(type) VALUES('Phone Number')";

        st = con.prepareStatement(sql);
        st.execute();
    }

    public static Vector<String> getContactsTypes () throws SQLException {
        Vector<String> result = new Vector<String>();

        String sql = "SELECT type FROM ContactType";

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()){
            result.add(resultSet.getString(1));
        }

        return result;
    }

    public static void deleteContacts (int idN) throws SQLException {
        String sql = String.format("DELETE FROM Contacts WHERE idN = '%s' ", idN);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();
    }


}
