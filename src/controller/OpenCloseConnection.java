package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class OpenCloseConnection {
    private ResourceBundle configFile;
    private String url;
    private String user;
    private String pass;

    // CONSTRUCTOR
    public OpenCloseConnection() {
        configFile = ResourceBundle.getBundle("resources.config");
        url = configFile.getString("URL");
        user = configFile.getString("USER");
        pass = configFile.getString("PASSWORD");
    }

    public Connection openConnection() throws Exception {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            // System.out.println("Error al intentar abrir la BD");
            // Gestión de la excepción
            throw new Exception(e.getMessage());
        }
        return con;
    }

    public void closeConnection(PreparedStatement stmt, Connection con) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
        if (con != null) {
            con.close();
        }
    }
}

