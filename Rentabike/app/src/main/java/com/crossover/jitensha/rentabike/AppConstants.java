package com.crossover.jitensha.rentabike;

/**
 * Created by abhishek on 15/12/16.
 */

public class AppConstants {

    // Server user login url
    private static final String URL_SERVER = "http://192.168.0.100:8080";

    public static final String URL_REGISTER = URL_SERVER + "/api/v1/register";
    public static final String URL_LOGIN = URL_SERVER + "/api/v1/auth";
    public static final String URL_PLACES = URL_SERVER + "/api/v1/places";
    public static final String URL_RENT = URL_SERVER + "/api/v1/rent";
}
