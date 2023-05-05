package org.example.impl;

import oracle.jdbc.driver.OracleDriver;
import org.example.IDBAdapter;
import org.example.util.PropertiesUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLDBAdapter implements IDBAdapter {

    private static final String DB_PROPERTIES  = "META-INF/DBMySQL.properties";
    private static final String DB_NAME_PROP = "dbname";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PASSWORD_PROP= "password";
    private static final String DB_PORT_PROP = "port";
    private static final String DB_USER_PROP = "user";

    static{
        try{
            new com.mysql.cj.jdbc.Driver();
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
        String db = prop.getProperty(DB_NAME_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);

        String connectionString = "jdbc:mysql://" + host
                + ":" + port + "/" + db + "?user=" + user + "&password=" + password;
        System.out.println("Connection " + connectionString);

        return connectionString;
    }

}
