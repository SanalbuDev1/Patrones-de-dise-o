package org.example.impl;

import oracle.jdbc.driver.OracleDriver;
import org.example.IDBAdapter;
import org.example.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDBAdapter implements IDBAdapter {

    private static final String DB_PROPERTIES  = "META-INF/DBOracle.properties";
    private static final String DB_SERVICE_PROP = "service";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP= "password";
    private static final String DB_PORT_PROP = "port";
    private static final String DB_USER_PROP = "user";

    static{
        try{
            new OracleDriver();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {

        try{
            String conectionString = createConnectionString();
            Connection connection = DriverManager.getConnection(conectionString);
            System.out.printf("Connection " + connection);
            return connection;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }




    }

    private String createConnectionString(){
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String service = prop.getProperty(DB_SERVICE_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);

        String connectionString = "jdbc:oracle:thin:"
                +user+"/"+password+"@//"+host+":"+port+"/"+service;

        System.out.printf("Connection " + connectionString);

        return connectionString;
    }
}
