package pl.board;

/**
 * *
 * Describes single city unit
 *
 * @author Krzysztof Olejniczak
 */
public class District extends Place {

    private City city;

    /**
     * Constructor of district
     *
     * @param city city that this district belongs to
     * @param coordX X coordinate on Board
     * @param coordY Y coordinate on Board
     * @param pixelsPerField number of pixels per field side length
     */
    public District(City city, int coordX, int coordY, int pixelsPerField) {
        super(coordX * pixelsPerField, coordY * pixelsPerField);
        this.city = city;
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public District() {
    }

    /**
     * Gets city that this district belongs to
     *
     * @return city that this district belongs to
     */
    public City getCity() {
        return this.city;
    }

    /**
     * Sets city that this district belongs to
     *
     * @param city city that this district belongs to
     */
    public void setCity(City city) {
        this.city = city;
    }

}
