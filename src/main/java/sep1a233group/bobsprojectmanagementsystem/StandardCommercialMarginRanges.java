package sep1a233group.bobsprojectmanagementsystem;

public class StandardCommercialMarginRanges {
  // Attributes
  private double budgetFloor, budgetCeiling;
  private int projectDurationFloor, projectDurationCeiling;

  // Constructor
  public StandardCommercialMarginRanges(double budgetFloor, double budgetCeiling, int projectDurationFloor, int projectDurationCeiling) {
    this.budgetFloor = budgetFloor;
    this.budgetCeiling = budgetCeiling;
    this.projectDurationFloor = projectDurationFloor;
    this.projectDurationCeiling = projectDurationCeiling;
  }

  // Getter for budgetFloor
  public double getBudgetFloor() {
    return budgetFloor;
  }

  // Setter for budgetFloor
  public void setBudgetFloor(double budgetFloor) {
    this.budgetFloor = budgetFloor;
  }

  // Other getters and setters for the remaining attributes
  public double getBudgetCeiling() {
    return budgetCeiling;
  }

  public void setBudgetCeiling(double budgetCeiling) {
    this.budgetCeiling = budgetCeiling;
  }

  public int getProjectDurationFloor() {
    return projectDurationFloor;
  }

  public void setProjectDurationFloor(int projectDurationFloor) {
    this.projectDurationFloor = projectDurationFloor;
  }

  public int getProjectDurationCeiling() {
    return projectDurationCeiling;
  }

  public void setProjectDurationCeiling(int projectDurationCeiling) {
    this.projectDurationCeiling = projectDurationCeiling;
  }
}