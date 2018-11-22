package pl.population;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.board.Board;
import pl.board.Road;
import pl.board.District;
import pl.board.City;
import pl.board.Place;
import pl.population.Human.State;
import pl.resources.Files;
import pl.powerSource.PowerSource;

/**
 * Extension of Role that represents Villain
 *
 * @author Krzysztof Olejniczak
 */
public class Villain extends Role implements Runnable {

    private static final double DECREASE_POTENTIAL_PER_LOOP = 1.0;
    private static final int TIME_CITY_DESTROY_INTERVAL = 2000;

    /**
     * Villain constructor
     *
     * @param name name of villain
     * @param image image of villain
     * @param location initial location of villain
     * @param board game's board
     * @param population game's population
     */
    public Villain(String name, BufferedImage image, Place location,
            Board board, Population population) {
        super(name, image, location, board.findClosestCityAndCapitalAtTheEnd(location),
                State.TRAVELING, board, population);
    }

    /**
     * Method for spending in City, tries to attact superhero, tries to kill
     * inhabitant, checks if this city is destroyed
     */
    @Override
    protected void inCity() {
        if (tryToAttackOnCity(Superhero.class)) {
            return;
        }

        boolean changeInCity = false;
        City cityOfLocation = ((District) this.getLocation()).getCity();
        for (PowerSource powerSource : cityOfLocation.getPowerSources()) {
            if (powerSource.getPotential() > 0.0) {
                powerSource.potentialDecrease(DECREASE_POTENTIAL_PER_LOOP);
                this.powerIncrease(powerSource.getCapabilityType(),
                        DECREASE_POTENTIAL_PER_LOOP);
                changeInCity = true;
            }
        }

        if (cityOfLocation.findNotFightingHumanInCity(Civilian.class) != null) {
            ((Civilian) cityOfLocation.findNotFightingHumanInCity(Civilian.class))
                    .killInhabitant();
            changeInCity = true;
        }

        if (cityOfLocation.getDeparture().getOnSpot() instanceof Civilian) {
            ((Civilian) cityOfLocation.getDeparture().getOnSpot()).killInhabitant();
        }

        if (cityOfLocation.findNotFightingHumanInCity(Civilian.class) == null && !changeInCity) {
            cityOfLocation.destroy();
            if (!board.getDestroyedCities().contains(cityOfLocation)) {
                board.getDestroyedCities().add(cityOfLocation);
            }
        }

        if (cityOfLocation.isDestroyed() && cityOfLocation != board.getCapitalCity()) {
            this.setState(State.TRAVELING);
            this.setTravelTo(board.findOtherNotDestroyedRandomCity(board.getCapitalCity()));
        }
    }

    /**
     * Method for whileTravelling, tries to attack superhero, kill human and
     * check if destination city is not destroyed
     */
    @Override
    protected void whileTravelling() {
        if (this.getLocation() instanceof Road) {
            List<Human> humans = ((Road) this.getLocation()).getHumansAround();

            if (tryToAttactFromList(humans, Superhero.class)) {
                return;
            }

            if (((Road) this.getLocation()).getDeparture() instanceof City
                    && ((City) ((Road) this.getLocation()).getDeparture())
                            .findNotFightingHumanInCity(Civilian.class) != null) {
                ((Civilian) ((City) ((Road) this.getLocation()).getDeparture())
                        .findNotFightingHumanInCity(Civilian.class)).killInhabitant();
            }

            tryToKillCivilianFromList(humans);
        }

        if (this.getTravelTo().isDestroyed()) {
            this.setTravelTo(board.findOtherNotDestroyedRandomCity(board.getCapitalCity()));
        }
    }

    /**
     * Method for sleeping in inCity
     */
    @Override
    protected void sleepInCity() {
        try {
            Thread.sleep(TIME_CITY_DESTROY_INTERVAL);
        } catch (InterruptedException ex) {
            Logger.getLogger(Character.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Method for getting image for dead character
     *
     * @return image of dead character
     */
    @Override
    protected BufferedImage getDeadImage() {
        return Files.getImage(Files.VILLAIN_DEAD_IMAGE_FILE);
    }

    /**
     * Character tries to kill civiliansr from list
     *
     * @param humans list of civilians
     */
    protected void tryToKillCivilianFromList(List<Human> humans) {
        humans.stream().filter((human) -> (human instanceof Civilian)).forEachOrdered((human) -> {
            ((Civilian) human).killInhabitant();
        });
    }

}
