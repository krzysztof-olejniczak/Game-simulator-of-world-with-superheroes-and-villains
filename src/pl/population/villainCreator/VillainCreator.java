package pl.population.villainCreator;

import pl.population.Villain;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.Main.Main;
import pl.board.Board;
import pl.population.fieldGenerators.VillainFieldsGenerator;
import pl.population.Population;

/**
 * *
 * Class with thread that creates villains every period of time
 *
 * @author Krzysztof Olejniczak
 */
public class VillainCreator implements Runnable {

    private static final int TIME_BEFORE_FIRST_VILLAIN = 40000;
    private static final int TIME_BETWEEN_GENERATIONS_FROM = 10000;
    private static final int TIME_BETWEEN_GENERATIONS_TO = 40000;
    private static final double TIME_IN_SECONDS_PER_VILLAIN = 120;

    private final Date timeOfStart;

    private final Board board;
    private final Population population;

    /**
     * Constructor creates object
     *
     * @param timeOfStart time of game start
     * @param board board of game
     * @param population population of game
     */
    public VillainCreator(Date timeOfStart, Board board, Population population) {
        this.board = board;
        this.population = population;
        this.timeOfStart = timeOfStart;
    }

    /**
     * Method of thread, that updates power sources. Number of created villains
     * depends on time from beginning of game
     */
    @Override
    public void run() {
        try {
            Thread.sleep(TIME_BEFORE_FIRST_VILLAIN);
        } catch (InterruptedException ex) {
            Logger.getLogger(VillainCreator.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (!board.getCapitalCity().isDestroyed()) {
            try {
                Thread.sleep((int) (Math.random() * (TIME_BETWEEN_GENERATIONS_TO
                        - TIME_BETWEEN_GENERATIONS_FROM + 1) + TIME_BETWEEN_GENERATIONS_FROM));
            } catch (InterruptedException ex) {
                Logger.getLogger(VillainCreator.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Main.isGameRunning()
                    && population.getVillains().size() < (((new Date()).getTime()
                    - timeOfStart.getTime()) / 1000.0 - Main.getTotalTimeOfBreaks()) / TIME_IN_SECONDS_PER_VILLAIN) {

                Villain villain = new Villain(
                        VillainFieldsGenerator.generateName(),
                        VillainFieldsGenerator.generateImage(),
                        board.findNotBusyRoad(), board, population
                );
                population.getVillains().add(villain);

                Thread thread = new Thread(villain);
                thread.start();

            }
        }
    }
}
