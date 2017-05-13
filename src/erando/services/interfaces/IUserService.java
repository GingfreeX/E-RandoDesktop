/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.interfaces;

import erando.models.User;

/**
 *
 * @author cimope
 */
public interface IUserService extends IService<User,Integer>{
    public boolean  Authentification(String login,String password);
    public User getUserbyId(int id);
    public String CheckRole(User u );
    public User getUserByEmail(String email);
    public void SendMailAndAddTokenToUser(User u,String HashedToken);
    public Boolean CheckToken(User user,String token);
    public Boolean CheckIfUserExist(String email);
    public void ressettingpassword(User u );
    public void SendSMSAndAddTokenToUser(String HashedToken,User u);
    public Boolean Checkconfirmationtoken(User user,String Token );
    public void ConfirmAccount(User user,String Token);
    public boolean checkEnabled(User u);
}
