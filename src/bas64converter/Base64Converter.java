/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bas64converter;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Simple main JavaFX GUI.
 * @author Pan-Kawia
 */
public class Base64Converter extends Application {
    
    /**
     * Base settings for JavaFX GUI.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        Button saveEncoded = new Button();
        saveEncoded.setText("Encode file");
        saveEncoded.setOnAction( (ActionEvent e) -> {
            try {
                Base64FX.EncoderWithDialog(primaryStage);
            } catch (IOException ex) {
                System.out.println( ex.getMessage() );
            }
        });
        
        Button saveDecoded = new Button();
        saveDecoded.setText("Decode file");
        saveDecoded.setOnAction( (ActionEvent e ) -> {
            try {
                Base64FX.DecoderWithDialog(primaryStage);
            } catch (IOException ex) {
                ex.getMessage();
            }
        });
        
        Button aboutBtn = new Button();
        aboutBtn.setText("About");
        aboutBtn.setOnAction( (ActionEvent e ) -> {
            /*Stage stage = new Stage();
            Parent root = FXMLLoader.load(
                YourClassController.class.getResource("YourClass.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("My modal window");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                ((Node)event.getSource()).getScene().getWindow() );
            stage.show(); */
            
            Stage dialog = new Stage();
            Text text = new Text(10, 40, "https://github.com/PanKawia");
            Scene info = new Scene(new Group(text));
            dialog.setMaxWidth(400);
            dialog.setMaxHeight(100);
            dialog.setMinWidth(400);
            dialog.setMinHeight(100);
            dialog.setTitle("About");
            dialog.setScene(info);
            dialog.initOwner(primaryStage);
            dialog.initModality(Modality.APPLICATION_MODAL); 
            dialog.showAndWait();
        });
        
        StackPane rootPane = new StackPane();
        Pane menuBar = new Pane();

        HBox hbox = new HBox();
        hbox.getChildren().addAll( saveEncoded, saveDecoded, aboutBtn );
        hbox.setSpacing(20);
        hbox.setPadding( new Insets(20, 20, 20, 20) );
        
        menuBar.getChildren().addAll(hbox);
        rootPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootPane);
        
        primaryStage.setTitle("BASE64 ENCODER/DECODER");
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(120);
        primaryStage.setMaxHeight(120);
        primaryStage.setMinWidth(500);
        primaryStage.setMaxWidth(500);
        primaryStage.show();
    }

    /**
     * Main program.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
 
}

