package fos.models;

import fos.services.DatabaseService;

public class Product {
  private String ID;
  private String name;
  private float price;
  private Restaurant restaurant;

  public Product(String id, String name, float price, Restaurant restaurant) {
    this.ID = id;
    this.name = name;
    this.price = price;
    this.restaurant = restaurant;
  }

  public void insertToDB() {

    DatabaseService.executeDB("INSERT INTO foodsystem.product " + "(ID, PRODUCT_NAME, PRODUCT_PRICE, RESTAURANT_ID)"
        + "VALUES ('" + ID + "','" + name + "'," + price + "," + restaurant.getID() + ")" + "ON DUPLICATE KEY UPDATE"
        + "   PRODUCT_NAME = '" + name + "'," + "   PRODUCT_PRICE = " + price + "," + "   RESTAURANT_ID = "
        + restaurant.getID());
  }

  public String toString() {
    return "Meal Name: " + name + "\n" + "Price: " + price + "\n";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

}
