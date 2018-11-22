package pl.population;

import java.awt.image.BufferedImage;
import pl.board.Board;
import pl.board.City;
import pl.board.Place;
import pl.population.Human.State;

/**
 * Describes single Inhabitant
 *
 * @author Krzysztof Olejniczak
 */
public abstract class Inhabitant extends Human {

    private final String surname;
    private City homeTown;
    private boolean stop;

    /**
     * Sets the fields of Inhabitant
     *
     * @param name Inhabitant's name
     * @param surname Inhabitant's surname
     * @param image Inhabitant's image shown on screen
     * @param location Inhabitant's initial location
     * @param homeTown Inhabitant's initial home town
     * @param board game's board
     * @param population game's population of all humans
     */
    public Inhabitant(String name, String surname, BufferedImage image, Place location,
            City homeTown, Board board, Population population) {
        super(name, image, State.IN_CITY, location, homeTown, board, population);
        this.surname = surname;
        this.homeTown = homeTown;
        this.homeTown.incrementNumberOfCitizens();
        this.stop = false;
    }

    /**
     * Kills this Inhabitant
     */
    public void killInhabitant() {
        this.kill();
        this.homeTown.decrementNumberOfCitizens();
    }

    /**
     * Gets inhabitant's surname
     *
     * @return inhabitant's surname
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Gets inhabitant's home town
     *
     * @return inhabitant's home town
     */
    public City getHomeTown() {
        return this.homeTown;
    }

    /**
     * Sets inhabitant's home town
     *
     * @param homeTown New inhabitant's home town
     */
    public void setHomeTown(City homeTown) {
        this.homeTown = homeTown;
    }

    /**
     * Gets if inhabitant is on stop
     *
     * @return true if inhabitant is on stop, false otherwise
     */
    public boolean isStop() {
        return this.stop;
    }

    /**
     * Sets if inhabitant is on stop
     *
     * @param stop true if inhabitant is on stop, false otherwise
     */
    public void setStop(boolean stop) {
        this.stop = stop;
    }

    /**
     * Change stop of inhabitant. If inhabitant's stop == true then it sets
     * false, if inhabitant's stop == false the it sets true
     */
    public void reverseStop() {
        this.stop = !this.stop;
    }

}
