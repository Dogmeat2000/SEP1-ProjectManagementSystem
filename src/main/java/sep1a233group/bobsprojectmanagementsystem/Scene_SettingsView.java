package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "Settings View" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class Scene_SettingsView implements Scene_ControllerInterface
{
  @FXML TextField GUI_Console;
  private MainModel activeModel;
  private SceneController sceneController;
  @FXML private TextField timeLineRP;
  @FXML private TextField numberOfKitchensRP;
  @FXML private TextField numberOfBathroomsRP;
  @FXML private TextField roomsWithPlumbingRP;
  @FXML private TextField buildOrRenovationRP;
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
    loadSystemSettings();

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


  /* Loading and displaying default values.
    */

  public void loadSystemSettings()
  {
    //Settings for Residential Projects
    timeLineRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getProjectDuration());
    numberOfKitchensRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfKitchens());
    numberOfBathroomsRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfBathrooms());
    roomsWithPlumbingRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getNumberOfOtherRoomsWithPlumbing());
    buildOrRenovationRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().isRenovation());

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

  /* Saving and updating system settings, for different type of construction projects.
   */
  public void saveSystemSettings()
  {
    //Settings for Residential Projects
    this.getActiveModel().getDefaultResidentialSettings().setProjectDuration(Integer.parseInt(timeLineRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfKitchens(Integer.parseInt(numberOfKitchensRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfBathrooms(Integer.parseInt(numberOfBathroomsRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setNumberOfOtherRoomsWithPlumbing(Integer.parseInt(roomsWithPlumbingRP.getText().trim()));
    this.getActiveModel().getDefaultResidentialSettings().setRenovation(true);
    //Settings for Commercial Projects
    this.getActiveModel().getDefaultCommercialSettings().setProjectDuration(Integer.parseInt(timeLineCP.getText().trim()));
    this.getActiveModel().getDefaultCommercialSettings().setNumberOfFloors(Integer.parseInt(numberOfFloorsCP.getText().trim()));

    //Settings for Industrial Projects
    this.getActiveModel().getDefaultIndustrialSettings().setProjectDuration(Integer.parseInt(timeLineIP.getText().trim()));

    //Settings for Road construction Projects
    this.getActiveModel().getDefaultRoadSettings().setProjectDuration(Integer.parseInt(timeLineRCP.getText().trim()));
    this.getActiveModel().getDefaultRoadSettings().setBridgesOrTunnelDetails(bridgesOrTunnelsRCP.getText().trim());
    this.getActiveModel().getDefaultRoadSettings().setEnviromentalOrGeographicalChallenges(enviromentalOrGeographicalRCP.getText().trim());

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

    System.out.println("Project Main View Scene is now the active stage.");
  }

  /**
   * This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //TODO: Implement a pop-up message warning the user to confirm (yes/no) if they really wish to proceed to this new view.
    // However this warning should ONLY be shown when trying to navigate away from the "create project" or "edit project" views, to avoid the user accidentally loosing data.

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


}

