package sep1a233group.bobsprojectmanagementsystem;

import java.io.*;

/** This is the main file in-and-out controller used for maintaining data persistence across sessions,
 * and providing cross-platform functionality so project information can be generically loaded in the company homepage.
 * Local files are saves in binary format.
 * Exported files for webpage use are exported in xml/json format.
 * Author: K. Dashnaw
 * */
public class FileIO
{
  private File systemSaveFile; //Reference to the system save file object.
  private File webpageFile; //Reference to the exported webpage compatible file object.
  private String systemFileName; //The name used for the system save file.
  private String webPageFileName; //The name used for the exported webpage compatible file.
  private Date lastDataSaveTime; //Contains a Date/Time representation of when the system data was last saved.
  private Date lastWebExportTime; //Contains a Date/Time representation of when the system data was last saved.

  /** Constructs the FileIO object and calls relevant methods for field attribute initialization.
   * Author: K. Dashnaw
   * */
  public FileIO()
  {
    setSystemFileName("mainProjectSaveFile.bin");
    setSystemSaveFile("Project Data Files/" + this.getSystemFileName());

    setWebPageFileName("webpageFile.json");
    setWebpageFile("Project Data Files/" + this.getWebPageFileName());





    //TODO: Implement
    setLastDataSaveTime(new Date());
    setLastWebExportTime(new Date());
  }

  /** Returns the name of the system data file
   * Author: K. Dashnaw
   * */
  public File getSystemSaveFile()
  {
    return systemSaveFile;
  }

  /** Sets/Initializes the name of the system data file
   * Author: K. Dashnaw
   * */
  public void setSystemSaveFile(String fileName)
  {
    this.systemSaveFile = new File(fileName);
  }

  /** Returns the name of the main system data file
   * Author: K. Dashnaw
   * */
  public String getSystemFileName()
  {
    return systemFileName;
  }

  /** Sets/Initializes the name for the main system data file
   * Author: K. Dashnaw
   * */
  public void setSystemFileName(String systemFileName)
  {
    this.systemFileName = systemFileName;
  }

  /** Returns the name of the webpage Project Data file
   * Author: K. Dashnaw
   * */
  public File getWebpageFile()
  {
    return webpageFile;
  }

  /** Sets/Initializes the name of the webpage Project Data file
   * Author: K. Dashnaw
   * */
  public void setWebpageFile(String fileName)
  {
    this.webpageFile = new File(fileName);
  }

  /** Returns the name of the exported Web Page Compatible file
   * Author: K. Dashnaw
   * */
  public String getWebPageFileName()
  {
    return webPageFileName;
  }

  /** Sets/Initializes the name for the exported Web Page Compatible file
   * Author: K. Dashnaw
   * */
  public void setWebPageFileName(String webPageFileName)
  {
    this.webPageFileName = webPageFileName;
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
   * File references are defined directly in FileIO.
   * Return true if saving was successful.
   * Returns false if not.
   * Author: K. Dashnaw
   * */
  public boolean writeToBinary(Object[] objectList)
  {
    System.out.println("WriteToBinary method called.");
    //Create the fileOutputStream with "try-with-resources")
    try (FileOutputStream fos = new FileOutputStream(this.getSystemSaveFile()))
    {
      //Create the ObjectOutputStreams, as a "try-with-resources")
      try (ObjectOutputStream out = new ObjectOutputStream(fos))
      {
        //We now write the received Object to the Binary File:
        out.writeObject(objectList);
        out.flush(); //Force it to write the text, emptying the buffer.
        //out.close(); - Not needed since we are using "try-with-resources"

        return true; //Data successfully saved.
      }
    }
    catch (IOException e)
    {
      System.out.println("'WriteToBinary' failed.");
      System.out.println("IOException Error: " + e);
      return false; //Write to Binary failed.
    }
  }

  /** Writes the relevant project data to a local xml/json file for use on the company homepage.
   * File references are defined directly in FileIO.
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
   * File references are defined directly in FileIO.
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
