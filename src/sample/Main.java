package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    static Stage stage = new Stage();
    static Pane layout = new Pane();
    static Scene main = new Scene(layout,450,450);


    @Override
    public void start(Stage primaryStage){


        stage.setScene(main);
        stage.setTitle("Color Games ");
        layout.setStyle("-fx-background-color: #F08080");
        Text text = new Text();
        text.setText("Choose a game mode");
        text.setTranslateX(125);
        text.setTranslateY(100);
        text.setStyle("-fx-background-color: Black");
        text.setFont(Font.font(20));

        Button mod1 = new Button("Mod 1");
        mod1.setTranslateX(150);
        mod1.setTranslateY(120);
        mod1.setStyle("-fx-background-color: #00BFFF");
        mod1.setPrefHeight(40);
        mod1.setPrefWidth(100);
        mod1.setFont(Font.font(20));

        Button mod2 = new Button("Mod 2");
        mod2.setTranslateX(150);
        mod2.setTranslateY(170);
        mod2.setStyle("-fx-background-color: #00BFFF");
        mod2.setFont(Font.font(20));
        mod2.setPrefHeight(40);
        mod2.setPrefWidth(100);

        layout.getChildren().addAll(text,mod1,mod2);


        mod1.setOnAction(e -> {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                Scene scene = new Scene(root,450,450);
                stage.setScene(scene);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        mod2.setOnAction(e -> {
            try {
                Parent root2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
                Scene scene2 = new Scene(root2,450,450);
                stage.setScene(scene2);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });



        stage.show();
    }


    public static void main(String[] args)  {
        launch(args);
    }
}
