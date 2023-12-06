package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "Set project filters" GUI stage.
 * This is where filters are set, so that only projects that fall within the filter parameters are shown on the project view stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class SubScene_FilterProjectsView implements Scene_ControllerInterface
{
  @FXML Button buttonSetFilers;
  @FXML CheckBox residentialProject;
  @FXML CheckBox commercialProject;
  @FXML CheckBox industrialProject;
  @FXML CheckBox roadBuildingProject;
  @FXML CheckBox hideFinishedProjects;
  @FXML CheckBox hideOngoingProjects;
  @FXML TextField ownerPhoneNumber;
  @FXML TextField budgetRangeMax;
  @FXML TextField durationMax;
  @FXML TextField durationMin;
  @FXML TextField budgetRangeMin;
  @FXML MainModel activeModel;
  @FXML SceneController sceneController;

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    setActiveModel(activeModel);
    setSceneController(sceneController);
    refresh();
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

  public MainModel getActiveModel()
  {
    return activeModel;
  }

  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  @Override public void refresh()
  {
    //This method should do nothing apart from the below internal debug note. Not refreshing this page will let the previously entered
    //filters remain in view. On session restart these will likewise be reset to nothing, as intended.
    loadFilterSettings();
    enableSetFiltersButton();
    System.out.println("Set project filters scene is now the active stage.");
  }

  public void enableSetFiltersButton()
  {
    boolean validationPassed = true;
    //Enables the filters button after input validation has been performed.

    TextField[] textFields = {this.budgetRangeMin, this.budgetRangeMax, this.durationMin, this.durationMax}; //Insert TextFields from screen page

    //Validate all textFields:
    for (int i = 0; i < 3 /*Replace with number of TextFields*/; i++)
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

    if(validationPassed)
    {
      buttonSetFilers.setDisable(false);
    }
    else
    {
      buttonSetFilers.setDisable(true);
    }
  }

  public void cancel(ActionEvent actionEvent) throws IOException
  {
    //Should be tied to the cancel button only!
    this.getSceneController().loadNewWindow("Projects_MainView");

    Button cancelButton = (Button) actionEvent.getSource();

    Stage stage = (Stage) cancelButton.getScene().getWindow();
    stage.close();
  }

  public void loadFilterSettings()
  {
    Object[] filterSettings = this.getActiveModel().getFilterSettings();

    this.budgetRangeMin.setText("" + (filterSettings[0]));
    this.budgetRangeMax.setText("" + (filterSettings[1]));
    this.durationMin.setText("" + (filterSettings[2]));
    this.durationMax.setText("" + (filterSettings[3]));
    this.ownerPhoneNumber.setText("" + filterSettings[4]);
    this.hideFinishedProjects.setSelected((Boolean) filterSettings[5]);
    this.hideOngoingProjects.setSelected((Boolean) filterSettings[6]);
    this.residentialProject.setSelected((Boolean) filterSettings[7]);
    this.commercialProject.setSelected((Boolean) filterSettings[8]);
    this.industrialProject.setSelected((Boolean) filterSettings[9]);
    this.roadBuildingProject.setSelected((Boolean) filterSettings[10]);
  }

  public void setFiltersButton()
  {
    String minBudget = this.budgetRangeMin.getText();
    String maxBudget = this.budgetRangeMax.getText();
    String minDuration = this.durationMin.getText();
    String maxDuration = this.durationMax.getText();
    String ownerPhoneNumber = this.ownerPhoneNumber.getText();
    boolean hideFinished = hideFinishedProjects.isSelected();
    boolean hideOngoing = hideOngoingProjects.isSelected();
    boolean hideResidential = residentialProject.isSelected();
    boolean hideCommercial = commercialProject.isSelected();
    boolean hideIndustrial = industrialProject.isSelected();
    boolean hideRoad = roadBuildingProject.isSelected();

    this.getActiveModel().setFilterSettings(minBudget, maxBudget, minDuration, maxDuration, ownerPhoneNumber, hideFinished, hideOngoing, hideResidential, hideCommercial, hideIndustrial, hideRoad);

    if(this.budgetRangeMin.getText().isBlank())
    {
      minBudget = "" + Integer.MIN_VALUE;
    }
    if(this.budgetRangeMax.getText().isBlank())
    {
      maxBudget = "" + Integer.MAX_VALUE;
    }
    if(this.durationMin.getText().isBlank())
    {
      minDuration = "" + Integer.MIN_VALUE;
    }
    if(this.durationMax.getText().isBlank())
    {
      maxDuration = "" + Integer.MAX_VALUE;
    }

    //Applies the filters.
    this.getActiveModel().setFilteredProjectsList(this.getActiveModel().filterProject(Integer.parseInt(minBudget),Integer.parseInt(maxBudget),Integer.parseInt(minDuration),Integer.parseInt(maxDuration), ownerPhoneNumber, hideFinished, hideOngoing, hideResidential, hideCommercial, hideIndustrial, hideRoad));

    Stage stage = (Stage) buttonSetFilers.getScene().getWindow();
    stage.close();
  }
}