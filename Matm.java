public class Matm {
    int nDig;
    public Matm(int nd) {
        nDig = nd;
    }
    
    public double redondear(double val) {
        return Math.round(val * Math.pow(10, nDig)) / Math.pow(10, nDig);
    }
    
    @SuppressWarnings("empty-statement")
    public boolean fprimo(int n) {
       if (n < 4) return true;
       if (n % 2 == 0) return false;
       int divisor;
       for (divisor = 3; n % divisor != 0; divisor += 2);
       return (divisor >= n);
    }

}
