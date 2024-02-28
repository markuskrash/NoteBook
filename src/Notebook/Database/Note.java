package Notebook.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Note {
    public String name;
    public String contact;

    public Note(String name) {
        this.name = name;
    }

    public void save() throws SQLException {
        String sql = String.format("INSERT INTO Notes(Name) VALUES('%s')", name);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();
    }

    public void update(Note prev) throws SQLException {
        String sql = String.format("UPDATE Notes SET Name = '%s' WHERE Name = '%s'", name, prev.name);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();
    }

    public void delete() throws SQLException {
        String sql = String.format("DELETE FROM Notes WHERE Name = '%s'", name);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();

    }

    public int getID() throws SQLException {
        String sql = String.format("SELECT id FROM Notes WHERE Name = '%s'", name);
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        return resultSet.getInt(1);
    }

    public Vector<Contact> getContacts() throws SQLException{
        String sql = String.format("SELECT type, contact FROM Contacts WHERE idN = '%s'", this.getID());
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        Vector<Contact> contacts = new Vector<>();
        while (resultSet.next()){
            contacts.add(new Contact(resultSet.getString(1), resultSet.getString(2), getID()));
        }
        return contacts;
    }

    public Vector<Integer> getContactsIds() throws SQLException{
        String sql = String.format("SELECT id FROM Contacts WHERE idN = '%s'", this.getID());
        PreparedStatement ps = DataBase.con.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        Vector<Integer> ids = new Vector<>();
        while (resultSet.next()){
            ids.add(Integer.valueOf(resultSet.getString(1)));
        }
        return ids;
    }
}
