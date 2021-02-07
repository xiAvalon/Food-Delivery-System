package fos.models;

import fos.services.DatabaseService;

public class Payment {
  private String ID;
  private Order order;
  private float amount;
  private PaymentType type;

  public Payment(String id, Order order, float amount, PaymentType type) {
    this.ID = id;
    this.order = order;
    this.amount = amount;
    this.type = type;
  }

  public void insertToDB() {

    DatabaseService.executeDB("INSERT INTO foodsystem.payment " + "(ID, ORDER_NUMBER, amount, type)" + "VALUES ('" + ID
        + "'," + order.getID() + "," + amount + ", '" + type + "')" + "ON DUPLICATE KEY UPDATE" + "   ORDER_NUMBER = "
        + order.getID() + "," + "   amount = " + amount);
  }

  public String toString() {
    return "Order: " + order + "\n" + "Amount: " + amount + "\n" + "Type: " + type + "\n";
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public float getAmount() {
    return amount;
  }

  public void setAmount(float amount) {
    this.amount = amount;
  }

  public String getID() {
    return ID;
  }

  public void setID(String ID) {
    this.ID = ID;
  }

  public PaymentType getPaymentType() {
    return type;
  }

  public void setPaymentType(PaymentType type) {
    this.type = type;
  }
}
