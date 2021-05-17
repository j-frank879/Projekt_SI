package project_si;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class mainWindowController {

   public Data data=new Data();
    @FXML
    Button next_gen;
    Button parameters;
    Button last_gen;
    @FXML

    void initialize()  {
}

    public void parameters(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/parametersWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        parametersWindowController controller= fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Parameters");
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void last_generation(ActionEvent actionEvent) {
    }

    public void next_gen(ActionEvent actionEvent) {
    }


}
