package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This class controls the GUI related view and methods concerning the "create new project" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.
 * Author: K. Dashnaw
 */
public class SubScene_CreateNewProjectView implements Scene_ControllerInterface
{
  //Residential Project control attributes
  @FXML VBox vBoxResidentialProjectType;
  @FXML Button buttonResidentialProjectType;
  @FXML ImageView imgResidentialProjectType;
  @FXML Label labelResidentialProjectType;
  @FXML GridPane gridResidentialUniqueData;
  @FXML TextField tResBathroomNumber;
  @FXML TextField tResNumberKitchens;
  @FXML TextField tResNumberOfOtherPlumbing;
  @FXML TextField tResDuration;
  @FXML TextField tResBuildingSize;

  //Commercial Project control attributes
  @FXML VBox vBoxCommercialProjectType;
  @FXML Button buttonCommercialProjectType;
  @FXML ImageView imgCommercialProjectType;
  @FXML Label labelCommercialProjectType;
  @FXML GridPane gridCommercialUniqueData;
  @FXML TextField tComNumberFloors;
  @FXML TextField tComDuration;
  @FXML TextField tComBuildingSize;
  @FXML TextArea taComIntendedUse;

  //Industrial Project control attributes
  @FXML VBox vBoxIndustrialProjectType;
  @FXML Button buttonIndustrialProjectType;
  @FXML ImageView imgIndustrialProjectType;
  @FXML Label labelIndustrialProjectType;
  @FXML GridPane gridIndustrialUniqueData;
  @FXML TextField tIndDuration;
  @FXML TextField tIndFacilitySize;
  @FXML TextArea taIndFacilityType;

  //Road Project control attributes
  @FXML VBox vBoxRoadProjectType;
  @FXML Button buttonRoadProjectType;
  @FXML ImageView imgRoadProjectType;
  @FXML Label labelRoadProjectType;
  @FXML GridPane gridRoadUniqueData;
  @FXML TextField tRDLength;
  @FXML TextField tRDWidth;
  @FXML TextField tRDDuration;
  @FXML TextArea taRDBridgeTunnelInfo;
  @FXML TextArea taRDEnvironmentInfo;

  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML Label labelProjectTypePrompt;
  @FXML GridPane gridProjectDataContainer;
  @FXML Label labelProjectDataPrompt;
  @FXML Button buttonCreateProject;
  @FXML Button buttonCancel;
  @FXML TextArea taProjectDescription;
  @FXML TextArea taManagersComments;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;
  private boolean projectTypeSelected;
  private String projectTypeSelectedString;

  private ArrayList<TextField> textFieldsArray;
  private ArrayList<TextArea> textAreasArray;

  private int loadCounter; //Keeps track of how many times critical methods have been executed. This is to prevent unnecessary use of confirmation prompts.

  /**
   * Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());

    setProjectTypeSelected(false);
    hideGridElementNode(gridProjectDataContainer, true);
    hideLabelElementNode(labelProjectDataPrompt, true);
    hideAllUniqueProjectDataFields(true);
    hideButtonElementNode(buttonCreateProject, true);
    hideButtonElementNode(buttonCancel, true);

    textFieldsArray = new ArrayList<>();
    textAreasArray = new ArrayList<>();
    loadCounter = 0;
    System.out.println("Create New Project panel is now active");
  }

  /**
   * Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
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

    for (Node node : gridProjectDataContainer.getChildren())
    {
      if (node instanceof TextArea)
      {
        ((TextArea) node).setText("");
      }
      else if (node instanceof TextField)
      {
        ((TextField) node).setText("");
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
    }
    taProjectDescription.setText("");
    taManagersComments.setText("");
    loadCounter = 0;

    //Refresh GUI console latest message:
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());
  }

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
   * Sets/Initializes a boolean that indicates whether the user has selected a projectType yet, or not.
   * Parameters are:
   * True = User has selected a project type to create.
   * False = User has not selected a project type to create.
   * Author: K. Dashnaw
   */
  private void setProjectTypeSelected(boolean projectTypeSelected)
  {
    this.projectTypeSelected = projectTypeSelected;
  }

  /**
   * Returns a boolean that indicates whether the user has selected a projectType yet, or not.
   * Parameters are:
   * True = User has selected a project type to create.
   * False = User has not selected a project type to create.
   * Author: K. Dashnaw
   */
  public boolean isProjectTypeSelected()
  {
    return projectTypeSelected;
  }

  /**
   * Returns a String value containing the name of the selected project type to create.
   * This is used for evaluating which project type should be created once the user confirms their data.
   * Author: K. Dashnaw
   */
  public String getProjectTypeSelectedString()
  {
    return projectTypeSelectedString;
  }

  /**
   * Sets/Initializes a String value containing the name of the selected project type to create.
   * This is used for evaluating which project type should be created once the user confirms their data.
   * Author: K. Dashnaw
   */
  private void setProjectTypeSelectedString(String projectTypeSelectedString)
  {
    this.projectTypeSelectedString = projectTypeSelectedString;
  }

  /**
   * This method prompts a warning the user that unsaved data will be lost on change of view-screen.
   * And then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if (!(this.isProjectTypeSelected()))
    {
      //No project type has been selected. No need to prompt to the user about unsaved changes.
      //Also, no need to refresh anything.

    }
    else
    {
      //TODO: Implement a check if there is any unsaved data at all. If there isn't no need to display any prompt.
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      if (createPromptWindow())
      {

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
    if (!(this.isProjectTypeSelected()))
    {
      //No project type has been selected. No need to prompt to the user about unsaved changes.
      //Also, no need to refresh anything.

    }
    else
    {
      //TODO: Implement a check if there is any unsaved data at all. If there isn't no need to display any prompt.
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      if (createPromptWindow())
      {

      }
      else
      {
        //Breaks the method here, since the user selected "abort" in the prompt window.
        return;
      }
    }
    this.getSceneController().exitApplication();
  }

  public MainModel getActiveModel()
  {
    return activeModel;
  }

  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  /**
   * Is called as an on-action event from the one of the 4 buttons on screen, where you select which project type to create.
   * It initializes the next stage of the creation process, making visible shared project data fields
   * as well as unique data fields for the selected project type.
   * Author: K. Dashnaw
   */
  public void ProjectTypeSelected(ActionEvent actionEvent)
  {
    String buttonText = ((Button) actionEvent.getSource()).getText();
    setProjectTypeSelected(true);
    setProjectTypeSelectedString(buttonText);

    //If we already loaded the page, but the user is simply changing project types in view, we display a confirmation window warning about data loss.
    if (loadCounter > 0)
    {
      if (createPromptWindowNoRefresh())
      {
        loadPageFields();
      }
    }
    else
    {
      loadPageFields();
    }
    loadCounter++;
  }

  public void loadPageFields()
  {
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

    //Initialize a new default project of this type.
    this.getActiveModel().newActiveProject(this.getProjectTypeSelectedString());

    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        this.getActiveModel()
            .initializeCreateProjectGUI(tResBathroomNumber, tResNumberKitchens,
                tResNumberOfOtherPlumbing, tResDuration, tResBuildingSize);
        break;
      case "CommercialProjectType":
        this.getActiveModel()
            .initializeCreateProjectGUI(tComNumberFloors, tComDuration,
                tComBuildingSize, taComIntendedUse);
        break;
      case "IndustrialProjectType":
        this.getActiveModel()
            .initializeCreateProjectGUI(tIndDuration, tIndFacilitySize,
                taIndFacilityType);
        break;
      case "RoadProjectType":
        this.getActiveModel()
            .initializeCreateProjectGUI(tRDLength, tRDWidth, tRDDuration,
                taRDBridgeTunnelInfo, taRDEnvironmentInfo);
        break;
    }
  }

  /**
   * Resizes all the elements relating to the initial project selection.
   * This in order to provide more space on screen for data field entries.
   * Author: K. Dashnaw
   */
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

  /**
   * Method used to resize a button's height.
   * Resizes maxHeight, prefHeight and minHeight at once.
   * Parameters:
   * "Button buttonID": a reference to the button to resize.
   * "int height": what height to set, in pixels.
   * Author: K. Dashnaw
   */
  public void resizeButtonHeight(Button buttonID, int height)
  {
    buttonID.setMaxHeight(height);
    buttonID.setPrefHeight(height);
    buttonID.setMinHeight(height);
  }

  /**
   * Method used to resize a button's width.
   * Parameters:
   * "Button buttonID": a reference to the label to resize.
   * "int height": what height to set, in pixels.
   * Author: K. Dashnaw
   */
  public void resizeButtonWidth(Button buttonID, int width)
  {
    buttonID.setMinWidth(width);
    buttonID.setPrefWidth(width);
    buttonID.setMaxWidth(width);
  }

  /**
   * Method used to resize a label's height.
   * Resizes maxHeight, prefHeight and minHeight at once.
   * Parameters:
   * "Label labelID": a reference to the label to resize.
   * "int height": what height to set, in pixels.
   * Author: K. Dashnaw
   */
  public void resizeLabelHeight(Label labelID, int height)
  {
    labelID.setMinHeight(height);
    labelID.setPrefHeight(height);
    labelID.setMaxHeight(height);
  }

  /**
   * Method used to resize the 4 project buttons, which have integrated images inside.
   * Parameters:
   * "int width": what height to set, in pixels.
   * Author: K. Dashnaw
   */
  private void resizeProjectButtonWidth(int width)
  {
    resizeButtonWidth(buttonResidentialProjectType, width);
    resizeButtonWidth(buttonCommercialProjectType, width);
    resizeButtonWidth(buttonIndustrialProjectType, width);
    resizeButtonWidth(buttonRoadProjectType, width);
  }

  /**
   * Method used to change the text size of a labels integrated text.
   * Parameters:
   * "Label labelID": A reference to the label to manipulate.
   * "int textSize": what text size to set. (Same scale as in word).
   * "boolean bold": true if text should be bold, otherwise false.
   * Author: K. Dashnaw
   */
  public void changeLabelTextSize(Label labelID, int textSize, boolean bold)
  {
    if (bold)
    {
      labelID.setFont(Font.font("System", FontWeight.BOLD, textSize));
    }
    else
    {
      labelID.setFont(Font.font("System", textSize));
    }
  }

  /**
   * Method used to reset all project label sizes to their initial values.
   * Is used upon stage refresh/reset..
   * Author: K. Dashnaw
   */
  private void resetLabelTextSizes()
  {
    changeLabelTextSize(labelResidentialProjectType, 14, true);
    changeLabelTextSize(labelCommercialProjectType, 14, true);
    changeLabelTextSize(labelIndustrialProjectType, 14, true);
    changeLabelTextSize(labelRoadProjectType, 14, true);
    resizeLabelHeight(labelProjectTypePrompt, 30);
  }

  /**
   * Method used to change the opacity of non-selected project type images for a clearer GUI experience.
   * Author: K. Dashnaw
   */
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

  /**
   * Method used to reset all project image types to their initial values.
   * Author: K. Dashnaw
   */
  private void resetProjectTypeImagesTransparency()
  {
    imgResidentialProjectType.setOpacity(1);
    imgCommercialProjectType.setOpacity(1);
    imgIndustrialProjectType.setOpacity(1);
    imgRoadProjectType.setOpacity(1);
  }

  /**
   * Method used to hide all unique project data fields.
   * It is used in conjunction with a show method for the specific data fields, so that only relevant data fields are displayed.
   * Author: K. Dashnaw
   */
  private void hideAllUniqueProjectDataFields(boolean bool)
  {
    hideGridElementNode(gridResidentialUniqueData, bool);
    hideGridElementNode(gridCommercialUniqueData, bool);
    hideGridElementNode(gridIndustrialUniqueData, bool);
    hideGridElementNode(gridRoadUniqueData, bool);
  }

  /**
   * Method used to hide a GridPane element.
   * Parameters are:
   * "GridPane gridID": A reference to the GridPane element to hide.
   * "boolean bool": if true, hide the element - if false, show it.
   * Author: K. Dashnaw
   */
  public void hideGridElementNode(GridPane gridID, boolean bool)
  {
    gridID.setVisible(!bool);
    gridID.managedProperty().bind(gridID.visibleProperty());
  }

  /**
   * Method used to hide a Button element.
   * Parameters are:
   * "Button buttonID": A reference to the Button element to hide.
   * "boolean bool": if true, hide the element - if false, show it.
   * Author: K. Dashnaw
   */
  public void hideButtonElementNode(Button buttonID, boolean bool)
  {
    buttonID.setVisible(!bool);
    buttonID.managedProperty().bind(buttonID.visibleProperty());
  }

  /**
   * Method used to hide a Label element.
   * Parameters are:
   * "Label labelID": A reference to the Label element to hide.
   * "boolean bool": if true, hide the element - if false, show it.
   * Author: K. Dashnaw
   */
  public void hideLabelElementNode(Label labelID, boolean bool)
  {
    labelID.setVisible(!bool);
    labelID.managedProperty().bind(labelID.visibleProperty());
  }

  /**
   * Method used to display the unique data fields specific to the selected project type!
   * Author: K. Dashnaw
   */
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

  /**
   * Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!
   * Parameters:
   * "boolean refreshData": Set to true if all data should be erased and window sizing reset.
   * Returns TRUE if the user decided to confirm their selected action (meaning we need to some code),
   * or FALSE if the user changed their mind and the selected action SHOULD NOT be executed.
   * Author: K. Dashnaw
   */
  public boolean createPromptWindow()
  {
    String confirmationAction = this.getSceneController().createPromptWindow(
        "WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel aborted.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel aborted.");
        return false; //do not proceed with cancel operation

      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage(
            "Cancel confirmed. All data fields on 'Create new project' screen have been reset to default values.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println(
            "Cancel confirmed. Resetting all data fields! Any unsaved data is removed.");
        refresh();
        return true; //Proceed with cancel operation

      default:
        return false;
    }
  }

  public boolean createPromptWindowNoRefresh()
  {
    String confirmationAction = this.getSceneController().createPromptWindow(
        "WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

    switch (confirmationAction)
    {
      case "cancelPressed":
        this.getSceneController().setGUI_ConsoleMessage("Cancel aborted.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel aborted.");
        return false; //do not proceed with cancel operation

      case "confirmationPressed":
        this.getSceneController().setGUI_ConsoleMessage(
            "Cancel confirmed. All data fields on 'Create new project' screen have been reset to default values.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println(
            "Cancel confirmed. Resetting all data fields! Any unsaved data is removed.");
        return true; //Proceed with cancel operation

      default:
        return false;
    }
  }

  public void addFXMLField(TextField field)
  {
    if (!(this.textFieldsArray.contains(field)))
    {
      this.textFieldsArray.add(field);
    }
  }

  public void addFXMLField(TextArea field)
  {
    if (!(this.textAreasArray.contains(field)))
    {
      this.textAreasArray.add(field);
    }
  }

  /**
   * MUST BE RUN ON A TEXTFIELD!
   * Checks if field is empty
   */
  public boolean validate_NotEmpty(KeyEvent keyNode)
  {
    TextField text = (TextField) keyNode.getSource();
    String userText = text.getText();

    //Check if field is empty:
    if (userText.isBlank())
    {
      emptyTextFieldCode(keyNode);
      return false;
    }
    //Passed validation, ensure previous tooltips are removed and text colors reverted:
    if (text.getTooltip() != null)
    {
      text.setTooltip(null);
      text.setStyle("-fx-text-fill: black;");
    }
    addTemporaryProjectData(keyNode);
    return true;
  }

  /**
   * MUST BE RUN ON A TEXTFIELD!
   * Checks if field is empty
   */
  public boolean validate_NotEmpty_NotNumber(KeyEvent keyNode)
  {
    TextField text = (TextField) keyNode.getSource();
    String userText = text.getText();

    //Check if field is empty:
    if (userText.isBlank())
    {
      emptyTextFieldCode(keyNode);
      return false;
    }
    else
    {
      //Check if field is a number:
      try
      {
        //If the below test throws an exception, the string is not a number.
        double testValue = Double.parseDouble(userText);

        //Field is a number. Show a tooltip!
        addErrorTooltip(keyNode, "-fx-text-fill: red;",
            "Field cannot be a number.");

        //Update console with an error:
        this.getSceneController().setGUI_ConsoleMessage(
            "Error in data values while creating new project. Please review and correct!");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        return false;
      }
      catch (NumberFormatException error)
      {
        //Passed validation, ensure previous tooltips are removed and text colors reverted:
        if (text.getTooltip() != null)
        {
          text.setTooltip(null);
          text.setStyle("-fx-text-fill: black;");
        }
        addTemporaryProjectData(keyNode);
      }
      return true;
    }
  }

  /**
   * MUST BE RUN ON A TEXTFIELD!
   * Checks if field is empty
   */
  public boolean validate_NotEmpty_NotString_NotNegative(KeyEvent keyNode)
  {
    TextField text = (TextField) keyNode.getSource();
    String userText = text.getText();

    //Check if field is empty:
    if (userText.isBlank())
    {
      emptyTextFieldCode(keyNode);
      return false;
    }
    else
    {
      //Check if field is a String:
      try
      {
        //If the below test succeeds, then this is a number.
        double testValue = Double.parseDouble(userText);
        if (testValue < 0)
        {
          //Check if it is a negative value. If so, throw an error and catch it later.
          throw new NumberFormatException();
        }

        //Passed validation, ensure previous tooltips are removed and text colors reverted:
        if (text.getTooltip() != null)
        {
          text.setTooltip(null);
          text.setStyle("-fx-text-fill: black;");
        }
        addTemporaryProjectData(keyNode);
        return true;
      }
      catch (NumberFormatException error)
      {
        //Field is a number. Show a tooltip!
        addErrorTooltip(keyNode, "-fx-text-fill: red;",
            "Field must be a positive number");

        //Update console with an error:
        this.getSceneController().setGUI_ConsoleMessage(
            "Error in data values while creating new project. Please review and correct!");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        return false;
      }
    }
  }

  public void emptyTextFieldCode(KeyEvent keyNode)
  {
    TextField text = (TextField) keyNode.getSource();

    //Field is empty. Show a tooltip!
    addErrorTooltip(keyNode, "-fx-text-fill: red;", "Field cannot be empty.");

    text.setStyle("-fx-prompt-text-fill: red;");

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage(
        "Error in data values while creating new project. Please review and correct!");
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  public void addErrorTooltip(KeyEvent keyNode, String textStyle,
      String toolTipMessage)
  {
    TextField text = (TextField) keyNode.getSource();

    //Below is run if input validation FAILS!
    text.setStyle(textStyle);

    Tooltip tooltip = new Tooltip();
    tooltip.setText(toolTipMessage);
    tooltip.setShowDelay(Duration.seconds(0));
    text.setTooltip(tooltip);
  }

  //Validate input
  public void addTemporaryProjectData(KeyEvent keyNode)
  {
    TextField userInput = (TextField) keyNode.getSource();

    //Get the project type:
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        addTemporaryResidentialData(
            (ResidentialProject) this.getActiveModel().getSelectedProject(),
            userInput);
        break;
      case "CommercialProjectType":
        addTemporaryCommercialData(
            (CommercialProject) this.getActiveModel().getSelectedProject(),
            userInput);
        break;
      case "IndustrialProjectType":
        addTemporaryIndustrialData(
            (IndustrialProject) this.getActiveModel().getSelectedProject(),
            userInput);
        break;
      case "RoadProjectType":
        addTemporaryRoadData(
            (RoadProject) this.getActiveModel().getSelectedProject(),
            userInput);
        break;
    }
  }

  /** This method is only for adding checkbox data (selected/not selected) */
  public void addTemporaryProjectData(TextField text)
  {
    //Get the project type:
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        addTemporaryResidentialData(
            (ResidentialProject) this.getActiveModel().getSelectedProject(),
            text);
        break;
      case "CommercialProjectType":
        addTemporaryCommercialData(
            (CommercialProject) this.getActiveModel().getSelectedProject(),
            text);
        break;
      case "IndustrialProjectType":
        addTemporaryIndustrialData(
            (IndustrialProject) this.getActiveModel().getSelectedProject(),
            text);
        break;
      case "RoadProjectType":
        addTemporaryRoadData(
            (RoadProject) this.getActiveModel().getSelectedProject(), text);
        break;
    }
  }

  /** This method is only for adding checkbox data (selected/not selected) */
  public void addTemporaryProjectData_TextArea(KeyEvent keyEvent)
  {
    TextArea userInput = (TextArea) keyEvent.getSource();
    TextField text2 = new TextField();
    text2.setText(userInput.getText());

    //Get the project type:
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        addTemporaryResidentialData(
            (ResidentialProject) this.getActiveModel().getSelectedProject(),
            text2);
        break;
      case "CommercialProjectType":
        addTemporaryCommercialData(
            (CommercialProject) this.getActiveModel().getSelectedProject(),
            text2);
        break;
      case "IndustrialProjectType":
        addTemporaryIndustrialData(
            (IndustrialProject) this.getActiveModel().getSelectedProject(),
            text2);
        break;
      case "RoadProjectType":
        addTemporaryRoadData(
            (RoadProject) this.getActiveModel().getSelectedProject(), text2);
        break;
    }
  }

  /** Runs when checkboxes are manipulated. ONLY RUN ON CHECKBOXES */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    CheckBox checkBox = (CheckBox) actionEvent.getSource();
    TextField value = new TextField();
    value.setText(checkBox.getText());

    if (checkBox.isSelected())
    {
      value.setText(value.getText() + "_True");
    }
    else
    {
      value.setText(value.getText() + "_False");
    }
    addTemporaryProjectData(value);
  }

  public boolean addCommonProjectData(ConstructionProject project,
      TextField text)
  {
    switch (text.getPromptText())
    {
      case "first name":
        project.getCustomer().setFirstName(text.getText());
        return true;
      case "last name":
        project.getCustomer().setLastName(text.getText());
        return true;
      case "Prefix (ie. +45)":
        project.getCustomer().setPhoneNumberPrefix(text.getText());
        return true;
      case "Number (8 digits)":
        project.getCustomer().setPhoneNumber(Integer.parseInt(text.getText()));
        return true;
      case "email":
        project.getCustomer().setEmail(text.getText());
        return true;
      case "Company Name the customer is representing":
        project.getCustomer().getCustomerCompany().setName(text.getText());
        return true;
      case "Customers Street Name":
        project.getCustomer().getCustomerAddress().setStreet(text.getText());
        return true;
      case "Building number":
        project.getCustomer().getCustomerAddress()
            .setStreetNumber(text.getText());
        return true;
      case "Customer apartment number, if applicable.":
        project.getCustomer().getCustomerAddress().setApartment(text.getText());
        return true;
      case "ZIP code":
        project.getCustomer().getCustomerAddress()
            .setPostalCode(Integer.parseInt(text.getText()));
        return true;
      case "City":
        project.getCustomer().getCustomerAddress().setCity(text.getText());
        return true;
      case "Country":
        project.getCustomer().getCustomerAddress().setCountry(text.getText());
        return true;
      case "Street name":
        project.getProjectAddress().setStreet(text.getText());
        return true;
      case "Property number":
        project.getProjectAddress().setStreetNumber(text.getText());
        return true;
      case "Apartment number, if applicable. (else leave empty)":
        project.getProjectAddress().setApartment(text.getText());
        return true;
      case "Project ZIP code":
        project.getProjectAddress()
            .setPostalCode(Integer.parseInt(text.getText()));
        return true;
      case "Project City":
        project.getProjectAddress().setCity(text.getText());
        return true;
      case "Project Country":
        project.getProjectAddress().setCountry(text.getText());
        return true;
      case "In hours":
        project.getHumanRessources()
            .setTotalManHoursNeeded(Double.parseDouble(text.getText()));
        return true;
      case "in $USD":
        project.getFinances()
            .setTotalBudget(Double.parseDouble(text.getText()));
        return true;
      case "Enter any internal only notes directed towards the project manager":
        project.getProjectInformation()
            .addProjectManagerComment(text.getText());
        return true;
      case "Start date":
        //TODO: Missing implementation
        //TODO: If estimated completion field is blank - increment this with the default month value
        return true;
      case "Est. Completion Date":
        //TODO: Missing implementation
        return true;
      case "Enter a short project description. This information is exported and displayed on the company homepage":
        project.getProjectInformation().setProjectDescription(text.getText());
        return true;
      case "Project name. Will be displayed on homepage":
        project.getProjectInformation().setProjectName(text.getText());
        return true;
      case "Add project to Dashboard?_True":
        //TODO: Missing implementation
        return true;
      case "Is project confidential?_True":
        //TODO: Missing implementation
        return true;
      default:
        break;
    }
    return false;
  }

  public void addTemporaryResidentialData(ResidentialProject project,
      TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      switch (text.getPromptText())
      {
        case "Number of Bathrooms":
          project.setNumberOfBathrooms(Integer.parseInt(text.getText()));
          break;
        case "Number of Kitchens":
          project.setNumberOfKitchens(Integer.parseInt(text.getText()));
          break;
        case "Other plumbing?":
          project.setNumberOfOtherRoomsWithPlumbing(
              Integer.parseInt(text.getText()));
          break;
        case "Duration in months":
          project.setProjectDuration(Integer.parseInt(text.getText()));
          break;
        case "in m^2":
          project.setBuildingSize(Double.parseDouble(text.getText()));
          break;
        case "Is project a renovation?_True":
          project.setIsRenovation(true);
          break;
        case "Is project a renovation?_False":
          project.setIsRenovation(false);
          break;
        default:
          break;
      }
    }
  }

  public void addTemporaryCommercialData(CommercialProject project,
      TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      switch (text.getPromptText())
      {
        case "Number of floors":
          project.setNumberOfFloors(Integer.parseInt(text.getText()));
          break;
        case "Describe the intended use of the building":
          project.setIntendedBuildingUse(text.getText());
          break;
        case "Duration in months":
          project.setProjectDuration(Integer.parseInt(text.getText()));
          break;
        case "in m^2":
          project.setBuildingSize(Double.parseDouble(text.getText()));
          break;
        default:
          break;
      }
    }
  }

  public void addTemporaryIndustrialData(IndustrialProject project,
      TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      switch (text.getPromptText())
      {
        case "Describe the intended use of the facility":
          project.setFacilityType(text.getText());
          break;
        case "Duration in months":
          project.setProjectDuration(Integer.parseInt(text.getText()));
          break;
        case "in m^2":
          project.setFacilitySize(Double.parseDouble(text.getText()));
          break;
        default:
          break;
      }
    }
  }

  public void addTemporaryRoadData(RoadProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      switch (text.getPromptText())
      {
        case "Type any relevant information about bridges or tunnels":
          project.setBridgeOrTunnelDetails(text.getText());
          break;
        case "Type any relevant environmental or geographical information":
          project.setEnvironmentalOrGeographicalChallenges(text.getText());
          break;
        case "Duration in months":
          project.setProjectDuration(Integer.parseInt(text.getText()));
          break;
        case "length in meters":
          project.setRoadLength(Double.parseDouble(text.getText()));
          break;
        case "width in meters":
          project.setRoadWidth(Double.parseDouble(text.getText()));
          break;
        default:
          break;
      }
    }
  }
}