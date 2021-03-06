package sample;

import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Владислав on 14.11.2015.
 */
public class RequestProcessor{
    static RequestProcessor requestProcessor;
    private String request;
    private String requestTemplate = "https://api.themoviedb.org/3/";
    private String api_key = "api_key=99ecd314dd95a3f64880fece25890f58";
    private String response;
    private String[] result;
    private  Thread thread;
    private ArrayList<Film> films;
    private RequestType requestType;
    private Controller controller;
    private RequestProcessor(){}
    public static RequestProcessor getInstance(){
        if(requestProcessor==null){
            requestProcessor =  new RequestProcessor();
        }
        return requestProcessor;
    }
    public void setRequest(String request){
        this.request = request;
    }

    public void setRequestType(RequestType requestType){
        this.requestType = requestType;
    }

    public void setController(Controller controller){this.controller = controller;}
    public void start(){
        films = new ArrayList<>();
        thread = new Thread(){
            public void run() {
                if (requestType == RequestType.Discover) {

                }
                if (requestType == RequestType.Search) {
                    try {
                        response = doGet(requestTemplate + "search/movie?query=" + request + "&" + api_key);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    result = response.split("\\Q},{\\E");
                    if (response.equals("{\"page\":1,\"results\":[],\"total_results\":0,\"total_pages\":1}")) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                controller.end(null);
                            }
                        });
                    } else {
                        for (String film : result) {
                            try {
                                String pathToImage = "https://image.tmdb.org/t/p/w185";
                                int index = film.indexOf("\"poster_path\":\"");
                                if(index!=-1) {
                                    index += 16;
                                    for (int i = index; film.charAt(i) != '\"' && index != -1; i++) {
                                        pathToImage += film.charAt(i);
                                    }
                                }
                                else{
                                    pathToImage = "";
                                }
                                films.add(StringProcessor.getFilmbyDesc(doGet(requestTemplate + "movie/" + StringProcessor.getFilmId(film) + "?" + api_key)).setPathToImage(pathToImage));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                controller.end(films);
                            }
                        });
                    }

                }
            }
        };
        thread.start();
    }
    private String doGet(String request) throws Exception{
       String USER_AGENT = "Mozilla/5.0";
            URL obj = new URL(request);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer resp = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                resp.append(inputLine);
            }
            in.close();

        return resp.toString();
    }

}
