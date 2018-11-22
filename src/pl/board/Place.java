package pl.board;

import pl.population.Human;

/**
 * *
 * Represents single place on board
 *
 * @author Krzysztof Olejniczak
 */
public abstract class Place {

    private int x, y;
    private Human onSpot;

    /**
     * Place constructor
     *
     * @param x X coordinate of its position on screen
     * @param y Y coordinate of its position on screen
     */
    public Place(int x, int y) {
        this.x = x;
        this.y = y;
        this.onSpot = null;
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public Place() {
    }

    /**
     * Sets place empty if human standing on this place is the same as argument
     *
     * @param human human probably standing on this place
     */
    public void setEmpty(Human human) {
        if (this.onSpot == human) {
            this.onSpot = null;
        }
    }

    /**
     * Gets X coordinate
     *
     * @return X coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Sets X coordinate
     *
     * @param x new X coordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets Y coordinate
     *
     * @return Y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Sets Y coordinate
     *
     * @param y new Y coordinate
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets human actually standing on this place
     *
     * @return human actually standing on this place or null if nobody standing
     */
    public Human getOnSpot() {
        return onSpot;
    }

    /**
     * Sets human standing on this place
     *
     * @param onSpot human standing on this place
     */
    public void setOnSpot(Human onSpot) {
        this.onSpot = onSpot;
    }

}
