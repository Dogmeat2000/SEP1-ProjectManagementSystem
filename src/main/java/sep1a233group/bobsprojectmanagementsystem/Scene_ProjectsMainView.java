package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "View Projects" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class Scene_ProjectsMainView implements Scene_ControllerInterface
{
  @FXML TextArea viewArea;
  @FXML TextField GUI_Console;
  private MainModel activeModel;
  private SceneController sceneController;


  /** Returns a reference to the GUI_Console on this page.
   * Author: K. Dashnaw
   * */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }
  /** Sets/Initializes the GUI_Console on this page.
   * Author: K. Dashnaw
   * */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /** Returns a SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   * */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /** Sets/Initializes the SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   * */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    //Sets necessary attributes:
    setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.GUI_Console.setText(this.getSceneController().getGUI_ConsoleMessage());
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    //Display all projects in system:
    displayAllProjects();
    displayDashboardProjects();

    System.out.println("Project view Scene is now active");
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  @Override public void refresh()
  {
    //TODO: Genindlæs indholdet på siden. F.eks. hvis der skal stå noget specifikt tekst i en boks, osv.!
    displayAllProjects();
    displayDashboardProjects();

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    System.out.println("Project Main View is now the active stage.");
  }

  public MainModel getActiveModel()
  {
    return activeModel;
  }

  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  public void displayAllProjects()
  {
    String allProjects = "No projects in system!";
    if(!this.getActiveModel().getAllProjectsList().isEmpty())
    {
      allProjects = "";
      for (int i = 0; i < this.getActiveModel().getAllProjectsList().size(); i++)
      {
        allProjects += "Project #" + i + "\n";
        allProjects += this.getActiveModel().getAllProjectsList().get(i);
        allProjects += "\n\n";
      }
    }
    viewArea.setText(allProjects);
  }

  public void displayDashboardProjects()
  {
    String allProjects = "No projects marked for adding to the dashboard";

    if(this.getActiveModel().getDashboardProgressReports().getProgressReports().length > 0)
    {
      allProjects = "";
      for (int i = 0; i < this.getActiveModel().getDashboardProgressReports().getProgressReports().length; i++)
      {
        allProjects += "Project #" + i + "\n";
        allProjects += this.getActiveModel().getDashboardProgressReports().getProgressReports()[i];
        allProjects += "\n\n";
      }
    }
    viewArea.setText(viewArea.getText() + "\n\n\n\nPROJECTS MARKED FOR SHOW ON DASHBOARD ARE BELOW\n___________________________________\n" + allProjects);
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.*/
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    String buttonText = ((Button)actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.*/
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.*/
  public void exitApplication()
  {
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }
}
