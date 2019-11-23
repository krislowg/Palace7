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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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

  @FXML
  private Label lbl_PasswordVal;

  /**
   * @param event Action that handles the password entry
   */
  @FXML
  void passwordKeyRelease(KeyEvent event) {
    lbl_PasswordVal.setText(" ");

  }

  /***
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
          if(userLogIn()){
            System.out.println("Changing Scene");
            Parent guestAcParent = FXMLLoader.load(getClass().getResource("UserReservationDetails.fxml"));
            Scene gAccountScene = new Scene(guestAcParent);

            Stage gAccWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            gAccWindow.setScene(gAccountScene);
            gAccWindow.show();
          } else {
            System.out.println("Not Changing Scene");
          }



    }
  }

  /***
   *
   * @param event Event that indicates the move back to home window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeScreenUAccountToHome(ActionEvent event) throws IOException {
    userLogIn();
    Parent uAccountParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene userAccScene = new Scene(uAccountParent);

    Stage uAccWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    uAccWindow.setScene(userAccScene);
    uAccWindow.show();
  }




  public String myEmail;
  public String myName;
  public String myLName;
  public String myNoPeople;
  public String myNoRooms;
  public String myCheckIn;
  public String myCheckOut;
  public String myRoomType;
  public String myPassword;

  public String email;

  @FXML
  boolean userLogIn() {
    System.out.println("Log in pressed");
    String email = txtFld_UName.getText().toString();
    String password = pField_Password.getText().toString();

    try {
      initializeDB();
      String sql = "SELECT * FROM guest WHERE email = ? and password = ?";
      System.out.println("Attempting to login");
      ResultSet rs = null;
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, email);
      pstmt.setString(2, password);
      rs = pstmt.executeQuery();
      /**********************************************************
      sql = "SELECT * FROM guest WHERE email = " + "\'" + email + "\';";
      pstmt = conn.prepareStatement(sql);
      ResultSet rs2 = pstmt.executeQuery();
      while(rs2.next()){
        myEmail = rs2.getString("EMAIL");
        myName = rs2.getString("NAME");
        myLName = rs2.getString("LASTNAME");
        myNoPeople = rs2.getString("NOPEOPLE");
        myNoRooms = rs2.getString("NOROOMS");
        myCheckIn = rs2.getString("CHECKIN");
        myCheckOut = rs2.getString("CHECKOUT");
        myRoomType = rs2.getString("ROOMTYPE");
        myPassword = rs2.getString("PASSWORD");
        System.out.println(myEmail + "\n" + myName + "\n" + myLName + "\n" + myNoPeople + "\n" + myNoRooms + "\n" + myCheckIn + "\n" + myCheckOut + "\n" + myRoomType + "\n" + myPassword);
      }
********************************************************/
      if (!rs.next()) {
        System.out.println("Wrong email/password!");
        return false;
      } else{
        System.out.println("Login succesful!");
        return true;
      }
    } catch (SQLException e) {
      System.out.println("Could not login");
      e.printStackTrace();
    }
      return true;
  }


/*
  public void initialize(){
    initializeDB();
  }
*/
  // Database management
  private Connection conn = null;
  private Statement stmt = null;

  private void initializeDB(){
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/palace";
    final String USER = "";
    final String PASS = "";

    System.out.println("Attempting to connect to database");
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      stmt = conn.createStatement();
      System.out.println("Successfully connected to database!");
    } catch (Exception e) {
      e.printStackTrace();
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.show();

    }
  }

}