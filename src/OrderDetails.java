public class OrderDetails {
  private long ID;
  private Order order;
  private Product product;
  private boolean cancelled;

  OrderDetails(long id, Order order, Product product, boolean cancelled) {
    this.ID = id;
    this.order = order;
    this.product = product;
    this.cancelled = cancelled;
  }

  public void insertToDB() {


    DBAccessor.executeDB("INSERT INTO foodsystem.order_detail " +
        "(ID, ORDER_NUMBER, PRODUCT_ID, cancelled)" +
        "VALUES (" +
        ID + "," +
        order.getID() + ",'" +
        product.getID() + "'," +
        cancelled + ")" +
        "ON DUPLICATE KEY UPDATE" +
        "   ORDER_NUMBER = " + order.getID() + "," +
        "   PRODUCT_ID = '" + product.getID() + "'," +
        "   cancelled = " + cancelled
    );
  }

  public String toString() {
    return "Order: " + order + "\n" +
        "Meal: " + product + "\n" +
        "Status: " + (cancelled ? "CANCELLED" : "NOT CANCELLED") + "\n";

  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
  }

  public boolean isCancelled() {
    return cancelled;
  }

  public void setCancelled(boolean cancelled) {
    this.cancelled = cancelled;
  }
}
