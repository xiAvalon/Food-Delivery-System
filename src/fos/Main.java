package fos;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import fos.services.DatabaseService;
import fos.models.*;

public class Main {
  static {
    new DatabaseService();
  }
  public static ArrayList<Customer> customers = DatabaseService.customersToArrayList();
  public static ArrayList<Restaurant> restaurants = DatabaseService.restaurantsToArrayList(false);
  public static ArrayList<Restaurant> availableRestaurants = DatabaseService.restaurantsToArrayList(true);
  public static ArrayList<Rider> riders = DatabaseService.ridersToArrayList();
  public static ArrayList<Order> orders = DatabaseService.orderToArrayList();
  public static ArrayList<Product> products = DatabaseService.productsToArrayList();
  public static ArrayList<OrderDetails> orderDetailsList = DatabaseService.orderDetailToArrayList();
  public static ArrayList<Payment> payments = DatabaseService.paymentsToArrayList();
  public static Cart cart = new Cart();
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    if (args.length > 0) {
      updateAll(customers, restaurants, products, riders, orders, orderDetailsList, payments);
      args = new String[0];
    }
    System.out.println("\t\t\t\tWELCOME TO FOS");
    System.out.println("Do you have an account?");
    String choice = scanner.nextLine();
    if (choice.matches("[Yy]|[Yy]es"))
      login(null);
    else if (choice.matches("[Nn]|[Nn]o"))
      register();
    else
      main(args);
  }

  private static void updateAll(ArrayList... data) {
    for (ArrayList Y : data)
      for (Object X : Y) {
        try {
          X.getClass().getMethod("insertToDB").invoke(X);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
  }

  private static void register() {

    UserType type = checkType();
    String userName;
    try {
      userName = compare("UserName", customers, riders, restaurants);
    } catch (NullPointerException e) {
      userName = compare("UserName", customers, riders, restaurants);
    }

    System.out.println("please enter your first name");
    String name = scanner.next();
    System.out.println("please enter your last name");
    name = name.concat(" " + scanner.next());

    System.out.println("please enter your password");
    String password = scanner.next();

    System.out.println("please enter your email");
    String email = scanner.next(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    String address;
    try {
      System.out.println("please enter your address");
      address = scanner.next("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.");
    } catch (InputMismatchException e) {
      System.out.println("please enter your address");
      address = scanner.next("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.");
    }

    assert type != null;
    String phoneNumber = compare("PhoneNumber", customers, riders, restaurants);
    ResultSet rs = DatabaseService.executeDB("SELECT * FROM " + type.name() + " ORDER BY ID DESC LIMIT 1");
    long ID = 0;
    try {
      assert rs != null;
      rs.next();
      ID = rs.getLong(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    switch (type) {
      case customer -> {
        customers.add(new Customer(++ID, userName, password, name, address, Long.parseLong(phoneNumber), email));
        customers.get(customers.size() - 1).insertToDB();
        login(customers.get(customers.size() - 1));
      }
      case rider -> {
        String plate = compare("VehiclePlate", riders);
        riders.add(new Rider(++ID, name, plate, userName, password, Long.parseLong(phoneNumber)));
        riders.get(riders.size() - 1).insertToDB();
        login(riders.get(riders.size() - 1));
      }
      case restaurant -> {
        System.out.println("please enter the minimum order");
        float minimum = scanner.nextFloat();
        System.out.println("please enter the delivery time in minutes");
        int time = scanner.nextInt();
        System.out.println("please enter the delivery fee");
        float fee = scanner.nextFloat();

        restaurants.add(new Restaurant(++ID, name, address, userName, password, Long.parseLong(phoneNumber), false,
            minimum, time, fee));
        restaurants.get(restaurants.size() - 1).insertToDB();
        login(restaurants.get(restaurants.size() - 1));
      }
    }

  }

  private static UserType checkType() {
    System.out.println("How would you like to use the system? ");
    System.out.println("Customer->1, Rider->2, Restaurant->3");
    int input = scanner.nextInt();
    if (input > 3 || input < 1)
      checkType();
    else
      return UserType.valueOf(input);
    assert (false);
    return null;
  }

  private static String compare(String what, ArrayList... data) {
    System.out.println("please enter a " + what);
    String choice;
    choice = scanner.next();
    if (what.equals("PhoneNumber")) {
      try {
        Long.parseLong(choice);
      } catch (NumberFormatException e) {
        return compare(what, data);
      }
    }
    for (ArrayList Y : data) {
      for (Object X : Y) {
        try {
          if (X.getClass().getMethod("get" + what).invoke(X).equals(choice))
            return compare(what);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
    }
    return choice;

  }

  private static Object findByUsername(ArrayList... data) {
    String choice;
    System.out.println("Please enter your username");
    choice = scanner.next();
    for (ArrayList Y : data) {
      for (Object X : Y) {
        try {
          if (X.getClass().getMethod("getUserName").invoke(X).equals(choice))
            return X;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
    }
    return null;
  }

  private static void comparePass(String what, String data) {
    System.out.println("please enter the " + what);
    String choice = scanner.next();
    if (!data.equals(choice))
      comparePass(what, data);
  }

  private static void login(Object user) {
    if (user == null) {
      user = findByUsername(customers, riders, restaurants);
      if (user == null)
        login(null);

      try {
        comparePass("password", (String) user.getClass().getMethod("getPassword").invoke(user));
      } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NullPointerException e) {
        e.printStackTrace();
      }
    }
    if (user instanceof Customer)
      customerOptions((Customer) user);
    else if (user instanceof Restaurant)
      restaurantOptions((Restaurant) user);
    else if (user instanceof Rider)
      riderOptions((Rider) user);
    else
      adminOptions();
  }

  private static void adminOptions() {

  }

  private static void customerOptions(Customer user) {
    System.out.println("Enter 1 to make a new order");
    System.out.println("Enter 2 to show available restaurants");
    System.out.println("Enter 3 to show cart");
    System.out.println("Enter 4 to change your details");
    System.out.println("Enter 5 to show order history");
    System.out.println("Enter 0 to logout");

    int choice = checkOptions(5);

    switch (choice) {
      case 1 -> {
        Restaurant restaurant = chooseRestaurant();
        restaurant.addMeals();

        while (true) {
          int counter1 = 0;
          for (Product X : restaurant.getMeals()) {
            counter1++;
            System.out.println(X);
          }
          if (counter1 == 0) {
            System.out.println("restaurant does not offer meals at the moment");
            customerOptions(user);
          }

          System.out.println("Choose meals to add ");
          int mealNum, counter = 0, choice2;
          for (Product X : restaurant.getMeals())
            System.out.println("Meal " + (counter++) + "\n" + X);
          mealNum = checkOptions(counter);
          Cart.add(restaurant.getMeals().get(mealNum));
          System.out.println("Enter 1 to continue adding to cart.");
          System.out.println("Enter 0 to go back.");
          choice2 = checkOptions(1);
          if (choice2 == 0)
            break;
        }
        Cart.show();
        System.out.println("do you want to confirm the order?");
        System.out.println("enter 1 to confirm");
        System.out.println("enter 2 to update cart");
        System.out.println("enter 3 to delete from cart");
        System.out.println("enter 0 to discard");
        int choice3 = checkOptions(3);

        switch (choice3) {
          case 1 -> cart.updateAll(user);
          case 2 -> {
            System.out.println("Enter number of meal you want to change");
            int choice4 = checkOptions(Cart.getCart().size() - 1);
            System.out.println(restaurant.getMeals());
            System.out.println("enter number of meal");
            int choice5 = checkOptions(restaurant.getMeals().size() - 1);
            Cart.getCart().remove(choice4);
            Cart.add(restaurant.getMeals().get(choice5));
          }
          case 3 -> {
            System.out.println("Enter number of meal you want to delete");
            int choice4 = checkOptions(Cart.getCart().size() - 1);
            System.out.println(restaurant.getMeals());
            Cart.getCart().remove(choice4);
          }
        }
        cart.updateAll(user);
      }
      case 2 -> {
        for (Restaurant X : availableRestaurants)
          System.out.println(X);
      }
      case 3 -> {
        int counter = 0;
        for (Product X : Cart.getCart())
          System.out.println("[" + counter++ + "]" + X);
        System.out.println("Choose item to delete");
        System.out.println("enter -1 to go back");
        int choice1 = checkOptions(counter);
        if (choice1 == -1)
          break;
        Cart.delete(Cart.getCart().get(choice1));
      }
      case 4 -> {
        System.out.println("\t\t\t\t" + user.getName() + "'s profile details");
        System.out.println(user);
        System.out.println("Enter number of detail");
        System.out.println("[0] for name");
        System.out.println("[1] for password");
        System.out.println("[2] for address");
        System.out.println("[3] for phoneNumber");
        System.out.println("[4] for email");
        System.out.println("[-1] to go back");
        int choice1 = checkOptions(4);
        switch (choice1) {
          case 0 -> user.setName(scanner.next());
          case 1 -> user.setPassword(scanner.next());
          case 2 -> user.setAddress(scanner.next());
          case 3 -> user.setPhoneNumber(scanner.nextLong());
          case 4 -> user.setEmail(scanner.next());
          case -1 -> customerOptions(user);
        }
      }
      case 5 -> {
        for (Order X : orders)
          if (X.getDelivered() && X.getCustomer() == user)
            System.out.println(X);
      }
      case 0 -> main(new String[1]);
    }
    customerOptions(user);
  }

  private static Restaurant chooseRestaurant() {
    int choice, rest = 0;
    for (Restaurant X : availableRestaurants)
      System.out.println("Restaurant " + (rest++) + ": " + X);

    System.out.println("\nChoose a restaurant ");
    choice = checkOptions(rest);
    return availableRestaurants.get(choice);
  }

  private static int checkOptions(int max) {
    int input = scanner.nextInt();
    if (input > max || input < -1)
      checkType();
    else
      return input;
    assert (false);
    return 0;
  }

  private static void restaurantOptions(Restaurant user) {
    System.out.println("Enter 1 to show orders");
    System.out.println("Enter 2 show menu");
    System.out.println("Enter 3 edit menu");
    System.out.println("Enter 4 set Open/Closed");
    System.out.println("Enter 0 to logout");

    int choice = checkOptions(4);
    switch (choice) {
      case 1 -> {
        for (Order Y : orders)
          for (OrderDetails X : orderDetailsList) {
            if (Y.getRestaurant() == user && X.getOrder() == Y)
              System.out.println(Y);
          }
      }
      case 2 -> {
        for (Product X : products)
          if (X.getRestaurant() == user)
            System.out.println(X);
      }
      case 3 -> {
        ArrayList<Product> meals = new ArrayList<>();
        int counter = 0;
        for (Product X : products)
          if (X.getRestaurant() == user) {
            meals.add(X);
            System.out.println("[" + counter++ + "]" + X);
          }
        System.out.println("Choose item to update");
        int choice1 = checkOptions(counter);
        System.out.println("Choose what would you like to change");
        System.out.println("enter 1 to change name");
        System.out.println("enter 2 to change price");
        System.out.println("enter 0 to change to go back");
        int choice2 = checkOptions(2);
        switch (choice2) {
          case 1 -> meals.get(choice1).setName(scanner.next());
          case 2 -> meals.get(choice1).setPrice(scanner.nextFloat());
          case 0 -> main(new String[1]);
        }

      }
      case 4 -> {
        System.out.println("restaurant is: " + (user.getOpen() ? "OPEN" : "CLOSED"));
        System.out.println("enter 1 for opening.");
        System.out.println("enter 0 for closing.");
        int choice1 = checkOptions(1);
        user.setOpen(choice1 == 1);
      }
      case 0 -> main(new String[1]);
    }
    restaurantOptions(user);
  }

  private static void riderOptions(Rider user) {
    System.out.println("Enter 1 to show assigned orders");
    System.out.println("Enter 2 to show order details");
    System.out.println("Enter 3 to confirm order delivery");
    System.out.println("Enter 0 to logout");
    int choice = checkOptions(3);

    switch (choice) {
      case 1 -> {
        for (Order Y : orders)
          for (OrderDetails X : orderDetailsList) {
            if (Y.getRider() == user && X.getOrder() == Y)
              System.out.println(Y);
          }
      }
      case 2 -> {
        for (OrderDetails X : orderDetailsList)
          if (X.getOrder().getRider() == user)
            System.out.println(X);
      }
      case 3 -> {
        int choice1, counter = 0;
        ArrayList<Order> orderss = new ArrayList<>();
        for (Order Y : orders)
          for (OrderDetails X : orderDetailsList)
            if (Y.getRider() == user && X.getOrder() == Y) {
              System.out.println("[" + (counter++) + "]" + Y);
              orderss.add(Y);
            }
        System.out.println("Choose an order to confirm");
        choice1 = checkOptions(counter);
        System.out.println("Enter 1 to confirm");
        System.out.println("Enter 0 to un-confirm");
        int choice2 = checkOptions(1);
        orderss.get(choice1).setDelivered(choice2 == 1);
      }
      case 0 -> main(new String[1]);
    }
    riderOptions(user);
  }

}
