package sep1a233group.bobsprojectmanagementsystem;

public class StandardResidentialMarginRanges {
  // Attributes
  private double budgetFloor, budgetCeiling;
  private int projectDurationFloor, projectDurationCeiling;

  // Constructor
  public StandardResidentialMarginRanges(double budgetFloor, double budgetCeiling, int projectDurationFloor, int projectDurationCeiling) {
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

  // Additional Methods

  // Calculate average budget
  /** Calculates and returns the average budget between 'budgetFloor' and 'budgetCeiling'. **/
  public double calculateAverageBudget() {
    return (budgetFloor + budgetCeiling) / 2;
  }

  // Calculate average project duration
  /** Checks if a given budget value is within the specified range.**/
  public int calculateAverageProjectDuration() {
    return (projectDurationFloor + projectDurationCeiling) / 2;
  }

  // Check if a given budget is within the range
  public boolean isBudgetWithinRange(double value) {
    return value >= budgetFloor && value <= budgetCeiling;
  }

  // Check if a given project duration is within the range
  public boolean isProjectDurationWithinRange(int value) {
    return value >= projectDurationFloor && value <= projectDurationCeiling;
  }

  // Override toString method
  @Override
  public String toString() {
    return "StandardResidentialMarginRanges{" +
        "budgetFloor=" + budgetFloor +
        ", budgetCeiling=" + budgetCeiling +
        ", projectDurationFloor=" + projectDurationFloor +
        ", projectDurationCeiling=" + projectDurationCeiling +
        '}';
  }
}

