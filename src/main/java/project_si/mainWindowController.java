package project_si;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import static javafx.application.Platform.exit;

public class mainWindowController {
    @FXML
    Button closeButton;

    @FXML
    void initialize(){
        closeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> exit());
    }
}
