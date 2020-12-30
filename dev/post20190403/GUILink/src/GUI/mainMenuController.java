package GUI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

import java.io.IOException;

public class mainMenuController {

    public Button continueBtn;
    public Button createTownBtn;
    public Button createTownAndNextBtn;

    /**
     * Function for switching scenes
     * If made a new one and auto imports packages, rename java.awt.event.ActionEvent to javafx.event.Ac.. to be able to communicate with fxml
     * @param event is just when an event is being used
     * @throws IOException if there is a typo or other errors with the file in .getResource
     */
    public void moveNextScene(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("addPub.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
    public void createTown(){

    }

    public void createTownAndNext(){

    }
}
