package sep1a233group.bobsprojectmanagementsystem;

import java.util.ArrayList;

public class PromotionalInformation
{
  private String photoURL, projectDescription, projectName;
  private ArrayList<String>projectManagerComments;

  public PromotionalInformation(String projectName)
  {
    this.photoURL = null;
    this.projectDescription = null;
    this.projectName = projectName;
    this.projectManagerComments = new ArrayList<>();
  }

  public String getPhotoURL()
  {
    return photoURL;
  }

  public void addPhotoURL(String photoURL)
  {
    this.photoURL = photoURL;
  }

  public void removePhotoURL()
  {
    this.photoURL = null;
  }

  public String getProjectDescription()
  {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription)
  {
    this.projectDescription = projectDescription;
  }

  public String getProjectName()
  {
    return projectName;
  }

  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }

  public ArrayList<String> getProjectManagerComments()
  {
      return projectManagerComments;
  }

  public void addProjectManagerComment(String comment)
  {
    projectManagerComments.add(comment);
  }

  public void deleteProjectManagerCommentLine(int index)
  {
    projectManagerComments.remove(index);
  }

}
