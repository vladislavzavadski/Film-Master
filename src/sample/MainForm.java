package sample;

import javafx.application.Platform;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Separator;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Владислав on 24.11.2015.
 */
public class MainForm extends Group {
    private WindowTop windowTop;
    private Separator separatorLine;
    private ScrollBar sb;
    private Image image;
    private Model model;
    private ImageView connectionLost;
    private VBox films;
    private Controller controller;
    private Rectangle rect;
    private ProgressIndicator indicator;
    private boolean is = false;
    public MainForm(){
        model = new Model();
        controller = new Controller(model, this);
        indicator = new ProgressIndicator();
        indicator.setProgress(-1.0f);
        indicator.setTranslateX(350);
        indicator.setTranslateY(250);
        indicator.setMaxSize(200, 200);
        indicator.setMinSize(100, 100);
        windowTop = new WindowTop();
        rect = new Rectangle(800, 600, Color.WHITE);
        rect.setOpacity(0.6);
        try {
            if("127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress().toString())){
                image = new Image(new File("resourse/lost.png").toURI().toString());
                connectionLost = new ImageView(image);
                connectionLost.setTranslateY(140);
                connectionLost.setTranslateX(220);
                getChildren().addAll(connectionLost);
                windowTop.setDisable(true);
                Thread thread = new Thread(){
                  public void run(){
                      try {
                          while("127.0.0.1".equals(InetAddress.getLocalHost().getHostAddress().toString())){

                          }
                          Platform.runLater(new Thread(){
                              @Override
                              public void run(){
                                  getChildren().remove(connectionLost);
                              }


                          });
                          windowTop.setDisable(false);
                       } catch (UnknownHostException e) {
                          e.printStackTrace();
                      }
                  }
                };
                thread.start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        separatorLine = new Separator();
        sb = new ScrollBar();
        separatorLine.setOrientation(Orientation.HORIZONTAL);
        separatorLine.setMinWidth(800);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setTranslateX(780);
        sb.setTranslateY(70);
        sb.setMinHeight(500);
        sb.setMin(0);
        sb.setOrientation(Orientation.VERTICAL);
        sb.setPrefHeight(180);
        sb.setMax((480)*5);

        separatorLine.setTranslateY(70);
        films = new VBox(40);
        getChildren().addAll(separatorLine, windowTop);
        windowTop.getSearchButton().setOnMouseClicked(event -> {
            controller.processRequest(windowTop.getRequest());
            getChildren().addAll(rect, indicator);
        });
        sb.valueProperty().addListener((ov, old_val, new_val) -> {
            films.setLayoutY(-new_val.doubleValue());
        });
    }
    public void update(){

        try {
            films.getChildren().clear();
            if(is){
                getChildren().remove(films);
                getChildren().remove(sb);
            }
            for(int i=0; i<model.getArraySize(); i++) {
                films.getChildren().addAll(new FilmView(model.getFilm(i)));
            }
            sb.setMax(480*(model.getArraySize()-1));
            this.getChildren().remove(windowTop);
            this.getChildren().addAll(films, windowTop);
            getChildren().addAll(sb);
            is = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        getChildren().remove(rect);
        getChildren().remove(indicator);
    }

}
