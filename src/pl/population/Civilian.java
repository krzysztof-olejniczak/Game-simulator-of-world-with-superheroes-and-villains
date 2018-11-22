package pl.population;

import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.Main.Main;
import pl.board.Board;
import pl.board.City;
import pl.board.District;
import pl.board.Road;
import pl.resources.Files;

/**
 * Extension of Inhabitant that is runnable and act on a board
 *
 * @author Krzysztof Olejniczak
 */
public class Civilian extends Inhabitant implements Runnable {

    private static final double TRAVEL_PROBABILITY = 0.01;
    private static final double STOP_ON_ROAD_PROBABILITY = 0.001;
    private static final double GO_AFTER_STOP_PROBABILITY = 0.01;

    /**
     * Civilian's constructor
     *
     * @param name name of civilian
     * @param surname surname of civilian
     * @param image image of civilian
     * @param location initial location of civilian
     * @param board game's board
     * @param population game's population
     */
    public Civilian(String name, String surname, BufferedImage image,
            District location, Board board, Population population) {
        super(name, surname, image, location, location.getCity(), board, population);
    }

    /**
     * Method for civilian's thread
     */
    @Override
    public void run() {
        while (!this.isKilled()) {
            if (Main.isGameRunning()) {

                switch (this.getState()) {
                    case IN_CITY:
                        city();
                        break;

                    case TRAVELING:
                        travelling();
                        if (!this.isStop()) {
                            this.setLocation(travel());
                        }
                        break;
                }

                if (this.getState() == State.IN_CITY) {
                    try {
                        Thread.sleep(TIME_TO_SLEEP_IN_TOWN);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Civilian.class.getName())
                                .log(Level.SEVERE, null, ex);
                    }
                }

            } else {

                try {
                    Thread.sleep(TIME_TO_SLEEP_ON_STOP);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Civilian.class.getName())
                            .log(Level.SEVERE, null, ex);
                }

            }
        }

        this.getHomeTown().decrementNumberOfCitizens();
        this.setImage(Files.getImage(Files.CIVILIAN_DEAD_IMAGE_FILE));

        try {
            Thread.sleep(TIME_TO_SLEEP_AFTER_BEING_KILLED);
        } catch (InterruptedException ex) {
            Logger.getLogger(Civilian.class.getName())
                    .log(Level.SEVERE, null, ex);
        }

        population.getCivilians().remove(this);
        this.getLocation().setEmpty(this);
    }

    private void city() {
        if (this.getHomeTown().isDestroyed()
                && ((District) this.getLocation()).getCity() == this.getHomeTown()) {
            City newHomeTown = board.findOtherNotDestroyedRandomCity(this.getHomeTown());
            this.setTravelTo(newHomeTown);
            this.setState(State.TRAVELING);
            this.setHomeTown(newHomeTown);
            newHomeTown.incrementNumberOfCitizens();
            return;
        } else if (this.getHomeTown().isDestroyed()) {
            City newHomeTown = ((District) this.getLocation()).getCity();
            this.setHomeTown(newHomeTown);
            newHomeTown.incrementNumberOfCitizens();
            return;
        } else if (((District) this.getLocation()).getCity().isDestroyed()) {
            this.setTravelTo(getHomeTown());
            this.setState(State.TRAVELING);
            return;
        }

        if (this.getTravelTo() != ((District) this.getLocation()).getCity()) {
            this.setState(State.TRAVELING);
        } else if ((int) (Math.random() / TRAVEL_PROBABILITY) == 0) {
            if (((District) this.getLocation()).getCity() == this.getHomeTown()) {
                this.setTravelTo(board.findOtherNotDestroyedRandomCity(this.getHomeTown()));
            } else {
                this.setTravelTo(this.getHomeTown());
            }
            this.setState(State.TRAVELING);
        }

    }

    private void travelling() {
        if (this.getHomeTown().isDestroyed() && this.getTravelTo() == this.getHomeTown()) {
            City newHomeTown = board.findOtherNotDestroyedRandomCity(this.getHomeTown());
            this.setTravelTo(newHomeTown);
            this.setHomeTown(newHomeTown);
            newHomeTown.incrementNumberOfCitizens();
            return;
        } else if (this.getHomeTown().isDestroyed()) {
            City newHomeTown = this.getTravelTo();
            this.setHomeTown(newHomeTown);
            newHomeTown.incrementNumberOfCitizens();
            return;
        } else if (this.getTravelTo().isDestroyed()) {
            this.setTravelTo(board.findOtherNotDestroyedRandomCity(this.getHomeTown()));
            return;
        }

        if (this.isStop()) {
            try {
                Thread.sleep(TIME_TO_SLEEP_ON_STOP);
            } catch (InterruptedException ex) {
                Logger.getLogger(Inhabitant.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }

        if (this.getLocation() instanceof Road) {
            if ((int) (Math.random() / STOP_ON_ROAD_PROBABILITY) == 0) {
                this.setStop(true);
            } else if ((int) (Math.random() / GO_AFTER_STOP_PROBABILITY) == 0) {
                this.setStop(false);
            }
        }
    }

}
