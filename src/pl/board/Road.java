package pl.board;

import java.util.LinkedList;
import java.util.List;
import pl.population.Human;

/**
 * *
 * Describes single Road with entry and departure
 *
 * @author Krzysztof Olejniczak
 */
public class Road extends Place {

    private Place entry;
    private Place departure;
    private Road reverseWay;

    /**
     * Constructor of Road. It do not sets entry, departure and reverse way.
     *
     * @param coordX X coordinate on Board
     * @param coordY Y coordinate on Board
     * @param pixelsPerField number of pixels per field side length
     */
    public Road(int coordX, int coordY, int pixelsPerField) {
        super(pixelsPerField * coordX, pixelsPerField * coordY);
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public Road() {
    }

    /**
     * Gets humans on entry, departure and reverse way
     * @return list of humans around
     */
    public List<Human> getHumansAround() {
        List<Human> humans = new LinkedList<>();
        List<Human> humans2 = new LinkedList<>();
        
        humans.add(this.getEntry().getOnSpot());
        humans.add(this.getDeparture().getOnSpot());
        humans.add(this.getReverseWay().getOnSpot());
        
        humans.stream().filter((human) -> (human != null)).forEachOrdered((human) -> {
            humans2.add(human);
        });
        
        return humans2;
    }

    /**
     * *
     * Sets entry, departure and reverse way of road
     *
     * @param entry entry from which humans can appear on road
     * @param departure departure to which humans can leave from road
     * @param reverseWay road opposed to this road
     */
    public void setEntryDepartureReverseWay(Place entry, Place departure, Road reverseWay) {
        this.entry = entry;
        this.departure = departure;
        this.reverseWay = reverseWay;
    }

    /**
     * Gets road entry
     *
     * @return entry from which humans can appear on road
     */
    public Place getEntry() {
        return this.entry;
    }

    /**
     * Sets road entry
     *
     * @param entry entry from which humans can appear on road
     */
    public void setEntry(Place entry) {
        this.entry = entry;
    }

    /**
     * Gets road departure
     *
     * @return departure to which humans can leave from road
     */
    public Place getDeparture() {
        return this.departure;
    }

    /**
     * Sets road departure
     *
     * @param departure departure to which humans can leave from road
     */
    public void setDeparture(Place departure) {
        this.departure = departure;
    }

    /**
     * Gets road reverse way
     *
     * @return road opposed to this road
     */
    public Road getReverseWay() {
        return this.reverseWay;
    }

    /**
     * Sets road reverse way
     *
     * @param reverseWay road opposed to this road
     */
    public void setReverseWay(Road reverseWay) {
        this.reverseWay = reverseWay;
    }
}
