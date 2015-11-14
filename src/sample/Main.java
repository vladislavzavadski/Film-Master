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

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Separator separatorLine = new Separator();
        ScrollBar sb = new ScrollBar();
        separatorLine.setOrientation(Orientation.HORIZONTAL);
        separatorLine.setMinWidth(800);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setTranslateX(780);
        sb.setTranslateY(70);
        sb.setMinHeight(500);
        sb.setMin(0);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(180);
        sb.setMax((480)*3);
        separatorLine.setTranslateY(70);
        String recense = "Марсианская миссия «Арес-3» в процессе работы была вынуждена экстренно покинуть планету из-за надвигающейся песчаной бури. Инженер и биолог Марк Уотни получил повреждение скафандра во время песчаной бури. Сотрудники миссии, посчитав его погибшим, эвакуировались с планеты, оставив Марка одного. Очнувшись, Уотни обнаруживает, что связь с Землёй отсутствует, но при этом полностью функционирует автономный Дом. Главный герой начинает искать способ продержаться на имеющихся запасах еды, витаминов, воды и воздуха ещё 4 года до прилёта следующей миссии «Арес-4». В это время на Земле NASA ищет способ как можно скорее отправить спасательную миссию на красную планету.";
        FilmView filmView = new FilmView("Марсианин", "https://image.tmdb.org/t/p/w185/sQdalmBSUiaU0QCgZKBfy0l2vUR.jpg", "18/10/2015", "100000000", "100000000",recense, FXCollections.observableArrayList(
                "Single", "Double", "Suite"), 9);
        WindowTop top = new WindowTop();
        VBox films = new VBox(40);
        films.getChildren().addAll(filmView, new FilmView("Марсианин", "https://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg", "18/10/2015", "100000000", "100000000",recense, FXCollections.observableArrayList(
                "Single", "Double", "Suite"), 4), new FilmView("Белорусианин", "https://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg", "18/10/2015", "100000000", "100000000",recense, FXCollections.observableArrayList(
                "Single", "Double", "Suite"), 4), new FilmView("Марсианин", "https://image.tmdb.org/t/p/w185/al0BqCrBAeMaWflvFKt3mZzTvXS.jpg", "18/10/2015", "100000000", "100000000",recense, FXCollections.observableArrayList(
                "Single", "Double", "Suite"), 4));
        root.getChildren().addAll(films, separatorLine, sb, top);
        Scene scene = new Scene(root);
        stage.setResizable(false);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setTitle("Film master");
        stage.setScene(scene);
        stage.show();
        System.out.print(0b10);
        sb.valueProperty().addListener((ov, old_val, new_val) -> {
            films.setLayoutY(-new_val.doubleValue());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}