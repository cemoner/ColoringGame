package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    @FXML
    public Button button;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;

    @FXML
    private Button button9;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;


    @FXML
    private Label nameLabel;

    @FXML
    private Label pointLabel;


    String[] colorNames = {"Red", "Blue", "Purple", "Yellow", "Green", "Brown", "White", "Orange", "Black"};
    List<String> colorNamesList = new ArrayList<>();
    String buttonName = "";
    Integer trueCounter = 0;
    Integer clickCounter = 0;

    public void generateColors() {
        String[] colors = {"-fx-background-color: Red", "-fx-background-color: Blue", "-fx-background-color: Purple",
                "-fx-background-color: Yellow", "-fx-background-color: Green", "-fx-background-color: Brown",
                "-fx-background-color: White", "-fx-background-color: Orange", "-fx-background-color: Black"};

        List<String> colorsList = new ArrayList<>();
        Collections.addAll(colorsList, colors);
        Button[] buttons = {button, button2, button3, button4, button5, button6, button7, button8, button9};
        for (int i = 9; i >= 1; i--) {
            int randomColor = (int) (Math.random() * (i));
            buttons[i - 1].setStyle(colorsList.get(randomColor));
            colorsList.remove(randomColor);
        }
    }

    public void startGame() {
        duplicate(button, button2, button3, button4);
        duplicate(button5, button6, button7, button8);
        button9.setOnAction(e -> {
            if(clickCounter <= 9){
                buttonName = button9.getStyle();
                clickCounter += 1;
                game();
            }
        });
        nameLabel.setFont(Font.font(20));
        pointLabel.setFont(Font.font(20));
        if(colorNamesList.isEmpty()){
            Collections.addAll(colorNamesList, colorNames);
        }
        generateColors();
        nameLabel.setText(generateName());
    }

    private void duplicate(Button button, Button button2, Button button3, Button button4) {
        button.setOnAction(e -> {
            if(clickCounter <= 9){
                buttonName = button.getStyle();
                clickCounter += 1;
                game();
            }
        });

        button2.setOnAction(e -> {
            if(clickCounter <= 9){
                buttonName = button2.getStyle();
                clickCounter += 1;
                game();
            }
        });
        button3.setOnAction(e -> {
            if(clickCounter <= 9){
                buttonName = button3.getStyle();
                clickCounter += 1;
                game();
            }
        });
        button4.setOnAction(e -> {
            if(clickCounter <= 9){
                buttonName = button4.getStyle();
                clickCounter += 1;
                game();
            }
        });
    }

    public void game() {
        if (buttonName.equals("-fx-background-color: " + nameLabel.getText()) && clickCounter <=9) {
            trueCounter += 1;
            pointLabel.setText("Points: " + trueCounter);
        }
        if (!colorNamesList.isEmpty() && !nameLabel.getText().equals("")) {
            generateColors();
            nameLabel.setText(generateName());
        }
        else if(colorNamesList.isEmpty()){
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

    public String generateName() {
        int randomNameIndex = (int) (Math.random() * colorNamesList.size());
        String color = colorNamesList.get(randomNameIndex);
        colorNamesList.remove(randomNameIndex);
        return color;
    }

    public void restart(){
        Collections.addAll(colorNamesList, colorNames);
        pointLabel.setText("Points: 0");
        String buttonName = "";
        nameLabel.setText("");
        trueCounter = 0;
        startGame();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startGame();
    }
}
