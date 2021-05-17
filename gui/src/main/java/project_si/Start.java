package project_si;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Start extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("fxml/mainWindow.fxml")));
        stage.setTitle("Project_SI");
        stage.setScene(scene);
        stage.show();
    }
}
