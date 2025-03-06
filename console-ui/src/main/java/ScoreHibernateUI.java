import com.mycompany.tennis.controller.JoueurControllerJdbc;
import com.mycompany.tennis.controller.ScoreControllerHibernate;

public class ScoreHibernateUI {

    public static void main(String[] args) {

        ScoreControllerHibernate scoreController = new ScoreControllerHibernate();
        //scoreController.afficherScoreHibernate();
        scoreController.supprimeScoreHibernate();


    }
}
