package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Separator separatorLine = new Separator();
        separatorLine.setOrientation(Orientation.HORIZONTAL);
        separatorLine.setMaxHeight(1000);
        separatorLine.setMaxWidth(500);

        separatorLine.setTranslateX(100);
        separatorLine.setTranslateY(50);
        String recense = "Марсианская миссия «Арес-3» в процессе работы была вынуждена экстренно покинуть планету из-за надвигающейся песчаной бури. Инженер и биолог Марк Уотни получил повреждение скафандра во время песчаной бури. Сотрудники миссии, посчитав его погибшим, эвакуировались с планеты, оставив Марка одного. Очнувшись, Уотни обнаруживает, что связь с Землёй отсутствует, но при этом полностью функционирует автономный Дом. Главный герой начинает искать способ продержаться на имеющихся запасах еды, витаминов, воды и воздуха ещё 4 года до прилёта следующей миссии «Арес-4». В это время на Земле NASA ищет способ как можно скорее отправить спасательную миссию на красную планету.";
        FilmView filmView = new FilmView("Марсианин", "https://image.tmdb.org/t/p/w185/AjbENYG3b8lhYSkdrWwlhVLRPKR.jpg", "18/10/2015", "Владислав Завадский",recense, FXCollections.observableArrayList(
                "Single", "Double", "Suite"), 4);
        WindowTop top = new WindowTop();
        VBox vBox = new VBox(0);
        vBox.getChildren().addAll(top,separatorLine,filmView);
        root.getChildren().addAll(vBox);
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