/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.ImageG;
import java.util.List;

/**
 *
 * @author wassim
 */
public interface IImageService extends IService<ImageG, Integer>{
    List<ImageG> getAllGroupImage(ImageG image);
    ImageG getImage(int id );
    List<ImageG> getImagewithId(int id_user,int id_group);
    
}
