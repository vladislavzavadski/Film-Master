package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.InetAddress;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        MainForm root = new MainForm();
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