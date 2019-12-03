package ResortArenaPalace;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManEventReport {

  @FXML private TableView<EventReservation> tablev_EventReport;
  @FXML private TableColumn<EventReservation, String> col_EvEmail;
  @FXML private TableColumn<EventReservation, String> col_Name;
  /*@FXML
  private TableColumn<EventReservation, String> col_FullName; */
  @FXML private TableColumn<EventReservation, String> col_EventType;
  @FXML private TableColumn<EventReservation, String> col_EvDate;
  @FXML private TableColumn<EventReservation, Integer> col_EvNPeople;
  @FXML private TableColumn<EventReservation, String> col_EvVenue;
  @FXML private TableColumn<EventReservation, Boolean> col_EvCatering;
  @FXML private TableColumn<EventReservation, Boolean> col_DJ;
  @FXML private TableColumn<EventReservation, Boolean> col_PartyPlanner;
  @FXML private TableColumn<EventReservation, String> col_EvPassword;
  @FXML private TextField txt_Name;
  @FXML private TextField txt_PartyPlanner;
  @FXML private TextField txt_EvType;
  @FXML private TextField txt_EvDate;
  @FXML private TextField txt_EvPeople;
  @FXML private TextField txt_Venue;
  @FXML private TextField txt_EvCatering;
  @FXML private TextField txt_DJ;
  @FXML private TextField txt_Email;
  @FXML private TextField txt_Password;
  @FXML private Label lbl_TitleReport;
  @FXML private Button btn_CancelEvent;
  @FXML private Button btn_SoldOut;
  @FXML private Button btn_BackEVRepToGuestRep;
  @FXML private Button btn_AddEvent;
  @FXML private Button btn_LogOut;
  @FXML private ComboBox<String> cbox_EventType;
  @FXML private ComboBox<String> cbox_Venue;
  @FXML private ComboBox<String> cbox_Catering;
  @FXML private ComboBox<String> cbox_Dj;
  @FXML private ComboBox<String> cbox_PartyPlanner;

  private ObservableList<String> eventType =
      FXCollections.observableArrayList("Wedding", "Conference", "Celebration", "Sweet 16");

  private ObservableList<String> venuePick =
      FXCollections.observableArrayList("Ballroom", "Conf. R.", "Beach", "Restaurant", "Club");

  private ObservableList<String> yesNo = FXCollections.observableArrayList("YES", "NO");

  private ObservableList<EventReservation> eventReport =
      FXCollections.observableArrayList(); // Table view related

  @FXML
  void changeEvRepToChart(ActionEvent event) throws IOException {
    Parent eventReportParent = FXMLLoader.load(getClass().getResource("EventChart.fxml"));
    Scene evRepScene = new Scene(eventReportParent);

    Stage eventChartWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    eventChartWindow.setScene(evRepScene);
    eventChartWindow.show();
  }

  /**
   * *
   *
   * @param event Action that creates a new event reservation and adds it to the report
   */
  @FXML
  void addEvent(ActionEvent event) {
    cbox_Catering.setEditable(false);
    cbox_Dj.setEditable(false);
    cbox_PartyPlanner.setEditable(false);
    if (txt_Email.getText().trim().isEmpty() || txt_EvDate.getText().trim().isEmpty()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("You must fill all the fields");
      alert.setContentText(null);
      Optional<ButtonType> action = alert.showAndWait();
    } else {
      String e_Email = txt_Email.getText();
      String e_FullName = txt_Name.getText();
      // String e_FullName = txt_Fname.getText();
      String e_Type = cbox_EventType.getValue();
      String e_Password = txt_Password.getText();
      String e_Date = String.valueOf(txt_EvDate.getText());
      int e_People = Integer.parseInt(txt_EvPeople.getText());
      String e_Venue = cbox_Venue.getValue();
      Boolean e_Catering = null;
      if (cbox_Catering.getValue().equals("YES")) {
        e_Catering = true;
      } else if (cbox_Catering.getValue().equals("NO") || cbox_Catering.getValue().equals("NO ")) {
        e_Catering = false;
      }
      Boolean e_DJ = null;
      if (cbox_Dj.getValue().equals("YES")) {
        e_DJ = true;
      } else if (cbox_Dj.getValue().equals("NO") || cbox_Dj.getValue().equals("NO ")) {
        e_DJ = false;
      }
      Boolean e_PartyPlanner = null;
      if (cbox_PartyPlanner.getValue().equals("YES")) {
        e_PartyPlanner = true;
      } else if (cbox_PartyPlanner.getValue().equals("NO")
          || cbox_PartyPlanner.getValue().equals("NO ")) {
        e_PartyPlanner = false;
      }

      EventReservation newEvent =
          new EventReservation(
              e_Email,
              e_FullName,
              e_Type,
              e_Password,
              e_Date,
              e_People,
              e_Venue,
              e_Catering,
              e_DJ,
              e_PartyPlanner);

      eventReport.add(newEvent);
      tablev_EventReport.setItems(eventReport);

      try {

        // Inserts the given values into the DataBase EVENTRESERVATION table
        System.out.println("Attempting to INSERT");
        String sql =
            "INSERT INTO EVENTRESERVATION(EMAIL,FNAME,EVENT,PASSWORD,EVENTDATE,NOPEOPLE,VENUE,CATERING,DJ,PARTYPLANNER)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql); // bugfound
        ps.setString(1, e_Email);
        ps.setString(2, e_FullName);
        ps.setString(3, e_Type);
        ps.setString(4, e_Password);
        ps.setString(5, e_Date);
        ps.setString(6, String.valueOf(e_People));
        ps.setString(7, e_Venue);
        ps.setBoolean(8, e_Catering);
        ps.setBoolean(9, e_DJ);
        ps.setBoolean(10, e_PartyPlanner);

        ps.executeUpdate(); // Updates the values in the EVENTRESERVATION table
        System.out.println("Inserted!");

        // STEP 4: Clean-up environment

      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        String sql = "SELECT * FROM EVENTRESERVATION";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          if (rs.last()) {
            eventList.add(
                new EventReservation(
                    rs.getString("EMAIL"),
                    rs.getString("FNAME"),
                    rs.getString("EVENT"),
                    rs.getString("PASSWORD"),
                    rs.getString("EVENTDATE"),
                    Integer.parseInt(rs.getString("NOPEOPLE")),
                    rs.getString("VENUE"),
                    rs.getBoolean("CATERING"),
                    rs.getBoolean("DJ"),
                    rs.getBoolean("PARTYPLANNER")));
          }
        }

      } catch (SQLException e) {
        e.printStackTrace();
      }

      tablev_EventReport.setItems(eventList);
    }
  }

  /** @param event Action that cancels the reservation of an event */
  @FXML
  void cancelEvent(ActionEvent event) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Cancel Event");
    alert.setHeaderText("Are you sure you want to CANCEL this Event?");
    alert.setContentText(null);
    Optional<ButtonType> action = alert.showAndWait();

    if (action.get() == ButtonType.OK) {
      try {
        System.out.println("Deleting Event Info");
        EventReservation eventReservation =
            tablev_EventReport.getSelectionModel().getSelectedItem();
        String selectedEvent = eventReservation.getEmail();
        String sql = "DELETE FROM EVENTRESERVATION WHERE EMAIL = " + "\'" + selectedEvent + "\';";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();
        System.out.println("Event Deleted!");

      } catch (SQLException e) {
        System.out.println("Could not delete event");
        e.printStackTrace();
      }
      tablev_EventReport
          .getItems()
          .removeAll(tablev_EventReport.getSelectionModel().getSelectedItem());
    }
  }

  /**
   * @param event Action that allows getting back to the home page
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeEvRepToHome(ActionEvent event) throws IOException {
    Parent evReportParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene evRepScene = new Scene(evReportParent);

    Stage eRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    eRWindow.setScene(evRepScene);
    eRWindow.show();
  }

  /**
   * @param event Action that allows going to guest report window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeEventRepToGuestReport(ActionEvent event) throws IOException {
    Parent eventReportParent = FXMLLoader.load(getClass().getResource("ManReport.fxml"));
    Scene eventRepScene = new Scene(eventReportParent);

    Stage homeRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    homeRWindow.setScene(eventRepScene);
    homeRWindow.show();
  }

  // Database handling

  private Connection conn = null;
  private Statement stmt = null;

  public void initialize() {
    cbox_EventType.setItems(eventType); // sets the items in the ComboBox
    cbox_EventType.setEditable(true); // Allows the user edit
    cbox_EventType.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    cbox_Venue.setItems(venuePick);
    cbox_Venue.setEditable(true);
    cbox_Venue.getSelectionModel().selectFirst();

    cbox_Catering.setItems(yesNo);
    cbox_Catering.setEditable(true);
    cbox_Catering.getSelectionModel();

    cbox_Dj.setItems(yesNo);
    cbox_Dj.setEditable(true);
    cbox_Dj.getSelectionModel();

    cbox_PartyPlanner.setItems(yesNo);
    cbox_PartyPlanner.setEditable(true);
    cbox_PartyPlanner.getSelectionModel();

    initializeDB();
    settingUpColumns();
    populateEventTableReport();
  }

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

  private void settingUpColumns() {
    col_EvEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    // col_Name.setCellValueFactory(new PropertyValueFactory<>("fName"));
    // col_FullName.setCellValueFactory(new PropertyValueFactory<>("fName"));
    col_EventType.setCellValueFactory(new PropertyValueFactory<>("event"));
    col_EvDate.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
    col_EvNPeople.setCellValueFactory(new PropertyValueFactory<>("noPeople"));
    col_EvVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
    col_EvCatering.setCellValueFactory(new PropertyValueFactory<>("catering"));
    col_DJ.setCellValueFactory(new PropertyValueFactory<>("dj"));
    col_PartyPlanner.setCellValueFactory(new PropertyValueFactory<>("partyPlanner"));
    col_EvPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
  }

  private ObservableList<EventReservation> eventList = FXCollections.observableArrayList();

  // Method that populates the Guest tableview with the data from the database

  private void populateEventTableReport() {

    try {
      String sql = "SELECT * FROM EVENTRESERVATION";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        eventList.add(
            new EventReservation(
                rs.getString("EMAIL"),
                rs.getString("FNAME"),
                rs.getString("EVENT"),
                rs.getString("PASSWORD"),
                rs.getString("EVENTDATE"),
                Integer.parseInt(rs.getString("NOPEOPLE")),
                (rs.getString("VENUE")),
                Boolean.parseBoolean(rs.getString("CATERING")),
                Boolean.parseBoolean(rs.getString("DJ")),
                Boolean.parseBoolean(rs.getString("PARTYPLANNER"))));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    tablev_EventReport.setItems(eventList);
  }
}
