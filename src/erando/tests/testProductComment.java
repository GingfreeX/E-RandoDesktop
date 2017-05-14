/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.tests;

import erando.models.Product;
import erando.models.ProductComment;
import erando.services.impl.ProductCommentService;
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
public class testProductComment {
      public static void main(String[] args) {
        ProductComment productcomment = new ProductComment(0,85,0,"testcomment","04/05/1995");
        IShopService productcommentService = new ProductCommentService();
        //productcommentService.add(productcomment); //test adding comment to DB
        //productcommentService.delete(14);//test delete by id
        //productcommentService.getAll().forEach(System.out::println);//test show all
        //productcommentService.findById(85).toString();//test find and show
    }
}
