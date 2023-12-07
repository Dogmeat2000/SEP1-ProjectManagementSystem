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

import java.io.IOException;

/** This class controls the GUI related view and methods concerning the "create new project" GUI stage.
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
  @FXML DatePicker date_EndDateField;
  @FXML CheckBox checkBox_AddToDashBoard;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;
  private boolean projectTypeSelected;
  private String projectTypeSelectedString;

  private int loadCounter; //Keeps track of how many times critical methods have been executed. This is to prevent unnecessary use of confirmation prompts.

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    //Sets necessary attributes:
    setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    //Ensure the elements shown are in the proper format for a clean view load:
    refresh();

    //Set the load counter used to check if user has previously performed critical methods or not.
    loadCounter = 0;
    System.out.println("Create New Project panel is now active");
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * Author: K. Dashnaw
   */
  @Override public void refresh()
  {
    //Refresh the page, as it is shown on a clean load:
    setProjectTypeSelected(false);
    labelProjectTypePrompt.setVisible(true);
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
    this.getSceneController().hideGridElementNode(gridProjectDataContainer, true);
    this.getSceneController().hideLabelElementNode(labelProjectDataPrompt, true);
    this.getSceneController().hideAllUniqueProjectDataFields(true, gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);
    this.getSceneController().hideButtonElementNode(buttonCreateProject, true);
    this.getSceneController().hideButtonElementNode(buttonCancel, true);

    //Reset all windows and fields to initial width/height:
    resizeAllElementHeight(185);
    resizeProjectButtonWidth(245);
    resetProjectTypeImagesTransparency();
    resetLabelTextSizes();
    System.out.println("New Project Creation Scene is now the active stage.");

    //Loop though non fx:id referenced data fields and reset these.
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
      else if(node instanceof DatePicker)
      {
        ((DatePicker) node).getEditor().setText("");
      }
      else if(node instanceof CheckBox)
      {
        ((CheckBox) node).setSelected(false);
        node.setDisable(false);
      }
    }
    taProjectDescription.setText("");
    taManagersComments.setText("");
    checkBox_AddToDashBoard.setText("Add project to Dashboard?");

    //Page has been refreshed. Reset the load counter.
    loadCounter = 0;

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** Returns a reference to the GUI_Console on this page.
   * Author: K. Dashnaw
   */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }

  /** Sets/Initializes the GUI_Console on this page.
   * Author: K. Dashnaw
   */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /** Returns a SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /** Sets/Initializes the SceneController object containing a reference to this stages parent controller
   * Author: K. Dashnaw
   */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  /** Sets/Initializes a boolean that indicates whether the user has selected a projectType yet, or not.
   * Parameters are:
   * True = User has selected a project type to create.
   * False = User has not selected a project type to create.
   * Author: K. Dashnaw
   */
  private void setProjectTypeSelected(boolean projectTypeSelected)
  {
    this.projectTypeSelected = projectTypeSelected;
  }

  /** Returns a boolean that indicates whether the user has selected a projectType yet, or not.
   * Parameters are:
   * True = User has selected a project type to create.
   * False = User has not selected a project type to create.
   * Author: K. Dashnaw
   */
  public boolean isProjectTypeSelected()
  {
    return projectTypeSelected;
  }

  /** Returns a String value containing the name of the selected project type to create.
   * This is used for evaluating which project type should be created once the user confirms their data.
   * Author: K. Dashnaw
   */
  public String getProjectTypeSelectedString()
  {
    return projectTypeSelectedString;
  }

  /** Sets/Initializes a String value containing the name of the selected project type to create.
   * This is used for evaluating which project type should be created once the user confirms their data.
   * Author: K. Dashnaw
   */
  private void setProjectTypeSelectedString(String projectTypeSelectedString)
  {
    this.projectTypeSelectedString = projectTypeSelectedString;
  }

  /** Returns a reference to the active project model currently providing project related functionality.
   * Author: K. Dashnaw
   * */
  public MainModel getActiveModel()
  {
    return activeModel;
  }

  /** Sets/Initializes the reference to the active project model currently providing project related functionality.
   * Author: K. Dashnaw
   * */
  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  /** This method prompts a warning the user that unsaved data will be lost on change of view-screen.
   * And then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if (this.isProjectTypeSelected() && !(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /** This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();

    //Update GUI Console message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** This method checks if there is any unsaved data and then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.
   * Author: K. Dashnaw
   */
  public void exitApplication()
  {
    //Check if user has selected a projectType yet. If not, it is impossible for the user to have entered any data on this GUI view yet.
    if (this.isProjectTypeSelected() && !(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the creation view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Proceed with application termination.
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** Is called as an on-action event from the one of the 4 buttons on screen, where you select which project type to create.
   * It initializes the next stage of the creation process, making visible shared project data fields
   * as well as unique data fields for the selected project type.
   * Author: K. Dashnaw
   */
  public void ProjectTypeSelected(ActionEvent actionEvent)
  {
    String buttonText = ((Button) actionEvent.getSource()).getText();
    setProjectTypeSelected(true);
    setProjectTypeSelectedString(buttonText);

    //Initialize a new default project of the selected type.
    this.getActiveModel().newActiveProject(this.getProjectTypeSelectedString());
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tResBathroomNumber, tResNumberKitchens, tResNumberOfOtherPlumbing, tResDuration, tResBuildingSize);
        break;
      case "CommercialProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tComNumberFloors, tComDuration, tComBuildingSize, taComIntendedUse);
        break;
      case "IndustrialProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tIndDuration, tIndFacilitySize, taIndFacilityType);
        break;
      case "RoadProjectType":
        this.getActiveModel().initializeCreateProjectGUI(tRDLength, tRDWidth, tRDDuration, taRDBridgeTunnelInfo, taRDEnvironmentInfo);
        break;
    }

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

  /** Loads the data fields on this page, ensuring these are initialized in a reset manner, as well as initializing
   * a new temporary project of the selected project type for use in regard to input validation while user inputs information
   * Author: K. Dashnaw
   * */
  public void loadPageFields()
  {
    //Display the common project data fields:
    this.getSceneController().hideGridElementNode(gridProjectDataContainer, false);
    this.getSceneController().hideLabelElementNode(labelProjectDataPrompt, false);

    //Display the unique data fields relating to the selected project type:
    this.getSceneController().showUniqueProjectDataFields(gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);

    //Display the buttons the user may interact with:
    this.getSceneController().hideButtonElementNode(buttonCreateProject, false);
    this.getSceneController().hideButtonElementNode(buttonCancel, false);

    //Hide the "Select Project Type" prompt and resize the project type pictures for more space on screen:
    labelProjectTypePrompt.setVisible(false);
    resizeAllElementHeight(80);
    resizeProjectButtonWidth(106);
    setNonSelectedProjectTypesTransparent();
  }

  /** Resizes all the elements relating to the initial project selection.
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

  /** Method used to resize a button's height.
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

  /** Method used to resize a button's width.
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

  /** Method used to resize a label's height.
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

  /** Method used to resize the 4 project buttons, which have integrated images inside.
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

  /** Method used to change the text size of a labels integrated text.
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

  /** Method used to reset all project label sizes to their initial values.
   * Is used upon stage refresh/reset.
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

  /** Method used to change the opacity of non-selected project type images for a clearer GUI experience.
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

  /** Method used to reset all project image types to their initial values.
   * Author: K. Dashnaw
   */
  private void resetProjectTypeImagesTransparency()
  {
    imgResidentialProjectType.setOpacity(1);
    imgCommercialProjectType.setOpacity(1);
    imgIndustrialProjectType.setOpacity(1);
    imgRoadProjectType.setOpacity(1);
  }

  /** Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!
   * Author: K. Dashnaw
   */
  public boolean createPromptWindow()
  {
    String confirmationAction = this.getSceneController().createPromptWindow("WARNING: Any unsaved date will be lost.\n\nDo you wish to proceed?\n");

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
        return false; //do not proceed with cancel operation
    }
  }

  /** Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the creation view,
   * which could otherwise result loss of entered non-saved data.!
   * This method is used when changing project type after already selected an initial type. This method does not refresh all data fields,
   * but only the ones that are unique to the selected project.
   * Author: K. Dashnaw
   */
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
            "Cancel confirmed. Project specific data fields on screen have been reset to default values.");
        this.getGUI_Console()
            .setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. Project specific data fields on screen have been reset to default values.");
        return true; //Proceed with cancel operation

      default:
        return false;
    }
  }

  /** Returns FALSE if TextField is empty and TRUE is they are not.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.
   * This method MUST be run on a TextField in order to avoid potential crashes/errors.
   * Author: K. Dashnaw
   */
  public boolean validate_NotEmpty(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty(keyNode))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }

  /** Returns FALSE if TextField is either empty OR a number/digit, and TRUE is TextField is none of both.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.
   * This method MUST be run on a TextField in order to avoid potential crashes/errors.
   * Author: K. Dashnaw
   */
  public boolean validate_NotEmpty_NotNumber(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty_NotNumber(keyNode, "Error in data values while creating new project. Please review and correct!"))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }

  /** Returns FALSE if TextField is either empty OR a string OR a negative number/digit, and TRUE is TextField is none of either.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.
   * This method MUST be run on a TextField in order to avoid potential crashes/errors.
   * Author: K. Dashnaw
   */
  public boolean validate_NotEmpty_NotString_NotNegative(KeyEvent keyNode)
  {
    resetValidation();
    if(getSceneController().validate_NotEmpty_NotString_NotNegative(keyNode, "Error in data values while creating new project. Please review and correct!"))
    {
      addTemporaryProjectData(keyNode);
      return true;
    }
    else
    {
      //Update console with error set in SceneController
      this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      return false;
    }
  }

  /** Method disabled the "create project" button and is used in conjunction with the validation fields to ensure that the
   * "create project" button only is enabled when proper data is ready to be added to the system.
   * Author: K. Dashnaw
   * */
  private void resetValidation()
  {
    buttonCreateProject.setDisable(true);

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());

  }

  /** This code is run locally in this class. It simply checks if the given TextField contains any data or not.
   * Takes a KeyEvent and parses this as a TextField.
   * Warning: KeyEvent source must be a TextField, otherwise crashes may occur.
   * Author: K. Dashnaw
   * */
  private void emptyDatePickerCode(DatePicker node)
  {
    //Add a Show a tooltip!
    this.getSceneController().addErrorTooltip(node, "-fx-text-fill: red;", "Field cannot be empty.");
    node.getEditor().setStyle("-fx-prompt-text-fill: red;");

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("Error in data values while creating new project. Please review and correct!");
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** Runs as an "On Action" event in the .fxml scene on a DatePicker upon interacted with.
   * It performs validation on the selected date and adds this to the active project.
   * Author: K. Dashnaw
   */
  public void validate_DatePicker(ActionEvent actionEvent)
  {
    buttonCreateProject.setDisable(true);
    DatePicker date = (DatePicker) actionEvent.getSource();

    TextField dateValue = new TextField();
    dateValue.setText(date.getEditor().getText());
    dateValue.setPromptText(date.getPromptText());

    //Check if field is empty:
    if (date.getEditor().getText().isBlank())
    {
      emptyDatePickerCode(date);
    }
    else
    {
      addTemporaryProjectData(dateValue);
    }
  }

  /** Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.
   * Method adds the received KeyEvent node to the project data.
   * It receives a "KeyEvent node" and parses this to a TextField.
   * Warning: KeyEvent node must have a source type of TextField, else errors will occur.
   * Author: K. Dashnaw
   * */
  public void addTemporaryProjectData(KeyEvent keyNode)
  {
    TextField userInput = (TextField) keyNode.getSource();
    addTemporaryProjectData(userInput);
  }

  /** Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.
   * Method adds the received KeyEvent node to the project data.
   * It receives a "KeyEvent node" and parses this to a TextArea
   * Warning: KeyEvent node must have a source type of TextArea, else errors will occur.
   * Author: K. Dashnaw
   * */
  public void addTemporaryProjectData_TextArea(KeyEvent keyNode)
  {
    TextArea userInput = (TextArea) keyNode.getSource();
    TextField text2 = new TextField();
    text2.setText(userInput.getText());
    text2.setPromptText(userInput.getPromptText());
    addTemporaryProjectData(text2);
  }

  /** This method is used in conjunction with the above input validation methods.
   * It allows the typed text fields to be passed onto the "addCommonProjectData" input validation step
   * It simply ensures that the proper add Method is called (based on project Type).
   * It takes a TextField value, which is passed on to the identified proper child method.
   * Author: K. Dashnaw
   * */
  public void addTemporaryProjectData(TextField text)
  {
    //Get the project type:
    switch (this.getProjectTypeSelectedString())
    {
      case "ResidentialProjectType":
        addTemporaryResidentialData((ResidentialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "CommercialProjectType":
        addTemporaryCommercialData((CommercialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "IndustrialProjectType":
        addTemporaryIndustrialData((IndustrialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "RoadProjectType":
        addTemporaryRoadData((RoadProject) this.getActiveModel().getSelectedProject(), text);
        break;
    }
  }

  /** Is called from "On Action" EventHandlers in the .fxml scene
   * Method adds the received ActionEvent node to the project data.
   * It receives a "ActionEvent node" parses this as a "CheckBox" and checks if it is selected or not.
   * Warning: ActionEvent node must have a source type of CheckBox, else errors will occur.
   * Author: K. Dashnaw
   * */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    buttonCreateProject.setDisable(true);
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
    value.setPromptText(value.getText());
    addTemporaryProjectData(value);
  }

  /** This method is used in conjunction with the "addTemporaryProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.
   * Returns TRUE if data was added and FALSE if no data was added.
   * Parameters are:
   * "ConstructionProject project": This is a reference to the super class that all construction projects are a member of.
   * "TextField text": This is a reference to the node containing the information to add to the project.
   * Author: K. Dashnaw
   * */
  public boolean addCommonProjectData(ConstructionProject project, TextField text)
  {
    boolean dataAddedToProject = false;

    //TODO: Implement handling of picture file link!

    switch (text.getPromptText())
    {
      case "first name":
        project.getCustomer().setFirstName(text.getText());
        dataAddedToProject = true;
        break;
      case "last name":
        project.getCustomer().setLastName(text.getText());
        dataAddedToProject = true;
        break;
      case "Prefix (ie. +45)":
        project.getCustomer().setPhoneNumberPrefix(text.getText());
        dataAddedToProject = true;
        break;
      case "Number (8 digits)":
        project.getCustomer().setPhoneNumber(Integer.parseInt(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "email":
        project.getCustomer().setEmail(text.getText());
        dataAddedToProject = true;
        break;
      case "Company Name the customer is representing":
        project.getCustomer().getCustomerCompany().setName(text.getText());
        dataAddedToProject = true;
        break;
      case "Customers Street Name":
        project.getCustomer().getCustomerAddress().setStreet(text.getText());
        dataAddedToProject = true;
        break;
      case "Building number":
        project.getCustomer().getCustomerAddress().setStreetNumber(text.getText());
        dataAddedToProject = true;
        break;
      case "Customer apartment number, if applicable.":
        project.getCustomer().getCustomerAddress().setApartment(text.getText());
        dataAddedToProject = true;
        break;
      case "ZIP code":
        project.getCustomer().getCustomerAddress().setPostalCode(Integer.parseInt(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "City":
        project.getCustomer().getCustomerAddress().setCity(text.getText());
        dataAddedToProject = true;
        break;
      case "Country":
        project.getCustomer().getCustomerAddress().setCountry(text.getText());
        dataAddedToProject = true;
        break;
      case "Street name":
        project.getProjectAddress().setStreet(text.getText());
        dataAddedToProject = true;
        break;
      case "Property number":
        project.getProjectAddress().setStreetNumber(text.getText());
        dataAddedToProject = true;
        break;
      case "Apartment number, if applicable. (else leave empty)":
        project.getProjectAddress().setApartment(text.getText());
        dataAddedToProject = true;
        break;
      case "Project ZIP code":
        project.getProjectAddress().setPostalCode(Integer.parseInt(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Project City":
        project.getProjectAddress().setCity(text.getText());
        dataAddedToProject = true;
        break;
      case "Project Country":
        project.getProjectAddress().setCountry(text.getText());
        dataAddedToProject = true;
        break;
      case "In hours":
        project.getHumanRessources().setTotalManHoursNeeded(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "in $USD":
        this.getSceneController().validateIsWithinNormalMargins(text);
        project.getFinances().setTotalBudget(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Enter any internal only notes directed towards the project manager":
        project.getProjectInformation().setProjectManagerComments(text.getText());
        dataAddedToProject = true;
        break;
      case "Start date":
        String receivedStartDate = text.getText();
        String dayStart = receivedStartDate.substring(0,2);
        String monthStart = receivedStartDate.substring(3,5);
        String yearStart = receivedStartDate.substring(6,10);
        project.getProjectStartDate().set(Integer.parseInt(dayStart), Integer.parseInt(monthStart), Integer.parseInt(yearStart));

        //Reset project duration textField field with new duration:
        tIndDuration.setText("" + getActiveModel().getDefaultIndustrialSettings().getProjectDuration());
        tResDuration.setText("" + getActiveModel().getDefaultResidentialSettings().getProjectDuration());
        tComDuration.setText("" + getActiveModel().getDefaultCommercialSettings().getProjectDuration());
        tRDDuration.setText("" + getActiveModel().getDefaultRoadSettings().getProjectDuration());

        if(project instanceof ResidentialProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultResidentialSettings().getProjectDuration());
        }
        else if(project instanceof CommercialProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultCommercialSettings().getProjectDuration());
        }
        else if(project instanceof IndustrialProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultIndustrialSettings().getProjectDuration());
        }
        else if(project instanceof RoadProject)
        {
          project.setProjectDuration(getActiveModel().getDefaultRoadSettings().getProjectDuration());
        }

        //Update estimated Completion date:
        project.getProjectEndDate().set(project.getProjectStartDate().getDay(), project.getProjectStartDate().getMonth(), project.getProjectStartDate().getYear());
        for (int i = 0; i < project.getProjectDuration()*30; i++)
        {
          project.getProjectEndDate().stepForwardOneDay();
        }

        //Calculate months between the start and end dates, and update the "project duration" attribute:
        int daysBetweenStart = project.getProjectEndDate().daysBetween(project.getProjectStartDate());
        project.setProjectDuration(daysBetweenStart/30);
        date_EndDateField.getEditor().setText(project.getProjectEndDate().getDay() + "." + project.getProjectEndDate().getMonth() + "." + project.getProjectEndDate().getYear());
        dataAddedToProject = true;

        //Update project duration textField again field with new duration:
        if(project instanceof ResidentialProject)
        {
          tResDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tResDuration);
        }
        else if(project instanceof CommercialProject)
        {
          tComDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tComDuration);
        }
        else if(project instanceof IndustrialProject)
        {
          tIndDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tIndDuration);
        }
        else if(project instanceof RoadProject)
        {
          tRDDuration.setText("" + project.getProjectDuration());
          this.getSceneController().validateIsWithinNormalMargins(tRDDuration);
        }
        break;
      case "Est. Completion Date":
        String receivedEndDate = text.getText();
        String dayEnd = receivedEndDate.substring(0,2);
        String monthEnd = receivedEndDate.substring(3,5);
        String yearEnd = receivedEndDate.substring(6,10);
        project.getProjectEndDate().set(Integer.parseInt(dayEnd), Integer.parseInt(monthEnd), Integer.parseInt(yearEnd));
        dataAddedToProject = true;

        //Calculate months between the start and end dates, and update the "project duration" attribute:
        int daysBetween = project.getProjectEndDate().daysBetween(project.getProjectStartDate());
        project.setProjectDuration(daysBetween/30);

        //Update project duration field with new duration:
        tIndDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tIndDuration);
        tResDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tResDuration);
        tComDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tComDuration);
        tRDDuration.setText("" + project.getProjectDuration());
        this.getSceneController().validateIsWithinNormalMargins(tRDDuration);
        break;
      case "Enter a short project description. This information is exported and displayed on the company homepage":
        project.getProjectInformation().setProjectDescription(text.getText());
        dataAddedToProject = true;
        break;
      case "Project name. Will be displayed on homepage":
        project.getProjectInformation().setProjectName(text.getText());
        dataAddedToProject = true;
        break;
      case "Add project to Dashboard?_True", "Dashboard is already full._True":
        if(this.getActiveModel().getDashboardProgressReports().getCurrentCapacity() <= this.getActiveModel().getDashboardProgressReports().getMaxCapacity() && this.getActiveModel().getDashboardProgressReports().getCurrentCapacity() > 0)
        {
          project.setDashboardProject(true);
        }
        else
        {
          checkBox_AddToDashBoard.setSelected(false);
          checkBox_AddToDashBoard.setDisable(true);
          checkBox_AddToDashBoard.setText("Dashboard is already full.");
        }
        dataAddedToProject = true;
        break;
      case "Add project to Dashboard?_False", "Dashboard is already full._False":
        project.setDashboardProject(false);
        dataAddedToProject = true;
        break;
      case "Is project confidential?_True":
        project.setProjectConfidentiality(true);
        dataAddedToProject = true;
        break;
      case "Is project confidential?_False":
        project.setProjectConfidentiality(false);
        dataAddedToProject = true;
        break;
      default:
        break;
    }

    if(dataAddedToProject)
    {
      activateCreateButton();
      return true;
    }
    else
    {
      return false;
    }
  }

  /** This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.
   * Parameters are:
   * "ResidentialProject project": This is a reference to the specific project type Class.
   * "TextField text": This is a reference to the node containing the information to add to the project.
   * Author: K. Dashnaw
   * */
  public void addTemporaryResidentialData(ResidentialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryResidentialData(project, text);
      activateCreateButton();
    }
  }

  /** This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.
   * Parameters are:
   * "CommercialProject project": This is a reference to the specific project type Class.
   * "TextField text": This is a reference to the node containing the information to add to the project.
   * Author: K. Dashnaw
   * */
  public void addTemporaryCommercialData(CommercialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryCommercialData(project, text);
      activateCreateButton();
    }
  }

  /** This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.
   * Parameters are:
   * "IndustrialProject project": This is a reference to the specific project type Class.
   * "TextField text": This is a reference to the node containing the information to add to the project.
   * Author: K. Dashnaw
   * */
  public void addTemporaryIndustrialData(IndustrialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryIndustrialData(project, text);
      activateCreateButton();
    }
  }

  /** This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.
   * Parameters are:
   * "RoadProject project": This is a reference to the specific project type Class.
   * "TextField text": This is a reference to the node containing the information to add to the project.
   * Author: K. Dashnaw
   * */
  public void addTemporaryRoadData(RoadProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryRoadData(project, text);
      activateCreateButton();
    }
  }

  /** This method checks if all required data fields have been filled out before enabling the "create project" button.
   * Author: K. Dashnaw
   * */
  public void activateCreateButton()
  {
    //First check if project has all necessary values for creation:

    boolean dataIsMissing = this.getSceneController().validateActiveProject(date_EndDateField);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    //If all required fields are present. Activate the create button now.
    buttonCreateProject.setDisable(dataIsMissing);
  }

  /** This method finalizes the project creation by calling relevant methods from the MainModel. It also asks the user to confirm their creation before finalizing.
   * Author: K. Dashnaw
   * */
  public void createNewProject()
  {

    //Prompt user to confirm:
    if(this.getSceneController().createPromptWindow("Add project to system?").equalsIgnoreCase("confirmationPressed"))
    {
      //Add project to system.
      if(this.getActiveModel().addProject(this.getActiveModel().getSelectedProject()))
      {
        //Project successfully added!
        //Now check if project should be added to the Dashboard:
        if (this.getActiveModel().getSelectedProject().isDashboardProject())
        {
          this.getActiveModel().getDashboardProgressReports().addProgressReport(this.activeModel.getSelectedProject().generateProgressReport());
        }
        //Update console with message:
        this.getSceneController().setGUI_ConsoleMessage("New projected added to system. System saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

        //Reset creation view.
        refresh();

        //Redirect user back to the "view project" page:
        try
        {
          this.getSceneController().openWindow("Projects", this.getGUI_Console());
        }
        catch(IOException error)
        {
          System.out.println("Unknown error occurred after project creation. No data has been lost.");
        }
      }
      else
      {
        //Project was NOT added successfully.
        //Update console with error message from the model:
        this.getSceneController().setGUI_ConsoleMessage(this.getActiveModel().getInitializationErrorMessage());
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
      }
    }
  }
}