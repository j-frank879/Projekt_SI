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

    private TextField crossoverProbabilityTextField;
    private TextField mutationProbabilityTextField;
    private TextField twoVariablesFunctionTextField;
    private TextField populationSizeTextField;
    private TextField numberOfGenerationsTextField;
    private TextField minXTextField;
    private TextField minYTextField;
    private TextField maxXTextField;
    private TextField maxYTextField;

    ParametersButton(String text, mainWindowController aMainWindowController) {
        super(text);
        setFont(Font.font(15));
        addEventFilter(MouseEvent.MOUSE_CLICKED, x -> {
            dataWithParameters = showParametersWindowAndReturnSetData();
            if(dataWithParameters!=null)
                aMainWindowController.setData(dataWithParameters);

        });
    }

    private Data showParametersWindowAndReturnSetData() {
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

        return null;
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

        crossoverProbabilityTextField = new TextField();
        HBox hBoxCrossoverProbability = new HBox(5,new Label("CrossoverProbability"),crossoverProbabilityTextField);
        hBoxCrossoverProbability.setAlignment(Pos.CENTER);

        mutationProbabilityTextField = new TextField();
        HBox hBoxMutationProbability = new HBox(5,new Label("MutationProbability"),mutationProbabilityTextField);
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

        twoVariablesFunctionTextField = new TextField();
        HBox twoVariablesFunction = new HBox(5,new Label("F(x,y):"),twoVariablesFunctionTextField);
        twoVariablesFunction.setAlignment(Pos.CENTER);

        minXTextField = new TextField();
        minXTextField.setPrefWidth(30);
        minYTextField = new TextField();
        minYTextField.setPrefWidth(30);
        HBox minXAndMinY = new HBox(5,new Label("MinX:"),minXTextField,new Label("MinY:"),minYTextField);
        minXAndMinY.setAlignment(Pos.CENTER);

        maxXTextField = new TextField();
        maxXTextField.setPrefWidth(30);
        maxYTextField = new TextField();
        maxYTextField.setPrefWidth(30);
        HBox maxXAndMaxY = new HBox(5,new Label("MaxX:"),maxXTextField,new Label("MaxY:"),maxYTextField);
        maxXAndMaxY.setAlignment(Pos.CENTER);

        aLeft.getChildren().addAll(new Label("Two-variable function:"),twoVariablesFunction,minXAndMinY,maxXAndMaxY);
    }

    private void prepareCenter(VBox aCenter) {
        populationSizeTextField = new TextField();
        HBox populationSize = new HBox(5,new Label("Population size:"),populationSizeTextField);
        populationSize.setAlignment(Pos.CENTER);

        numberOfGenerationsTextField = new TextField();
        HBox numberOfGenerations = new HBox(5,new Label("Number of generation:"),numberOfGenerationsTextField);
        numberOfGenerations.setAlignment(Pos.CENTER);

        aCenter.getChildren().addAll(populationSize,numberOfGenerations);
    }

    private void prepareBottom(HBox aBottom) {
        Button saveButton = new Button("Save");
        saveButton.setPrefWidth(100);
        saveButton.addEventHandler(MouseEvent.MOUSE_CLICKED,x-> parametersWindow.close());

        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefWidth(100);
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED,x-> {
            dataWithParameters = null;
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
