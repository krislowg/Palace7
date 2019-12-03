package ResortArenaPalace;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class UserEventDetails {

  @FXML private Label res_name;
  @FXML private Label res_email;
  @FXML private Label res_event;
  @FXML private Label res_date;
  @FXML private Label res_num_people;
  @FXML private Label res_venue;
  @FXML private Label res_catering;
  @FXML private Label res_dj;
  @FXML private Label res_partyplanner;
  private EventReservation events;

  public void initialize() {}

  /***
   * Event handling and going back to the home page.
   */

  void sendEvent(EventReservation newEvent) {
    events = newEvent;
    res_name.setText(events.getfName());
    res_email.setText(events.getEmail());
    res_event.setText(events.getEvent());
    res_date.setText(events.getEventDate());
    res_num_people.setText(String.valueOf(events.getNoPeople()));
    res_venue.setText(events.getVenue());
    if (events.getCatering()) {
      res_catering.setText("YES");
    } else {
      res_catering.setText("NO");
    }
    if (events.getDj()) {
      res_dj.setText("YES");
    } else {
      res_dj.setText("NO");
    }
    if (events.getPartyPlanner()) {
      res_partyplanner.setText("YES");
    } else {
      res_partyplanner.setText("NO");
    }
  }

  @FXML
  void homePage(ActionEvent event) throws IOException {
    Parent userAccountParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene roomAvScene = new Scene(userAccountParent);

    Stage uAWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    uAWindow.setScene(roomAvScene);
    uAWindow.show();
  }
}
