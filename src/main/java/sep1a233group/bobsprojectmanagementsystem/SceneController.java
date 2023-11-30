package sep1a233group.bobsprojectmanagementsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javafx.application.Platform.exit;

/** This class controls the overall flow between the various .fxml scenes in this application.
 * It allows for a seamless transition between windows for the user.
 * This class also maintains the shared operations that each scene/stage calls, in order to avoid having duplicate methods described multiple places.
 * Thus, many of the stages will refer to this class for operational instructions in their methods.
 * Author: K. Dashnaw, inspired by the work of Lector Michael from our SDJ1 education.*/
public class SceneController
{
  private MainModel activeModel; //Refers to the currently active model. (The one associated with the active scene)
  private Stage activeStage; //Refers to the scene/stage that is currently being displayed in the application.

  private Map<String, Scene_ControllerInterface> sceneControllers; //Here we map a String key to a Scene value, so that it is possible for us to easily identify which controller is connected to which window.

  private Map<String, Scene> scenes; //Here we map a String "key" to a scene, so that we can easily identify which scene is connected to each window.

  private String GUI_ConsoleMessage; //Continuously updated by GUI pages and contains the latest console message to display.

  public Stage getActiveStage()
  {
    return activeStage;
  }

  public void setActiveStage(Stage activeStage)
  {
    this.activeStage = activeStage;
  }

  public String getGUI_ConsoleMessage()
  {
    return GUI_ConsoleMessage;
  }

  public void setGUI_ConsoleMessage(String GUI_ConsoleMessage)
  {
    this.GUI_ConsoleMessage = GUI_ConsoleMessage;
  }

  /**
   * The Scene Controller class is used for controlling the active scene in the GUI application.
   * It is inspired by some of the SDJ1 developmental work our Lector, Michael, has presented during the SDJ1 course - specifically relating to the use of a 'viewhandler'
   */
  public SceneController(MainModel activeModel, Stage activeStage)
  {
    this.activeModel = activeModel;
    setActiveStage(activeStage);
    setGUI_ConsoleMessage("Application successfully loaded");
    sceneControllers = new HashMap<>();
    scenes = new HashMap<>();
  }

  /**
   * This method when called, loads a new window into the FXML loader, re-using the existing window area.
   * This allows for seamless navigation between different windows in the GUI application.
   * It is inspired by some of the SDJ1 developmental work our Lector, Michael, has presented during the
   * SDJ1 course - specifically relating to the use of a 'viewhandler'
   */
  public void loadNewWindow(String newWindow) throws IOException
  {
    Scene newScene = null;

    //If the controller has not been initialized yet, we need to initialize it:
    if (sceneControllers.get(newWindow) == null)
    {
      //TODO: Implement a try / catch validation here to guard against trying to open scenes that have been moved from their folder - or that no longer exists.

      FXMLLoader fxmlLoader = new FXMLLoader(
          getClass().getResource(newWindow + ".fxml"));
      newScene = new Scene(fxmlLoader.load(), 1366, 768);
      Scene_ControllerInterface controller = fxmlLoader.getController();
      controller.init(activeModel, this);

      //Then, after creation, we add it to our map
      sceneControllers.put(newWindow, controller);
      scenes.put(newWindow, newScene);
    }
    else
    {
      //The controller is already initialized, so we simply add it to our map and refresh!
      Scene_ControllerInterface controller = sceneControllers.get(newWindow);
      newScene = scenes.get(newWindow);
      controller.refresh();
    }

    activeStage.show();
    activeStage.setScene(newScene);
    activeStage.setResizable(false);
    activeStage.setTitle("Bob's Project Management System");
  }

  /** This is a common method shared between all GUI scenes. This method saves all system data before terminating the application safely. */
  public void exitApplication()
  {

    //TODO 1: Implement a check before initializing the termination. It is important to verify if the user truly wishes to exit, if this
    // method is called from the 'Create new project' or 'edit project' view screen. Data might be lost unintentionally if no check is performed here.

    //TODO 2: Implement a method to save the data currently in the system to Binary file (or similar) before exiting the application.

    System.out.println(
        "User pressed the 'EXIT' button. System is now attempting to terminate the GUI application.");
    exit();
  }

  /**
   * This is a common method shared between all GUI scenes. This method saves all system data and then exports
   * progress reports on all non-confidential projects to a webpage compatible file that can be readily loaded
   * on the company's webpage.
   */
  public void exportToWeb()
  {
    //TODO Implement the method!
    System.out.println(
        "User pressed the 'Export' button. System will now save all data to binary file, and then export this data to a webpage compatible file!");
  }

  /**
   * This method is used on GUI buttons as an "On Action" call and is used to navigate between different scenes/pages in the GUI.
   * It takes the text-label from the button and checks in an if/else manner which GUI scene corresponds to that button.
   * If changes are made in the GUI labels, but not updated in this method, the application WILL break.
   * Author: K. Dashnaw
   */
  public void openWindow(String buttonText, TextField GUI_Console)
      throws IOException
  {
    //TODO: Implement a pop-up message warning the user to confirm (yes/no) if they really wish to proceed to this new view.
    // However this warning should ONLY be shown when trying to navigate away from the "create project" or "edit project" views, to avoid the user accidentally loosing data.

    //Guard against any unexpected errors. Since this method should ONLY be called on buttons using set action any exceptions that might arise are due to programing flaws.
    try
    {
      if (buttonText.equalsIgnoreCase("Dashboard"))
      {
        this.loadNewWindow("Dashboard");
      }
      else if (buttonText.equalsIgnoreCase("Projects"))
      {
        this.loadNewWindow("Projects_MainView");
      }
      else if (buttonText.equalsIgnoreCase("Filter results"))
      {
        this.loadNewWindow("Projects_FilterView");
      }
      else if (buttonText.equalsIgnoreCase("New project"))
      {
        this.loadNewWindow("Projects_CreateProjectView");
      }
      else if (buttonText.equalsIgnoreCase("Settings"))
      {
        this.loadNewWindow("Projects_SettingsView");
      }
    }
    catch (Exception error)
    {
      GUI_Console.setText(
          "Unexpected error occurred while attempting to load stage. Code: " + error);
      System.out.println("Unexpected error occurred while attempting to load stage. Code: " + error);
    }
  }

  /**
   * This method is called from the various Scene Controllers towards this overall controller.
   * It creates a simple confirmation window inside the active window area and returns which button the user selected to press.
   * It takes these parameters:
   * "String message": This message is displayed inside the confirmation window.
   *
   * It returns one of these messages:
   * "cancelPressed": The user decided to press cancel, or exit, in the confirmation window.
   * "confirmationPressed": The user pressed "confirm"/"ok" in the window, signaling the intent to proceed with the prompted action.
   * Author: K. Dashnaw
   */
  public String createPromptWindow(String message)
  {
    //Get the x/y position of the active window:
    double x = this.getActiveStage().getX();
    double y = this.getActiveStage().getY();

    //Create a confirmation alert
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);

    //Set the alert text
    a.setContentText(message);

    //Set the x/y position of the window to be close to the calling button. This avoids the confirmation popping up on another screen, if more than 1 screen is connected.
    a.setX(x + this.getActiveStage().getWidth() / 2 - 180);
    a.setY(y + this.getActiveStage().getHeight() / 2 - 130);

    // show the dialog
    Optional<ButtonType> result = a.showAndWait();
    a.setResizable(false);

    //Get what button was pushed. Null if user exited without pressing a button
    if (result.isEmpty() || result.get() == ButtonType.CANCEL)
    {
      // alert is exited, no button has been pressed OR the cancel button was pressed.
      return "cancelPressed";
    }
    else
    {
      //OK button is pressed
      return "confirmationPressed";
    }
  }

}
