package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.File;

/**
 * Created by Владислав on 18.10.2015.
 */
public class FilmRating extends HBox {
    public FilmRating(double rating){
        super(0);
        File fileFullStar = null;
        File fileHalfStar = null;
        File fileEmptyStar = null;
        int halfStars = (int)(rating+0.5);
        int fullStars;
        int halfStar;
        if(halfStars%2==0){
            fullStars = halfStars/2;
            halfStar = 0;
        }
        else{
            fullStars = (halfStars-1)/2;
            halfStar = 1;
        }
        int emptyStars = 5-(fullStars+halfStar);
        if(fullStars!=0){
            fileFullStar = new File("resourse/starfull.png");
            Image image = new Image(fileFullStar.toURI().toString());
            while(fullStars-->0){
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(image.getHeight()-450);
                imageView.setFitWidth(image.getWidth()-450);
                getChildren().addAll(imageView);
            }
        }
        if(halfStar!=0){
            fileHalfStar = new File("resourse/starhalffull.png");
            Image image = new Image(fileHalfStar.toURI().toString());
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(image.getHeight()-450);
            imageView.setFitWidth(image.getWidth()-450);
            getChildren().addAll(imageView);
        }
        if(emptyStars!=0){
            fileEmptyStar = new File("resourse/starempty.png");
            Image image = new Image(fileEmptyStar.toURI().toString());
            while(emptyStars-->0){
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(image.getHeight()-450);
                imageView.setFitWidth(image.getWidth()-450);
                getChildren().addAll(imageView);
            }
        }
    }
}
