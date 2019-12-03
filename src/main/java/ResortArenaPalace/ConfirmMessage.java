package ResortArenaPalace;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Confirm Message page in reserving a room, passes through all previous data.
 *
 * @file ConfirmMessage.java
 * @author Shane Broxson
 */
public class ConfirmMessage {
  public static GuestReservation finalDataSets;

  /**
   * @param event Event that indicates the move to room availability window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void userDetails(ActionEvent event) throws IOException {
    GuestReservation showData = finalDataSets;
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("UserReservationDetails.fxml"));
    Parent tableViewParent = loader.load();
    Scene tableViewScene = new Scene(tableViewParent);

    // Access the controller and call a method
    UserReservationDetails controller = loader.getController();
    controller.sendText4(finalDataSets);

    // This line gets the Stage information
    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

    window.setScene(tableViewScene);
    window.show();
  }

  /**
   * *
   *
   * @param finalDataSet Object of type guest reservation used to send text input
   */
  void sendText3(GuestReservation finalDataSet) {
    finalDataSets = finalDataSet;
    System.out.println(finalDataSets.getRoomType());
  }
}
