package repository;

import domain.Document;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Document> getAll(){

        ArrayList<Document> Documents= new ArrayList<>();;
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM documentdatabase");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                Document w = new Document(
                        rs.getString("name"),
                        rs.getString("keywords"),
                        rs.getString("content")
                );
                Documents.add(w);
            }
            rs.close();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Documents;
    }

    public void UpdateSchema(String newKeywords,String newContent, String name){
        try {
            openConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE documentdatabase  set keywords=?,content=? WHERE name=?");

            statement.setString(1, newKeywords);
            statement.setString(2, newContent);
            statement.setString(3,name);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
