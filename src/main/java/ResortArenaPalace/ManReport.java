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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManReport {

  @FXML private TableView<Guest> tablev_Report;

  @FXML private TableColumn<Guest, String> col_FirstName;

  @FXML private TableColumn<Guest, String> col_LastName;

  @FXML private TableColumn<Guest, Integer> col_NPeople;

  @FXML private TableColumn<Guest, Integer> col_Nrooms;

  @FXML private TableColumn<Guest, String> col_CheckIn;

  @FXML private TableColumn<Guest, String> col_CheckOut;

  @FXML private TableColumn<Guest, String> col_RType;

  @FXML private TableColumn<Guest, String> col_Email;

  @FXML private TableColumn<Guest, String> col_Password;

  @FXML private Label lbl_TitleReport;

  @FXML private Button btn_CancelR;

  @FXML private Button btn_SummaryGuest;

  @FXML private Button btn_BackRepToMan;

  @FXML private TextField txt_password;

  @FXML private TextField txt_Fname;

  @FXML private TextField txt_Lname;

  @FXML private TextField txt_CheckIn;

  @FXML private TextField txt_CheckOut;

  @FXML private TextField txt_Email;

  @FXML
  private Label txt_ErrorFillFields;

  @FXML private Button btn_AddGuest;

  @FXML private Button btn_GRepToEvReport;

  @FXML private ComboBox<String> cbox_NoPeople;

  @FXML private ComboBox<String> cbox_NoRoom;

  @FXML private ComboBox<String> cbox_RoomType;

  /**
   * *
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

  @FXML
  void changeRepToBarChart(ActionEvent event) throws IOException {
    Parent eventRepParent = FXMLLoader.load(getClass().getResource("BarChartEx.fxml"));
    // Parent eventRepParent = FXMLLoader.load(getClass().getResource("BarChartEx.fxml"));
    Scene evRepScene = new Scene(eventRepParent);

    Stage barChartWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    barChartWindow.setScene(evRepScene);
    barChartWindow.show();
  }

  // Array list for the comboBox to pick the number of rooms
  private ObservableList<String> noRoomsReport =
      FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

  // Array list for the comboBox to pick the number of guests
  private ObservableList<String> noGuestReport =
      FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

  private ObservableList<String> roomTypes =
      FXCollections.observableArrayList("Luxury", "Underwater", "Superior", "GrandArena");

  private ObservableList<Guest> guestReport = FXCollections.observableArrayList(); // Table view related
  /**
   * *
   *
   * @param event Action that indicates the click on the button to add a guest
   */
  @FXML
  void addGuest(ActionEvent event) {
    if (txt_Email.getText().trim().isEmpty() || txt_CheckIn.getText().trim().isEmpty()) {
      Alert alert = new Alert(AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("You must fill all the fields");
      alert.setContentText(null);
      Optional<ButtonType> action = alert.showAndWait();
    } else {
      // Getting values from text field and combobox in Product Line tab and storing them in a
      // variable
      String g_email = txt_Email.getText();
      String g_FirstName = txt_Fname.getText();
      String g_LastName = txt_Lname.getText();
      int g_NoPeople = Integer.parseInt(cbox_NoPeople.getValue());
      int g_NoRooms = Integer.parseInt(cbox_NoRoom.getValue());
      String g_CheckIn = txt_CheckIn.getText();
      String g_Checkout = txt_CheckOut.getText();
      String g_RoomType = cbox_RoomType.getValue();
      String g_password = txt_password.getText();

      Guest newGuest =
          new Guest(
              g_email,
              g_FirstName,
              g_LastName,
              g_NoPeople,
              g_NoRooms,
              g_CheckIn,
              g_Checkout,
              g_RoomType,
              g_password);

      // settingUpColumns();
      guestReport.add(newGuest);
      tablev_Report.setItems(guestReport);

      try {

        // Inserts the given values into the DataBase ProdDB. (Product Table)
        System.out.println("Attempting to INSERT");
        String sql =
            "INSERT INTO GUEST(EMAIL,NAME,LASTNAME,NOPEOPLE,NOROOMS,CHECKIN,CHECKOUT,ROOMTYPE,PASSWORD)"
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        // "SELECT * FROM PRODUCT";
        PreparedStatement ps = conn.prepareStatement(sql); // bugfound
        ps.setString(1, g_email);
        ps.setString(2, g_FirstName);
        ps.setString(3, g_LastName);
        ps.setString(4, String.valueOf(g_NoPeople));
        ps.setString(5, String.valueOf(g_NoRooms));
        ps.setString(6, g_CheckIn);
        ps.setString(7, g_Checkout);
        ps.setString(8, g_RoomType);
        ps.setString(9, g_password);

        ps.executeUpdate(); // Updates the values in the Product table
        System.out.println("Inserted!");

        // STEP 4: Clean-up environment
        stmt.close(); // Closes the statements and the connections
        conn.close();

      } catch (SQLException e) {
        e.printStackTrace();
      }
      /*Guest myGuest = new Guest((txt_Email.getText()), txt_Fname.getText(),
          txt_Lname.getText(), Integer.parseInt(txt_Npeople.getText()),
          Integer.parseInt(txt_nRooms.getText()),
          txt_CheckIn.getText(), txt_CheckOut.getText(), txt_RoomType.getText(),
          txt_password.getText());
      tablev_Report.getItems().add(myGuest);*/
    }
  }

  /**
   * *
   *
   * @param event Action that indicates the click to cancel a selected reservation from the manager
   *     report
   */
  @FXML
  void cancelReservation(ActionEvent event) {
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
    col_Email.setCellValueFactory(new PropertyValueFactory<>("email"));
    col_FirstName.setCellValueFactory(new PropertyValueFactory<>("name"));
    col_LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    col_NPeople.setCellValueFactory(new PropertyValueFactory<>("noPeople"));
    col_Nrooms.setCellValueFactory(new PropertyValueFactory<>("noRooms"));
    col_CheckIn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
    col_CheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
    col_RType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    col_Password.setCellValueFactory(new PropertyValueFactory<>("password"));

    cbox_NoPeople.setItems(noGuestReport); // sets the items in the ComboBox
    cbox_NoPeople.setEditable(true); // Allows the user edit
    cbox_NoPeople.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    cbox_NoRoom.setItems(noRoomsReport); // sets the items in the ComboBox
    cbox_NoRoom.setEditable(true); // Allows the user edit
    cbox_NoRoom.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

    cbox_RoomType.setItems(roomTypes); // sets the items in the ComboBox
    cbox_RoomType.setEditable(true); // Allows the user edit
    cbox_RoomType.getSelectionModel().selectFirst(); // Sets a default value in the ComboBox

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

  private ObservableList<Guest> glist = FXCollections.observableArrayList();

  private void populateGuestTableReport() {

    try {
      String sql = "SELECT * FROM Guest";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {

        glist.add(
            new Guest(
                rs.getString("EMAIL"),
                rs.getString("NAME"),
                rs.getString("LASTNAME"),
                Integer.parseInt(rs.getString("NOPEOPLE")),
                Integer.parseInt(rs.getString("NOROOMS")),
                rs.getString("CHECKIN"),
                rs.getString("CHECKOUT"),
                rs.getString("ROOMTYPE"),
                rs.getString("PASSWORD")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    tablev_Report.setItems(glist);
  }
}
