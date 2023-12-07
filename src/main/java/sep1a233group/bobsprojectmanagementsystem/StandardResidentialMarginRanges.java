package sep1a233group.bobsprojectmanagementsystem;

public class StandardResidentialMarginRanges {
  // Attributes
  private static final double budgetFloor = 100000;
  private static final double budgetCeiling = 500000;
  private static final int projectDurationFloor = 6;
  private static final int projectDurationCeiling = 12;


  // Getters
  public static double getBudgetFloor() {
    return budgetFloor;
  }
  // Other getters and setters for the remaining attributes
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

