package ResortArenaPalace;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SortEvent;
import javafx.stage.Stage;

public class BarChartEx {

  @FXML private BarChart<String, Integer> barChart_Booking;

  @FXML private CategoryAxis x_Months;

  @FXML private NumberAxis y_Reservation;

  @FXML
  private Button btn_GuestChart;

  @FXML
  void changeGuestChartToReport(ActionEvent event) throws IOException {
    Parent barChartParent = FXMLLoader.load(getClass().getResource("ManReport.fxml"));
    Scene guestRepScene = new Scene(barChartParent);

    Stage homeRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    homeRWindow.setScene(guestRepScene);
    homeRWindow.show();
  }

  /* Database initialization and connection */

  private Connection conn = null;
  private Statement stmt = null;

  public void initialize() {

    initializeDB();
    countJanuary();
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

  /* Data extraction for the bar chart */

  public void countJanuary() {
    String string1;
    int jan_count = 0;
    int feb_count = 0;
    int mar_count = 0;
    int apr_count = 0;
    int may_count = 0;
    int jun_count = 0;
    int jul_count = 0;
    int aug_count = 0;
    int sep_count = 0;
    int oct_count = 0;
    int nov_count = 0;
    int dec_count = 0;
    try {
      String sql = "SELECT CHECKIN FROM GUEST";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        string1 = rs.getString("CHECKIN");
        System.out.println(string1);
        if (string1.substring(5, 7).equals("01")) jan_count++;
        else if (string1.substring(5, 7).equals("02")) feb_count++;
        else if (string1.substring(5, 7).equals("03")) mar_count++;
        else if (string1.substring(5, 7).equals("04")) apr_count++;
        else if (string1.substring(5, 7).equals("05")) may_count++;
        else if (string1.substring(5, 7).equals("06")) jun_count++;
        else if (string1.substring(5, 7).equals("07")) jul_count++;
        else if (string1.substring(5, 7).equals("08")) aug_count++;
        else if (string1.substring(5, 7).equals("09")) sep_count++;
        else if (string1.substring(5, 7).equals("10")) oct_count++;
        else if (string1.substring(5, 7).equals("11")) nov_count++;
        else if (string1.substring(5, 7).equals("12")) dec_count++;
        else System.out.println("Date Not available");
      }
      System.out.println(jan_count);
      System.out.println(feb_count);
      System.out.println(mar_count);
      System.out.println(apr_count);
      System.out.println(may_count);
      System.out.println(jun_count);
      System.out.println(jul_count);
      System.out.println(aug_count);
      System.out.println(sep_count);
      System.out.println(oct_count);
      System.out.println(nov_count);
      System.out.println(dec_count);

    } catch (SQLException e) {
      e.printStackTrace();
    }
    loadBarChart(
        jan_count, feb_count, mar_count, apr_count, may_count, jun_count, jul_count, aug_count,
        sep_count, oct_count, nov_count, dec_count);
  }

  public void loadBarChart(
      int jan,
      int feb,
      int mar,
      int apr,
      int may,
      int jun,
      int jul,
      int aug,
      int sep,
      int oct,
      int nov,
      int dec) {
    XYChart.Series setGuest = new XYChart.Series<>();
    setGuest.getData().add(new XYChart.Data("January", jan));
    setGuest.getData().add(new XYChart.Data("February", feb));
    setGuest.getData().add(new XYChart.Data("March", mar));
    setGuest.getData().add(new XYChart.Data("April", apr));
    setGuest.getData().add(new XYChart.Data("May", may));
    setGuest.getData().add(new XYChart.Data("June", jun));
    setGuest.getData().add(new XYChart.Data("July", jul));
    setGuest.getData().add(new XYChart.Data("August", aug));
    setGuest.getData().add(new XYChart.Data("September", sep));
    setGuest.getData().add(new XYChart.Data("October", oct));
    setGuest.getData().add(new XYChart.Data("November", nov));
    setGuest.getData().add(new XYChart.Data("December", dec));
    barChart_Booking.getData().addAll(setGuest);
  }
}
