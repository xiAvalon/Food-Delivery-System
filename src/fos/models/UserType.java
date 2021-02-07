package fos.models;

import java.util.HashMap;

public enum UserType {
  customer(1), rider(2), restaurant(3);

  private static final HashMap<Integer, UserType> map = new HashMap<>();

  static {
    for (UserType pageType : UserType.values())
      map.put(pageType.value, pageType);
  }

  private final int value;

  UserType(int value) {
    this.value = value;
  }

  public static UserType valueOf(int UserType) {
    return (UserType) map.get(UserType);
  }
}
