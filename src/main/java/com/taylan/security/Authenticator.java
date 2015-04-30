package com.taylan.security;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author  Taylan Kurt <taylankurt34@gmail.com>
 * 
 *      Authenticator
 */
public class Authenticator {
     private static final Map<String, String> USERS = new HashMap<String, String>();
     static {
        USERS.put("admin", "admin");
    }
     public static boolean validate(String user, String password){
        String validUserPassword = USERS.get(user);
        return validUserPassword != null && validUserPassword.equals(password);
    }  
}
