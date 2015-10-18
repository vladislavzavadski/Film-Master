package sample;

import javafx.collections.ObservableList;
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

/**
 * Created by Владислав on 18.10.2015.
 */
public class FilmView extends StackPane {
    private ImageView coverPage;
    private ListView actorList;
    private TextArea textArea;
    private FilmRating filmRating;

      public FilmView(String filmName, String pathToImage, String date, String producerName, String recense, ObservableList<String> actorsAndRoles, double rating) throws IOException {
        Image image = getImage(pathToImage);
        Text filmNameText = new Text("Film name: ");
        Text premierDateText = new Text("Premier date: ");
        Text premierDate = new Text(date);
        Text FilmName = new Text(filmName);
        Text producerText = new Text("Producer: ");
        Text producerNameView = new Text(producerName);
        Text starringText = new Text("Starring: ");
        Text ratingText = new Text("Rating: ");
        HBox filmNameSpace = new HBox(0);
        HBox premierDateSpace = new HBox(0);
        HBox producerNameSpace = new HBox(0);
        HBox nameDateInfo = new HBox(30);
        HBox ratingSpace = new HBox(0);
        VBox nameDateProducerInfo = new VBox(20);
        textArea = new TextArea();
        textArea.setMaxWidth(300);
        textArea.setMaxHeight(207);
        textArea.setTranslateX(410);
        textArea.setTranslateY(154);
        textArea.setEditable(false);
        textArea.setFont(textArea.getFont().font(14));

        textArea.setText(getStringWithEnter(recense));
        filmNameText.setFill(Color.BLACK);
        filmNameText.setFont(filmNameText.getFont().font(20));
        FilmName.setFont(filmNameText.getFont().font(20));
        FilmName.setFill(Color.BLACK);
        premierDateText.setFont(premierDateText.getFont().font(20));
        premierDate.setFont(premierDate.getFont().font(20));
        producerText.setFont(producerText.getFont().font(20));
        producerNameView.setFont(producerNameView.getFont().font(20));
        starringText.setFont(starringText.getFont().font(20));
        ratingText.setFont(ratingText.getFont().font(30));
        filmNameSpace.getChildren().addAll(filmNameText, FilmName);
        premierDateSpace.getChildren().addAll(premierDateText, premierDate);
        producerNameSpace.getChildren().addAll(producerText, producerNameView);
        nameDateInfo.getChildren().addAll(filmNameSpace, premierDateSpace);
        actorList = new ListView(actorsAndRoles);
        actorList.setMaxWidth(200);
        actorList.setMaxHeight(205);
        nameDateProducerInfo.getChildren().addAll(nameDateInfo, producerNameSpace, starringText, actorList);
        ratingSpace.getChildren().addAll(ratingText, new FilmRating(rating));
        ratingSpace.setTranslateY(450);
        coverPage = new ImageView(image);
        filmRating = new FilmRating(5);
        coverPage.setTranslateX(-80);
        coverPage.setTranslateY(80);
        coverPage.setFitWidth(image.getWidth()+80);
        coverPage.setFitHeight(image.getHeight() + 80);
        nameDateProducerInfo.setTranslateY(90);
        nameDateProducerInfo.setTranslateX(290);
        this.getChildren().addAll(coverPage, nameDateProducerInfo,  ratingSpace, textArea);
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
}
