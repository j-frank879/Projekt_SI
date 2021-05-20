package project_si;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static javafx.application.Platform.exit;

public class mainWindowController {

    private Data data;
    private int generation;
    private boolean ifStarted;
    @FXML
    Button nextGenerationButton;
    @FXML
    Button lastGenerationButton;
    @FXML
    Button startButton;
    @FXML
    Button exitButton;
    @FXML
    TextArea results;
    @FXML
    HBox hBoxWhichParameterButtonIs;
    @FXML
    RadioButton splitBetweenGenerationsRadioButton;

    @FXML
    void initialize() {
        data = new Data.Builder().build();
        nextGenerationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x -> nextGenerationButtonClicked());
        lastGenerationButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x -> lastGenerationButtonClicked());
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x -> startButtonClicked());
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x -> exit());

        createParametersAndResetButtons();
    }

    private void createParametersAndResetButtons() {
        ParametersButton parametersButton = new ParametersButton("Parameters", this);
        Button resetButton = new Button("Reset");
        resetButton.setFont(Font.font(15));
        resetButton.addEventHandler(MouseEvent.MOUSE_CLICKED, x -> resetButtonClicked());
        hBoxWhichParameterButtonIs.getChildren().addAll(parametersButton,resetButton);
    }

    private void resetButtonClicked() {
        generation = 0;
        results.clear();
        ifStarted = false;
    }

    private void nextGenerationButtonClicked() {
        if (!checkIfShouldSplitBetweenGenerationsAndReturnIfCanGoOn()) return;

        if (data.getKindOfCrossing() == Crossover.UNIFORM) {
            data.UniformCrossover(results);
        } else if (data.getKindOfCrossing() == Crossover.ONE_POINT) {
            data.onePointCrossover(results);
        } else if (data.getKindOfCrossing() == Crossover.TWO_POINT) {
            data.twoPointCrossover(results);
        }
        if (data.getProbabilityOfMutation() > 0) {
            data.Mutation(results);
        }
        data.rouletteSelection();
        show_generation();
        generation++;
        if (generation > data.getNumberOfGeneration()) ifStarted = false;
    }

    private boolean checkIfShouldSplitBetweenGenerationsAndReturnIfCanGoOn() {
        if ((!ifStarted) || generation > data.getNumberOfGeneration()) return false;
        if(splitBetweenGenerationsRadioButton.isSelected()) results.setText("Generation: " + generation + "\n");
        else results.appendText("Generation: " + generation + "\n");
        return true;
    }

    private void lastGenerationButtonClicked() {

        while (generation <= data.getNumberOfGeneration()) {
            if(!ifStarted) break;
            nextGenerationButtonClicked();
        }
    }

    private void startButtonClicked() {
        if(!ifStarted){
            ifStarted = true;
            generation = 0;
            data.makeFirstGeneration();
            results.clear();
            show_generation();
            generation++;
        }
    }

    void setData(Data aDataWithParameters) {
        data = aDataWithParameters;
    }

    void show_generation() {
        results.appendText("Generation: " + generation);
        results.appendText("\n");
        int x = 1;
        int y = 2 * data.getLengthOfBinaryTheGreatestWord();
        for (Individual ind1 : data.getDane()) {
            results.appendText("Osobnik: " + x + "  " + ind1.in + "  " + "X=" + ind1.x + "  " + "Y=" + ind1.y + "  " + "Adaptation=" + ind1.adaptation + "\n");
            x++;
        }
        results.appendText("\n");
    }

    Data getData() {
        return data;
    }
}
