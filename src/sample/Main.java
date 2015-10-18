package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        FilmView filmView = new FilmView("Марсианин", "https://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg", "18/10/2015", "Владислав Завадский", FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App", "Family App", "Family App", "Family App", "Family App", "Family App", "Family App", "Family App", "Family App", "Family App"));
        root.getChildren().addAll(filmView);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setTitle("Film master");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}