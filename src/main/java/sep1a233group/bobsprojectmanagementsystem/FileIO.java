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
  private MyDate lastDataSaveTime; //Contains a MyDate/Time representation of when the system data was last saved.
  private MyDate lastWebExportTime; //Contains a MyDate/Time representation of when the system data was last saved.

  /** Constructs the FileIO object and calls relevant methods for field attribute initialization.
   * Author: K. Dashnaw
   * */
  public FileIO()
  {
    setSystemFileName("mainProjectSaveFile.bin");
    setSystemSaveFile("Project Data Files/" + this.getSystemFileName());
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
  public void setWebpageFile(File file)
  {
    this.webpageFile = file;
  }

  /** Returns a MyDate Object containing the last date and time the system files were saved
   * Author: K: Dashnaw
   * */
  public MyDate getLastDataSaveTime()
  {
    return lastDataSaveTime;
  }

  /** Sets/Initializes a MyDate Object containing the last date and time the system files were exported to webpage compatible format
   * Author: K: Dashnaw
   * */
  public void setLastDataSaveTime(MyDate lastDataSaveTime)
  {
    this.lastDataSaveTime = lastDataSaveTime;
  }

  /** Returns a MyDate Object containing the last date and time the system files were exported to webpage compatible format
   * Author: K: Dashnaw
   * */
  public MyDate getLastWebExportTime()
  {
    return lastWebExportTime;
  }

  /** Sets/Initializes a MyDate Object containing the last date and time the system files were exported to webpage compatible format
   * Author: K: Dashnaw
   * */
  public void setLastWebExportTime(MyDate lastWebExportTime)
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

        setLastDataSaveTime(MyDate.now());
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

  /** <p>Writes the relevant project data to a local user defined file type for use on the company homepage.
   * File path references are defined directly in FileIO.
   * This implementation is basically a textFile writer, which requires the received String data to already be formatted into the proper string pattern. Used incorrectly this may cause errors.
   * In this application the text formatting is conducted in the MainModel.exportAsJson() method.
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param exportData A String containing the data to export.
   * @param fileName A string containing the name the exported JSON file should have.
   * @param fileType A string containing the file type to append after the name. In the MainModel class we use '.json'.
   * @throws FileNotFoundException if any exceptions parsing, transforming, writing or reading.
   * */
  public void export(String exportData, String fileName, String fileType) throws FileNotFoundException
  {
    File exportFile = new File(getWebpageFile().getAbsolutePath() + "\\" + fileName + fileType);

    System.out.println("Data exported to: " + getWebpageFile().getAbsolutePath() + "\\" + fileName + fileType);

    //Give the File to the PrintWriter. We use try with resources.
    try (PrintWriter out = new PrintWriter(exportFile))
    {
      //Now we write to the file using
      out.println(exportData);
      out.flush(); //Force it to write the text, emptying the buffer.
      setLastWebExportTime(MyDate.now());
    }
  }

  /** <p>Loads system persistence data from a local binary file.
   * File references are defined directly in FileIO.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @return An Object Array containing all the system projects and related information, or throws a NullPointerException..
   * */
  public Object[] readFromBinary() throws FileNotFoundException, IOException, ClassNotFoundException, NullPointerException
  {
    System.out.println("Loading data from binary file");

    //Try with resources:
    //1. Create fileInput Stream:
    try(FileInputStream fis = new FileInputStream(getSystemSaveFile()))
    {
      //2. Create ObjectStream:
      ObjectInputStream in = new ObjectInputStream(fis);
      Object[] objectList = (Object[]) in.readObject();
      System.out.println("Data successfully read from Binary.");

      if(objectList == null)
      {
        System.out.println("Data is corrupt!");
        throw new NullPointerException();
      }
      else
      {
        return objectList;
      }
    }
  }
}
