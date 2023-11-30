package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "create new project" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: K. Dashnaw*/
public class SubScene_CreateNewProjectView implements Scene_ControllerInterface
{
  //Residential Project control attributes
  @FXML VBox vBoxResidentialProjectType;
  @FXML Button buttonResidentialProjectType;
  @FXML ImageView imgResidentialProjectType;
  @FXML Label labelResidentialProjectType;
  @FXML GridPane gridResidentialUniqueData;

  //Commercial Project control attributes
  @FXML VBox vBoxCommercialProjectType;
  @FXML Button buttonCommercialProjectType;
  @FXML ImageView imgCommercialProjectType;
  @FXML Label labelCommercialProjectType;
  @FXML GridPane gridCommercialUniqueData;

  //Industrial Project control attributes
  @FXML VBox vBoxIndustrialProjectType;
  @FXML Button buttonIndustrialProjectType;
  @FXML ImageView imgIndustrialProjectType;
  @FXML Label labelIndustrialProjectType;
  @FXML GridPane gridIndustrialUniqueData;

  //Road Project control attributes
  @FXML VBox vBoxRoadProjectType;
  @FXML Button buttonRoadProjectType;
  @FXML ImageView imgRoadProjectType;
  @FXML Label labelRoadProjectType;
  @FXML GridPane gridRoadUniqueData;

  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML Label labelProjectTypePrompt;
  @FXML GridPane gridProjectDataContainer;
  @FXML Label labelProjectDataPrompt;
  @FXML Button buttonCreateProject;
  @FXML Button buttonCancel;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;
  private boolean projectTypeSelected;
  private String projectTypeSelectedString;

  /**
   * Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.activeModel = activeModel;
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());


    setProjectTypeSelected(false);
    hideGridElementNode(gridProjectDataContainer, true);
    hideLabelElementNode(labelProjectDataPrompt, true);
    hideAllUniqueProjectDataFields(true);
    hideButtonElementNode(buttonCreateProject, true);
    hideButtonElementNode(buttonCancel, true);

    System.out.println("Create New Project panel is now active");
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   */
  @Override public void refresh()
  {
    //Refresh the page, as it is shown on a clean load:
    setProjectTypeSelected(false);
    labelProjectTypePrompt.setVisible(true);
    hideGridElementNode(gridProjectDataContainer, true);
    hideLabelElementNode(labelProjectDataPrompt, true);
    hideAllUniqueProjectDataFields(true);
    hideButtonElementNode(buttonCreateProject, true);
    hideButtonElementNode(buttonCancel, true);

    //Reset all windows and fields to initial width/height:
    resizeAllElementHeight(185);
    resizeProjectButtonWidth(245);
    resetProjectTypeImagesTransparency();
    resetLabelTextSizes();
    System.out.println("New Project Creation Scene is now the active stage.");

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

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

  /** Sets/Initializes a boolean that indicates whether the user has selected a projectType yet, or not.
   * Parameters are:
   * True = User has selected a project type to create.
   * False = User has not selected a project type to create.
   * Author: K. Dashnaw
   * */
  private void setProjectTypeSelected(boolean projectTypeSelected)
  {
    this.projectTypeSelected = projectTypeSelected;
  }

  /** Returns a boolean that indicates whether the user has selected a projectType yet, or not.
   * Parameters are:
   * True = User has selected a project type to create.
   * False = User has not selected a project type to create.
   * Author: K. Dashnaw
   * */
  public boolean isProjectTypeSelected()
  {
    return projectTypeSelected;
  }

  /** Returns a String value containing the name of the selected project type to create.
   * This is used for evaluating which project type should be created once the user confirms their data.
   * Author: K. Dashnaw
   * */
  public String getProjectTypeSelectedString()
  {
    return projectTypeSelectedString;
  }

  /** Sets/Initializes a String value containing the name of the selected project type to create.
   * This is used for evaluating which project type should be created once the user confirms their data.
   * Author: K. Dashnaw
   * */
  private void setProjectTypeSelectedString(String projectTypeSelectedString)
  {
    this.projectTypeSelectedString = projectTypeSelectedString;
  }

  /** This method prompts a warning the user that unsaved data will be lost on change of view-screen.
   * And then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if(!(this.isProjectTypeSelected()))
    {
      //No project type has been selected. No need to prompt to the user about unsaved changes.
      //Also, no need to refresh anything.

    }
    else
    {
      //TODO: Implement a check if there is any unsaved data at all. If there isn't no need to display any prompt.
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      if(createPromptWindow())
      {

        //Re-set the Creation View scene.
        refresh();

      }
      else
      {
        //Breaks the method here, since the user selected "abort" in the prompt window.
        return;
      }
    }

    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText()
        .toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /**
   * This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();
  }

  /**
   * This method checks if there is any unsaved data and then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void exitApplication()
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if(!(this.isProjectTypeSelected()))
    {
      //No project type has been selected. No need to prompt to the user about unsaved changes.
      //Also, no need to refresh anything.

    }
    else
    {
      //TODO: Implement a check if there is any unsaved data at all. If there isn't no need to display any prompt.
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      if(createPromptWindow())
      {

        //Re-set the Creation View scene.
        refresh();

      }
      else
      {
        //Breaks the method here, since the user selected "abort" in the prompt window.
        return;
      }
    }
    this.getSceneController().exitApplication();
  }

  /** Is called as an on-action event from the one of the 4 buttons on screen, where you select which project type to create.
   * It initializes the next stage of the creation process, making visible shared project data fields
   * as well as unique data fields for the selected project type.
   * Author: K. Dashnaw
   * */
  public void ProjectTypeSelected(ActionEvent actionEvent)
  {
    String buttonText = ((Button) actionEvent.getSource()).getText();
    setProjectTypeSelected(true);
    setProjectTypeSelectedString(buttonText);

    //Display the common project data fields:
    hideGridElementNode(gridProjectDataContainer, false);
    hideLabelElementNode(labelProjectDataPrompt, false);

    //Display the unique data fields relating to the selected project type:
    showUniqueProjectDataFields();

    //Display the buttons the user may interact with:
    hideButtonElementNode(buttonCreateProject, false);
    hideButtonElementNode(buttonCancel, false);

    //Hide the "Select Project Type" prompt and resize the project type pictures for more space on screen:
    labelProjectTypePrompt.setVisible(false);
    resizeAllElementHeight(80);
    resizeProjectButtonWidth(106);
    setNonSelectedProjectTypesTransparent();
  }

  /** Resizes all the elements relating to the initial project selection.
   * This in order to provide more space on screen for data field entries.
   * Author: K. Dashnaw
   * */
  private void resizeAllElementHeight(int height)
  {
    //Resize the image elements:
    imgResidentialProjectType.setFitHeight(height - 5);
    imgCommercialProjectType.setFitHeight(height - 5);
    imgIndustrialProjectType.setFitHeight(height - 5);
    imgRoadProjectType.setFitHeight(height - 5);

    //Resize the button elements:
    resizeButtonHeight(buttonResidentialProjectType, height);
    resizeButtonHeight(buttonCommercialProjectType, height);
    resizeButtonHeight(buttonIndustrialProjectType, height);
    resizeButtonHeight(buttonRoadProjectType, height);

    //Resize the label elements:
    changeLabelTextSize(labelResidentialProjectType, 11, false);
    changeLabelTextSize(labelCommercialProjectType, 11, false);
    changeLabelTextSize(labelIndustrialProjectType, 11, false);
    changeLabelTextSize(labelRoadProjectType, 11, false);
    resizeLabelHeight(labelProjectTypePrompt, 0);

    //Resize the vBox elements:
    vBoxResidentialProjectType.setMaxHeight(height);
    vBoxCommercialProjectType.setMaxHeight(height);
    vBoxIndustrialProjectType.setMaxHeight(height);
    vBoxRoadProjectType.setMaxHeight(height);
  }

  /** Method used to resize a button's height.
   * Resizes maxHeight, prefHeight and minHeight at once.
   * Parameters:
   * "Button buttonID": a reference to the button to resize.
   * "int height": what height to set, in pixels.
   * Author: K. Dashnaw
   * */
  public void resizeButtonHeight(Button buttonID, int height)
  {
    buttonID.setMaxHeight(height);
    buttonID.setPrefHeight(height);
    buttonID.setMinHeight(height);
  }

  /** Method used to resize a button's width.
   * Parameters:
   * "Button buttonID": a reference to the label to resize.
   * "int height": what height to set, in pixels.
   * Author: K. Dashnaw
   * */
  public void resizeButtonWidth(Button buttonID, int width)
  {
    buttonID.setMinWidth(width);
    buttonID.setPrefWidth(width);
    buttonID.setMaxWidth(width);
  }

  /** Method used to resize a label's height.
   * Resizes maxHeight, prefHeight and minHeight at once.
   * Parameters:
   * "Label labelID": a reference to the label to resize.
   * "int height": what height to set, in pixels.
   * Author: K. Dashnaw
   * */
  public void resizeLabelHeight(Label labelID, int height)
  {
    labelID.setMinHeight(height);
    labelID.setPrefHeight(height);
    labelID.setMaxHeight(height);
  }

  /** Method used to resize the 4 project buttons, which have integrated images inside.
   * Parameters:
   * "int width": what height to set, in pixels.
   * Author: K. Dashnaw
   * */
  private void resizeProjectButtonWidth(int width)
  {
    resizeButtonWidth(buttonResidentialProjectType, width);
    resizeButtonWidth(buttonCommercialProjectType, width);
    resizeButtonWidth(buttonIndustrialProjectType, width);
    resizeButtonWidth(buttonRoadProjectType, width);
  }

  /** Method used to change the text size of a labels integrated text.
   * Parameters:
   * "Label labelID": A reference to the label to manipulate.
   * "int textSize": what text size to set. (Same scale as in word).
   * "boolean bold": true if text should be bold, otherwise false.
   * Author: K. Dashnaw
   * */
  public void changeLabelTextSize(Label labelID, int textSize, boolean bold)
  {
    if(bold)
    {
      labelID.setFont(Font.font("System", FontWeight.BOLD, textSize));
    }
    else
    {
      labelID.setFont(Font.font("System", textSize));
    }
  }

  /** Method used to reset all project label sizes to their initial values.
   * Is used upon stage refresh/reset..
   * Author: K. Dashnaw
   * */
  private void resetLabelTextSizes()
  {
    changeLabelTextSize(labelResidentialProjectType, 14, true);
    changeLabelTextSize(labelCommercialProjectType, 14, true);
    changeLabelTextSize(labelIndustrialProjectType, 14, true);
    changeLabelTextSize(labelRoadProjectType, 14, true);
    resizeLabelHeight(labelProjectTypePrompt, 30);
  }

  /** Method used to change the opacity of non-selected project type images for a clearer GUI experience.
   * Author: K. Dashnaw
   * */
  private void setNonSelectedProjectTypesTransparent()
  {
    double discreetOpacityLevel = 0.3;
    double nonDiscreetOpacityLevel = 1;
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        imgResidentialProjectType.setOpacity(nonDiscreetOpacityLevel);
        imgCommercialProjectType.setOpacity(discreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(discreetOpacityLevel);
        imgRoadProjectType.setOpacity(discreetOpacityLevel);
        break;
      case "CommercialProjectType":
        imgResidentialProjectType.setOpacity(discreetOpacityLevel);
        imgCommercialProjectType.setOpacity(nonDiscreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(discreetOpacityLevel);
        imgRoadProjectType.setOpacity(discreetOpacityLevel);
        break;
      case "IndustrialProjectType":
        imgResidentialProjectType.setOpacity(discreetOpacityLevel);
        imgCommercialProjectType.setOpacity(discreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(nonDiscreetOpacityLevel);
        imgRoadProjectType.setOpacity(discreetOpacityLevel);
        break;
      case "RoadProjectType":
        imgResidentialProjectType.setOpacity(discreetOpacityLevel);
        imgCommercialProjectType.setOpacity(discreetOpacityLevel);
        imgIndustrialProjectType.setOpacity(discreetOpacityLevel);
        imgRoadProjectType.setOpacity(nonDiscreetOpacityLevel);
        break;
    }
  }

  /** Method used to reset all project image types to their initial values.
   * Author: K. Dashnaw
   * */
  private void resetProjectTypeImagesTransparency()
  {
    imgResidentialProjectType.setOpacity(1);
    imgCommercialProjectType.setOpacity(1);
    imgIndustrialProjectType.setOpacity(1);
    imgRoadProjectType.setOpacity(1);
  }

  /** Method used to hide all unique project data fields.
   * It is used in conjunction with a show method for the specific data fields, so that only relevant data fields are displayed.
   * Author: K. Dashnaw
   * */
  private void hideAllUniqueProjectDataFields(boolean bool)
  {
    hideGridElementNode(gridResidentialUniqueData, bool);
    hideGridElementNode(gridCommercialUniqueData, bool);
    hideGridElementNode(gridIndustrialUniqueData, bool);
    hideGridElementNode(gridRoadUniqueData, bool);
  }

  /** Method used to hide a GridPane element.
   * Parameters are:
   * "GridPane gridID": A reference to the GridPane element to hide.
   * "boolean bool": if true, hide the element - if false, show it.
   * Author: K. Dashnaw
   * */
  public void hideGridElementNode(GridPane gridID, boolean bool)
  {
    gridID.setVisible(!bool);
    gridID.managedProperty().bind(gridID.visibleProperty());
  }

  /** Method used to hide a Button element.
   * Parameters are:
   * "Button buttonID": A reference to the Button element to hide.
   * "boolean bool": if true, hide the element - if false, show it.
   * Author: K. Dashnaw
   * */
  public void hideButtonElementNode(Button buttonID, boolean bool)
  {
    buttonID.setVisible(!bool);
    buttonID.managedProperty().bind(buttonID.visibleProperty());
  }

  /** Method used to hide a Label element.
   * Parameters are:
   * "Label labelID": A reference to the Label element to hide.
   * "boolean bool": if true, hide the element - if false, show it.
   * Author: K. Dashnaw
   * */
  public void hideLabelElementNode(Label labelID, boolean bool)
  {
    labelID.setVisible(!bool);
    labelID.managedProperty().bind(labelID.visibleProperty());
  }

  /** Method used to display the unique data fields specific to the selected project type!
   * Author: K. Dashnaw
   * */
  private void showUniqueProjectDataFields()
  {
    hideAllUniqueProjectDataFields(true);

    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        hideGridElementNode(gridResidentialUniqueData, false);
        break;
      case "CommercialProjectType":
        hideGridElementNode(gridCommercialUniqueData, false);
        break;
      case "IndustrialProjectType":
        hideGridElementNode(gridIndustrialUniqueData, false);
        break;
      case "RoadProjectType":
        hideGridElementNode(gridRoadUniqueData, false);
        break;
    }
  }

  /** Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!
   * Returns TRUE if the user decided to confirm their selected action (meaning we need to some code),
   * or FALSE if the user changed their mind and the selected action SHOULD NOT be executed.
   * Author: K. Dashnaw
   * */
  public boolean createPromptWindow()
  {
    String confirmationAction = this.getSceneController().createPromptWindow(
        "WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel aborted.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel aborted.");
        return false; //do not proceed with cancel operation

      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel confirmed. All data fields on 'Create new project' screen have been reset to default values.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. Resetting all data fields! Any unsaved data is removed.");
        refresh();
        return true; //Proceed with cancel operation

      default:
        return false;
    }
  }
}