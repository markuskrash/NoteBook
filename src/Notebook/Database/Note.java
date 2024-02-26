package Notebook.Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Note {
    public String name;
    public String contact;

    public Note(String name) {
        this.name = name;
    }

    public void save() throws SQLException {
        String sql = String.format("INSERT INTO Notes(Name) VALUES('%s', '%s')", name);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();
    }

    public void update(Note prev) throws SQLException {
        String sql = String.format("UPDATE Notes SET Name = '%s' WHERE Name = '%s'", name, prev.name);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();
        sql = String.format("UPDATE Notes SET Contact = '%s' WHERE Name = '%s'", contact, prev.name);
        st = DataBase.con.prepareStatement(sql);
        st.execute();
    }

    public void delete() throws SQLException {
        String sql = String.format("DELETE FROM Notes WHERE Name = '%s'", name);
        PreparedStatement st = DataBase.con.prepareStatement(sql);
        st.execute();

    }
}
