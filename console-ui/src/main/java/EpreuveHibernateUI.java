import com.mycompany.tennis.controller.EpreuveControllerHibernate;

public class EpreuveHibernateUI {

    public static void main(String[] args) {

        EpreuveControllerHibernate controller = new EpreuveControllerHibernate();
        //controller.afficherListEpreuve();
        controller.afficherDetailleeEpreuveHibernate();
        //controller.afficherEpreuveHibernateRolangaros();
    }
}
