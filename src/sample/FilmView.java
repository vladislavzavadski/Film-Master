package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
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
public class FilmView extends StackPane {
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
        Text revenueText = new Text(" Revenue: ");
        Text revenueView = new Text("$"+film.getRevenue());
        Text genreText = new Text(" Genre: ");
        Text budgetView = new Text("$"+film.getBudget());
        Text producedText = new Text("Produced by: ");
        Text ratingText = new Text("Rating: ");
        ListView genre = new ListView(getGenres(film.getGenre()));
        genre.setOrientation(Orientation.HORIZONTAL);
        HBox filmNameSpace = new HBox(0);
        HBox premierDateSpace = new HBox(0);
        HBox budgetSpace = new HBox(0);
        HBox nameDateInfo = new HBox(30);
        HBox ratingSpace = new HBox(0);
        VBox nameDateProducerInfo = new VBox(20);
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
      //  genre.setFont(genre.getFont().font(20));
        premierDateText.setFont(premierDateText.getFont().font(20));
        premierDate.setFont(premierDate.getFont().font(20));
        budgetText.setFont(budgetText.getFont().font(20));
        budgetView.setFont(budgetView.getFont().font(20));
        revenueText.setFont(revenueText.getFont().font(20));
        revenueView.setFont(revenueView.getFont().font(20));
        genreText.setFont(genreText.getFont().font(20));
        producedText.setFont(producedText.getFont().font(20));
        ratingText.setFont(ratingText.getFont().font(30));
        filmNameSpace.getChildren().addAll(filmNameText, FilmName);
        premierDateSpace.getChildren().addAll(premierDateText, premierDate);
        budgetSpace.getChildren().addAll(budgetText, budgetView, revenueText, revenueView);
        nameDateInfo.getChildren().addAll(filmNameSpace, premierDateSpace);

        genre.setMaxWidth(200);
        genre.setMaxHeight(35);
        actorList = new ListView(getProdComps(film.getProducedComp()));
        actorList.setMaxWidth(200);
        actorList.setMaxHeight(205);
        nameDateProducerInfo.getChildren().addAll(nameDateInfo, genreText, producedText, actorList);
        ratingSpace.getChildren().addAll(ratingText, new FilmRating(film.getRating()));
        coverPage = new ImageView(image);
        filmRating = new FilmRating(5);
        coverPage.setFitWidth(image.getWidth()+80);
        coverPage.setFitHeight(image.getHeight() + 80);
        VBox ratingImage = new VBox(20);
        ratingImage.getChildren().addAll(coverPage, ratingSpace);
        ratingImage.setTranslateY(95);
        nameDateProducerInfo.setTranslateY(106);
        nameDateProducerInfo.setTranslateX(270);
        budgetSpace.setTranslateY(150);
        budgetSpace.setTranslateX(400);
        genre.setTranslateX(240);
        genre.setTranslateY(-54);
        this.getChildren().addAll(ratingImage,nameDateProducerInfo, textArea, genre, budgetSpace);
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
