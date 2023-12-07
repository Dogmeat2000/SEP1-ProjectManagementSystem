package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "Settings View" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class Scene_SettingsView implements Scene_ControllerInterface
{
  @FXML CheckBox renovationCheckbox;
  @FXML CheckBox newBuildCheckbox;
  @FXML Button saveChangesButton;
  @FXML TextField GUI_Console;
  private MainModel activeModel;
  private SceneController sceneController;
  @FXML private TextField timeLineRP;
  @FXML private TextField numberOfKitchensRP;
  @FXML private TextField numberOfBathroomsRP;
  @FXML private TextField roomsWithPlumbingRP;

  @FXML private TextField timeLineCP;
  @FXML private TextField numberOfFloorsCP;
  @FXML private TextField timeLineIP;
  @FXML private TextField timeLineRCP;
  @FXML private TextField bridgesOrTunnelsRCP;
  @FXML private TextField enviromentalOrGeographicalRCP;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;

  /**
   * Returns a reference to the GUI_Console on this page.
   * Author: K. Dashnaw
   */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }

  /**
   * Sets/Initializes the GUI_Console on this page.
   * Author: K. Dashnaw
   */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /**
   * Returns a SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /**
   * Sets/Initializes the SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  /**
   * Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    refresh();

    System.out.println("Project Settings view Scene is now active");
  }

  public MainModel getActiveModel()
  {
    return activeModel;
  }

  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }


  /** Loading and displaying default values.
    */

  public void loadSystemSettings()
  {
    //Settings for Residential Projects
    timeLineRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getProjectDuration());
    numberOfKitchensRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfKitchens());
    numberOfBathroomsRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfBathrooms());
    roomsWithPlumbingRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfOtherRoomsWithPlumbing());

    if(this.getActiveModel().getDefaultResidentialSettings().isRenovation())
    {
      renovationCheckbox.setSelected(true);
      newBuildCheckbox.setSelected(false);
    }
    else
    {
      renovationCheckbox.setSelected(false);
      newBuildCheckbox.setSelected(true);
    }

    //Settings for Commercial Projects
    timeLineCP.setText("" + this.getActiveModel().getDefaultCommercialSettings().getProjectDuration());
    numberOfFloorsCP.setText("" + this.getActiveModel().getDefaultCommercialSettings().getNumberOfFloors());

    //Settings for Industrial Projects
    timeLineIP.setText("" + this.getActiveModel().getDefaultIndustrialSettings().getProjectDuration());

    //Settings for Road construction Projects
    timeLineRCP.setText("" + this.getActiveModel().getDefaultRoadSettings().getProjectDuration());
    bridgesOrTunnelsRCP.setText(this.getActiveModel().getDefaultRoadSettings().getBridgesOrTunnelDetails());
    enviromentalOrGeographicalRCP.setText(this.getActiveModel().getDefaultRoadSettings().getEnviromentalOrGeographicalChallenges());

  }

  /** Saving and updating system settings, for different type of construction projects.
   */
  public void saveSystemSettings()
  {
    //Settings for Residential Projects
    this.getActiveModel().getDefaultResidentialSettings().setProjectDuration(Integer.parseInt(timeLineRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfKitchens(Integer.parseInt(numberOfKitchensRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfBathrooms(Integer.parseInt(numberOfBathroomsRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfOtherRoomsWithPlumbing(Integer.parseInt(roomsWithPlumbingRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setRenovation(!newBuildCheckbox.isSelected());

    //Settings for Commercial Projects
    this.getActiveModel().getDefaultCommercialSettings().setProjectDuration(Integer.parseInt(timeLineCP.getText().trim()));
    this.getActiveModel().getDefaultCommercialSettings().setNumberOfFloors(Integer.parseInt(numberOfFloorsCP.getText().trim()));

    //Settings for Industrial Projects
    this.getActiveModel().getDefaultIndustrialSettings().setProjectDuration(Integer.parseInt(timeLineIP.getText().trim()));

    //Settings for Road construction Projects
    this.getActiveModel().getDefaultRoadSettings().setProjectDuration(Integer.parseInt(timeLineRCP.getText().trim()));
    this.getActiveModel().getDefaultRoadSettings().setBridgesOrTunnelDetails(bridgesOrTunnelsRCP.getText().trim());
    this.getActiveModel().getDefaultRoadSettings().setEnviromentalOrGeographicalChallenges(enviromentalOrGeographicalRCP.getText().trim());

    //Update console
    this.getSceneController().setGUI_ConsoleMessage("Default project settings saved.");
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    //Send user back to dashboard:
    try
    {
      this.getSceneController().loadNewWindow("Dashboard");
    }
    catch(IOException error)
    {
      this.getSceneController().setGUI_ConsoleMessage("Unknown error occurred after saving settings. Try restarting the application");
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
  }

  /**
   * Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   */
  @Override public void refresh()
  {
    loadSystemSettings();

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    if(this.getActiveModel().getFileManager().getLastDataSaveTime() != null)
    {
      labelLastProjectSave.setText("Project file version: " + this.getActiveModel().getFileManager().getLastDataSaveTime());
    }
    else
    {
      labelLastProjectSave.setText("Project file version: Unknown");
    }
    if(this.getActiveModel().getFileManager().getLastWebExportTime() != null)
    {
      labelHTMLExportDate.setText("Last HTML export : " + this.getActiveModel().getFileManager().getLastWebExportTime());
    }
    else
    {
      labelHTMLExportDate.setText("Last HTML export : Unknown");
    }

    System.out.println("Project Settings Scene is now the active stage.");
  }

  /**
   * This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /**
   * This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.
   */
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();
  }

  /**
   * This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.
   */
  public void exitApplication()
  {
    this.getSceneController().exitApplication();
  }

  /** <p>Returns FALSE if TextField is empty and TRUE is they are not.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.</p>
   * <p><b>This method MUST be run on a TextField in order to avoid potential crashes/errors.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void validate_NotEmpty(KeyEvent keyNode)
  {
    resetValidation();
    if(!(getSceneController().validate_NotEmpty(keyNode)))
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    }
    else
    {
      enableSaveButton();
    }
  }

  /** <p>Method disabled the "save" button and is used in conjunction with the validation fields to ensure that the
   * "save" button only is enabled when proper data is ready to be added to the system.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  private void resetValidation()
  {
    saveChangesButton.setDisable(true);

    //Update console:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  public void enableSaveButton()
  {
    boolean validationPassed = true;
    //Enables the filters button after input validation has been performed.

    TextField[] textFields = {this.timeLineRP, this.numberOfKitchensRP, this.numberOfBathroomsRP, this.roomsWithPlumbingRP,
        this.timeLineCP, this.numberOfFloorsCP, this.timeLineIP, this.timeLineRCP}; //Insert TextFields from screen page

    //Validate all textFields:
    for (int i = 0; i < textFields.length; i++)
    {
      if(!(textFields[i].getText().isBlank()))
      {
        //Check if field is a String:
        try
        {
          //If the below test succeeds, then this is a number.
          double testValue = Double.parseDouble(textFields[i].getText());
          if (testValue < 0)
          {
            //Check if it is a negative value. If so, throw an error and catch it later.
            throw new NumberFormatException();
          }

          //Passed validation, ensure previous tooltips are removed and text colors reverted:
          if (textFields[i].getTooltip() != null)
          {
            textFields[i].setTooltip(null);
            textFields[i].setStyle("-fx-text-fill: black;");
          }
        }
        catch (NumberFormatException error)
        {
          //Field is a number. Show a tooltip!
          this.getSceneController().addErrorTooltip(textFields[i], "-fx-text-fill: red;", "Field must be a positive number");
          validationPassed = false;

        }
      }
    }
    saveChangesButton.setDisable(!validationPassed);
  }

  /** <p>****</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    saveChangesButton.setDisable(true);
    CheckBox checkBox = (CheckBox) actionEvent.getSource();

    //Check which box has been selected, and unselect the other
    if(checkBox.getText().equalsIgnoreCase("Renovation project") && checkBox.isSelected())
    {
      //RenovationCheckbox was selected
      newBuildCheckbox.setSelected(false);
      renovationCheckbox.setSelected((true));
    }
    else
    {
      newBuildCheckbox.setSelected(true);
      renovationCheckbox.setSelected((false));
    }
    enableSaveButton();
  }
}

