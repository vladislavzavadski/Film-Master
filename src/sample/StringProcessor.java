package sample;

import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * Created by Владислав on 14.11.2015.
 */
public class StringProcessor {//TODO: JSON
    public static String getFilmId(String request){
        String result = "";
        int index = request.indexOf("\"id\":");
        index+=5;
        for(int i=index; request.charAt(i)!=','&&index!=-1; i++){
            result+=request.charAt(i);
        }
        return result;
    }
    public static Film getFilmbyDesc(String request){
        Film result = new Film();
        String pathToImage = "https://image.tmdb.org/t/p/w185";
        String title = "";
        String releaseDate = "";
        String budget = "";
        String revenue = "";
        String overview = "";
        String tempRat = "";
        String tempComp="";
        ArrayList<String> coms;
        ArrayList<String> genres;
        double rating;
        coms = new ArrayList<>();
        genres = new ArrayList<>();
        int index = request.indexOf("\"poster_path\":\"");
        index+=15;
        for(int i=index; request.charAt(i)!='\"'&&index!=-1; i++){
            pathToImage+=request.charAt(i);
        }
        index = request.indexOf("\"title\":\"");
        index+=9;
        for(int i=index; request.charAt(i)!='\"'&&index!=-1; i++){
            title+=request.charAt(i);
        }
        index = request.indexOf("\"release_date\":\"");
        index+=16;
        for(int i=index; request.charAt(i)!='\"'&&index!=-1; i++){
            releaseDate+=request.charAt(i);
        }
        index = request.indexOf("\"budget\":");
        index+=9;
        for(int i=index; request.charAt(i)!=','&&index!=-1; i++){
            budget+=request.charAt(i);
        }
        index = request.indexOf("\"revenue\":");
        index+=10;
        for(int i=index; request.charAt(i)!=','&&index!=-1; i++){
            revenue+=request.charAt(i);
        }
        index = request.indexOf("\"overview\":\"");
        index+=12;
        for(int i=index; request.charAt(i)!='\"'&&index!=-1; i++){
            overview+=request.charAt(i);
        }
        index = request.indexOf("\"vote_average\":");
        index+=15;
        for(int i=index; request.charAt(i)!=','&&index!=-1; i++){
            tempRat+=request.charAt(i);
        }
        rating = Double.parseDouble(tempRat);
        String[] temp = request.split("\"production_companies\":\\[");
        temp = temp[1].split("\\Q],\\E");
        temp = temp[0].split("\\Q{\"name\":\"\\E");
        for(int j=1; j<temp.length; j++){
            for(int i=0; temp[j].charAt(i)!='\"'; i++){
                tempComp+=temp[j].charAt(i);
            }
            coms.add(tempComp);
            tempComp = "";
        }
        temp = request.split("\"genres\":\\[");
        temp = temp[1].split("\\Q],\\E");
        temp = temp[0].split("\\Q\"name\":\"\\E");
        for(int j=1; j<temp.length; j+=1){
            for(int i=0; temp[j].charAt(i)!='\"'; i++){
                tempComp+=temp[j].charAt(i);
            }
            genres.add(tempComp);
            tempComp = "";
        }
        result.setBudget(budget);
        result.setFilmName(title);
        result.setOverview(overview);
        result.setPathToImage(pathToImage);
        result.setPremierDate(releaseDate);
        result.setProducedComp(coms);
        result.setRating(rating);
        result.setRevenue(revenue);
        result.setGenre(genres);
        return result;
    }

}
