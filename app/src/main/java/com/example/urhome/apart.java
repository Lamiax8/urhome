package com.example.urhome;


import java.io.Serializable;

public class apart implements Serializable {
    private int id;
    private String name;

    private int price;



    private String location;

    private int rooms;
    private String rentEmail = "NOT RENTED YET";

    public apart() {
    }

    public apart(int id, String name, int price, String location, int rooms, String rentEmail) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.location = location;
        this.rooms = rooms;
        this.rentEmail = rentEmail;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getRentEmail() {
        return rentEmail;
    }

    public void setRentEmail(String rentEmail) {
        this.rentEmail = rentEmail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }


    @Override
    public String toString() {
        if (id == -1) {
            return "Apartment Name: " + name + ",   Price: " + price + ",   Location: " + location + ",   Rooms: " + rooms +  ", Tenant: " + rentEmail;
        } else {
            return "ApartmentID: " + id + ",   Name: " + name + ",   Price: " + price + ",   Location: " + location + ",   Rooms: " + rooms + ", Tenant: " + rentEmail ;
        }
    }
}
