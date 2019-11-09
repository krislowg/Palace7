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
