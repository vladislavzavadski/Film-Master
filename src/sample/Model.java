package sample;

import java.util.ArrayList;

/**
 * Created by Владислав on 27.11.2015.
 */
public class Model {
    private ArrayList<Film> films;
    private boolean isDataValid;
    public void setFilms(ArrayList<Film> films){
        this.films = films;
        if(films==null){
            isDataValid = false;
        }
        else {
            isDataValid = true;
            int i;
        }
    }
    public Film getFilm(int index){
        return films.get(index);
    }
    public int getArraySize(){
        return films.size();
    }
    public boolean valid(){return isDataValid;}

}
