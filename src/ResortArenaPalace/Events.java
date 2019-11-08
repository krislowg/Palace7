package ResortArenaPalace;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Events {

    @FXML
    private Button eventButton;

    /***
     *
     * @param actionEvent   Action to load new window
     * @throws IOException  The check exception thrown when working with input or output
     */
    public void openResaurant(ActionEvent actionEvent) throws IOException {
        Parent restaurantParent = FXMLLoader.load(getClass().getResource("Restaurant.fxml"));
        Scene restaurant = new Scene(restaurantParent);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(restaurant);
        window.show();

    }


}

