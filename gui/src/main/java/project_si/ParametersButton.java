package project_si;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParametersButton extends Button {

    private Stage parametersWindow = new Stage();

    ParametersButton(String text, mainWindowController aMainWindowController) {
        super(text);
        setFont(Font.font(15));
        addEventFilter(MouseEvent.MOUSE_CLICKED, x -> {
            Data dataWithParameters = showParametersWindowAndReturnSetData();
            if(dataWithParameters!=null){
                aMainWindowController.setData(dataWithParameters);
            }
        });
    }

    private Data showParametersWindowAndReturnSetData() {
        HBox top = new HBox();
        VBox right = new VBox();
        VBox center = new VBox();
        HBox bottom = new HBox();
        preparingParametersWindow(top,right,center,bottom);

        return null;
    }

    private void preparingParametersWindow(HBox aTop, VBox aRight, VBox aCenter, HBox aBottom) {
        parametersWindow = new Stage();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 700, 350);
        scene.getStylesheets().add("fxml/main.css");
        parametersWindow.setScene(scene);
        parametersWindow.initOwner(this.getScene().getWindow());
        parametersWindow.initModality(Modality.APPLICATION_MODAL);
        parametersWindow.setTitle("Parameters");
        parametersWindow.setResizable(false);
        aBottom.setAlignment(Pos.CENTER);
        aCenter.setAlignment(Pos.CENTER);
        aTop.setAlignment(Pos.CENTER);
        pane.setBottom(aBottom);
        pane.setCenter(aCenter);
        pane.setTop(aTop);
    }
}
