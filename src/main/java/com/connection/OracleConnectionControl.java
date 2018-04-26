package com.connection;

import javax.swing.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class OracleConnectionControl {

    private String host;
    private String user;
    private String pass;
    private String servico;
    public Connection c;

    public OracleConnectionControl(String host, String user, String pass, String servico) {
        this.pass = pass;
        this.user = user;
        this.host = host;
        this.servico = servico;
    }


    public boolean connect() {
        boolean isConnected = false;

        String portNumber = "1521";
        String url = "jdbc:oracle:thin:@" + host + ":" + portNumber + "/" + servico;
        System.out.println(url);

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            this.c = DriverManager.getConnection(url, user, pass);
            isConnected = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        }
        return isConnected;
    }


    public boolean disconnect() {
        boolean isConnected = false;


        String portNumber = "1521";
        String url = "jdbc:oracle:thin:@" + host + ":" + portNumber + "/" + servico;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            this.c = DriverManager.getConnection(url, user, pass);
            this.c.close();
            isConnected = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            isConnected = false;
        }

        return isConnected;
    }


    public ResultSet executar(String query) {
        Statement st;
        ResultSet rs;

        try {
            st = this.c.createStatement();
            rs = st.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return null;
    }
}
