package pl.coderslab.utils;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DbUtil {

    private String DB_USER;
    private String DB_PASS;
    private String DB_SERVER_URL;
    private String DB_PARAMS;

    public String getDB_USER() {
        return DB_USER;
    }

    public void setDB_USER(String DB_USER) {
        this.DB_USER = DB_USER;
    }

    public String getDB_PASS() {
        return DB_PASS;
    }

    public void setDB_PASS(String DB_PASS) {
        this.DB_PASS = DB_PASS;
    }

    public String getDB_SERVER_URL() {
        return DB_SERVER_URL;
    }

    public void setDB_SERVER_URL(String DB_SERVER_URL) {
        this.DB_SERVER_URL = DB_SERVER_URL;
    }

    public String getDB_PARAMS() {
        return DB_PARAMS;
    }

    public void setDB_PARAMS(String DB_PARAMS) {
        this.DB_PARAMS = DB_PARAMS;
    }

    public Connection connect() throws SQLException {
        String dbUrl=DB_SERVER_URL+DB_PARAMS;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(dbUrl,DB_USER,DB_PASS);
    }

}