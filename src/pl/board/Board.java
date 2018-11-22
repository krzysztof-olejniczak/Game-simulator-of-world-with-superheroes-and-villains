package pl.board;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.powerSource.Capability.CapabilityType;
import pl.powerSource.PowerSource;

/**
 * Represents whole board of game
 *
 * @author Krzysztof Olejniczak
 */
public class Board {

    private static final int NUMBER_OF_STEPS = 100;
    private static final int TIME_TO_SLEEP_IF_ROAD_BUSY = 100;

    private City[] cities;
    private List<City> destroyedCities = new CopyOnWriteArrayList<>();
    private City capitalCity;

    private List<PowerSource> powerSources = new LinkedList<>();

    private int boardLengthY;
    private int boardLengthX;

    private int pixelsPerField;

    private int verticalMargin;
    private int horizontalMargin;

    private int humanWidth;
    private int humanHeight;

    /**
     * Empty constructor needed for XML serialization
     */
    public Board() {
    }

    /**
     * Finds random and empty road on board
     *
     * @return random road
     */
    public Place findNotBusyRoad() {
        int steps = (int) (Math.random() * NUMBER_OF_STEPS + 1);
        Place location = cities[(int) (Math.random() * cities.length % cities.length)]
                .getDeparture();

        for (; steps > 0; steps--) {
            if (location instanceof City) {
                location = ((City) location).getDeparture();
            } else if (location instanceof Intersection
                    && (int) (Math.random() * 2) % 2 == 1
                    && ((Intersection) location).getDeparture2() != null) {
                location = ((Intersection) location).getDeparture2();
            } else if (location instanceof Road) {
                location = ((Road) location).getDeparture();
            }
        }

        while (true) {
            if (location instanceof City) {
                location = ((City) location).getDeparture();
            } else if (location.getOnSpot() != null) {
                location = ((Road) location).getDeparture();
            } else {
                break;
            }

            try {
                Thread.sleep(TIME_TO_SLEEP_IF_ROAD_BUSY);
            } catch (InterruptedException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return location;
    }

    /**
     * Finds random city, which is not destroyed, lastCity is return only when
     * other cities are destroyed
     *
     * @param lastCity city returned if other cities are destroyed
     * @return random city, which is not destroyed
     */
    public City findOtherNotDestroyedRandomCity(City lastCity) {
        List<City> notDestroyedOtherCities = getAliveCities();
        if (lastCity != null) {
            notDestroyedOtherCities.remove(lastCity);
        }

        if (notDestroyedOtherCities.isEmpty()) {
            return lastCity;
        }
        return notDestroyedOtherCities.get((int) (Math.random()
                * notDestroyedOtherCities.size() % notDestroyedOtherCities.size()));
    }

    /**
     * Finds closest not destroyed city, capital city is always chosen at the
     * end
     *
     * @param placeToSearchFrom place from which searching starts
     * @return closest not destroyed city
     */
    public City findClosestCityAndCapitalAtTheEnd(Place placeToSearchFrom) {
        if (cities.length <= destroyedCities.size() + 1) {
            return capitalCity;
        }

        Queue<Place> roads = new LinkedList<>();
        roads.add(placeToSearchFrom);
        while (!roads.isEmpty()) {
            Place place = roads.poll();
            if (place instanceof City) {
                City city = (City) place;
                if (!city.isDestroyed() && !city.isCapital()) {
                    return city;
                } else {
                    roads.add(city.getDeparture());
                }
            } else {
                roads.add(((Road) place).getDeparture());
                if (place instanceof Intersection && ((Intersection) place).getDeparture2() != null) {
                    roads.add(((Intersection) place).getDeparture2());
                }
            }
        }
        return capitalCity;
    }

    /**
     * Finds cities that have any empty neigbourhood
     *
     * @return list of cities with at least one empty neigbourhood
     */
    public List<City> findCitiesWithEmptyNeigbourhood() {
        List<City> properCities = getAliveCities();

        properCities.stream().filter((city) -> (city.findEmptyNeighbourhood() == null))
                .forEachOrdered((city) -> {
                    properCities.remove(city);
                });
        if (properCities.isEmpty()) {
            return null;
        }
        return properCities;
    }

    /**
     * Gets list of cities names
     *
     * @return list of cities names
     */
    public String[] getCitiesNames() {
        String[] citiesNames = new String[cities.length];
        for (int i = 0; i < cities.length; i++) {
            citiesNames[i] = cities[i].getName();
        }
        return citiesNames;
    }

    /**
     * Gets index of city on cities list
     *
     * @param city city for search
     * @return index of city on cities list or -1 if does not exists
     */
    public int findCityIndex(City city) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i] == city) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds city of given name
     *
     * @param cityName name of city to find
     * @return city of given name
     */
    public City findCity(String cityName) {
        for (int i = 0; i < 10; i++) {
            if (cityName.equals(cities[i].getName())) {
                return cities[i];
            }
        }
        return null;
    }

    /**
     * Sums all potentials of given capability
     *
     * @param capability capability to sum
     * @return sum of potentials of given capability
     */
    public double sumOfPowerSources(CapabilityType capability) {
        double sum = 0.0;
        sum = powerSources.stream().filter((powerSource)
                -> (powerSource.getCapabilityType() == capability))
                .map((powerSource) -> powerSource.getPotential())
                .reduce(sum, (accumulator, _item) -> accumulator + _item);
        return sum;
    }

    /**
     * Calculates maxinum number of superheros based on all potentials of power
     * sources
     *
     * @return maxinum number of superheros
     */
    public int calcMaxNumberOfSuperheroes() {
        double sum = 0.0;
        for (PowerSource powerSource : this.getPowerSources()) {
            sum = sum + powerSource.getPotential();
        }
        return (int) (sum / 10);
    }

    /**
     * Gets alive cities
     *
     * @return list of not destroyed cities
     */
    public List<City> getAliveCities() {
        List<City> aliveCities = new LinkedList<>();
        aliveCities.addAll(Arrays.asList(cities));
        aliveCities.removeAll(destroyedCities);
        return aliveCities;
    }

    /**
     * Gets number of cities
     *
     * @return number of cities
     */
    public int getNumberOfCities() {
        return cities.length;
    }

    /**
     * Gets number of destroyed cities
     *
     * @return number of destroyed cities
     */
    public int getNumberOfDestroyedCities() {
        return destroyedCities.size();
    }

    /**
     * Gets table of cities
     *
     * @return table of cities
     */
    public City[] getCities() {
        return cities;
    }

    /**
     * Sets table of cities
     *
     * @param cities table of cities
     */
    public void setCities(City[] cities) {
        this.cities = cities;
    }

    /**
     * Gets list of destroyed cities
     *
     * @return list of destroyed cities
     */
    public List<City> getDestroyedCities() {
        return destroyedCities;
    }

    /**
     * Sets list of destroyed cities
     *
     * @param destroyedCities list of destroyed cities
     */
    public void setDestroyedCities(List<City> destroyedCities) {
        this.destroyedCities = destroyedCities;
    }

    /**
     * Gets capital city
     *
     * @return capital city
     */
    public City getCapitalCity() {
        return capitalCity;
    }

    /**
     * Sets capital city
     *
     * @param capitalCity capital city
     */
    public void setCapitalCity(City capitalCity) {
        this.capitalCity = capitalCity;
    }

    /**
     * Gets list of power sources
     *
     * @return list of power sources
     */
    public List<PowerSource> getPowerSources() {
        return powerSources;
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
     * Gets horizontal length of board
     *
     * @return horizontal length of board
     */
    public int getBoardLengthX() {
        return boardLengthX;
    }

    /**
     * Sets horizontal length of board
     *
     * @param boardLengthX horizontal length of board
     */
    public void setBoardLengthX(int boardLengthX) {
        this.boardLengthX = boardLengthX;
    }

    /**
     * Gets vertical length of board
     *
     * @return vertical length of board
     */
    public int getBoardLengthY() {
        return boardLengthY;
    }

    /**
     * Sets vertical length of board
     *
     * @param boardLengthY vertical length of board
     */
    public void setBoardLengthY(int boardLengthY) {
        this.boardLengthY = boardLengthY;
    }

    /**
     * Gets number of pixels per field
     *
     * @return number of pixels per field
     */
    public int getPixelsPerField() {
        return pixelsPerField;
    }

    /**
     * Sets number of pixels per field
     *
     * @param pixelsPerField number of pixels per field
     */
    public void setPixelsPerField(int pixelsPerField) {
        this.pixelsPerField = pixelsPerField;
    }

    /**
     * Gets board width in pixels
     *
     * @return board width in pixels
     */
    public int getWidthInPixels() {
        return boardLengthX * pixelsPerField + horizontalMargin;
    }

    /**
     * Gets board height in pixels
     *
     * @return board height in pixels
     */
    public int getHeightInPixels() {
        return boardLengthY * pixelsPerField + verticalMargin;
    }

    /**
     * Gets vertical margin
     *
     * @return vertical margin
     */
    public int getVerticalMargin() {
        return verticalMargin;
    }

    /**
     * Sets vertical margin
     *
     * @param verticalMargin vertical margin
     */
    public void setVerticalMargin(int verticalMargin) {
        this.verticalMargin = verticalMargin;
    }

    /**
     * Gets horizontal margin
     *
     * @return horizontal margin
     */
    public int getHorizontalMargin() {
        return horizontalMargin;
    }

    /**
     * Sets horizontal margin
     *
     * @param horizontalMargin horizontal margin
     */
    public void setHorizontalMargin(int horizontalMargin) {
        this.horizontalMargin = horizontalMargin;
    }

    /**
     * Gets human width
     *
     * @return human width
     */
    public int getHumanWidth() {
        return humanWidth;
    }

    /**
     * Sets human width
     *
     * @param humanWidth human width
     */
    public void setHumanWidth(int humanWidth) {
        this.humanWidth = humanWidth;
    }

    /**
     * Gest human height
     *
     * @return human height
     */
    public int getHumanHeight() {
        return humanHeight;
    }

    /**
     * Sets human height
     *
     * @param humanHeight human height
     */
    public void setHumanHeight(int humanHeight) {
        this.humanHeight = humanHeight;
    }
}
