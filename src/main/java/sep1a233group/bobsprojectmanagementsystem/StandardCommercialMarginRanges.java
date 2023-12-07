package sep1a233group.bobsprojectmanagementsystem;

/***/
public class StandardCommercialMarginRanges {
  // Attributes
  private static final double budgetFloor = 500000;
  private static final double budgetCeiling = 2000000;
  private static final int projectDurationFloor = 12;
  private static final int projectDurationCeiling = 24;

  /***/
  // Getter for budgetFloor
  public static double getBudgetFloor() {
    return budgetFloor;
  }

  /***/
  // Other getters and setters for the remaining attributes
  public static double getBudgetCeiling() {
    return budgetCeiling;
  }

  /***/
  public static int getProjectDurationFloor() {
    return projectDurationFloor;
  }

  /***/
  public static int getProjectDurationCeiling() {
    return projectDurationCeiling;
  }
}
