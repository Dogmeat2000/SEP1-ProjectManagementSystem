package sep1a233group.bobsprojectmanagementsystem;

import java.io.Serializable;
import java.util.ArrayList;

public class PromotionalInformation implements Serializable
{
  private String photoURL, projectDescription, projectName;
  private String projectManagerComments;

  public PromotionalInformation(String projectName)
  {
    this.photoURL = "";
    this.projectDescription = "";
    this.projectName = projectName;
    this.projectManagerComments = "";
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

  public String getProjectManagerComments()
  {
      return projectManagerComments;
  }

  public void setProjectManagerComments(String message)
  {
    this.projectManagerComments = message;
  }

  @Override public String toString()
  {
    return "PromotionalInformation{" + "photoURL='" + photoURL + '\'' + ", projectDescription='" + projectDescription + '\''
        + ", projectName='" + projectName + '\'' + ", projectManagerComments=" + projectManagerComments + '}';
  }

  /** Returns a boolean if passed object is identical to this object.
   * TRUE = They are identical. FALSE = They are not.
   * Author: K. Dashnaw
   * */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof PromotionalInformation))
    {
      return false;
    }
    PromotionalInformation other = (PromotionalInformation) obj;
    return (other.getProjectDescription().equals(this.getProjectDescription()) && other.getProjectName().equals(this.getProjectName()) && other.getPhotoURL().equals(this.getPhotoURL()) && other.getProjectManagerComments().equals(this.getProjectManagerComments()));
  }

}
