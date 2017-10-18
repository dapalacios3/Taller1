package libreriacomun;

import java.util.Scanner;

public class CTrianPas {
    // Atributos:
    private int nFilas;
    
    // Metodos:
    public void lectura() {
        Scanner sc = new Scanner(System.in);
        System.out.println("TRIANGULO DE PASCAL");
        do {
          System.out.print("Ingresar el numero de filas: ");
          nFilas = sc.nextInt();
        }
        while(nFilas < 1 || nFilas > 15);
    }
    
    public void desplegarTriangulo () {
        System.out.println("TRIANGULO DE PASCAL DE " + nFilas + " FILAS:");
        for (int i = 0; i < nFilas; i++ ) {
           for (int j = 0; j <= i; j++ ) 
               System.out.print(combinacion(i, j) + "  ");
           System.out.println();
        }
    }
    
    public int combinacion(int m, int n) {
        return (int) (factorial(m) / factorial(n) / factorial(m -n));
    }
    
    public long factorial(int x) {
        long f = 1;
        int i;
        for (i = 2; i <= x; i ++)
            f = f * i;
        return f;
    }
    
}
