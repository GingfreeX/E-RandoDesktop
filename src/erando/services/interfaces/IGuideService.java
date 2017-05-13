/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erandopi.services.interfaces;
import java.util.List;
import erandopi.models.Guide;
/**
 *
 * @author student1
 */
public interface IGuideService   extends IService<Guide, Integer>  {
    public void BannGuide(Guide g);
    public void ApprouvGuide(Guide g);

}
