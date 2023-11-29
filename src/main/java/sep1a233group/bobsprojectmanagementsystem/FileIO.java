package sep1a233group.bobsprojectmanagementsystem;

/** This is the main file in-and-out controller used for maintaining data persistence across sessions,
 * and providing cross-platform functionality so project information can be generically loaded in the company homepage.
 * Local files are saves in binary format.
 * Exported files for webpage use are exported in xml/json format.
 * Author: K. Dashnaw
 * */
public class FileIO
{
  private String systemSaveFileUrl; //A reference URL to the system file containing: Projects, Dashboard Information & Default settings.
  private String webPageFileUrl; //A reference URL to the webpage file that should be exported upon called by the user from the GUI.
  private Date lastDataSaveTime; //Contains a Date/Time representation of when the system data was last saved.
  private Date lastWebExportTime; //Contains a Date/Time representation of when the system data was last saved.

  /** Constructs the FileIO object and calls relevant methods for field attribute initialization.
   * Parameters are:
   * String systemSaveFileUrl: A string address (URL) pointing to the location of the system save file containing all persistence data for this application.
   * String webPageFileUrl: A string address (URL) pointing to the location the webpage data file, containing relevant project data for display on the company homepage.
   * Author: K. Dashnaw
   * */
  public FileIO(String systemSaveFileUrl, String webPageFileUrl)
  {
    //TODO: Implement
    setSystemSaveFileUrl(systemSaveFileUrl);
    setWebPageFileUrl(webPageFileUrl);

  }

  /** Returns a URL pointing to the location of the system save file containing all persistence data for this application.
   * Author: K. Dashnaw
   * */
  public String getSystemSaveFileUrl()
  {
    return systemSaveFileUrl;
  }

  /** Sets/Initializes a URL pointing to the location of the system save file containing all persistence data for this application.
   * Author: K. Dashnaw
   * */
  public void setSystemSaveFileUrl(String systemSaveFileUrl)
  {
    //TODO: Check if the given fileName has the proper ending (ie. .bin). If not, add the proper fileName!
    this.systemSaveFileUrl = systemSaveFileUrl;
  }

  /** Returns a URL pointing to the location of the webpage data file, containing relevant project data for display on the company homepage.
   * Author: K. Dashnaw
   * */
  public String getWebPageFileUrl()
  {
    return webPageFileUrl;
  }

  /** Sets/Initializes a URL pointing to the location of the webpage data file, containing relevant project data for display on the company homepage.
   * Author: K. Dashnaw
   * */
  public void setWebPageFileUrl(String webPageFileUrl)
  {
    //TODO: Check if the given fileName has the proper ending (ie. .xml or .json). If not, add the proper fileName!
    this.webPageFileUrl = webPageFileUrl;
  }

  /** Returns a Date Object containing the last date and time the system files were saved
   * Author: K: Dashnaw
   * */
  public Date getLastDataSaveTime()
  {
    return lastDataSaveTime;
  }

  /** Sets/Initializes a Date Object containing the last date and time the system files were exported to webpage compatible format
   * Author: K: Dashnaw
   * */
  public void setLastDataSaveTime(Date lastDataSaveTime)
  {
    this.lastDataSaveTime = lastDataSaveTime;
  }

  /** Returns a Date Object containing the last date and time the system files were exported to webpage compatible format
   * Author: K: Dashnaw
   * */
  public Date getLastWebExportTime()
  {
    return lastWebExportTime;
  }

  /** Sets/Initializes a Date Object containing the last date and time the system files were exported to webpage compatible format
   * Author: K: Dashnaw
   * */
  public void setLastWebExportTime(Date lastWebExportTime)
  {
    this.lastWebExportTime = lastWebExportTime;
  }

  /** Writes the persistence system data to a local binary file.
   * URL references are defined directly in FileIO.
   * Return true if saving was successful.
   * Returns false if not.
   * Author: K. Dashnaw
   * */
  public boolean writeToBinary()
  {
    //TODO: Implement
    return false;
  }

  /** Writes the relevant project data to a local xml/json file for use on the company homepage.
   * URL references are defined directly in FileIO.
   * Return true if saving was successful.
   * Returns false if not.
   * Author: K. Dashnaw
   * */
  public boolean writeToXML()
  {
    //TODO: Implement
    return false;
  }

  /** Loads system persistence data from a local binary file.
   * URL references are defined directly in FileIO.
   * Return true if loading was successful.
   * Returns false if not.
   * Author: K. Dashnaw
   * */
  public boolean readFromBinary()
  {
    //TODO: Implement
    return false;
  }

}
