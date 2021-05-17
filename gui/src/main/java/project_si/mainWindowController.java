package project_si;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Platform.exit;

public class mainWindowController {

    public Data data=new Data();

    @FXML
    Button nextGenerationButton;
    @FXML
    Button parametersButton;
    @FXML
    Button lastGenerationButton;
    @FXML
    Button startButton;
    @FXML
    Button exitButton;


    @FXML
    void initialize(){
        parametersButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> parametersButtonClicked());
        nextGenerationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x-> nextGenerationButtonClicked());
        lastGenerationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x->  lastGenerationButtonClicked());
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x->  startButtonClicked());
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x->  exit());
    }

    private void parametersButtonClicked(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/parametersWindow.fxml"));
        Stage parametersWindow = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add("fxml/main.css");
            parametersWindow.setScene(scene);
            parametersWindow.initOwner(parametersButton.getScene().getWindow());
            parametersWindow.initModality(Modality.APPLICATION_MODAL);
            parametersWindow.setTitle("Choose parameters!");
            parametersWindow.setResizable(false);
            parametersWindow.showAndWait();
        } catch (IOException aE) {
            aE.printStackTrace();
        }
//        Parent root = null;
//        try {
//            root = (Parent) fxmlLoader.load();
//            parametersWindowController controller= fxmlLoader.getController();
//            controller.setData(data);
//            Stage stage = new Stage();
//            stage.setTitle("Parameters");
//            stage.setScene(new Scene(root));
//            stage.showAndWait();
//        } catch (IOException aE) {
//            aE.printStackTrace();
//        }

    }

    private void nextGenerationButtonClicked() {
        System.out.println("nextGenerationClicked!");
    }

    private void lastGenerationButtonClicked() {
        System.out.println("lastGenerationClicked!");
    }

    private void startButtonClicked() {
        System.out.println("startClicked!");
    }
}
