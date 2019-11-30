package ResortArenaPalace;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class ConfirmMessage {
  @FXML private DialogPane dialog_p;

  @FXML private Button OkayBtn;

  public static GuestReservation finalDataSet;

  /**
   * @param event Event that indicates the move to room availability window
   * @throws IOException The check exception thrown when working with input or output
   */
  @FXML
  void userDetails(ActionEvent event) throws IOException {
    GuestReservation showData = finalDataSet;
    // System.out.println(roomChoice.getRoomType());
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("UserReservationDetails.fxml"));
    try {
      loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    UserReservationDetails y = loader.getController();
    y.sendText4(showData);

    Parent userAccountParent =
        FXMLLoader.load(getClass().getResource("UserReservationDetails.fxml"));
    Scene roomAvScene = new Scene(userAccountParent);

    Stage uAWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    uAWindow.setScene(roomAvScene);
    uAWindow.show();
  }

  void sendText3(GuestReservation finalDataSet) {
    this.finalDataSet = finalDataSet;
    System.out.println(finalDataSet.getRoomType());
  }
}
