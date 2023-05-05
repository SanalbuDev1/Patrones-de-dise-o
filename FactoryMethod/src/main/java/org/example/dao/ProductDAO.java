package org.example.dao;

import org.example.DBFactory;
import org.example.DBType;
import org.example.IDBAdapter;
import org.example.entity.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

public class ProductDAO {

    private IDBAdapter idbAdapter;

    public ProductDAO(){
        idbAdapter = DBFactory.getDefaultDBAdapter();
    }

    public ProductDAO(DBType dbType){
        idbAdapter = DBFactory.getDBadapter(dbType);
    }

    public List<Product> findAllProducts(){
        Connection connection = idbAdapter.getConnection();
        List<Product> productList = new ArrayList<Product>();
        String sql = "SELECT idProductos,productName,productPrice from productos2";
        try{
            PreparedStatement pStatement = connection.prepareStatement(sql);
            ResultSet rSet = pStatement.executeQuery();
            while(rSet.next()){
                Product tempProduct = new Product(rSet.getLong(1),
                        rSet.getString(2),
                        rSet.getDouble(3));
                productList.add(tempProduct);
            }
            return productList;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean saveProduct(Product product){
        Connection connection = idbAdapter.getConnection();
        try {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO Productos2(idProductos,"
                            + "productName, productPrice) VALUES (?,?,?)");
            statement.setLong(1, product.getIdProduct());
            statement.setString(2, product.getProductName());
            statement.setDouble(3, product.getPrice());
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            try {
                connection.close();
            } catch (Exception e) {}
        }
    }

}
