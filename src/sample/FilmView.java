package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ��������� on 18.10.2015.
 */
public class FilmView extends Group {
    private ImageView coverPage;
    private ListView actorList;
    private TextArea textArea;
    private FilmRating filmRating;
      public FilmView(Film film) throws IOException {
        Image image = getImage(film.getPathToImage());
        Text filmNameText = new Text("Film name: ");
        Text premierDateText = new Text("Premier date: ");
        Text premierDate = new Text(film.getPremierDate());
        Text FilmName = new Text(film.getFilmName());
        Text budgetText = new Text("Budget: ");
        Text revenueText = new Text("Revenue: ");
        Text revenueView = new Text("$"+film.getRevenue());
        Text genreText = new Text(" Genre: ");
        Text budgetView = new Text("$"+film.getBudget());
        Text producedText = new Text("Produced by: ");
        ListView genre = new ListView(getGenres(film.getGenre()));
        genre.setOrientation(Orientation.HORIZONTAL);
        textArea = new TextArea();
        textArea.setMaxWidth(300);
        textArea.setMaxHeight(207);
        textArea.setTranslateX(390);
        textArea.setTranslateY(130);
        textArea.setEditable(false);
        textArea.setFont(textArea.getFont().font(14));
        textArea.setText(getStringWithEnter(film.getOverview()));
        filmNameText.setFill(Color.BLACK);
        filmNameText.setFont(filmNameText.getFont().font(20));
        FilmName.setFont(filmNameText.getFont().font(20));
        FilmName.setFill(Color.BLACK);
        premierDateText.setFont(premierDateText.getFont().font(20));
        premierDate.setFont(premierDate.getFont().font(20));
        budgetText.setFont(budgetText.getFont().font(20));
        budgetView.setFont(budgetView.getFont().font(20));
        revenueText.setFont(revenueText.getFont().font(20));
        revenueView.setFont(revenueView.getFont().font(20));
        genreText.setFont(genreText.getFont().font(20));
        producedText.setFont(producedText.getFont().font(20));
        genre.setMaxWidth(200);
        genre.setMaxHeight(35);
        actorList = new ListView(getProdComps(film.getProducedComp()));
        actorList.setMaxWidth(200);
        actorList.setMaxHeight(205);
        coverPage = new ImageView(image);
        filmRating = new FilmRating(5);
        coverPage.setFitWidth(265);
        coverPage.setFitHeight(358);
        coverPage.setTranslateY(100);
        coverPage.setTranslateX(20);
        HBox name = new HBox(0);
        HBox date = new HBox(0);
        name.getChildren().addAll(filmNameText, FilmName);
        name.setTranslateY(100);
        name.setTranslateX(300);
        date.getChildren().addAll(premierDateText, premierDate);
        date.setTranslateY(125);
        date.setTranslateX(300);
        HBox Genre = new HBox(0);
        Genre.getChildren().addAll(genreText, genre);
        Genre.setTranslateX(295);
        Genre.setTranslateY(155);
        VBox img = new VBox(10);
        HBox budget = new HBox(0);
        budget.getChildren().addAll(budgetText, budgetView);
        img.getChildren().addAll(coverPage, filmRating);
        filmRating.setTranslateY(100);
        budget.setTranslateY(185);
        budget.setTranslateX(300);
        HBox revenue = new HBox(10);
        revenue.getChildren().addAll(revenueText, revenueView);
        revenue.setTranslateX(300);
        revenue.setTranslateY(210);
        VBox produced = new VBox(0);
        produced.getChildren().addAll(producedText, actorList);
        produced.setTranslateY(230);
        produced.setTranslateX(300);
        textArea.setTranslateX(500);
        textArea.setTranslateY(255);
        textArea.setMaxWidth(280);
        getChildren().addAll(img,   name, date, Genre, budget, revenue, produced, textArea);
    }
    private String getStringWithEnter(String recense){
        String[] strArr = recense.split("\\.");
        String result = "";
        for(String x: strArr){
            result+=(x+".\n");
        }
        return result;
    }
    private Image getImage(String path){
        if(!path.equals("")) {
            BufferedInputStream inputStream = null;
            try {
                inputStream = new BufferedInputStream(new URL(path).openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream("resourse/image.jpg");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            byte data[] = new byte[1024];
            int count;
            try {
                while ((count = inputStream.read(data, 0, 1024)) != -1) {
                    fileOutputStream.write(data, 0, count);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File("resourse/image.jpg");
            return new Image(file.toURI().toString());
        }
        else{
            File file = new File("resourse/no-image-available.jpg");
            return new Image(file.toURI().toString());
        }
    }
    private ObservableList<String> getGenres(ArrayList<String> genr){
        ObservableList<String> items = FXCollections.observableArrayList();
        for(String x: genr){
            items.add(x);
        }
        return items;
    }

    private ObservableList<String> getProdComps(ArrayList<String> comps){
        ObservableList<String> items = FXCollections.observableArrayList();
        for(String x: comps){
            items.add(x);
        }
        return items;
    }
}
