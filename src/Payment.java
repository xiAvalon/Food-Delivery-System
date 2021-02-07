public class Payment {
  private String ID;
  private Order order;
  private float amount;

  Payment(String id, Order order, float amount) {
    this.ID = id;
    this.order = order;
    this.amount = amount;
  }

  public void insertToDB() {

    DBAccessor.executeDB("INSERT INTO foodsystem.payment " +
        "(ID, ORDER_NUMBER, amount)" +
        "VALUES ('" +
        ID + "'," +
        order.getID() + "," +
        amount + ")" +
        "ON DUPLICATE KEY UPDATE" +
        "   ORDER_NUMBER = " + order.getID() + "," +
        "   amount = " + amount
    );
  }

  public String toString() {
    return "Restaurant Name: " + order + "\n" +
        "Address: " + amount + "\n";
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
}
