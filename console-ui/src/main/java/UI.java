
import com.mycompany.tennis.controller.JoueurControllerHibernate;
import com.mycompany.tennis.controller.JoueurControllerJdbc;
import com.mycompany.tennis.controller.TournoiControllerHibernate;

public class UI {

    public static void main(String[] args) {

//        JoueurControllerJdbc joueurController = new JoueurControllerJdbc();
//        joueurController.afficherJoueur();

        JoueurControllerHibernate joueurControllerHibernate = new JoueurControllerHibernate();
        joueurControllerHibernate.afficherJoueurHibernate();


//        TournoiControllerHibernate tournoiControllerHibernate = new TournoiControllerHibernate();
//        tournoiControllerHibernate.afficherTournoiHibernate();

    }
}
