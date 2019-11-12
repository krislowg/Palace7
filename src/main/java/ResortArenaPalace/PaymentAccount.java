package ResortArenaPalace;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

//Payment and User Account

public class PaymentAccount {

  @FXML
  private Button btn_GoBackRoomA;

  @FXML
  private ChoiceBox<String> cBox_CCType;

  @FXML
  private Button btn_ReserveRoom;

  @FXML
  private Label lbl_RoomConf;

  private ObservableList<String> cCType = FXCollections.observableArrayList("MasterCard", "Visa",
      "AmericanExpress", "Discovery");

  public void initialize() {
    cBox_CCType.setItems(cCType);//sets the items in the comboBox
    cBox_CCType.getSelectionModel().selectFirst();//Sets a default value in the comboBox
  }

  /***
   *
   * @param event Event that indicates the move to room availability window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void changePayToRoomA(ActionEvent event) throws IOException {
    Parent payParent = FXMLLoader.load(getClass().getResource("RoomAvailability.fxml"));
    Scene payScene = new Scene(payParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(payScene);
    payWindow.show();
  }

  /***
   *
   * @param event Event that allows showing a reservation confirmation
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void showConfirmation(ActionEvent event) throws IOException {
    Parent payParent = FXMLLoader.load(getClass().getResource("ConfirmMessage.fxml"));
    Scene payScene = new Scene(payParent);

    Stage payWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    payWindow.setScene(payScene);
    payWindow.show();

  }
}