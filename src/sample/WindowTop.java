package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



/**
 * Created by Владислав on 18.10.2015.
 */
public class WindowTop extends StackPane {
    private TextField searchField;
    private Button searchButton;
    private ComboBox<String> genreBox;
    private Rectangle rect;
    public WindowTop(){
        searchField = new TextField();
        rect = new Rectangle(800, 70, Color.WHITE);
        searchField.setPromptText("Enter film name...");
        searchButton = new Button("Search");
        genreBox = new ComboBox<>();
        HBox searchFieldAndButton = new HBox(10);
        genreBox.setPromptText("Genre");
        genreBox.setItems(FXCollections.observableArrayList("All...", "Horror", "Action", "Comedy", "Cartoon"));
        searchFieldAndButton.setTranslateX(10);
        searchFieldAndButton.setTranslateY(20);
        searchFieldAndButton.getChildren().addAll(searchField, searchButton);
        genreBox.setTranslateX(290);
        genreBox.setTranslateY(0);
        getChildren().addAll(rect,searchFieldAndButton);
        getChildren().addAll(genreBox);
        searchButton.setOnMouseClicked(event -> {
            RequestProcessor re = RequestProcessor.getInstance();
            re.setRequest("Martian");
            re.setRequestType(RequestType.Search);
            re.start();
        });
    }
}
