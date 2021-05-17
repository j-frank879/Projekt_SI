package project_si;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ParametersButton extends Button {

    private Stage parametersWindow = new Stage();
    private Data dataWithParameters;
    private boolean saving;

    private TextField twoVariablesFunctionTextField;
    private IntField crossoverProbabilityTextField;
    private IntField mutationProbabilityTextField;
    private IntField populationSizeTextField;
    private IntField numberOfGenerationsTextField;
    private IntField minXTextField;
    private IntField minYTextField;
    private IntField maxXTextField;
    private IntField maxYTextField;

    ParametersButton(String text, mainWindowController aMainWindowController) {
        super(text);
        setFont(Font.font(15));
        addEventFilter(MouseEvent.MOUSE_CLICKED, x -> {
            showParametersWindowAndSetWhetherSaveParameters();
            if(saving){

                aMainWindowController.setData(dataWithParameters);
            }
        });
    }

    private void showParametersWindowAndSetWhetherSaveParameters() {
        HBox top = new HBox();
        VBox left = new VBox(20);
        VBox center = new VBox(20);
        HBox bottom = new HBox();
        prepareParametersWindow(top,left,center,bottom);
        prepareTop(top);
        prepareLeft(left);
        prepareCenter(center);
        prepareBottom(bottom);
        parametersWindow.showAndWait();
    }

    private void prepareTop(HBox aTop) {
        aTop.setStyle("-fx-padding: 25px;");

        RadioButton nkbRadioButton = new RadioButton("NKB");
        RadioButton grayRadioButton = new RadioButton("Gray");

        ToggleGroup coding = new ToggleGroup();
        nkbRadioButton.setToggleGroup(coding);
        grayRadioButton.setToggleGroup(coding);

        VBox vBoxForCoding = new VBox(10,nkbRadioButton,grayRadioButton);
        vBoxForCoding.setAlignment(Pos.CENTER);

        RadioButton onePointCrossover = new RadioButton("One-point crossover");
        RadioButton twoPointCrossover = new RadioButton("Two-point crossover");
        RadioButton uniformCrossover = new RadioButton("Uniform crossover");

        ToggleGroup crossover = new ToggleGroup();
        onePointCrossover.setToggleGroup(crossover);
        twoPointCrossover.setToggleGroup(crossover);
        uniformCrossover.setToggleGroup(crossover);

        VBox vBoxForCrossover = new VBox(10,onePointCrossover,twoPointCrossover,uniformCrossover);
        vBoxForCrossover.setAlignment(Pos.CENTER);

        crossoverProbabilityTextField = new IntField(-1000,1000,0);
        HBox hBoxCrossoverProbability = new HBox(5,new Label("Crossover Probability"),crossoverProbabilityTextField);
        hBoxCrossoverProbability.setAlignment(Pos.CENTER);

        mutationProbabilityTextField = new IntField(-1000,1000,0);
        HBox hBoxMutationProbability = new HBox(5,new Label("Mutation Probability"),mutationProbabilityTextField);
        hBoxMutationProbability.setAlignment(Pos.CENTER);

        VBox vBoxForProbability = new VBox(10,hBoxCrossoverProbability,hBoxMutationProbability);
        vBoxForProbability.setAlignment(Pos.CENTER);

        Region regionLeft = new Region();
        Region regionCenterLeft = new Region();
        Region regionCenterRight = new Region();
        Region regionRight = new Region();
        HBox.setHgrow(regionLeft, Priority.ALWAYS);
        HBox.setHgrow(regionCenterLeft, Priority.ALWAYS);
        HBox.setHgrow(regionCenterRight, Priority.ALWAYS);
        HBox.setHgrow(regionRight, Priority.ALWAYS);

        aTop.getChildren().addAll(regionLeft,vBoxForCoding,regionCenterLeft,vBoxForCrossover,regionCenterRight,vBoxForProbability,regionRight);
    }

    private void prepareLeft(VBox aLeft) {
        aLeft.setStyle("-fx-padding: 30px;");

        twoVariablesFunctionTextField = new IntField(-1000,1000,0);
        HBox twoVariablesFunction = new HBox(5,new Label("F(x,y):"),twoVariablesFunctionTextField);
        twoVariablesFunction.setAlignment(Pos.CENTER);

        minXTextField = new IntField(-1000,1000,0);
        minXTextField.setPrefWidth(50);
        minYTextField = new IntField(-1000,1000,0);
        minYTextField.setPrefWidth(50);
        HBox minXAndMinY = new HBox(5,new Label("MinX:"),minXTextField,new Label("MinY:"),minYTextField);
        minXAndMinY.setAlignment(Pos.CENTER);

        maxXTextField = new IntField(-1000,1000,0);
        maxXTextField.setPrefWidth(50);
        maxYTextField = new IntField(-1000,1000,0);
        maxYTextField.setPrefWidth(50);
        HBox maxXAndMaxY = new HBox(5,new Label("MaxX:"),maxXTextField,new Label("MaxY:"),maxYTextField);
        maxXAndMaxY.setAlignment(Pos.CENTER);

        aLeft.getChildren().addAll(new Label("Two-variable function:"),twoVariablesFunction,minXAndMinY,maxXAndMaxY);
    }

    private void prepareCenter(VBox aCenter) {
        populationSizeTextField = new IntField(-1000,1000,0);
        HBox populationSize = new HBox(5,new Label("Population size:"),populationSizeTextField);
        populationSize.setAlignment(Pos.CENTER);

        numberOfGenerationsTextField = new IntField(-1000,1000,0);
        HBox numberOfGenerations = new HBox(5,new Label("Number of generation:"),numberOfGenerationsTextField);
        numberOfGenerations.setAlignment(Pos.CENTER);

        aCenter.getChildren().addAll(populationSize,numberOfGenerations);
    }

    private void prepareBottom(HBox aBottom) {
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        saveButton.setPrefWidth(100);
        cancelButton.setPrefWidth(100);

        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED,x->{
            saving = true;
            parametersWindow.close();
        });

        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,x-> {
            saving = false;
            parametersWindow.close();
        });

        aBottom.getChildren().addAll(saveButton,cancelButton);
    }

    private void prepareParametersWindow(HBox aTop, VBox aLeft, VBox aCenter, HBox aBottom) {
        parametersWindow = new Stage();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 700, 380);
        scene.getStylesheets().add("css/main.css");
        parametersWindow.setScene(scene);
        parametersWindow.initOwner(this.getScene().getWindow());
        parametersWindow.initModality(Modality.APPLICATION_MODAL);
        parametersWindow.setTitle("Parameters");
        parametersWindow.setResizable(false);
        aBottom.setAlignment(Pos.BOTTOM_RIGHT);
        aCenter.setAlignment(Pos.CENTER);
        aLeft.setAlignment(Pos.CENTER);
        aTop.setAlignment(Pos.CENTER);
        pane.setBottom(aBottom);
        pane.setLeft(aLeft);
        pane.setCenter(aCenter);
        pane.setTop(aTop);
    }
}
