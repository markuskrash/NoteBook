package Notebook.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Contact {
    public String type;
    public String contact;
    public int idN;

    public int id;
    public Contact(String type, String contact, int idN) {
        this.type = type;
        this.contact = contact;
        this.idN = idN;
    }
    public Contact(int id) {
        this.id = id;
    }

    public void save() throws SQLException {
        String sql = String.format("SELECT id FROM ContactType WHERE type = '%s'", type);
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        this.type = resultSet.getString(1);
        sql = String.format("INSERT INTO Contacts(type, contact, idN) VALUES('%s', '%s', '%s')", type, contact, idN);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();
    }

    public void update(Contact prev) throws SQLException {
        String sql = String.format("UPDATE Contacts SET type = '%s' contact = '%s' WHERE id = '%s'", type, contact, prev.id);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();

    }

    public void delete() throws SQLException {
        String sql = String.format("DELETE FROM Contacts WHERE type = '%s' AND contact = '%s' AND idN = '%s'", type, contact, idN);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();

    }

    public int getId() throws SQLException {
        String sql = String.format("SELECT id FROM Contacts WHERE type = '%s' AND contact = '%s' AND idN = '%s'", type, contact, idN);
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        return resultSet.getInt(1);

    }
}

