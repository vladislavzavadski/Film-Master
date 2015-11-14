package sample;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Владислав on 14.11.2015.
 */
public class Film {
    private String pathToImage;
    private String filmName;
    private String premierDate;
    private String budget;
    private String revenue;
    private String overview;
    private double rating;
    private ObservableList<String> producedComp;
    private ArrayList<String> genre;
    public Film() {

    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setPremierDate(String premierDate) {
        this.premierDate = premierDate;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setProducedComp(ObservableList<String> producedComp) {
        this.producedComp = producedComp;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getPremierDate() {
        return premierDate;
    }

    public String getBudget() {
        return budget;
    }

    public String getRevenue() {
        return revenue;
    }

    public String getOverview() {
        return overview;
    }

    public double getRating() {
        return rating;
    }

    public ObservableList<String> getProducedComp() {
        return producedComp;
    }
}
