package movement;

public class Suradnice {
    private int x;
    private int y;

    public Suradnice() {
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void konvertujPlohu(int x , int y, int rozmer) {
        this.x = x / rozmer;
        this.y = y / rozmer;
    }
}
