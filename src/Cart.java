import java.util.ArrayList;

public class Cart {
  private static ArrayList<Product> cart = new ArrayList<>();

  public static void add(Product product) {
    cart.add(product);
  }


  public static void delete(Product product) {
    cart.remove(product);
  }

  public static void show() {
    System.out.println("\t\t\t\t CART");
    for (Product X : cart)
      System.out.println(X);
  }

  public static ArrayList<Product> getCart() {
    return cart;
  }

  public static void setCart(ArrayList<Product> cart) {
    Cart.cart = cart;
  }

  public void updateAll(Customer user) {

  }
}
