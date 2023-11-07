package com.example.myapplication;

public class contactModel {
    int image_id;
    String name;
    String number;
    String email;

  public  contactModel(int image_id,String name,String number){
        this.name= name;
        this.image_id = image_id;
        this.number = number;
    }
    public contactModel(String name,String number){
      this.name=name;
      this.number=number;
    }
    public contactModel(String name,String number,String email){
      this.name = name;
      this.number = number;
      this.email = email;
    }
    public contactModel(int image_id,String name,String number,String email){
      this.name = name;
      this.number = number;
      this.image_id = image_id;
      this.email = email;
    }
}
