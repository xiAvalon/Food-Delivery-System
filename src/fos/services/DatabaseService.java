package fos.services;

import fos.models.*;
import fos.Main;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseService {
  static private Connection conn;
  static private Statement statement;

  public DatabaseService() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/foodsystem?user=root");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  static public ResultSet executeDB(String sql) {
    try {
      statement = conn.createStatement();
      if (statement.execute(sql)) {
        return statement.getResultSet();
      } else
        return null;

    } catch (SQLException ex) {
      // handle any errors
      ex.printStackTrace();
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
    return null;
  }

  static private Object findRowBy(long ID, String className) {
    switch (className) {
      case "customer":
        return (Main.customers.stream().filter(customer -> ID == customer.getID()).findAny().orElse(null));
      case "order":
        return Main.orders.stream().filter(order -> ID == order.getID()).findAny().orElse(null);
      case "orderDetail":
        return Main.orderDetailsList.stream().filter(orderDetail -> ID == orderDetail.getID()).findAny().orElse(null);
      case "restaurant":
        return Main.restaurants.stream().filter(restaurant -> ID == restaurant.getID()).findAny().orElse(null);
      case "rider":
        return Main.riders.stream().filter(rider -> ID == rider.getID()).findAny().orElse(null);
      default:
        assert (false);
    }
    return null;
  }

  static private Object findRowBy(String ID, String className) {
    switch (className) {
      case "payment":
        return Main.payments.stream().filter(payment -> ID.contains(payment.getID())).findAny().orElse(null);
      case "product":
        return Main.products.stream().filter(product -> ID.contains(product.getID())).findAny().orElse(null);
      default:
        assert (false);
    }
    return null;
  }

  static public ArrayList<Customer> customersToArrayList() {
    try {
      statement = conn.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    ResultSet rs = executeDB("SELECT * FROM `customer`");
    ArrayList<Customer> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.printStackTrace();

      }
      try {
        results.add(new Customer(rs.getLong("ID"), rs.getString(2), rs.getString(3), rs.getString("customer_name"),
            rs.getString("customer_address"), rs.getLong(6), rs.getString(7)));
      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
    return results;
  }

  static public ArrayList<Order> orderToArrayList() {
    try {
      statement = conn.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    ResultSet rs = executeDB("SELECT * FROM `orders`");
    ArrayList<Order> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.printStackTrace();

      }
      try {
        results.add(new Order(rs.getLong(1), rs.getFloat(2), (Customer) findRowBy(rs.getLong(3), "customer"),
            (Restaurant) findRowBy(rs.getLong(4), "restaurant"), (Rider) findRowBy(rs.getLong(5), "rider"),
            rs.getBoolean(6)));
      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
    return results;
  }

  public static ArrayList<Restaurant> restaurantsToArrayList(boolean onlyAvailable) {
    try {
      statement = conn.createStatement();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    ResultSet rs;
    if (onlyAvailable)
      rs = executeDB("SELECT * FROM `restaurant` where open = 1");
    else
      rs = executeDB("SELECT * FROM `restaurant`");

    ArrayList<Restaurant> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.printStackTrace();

      }
      try {
        results.add(new Restaurant(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
            rs.getLong(6), rs.getBoolean(7), rs.getFloat(8), rs.getFloat(9), rs.getFloat(10)));
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return results;
  }

  public static ArrayList<Payment> paymentsToArrayList() {
    try {
      statement = conn.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    ResultSet rs = executeDB("SELECT * FROM `payment`");
    ArrayList<Payment> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.printStackTrace();

      }
      try {
        results.add(new Payment(rs.getString(1), (Order) findRowBy(rs.getLong(2), "order"), rs.getFloat(3)));
      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
    return results;
  }

  public static ArrayList<OrderDetails> orderDetailToArrayList() {
    try {
      statement = conn.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    ResultSet rs = executeDB("SELECT * FROM `order_detail`");
    ArrayList<OrderDetails> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.getMessage();
      }
      try {
        results.add(new OrderDetails(rs.getLong(1), (Order) findRowBy(rs.getInt(2), "order"),
            (Product) findRowBy(rs.getString(3), "product"), rs.getBoolean(4)));
      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
    return results;
  }

  public static ArrayList<Product> productsToArrayList() {
    try {
      statement = conn.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    ResultSet rs = executeDB("SELECT * FROM `product`");
    ArrayList<Product> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.printStackTrace();

      }
      try {
        results.add(new Product(rs.getString(1), rs.getString(2), rs.getFloat(3),
            (Restaurant) findRowBy(rs.getLong(4), "restaurant")));

      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
    return results;
  }

  public static ArrayList<Rider> ridersToArrayList() {
    try {
      statement = conn.createStatement();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    ResultSet rs = executeDB("SELECT * FROM `rider`");
    ArrayList<Rider> results = new ArrayList<>();
    while (true) {
      assert rs != null;
      try {
        if (!rs.next())
          break;
      } catch (SQLException e) {
        e.printStackTrace();

      }
      try {
        results.add(new Rider(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
            rs.getLong(6)));
      } catch (SQLException e) {
        e.printStackTrace();

      }
    }
    return results;
  }
}
