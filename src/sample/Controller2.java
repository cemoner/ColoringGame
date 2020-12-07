package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @FXML
    private TextField textField;

    @FXML
    private Button submit;

    @FXML
    private Label pointLabel;

    @FXML
    private Pane pane;

    String[] colors = {"-fx-background-color: Red", "-fx-background-color: Blue", "-fx-background-color: Purple",
            "-fx-background-color: Yellow", "-fx-background-color: Green", "-fx-background-color: Brown",
            "-fx-background-color: White", "-fx-background-color: Orange", "-fx-background-color: Black"};

    List<String> colorNames = new ArrayList<>();
    Integer counter = 0;
    Integer trueCounter = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGame();
    }

    public void startGame() {
        if (colorNames.isEmpty()) {
            Collections.addAll(colorNames, colors);
        }
        generateColors();
        submit.setOnAction(e -> {
            game();
            textField.clear();
        });
        pointLabel.setFont(Font.font(20));

    }

    public void game(){
        if(("-fx-background-color: " + textField.getText()).equals(pane.getStyle()) && counter <= 9){
            trueCounter += 1;
            pointLabel.setText("Points: " + trueCounter);
        }
        if (!colorNames.isEmpty()) {
            generateColors();
        }
        else{
            Button restart = new Button("Restart");
            Button exit = new Button("Exit");

            restart.setDisable(false);
            Pane pane = new Pane();
            Stage stage = new Stage();
            Text text = new Text();
            Text text1 = new Text();

            restart.setOnAction(e -> {
                stage.close();
                Main.stage.setScene(Main.main);
                restart();
            });

            exit.setOnAction(e -> {
                stage.close();
                Main.stage.close();
            });

            text.setText("GAME OVER!");
            text.setTranslateX(100);
            text.setTranslateY(30);

            text1.setText(pointLabel.getText() +"/9 times");
            text1.setTranslateX(100);
            text1.setTranslateY(50);

            restart.setTranslateX(90);
            restart.setTranslateY(60);
            restart.prefHeight(70);
            restart.prefWidth(70);

            exit.setTranslateY(60);
            exit.setTranslateX(150);
            exit.prefHeight(70);
            exit.prefWidth(70);

            pane.getChildren().addAll(text,text1,restart,exit);


            Scene scene = new Scene(pane,300,100);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void restart(){
        Collections.addAll(colorNames, colors);
        trueCounter = 0;
        counter = 0;
        pointLabel.setText("Points: " + trueCounter);
    }

    public void generateColors(){
        int randomNameIndex = (int) (Math.random() * colorNames.size());
        String color = colorNames.get(randomNameIndex);
        colorNames.remove(randomNameIndex);
        pane.setStyle(color);

    }

}
