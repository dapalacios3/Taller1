import java.util.Calendar;
public class CFechaHora {
     // Metodos:
    public String getFecha() {
        Calendar cl = Calendar.getInstance();
        return cl.get(cl.DAY_OF_MONTH) + "/" + (cl.get(cl.MONTH) + 1) + "/" +  cl.get(cl.YEAR);
    }

    public String getHora() {
        Calendar cl = Calendar.getInstance();
        return cl.get(cl.HOUR) + ":" + cl.get(cl.MINUTE) + ":" +  cl.get(cl.SECOND);
    }
    
    public String getFechaLarga() {
        return getFecha() + " " + getHora();
    }

}
