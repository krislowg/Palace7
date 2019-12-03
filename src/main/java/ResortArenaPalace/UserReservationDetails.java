package ResortArenaPalace;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Final page for creation of reservation.
 *
 * @file UserReservationDetails.java
 * @author Shane Broxson
 */
public class UserReservationDetails {
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
  private static final String JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:./res/palace";
  private Connection conn = null;
  private Statement stmt = null;
  private GuestReservation detail;

  @FXML
  public void initialize() {
    cancelText.setVisible(false);
  }

  /**
   * *
   *
   * @param details Object of guest reservation type that stores the details of the booking
   */
  void sendText4(GuestReservation details) {
    detail = details;
    System.out.println(detail.getEmail());
    email.setText(detail.getEmail());
    firstName.setText(detail.getName());
    lastName.setText(detail.getlName());
    numberGuests.setText(String.valueOf(detail.getNum_p()));
    numberRooms.setText(String.valueOf(detail.getNum_r()));
    checkInDate.setText(detail.getChkIn());
    checkOutDate.setText(detail.getChkOut());
    roomType.setText(detail.getRoomType());

    // Sets Image based on room type
    Image image;
    if (roomType.getText().equals("Luxury")) {
      image = new Image("/ResortPictures/First_room.jpg");
    } else if (roomType.getText().equals("Underwater")) {
      image = new Image("/ResortPictures/UnderWater_Room.jpg");
    } else if (roomType.getText().equals("Superior")) {
      image = new Image("/ResortPictures/Queen_room.jpg");
    } else if (roomType.getText().equals("Grand")) {
      image = new Image("/ResortPictures/Grand_room.jpg");
    } else {
      image = new Image("/ResortPictures/beach.jpg");
    }
    roomImage.setImage(image);
  }

  /**
   * *
   *
   * @param event Event related to canceling a reservation
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void cancelReservation(ActionEvent event) throws IOException {
    System.out.println("Cancel Reservation Pressed");
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Cancel Reservation");
    alert.setHeaderText("Are you sure you want to CANCEL this Reservation?");
    alert.setContentText(null);
    Optional<ButtonType> action = alert.showAndWait();
    try {
      Class.forName(JDBC_DRIVER);
      conn = DriverManager.getConnection(DB_URL);
      stmt = conn.createStatement();
      System.out.println("Successfully connected to database!");
    } catch (Exception e) {
      e.printStackTrace();
      Alert a = new Alert(Alert.AlertType.ERROR);
      a.show();
    }
    if (action.get() == ButtonType.OK) {
      try {
        System.out.println("Deleting Guest/Reservation Info");
        String sql = "DELETE FROM GUEST WHERE EMAIL = " + "\'" + email.getText() + "\';";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();
        System.out.println("Guest/Reservation Info Deleted!");

      } catch (SQLException e) {
        System.out.println("Could not delete guest");
        e.printStackTrace();
      }
    }
    roomImage.setVisible(false);
    infoPane.setVisible(false);
    roomPane.setVisible(false);
    infoText.setVisible(false);
    rmText.setVisible(false);
    cancelText.setVisible(true);
  }

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
}
