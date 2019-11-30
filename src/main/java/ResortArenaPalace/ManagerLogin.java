package ResortArenaPalace;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManagerLogin {

  @FXML private Label lbl_ManagerLog;

  @FXML private Label lbl_MUName;

  @FXML private TextField txtField_MUName;

  @FXML private Label lbl_MUPassword;

  @FXML private PasswordField passfld_MUPassword;

  @FXML private Button btn_ManagerSignIn;

  @FXML private Button btn_GbackManTOHome;

  @FXML private Label lbl_UserNaVal;

  @FXML private Label lbl_PasswordVal;

  /**
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

  /**
   * *
   *
   * @param event Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeManToReport(ActionEvent event) throws IOException {
    if (txtField_MUName.getText().trim().isEmpty()
        && passfld_MUPassword.getText().trim().isEmpty()) {
      lbl_UserNaVal.setText("User Name Required");
      lbl_PasswordVal.setText("Password Required");
    } else if (passfld_MUPassword.getText().trim().isEmpty()) {
      lbl_PasswordVal.setText("Password Required");
    } else {
      if (userLogIn()) {
        System.out.println("Changing Scene");
        Parent manReportParent = FXMLLoader.load(getClass().getResource("ManReport.fxml"));
        Scene mReportScene = new Scene(manReportParent);

        Stage mRepWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mRepWindow.setScene(mReportScene);
        mRepWindow.show();
      } else {
        System.out.println("Not Changing Scene");
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText("This Username/Password is incorrect.");
        alert.setContentText(null);
        Optional<ButtonType> action = alert.showAndWait();
      }
    }
  }

  @FXML
  boolean userLogIn() {
    System.out.println("Log in pressed");

    System.out.println("Attempting to login");
    System.out.println(txtField_MUName.getText());
    System.out.println(passfld_MUPassword.getText());
    if (txtField_MUName.getText().equals("admin")
        && passfld_MUPassword.getText().equals("password")) {
      System.out.println("Login succesful!");
      return true;

    } else {
      System.out.println("Wrong username/password!");
      return false;
    }
  }
}
