package cs3500.hw04;

import cs3500.hw03.WhistModel;

/**
 * This is a factory class for WhistModel objects, and implements a create
 * method that will return either a standard WhistModel or a TrumpWhistModel
 * given a ModelType enum type.
 */
public class WhistModelCreator {
  public enum ModelType {
    TRUMP, NOTRUMP;
  }

  /**
   * Returns a new WhistModel of either standard variety or of the Trump variety
   * given a ModelType type, or throws an IllegalArgumentException if given a
   * bad enum type.
   *
   * @param type The type of WhistModel as an enum
   * @return a new WhistModel
   */
  public static WhistModel create(ModelType type) {
    switch (type) {
      case TRUMP:
        return new TrumpWhistModel();
      case NOTRUMP:
        return new WhistModel();
      default:
        throw new IllegalArgumentException("Bad Model Type");
    }
  }
}
