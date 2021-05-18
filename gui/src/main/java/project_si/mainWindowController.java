package project_si;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import static javafx.application.Platform.exit;

public class mainWindowController {

    public Data data;

    @FXML
    Button nextGenerationButton;

    @FXML
    Button lastGenerationButton;
    @FXML
    Button startButton;
    @FXML
    Button exitButton;
    @FXML
    HBox hBoxWhichParameterButtonIs;

    @FXML
    void initialize(){
        data = new Data.Builder().build();
        nextGenerationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> nextGenerationButtonClicked());
        lastGenerationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x->  lastGenerationButtonClicked());
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x->  startButtonClicked());
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x->  exit());

        createParametersButton();
    }

    private void createParametersButton() {
        ParametersButton parametersButton = new ParametersButton("Parameters",this);
        hBoxWhichParameterButtonIs.getChildren().add(parametersButton);
    }

    private void nextGenerationButtonClicked() {
        System.out.println("nextGenerationClicked!");
    }

    private void lastGenerationButtonClicked() {
        System.out.println("lastGenerationClicked!");
    }

    private void startButtonClicked() { System.out.println("startClicked!");
    }

    void setData(Data aDataWithParameters) {
        data = aDataWithParameters;
    }

    Data getData() {
        return data;
    }
}
