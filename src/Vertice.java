public class Vertice {

    private int number;
    private int degree;

    public Vertice(int n){
        this.number = n;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public int getNumber() {
        return number;
    }

    public int getDegree() {
        return degree;
    }
}
