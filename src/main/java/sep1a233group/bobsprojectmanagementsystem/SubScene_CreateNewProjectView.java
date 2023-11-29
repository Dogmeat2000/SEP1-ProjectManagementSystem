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

import java.io.IOException;

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

  /** Initializes this scene into the active stage on the GUI - reusing the same window space.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  public void init(MainModel activeModel, SceneController sceneController)
  {
    this.activeModel = activeModel;
    this.sceneController = sceneController;
    setProjectTypeSelected(false);
    hideGridElementNode(gridProjectDataContainer, true);
    hideLabelElementNode(labelProjectDataPrompt, true);
    hideAllUniqueProjectDataFields(true);
    hideButtonElementNode(buttonCreateProject, true);
    hideButtonElementNode(buttonCancel, true);
    System.out.println("Create New Project panel is now active");
  }

  private void setProjectTypeSelected(boolean projectTypeSelected)
  {
    this.projectTypeSelected = projectTypeSelected;
  }

  private boolean isProjectTypeSelected()
  {
    return projectTypeSelected;
  }

  public String getProjectTypeSelectedString()
  {
    return projectTypeSelectedString;
  }

  public void setProjectTypeSelectedString(String projectTypeSelectedString)
  {
    this.projectTypeSelectedString = projectTypeSelectedString;
  }

  /** Used to refresh the onscreen view when navigating to this scene/page. It ensures that shown fields are updated with the proper data.
   * Implementation is inspired by Lector Michael's presentation (VIA University College, Horsens)
   * */
  @Override public void refresh()
  {
    //TODO: Genindlæs indholdet på siden. F.eks. hvis der skal stå noget specifikt tekst i en boks, osv.!

    System.out.println("Project Main View Scene is now the active stage.");
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

  public void ProjectTypeSelected(ActionEvent actionEvent)
  {
    String buttonText = ((Button)actionEvent.getSource()).getText();
    setProjectTypeSelected(true);
    setProjectTypeSelectedString(buttonText);

    //Display the common project data fields:
    hideGridElementNode(gridProjectDataContainer, false);
    hideLabelElementNode(labelProjectDataPrompt, false);

    //Display the unique data fields relating to the selected project type:
    showUniqueProjectDataFields(buttonText);

    //Display the buttons the user may interact with:
    hideButtonElementNode(buttonCreateProject, false);
    hideButtonElementNode(buttonCancel, false);

    //Hide the "Select Project Type" prompt and resize the project type pictures for more space on screen:
    labelProjectTypePrompt.setVisible(false);
    resizeAllElementHeight(80);
    resizeProjectButtonWidth(106);
    setNonSelectedProjectTypesTransparent(buttonText);
  }

  public void resizeAllElementHeight(int height)
  {
    //Resize the image elements:
    imgResidentialProjectType.setFitHeight(height-5);
    imgCommercialProjectType.setFitHeight(height-5);
    imgIndustrialProjectType.setFitHeight(height-5);
    imgRoadProjectType.setFitHeight(height-5);

    //Resize the button elements:
    resizeButtonHeight(buttonResidentialProjectType, height);
    resizeButtonHeight(buttonCommercialProjectType, height);
    resizeButtonHeight(buttonIndustrialProjectType, height);
    resizeButtonHeight(buttonRoadProjectType, height);

    //Resize the label elements:
    changeLabelTextSize(labelResidentialProjectType, 11);
    changeLabelTextSize(labelCommercialProjectType, 11);
    changeLabelTextSize(labelIndustrialProjectType, 11);
    changeLabelTextSize(labelRoadProjectType, 11);
    resizeLabelHeight(labelProjectTypePrompt, 0);

    //Resize the vBox elements:
    vBoxResidentialProjectType.setMaxHeight(height);
    vBoxCommercialProjectType.setMaxHeight(height);
    vBoxIndustrialProjectType.setMaxHeight(height);
    vBoxRoadProjectType.setMaxHeight(height);
  }

  public void resizeButtonHeight(Button button,int height)
  {
    button.setMaxHeight(height);
    button.setPrefHeight(height);
    button.setMinHeight(height);
  }

  public void resizeLabelHeight(Label label,int height)
  {
    label.setMinHeight(height);
    label.setPrefHeight(height);
    label.setMaxHeight(height);
  }

  public void resizeProjectButtonWidth(int width)
  {
    resizeButtonWidth(buttonResidentialProjectType, width);
    resizeButtonWidth(buttonCommercialProjectType, width);
    resizeButtonWidth(buttonIndustrialProjectType, width);
    resizeButtonWidth(buttonRoadProjectType, width);
  }

  public void resizeButtonWidth(Button buttonID,int width)
  {
    buttonID.setMinWidth(width);
    buttonID.setPrefWidth(width);
    buttonID.setMaxWidth(width);
  }

  public void changeLabelTextSize(Label labelName, int textSize)
  {
    labelName.setFont(Font.font("System", textSize));
  }

  public void setNonSelectedProjectTypesTransparent(String buttonText)
  {
    double discreetOpacityLevel = 0.3;
    double nonDiscreetOpacityLevel = 1;
    switch (buttonText)
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

  public void hideAllUniqueProjectDataFields(boolean bool)
  {
    hideGridElementNode(gridResidentialUniqueData, bool);
    hideGridElementNode(gridCommercialUniqueData, bool);
    hideGridElementNode(gridIndustrialUniqueData, bool);
    hideGridElementNode(gridRoadUniqueData, bool);
  }

  public void hideGridElementNode(GridPane gridID, boolean bool)
  {
    gridID.setVisible(!bool);
    gridID.managedProperty().bind(gridID.visibleProperty());
  }

  public void hideButtonElementNode(Button buttonID, boolean bool)
  {
    buttonID.setVisible(!bool);
    buttonID.managedProperty().bind(buttonID.visibleProperty());
  }

  public void hideLabelElementNode(Label labelID, boolean bool)
  {
    labelID.setVisible(!bool);
    labelID.managedProperty().bind(labelID.visibleProperty());
  }

  public void showUniqueProjectDataFields(String projectType)
  {
    hideAllUniqueProjectDataFields(true);

    switch (projectType)
    {
      case "ResidentialProjectType":
        gridResidentialUniqueData.setVisible(true);
        gridResidentialUniqueData.managedProperty().bind(gridResidentialUniqueData.visibleProperty());
        break;
      case "CommercialProjectType":
        gridCommercialUniqueData.setVisible(true);
        gridCommercialUniqueData.managedProperty().bind(gridCommercialUniqueData.visibleProperty());
        break;
      case "IndustrialProjectType":
        gridIndustrialUniqueData.setVisible(true);
        gridIndustrialUniqueData.managedProperty().bind(gridIndustrialUniqueData.visibleProperty());
        break;
      case "RoadProjectType":
        gridRoadUniqueData.setVisible(true);
        gridRoadUniqueData.managedProperty().bind(gridRoadUniqueData.visibleProperty());
        break;
    }
  }

  public void resetAllResizingToStandardValues()
  {
    resizeAllElementHeight(185);
  }

}