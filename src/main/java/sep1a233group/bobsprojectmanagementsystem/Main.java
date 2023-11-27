package sep1a233group.bobsprojectmanagementsystem;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
  public static void main(String[] args)
  {
    launch();
  }

  @Override public void start(Stage activeStage) throws IOException
  {
    //Application name:
    activeStage.setTitle("Bob's Project Management System");

    //Scene controller:
    MainModel activeModel = new MainModel();
    SceneController sceneController = new SceneController(activeModel, activeStage);

    //Launch the GUI at the Dashboard Scene:
    sceneController.loadNewWindow("Dashboard");
  }
}
