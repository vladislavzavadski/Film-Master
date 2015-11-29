package sample;

import java.util.ArrayList;

public class Controller {
    private Model model;
    private MainForm form;
    public Controller(Model model, MainForm form){
        this.model = model;
        this.form = form;
    }
    public void processRequest(String request){
        RequestProcessor re = RequestProcessor.getInstance();
        re.setRequest(request);
        re.setController(this);
        re.setRequestType(RequestType.Search);
        re.start();
    }
    public void end(ArrayList<Film> films){
        model.setFilms(films);
        form.update();
    }
}
