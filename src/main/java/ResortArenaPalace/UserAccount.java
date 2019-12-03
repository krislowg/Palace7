package ResortArenaPalace;

/* Controller for the UserAccount.fxml file
Scene where the Guest and the user can fill their credentials to access their accounts */

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Logger;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UserAccount {

  @FXML private GridPane UsAccountPane;
  @FXML private Label lbl_UName;
  @FXML private Label lbl_Password;
  @FXML private PasswordField pField_Password;
  @FXML private TextField txtFld_UName;
  @FXML private Label lbl_UAccount;
  @FXML private Button btn_SignInUAccount;
  @FXML private Button btn_GoHomeFromUA;
  @FXML private Label lbl_UserNaVal;
  @FXML private Label lbl_PasswordVal;
  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/palace";
  private Connection conn = null;
  private Statement stmt = null;

  /** @param event Action that handles the password entry */
  @FXML
  void passwordKeyRelease(KeyEvent event) {
    lbl_PasswordVal.setText(" ");
  }

  /**
   * *
   *
   * @param event Event that indicates the move to reservation window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeScreenUAccToReservat(ActionEvent event) throws IOException {

    if (txtFld_UName.getText().trim().isEmpty() && pField_Password.getText().trim().isEmpty()) {
      lbl_UserNaVal.setText("User Name Required");
      lbl_PasswordVal.setText("Password Required");
    } else if (pField_Password.getText().trim().isEmpty()) {
      lbl_PasswordVal.setText("Password Required");
    } else {
      try {
        Class.forName(JDBC_DRIVER);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      try {

        conn = DriverManager.getConnection(DB_URL);
        stmt = conn.createStatement();
        String sql = "SELECT * FROM guest WHERE email = ? and password = ?";
        System.out.println("Attempting to login");
        ResultSet rs = null;
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, txtFld_UName.getText());
        pstmt.setString(2, pField_Password.getText());
        rs = pstmt.executeQuery();

        if (!rs.next()) {
          System.out.println("Wrong email/password!");
          Alert alert = new Alert(AlertType.ERROR);
          alert.setTitle("Login Error");
          alert.setHeaderText("This Email/Password is incorrect.");
          alert.setContentText(null);
          Optional<ButtonType> action = alert.showAndWait();
          txtFld_UName.clear();
          pField_Password.clear();
        } else {
          System.out.println("Login succesful!");
          GuestReservation newReservation =
              new GuestReservation(
                  rs.getString("EMAIL"),
                  rs.getString("NAME"),
                  rs.getString("LASTNAME"),
                  Integer.valueOf(rs.getString("NOPEOPLE")),
                  Integer.valueOf(rs.getString("NOROOMS")),
                  rs.getString("CHECKIN"),
                  rs.getString("CHECKOUT"),
                  rs.getString("ROOMTYPE"),
                  rs.getString("PASSWORD"));

          FXMLLoader loader = new FXMLLoader();
          loader.setLocation(getClass().getResource("UserReservationDetails.fxml"));
          Parent tableViewParent = loader.load();

          Scene tableViewScene = new Scene(tableViewParent);

          // access the controller and call a method
          UserReservationDetails controller = loader.getController();
          controller.sendText4(newReservation);

          // This line gets the Stage information
          Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

          window.setScene(tableViewScene);
          window.show();
        }

        conn = DriverManager.getConnection(DB_URL);
        stmt = conn.createStatement();

      } catch (SQLException ex) {

        ex.printStackTrace();
      }
    }
  }

  /**
   * *
   *
   * @param event Event that indicates the move back to home window
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
