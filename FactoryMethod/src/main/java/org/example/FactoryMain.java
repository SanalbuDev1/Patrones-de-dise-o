package org.example;

import org.example.dao.ProductDAO;
import org.example.entity.Product;

import java.util.List;

public class FactoryMain {

    public static void main(String[] args) {

        // Productos a insertar
        Product product1 = new Product(3l,"Nintendo Switch oled2",1600000);
        Product product2 = new Product(4l,"Nintendo Switch clasica2",1300000);

        //Creamos una instancia del DAO
        ProductDAO productDAO = new ProductDAO();

        //Persistimos los productos
        productDAO.saveProduct(product1);
        productDAO.saveProduct(product2);

        //Consultamos los productos
        List<Product> listaDeProductos = productDAO.findAllProducts();
        for(Product pr1: listaDeProductos){
            System.out.println(pr1);
        }


    }

}
