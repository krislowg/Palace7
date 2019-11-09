package ResortArenaPalace;

/*Controller for the UserAccount.fxml file
Scene where the Guest and the user can fill their credentials to access their accounts*/

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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserAccount {

  @FXML
  private GridPane UsAccountPane;

  @FXML
  private Label lbl_UName;

  @FXML
  private Label lbl_Password;

  @FXML
  private PasswordField pField_Password;

  @FXML
  private TextField txtFld_UName;

  @FXML
  private Label lbl_UAccount;

  @FXML
  private Button btn_SignInUAccount;

  @FXML
  private Button btn_GoHomeFromUA;
  @FXML
  private Label lbl_UserNaVal;


  /***
   *
   * @param event Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeScreenUAccToReservat(ActionEvent event) throws IOException {
    if (txtFld_UName.getText().trim().isEmpty()) {
      lbl_UserNaVal.setText("User Name Required");
    } else {
      Parent guestAcParent = FXMLLoader.load(getClass().getResource("UserReservationDetails.fxml"));
      Scene gAccountScene = new Scene(guestAcParent);

      Stage gAccWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
      gAccWindow.setScene(gAccountScene);
      gAccWindow.show();
    }
  }

  /***
   *
   * @param event Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeScreenUAccountToHome(ActionEvent event) throws IOException {
    Parent uAccountParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene userAccScene = new Scene(uAccountParent);

    Stage uAccWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    uAccWindow.setScene(userAccScene);
    uAccWindow.show();
  }



}