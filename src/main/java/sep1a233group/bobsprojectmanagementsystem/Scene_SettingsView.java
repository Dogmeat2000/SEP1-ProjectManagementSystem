package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
  @FXML private Button saveChangesButton;

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

  public void loadSystemSettings()
  {
    timeLineRP.setText("" + this.getActiveModel().getDefaultResidentialSettings().getProjectDuration());
  }

  public void saveSystemSettings()
  {
    this.getActiveModel().getDefaultResidentialSettings().setProjectDuration(Integer.parseInt(timeLineRP.getText().trim()));
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
