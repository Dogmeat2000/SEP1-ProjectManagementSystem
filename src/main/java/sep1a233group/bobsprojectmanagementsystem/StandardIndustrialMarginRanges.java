package sep1a233group.bobsprojectmanagementsystem;

public class StandardIndustrialMarginRanges
{
  // Attributes
  private static final double  budgetFloor = 2000000;
  private static final double budgetCeiling = 10000000;
  private static final int projectDurationFloor = 24;
  private static final int projectDurationCeiling = 36;

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

