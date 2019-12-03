package ResortArenaPalace;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

// Payment and User Account

public class PaymentAccount {

  @FXML private Button btn_ReserveRoom;
  @FXML private TextField firstName;
  @FXML private TextField lastName;
  @FXML private TextField userEmail;
  @FXML private TextField userName;
  @FXML private PasswordField userPass;
  @FXML private ChoiceBox<String> cBox_CCType;
  @FXML private TextField ccNo;
  @FXML private TextField ccvCode;
  @FXML private TextField coupCode;
  @FXML private Button btn_GoBackRoomA;
  @FXML private Label lbl_RoomConf;
  @FXML private ChoiceBox<String> expMonth;
  @FXML private ChoiceBox<Integer> expYear;

  private ObservableList<String> cCType =
      FXCollections.observableArrayList("MasterCard", "Visa", "AmericanExpress", "Discovery");

  private ObservableList<String> expMon =
      FXCollections.observableArrayList(
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
          "November",
          "December");

  private static GuestReservation roomChoice;
  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/palace";

  /* Database connection */
  private Connection conn = null;
  private Statement stmt = null;

  public void initialize() {
    cBox_CCType.setItems(cCType); // sets the items in the credit card type comboBox
    cBox_CCType.getSelectionModel().selectFirst(); // Sets a default value in the comboBox

    expMonth.setItems(expMon); // sets the items in expiration month comboBox
    expMonth.getSelectionModel().selectFirst(); // Sets a default value in the comboBox

    Date date = new Date();
    int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
    int cutOffDate = year + 10;
    for (int i = year; i <= cutOffDate; i++) {
      expYear.getItems().add(i);
    }
    expYear.getSelectionModel().selectFirst(); // Sets a default value in the comboBox
  }

  public static Connection getConnection() {
    try {
      Class.forName(JDBC_DRIVER);
      Connection conn = DriverManager.getConnection(DB_URL, "", "");
      return conn;
    } catch (ClassNotFoundException | SQLException e) {
      throw new RuntimeException("Could not connect to database", e);
    }
  }

  /**
   * *
   *
   * @param roomChoices Object of the guest reservation type that stores room choice input
   */
  public void sendText2(GuestReservation roomChoices) {
    this.roomChoice = roomChoices;
    System.out.println(roomChoice);
  }

  /**
   * *
   *
   * @param event Event that indicates the move to room availability window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changePayToRoomA(ActionEvent event) throws IOException {
    Parent payParent = FXMLLoader.load(getClass().getResource("RoomAvailability.fxml"));
    Scene payScene = new Scene(payParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(payScene);
    payWindow.show();
  }

  /**
   * *
   *
   * @param event Event that allows showing a reservation confirmation
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void showConfirmation(ActionEvent event) throws IOException {
    String fName = firstName.getText();
    String lName = lastName.getText();
    String email = userEmail.getText();
    String username = userName.getText();
    String password = userPass.getText();
    String cardType = cBox_CCType.getValue();
    String cardNum = ccNo.getText();
    String experMonth = expMonth.getValue();
    Integer experYear = expYear.getValue();
    String cvv = ccvCode.getText();
    Character rmCh = roomChoice.getNoRooms().charAt(0);
    int roomchk = Character.getNumericValue(rmCh);

    System.out.println(this.roomChoice);

    Connection connection = getConnection();
    try {
      PreparedStatement ps =
          connection.prepareStatement(
              "INSERT INTO GUEST (EMAIL, NAME, LASTNAME, NOPEOPLE, NOROOMS, CHECKIN, CHECKOUT, ROOMTYPE,"
                  + " PASSWORD) VALUES ('"
                  + email
                  + "', '"
                  + fName
                  + "', '"
                  + lName
                  + "', '"
                  + Integer.parseInt(roomChoice.getNoPeople())
                  + "', '"
                  + roomchk
                  + "', '"
                  + roomChoice.getCheckIn().toString()
                  + "', '"
                  + roomChoice.getCheckOut().toString()
                  + "', '"
                  + roomChoice.getRoomType()
                  + "', '"
                  + password
                  + "')");
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    GuestReservation finalDataSet =
        new GuestReservation(
            email,
            fName,
            lName,
            Integer.parseInt(roomChoice.getNoPeople()),
            roomchk,
            roomChoice.getCheckIn().toString(),
            roomChoice.getCheckOut().toString(),
            roomChoice.getRoomType(),
            password);
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("ConfirmMessage.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    ConfirmMessage y = loader.getController();
    y.sendText3(finalDataSet);

    Parent payParent = FXMLLoader.load(getClass().getResource("ConfirmMessage.fxml"));
    Scene payScene = new Scene(payParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(payScene);
    payWindow.show();
  }
}
