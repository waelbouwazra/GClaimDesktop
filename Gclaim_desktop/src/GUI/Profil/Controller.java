package GUI.Profil;
import Services.RdvService;

import Entities.Rdv;
import java.net.URL;
import java.time.YearMonth;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

public class Controller implements Initializable{

    // Get the pane to put the calendar on
    @FXML Pane calendarPane;
    
 
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       RdvService rdv =new RdvService();
FullCalendarView fullCalendarView = new FullCalendarView(YearMonth.now());
List <Rdv> listRdv= rdv.ShowRdv();
if (!listRdv.isEmpty())
{
for (Rdv rdvs :listRdv){
fullCalendarView.highlightDays (rdvs.getDate(), rdvs.getDate());
}}
fullCalendarView.populateCalendar(YearMonth.now() );
calendarPane.getChildren ().add(fullCalendarView.getView() );
    }

}
