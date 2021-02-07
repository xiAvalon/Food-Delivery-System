package fos.models;

import java.util.HashMap;

public enum UserKind {
  customer(1), rider(2), restaurant(3);

  private static final HashMap<Integer, UserKind> map = new HashMap<>();

  static {
    for (UserKind pageType : UserKind.values())
      map.put(pageType.value, pageType);
  }

  private final int value;

  UserKind(int value) {
    this.value = value;
  }

  public static UserKind valueOf(int UserKind) {
    return (UserKind) map.get(UserKind);
  }
}
