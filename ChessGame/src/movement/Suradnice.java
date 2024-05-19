package movement;

/**
 * Suradnice class represents class converting clicked coordinates to 2D Array
 */
public class Suradnice {
    private int x;
    private int y;

    /**
     * Constructs class Suradnice
     */
    public Suradnice() {
    }

    /**
     * Returns x position in 2D Array
     * @return x coordinate of click
     */
    public int getX() {
        return this.x;
    }
    /**
     * Returns y position in 2D Array
     * @return y coordinate of click
     */
    public int getY() {
        return this.y;
    }
    /**
     * Converts clicked coordinates to 2D array equivalents
     */
    public void konvertujPlohu(int x , int y, int rozmer) {
        this.x = x / rozmer;
        this.y = y / rozmer;
    }
}
