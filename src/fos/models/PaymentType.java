package fos.models;

public enum PaymentType {
  CASH("Cash"), CREDIT_CARD("Credit Card");

  private final String type;

  PaymentType(final String type) {
    this.type = type;
  }

  public static PaymentType of(String type) {
    return type.equals("Cash") ? PaymentType.CASH : PaymentType.CREDIT_CARD;
  }

  @Override
  public String toString() {
    return type;
  }
}
