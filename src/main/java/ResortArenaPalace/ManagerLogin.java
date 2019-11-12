package ResortArenaPalace;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagerLogin {

  @FXML
  private Label lbl_ManagerLog;

  @FXML
  private Label lbl_MUName;

  @FXML
  private TextField txtField_MUName;

  @FXML
  private Label lbl_MUPassword;

  @FXML
  private PasswordField passfld_MUPassword;

  @FXML
  private Button btn_ManagerSignIn;

  @FXML
  private Button btn_GbackManTOHome;

  /**
   *
   * @param event Action that allows going back to the home page
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeManToHome(ActionEvent event) throws IOException {
    Parent manLoginParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene mLoginScene = new Scene(manLoginParent);

    Stage mLogWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    mLogWindow.setScene(mLoginScene);
    mLogWindow.show();
  }

  /***
   *
   * @param event Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeManToReport(ActionEvent event) throws IOException {
    Parent manReportParent = FXMLLoader.load(getClass().getResource("ManReport.fxml"));
    Scene mReportScene = new Scene(manReportParent);

    Stage mRepWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    mRepWindow.setScene(mReportScene);
    mRepWindow.show();
  }

}