import java.util.Random;
public class CAleatorios {
    // Atributos    
    private Random rd = new Random();
    
    // Metodos
    public int aleatorio(int m, int n) {
        if (m > n) return 0;
        return Math.abs(rd.nextInt()) % (n - m + 1) + m;
    }
    
}
