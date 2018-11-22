package pl.board;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import javafx.util.Pair;
import pl.population.Human;

/**
 * *
 * Extension of road with 2 entries and departures
 *
 * @author Krzysztof Olejniczak
 */
public class Intersection extends Road {

    private Place entry2;
    private Place departure2;

    /**
     * Constructor of Intersection. It do not sets entry, departure, reverse
     * way, entry2 and departure2
     *
     * @param coordX X coordinate on Board
     * @param coordY Y coordinate on Board
     * @param pixelsPerField number of pixels per field side length
     */
    public Intersection(int coordX, int coordY, int pixelsPerField) {
        super(coordX, coordY, pixelsPerField);
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public Intersection() {
    }

    /**
     * Gets humans on entry, departure, reverse way, entry2 and departure2
     *
     * @return list of humans around
     */
    @Override
    public List<Human> getHumansAround() {
        List<Human> humans = new LinkedList<>();
        List<Human> humans2 = new LinkedList<>();

        humans.add(this.getEntry().getOnSpot());
        humans.add(this.getDeparture().getOnSpot());
        humans.add(this.getReverseWay().getOnSpot());
        if (this.getDeparture2() != null) {
            humans.add(this.getDeparture2().getOnSpot());
        }
        if (this.getEntry2() != null) {
            humans.add(this.getEntry2().getOnSpot());
        }

        humans.stream().filter((human) -> (human != null)).forEachOrdered((human) -> {
            humans2.add(human);
        });

        return humans2;
    }

    /**
     * *
     * Look for proper departure from intersection towards the right city
     *
     * @param city city of human's destination
     * @return proper departure as Place
     */
    public Place getProperDeparture(City city) {
        Queue<Pair<Place, Road>> roads = new LinkedList<>();
        roads.add(new Pair(this.getDeparture(), (Road) this.getDeparture()));
        roads.add(new Pair(this.departure2, (Road) this.departure2));

        while (!roads.isEmpty()) {
            Pair pair = roads.poll();
            if (pair.getKey() == null) {
                continue;
            }
            if (pair.getKey() instanceof City) {
                if ((City) pair.getKey() == city) {
                    return (Place) pair.getValue();
                }
            } else if (pair.getKey() instanceof Intersection) {
                Intersection intersection = (Intersection) pair.getKey();
                roads.add(new Pair(intersection.getDeparture(), (Road) pair.getValue()));
                roads.add(new Pair(intersection.getDeparture2(), (Road) pair.getValue()));
            } else {
                Road road = (Road) pair.getKey();
                roads.add(new Pair(road.getDeparture(), (Road) pair.getValue()));
            }
        }
        return null;
    }

    /**
     * *
     * Sets second entry and departure of intersection
     *
     * @param entry2 second entry from which humans can appear on road
     * @param departure2 second departure to which humans can leave from road
     */
    public void setEntry2Departure2(Place entry2, Place departure2) {
        this.entry2 = entry2;
        this.departure2 = departure2;
    }

    /**
     * Gets second entry to intersection
     *
     * @return second entry to intersection
     */
    public Place getEntry2() {
        return entry2;
    }

    /**
     * Sets second entry to intersection
     *
     * @param entry2 second entry to intersection
     */
    public void setEntry2(Place entry2) {
        this.entry2 = entry2;
    }

    /**
     * Gets second departure from intersection
     *
     * @return second departure from intersection
     */
    public Place getDeparture2() {
        return departure2;
    }

    /**
     * Sets second departure from intersection
     *
     * @param departure2 second departure from intersection
     */
    public void setDeparture2(Place departure2) {
        this.departure2 = departure2;
    }

}
