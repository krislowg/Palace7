package ResortArenaPalace;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserReservationDetails {

  @FXML
  private Label lbl_TitleReservation;

  @FXML
  private TextArea txtA_ReservationDet;

  @FXML
  private Button btn_CancelR;

  @FXML
  private Button btn_SignOut;

  @FXML
  private ImageView roomImage;

  @FXML
  private Label thankMessage;


  @FXML
  private Pane infoPane;


  @FXML
  private Pane roomPane;

  @FXML
  private Text rmText;

  @FXML
  private Text infoText;


  @FXML
  private Text cancelText;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label email;

    @FXML
    private Label paymentType;

    @FXML
    private Label numberGuests;

    @FXML
    private Label numberRooms;

    @FXML
    private Label roomType;

    @FXML
    private Label checkInDate;

    @FXML
    private Label checkOutDate;

  public static GuestReservation details;
  /***
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
  void initialize() {
    cancelText.setVisible(false);
    firstName.setText(details.getName());
    lastName.setText(details.getlName());
    email.setText(details.getEmail());
    paymentType.setText("details.getPaymentType()");
    numberGuests.setText(Integer.toString(details.getNum_p()));
    numberRooms.setText(Integer.toString(details.getNum_r()));
    roomType.setText(details.getRoomType());
    checkInDate.setText(details.getChkIn());
    checkOutDate.setText(details.getChkOut());
//    Integer.toString(details.getNum_p());
//      numberGuests.setText(details.getNoPeople());
//      numberRooms.setText(details.getNoRooms());

    //change expiration date to datepicker
    //pull info
    //check info
    //push to db
    //on room availability add sold out functionality
    //add cancel reservation to homepage "email and password pull"
    //work on this file (pull textbox info/setlabel for incorrect cases), landingpage (ending date cannot be before start date (how to block out old dates on datepicker)/ if nothing selected label.settext("must select ~~")), room availability on date, add cancel reservation on landingpage.
    //edit management to add management and encrypt password
  }

  /***
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

  public void sendText4(GuestReservation details){
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
}
