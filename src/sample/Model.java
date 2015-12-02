package sample;

import java.util.ArrayList;

/**
 * Created by Владислав on 27.11.2015.
 */
public class Model {
    private ArrayList<Film> films;
    public void setFilms(ArrayList<Film> films){
        this.films = films;
    }
    public Film getFilm(int index){
        return films.get(index);
    }
    public int getArraySize(){
        return films.size();
    }
}
