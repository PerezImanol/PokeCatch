package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Trainer;
import interfaces.Logeable;

public class LogeableDBimplementation implements Logeable {

    private Connection con;
    private PreparedStatement stmt;
    private OpenCloseConnection occ = new OpenCloseConnection();

    @Override
    public Trainer isUser(String username, String password) {
        ResultSet rs;

        try {
            con = occ.openConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
