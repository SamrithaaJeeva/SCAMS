package com.example.scams;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Connector {

    final  static  String driver="com.mysql.cj.jdbc.Driver";
    final  static String database_url="jdbc:mysql://localhost:3306/scams";
    final static String username= "root";
    final static String password="";
    public static Connection  connection()
    {
        try
        {
            Class.forName(driver);
            Connection c= DriverManager.getConnection(database_url,username,password);
            System.out.println("Database is successfully connected");
            return c;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
            return null ;
        }
    }

    public static void main(String[] args)
    {
        connection();
    }

}
