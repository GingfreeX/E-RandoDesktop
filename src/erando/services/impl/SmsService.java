/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import erando.services.interfaces.ISms;



/**
 *
 * @author momo
 */
public class SmsService implements ISms{
 public static final String ACCOUNT_SID = "AC888e7d0423137d86274fb84425991923";
    public static final String AUTH_TOKEN = "96dd3326b88cc5f24a57bbc6136deb37";
    @Override
    public void sendSms(Sms sms) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message msg = Message.creator(new PhoneNumber(sms.getNum()), new PhoneNumber("+17146768843"), sms.getMessagetel()).create();
            System.out.println(msg.getSid());

    }
    
    
    
}