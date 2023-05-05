package org.example;

import org.example.impl.MySQLDBAdapter;
import org.example.impl.OracleDBAdapter;
import org.example.util.PropertiesUtil;

import java.util.Properties;

public class DBFactory {

    private static final String DB_FACTORY_PROPERTY_URL  = "META-INF/DBFactory.properties";
    private static final String DEFAULT_DB_CLASS_PROP = "defaultDBClass";

    public static IDBAdapter getDBadapter(DBType dbType){
        switch (dbType){
            case MySQL:
                return new MySQLDBAdapter();
            case Oracle:
                return new OracleDBAdapter();
            default:
                throw new IllegalArgumentException("No soportado el motor de BD");
        }
    }

    public static IDBAdapter getDefaultDBAdapter(){
        try{
            Properties prop = PropertiesUtil.loadProperty(DB_FACTORY_PROPERTY_URL);
            String defaultDbClass = prop.getProperty(DEFAULT_DB_CLASS_PROP);
            System.out.println("DefaultDBClass ==> " + defaultDbClass);
            return (IDBAdapter) Class.forName(defaultDbClass).newInstance();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }


}
