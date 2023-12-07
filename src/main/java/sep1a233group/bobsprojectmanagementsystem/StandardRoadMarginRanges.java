package sep1a233group.bobsprojectmanagementsystem;

public class StandardRoadMarginRanges {
  // Attributes
  private static final double budgetFloor = 1000000;
  private static final double budgetCeiling = 5000000;
  private static final int projectDurationFloor = 12;
  private static final int projectDurationCeiling = 24;

  // Getter methods
  public static double getBudgetFloor() {
    return budgetFloor;
  }

  public static double getBudgetCeiling() {
    return budgetCeiling;
  }

  public static int getProjectDurationFloor() {
    return projectDurationFloor;
  }

  public static int getProjectDurationCeiling() {
    return projectDurationCeiling;
  }


}
