package fos.models;

import fos.services.DatabaseService;

public class Customer {
  private long ID;
  private String name;
  private String address;
  private String userName;
  private String password;
  private long phoneNumber;
  private String email;

  public Customer(long ID, String userName, String password, String name, String address, long phoneNumber,
      String email) {
    this.ID = ID;
    this.name = name;
    this.address = address;
    this.userName = userName;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.email = email;
  }

  public String toString() {
    return "Customer Name: " + name + "\n" + "Address: " + address + "\n" + "Phone Number: " + phoneNumber + "\n"
        + "E-mail: " + email + "\n";

  }

  public void insertToDB() {
    DatabaseService.executeDB("INSERT INTO foodsystem.customer " + "(ID, user_name, password, CUSTOMER_NAME, "
        + "CUSTOMER_ADDRESS, phoneNumber, email)" + "VALUES (" + ID + ",'" + userName + "','" + password + "','" + name
        + "','" + address + "'," + phoneNumber + ",'" + email + "')" + "ON DUPLICATE KEY UPDATE" + "   user_name = '"
        + userName + "'," + "   password = '" + password + "'," + "   CUSTOMER_NAME = '" + name + "',"
        + "   CUSTOMER_ADDRESS = '" + address + "'," + "   phoneNumber = " + phoneNumber + "," + "   email = '" + email
        + "'");
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public long getID() {
    return ID;
  }

  public void setID(long ID) {
    this.ID = ID;
  }

  public long getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(long phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
