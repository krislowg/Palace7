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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManEventReport {

  @FXML
  private TableView<EventReservation> tablev_EventReport;

  @FXML
  private TableColumn<EventReservation, String> col_EvEmail;

  @FXML
  private TableColumn<EventReservation, String> col_FullName;

  @FXML
  private TableColumn<EventReservation, String> col_EventType;

  @FXML
  private TableColumn<EventReservation, String> col_EvDate;

  @FXML
  private TableColumn<EventReservation, Integer> col_EvNPeople;

  @FXML
  private TableColumn<EventReservation, String> col_EvVenue;

  @FXML
  private TableColumn<EventReservation, String> col_EvCatering;

  @FXML
  private TableColumn<EventReservation, String> col_DJ;

  @FXML
  private TableColumn<EventReservation, String> col_PartyPlanner;

  @FXML
  private TableColumn<EventReservation, String> col_EvPassword;

  @FXML
  private TextField txt_PartyPlanner;

  @FXML
  private TextField txt_Fname;

  @FXML
  private TextField txt_EvType;

  @FXML
  private TextField txt_EvDate;

  @FXML
  private TextField txt_EvPeople;

  @FXML
  private TextField txt_Venue;

  @FXML
  private TextField txt_EvCatering;

  @FXML
  private TextField txt_DJ;

  @FXML
  private TextField txt_Email;

  @FXML
  private TextField txt_Password;

  @FXML
  private Label lbl_TitleReport;

  @FXML
  private Button btn_CancelEvent;

  @FXML
  private Button btn_SoldOut;

  @FXML
  private Button btn_BackEVRepToGuestRep;

  @FXML
  private Button btn_AddEvent;

  @FXML
  private Button btn_LogOut;


  private ObservableList<EventReservation> eventReport = FXCollections.observableArrayList();//Table view related

  /***
   *
   * @param event Action that creates a new event reservation and adds it to the report
   */
  @FXML
  void addEvent(ActionEvent event) {
    // Getting values from text field and combobox in Manager Event Report and storing them in a
    // variable
    String e_Email = txt_Email.getText();
    String e_FullName = txt_Fname.getText();
    String e_Type = txt_EvType.getText();
    String e_Date = txt_EvDate.getText();
    int e_People = Integer.parseInt(txt_EvPeople.getText());
    String e_Venue = txt_Venue.getText();
    String e_Catering = txt_EvCatering.getText();
    String e_DJ = txt_DJ.getText();
    String e_PartyPlanner = txt_PartyPlanner.getText();
    String e_Password = txt_Password.getText();

    EventReservation newEvent = new EventReservation(e_Email, e_FullName, e_Type, e_Date, e_People, e_Venue, e_Catering,
        e_DJ, e_PartyPlanner, e_Password);

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
      ps.setString(8, e_Catering);
      ps.setString(9, e_DJ);
      ps.setString(10, e_PartyPlanner);

      ps.executeUpdate(); // Updates the values in the EVENTRESERVATION table
      System.out.println("Inserted!");

      // STEP 4: Clean-up environment
      stmt.close(); // Closes the statements and the connections
      conn.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }


  /**
   *
   * @param event Action that cancels the reservation of an event
   */
  @FXML
  void cancelEvent(ActionEvent event) {
    Alert alert = new Alert (AlertType.CONFIRMATION);
    alert.setTitle("Cancel Event");
    alert.setHeaderText("Are you sure you want to CANCEL this Event?");
    alert.setContentText(null);
    Optional<ButtonType> action = alert.showAndWait();

    if(action.get() == ButtonType.OK) {
      tablev_EventReport.getItems().removeAll(tablev_EventReport.getSelectionModel().getSelectedItem());
    }
  }

  /**
   *
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
   *
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

  private void settingUpColumns(){
    col_EvEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    col_FullName.setCellValueFactory(new PropertyValueFactory<>("fName"));
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

  //Method that populates the Guest tableview with the data from the database

  private void populateEventTableReport(){

    try {
      String sql = "SELECT * FROM EVENTRESERVATION";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {

        eventList.add(new EventReservation(rs.getString("EMAIL"),rs.getString("FNAME"),
            rs.getString("EVENT"), rs.getString("EVENTDATE"), Integer.parseInt(rs.getString("NOPEOPLE")),
            (rs.getString("VENUE")), rs.getString("CATERING"),
            rs.getString("DJ"), rs.getString("PARTYPLANNER"),
            rs.getString("PASSWORD") ));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    tablev_EventReport.setItems(eventList);
  }


}
