package sep1a233group.bobsprojectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static javafx.application.Platform.exit;

/** This class controls the overall flow between the various .fxml scenes in this application.
 * It allows for a seamless transition between windows for the user.
 * This class also maintains the shared operations that each scene/stage calls, in order to avoid having duplicate methods described multiple places.
 * Thus, many of the stages will refer to this class for operational instructions in their methods.
 * Author: K. Dashnaw, inspired by the work of Lector Michael from our SDJ1 education.*/
public class SceneController
{
  private MainModel activeModel; //Refers to the currently active model. (The one associated with the active scene)
  private Stage activeStage; //Refers to the scene/stage that is currently being displayed in the application.

  private Map<String, Scene_ControllerInterface> sceneControllers; //Here we map a String key to a Scene value, so that it is possible for us to easily identify which controller is connected to which window.

  private Map<String, Scene> scenes; //Here we map a String "key" to a scene, so that we can easily identify which scene is connected to each window.

  private String GUI_ConsoleMessage; //Continuously updated by GUI pages and contains the latest console message to display.


  /** The Scene Controller class is used for controlling the active scene in the GUI application.
   * It is inspired by some of the SDJ1 developmental work our Lector, Michael, has presented during the SDJ1 course - specifically relating to the use of a 'viewhandler'
   */
  public SceneController(MainModel activeModel, Stage activeStage)
  {
    setActiveModel(activeModel);
    setActiveStage(activeStage);
    if(getActiveModel().getInitializationErrorMessage().isEmpty())
    {
      setGUI_ConsoleMessage("Application successfully loaded");
    }
    else
    {
      setGUI_ConsoleMessage(getActiveModel().getInitializationErrorMessage());
    }
    sceneControllers = new HashMap<>();
    scenes = new HashMap<>();
  }

  /** Returns the screen view that is currently active
   * Author: K. Dashnaw
   * */
  public Stage getActiveStage()
  {
    return activeStage;
  }

  /** Sets the screen view that is currently active
   * Author: K. Dashnaw
   * */
  public void setActiveStage(Stage activeStage)
  {
    this.activeStage = activeStage;
  }

  /** Returns the latest GUI Console message. This attribute ensures cross-view port compatibility so
   * console on all view panes receive the same messages.
   * Author: K. Dashnaw
   * */
  public String getGUI_ConsoleMessage()
  {
    return GUI_ConsoleMessage;
  }

  /** Sets the latest GUI Console message. This attribute ensures cross-view port compatibility so
   * console on all view panes receive the same messages.
   * Author: K. Dashnaw
   * */
  public void setGUI_ConsoleMessage(String GUI_ConsoleMessage)
  {
    this.GUI_ConsoleMessage = GUI_ConsoleMessage;
  }

  /** Sets the active project model.
   * Author: K. Dashnaw
   * */
  public MainModel getActiveModel()
  {
    return activeModel;
  }

  /** Gets the active project model.
   * Author: K. Dashnaw
   * */
  public void setActiveModel(MainModel activeModel)
  {
    this.activeModel = activeModel;
  }

  /**
   * This method when called, loads a new window into the FXML loader, re-using the existing window area.
   * This allows for seamless navigation between different windows in the GUI application.
   * It is inspired by some of the SDJ1 developmental work our Lector, Michael, has presented during the
   * SDJ1 course - specifically relating to the use of a 'viewhandler'
   */
  public void loadNewWindow(String newWindow) throws IOException
  {
    Scene newScene = null;

    //If the controller has not been initialized yet, we need to initialize it:
    if (sceneControllers.get(newWindow) == null)
    {
      //TODO: Implement a try / catch validation here to guard against trying to open scenes that have been moved from their folder - or that no longer exists.

      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(newWindow + ".fxml"));
      newScene = new Scene(fxmlLoader.load(), 1366, 768);
      Scene_ControllerInterface controller = fxmlLoader.getController();
      controller.init(activeModel, this);

      //Then, after creation, we add it to our map
      sceneControllers.put(newWindow, controller);
      scenes.put(newWindow, newScene);
    }
    else
    {
      //The controller is already initialized, so we simply add it to our map and refresh!
      Scene_ControllerInterface controller = sceneControllers.get(newWindow);
      newScene = scenes.get(newWindow);
      controller.refresh();
    }

    activeStage.show();
    activeStage.setScene(newScene);
    activeStage.setResizable(false);
    activeStage.setTitle("Bob's Project Management System");
  }

  /** This is a common method shared between all GUI scenes. This method saves all system data before terminating the application safely. */
  public void exitApplication()
  {
    System.out.println(
        "User pressed the 'EXIT' button. System is now attempting to terminate the GUI application.");

    //Saving data, before closing.
    if(getActiveModel().save())
    {
      System.out.println("Data was saved successfully on user exit.");
      exit();
    }
    else
    {
      System.out.println("Error occurred while saving. Unable to save.");
      setGUI_ConsoleMessage("Unknown error occurred while saving. Unable to save. Please backup any critical information before manually terminating.");
    }
  }

  /**
   * This is a common method shared between all GUI scenes. This method saves all system data and then exports
   * progress reports on all non-confidential projects to a webpage compatible file that can be readily loaded
   * on the company's webpage.
   */
  public void exportToWeb()
  {
    System.out.println("User pressed the 'Export' button. System will now save all data to binary file, and then export this data to a webpage compatible file!");
    String promptMessage = "All ongoing and finished projects will be exported as .json files."
        + "\nPlease refer to the 'Project Data Files' folder once export has been successful to move them into your webpage directory."
        + "\n\nPlease confirm that you wish to proceed!";

    if(createPromptWindow(promptMessage).equals("confirmationPressed"))
    {
      //Execute the export method.
      this.getActiveModel().exportAsJson();

      //Update the GUI console:
      setGUI_ConsoleMessage(this.getActiveModel().getInitializationErrorMessage());
      System.out.println(this.getActiveModel().getInitializationErrorMessage());
    }
    else
    {
      setGUI_ConsoleMessage("User aborted export sequence. Nothing was exported.");
      System.out.println("User aborted export sequence. Nothing was exported.");
    }
  }

  /**
   * This method is used on GUI buttons as an "On Action" call and is used to navigate between different scenes/pages in the GUI.
   * It takes the text-label from the button and checks in an if/else manner which GUI scene corresponds to that button.
   * If changes are made in the GUI labels, but not updated in this method, the application WILL break.
   * Author: K. Dashnaw
   */
  public void openWindow(String buttonText, TextField GUI_Console) throws IOException
  {
    //Guard against any unexpected errors. Since this method should ONLY be called on buttons using set action any exceptions that might arise are due to programing flaws.
    try
    {
      if (buttonText.equalsIgnoreCase("Dashboard"))
      {
        this.loadNewWindow("Dashboard");
      }
      else if (buttonText.equalsIgnoreCase("Projects") || buttonText.equalsIgnoreCase("Cancel"))
      {
        this.loadNewWindow("Projects_MainView");
      }
      else if (buttonText.equalsIgnoreCase("Filter results"))
      {
        this.loadNewWindow("Projects_FilterView");
      }
      else if (buttonText.equalsIgnoreCase("New project"))
      {
        this.loadNewWindow("Projects_CreateProjectView");
      }
      else if (buttonText.equalsIgnoreCase("Settings"))
      {
        this.loadNewWindow("Projects_SettingsView");
      }
      else if (buttonText.equalsIgnoreCase("Edit"))
      {
        this.loadNewWindow("Projects_EditProjectView");
      }
      else if (buttonText.equalsIgnoreCase("View Details") || buttonText.equalsIgnoreCase("View project details"))
      {
        this.loadNewWindow("Projects_DetailsView");
      }
    }
    catch (Exception error)
    {
      GUI_Console.setText(
          "Unexpected error occurred while attempting to load stage. Code: " + error);
      System.out.println("Unexpected error occurred while attempting to load stage. Code: " + error);
    }
  }

  /**<p>This method is called from the various Scene Controllers towards this overall controller.
   * It creates a simple confirmation window inside the active window area and returns which button the user selected to press.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param message A String containing the message to display inside the confirmation window.
   * @return "cancelPressed" - This String value is returned if the user pressed the cancel button.<br>
   * "confirmationPressed" - This String value is returned if the user pressed the cancel button.
   */
  public String createPromptWindow(String message)
  {
    //Get the x/y position of the active window:
    double x = this.getActiveStage().getX();
    double y = this.getActiveStage().getY();

    //Create a confirmation alert
    Alert a = new Alert(Alert.AlertType.CONFIRMATION);

    //Set the alert text
    a.setContentText(message);

    //Set the x/y position of the window to be close to the calling button. This avoids the confirmation popping up on another screen, if more than 1 screen is connected.
    a.setX(x + this.getActiveStage().getWidth() / 2 - 180);
    a.setY(y + this.getActiveStage().getHeight() / 2 - 130);

    // show the dialog
    Optional<ButtonType> result = a.showAndWait();
    a.setResizable(false);

    //Get what button was pushed. Null if user exited without pressing a button
    if (result.isEmpty() || result.get() == ButtonType.CANCEL)
    {
      // alert is exited, no button has been pressed OR the cancel button was pressed.
      return "cancelPressed";
    }
    else
    {
      //OK button is pressed
      return "confirmationPressed";
    }
  }

  /** Returns FALSE if TextField is empty and TRUE is they are not.
   * Input validation method called from the local scene control model, which gets a call from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.
   * This method MUST be run on a TextField in order to avoid potential crashes/errors.
   * Author: K. Dashnaw
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
    return true;
  }

  /** Returns FALSE if TextField is either empty OR a number/digit, and TRUE is TextField is none of both.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.
   * This method MUST be run on a TextField in order to avoid potential crashes/errors.
   * Author: K. Dashnaw
   */
  public boolean validate_NotEmpty_NotNumber(KeyEvent keyNode, String errorMessage)
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
        //If the below parseTest throws an exception, the string is not a number.
        Double.parseDouble(userText);

        //Field is a number. Show a tooltip!
        addErrorTooltip(keyNode, "-fx-text-fill: red;","Field cannot be a number.");

        //Update console with an error:
        setGUI_ConsoleMessage(errorMessage);
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
      }
      return true;
    }
  }

  /** Returns FALSE if TextField is either empty OR a string OR a negative number/digit, and TRUE is TextField is none of either.
   * Input validation method called directly from the .fxml scene upon interacting with a
   * TextField with this method set as an "On Key Typed" event.
   * This method MUST be run on a TextField in order to avoid potential crashes/errors.
   * Author: K. Dashnaw
   */
  public boolean validate_NotEmpty_NotString_NotNegative(KeyEvent keyNode, String errorMessage)
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
        return true;
      }
      catch (NumberFormatException error)
      {
        //Field is a number. Show a tooltip!
        addErrorTooltip(keyNode, "-fx-text-fill: red;","Field must be a positive number");

        //Update console with an error:
        setGUI_ConsoleMessage(errorMessage);
        return false;
      }
    }
  }

  /** This code is run locally in this class. It simply checks if the given TextField contains any data or not.
   * Takes a KeyEvent and parses this as a TextField.
   * Warning: KeyEvent source must be a TextField, otherwise crashes may occur.
   * Author: K. Dashnaw
   * */
  public void emptyTextFieldCode(KeyEvent keyNode)
  {
    TextField text = (TextField) keyNode.getSource();

    //Add a Show a tooltip!
    addErrorTooltip(keyNode, "-fx-text-fill: red;", "Field cannot be empty.");
    text.setStyle("-fx-prompt-text-fill: red;");

    //Update console with an error:
    setGUI_ConsoleMessage("Error in data values while creating new project. Please review and correct!");
  }

  /** This code is run locally in this class. It adds a ToolTip to the received node.
   * Takes a KeyEvent and parses this as a TextField.
   * Warning: KeyEvent source must be a TextField, otherwise crashes may occur.
   * Parameters are:
   * "KeyEvent keyNode": A reference to the node belonging to the KeyEvent that triggered this method.
   * "String textStyle": A CSS style format used to i.e. color the text in the TextField (or similar).
   * "String toolTipMessage": The text that should be displayed in the tooltip.
   * Author: K. Dashnaw
   * */
  public void addErrorTooltip(KeyEvent keyNode, String textStyle, String toolTipMessage)
  {
    TextField text = (TextField) keyNode.getSource();
    text.setStyle(textStyle);

    Tooltip tooltip = new Tooltip();
    tooltip.setText(toolTipMessage);
    tooltip.setShowDelay(Duration.seconds(0));
    text.setTooltip(tooltip);
  }

  /** This code is run locally in this class. It adds a ToolTip to the received node.
   * Takes a DatePicker
   * Parameters are:
   * "DatePicker node": A reference to the node belonging to the DatePicker that triggered this method.
   * "String textStyle": A CSS style format used to i.e. color the text in the TextField (or similar).
   * "String toolTipMessage": The text that should be displayed in the tooltip.
   * Author: K. Dashnaw
   * */
  public void addErrorTooltip(DatePicker node, String textStyle, String toolTipMessage)
  {
    node.getEditor().setStyle(textStyle);

    Tooltip tooltip = new Tooltip();
    tooltip.setText(toolTipMessage);
    tooltip.setShowDelay(Duration.seconds(0));
    node.setTooltip(tooltip);
  }

  /** <p>This method returns the selected projects Data Field values that correspond with the shared project data fields.
   * This is intended for insertion into the on-screen editable textFields, so that already existing data is pre-entered for the user.
   * This method is especially used on: The Project Details view page and _________________________________</p>
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

  /** <p>Is called from "On Action" EventHandlers in the .fxml scene
   * Method adds the received ActionEvent node to the project data.
   * It receives a "ActionEvent node" parses this as a "CheckBox" and checks if it is selected or not.<br>
   * <b>Warning: ActionEvent node must have a source type of CheckBox, else errors will occur.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * */
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
    value.setPromptText(value.getText());
  }

  /** <p>This method returns the selected projects Data Field values that correspond with the shared project data fields.
   * This is intended for insertion into the on-screen clickable CheckBoxes, so that already existing data is pre-selected for the user.</p>
   * <p><b>Author:</b> K. Dashnaw </p>
   * @param node is a reference to the CheckBox node in which the returned boolean value shall be inserted.
   * @return A boolean value intended to be inserted into the above CheckBox node.
   * */
  public boolean loadProjectData_Checkbox (CheckBox node)
  {
    switch (node.getText())
    {
      case "Mark project as completed":
        return this.getActiveModel().getSelectedProject().isProjectFinished();
      case "Mark project as confidential":
        return this.getActiveModel().getSelectedProject().isProjectConfidential();
      case "Track on Dashboard":
        return this.getActiveModel().getSelectedProject().isDashboardProject();
      default:
        break;
    }
    return false;
  }

  /** <p>Method used to display the unique data fields specific to the selected project type!</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void showUniqueProjectDataFields(GridPane gridResidentialUniqueData, GridPane gridCommercialUniqueData, GridPane gridIndustrialUniqueData, GridPane gridRoadUniqueData)
  {
    hideAllUniqueProjectDataFields(true, gridResidentialUniqueData, gridCommercialUniqueData, gridIndustrialUniqueData, gridRoadUniqueData);

    switch (this.getActiveModel().getSelectedProject().getProjectType())
    {
      case "Residential":
        hideGridElementNode(gridResidentialUniqueData, false);
        break;
      case "Commercial":
        hideGridElementNode(gridCommercialUniqueData, false);
        break;
      case "Industrial":
        hideGridElementNode(gridIndustrialUniqueData, false);
        break;
      case "Road":
        hideGridElementNode(gridRoadUniqueData, false);
        break;
    }
  }

  /** <p>Method used to hide all unique project data fields.
   * It is used in conjunction with a show method for the specific data fields, so that only relevant data fields are displayed.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   */
  public void hideAllUniqueProjectDataFields(boolean bool, GridPane gridResidentialUniqueData, GridPane gridCommercialUniqueData, GridPane gridIndustrialUniqueData, GridPane gridRoadUniqueData)
  {
    hideGridElementNode(gridResidentialUniqueData, bool);
    hideGridElementNode(gridCommercialUniqueData, bool);
    hideGridElementNode(gridIndustrialUniqueData, bool);
    hideGridElementNode(gridRoadUniqueData, bool);
  }

  /** <p>Method used to hide a GridPane element.</p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param gridID A reference to the GridPane element to hide.
   * @param bool if true, hide the element - if false, show it.
   */
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
   */
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
   */
  public void hideLabelElementNode(Label labelID, boolean bool)
  {
    labelID.setVisible(!bool);
    labelID.managedProperty().bind(labelID.visibleProperty());
  }

  public boolean validateActiveProject(DatePicker date_EndDateField)
  {
    ConstructionProject activeProject = this.getActiveModel().getSelectedProject();

    //Check Shared Data First:
    //Customer: Attributes checked are; first name, last name, phone number and email.
    if(activeProject.getCustomer().getFirstName().isBlank() || activeProject.getCustomer().getLastName().isBlank() || activeProject.getCustomer().getPhoneNumber() == 0 || activeProject.getCustomer().getEmail().isBlank())
    {
      return true;
    }
    //Customer Address: Attributes checked are; Street name, Street number, City, Country and Postal Code
    else if(activeProject.getCustomer().getCustomerAddress().getStreet().isBlank() || activeProject.getCustomer().getCustomerAddress().getStreetNumber().isBlank() || activeProject.getCustomer().getCustomerAddress().getCity().isBlank() || activeProject.getCustomer().getCustomerAddress().getCountry().isBlank() || activeProject.getCustomer().getCustomerAddress().getPostalCode() == 0)
    {
      return true;
    }
    //Customer company: IS NOT CHECKED! Residential projects might not have companies as customers.
    //Project Address: Attributes checked are; Street name, Street number, City, Country and Postal Code
    else if(activeProject.getProjectAddress().getStreet().isBlank() || activeProject.getProjectAddress().getStreetNumber().isBlank() || activeProject.getProjectAddress().getCity().isBlank() || activeProject.getProjectAddress().getCountry().isBlank() || activeProject.getProjectAddress().getPostalCode() == 0)
    {
      return true;
    }
    //Promotional Information: Attributes checked are; Project Description & Project Name
    //TODO: Implement the photo URL functionality.
    else if(activeProject.getProjectInformation().getProjectName().isBlank() || activeProject.getProjectInformation().getProjectDescription().isBlank())
    {
      return true;
    }
    //Human Resources: Attributes checked are; Human Resources.
    else if(activeProject.getHumanRessources().getTotalManHoursNeeded() == 0)
    {
      return true;
    }
    //Finances: Attributes checked are; Total budget.
    else if(activeProject.getFinances().getTotalBudget() == 0)
    {
      return true;
    }
    //Start and End Dates: If user did not select a start date we know the project is preloaded with todays' date!
    else if(activeProject.getProjectStartDate() == null || activeProject.getProjectEndDate() == null || date_EndDateField.getEditor().getText().isBlank())
    {
      return true;
    }
    //Now check project specific data fields:
    else if(activeProject instanceof ResidentialProject project)
    {
      //Perform some more input validation on the remaining values, since these might not have been evaluated if the user has set illegal default values in the settings view.
      if(!(Integer.parseInt("" + project.getNumberOfBathrooms()) >= 0 && Integer.parseInt("" + project.getNumberOfBathrooms()) < Integer.MAX_VALUE))
      {
        //NumberOfBathrooms is not a legal value.
        setGUI_ConsoleMessage("Number Of Bathrooms has an illegal value!");
        return true;
      }
      else if(!(Integer.parseInt("" + project.getNumberOfKitchens()) >= 0 && Integer.parseInt("" + project.getNumberOfKitchens()) < Integer.MAX_VALUE))
      {
        //NumberOfKitchens is not a legal value.
        setGUI_ConsoleMessage("Number Of Bathrooms has an illegal value!");
        return true;
      }
      else if(!(Double.parseDouble("" + project.getBuildingSize()) > 0 && Double.parseDouble("" + project.getBuildingSize()) < Integer.MAX_VALUE))
      {
        //Building Size is not a legal value.
        setGUI_ConsoleMessage("Building size has an illegal value!");
        return true;
      }
      else if(!(Integer.parseInt("" + project.getNumberOfOtherRoomsWithPlumbing()) >= 0 && Integer.parseInt("" + project.getNumberOfOtherRoomsWithPlumbing()) < Integer.MAX_VALUE))
      {
        //Other rooms with plumbing number is not a legal value.
        setGUI_ConsoleMessage("Number of other rooms with plumbing size has an illegal value!");
        return true;
      }
    }
    else if(activeProject instanceof CommercialProject project)
    {
      //Perform some more input validation on the remaining values, since these might not have been evaluated if the user has set illegal default values in the settings view.
      if(!(Integer.parseInt("" + project.getNumberOfFloors()) >= 0 && Integer.parseInt("" + project.getNumberOfFloors()) < Integer.MAX_VALUE))
      {
        //NumberOfFloors is not a legal value.
        setGUI_ConsoleMessage("Number Of Floors has an illegal value!");
        return true;
      }
      else if(!(Double.parseDouble("" + project.getBuildingSize()) > 0 && Double.parseDouble("" + project.getBuildingSize()) < Double.MAX_VALUE))
      {
        //Building Size is not a legal value.
        setGUI_ConsoleMessage("Building size has an illegal value!");
        return true;
      }
    }
    else if(activeProject instanceof IndustrialProject project)
    {
      //Check Industrial fields with no default values defined. These being; Facility size
      if(!(Double.parseDouble("" + project.getFacilitySize()) > 0 && Double.parseDouble("" + project.getFacilitySize()) < Integer.MAX_VALUE))
      {
        //Building Size is not a legal value.
        setGUI_ConsoleMessage("Facility size has an illegal value!");
        return true;
      }
    }
    else if(activeProject instanceof RoadProject project)
    {
      //Perform some more input validation on the remaining values, since these might not have been evaluated if the user has set illegal default values in the settings view.
      if(!(Double.parseDouble("" + project.getRoadLength()) >= 0 && Double.parseDouble("" + project.getRoadLength()) < Integer.MAX_VALUE))
      {
        //Road length is not a legal value.
        setGUI_ConsoleMessage("Road length has an illegal value!");
        return true;
      }
      else if(!(Double.parseDouble("" + project.getRoadWidth()) >= 0 && Double.parseDouble("" + project.getRoadWidth()) < Integer.MAX_VALUE))
      {
        //Road width is not a legal value.
        setGUI_ConsoleMessage("Road width has an illegal value!");
        return true;
      }
      else if(project.getBridgeOrTunnelDetails().isBlank())
      {
        //Any bridges or tunnels is not a legal value.
        setGUI_ConsoleMessage("Any bridges or tunnels has an illegal value!");
        return true;
      }
      else if(project.getEnvironmentalOrGeographicalChallenges().isBlank())
      {
        //Any environmental or geographical comments is not a legal value.
        setGUI_ConsoleMessage("Any environmental or geological field has an illegal value!");
        return true;
      }
    }
    return false;
  }


  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Warning: Switch cases are based on the promptText's associated with the given TextField element.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */

  public void setTemporaryRoadData(RoadProject project, TextField text)
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
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.setProjectDuration(Integer.parseInt(text.getText().trim()));
        break;
      case "length in meters":
        project.setRoadLength(Double.parseDouble(text.getText().trim()));
        break;
      case "width in meters":
        project.setRoadWidth(Double.parseDouble(text.getText().trim()));
        break;
      default:
        break;
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Warning: Switch cases are based on the promptText's associated with the given TextField element.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */

  public void setTemporaryIndustrialData(IndustrialProject project, TextField text)
  {
    switch (text.getPromptText())
    {
      case "Describe the intended use of the facility":
        project.setFacilityType(text.getText());
        break;
      case "Duration in months":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.setProjectDuration(Integer.parseInt(text.getText().trim()));
        break;
      case "in m^2":
        project.setFacilitySize(Double.parseDouble(text.getText().trim()));
        break;
      default:
        break;
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Warning: Switch cases are based on the promptText's associated with the given TextField element.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */

  public void setTemporaryCommercialData(CommercialProject project, TextField text)
  {
    switch (text.getPromptText())
    {
      case "Number of floors":
        project.setNumberOfFloors(Integer.parseInt(text.getText().trim()));
        break;
      case "Describe the intended use of the building":
        project.setIntendedBuildingUse(text.getText());
        break;
      case "Duration in months":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.setProjectDuration(Integer.parseInt(text.getText().trim()));
        break;
      case "in m^2":
        project.setBuildingSize(Double.parseDouble(text.getText().trim()));
        break;
      default:
        break;
    }
  }

  /** <p>This method is used in conjunction with the "addCommonProjectData(TextField text) method".
   * It checks if the received data falls within the shared project data fields, and if so adds the data to the active project.</p>
   * <p><b>Warning: Switch cases are based on the promptText's associated with the given TextField element.</b></p>
   * <p><b>Author:</b> K. Dashnaw</p>
   * @param project This is a reference to the specific project type Class.
   * @param text This is a reference to the node containing the information to add to the project.
   * */

  public void setTemporaryResidentialData(ResidentialProject project, TextField text)
  {
    switch (text.getPromptText())
    {
      case "Number of Bathrooms":
        project.setNumberOfBathrooms(Integer.parseInt(text.getText().trim()));
        break;
      case "Number of Kitchens":
        project.setNumberOfKitchens(Integer.parseInt(text.getText().trim()));
        break;
      case "Other plumbing?":
        project.setNumberOfOtherRoomsWithPlumbing(Integer.parseInt(text.getText().trim()));
        break;
      case "Duration in months":
        //TODO: Implement check with standard margin ranges to see if budget is within.
        project.setProjectDuration(Integer.parseInt(text.getText().trim()));
        break;
      case "in m^2":
        project.setBuildingSize(Double.parseDouble(text.getText().trim()));
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
