package ResortArenaPalace;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;
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

public class ManReport {

  @FXML
  private TableView<Guest> tablev_Report;

  //@FXML private TableColumn<Guest, ?> col_Roomnum;

  @FXML
  private TableColumn<Guest, String> col_FirstName;

  @FXML
  private TableColumn<Guest, String> col_LastName;

  @FXML
  private TableColumn<Guest, Integer> col_NPeople;

  @FXML
  private TableColumn<Guest, Integer> col_Nrooms;

  @FXML
  private TableColumn<Guest, String> col_CheckIn;

  @FXML
  private TableColumn<Guest, String> col_CheckOut;

  @FXML
  private TableColumn<Guest, String> col_RType;

  @FXML
  private TableColumn<Guest, String> col_Email;

  @FXML
  private TableColumn<Guest, String> col_Password;

  @FXML
  private Label lbl_TitleReport;

  @FXML
  private Button btn_CancelR;

  @FXML
  private Button btn_SoldOut;

  @FXML
  private Button btn_BackRepToMan;

  @FXML
  private TextField txt_roomNumb;

  @FXML
  private TextField txt_Fname;

  @FXML
  private TextField txt_Lname;

  @FXML
  private TextField txt_Npeople;

  @FXML
  private TextField txt_nRooms;

  @FXML
  private TextField txt_CheckIn;

  @FXML
  private TextField txt_CheckOut;

  @FXML
  private TextField txt_RoomType;

  @FXML
  private TextField txt_Email;

  @FXML
  private Button btn_AddGuest;

  @FXML
  private Button btn_GRepToEvReport;


  /***
   *
   * @param event Event that indicates the move to manager window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeRepToManLog(ActionEvent event) throws IOException {
    Parent manSumParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene manSumScene = new Scene(manSumParent);

    Stage mSWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    mSWindow.setScene(manSumScene);
    mSWindow.show();
  }

  /**
   *
   * @param event Action that changes scenes from Guest Report to Event Report
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeGuRepEvReport(ActionEvent event) throws IOException {
    Parent manReportParent = FXMLLoader.load(getClass().getResource("ManEventReport.fxml"));
    Scene manRepScene = new Scene(manReportParent);

    Stage mRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    mRWindow.setScene(manRepScene);
    mRWindow.show();
  }

  /***
   *
   * @param event Action that indicates the click on the button to add a guest
   */
  @FXML
  void addGuest(ActionEvent event) {
    Guest myGuest = new Guest((txt_Email.getText()), txt_Fname.getText(),
        txt_Lname.getText(), Integer.parseInt(txt_Npeople.getText()),
        Integer.parseInt(txt_nRooms.getText()),
        txt_CheckIn.getText(), txt_CheckOut.getText(), txt_RoomType.getText(),
        txt_roomNumb.getText());
    tablev_Report.getItems().add(myGuest);
  }

  /***
   *
   * @param event Action that indicates the click to cancel  a selected reservation from the manager report
   */
  @FXML
  void cancelReservation(ActionEvent event) throws SQLException {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Cancel Reservation");
    alert.setHeaderText("Are you sure you want to CANCEL this Reservation?");
    alert.setContentText(null);
    Optional<ButtonType> action = alert.showAndWait();

    if (action.get() == ButtonType.OK) {
      try {
        System.out.println("Deleting Guest/Reservation Info");
        Guest guest = tablev_Report.getSelectionModel().getSelectedItem();
        String selectedGuest = guest.getEmail();
        String sql = "DELETE FROM GUEST WHERE EMAIL = " + "\'" + selectedGuest + "\';";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();
        System.out.println("Guest/Reservation Info Deleted!");

      } catch (SQLException e) {
        System.out.println("Could not delete guest");
        e.printStackTrace();
      }
      tablev_Report.getItems().removeAll(tablev_Report.getSelectionModel().getSelectedItem());
    }
  }

  // Database management

  private Connection conn = null;
  private Statement stmt = null;


  public void initialize() {
    initializeDB();
    populateGuestTableReport();
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

  ObservableList<Guest> glist = FXCollections.observableArrayList();

  public void populateGuestTableReport() {
    col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    col_FirstName.setCellValueFactory(new PropertyValueFactory<>("name"));
    col_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    col_NPeople.setCellValueFactory(new PropertyValueFactory<>("noPeople"));
    col_Nrooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
    col_CheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
    col_CheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
    col_RType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    col_Password.setCellValueFactory(new PropertyValueFactory<>("password"));
    try {
      String sql = "SELECT * FROM Guest";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {

        glist.add(new Guest(rs.getString("EMAIL"), rs.getString("NAME"),
            rs.getString("LASTNAME"), Integer.parseInt(rs.getString("NOPEOPLE")),
            Integer.parseInt(rs.getString("NOROOMS")), rs.getString("CHECKIN"),
            rs.getString("CHECKOUT"), rs.getString("ROOMTYPE"),
            rs.getString("PASSWORD")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    tablev_Report.setItems(glist);
  }
   /*Kristy is working on populating the Guest list for the Manager Report
  private Connection conn = null;
  private Statement stmt = null;
  public void initialize() {
    initializeDB();
    populateGuestTableReport();
  }
  private void initializeDB() {
    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/test";
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
  ObservableList<Guest> glist = FXCollections.observableArrayList();
  //@Override
 // public void initialize(URL location, ResourceBundle resources) {
    public void populateGuestTableReport(){
      try {
        String sql = "SELECT * FROM GUEST";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          glist.add(new Guest(rs.getString("email"),rs.getString("name"),
              rs.getString("lastName"), Integer.parseInt(rs.getString("noPeople")),
              Integer.parseInt(rs.getString("noRooms")), rs.getString("checkIn"),
              rs.getString("checkOut"), rs.getString("roomType"),
              rs.getString("password") ));
        }
        } catch (SQLException e) {
        e.printStackTrace();
      }
      col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
      col_FirstName.setCellValueFactory(new PropertyValueFactory<>("name"));
      col_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      col_NPeople.setCellValueFactory(new PropertyValueFactory<>("noPeople"));
      col_Nrooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
      col_CheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
      col_CheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
      col_RType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
      col_Password.setCellValueFactory(new PropertyValueFactory<>("password"));
      tablev_Report.setItems(glist);
    }
*/
}