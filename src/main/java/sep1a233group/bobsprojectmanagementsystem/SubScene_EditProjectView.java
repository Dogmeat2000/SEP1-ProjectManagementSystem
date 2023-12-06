package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/** <p>This class controls the GUI related view and methods concerning the "edit project" GUI stage.
 * It refers to SceneController for shared GUI related actions and methods.
 * It refers to MainModel for model specific methods and actions.</p>
 * <p><b>Author:</b> K. Dashnaw</p>
 */
public class SubScene_EditProjectView implements Scene_ControllerInterface
{
  //Residential Project control attributes
  @FXML GridPane gridResidentialUniqueData;
  @FXML TextField tResBathroomNumber;
  @FXML TextField tResNumberKitchens;
  @FXML TextField tResNumberOfOtherPlumbing;
  @FXML TextField tResDuration;
  @FXML TextField tResBuildingSize;
  @FXML CheckBox isRenovation;

  //Commercial Project control attributes
  @FXML GridPane gridCommercialUniqueData;
  @FXML TextField tComNumberFloors;
  @FXML TextField tComDuration;
  @FXML TextField tComBuildingSize;
  @FXML TextArea taComIntendedUse;

  //Industrial Project control attributes
  @FXML GridPane gridIndustrialUniqueData;
  @FXML TextField tIndDuration;
  @FXML TextField tIndFacilitySize;
  @FXML TextArea taIndFacilityType;

  //Road Project control attributes
  @FXML GridPane gridRoadUniqueData;
  @FXML TextField tRDLength;
  @FXML TextField tRDWidth;
  @FXML TextField tRDDuration;
  @FXML TextArea taRDBridgeTunnelInfo;
  @FXML TextArea taRDEnvironmentInfo;

  //Shared Control attributes
  @FXML TextField GUI_Console;
  @FXML GridPane gridProjectDataContainer;
  @FXML GridPane gridCommonProjectDataContainer;
  @FXML Button buttonEditProject;
  @FXML Button buttonCancel;
  @FXML TextArea taProjectDescription;
  @FXML TextArea taManagersComments;
  @FXML DatePicker date_StartDateField;
  @FXML DatePicker date_EndDateField;
  @FXML CheckBox checkBox_AddToDashBoard;
  @FXML Label labelLastProjectSave;
  @FXML Label labelHTMLExportDate;

  //Other field Attributes:
  private MainModel activeModel;
  private SceneController sceneController;

  /** <p>Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)</p>
   */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    //Sets necessary attributes:
    setActiveModel(activeModel);
    setSceneController(sceneController);
    this.setGUI_Console(this.GUI_Console);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
    refresh();
  }

  /** <p>Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  @Override public void refresh()
  {
    //Refresh the page, as it is shown on a clean load:
    this.getSceneController().showUniqueProjectDataFields(gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);
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

    System.out.println("Edit Scene is now the active stage.");

    //Loop though the shared project data gridPane and insert selected project values inside TextFields - while also ensuring fields are reset for previous views of this screen.
    for (Node node : gridProjectDataContainer.getChildren())
    {
      if (node instanceof TextArea)
      {
        ((TextArea) node).setText("");
      }
      else if (node instanceof TextField)
      {
        ((TextField) node).setText(this.loadProjectData_String((TextField) node));
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
      else if(node instanceof DatePicker)
      {
        ((DatePicker) node).getEditor().setText("");
      }
    }

    //Loop though the commonly used project data gridPane and insert selected project values inside TextFields - while also ensuring fields are reset for previous views of this screen.
    //Loop though non fx:id referenced data fields and reset these.
    for (Node node : gridCommonProjectDataContainer.getChildren())
    {
      if (node instanceof TextField)
      {
        ((TextField) node).setText(this.loadProjectData_String((TextField) node));
        ((TextField) node).setTooltip(null);
        node.setStyle("-fx-text-fill: black;");
      }
      else if(node instanceof CheckBox)
      {
        ((CheckBox) node).setSelected(this.getSceneController().loadProjectData_Checkbox((CheckBox) node));
        node.setDisable(false);
      }
    }

    //Set the remaining more "hard to get to" data fields:
    taProjectDescription.setText(this.getActiveModel().getSelectedProject().getProjectInformation().getProjectDescription());
    taManagersComments.setText(this.getActiveModel().getSelectedProject().getProjectInformation().getProjectManagerComments());
    date_StartDateField.getEditor().setText("" + this.getActiveModel().getSelectedProject().getProjectStartDate());
    date_EndDateField.getEditor().setText("" + this.getActiveModel().getSelectedProject().getProjectEndDate());

    //Set the Project specific datafields:
    if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("residential"))
    {
      ResidentialProject project = (ResidentialProject) this.getActiveModel().getSelectedProject();
      tResBathroomNumber.setText("" + project.getNumberOfBathrooms());
      tResNumberKitchens.setText("" + project.getNumberOfKitchens());
      tResNumberOfOtherPlumbing.setText("" + project.getNumberOfOtherRoomsWithPlumbing());
      tResDuration.setText("" + project.getProjectDuration());
      tResBuildingSize.setText("" + project.getBuildingSize());
      isRenovation.setSelected(project.getIsRenovation());
    }
    else if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("commercial"))
    {
      CommercialProject project = (CommercialProject) this.getActiveModel().getSelectedProject();
      tComNumberFloors.setText("" + project.getNumberOfFloors());
      tComDuration.setText("" + project.getProjectDuration());
      tComBuildingSize.setText("" + project.getBuildingSize());
      taComIntendedUse.setText(project.getIntendedBuildingUse());
    }
    else if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("industrial"))
    {
      IndustrialProject project = (IndustrialProject) this.getActiveModel().getSelectedProject();
      tIndDuration.setText("" + project.getProjectDuration());
      tIndFacilitySize.setText("" + project.getFacilitySize());
      taIndFacilityType.setText(project.getFacilityType());
    }
    else if(this.getActiveModel().getSelectedProject().getProjectType().equalsIgnoreCase("road"))
    {
      RoadProject project = (RoadProject) this.getActiveModel().getSelectedProject();
      tRDLength.setText("" + project.getRoadLength());
      tRDWidth.setText("" + project.getRoadWidth());
      tRDDuration.setText("" + project.getProjectDuration());
      taRDBridgeTunnelInfo.setText(project.getBridgeOrTunnelDetails());
      taRDEnvironmentInfo.setText(project.getEnvironmentalOrGeographicalChallenges());
    }

    checkBox_AddToDashBoard.setText("Track on Dashboard");

    //Refresh GUI console latest message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** <p>Returns a reference to the GUI_Console on this page.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public TextField getGUI_Console()
  {
    return GUI_Console;
  }

  /** <p>Sets/Initializes the GUI_Console on this page.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setGUI_Console(TextField GUI_Console)
  {
    this.GUI_Console = GUI_Console;
  }

  /** <p>Returns a SceneController object containing a reference to this stages parent controller</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public SceneController getSceneController()
  {
    return sceneController;
  }

  /** <p>Sets/Initializes the SceneController object containing a reference to this stages parent controller</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void setSceneController(SceneController sceneController)
  {
    this.sceneController = sceneController;
  }

  /** <p>Returns a reference to the active project model currently providing project related functionality.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public MainModel getActiveModel()
  {
    return activeModel;
  }

  /** <p>Sets/Initializes the reference to the active project model currently providing project related functionality.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  /** <p>This method prompts a warning to the user that unsaved data will be lost on change of view-screen.
   * And then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.openWindow() for a more detailed description.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void openWindow(ActionEvent actionEvent) throws IOException
  {
    //Prompt the user to confirm they wish to leave this screen.
    if (!(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the edit view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Load the scene the user selected.
    String buttonText = ((Button) actionEvent.getSource()).getText().toLowerCase();
    this.getSceneController().openWindow(buttonText, this.getGUI_Console());
  }

  /** <p>This method simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exportToWeb() for a more detailed description.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void exportToWeb()
  {
    this.getSceneController().exportToWeb();

    //Update GUI Console message:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** <p>This method checks if there is any unsaved data and then simply calls the common method with the same name, from the SceneController.
   * Check SceneController.exitApplication() for a more detailed description.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void exitApplication()
  {
    //Prompt the user to confirm they wish to leave this screen.
    if (!(createPromptWindow()))
    {
      //Prompt the user to confirm they really wish to navigate away from the edit view - loosing all unsaved data.
      //If the user aborted, we break the current method here.
      return;
    }
    //Proceed with application termination.
    this.getSceneController().exitApplication();

    //Update console message, in case an error occurred above:
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
  }

  /** <p>Method used to create a confirmation prompt window, in order to prompt the user before navigating away from the edit view,
   * which could otherwise result loss of entered non-saved data.!</p>
   * <p><b>Author:</b> K. Dashnaw</p>
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
        this.getSceneController().setGUI_ConsoleMessage("Cancel confirmed. No changes were saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        System.out.println("Cancel confirmed. No changes were saved.");

        try
        {
          this.getSceneController().openWindow("Projects", this.getGUI_Console());
        }
        catch(IOException error)
        {
          this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to return to project view. Reason unknown: " + error);
          this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
        }
        return true; //Proceed with cancel operation
      default:
        return false; //do not proceed with cancel operation
    }
  }


  /** <p>This method returns the selected projects Data Field values that correspond with the shared project data fields.
   * This is intended for insertion into the on-screen editable textFields, so that already existing data is pre-entered for the user.</p>
   * <p><b>Author:</b> K. Dashnaw </p>
   * @param node is a reference to the TextField node in which the returned String value shall be inserted.
   * @return A String value intended to be inserted into the above TextField node.
   * */
  public String loadProjectData_String (TextField node)
  {
    switch (node.getPromptText())
    {
      case "first name":
        return this.getActiveModel().getSelectedProject().getCustomer().getFirstName();
      case "last name":
        return this.getActiveModel().getSelectedProject().getCustomer().getLastName();
      case "Prefix (ie. +45)":
        return this.getActiveModel().getSelectedProject().getCustomer().getPhoneNumberPrefix();
      case "Number (8 digits)":
        return "" + this.getActiveModel().getSelectedProject().getCustomer().getPhoneNumber();
      case "email":
        return this.getActiveModel().getSelectedProject().getCustomer().getEmail();
      case "Company Name the customer is representing":
        return this.getActiveModel().getSelectedProject().getCustomer().getCustomerCompany().getName();
      case "Customers Street Name":
        return this.getActiveModel().getSelectedProject().getCustomer().getCustomerAddress().getStreet();
      case "Building number":
        return this.getActiveModel().getSelectedProject().getCustomer().getCustomerAddress().getStreetNumber();
      case "Customer apartment number, if applicable.":
        return this.getActiveModel().getSelectedProject().getCustomer().getCustomerAddress().getApartment();
      case "ZIP code":
        return "" + this.getActiveModel().getSelectedProject().getCustomer().getCustomerAddress().getPostalCode();
      case "City":
        return this.getActiveModel().getSelectedProject().getCustomer().getCustomerAddress().getCity();
      case "Country":
        return this.getActiveModel().getSelectedProject().getCustomer().getCustomerAddress().getCountry();
      case "Street name":
        return this.getActiveModel().getSelectedProject().getProjectAddress().getStreet();
      case "Property number":
        return this.getActiveModel().getSelectedProject().getProjectAddress().getStreetNumber();
      case "Apartment number, if applicable. (else leave empty)":
        return this.getActiveModel().getSelectedProject().getProjectAddress().getApartment();
      case "Project ZIP code":
        return "" + this.getActiveModel().getSelectedProject().getProjectAddress().getPostalCode();
      case "Project City":
        return this.getActiveModel().getSelectedProject().getProjectAddress().getCity();
      case "Project Country":
        return this.getActiveModel().getSelectedProject().getProjectAddress().getCountry();
      case "Man-Hours in hours":
        return "" + this.getActiveModel().getSelectedProject().getHumanRessources().getManHoursSpent();
      case "Est. total number of hours":
        return "" + this.getActiveModel().getSelectedProject().getHumanRessources().getTotalManHoursNeeded();
      case "Expenses in USD":
        return "" + this.getActiveModel().getSelectedProject().getFinances().getMaterialExpences();
      case "Budget in USD":
        return "" + this.getActiveModel().getSelectedProject().getFinances().getTotalBudget();
      case "Project name. Will be displayed on homepage":
        return this.getActiveModel().getSelectedProject().getProjectInformation().getProjectName();
      default:
        break;
    }
    return "";
  }

  /** <p>Returns FALSE if TextField is empty and TRUE is they are not.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.</p>
   * <p><b>This method MUST be run on a TextField in order to avoid potential crashes/errors.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
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

  /** <p>Returns FALSE if TextField is either empty OR a number/digit, and TRUE is TextField is none of both.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.</p>
   * <p><b>This method MUST be run on a TextField in order to avoid potential crashes/errors.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
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

  /** <p>Method disabled the "create project" button and is used in conjunction with the validation fields to ensure that the
   * "create project" button only is enabled when proper data is ready to be added to the system.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  private void resetValidation()
  {
    buttonEditProject.setDisable(true);

    //Update console with an error:
    this.getSceneController().setGUI_ConsoleMessage("");
    this.getGUI_Console()
        .setText(this.getSceneController().getGUI_ConsoleMessage());

  }

  /** <p>This code is run locally in this class. It simply checks if the given TextField contains any data or not.
   * Takes a KeyEvent and parses this as a TextField.</p>
   * <p><b>Warning: KeyEvent source must be a TextField, otherwise crashes may occur.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
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

  /** <p>Runs as an "On Action" event in the .fxml scene on a DatePicker upon interacted with.
   * It performs validation on the selected date and adds this to the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void validate_DatePicker(ActionEvent actionEvent)
  {
    buttonEditProject.setDisable(true);
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

  /** <p>Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.
   * Method adds the received KeyEvent node to the project data.
   * It receives a "KeyEvent node" and parses this to a TextField.</p>
   * <p><b>Warning: KeyEvent node must have a source type of TextField, else errors will occur.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void addTemporaryProjectData(KeyEvent keyNode)
  {
    TextField userInput = (TextField) keyNode.getSource();
    addTemporaryProjectData(userInput);
  }

  /** <p>Can be called from eventHandlers in the .fxml scene, which do not require input validation,
   * or from the input validation methods in this class.
   * Method adds the received KeyEvent node to the project data.
   * It receives a "KeyEvent node" and parses this to a TextArea</p>
   * <p>Warning: KeyEvent node must have a source type of TextArea, else errors will occur.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void addTemporaryProjectData_TextArea(KeyEvent keyNode)
  {
    TextArea userInput = (TextArea) keyNode.getSource();
    TextField text2 = new TextField();
    text2.setText(userInput.getText());
    text2.setPromptText(userInput.getPromptText());
    addTemporaryProjectData(text2);
  }

  /** <p>This method is used in conjunction with the above input validation methods.
   * It allows the typed text fields to be passed onto the "addCommonProjectData" input validation step
   * It simply ensures that the proper add Method is called (based on project Type).</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param text It takes a TextField value, which is passed on to the identified proper child method.
   * */
  public void addTemporaryProjectData(TextField text)
  {
    //Get the project type:
    switch (this.getActiveModel().getSelectedProject().getProjectType())
    {
      case "Residential":
        addTemporaryResidentialData((ResidentialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "Commercial":
        addTemporaryCommercialData((CommercialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "Industrial":
        addTemporaryIndustrialData((IndustrialProject) this.getActiveModel().getSelectedProject(), text);
        break;
      case "Road":
        addTemporaryRoadData((RoadProject) this.getActiveModel().getSelectedProject(), text);
        break;
    }
  }

  /** <p>Is called from "On Action" EventHandlers in the .fxml scene
   * Method adds the received ActionEvent node to the project data.
   * It receives a "ActionEvent node" parses this as a "CheckBox" and checks if it is selected or not.<br>
   * <b>Warning: ActionEvent node must have a source type of CheckBox, else errors will occur.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void checkBoxChecker(ActionEvent actionEvent)
  {
    buttonEditProject.setDisable(true);
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

  /** <p>This method is used in conjunction with the "addTemporaryProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so modifies the in the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the super class that all construction projects are a member of.
   * @param text This is a reference to the node containing the information to add to the project.
   * @return A boolean that is either true or false. True means that data was modified. False means that it was not.
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
      case "Man-Hours in hours":
        project.getHumanRessources().setManHoursSpent(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Est. total number of hours":
        project.getHumanRessources().setTotalManHoursNeeded(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Budget in USD":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.getFinances().setTotalBudget(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Expenses in USD":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.getFinances().setMaterialExpences(Double.parseDouble(text.getText().trim()));
        dataAddedToProject = true;
        break;
      case "Enter any internal only notes directed towards the project manager":
        project.getProjectInformation().setProjectManagerComments(text.getText());
        dataAddedToProject = true;
        break;
      case "Start date":
        String receivedStartDate = text.getText();
        String dayStart = receivedStartDate.substring(0,1);
        String monthStart = receivedStartDate.substring(3,4);
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
        }
        else if(project instanceof CommercialProject)
        {
          tComDuration.setText("" + project.getProjectDuration());
        }
        else if(project instanceof IndustrialProject)
        {
          tIndDuration.setText("" + project.getProjectDuration());
        }
        else if(project instanceof RoadProject)
        {
          tRDDuration.setText("" + project.getProjectDuration());
        }

        break;
      case "Est. Completion Date":
        String receivedEndDate = text.getText();
        String dayEnd = receivedEndDate.substring(0,1);
        String monthEnd = receivedEndDate.substring(3,4);
        String yearEnd = receivedEndDate.substring(6,10);
        project.getProjectEndDate().set(Integer.parseInt(dayEnd), Integer.parseInt(monthEnd), Integer.parseInt(yearEnd));
        dataAddedToProject = true;

        //Calculate months between the start and end dates, and update the "project duration" attribute:
        int daysBetween = project.getProjectEndDate().daysBetween(project.getProjectStartDate());
        project.setProjectDuration(daysBetween/30);

        //Update project duration field with new duration:
        tIndDuration.setText("" + project.getProjectDuration());
        tResDuration.setText("" + project.getProjectDuration());
        tComDuration.setText("" + project.getProjectDuration());
        tRDDuration.setText("" + project.getProjectDuration());
        break;
      case "Enter a short project description. This information is exported and displayed on the company homepage":
        project.getProjectInformation().setProjectDescription(text.getText());
        dataAddedToProject = true;
        break;
      case "Project name. Will be displayed on homepage":
        project.getProjectInformation().setProjectName(text.getText());
        dataAddedToProject = true;
        break;
      case "Track on Dashboard_True", "Dashboard is already full._True":
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
      case "Mark project as confidential_True":
        project.setProjectConfidentiality(true);
        dataAddedToProject = true;
        break;
      case "Mark project as confidential_False":
        project.setProjectConfidentiality(false);
        dataAddedToProject = true;
        break;
      case "Mark project as completed_True":
        project.setProjectFinished(true);
        dataAddedToProject = true;
        break;
      case "Mark project as completed_False":
        project.setProjectFinished(false);
        dataAddedToProject = true;
        break;
      default:
        break;
    }

    if(dataAddedToProject)
    {
      activateEditButton();
      return true;
    }
    else
    {
      return false;
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */
  public void addTemporaryResidentialData(ResidentialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryResidentialData(project, text);
      activateEditButton();
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */
  public void addTemporaryCommercialData(CommercialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryCommercialData(project, text);
      activateEditButton();
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */
  public void addTemporaryIndustrialData(IndustrialProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryIndustrialData(project, text);
      activateEditButton();
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */
  public void addTemporaryRoadData(RoadProject project, TextField text)
  {
    if (!(addCommonProjectData(project, text)))
    {
      this.getSceneController().setTemporaryRoadData(project, text);
      activateEditButton();
    }
  }

  /** <p>This method checks if all required data fields have been filled out before enabling the "edit project" button.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void activateEditButton()
  {
    //First check if project has all necessary values for creation:
    boolean dataIsMissing = this.getSceneController().validateActiveProject(date_EndDateField);
    this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

    //If all required fields are present. Activate the edit button now.
    buttonEditProject.setDisable(dataIsMissing);
  }

  /** <p>This method finalizes the project creation by calling relevant methods from the MainModel. It also asks the user to confirm their creation before finalizing.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
  public void editProject()
  {
    //Prompt user to confirm:
    if(this.getSceneController().createPromptWindow("Save changes?").equalsIgnoreCase("confirmationPressed"))
    {
      //Remove the original project from the project_list, and replace with this modified one.
      this.getActiveModel().removeProject(this.getActiveModel().getAllProjectsList().get(this.getActiveModel().getProjectIndexPosition()));

      //Add modified project to system.
      if(this.getActiveModel().editProject(this.getActiveModel().getSelectedProject()))
      {
        //Project successfully added!

        //Now we refresh the dashboard object container:
        this.getActiveModel().refreshDashboardProjects();

        //Update console with message:
        this.getSceneController().setGUI_ConsoleMessage("Project successfully modified. System saved.");
        this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());

        //Re-direct user back to the project view page:
        try
        {
          this.getSceneController().openWindow("Projects", this.getGUI_Console());
        }
        catch(IOException error)
        {
          this.getSceneController().setGUI_ConsoleMessage("ERROR: Unable to return to project view. Reason unknown: " + error);
          this.getGUI_Console().setText(this.getSceneController().getGUI_ConsoleMessage());
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