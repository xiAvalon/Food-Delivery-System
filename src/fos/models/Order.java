package fos.models;

import fos.services.DatabaseService;

public class Order {
  private long ID;
  private float totalPrice;
  private Customer customer;
  private Restaurant restaurant;
  private Rider rider;
  private Boolean delivered;

  public Order(long ID, float totalPrice, Customer customer, Restaurant restaurant, Rider rider, boolean delivered) {
    this.ID = ID;
    this.totalPrice = totalPrice;
    this.customer = customer;
    this.restaurant = restaurant;
    this.rider = rider;
    this.delivered = delivered;
  }

  public void insertToDB() {
    DatabaseService.executeDB(
        "INSERT INTO foodsystem.orders " + "(ID, ORDER_TOTALPRICE, CUSTOMER_NUMBER, RESTAURANT_NUMBER, RIDER_NUMBER,"
            + "delivered )" + "VALUES (" + ID + "," + totalPrice + "," + customer.getID() + "," + restaurant.getID()
            + "," + rider.getID() + "," + delivered + ")" + "ON DUPLICATE KEY UPDATE" + "   ORDER_TOTALPRICE = "
            + totalPrice + "," + "   CUSTOMER_NUMBER = " + customer.getID() + "," + "   RESTAURANT_NUMBER = "
            + restaurant.getID() + "," + "   RIDER_NUMBER = " + rider.getID() + "," + "   delivered = " + delivered);
  }

  public String toString() {
    return "Total Price: " + totalPrice + "\n" + "Customer Details: " + customer + "\n" + "Restaurant Details: "
        + restaurant + "\n" + "Rider Details: " + rider + "\n";
  }

  public float getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(float totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }

  public Rider getRider() {
    return rider;
  }

  public void setRider(Rider rider) {
    this.rider = rider;
  }

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
  }

  public Boolean getDelivered() {
    return delivered;
  }

  public void setDelivered(Boolean delivered) {
    this.delivered = delivered;
  }
}
