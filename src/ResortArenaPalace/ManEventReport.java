package ResortArenaPalace;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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


  @FXML
  void addEvent(ActionEvent event) {
    EventReservation myEReport = new EventReservation((txt_Email.getText()), txt_Fname.getText(),
        txt_EvType.getText(), txt_EvDate.getText(), Integer.parseInt(txt_EvPeople.getText()), (txt_Venue.getText()),
        txt_EvCatering.getText(),txt_DJ.getText(), txt_PartyPlanner.getText(), txt_Password.getText());
        tablev_EventReport.getItems().add(myEReport);
  }

  @FXML
  void cancelEvent(ActionEvent event) {
    tablev_EventReport.getItems().removeAll(tablev_EventReport.getSelectionModel().getSelectedItem());
  }

  @FXML
  void changeEvRepToHome(ActionEvent event) throws IOException {
    Parent evReportParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene evRepScene = new Scene(evReportParent);

    Stage eRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    eRWindow.setScene(evRepScene);
    eRWindow.show();
  }

  @FXML
  void changeEventRepToGuestReport(ActionEvent event) throws IOException {
    Parent eventReportParent = FXMLLoader.load(getClass().getResource("ManReport.fxml"));
    Scene eventRepScene = new Scene(eventReportParent);

    Stage homeRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    homeRWindow.setScene(eventRepScene);
    homeRWindow.show();
  }

  private Connection conn = null;
  private Statement stmt = null;

  public void initialize() {
    initializeDB();
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

  ObservableList<EventReservation> eventList = FXCollections.observableArrayList();

  //Method that populates the Guest tableview with the data from the database
  public void populateEventTableReport(){
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
