package ResortArenaPalace;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserReservationDetails implements Initializable {

  @FXML private Label lbl_TitleReservation;

  @FXML private TextArea txtA_ReservationDet;

  @FXML private Button btn_CancelR;

  @FXML private Button btn_SignOut;

  @FXML private ImageView roomImage;

  @FXML private Label thankMessage;

  @FXML private Pane infoPane;

  @FXML private Pane roomPane;

  @FXML private Text rmText;

  @FXML private Text infoText;

  @FXML private Text cancelText;

  @FXML private Label firstName;

  @FXML private Label lastName;

  @FXML private Label email;

  @FXML private Label paymentType;

  @FXML private Label numberGuests;

  @FXML private Label numberRooms;

  @FXML private Label roomType;

  @FXML private Label checkInDate;

  @FXML private Label checkOutDate;

  public String myEmail;
  public String myName;
  public String myLName;
  public String myNoPeople;
  public String myNoRooms;
  public String myCheckIn;
  public String myCheckOut;
  public String myRoomType;
  public String myPassword;

  public static GuestReservation details;

  /**
   * *
   *
   * @param event Event that indicates the move to the home window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeResDetToHome(ActionEvent event) throws IOException {
    Parent resDetParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene ReservationScene = new Scene(resDetParent);

    Stage rDWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    rDWindow.setScene(ReservationScene);
    rDWindow.show();
  }

  @FXML
  public void initialize(URL url, ResourceBundle rb) {
    cancelText.setVisible(false);
    System.out.println("____________________" + myEmail);
    email.setText(myEmail);
  }

  public void setText(String email) {
    // this.email.setText(email);
    System.out.println("Email is " + email);
    myEmail = email;
    this.email.setText(myEmail);
    try {
      initializeDB();
      String sql = "SELECT * FROM guest WHERE email = " + "\'" + email + "\';";
      // ResultSet rs = null;
      PreparedStatement pstmt = conn.prepareStatement(sql);
      // sql = "SELECT * FROM guest WHERE email = " + "\'" + email + "\';";
      pstmt = conn.prepareStatement(sql);
      ResultSet rs2 = pstmt.executeQuery();
      while (rs2.next()) {
        myEmail = rs2.getString("EMAIL");
        myName = rs2.getString("NAME");
        myLName = rs2.getString("LASTNAME");
        myNoPeople = rs2.getString("NOPEOPLE");
        myNoRooms = rs2.getString("NOROOMS");
        myCheckIn = rs2.getString("CHECKIN");
        myCheckOut = rs2.getString("CHECKOUT");
        myRoomType = rs2.getString("ROOMTYPE");
        myPassword = rs2.getString("PASSWORD");
        System.out.println(
            myEmail
                + "\n"
                + myName
                + "\n"
                + myLName
                + "\n"
                + myNoPeople
                + "\n"
                + myNoRooms
                + "\n"
                + myCheckIn
                + "\n"
                + myCheckOut
                + "\n"
                + myRoomType
                + "\n"
                + myPassword);
      }
      firstName.setText(myName);
      lastName.setText(myLName);
      paymentType.setText(" Visa");
      numberGuests.setText(myNoPeople);
      numberRooms.setText(myNoRooms);
      roomType.setText(myRoomType);
      checkInDate.setText(myCheckIn);
      checkOutDate.setText(myCheckOut);

    } catch (SQLException e) {
      System.out.println("Could not login");
      e.printStackTrace();
    }
  }
  //    Integer.toString(details.getNum_p());
  //      numberGuests.setText(details.getNoPeople());
  //      numberRooms.setText(details.getNoRooms());

  // change expiration date to datepicker
  // pull info
  // check info
  // push to db
  // on room availability add sold out functionality
  // add cancel reservation to homepage "email and password pull"
  // work on this file (pull textbox info/setlabel for incorrect cases), landingpage (ending date
  // cannot be before start date (how to block out old dates on datepicker)/ if nothing selected
  // label.settext("must select ~~")), room availability on date, add cancel reservation on
  // landingpage.
  // edit management to add management and encrypt password

  /**
   * *
   *
   * @param event Event related to canceling a reservation
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void cancelReservation(ActionEvent event) throws IOException {
    System.out.println("Cancel Reservation Pressed");
    roomImage.setVisible(false);
    infoPane.setVisible(false);
    roomPane.setVisible(false);
    infoText.setVisible(false);
    rmText.setVisible(false);
    cancelText.setVisible(true);
  }

  public void sendText4(GuestReservation details) {
    this.details = details;
  }

  /*
  @FXML
  void cancelReservation(ActionEvent event) throws SQLException {
    System.out.println("Cancel Reservation Pressed");

    //ObservableList selectedItems = tablev_Report.getSelectionModel().getSelectedItems();
    //System.out.println(selectedItems);


    try {
      System.out.println("Deleting Guest/Reservation Info");
      Guest guest = tablev_Report.getSelectionModel().getSelectedItem();
      String selectedGuest = guest.getEmail();
      String sql = "DELETE FROM GUEST WHERE EMAIL = " + "\'" + selectedGuest + "\';";
      PreparedStatement pstmt = conn.prepareStatement(sql);
      pstmt.execute();
      System.out.println("Guest/Reservation Info Deleted!");

    } catch (SQLException e){
      System.out.println("Could not delete guest");
      e.printStackTrace();
    }
    tablev_Report.getItems().removeAll(tablev_Report.getSelectionModel().getSelectedItem());
  }*/

  // Database management
  private Connection conn = null;
  private Statement stmt = null;

  private void initializeDB() {
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
