package br.epa.diego.agendaepai;

import java.io.Serializable;

/**
 * Created by Diego on 21/09/2015.
 */
public class Contact implements Serializable{
    private String name;
    private String phone;
    private String address;

    public Contact(String name, String phone, String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getName(){
        return this.name;
    }

    public String getPhone(){
        return this.phone;
    }

    public String getAddress(){
        return this.address;
    }
}