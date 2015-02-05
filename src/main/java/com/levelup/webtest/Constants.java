package com.levelup.webtest;

/**
 * Created by denis_zavadsky on 1/31/15.
 */
public class Constants {

    public final static String LOGIN_EMAIL_PARAMETER = "email";
    public final static String LOGIN_PASSWORD_PARAMETER = "password";

    public final static String LOGIN_ROLE_PARAMETER_ADMIN = "Admin";
    public final static String LOGIN_ROLE_PARAMETER_USER = "User";

    public final static String LOGIN = "Login";
    public final static String LOGOUT = "LogOut";



    public final static String LOGIN_USER_QUERY = "SELECT * FROM User WHERE email = ?";
}
