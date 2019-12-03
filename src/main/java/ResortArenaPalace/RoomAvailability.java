package ResortArenaPalace;

import java.io.IOException;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Page to determine RoomType for reservations.
 *
 * @file RoomAvailability.java
 * @author Shane Broxson
 */
public class RoomAvailability {

  @FXML private AnchorPane lbl_RoomAv;
  @FXML private AnchorPane room1;
  @FXML private TextArea r1Details;
  @FXML private TextArea r3Details;
  @FXML private Label room3;
  @FXML private Label room2;
  @FXML private TextArea r2Details;
  @FXML private Label room4;
  @FXML private Label room;
  @FXML private Button btn_Select1rm;
  @FXML private Button btn_GoHomeRA;
  @FXML private Button btn_Select3rm;
  @FXML private Button btn_Select2rm;
  @FXML private TextArea r4Details;
  @FXML private Button btn_Select4rm;
  public static GuestReservation roomInfo;

  /**
   * *
   *
   * @param roomInfos Object of guest reservation class that stores the booked room information
   */
  void sendText(GuestReservation roomInfos) {
    this.roomInfo = roomInfos;
    System.out.println(roomInfo);
  }

  /**
   * *
   *
   * @param event Event that indicates the move to payment scene
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeR1toPay(ActionEvent event) throws IOException {
    GuestReservation roomChoice = new GuestReservation(roomInfo, "Luxury");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("PaymentAccount.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    PaymentAccount y = loader.getController();
    y.sendText2(roomChoice);

    Parent paymentAccountParent = FXMLLoader.load(getClass().getResource("PaymentAccount.fxml"));
    Scene roomAvScene = new Scene(paymentAccountParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(roomAvScene);
    payWindow.show();
  }

  /**
   * *
   *
   * @param event Event that indicates the move to payment scene
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeR2toPay(ActionEvent event) throws IOException {
    GuestReservation roomChoice = new GuestReservation(roomInfo, "Underwater");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("PaymentAccount.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    PaymentAccount y = loader.getController();
    y.sendText2(roomChoice);

    Parent paymentAccountParent = FXMLLoader.load(getClass().getResource("PaymentAccount.fxml"));
    Scene roomAvScene = new Scene(paymentAccountParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(roomAvScene);
    payWindow.show();
  }

  /**
   * *
   *
   * @param event Event that indicates the move to payment scene
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeR3toPay(ActionEvent event) throws IOException {
    GuestReservation roomChoice = new GuestReservation(roomInfo, "Superior");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("PaymentAccount.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    PaymentAccount y = loader.getController();
    y.sendText2(roomChoice);

    Parent paymentAccountParent = FXMLLoader.load(getClass().getResource("PaymentAccount.fxml"));
    Scene roomAvScene = new Scene(paymentAccountParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(roomAvScene);
    payWindow.show();
  }

  /**
   * *
   *
   * @param event Event that indicates the move to payment scene
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeR4toPay(ActionEvent event) throws IOException {
    GuestReservation roomChoice = new GuestReservation(roomInfo, "Grand");
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("PaymentAccount.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    PaymentAccount y = loader.getController();
    y.sendText2(roomChoice);

    Parent paymentAccountParent = FXMLLoader.load(getClass().getResource("PaymentAccount.fxml"));
    Scene roomAvScene = new Scene(paymentAccountParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(roomAvScene);
    payWindow.show();
  }

  /**
   * *
   *
   * @param event Event that indicates the move to home scene
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changeRAToHome(ActionEvent event) throws IOException {
    Parent roomAvHParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene homePScene = new Scene(roomAvHParent);

    Stage homePWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    homePWindow.setScene(homePScene);
    homePWindow.show();
  }
}
