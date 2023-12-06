package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "Set project filters" GUI stage.
 * This is where filters are set, so that only projects that fall within the filter parameters are shown on the project view stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: */
public class SubScene_FilterProjectsView implements Scene_ControllerInterface
{
  private MainModel activeModel;
  private SceneController sceneController;

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    setActiveModel(activeModel);
    setSceneController(sceneController);

    System.out.println("Set project filters scene is now the active stage.");
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

    //buttonEditProject.setDisable(true);
    System.out.println("Set project filters scene is now the active stage.");
  }

  /** <p>Returns FALSE if TextField is either empty OR a string OR a negative number/digit, and TRUE is TextField is none of either.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.</p>
   * <p><b>This method MUST be run on a TextField in order to avoid potential crashes/errors.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public boolean validate_NotEmpty_NotString_NotNegative(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty_NotString_NotNegative(keyNode, "Error in data values while creating new project. Please review and correct!"))
    {
      return true;
    }
    else
    {
      return false;
    }
  }

  /** <p>Method disabled the "Apply filters" button and is used in conjunction with the validation fields to ensure that the
   * "apply filters" button only is enabled when proper data is ready to be added to the system.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  private void resetValidation()
  {
    //buttonEditProject.setDisable(true);
  }


  public void enableSetFiltersButton()
  {
    boolean validationPassed = true;
    //Enables the filters button after input validation has been performed.

    TextField one = new TextField();

    TextField[] textFields = {one}; //Insert TextFields from screen page

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
      //enable setFilters button
    }
  }

  public void cancel() throws IOException
  {
    //Should be tied to the cancel button only!
      this.getSceneController().loadNewWindow("Projects_MainView");
  }

  public void setFiltersButton()
  {
    double minBudget = 0; //Replace with userText from TextField
    double maxBudget = 10000; //Replace with userText from TextField
    int minDuration = 0; //Replace with userText from TextField
    int maxDuration = 10; //Replace with userText from TextField
    boolean projectStatus = false; //Replace with userText from TextField

    //Applies the filters.
    this.getActiveModel().setFilteredProjectsList(this.getActiveModel().filterProject(minBudget,maxBudget,minDuration,maxDuration,projectStatus));
  }
}