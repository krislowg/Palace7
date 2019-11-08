package ResortArenaPalace;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ManEventReport {

  @FXML
  private TableView<?> tablev_EventReport;

  @FXML
  private TableColumn<?, ?> col_EvEmail;

  @FXML
  private TableColumn<?, ?> col_FullName;

  @FXML
  private TableColumn<?, ?> col_EventType;

  @FXML
  private TableColumn<?, ?> col_EvDate;

  @FXML
  private TableColumn<?, ?> col_EvNPeople;

  @FXML
  private TableColumn<?, ?> col_EvVenue;

  @FXML
  private TableColumn<?, ?> col_EvCatering;

  @FXML
  private TableColumn<?, ?> col_DJ;

  @FXML
  private TableColumn<?, ?> col_PartyPlanner;

  @FXML
  private TableColumn<?, ?> col_EvPassword;

  @FXML
  private TextField txt_PartyPlanner;

  @FXML
  private TextField txt_Fname;

  @FXML
  private TextField txt_EvType;

  @FXML
  private TextField txt_EvDate;

  @FXML
  private TextField txt_EvPeople;

  @FXML
  private TextField txt_Venue;

  @FXML
  private TextField txt_EvCatering;

  @FXML
  private TextField txt_DJ;

  @FXML
  private TextField txt_Email;

  @FXML
  private TextField txt_Password;

  @FXML
  private Label lbl_TitleReport;

  @FXML
  private Button btn_CancelEvent;

  @FXML
  private Button btn_SoldOut;

  @FXML
  private Button btn_BackEVRepToGuestRep;

  @FXML
  private Button btn_AddEvent;

  @FXML
  private Button btn_LogOut;

  @FXML
  void addEvent(ActionEvent event) {

  }

  @FXML
  void cancelEvent(ActionEvent event) {

  }

  @FXML
  void changeEvRepToHome(ActionEvent event) throws IOException {
    Parent evReportParent = FXMLLoader.load(getClass().getResource("LandingPage.fxml"));
    Scene evRepScene = new Scene(evReportParent);

    Stage eRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    eRWindow.setScene(evRepScene);
    eRWindow.show();
  }

  @FXML
  void changeEventRepToGuestReport(ActionEvent event) throws IOException {
    Parent eventReportParent = FXMLLoader.load(getClass().getResource("ManReport.fxml"));
    Scene eventRepScene = new Scene(eventReportParent);

    Stage homeRWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    homeRWindow.setScene(eventRepScene);
    homeRWindow.show();
  }

}
