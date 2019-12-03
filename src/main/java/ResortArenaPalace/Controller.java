package ResortArenaPalace;

import java.io.IOException;
import java.sql.*;
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

/**
 * Controller class for LandingPage.
 *
 * @file Controller.java
 * @author Shane Broxson,
 */
public class Controller {

  @FXML public Button eventReserveBtn;
  @FXML private DatePicker checkin_pick;
  @FXML private DatePicker checkout_pick;
  @FXML private ComboBox<String> noguests_pick;
  @FXML private ComboBox<String> room_pick;
  @FXML private ComboBox<String> cBox_Venue;
  @FXML private TextField txtF_Email;
  @FXML private Label check_avail_error;
  @FXML private TextField first_name;
  @FXML private TextField last_name;
  @FXML private PasswordField password;
  @FXML private DatePicker event_date;
  @FXML private TextField even_no_people;
  @FXML private CheckBox catering;
  @FXML private CheckBox dj;
  @FXML private CheckBox party_planner;
  @FXML private ChoiceBox<String> event_types;
  @FXML private Label rec_field2;
  @FXML private Label rec_field1;
  @FXML private Label rec_field3;
  @FXML private Label rec_field4;
  @FXML private Label rec_field5;
  @FXML private Label rec_field6;
  @FXML private Label rec_field7;
  @FXML private Label rec_field8;
  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/palace";
  private Connection conn = null;
  private Statement stmt = null;

  /**
   * *
   *
   * @param event Event that manages showing the confirmation message after making a reservation
   */
  @FXML
  void showConfirmation(ActionEvent event) throws SQLException {
    Boolean f1, f2, f3, f4, f5, f6, f7, f8 = false;
    if (first_name.getText().equals("") || first_name.getText() == null) {
      rec_field1.setVisible(true);
      f1 = false;
    } else {
      rec_field1.setVisible(false);
      f1 = true;
    }
    if (last_name.getText().equals("") || last_name.getText() == null) {
      rec_field2.setVisible(true);
      f2 = false;
    } else {
      rec_field2.setVisible(false);
      f2 = true;
    }
    if (txtF_Email.getText().equals("") || txtF_Email.getText() == null) {
      rec_field3.setVisible(true);
      f3 = false;
    } else {
      rec_field3.setVisible(false);
      f3 = true;
    }
    if (password.getText().equals("") || password.getText() == null) {
      rec_field4.setVisible(true);
      f4 = false;
    } else {
      rec_field4.setVisible(false);
      f4 = true;
    }
    if (event_date.getValue() == null) {
      rec_field5.setVisible(true);
      f5 = false;
    } else {
      rec_field5.setVisible(false);
      f5 = true;
    }
    if (even_no_people.getText().equals("")
        || even_no_people.getText() == null
        || even_no_people.getText().matches("[a-z A-Z]")) {
      rec_field6.setVisible(true);
      f6 = false;
    } else {
      rec_field6.setVisible(false);
      f6 = true;
    }
    if (cBox_Venue.getValue().equals("Choose Venue")) {
      rec_field7.setVisible(true);
      f7 = false;
    } else {
      rec_field7.setVisible(false);
      f7 = true;
    }
    if (event_types.getValue().equals("Choose Type")) {
      rec_field8.setVisible(true);
      f8 = false;
    } else {
      rec_field8.setVisible(false);
      f8 = true;
    }
    if (f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8) {
      String sql =
          "INSERT INTO EVENTRESERVATION (EMAIL, FNAME, EVENT, PASSWORD, EVENTDATE, NOPEOPLE, VENUE, CATERING, DJ, PARTYPLANNER) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ?)";
      try {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL);
        stmt = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, txtF_Email.getText());
        ps.setString(2, first_name.getText());
        ps.setString(3, event_types.getValue());
        ps.setString(4, password.getText());
        ps.setString(5, String.valueOf(event_date.getValue()));
        ps.setInt(6, Integer.parseInt(even_no_people.getText()));
        ps.setString(7, cBox_Venue.getValue());
        ps.setBoolean(8, catering.isSelected());
        ps.setBoolean(9, dj.isSelected());
        ps.setBoolean(10, party_planner.isSelected());
        ps.executeUpdate();
        ps.close();
        stmt.close();
        conn.close();
        EventReservation newEvent =
            new EventReservation(
                txtF_Email.getText(),
                first_name.getText(),
                event_types.getValue(),
                password.getText(),
                String.valueOf(event_date.getValue()),
                Integer.parseInt(even_no_people.getText()),
                cBox_Venue.getValue(),
                catering.isSelected(),
                dj.isSelected(),
                party_planner.isSelected());
        System.out.println(newEvent);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UserEventDetails.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        // access the controller and call a method
        UserEventDetails controller = loader.getController();
        controller.sendEvent(newEvent);

        // This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
        // open new scene, and send through newEvent to confirmation screen
      } catch (SQLException | ClassNotFoundException | IOException e) {
        e.printStackTrace();
      }
    }
  }

  // HOMEPAGE====================================================================================

  // Array list for the comboBox to pick the number of rooms
  private ObservableList<String> norooms =
      FXCollections.observableArrayList(
          "1 room",
          "2 rooms",
          "3 rooms",
          "4 rooms",
          "5 rooms",
          "6 rooms",
          "7 rooms",
          "8 rooms",
          "9 rooms",
          "10 rooms");

  // Array list for the comboBox to pick the number of guests
  private ObservableList<String> noguest =
      FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

  // Array List for the comoboBox to pick the type of Venue
  private ObservableList<String> venue =
      FXCollections.observableArrayList(
          "Choose Venue", "Conference", "Ballroom", "Club", "Beach", "Restaurant");

  private ObservableList<String> eventType =
      FXCollections.observableArrayList("Choose Type", "Wedding", "Celebration", "Conference");

  /* Method to populate the number of rooms and guests inside the combobox */
  public void initialize() {
    room_pick.setItems(norooms); // sets the items in the ComboBox
    room_pick.setEditable(true); // Allows the user edit
    room_pick.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    // Guests
    noguests_pick.setItems(noguest); // sets the items in the ComboBox
    noguests_pick.setEditable(true); // Allows the user edit
    noguests_pick.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    cBox_Venue.setItems(venue); // sets the items in the ComboBox
    cBox_Venue.setEditable(false); // Allows the user edit
    cBox_Venue.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    event_types.setItems(eventType);
    event_types.getSelectionModel().selectFirst();

    check_avail_error.setVisible(false);
    rec_field1.setVisible(false);
    rec_field2.setVisible(false);
    rec_field3.setVisible(false);
    rec_field4.setVisible(false);
    rec_field5.setVisible(false);
    rec_field6.setVisible(false);
    rec_field7.setVisible(false);
    rec_field8.setVisible(false);
  }

  /**
   * *
   *
   * @param event Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeHomeToManLog(ActionEvent event) throws IOException {
    Parent manLoginParent = FXMLLoader.load(getClass().getResource("ManagerLogin.fxml"));
    Scene managerScene = new Scene(manLoginParent);

    Stage mLWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    mLWindow.setScene(managerScene);
    mLWindow.show();
  }

  /*Method that changes the scene from "home scene" to "check availability scene" when the user
  presses the check availability button in the home scene */

  /**
   * *
   *
   * @param actionEvent Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  public void changeScreenRoomAv(ActionEvent actionEvent) throws IOException {
    if (noguests_pick.getValue() == null
        || room_pick.getValue() == null
        || checkin_pick.getValue() == null
        || room_pick.getValue() == null
        || !String.valueOf(noguests_pick.getValue()).matches("[^a-zA-Z]")
        || !String.valueOf(room_pick.getValue()).contains("room")) {
      check_avail_error.setVisible(true);
    } else {
      check_avail_error.setVisible(false);
      GuestReservation roomInfo =
          new GuestReservation(
              noguests_pick.getValue(),
              room_pick.getValue(),
              checkin_pick.getValue(),
              checkout_pick.getValue());
      FXMLLoader loader = new FXMLLoader();
      int i = checkin_pick.getValue().compareTo(checkout_pick.getValue());
      // System.out.println(i);
      if (i <= 0) {
        loader.setLocation(getClass().getResource("RoomAvailability.fxml"));
        Parent tableViewParent = loader.load();
        Scene roomAvScene = new Scene(tableViewParent);
        RoomAvailability controller = loader.getController();
        controller.sendText(roomInfo);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(roomAvScene);
        window.show();
      } else {
        System.out.println("End date cannot be before start date!");
      }
    }
  }

  /**
   * *
   *
   * @param actionEvent Event that indicates the move to another window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeScreenUserAccount(ActionEvent actionEvent) throws IOException {
    Parent userAccountParent = FXMLLoader.load(getClass().getResource("UserAccount.fxml"));
    Scene roomAvScene = new Scene(userAccountParent);

    Stage uAWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    uAWindow.setScene(roomAvScene);
    uAWindow.show();
  }
  // ==============================================================================================
}
