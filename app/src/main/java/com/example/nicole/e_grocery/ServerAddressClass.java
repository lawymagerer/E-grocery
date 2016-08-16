package com.example.nicole.e_grocery;

/**
 * Created by Nicole on 3/8/2016.
 */
public class ServerAddressClass {
    String address;
    public ServerAddressClass(){
        address="http://10.55.24.29:8080/EgroceryWeb/";
    }

    protected String serverAddress(){

        return this.address;
    }
}
