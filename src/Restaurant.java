import java.util.ArrayList;

public class Restaurant {
  private final float deliveryTime;
  private long ID;
  private String name;
  private String address;
  private String userName;
  private String password;
  private long phoneNumber;
  private boolean open;
  private float minimumOrder;
  private float deliveryFee;
  private ArrayList<Product> meals = new ArrayList<>();


  Restaurant(long id, String name, String address,
             String userName, String password, long phoneNumber,
             boolean open, float minimumOrder,
             float time, float deliveryFee) {
    this.ID = id;
    this.name = name;
    this.address = address;
    this.userName = userName;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.open = open;
    this.minimumOrder = minimumOrder;
    this.deliveryTime = time;
    this.deliveryFee = deliveryFee;


  }

  public String toString() {
    return "Restaurant Name: " + name + "\n" +
        "Address: " + address + "\n" +
        "Phone Number: " + phoneNumber + "\n" +
        "Open/Closed: " + (open ? "OPEN" : "CLOSED") + "\n" +
        "Minimum Order: " + minimumOrder + "\n" +
        "Delivery Time: " + deliveryTime + "\n" +
        "Delivery Fee: " + deliveryFee + "\n";
  }


  public void addMeals() {
    for (Product X : Main.products)
      if (X.getRestaurant().getID() == getID())
        this.meals.add(X);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean getOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public void insertToDB() {
    DBAccessor.executeDB("INSERT INTO foodsystem.restaurant " +
        "(ID, RESTAURANT_NAME, RESTAURANT_ADDRESS, user_name, password" +
        ", phoneNumber, open, minimum_order, deliveryTime, deliveryFee)" +
        "VALUES (" +
        ID + ",'" +
        name + "','" +
        address + "','" +
        userName + "','" +
        password + "'," +
        phoneNumber + "," +
        open + "," +
        minimumOrder + "," +
        deliveryTime + "," +
        deliveryFee + ")" +
        "ON DUPLICATE KEY UPDATE" +
        "   RESTAURANT_NAME = '" + name + "'," +
        "   RESTAURANT_ADDRESS = '" + address + "'," +
        "   user_name = '" + userName + "'," +
        "   password = '" + password + "'," +
        "   phoneNumber = " + phoneNumber + "," +
        "   open = " + open + "," +
        "   minimum_order = " + minimumOrder + "," +
        "   deliveryTime = " + deliveryTime + "," +
        "   deliveryFee = " + deliveryFee
    );
  }

  public float getMinimumOrder() {
    return minimumOrder;
  }

  public void setMinimumOrder(float minimumOrder) {
    this.minimumOrder = minimumOrder;
  }

  public float getDeliveryTime() {
    return deliveryTime;
  }

  public float getDeliveryFee() {
    return deliveryFee;
  }

  public void setDeliveryFee(float deliveryFee) {
    this.deliveryFee = deliveryFee;
  }

  public ArrayList<Product> getMeals() {
    return meals;
  }

  public void setMeals(ArrayList<Product> meals) {
    this.meals = meals;
  }
}
