package project_si;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project_si.fields.DoubleField;
import project_si.fields.IntField;

public class ParametersButton extends Button {

    private Stage parametersWindow;
    private boolean saving;

    private DoubleField crossoverProbabilityTextField;
    private DoubleField mutationProbabilityTextField;

    private TextField twoVariablesFunctionTextField;
    private IntField populationSizeTextField;
    private IntField numberOfGenerationsTextField;
    private IntField minXTextField;
    private IntField minYTextField;
    private IntField maxXTextField;
    private IntField maxYTextField;

    private RadioButton nkbRadioButton;
    private RadioButton grayRadioButton;

    private RadioButton onePointCrossover;
    private RadioButton twoPointCrossover;
    private RadioButton uniformCrossover;

    ParametersButton(String text, mainWindowController aMainWindowController) {
        super(text);
        setFont(Font.font(15));
        addEventFilter(MouseEvent.MOUSE_CLICKED, x -> {
            Data startData = aMainWindowController.getData();
            Data resultData = showParametersWindowAndSetWhetherSaveParameters(startData);
            if(saving) aMainWindowController.setData(resultData);
        });
    }

    private Data showParametersWindowAndSetWhetherSaveParameters(Data aData) {
        HBox top = new HBox();
        VBox left = new VBox(20);
        VBox center = new VBox(20);
        HBox bottom = new HBox();
        prepareParametersWindow(top,left,center,bottom);
        prepareTop(top,aData);
        prepareLeft(left, aData);
        prepareCenter(center, aData);
        prepareBottom(bottom);
        parametersWindow.showAndWait();
        Data resultData = savingParametersInData();

        return resultData;
    }

    private Data savingParametersInData() {
        Coding kindOfCoding;
        Crossover kindOfCrossing;
        double probabilityOfCrossover;
        double probabilityOfMutation;
        int sizeOfPopulation;
        int numberOfGeneration;
        String twoVariablesFunction;
        int minX, maxX, minY, maxY;

        if(nkbRadioButton.isSelected()) kindOfCoding = Coding.NKB;
        else kindOfCoding = Coding.GRAY;

        if(onePointCrossover.isSelected()) kindOfCrossing = Crossover.ONE_POINT;
        else if (twoPointCrossover.isSelected()) kindOfCrossing = Crossover.TWO_POINT;
        else kindOfCrossing = Crossover.UNIFORM;

        probabilityOfCrossover = crossoverProbabilityTextField.getValue();
        probabilityOfMutation = mutationProbabilityTextField.getValue();
        sizeOfPopulation = populationSizeTextField.getValue();
        numberOfGeneration = numberOfGenerationsTextField.getValue();
        twoVariablesFunction = twoVariablesFunctionTextField.getText();

        minX = minXTextField.getValue();
        maxX = maxXTextField.getValue();
        minY = minYTextField.getValue();
        maxY = maxYTextField.getValue();

        Data resultData = new Data.Builder()
                .kindOfCoding(kindOfCoding)
                .kindOfCrossing(kindOfCrossing)
                .probabilityOfCrossover(probabilityOfCrossover)
                .probabilityOfMutation(probabilityOfMutation)
                .sizeOfPopulation(sizeOfPopulation)
                .numberOfGeneration(numberOfGeneration)
                .twoVariablesFunction(twoVariablesFunction)
                .minX(minX)
                .maxX(maxX)
                .minY(minY)
                .maxY(maxY)
                .build();

        return resultData;
    }

    private void prepareTop(HBox aTop, Data aData) {
        aTop.setStyle("-fx-padding: 25px;");

        nkbRadioButton = new RadioButton("NKB");
        grayRadioButton = new RadioButton("Gray");
        nkbRadioButton.setUserData(Coding.NKB);
        grayRadioButton.setUserData(Coding.GRAY);

        ToggleGroup coding = new ToggleGroup();
        nkbRadioButton.setToggleGroup(coding);
        grayRadioButton.setToggleGroup(coding);

        if(aData.getKindOfCoding() == Coding.NKB) nkbRadioButton.setSelected(true);
        else grayRadioButton.setSelected(true);

        VBox vBoxForCoding = new VBox(10,nkbRadioButton,grayRadioButton);
        vBoxForCoding.setAlignment(Pos.CENTER);

        onePointCrossover = new RadioButton("One-point crossover");
        twoPointCrossover = new RadioButton("Two-point crossover");
        uniformCrossover = new RadioButton("Uniform crossover");

        ToggleGroup crossover = new ToggleGroup();
        onePointCrossover.setToggleGroup(crossover);
        twoPointCrossover.setToggleGroup(crossover);
        uniformCrossover.setToggleGroup(crossover);

        if(aData.getKindOfCrossing() == Crossover.ONE_POINT) onePointCrossover.setSelected(true);
        else if(aData.getKindOfCrossing() == Crossover.TWO_POINT) twoPointCrossover.setSelected(true);
        else uniformCrossover.setSelected(true);

        VBox vBoxForCrossover = new VBox(10,onePointCrossover,twoPointCrossover,uniformCrossover);
        vBoxForCrossover.setAlignment(Pos.CENTER);

        crossoverProbabilityTextField = new DoubleField(0,1,0.2);
        crossoverProbabilityTextField.setValue(aData.getProbabilityOfCrossover()); //TODO change to double
        HBox hBoxCrossoverProbability = new HBox(5,new Label("Crossover Probability"),crossoverProbabilityTextField);
        hBoxCrossoverProbability.setAlignment(Pos.CENTER);

        mutationProbabilityTextField = new DoubleField(0,1,0.2);
        mutationProbabilityTextField.setValue(aData.getProbabilityOfMutation()); //TODO change to double
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

    private void prepareLeft(VBox aLeft, Data aData) {
        aLeft.setStyle("-fx-padding: 30px;");

        twoVariablesFunctionTextField = new TextField();
        twoVariablesFunctionTextField.setText(aData.getTwoVariablesFunction());
        HBox twoVariablesFunction = new HBox(5,new Label("F(x,y):"),twoVariablesFunctionTextField);
        twoVariablesFunction.setAlignment(Pos.CENTER);

        minXTextField = new IntField(-1000,1000,0);
        minXTextField.setValue(aData.getMinX());
        minXTextField.setPrefWidth(50);
        minYTextField = new IntField(-1000,1000,0);
        minYTextField.setValue(aData.getMinY());
        minYTextField.setPrefWidth(50);
        HBox minXAndMinY = new HBox(5,new Label("MinX:"),minXTextField,new Label("MinY:"),minYTextField);
        minXAndMinY.setAlignment(Pos.CENTER);

        maxXTextField = new IntField(-1000,1000,0);
        maxXTextField.setValue(aData.getMaxX());
        maxXTextField.setPrefWidth(50);
        maxYTextField = new IntField(-1000,1000,0);
        maxYTextField.setValue(aData.getMaxY());
        maxYTextField.setPrefWidth(50);
        HBox maxXAndMaxY = new HBox(5,new Label("MaxX:"),maxXTextField,new Label("MaxY:"),maxYTextField);
        maxXAndMaxY.setAlignment(Pos.CENTER);

        aLeft.getChildren().addAll(new Label("Two-variable function:"),twoVariablesFunction,minXAndMinY,maxXAndMaxY);
    }

    private void prepareCenter(VBox aCenter, Data aData) {
        populationSizeTextField = new IntField(-1000,1000,0);
        populationSizeTextField.setValue(aData.getSizeOfPopulation());
        HBox populationSize = new HBox(5,new Label("Population size:"),populationSizeTextField);
        populationSize.setAlignment(Pos.CENTER);

        numberOfGenerationsTextField = new IntField(-1000,1000,0);
        numberOfGenerationsTextField.setValue(aData.getNumberOfGeneration());
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
