package fos.models;

import fos.services.DatabaseService;

public class Rider {
  private long ID;
  private String name;
  private String vehiclePlate;
  private String userName;
  private String password;
  private long phoneNumber;

  public Rider(long id, String name, String vehiclePlate, String userName, String password, long phoneNumber) {
    this.ID = id;
    this.name = name;
    this.vehiclePlate = vehiclePlate;
    this.userName = userName;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  public String toString() {
    return "Rider Name: " + name + "\n" + "Vehicle Plate: " + vehiclePlate + "\n" + "Phone Number: " + phoneNumber
        + "\n";
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getVehiclePlate() {
    return vehiclePlate;
  }

  public void setVehiclePlate(String vehiclePlate) {
    this.vehiclePlate = vehiclePlate;
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

    DatabaseService.executeDB("INSERT INTO foodsystem.rider " + "(ID, RIDER_NAME, VEHICLE_PLATE, user_name, password"
        + ", phoneNumber)" + "VALUES (" + ID + ",'" + name + "','" + vehiclePlate + "','" + userName + "','" + password
        + "'," + phoneNumber + ")" + "ON DUPLICATE KEY UPDATE" + "   RIDER_NAME = '" + name + "',"
        + "   VEHICLE_PLATE = '" + vehiclePlate + "'," + "   user_name = '" + userName + "'," + "   password = '"
        + password + "'," + "   phoneNumber = " + phoneNumber);
  }
}
