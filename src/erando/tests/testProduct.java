/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.Product;
import erando.services.impl.ProductService;
import erando.techniques.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import erando.services.interfaces.IShopService;

/**
 *
 * @author F.Mouhamed
 */
public class testProduct {
      public static void main(String[] args) {
        Product product = new Product(1,"test","test description",0,100,"04/05/1995","tesType",10,"::c/images","1,13,15",0);
        IShopService productService = new ProductService();
        //productService.add(product); //test adding product to DB
        //productService.delete(86);//test delete by id
        productService.getAll().forEach(System.out::println);//test show all
        //productService.findById(85).toString();//test find and show
    }
}
