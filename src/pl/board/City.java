package pl.board;

import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import pl.powerSource.PowerSource;
import pl.population.Character;
import pl.population.Inhabitant;
import pl.population.Human;

/**
 * *
 * Represents single city on board
 *
 * @author Krzysztof Olejniczak
 */
public class City extends Place {

    private String name;

    private int sideLength;
    private District districts[][];

    private boolean capital;
    private boolean destroyed;

    private int numberOfCitizens;

    private Place entry;
    private Place departure;

    private List<PowerSource> powerSources;

    private int subtitlesX, subtitlesY, subtitlesWidth, subtitlesHeight;

    private javax.swing.JFrame infoWindow;

    /**
     * City constructor
     *
     * @param name name of city
     * @param capital set true if capital and false if it is not
     * @param coordX X coordinate on board
     * @param coordY Y coordinate on board
     * @param pixelsPerField number of pixels per field side length
     * @param sideLength side length of city, squared gives total number of
     * districts
     * @param subtitlesX X coordinate of location of city name on map
     * @param subtitlesY Y coordinate of location of city name on map
     * @param subtitlesWidth width of city name on map
     * @param subtitlesHeight height of city name on map
     */
    public City(String name, int sideLength, boolean capital, int coordX, int coordY,
            int pixelsPerField, int subtitlesX, int subtitlesY, int subtitlesWidth, int subtitlesHeight) {
        super(pixelsPerField * coordX, pixelsPerField * coordY);
        this.name = name;
        this.sideLength = sideLength;
        this.districts = new District[this.sideLength][this.sideLength];
        this.capital = capital;
        this.destroyed = false;
        this.numberOfCitizens = 0;
        this.powerSources = new LinkedList<>();
        this.subtitlesX = subtitlesX;
        this.subtitlesY = subtitlesY;
        this.subtitlesWidth = subtitlesWidth;
        this.subtitlesHeight = subtitlesHeight;
    }

    /**
     * Empty constructor needed for XML serialization
     */
    public City() {

    }

    /**
     * Finds empty district in city
     *
     * @return empty district or null if does not exist
     */
    public District findEmptyNeighbourhood() {
        int y, x;
        for (y = 0; y < this.sideLength; y++) {
            for (x = 0; x < this.sideLength; x++) {
                if (districts[x][y].getOnSpot() == null) {
                    return districts[x][y];
                }
            }
        }
        return null;
    }

    /**
     * Finds Human of given class in city. If Human's class is Character then it
     * additionally have to doesn't fight at the moment.
     *
     * @param classOfHuman given class of character
     * @return character found or null if does not exist
     */
    public Human findNotFightingHumanInCity(Class<? extends Human> classOfHuman) {
        for (int y = 0; y < this.sideLength; y++) {
            for (int x = 0; x < this.sideLength; x++) {

                if (this.districts[x][y].getOnSpot() != null
                        && classOfHuman.isInstance(this.districts[x][y].getOnSpot())) {

                    if (this.districts[x][y].getOnSpot() instanceof Inhabitant) {
                        return this.districts[x][y].getOnSpot();
                    } else if (this.districts[x][y].getOnSpot() instanceof Character
                            && ((Character) this.districts[x][y].getOnSpot()).getFightWith() == null) {
                        return this.districts[x][y].getOnSpot();
                    }

                }
            }
        }
        return null;
    }

    /**
     * Adds given power source to city
     *
     * @param powerSource power source to add
     */
    public void addPowerSourceToCity(PowerSource powerSource) {
        this.powerSources.add(powerSource);
    }

    /**
     * Destroys city
     */
    public void destroy() {
        this.destroyed = true;
    }

    /**
     * Increase number of citizens by 1
     */
    public void incrementNumberOfCitizens() {
        this.numberOfCitizens++;
    }

    /**
     * Decrease number of citizens by 1
     */
    public void decrementNumberOfCitizens() {
        this.numberOfCitizens--;
        if (this.numberOfCitizens < 0) {
            this.numberOfCitizens = 0;
        }
    }

    /**
     * Gets name of city
     *
     * @return name of city
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name of city
     *
     * @param name name of city
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets side length of city
     *
     * @return side length of city
     */
    public int getSideLength() {
        return this.sideLength;
    }

    /**
     * Sets side length of city
     *
     * @param sideLength side length of city
     */
    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * Gets districts of city
     *
     * @return districts of city
     */
    public District[][] getDistricts() {
        return districts;
    }

    /**
     * Sets districts of city
     *
     * @param districts districts of city as two dimentional table
     */
    public void setDistricts(District[][] districts) {
        this.districts = districts;
    }

    /**
     * Set single district of city
     *
     * @param x X coordinate on table of city districts
     * @param y Y coordinate on table of city districts
     * @param district new district
     */
    public void setDistrict(int x, int y, Place district) {
        this.districts[x][y] = (District) district;
    }

    /**
     * Is city capital
     *
     * @return true if city is capital, false otherwise
     */
    public boolean isCapital() {
        return capital;
    }

    /**
     * Sets if city is capital
     *
     * @param capital true if city is capital, false otherwise
     */
    public void setCapital(boolean capital) {
        this.capital = capital;
    }

    /**
     * Is city destroyed
     *
     * @return true if city is destroyed, false overwise
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }

    /**
     * Sets city destroyed
     *
     * @param destroyed true if city is destroyed, false overwise
     */
    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Gets number of citizens in city
     *
     * @return number of citizens in city
     */
    public int getNumberOfCitizens() {
        return numberOfCitizens;
    }

    /**
     * Sets number of citizens in city
     *
     * @param numberOfCitizens number of citizens in city
     */
    public void setNumberOfCitizens(int numberOfCitizens) {
        this.numberOfCitizens = numberOfCitizens;
    }

    /**
     * Gets entry to city
     *
     * @return entry to city
     */
    public Place getEntry() {
        return this.entry;
    }

    /**
     * Sets entry to city
     *
     * @param entry entry to city
     */
    public void setEntry(Place entry) {
        this.entry = entry;
    }

    /**
     * Gets departure from city
     *
     * @return departure from city
     */
    public Place getDeparture() {
        return this.departure;
    }

    /**
     * Sets departure from city
     *
     * @param departure departure from city
     */
    public void setDeparture(Place departure) {
        this.departure = departure;
    }

    /**
     * Sets entry and departure of city
     *
     * @param entry entry to city
     * @param departure departure from city
     */
    public void setEntryDeparture(Place entry, Place departure) {
        this.entry = entry;
        this.departure = departure;
    }

    /**
     * Gets list of power sources
     *
     * @return list of power sources
     */
    public List<PowerSource> getPowerSources() {
        return this.powerSources;
    }

    /**
     * Sets list of power sources
     *
     * @param powerSources list of power sources
     */
    public void setPowerSources(List<PowerSource> powerSources) {
        this.powerSources = powerSources;
    }

    /**
     * Gets subtitles X coordinate
     *
     * @return subtitles X coordinate
     */
    public int getSubtitlesX() {
        return subtitlesX;
    }

    /**
     * Sets subtitles X coordinate
     *
     * @param subtitlesX subtitles X coordinate
     */
    public void setSubtitlesX(int subtitlesX) {
        this.subtitlesX = subtitlesX;
    }

    /**
     * Gets subtitles Y coordinate
     *
     * @return subtitles Y coordinate
     */
    public int getSubtitlesY() {
        return subtitlesY;
    }

    /**
     * Sets subtitles Y coordinate
     *
     * @param subtitlesY subtitles Y coordinate
     */
    public void setSubtitlesY(int subtitlesY) {
        this.subtitlesY = subtitlesY;
    }

    /**
     * Gets subtitles width
     *
     * @return subtitles width
     */
    public int getSubtitlesWidth() {
        return subtitlesWidth;
    }

    /**
     * Sets subtitles width
     *
     * @param subtitlesWidth subtitles width
     */
    public void setSubtitlesWidth(int subtitlesWidth) {
        this.subtitlesWidth = subtitlesWidth;
    }

    /**
     * Gets subtitles height
     *
     * @return subtitles height
     */
    public int getSubtitlesHeight() {
        return subtitlesHeight;
    }

    /**
     * Sets subtitles height
     *
     * @param subtitlesHeight subtitles height
     */
    public void setSubtitlesHeight(int subtitlesHeight) {
        this.subtitlesHeight = subtitlesHeight;
    }

    /**
     * Gets info window
     *
     * @return info window or null if does not exist
     */
    public JFrame getInfoWindow() {
        return infoWindow;
    }

    /**
     * Sets info window
     *
     * @param infoWindow info window
     */
    public void setInfoWindow(JFrame infoWindow) {
        this.infoWindow = infoWindow;
    }

}
