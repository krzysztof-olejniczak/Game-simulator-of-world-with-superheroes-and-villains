package pl.population;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.board.Board;
import pl.board.Road;
import pl.board.District;
import pl.population.Human.State;
import pl.resources.Files;

/**
 * Extension of Role that represents Superhero and its behavior
 *
 * @author Krzysztof Olejniczak
 */
public class Superhero extends Role {

    /**
     * Superhero constructor
     *
     * @param name name of superhero
     * @param image image of superhero
     * @param location initial location of superhero
     * @param board game's board
     * @param population game's population
     */
    public Superhero(String name, BufferedImage image, District location,
            Board board, Population population) {
        super(name, image, location, location.getCity(), State.IN_CITY, board, population);
    }

    /**
     * Method for spending in City, tries to attact villain, checks if this city
     * is destroyed and if destination city has not changed
     */
    @Override
    protected void inCity() {
        if (tryToAttackOnCity(Villain.class)) {
            return;
        }

        if (((District) this.getLocation()).getCity().isDestroyed()) {
            this.setTravelTo(board.findOtherNotDestroyedRandomCity(board.getCapitalCity()));
            this.setState(State.TRAVELING);
            return;
        }

        if (this.getTravelTo() != ((District) this.getLocation()).getCity()) {
            this.setState(State.TRAVELING);
        }
    }

    /**
     * Method for whileTravelling, tries to attack villain
     */
    @Override
    protected void whileTravelling() {
        if (this.getLocation() instanceof Road) {
            if (tryToAttactFromList(((Road) this.getLocation()).getHumansAround(),
                    Superhero.class)) {
            }
        }
    }

    /**
     * Method for sleeping in inCity
     */
    @Override
    protected void sleepInCity() {
        if (this.getState() == State.IN_CITY && this instanceof Superhero) {
            try {
                Thread.sleep(TIME_TO_SLEEP_IN_TOWN);
            } catch (InterruptedException ex) {
                Logger.getLogger(Character.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Method for getting image for dead superhero
     *
     * @return image of dead superhero
     */
    @Override
    protected BufferedImage getDeadImage() {
        return Files.getImage(Files.SUPERHERO_DEAD_IMAGE_FILE);
    }
}
