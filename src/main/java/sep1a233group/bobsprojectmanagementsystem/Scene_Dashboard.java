package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Scene_Dashboard implements Scene_ControllerInterface
{
  @FXML TextField GUI_Console; //textField in the gui, where messages are shown to the user.
  private MainModel activeModel;
  private SceneController sceneController;

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.activeModel = activeModel;
    this.sceneController = sceneController;
    System.out.println("Dashboard Scene is now active");
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  @Override public void refresh()
  {
    //TODO: Genindlæs indholdet på siden. F.eks. hvis der skal stå noget specifikt tekst i en boks, osv.!
    System.out.println("Dashboard Scene is now the active stage.");
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.*/
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //TODO: Implement a pop-up message warning the user to confirm (yes/no) if they really wish to proceed to this new view.
    // However this warning should ONLY be shown when trying to navigate away from the "create project" or "edit project" views, to avoid the user accidentally loosing data.

    String buttonText = ((Button)actionEvent.getSource()).getText().toLowerCase();
    sceneController.openWindow(buttonText, this.GUI_Console);
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.*/
  public void exportToWeb()
  {
    sceneController.exportToWeb();
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.*/
  public void exitApplication()
  {
    sceneController.exitApplication();
  }

}

