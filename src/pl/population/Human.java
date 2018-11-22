package pl.population;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import pl.board.Board;
import pl.board.City;
import pl.board.District;
import pl.board.Intersection;
import pl.board.Place;
import pl.board.Road;

/**
 * Describes single human in game
 *
 * @author Krzysztof Olejniczak
 */
public abstract class Human {

    /**
     * Time to suspend thread in case human is in the city, in miliseconds
     */
    protected static final int TIME_TO_SLEEP_IN_TOWN = 250;

    /**
     * Time to suspend thread in case human is stopped while travelling, or game
     * is stopped, in miliseconds
     */
    protected static final int TIME_TO_SLEEP_ON_STOP = 250;

    /**
     * Time before dispose human in case it is killed, in miliseconds
     */
    protected static final int TIME_TO_SLEEP_AFTER_BEING_KILLED = 1000;

    private static final double MOVEMENT_NUMBER_OF_STEPS = 25.0;
    private static final int MOVEMENT_TIME = 500;

    /**
     * Possible states of human
     */
    public enum State {
        IN_CITY,
        TRAVELING,
        FIGHT;
    }

    private final String name;
    private BufferedImage image;
    private State state;
    private boolean killed;

    private Place location;
    private City travelTo;
    private int x, y;

    private javax.swing.JFrame infoWindow;

    /**
     * Reference to game's board
     */
    protected final Board board;

    /**
     * Reference to game's whole population object
     */
    protected final Population population;

    /**
     * Sets the fields of human
     *
     * @param name human's name
     * @param image human's image displayed on screen
     * @param state human's initial state
     * @param location human's initial location
     * @param travelTo human's initial location
     * @param board reference to game's board
     * @param population reference to game's whole population object
     */
    public Human(String name, BufferedImage image, State state, Place location,
            City travelTo, Board board, Population population) {
        this.name = name;
        this.image = image;
        this.state = state;
        this.killed = false;

        this.x = location.getX();
        this.y = location.getY();
        this.location = location;
        location.setOnSpot(this);
        this.travelTo = travelTo;

        this.board = board;
        this.population = population;
    }

    /**
     * Simulates one field travel of human
     *
     * @return destination location after step
     */
    protected Place travel() {
        Place departure = null;
        if (this.getLocation() instanceof District) {
            departure = ((District) this.getLocation()).getCity().getDeparture();
        } else if (this.getLocation() instanceof Intersection) {
            departure = ((Intersection) this.getLocation())
                    .getProperDeparture(this.getTravelTo());
        } else if (this.getLocation() instanceof Road) {
            departure = ((Road) this.getLocation()).getDeparture();
        }

        if (departure.getOnSpot() != null) {
            try {
                Thread.sleep(TIME_TO_SLEEP_ON_STOP);
            } catch (InterruptedException ex) {
                Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
            }
            return this.getLocation();
        }

        if (departure instanceof City) {
            if (departure != this.getTravelTo()) {
                return this.movement(this.getLocation(),
                        ((City) departure).getDeparture());
            }
            District district = ((City) departure).findEmptyNeighbourhood();
            if (district != null) {
                this.setState(State.IN_CITY);
                return this.movement(this.getLocation(), district);
            }
            return this.getLocation();
        }
        return this.movement(this.getLocation(), departure);
    }

    /**
     * *
     * Simulates slow movement by setting next human locations
     *
     * @param xInitial initial coordinate X
     * @param yInitial initial coordinate Y
     * @param xTarget target coordinate X
     * @param yTarget target coordinate Y
     */
    private void slowMovement(int xInitial, int yInitial, int xTarget, int yTarget) {
        double stepX = (xTarget - xInitial) / MOVEMENT_NUMBER_OF_STEPS,
                stepY = (yTarget - yInitial) / MOVEMENT_NUMBER_OF_STEPS;
        for (int i = 1; i <= MOVEMENT_NUMBER_OF_STEPS; i++) {
            this.setCoordinates((int) ((xInitial + i * stepX)), (int) ((yInitial + i * stepY)));

            try {
                Thread.sleep((long) (MOVEMENT_TIME / MOVEMENT_NUMBER_OF_STEPS));
            } catch (InterruptedException ex) {
                Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            Thread.sleep((long) (MOVEMENT_TIME / MOVEMENT_NUMBER_OF_STEPS));
        } catch (InterruptedException ex) {
            Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Moves human around the board
     *
     * @param from initial place of human
     * @param to target place of human
     * @return destination place
     */
    protected Place movement(Place from, Place to) {
        to.setOnSpot(this);
        this.slowMovement(from.getX(), from.getY(), to.getX(), to.getY());
        this.setLocation(to);
        from.setEmpty(this);
        return to;
    }

    /**
     *
     * @return human's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets image of human
     *
     * @return image of human
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Sets image of human
     *
     * @param obrazek new image of human
     */
    public void setImage(BufferedImage obrazek) {
        this.image = obrazek;
    }

    /**
     * Gets state of human
     *
     * @return state of human
     */
    public State getState() {
        return state;
    }

    /**
     * *
     * Sets state of human
     *
     * @param state new state of human
     */
    public void setState(State state) {
        this.state = state;
    }

    /**
     * Kills human
     */
    protected void kill() {
        this.killed = true;
    }

    /**
     * Checks if human is killed
     *
     * @return true if human is killed, false otherwise
     */
    public boolean isKilled() {
        return this.killed;
    }

    /**
     * Gets actual location of human on the board
     *
     * @return location of human on the board
     */
    public Place getLocation() {
        return location;
    }

    /**
     * Sets location of human on the board
     *
     * @param location location of human on the board
     */
    public void setLocation(Place location) {
        this.location = location;
    }

    /**
     * Gets city of travel destination
     *
     * @return city of travel destination
     */
    public City getTravelTo() {
        return this.travelTo;
    }

    /**
     * Sets city of travel destination
     *
     * @param destination city of new travel destination
     */
    public void setTravelTo(City destination) {
        this.travelTo = destination;
    }

    /**
     * Gets X coordinate of human location in the board
     *
     * @return X coordinate of human location in the board
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets Y coordinate of human in the board
     *
     * @return Y coordinate of human in the board
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets coordinates of human in the board
     *
     * @param x X coordinate of human in the board
     * @param y Y coordinate of human in the board
     */
    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets infoWindow of human if exists
     *
     * @return infoWindow of human if exists, null otherwise
     */
    public JFrame getInfoWindow() {
        return infoWindow;
    }

    /**
     * Sets launched infoWindow of human
     *
     * @param infoWindow launched infoWindow of human
     */
    public void setInfoWindow(JFrame infoWindow) {
        this.infoWindow = infoWindow;
    }
}
