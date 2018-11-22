package pl.powerSource;

import pl.board.City;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.Main.Main;
import pl.board.Board;

/**
 * Thread updates power sources
 *
 * @author Krzysztof Olejniczak
 */
public class PowerSourcesUpdater implements Runnable {

    private static final long TIME_TO_SLEEP = 5000;
    private static final double NUMBER_OF_CITIZENS_PER_ONE_POINT_INCREASE = 30.0;

    private final Board board;

    /**
     * Constructor of Power Sources Updater
     *
     * @param board board of game
     */
    public PowerSourcesUpdater(Board board) {
        this.board = board;
    }

    /**
     * Thread which updates power sources
     */
    @Override
    public void run() {
        while (!board.getCapitalCity().isDestroyed()) {
            try {
                Thread.sleep(TIME_TO_SLEEP);
            } catch (InterruptedException ex) {
                Logger.getLogger(PowerSourcesUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (Main.isGameRunning()) {
                for (City city : board.getCities()) {

                    city.getPowerSources().forEach((powerSource) -> {
                        powerSource.potentialIncrease(
                                (double) city.getNumberOfCitizens()
                                / NUMBER_OF_CITIZENS_PER_ONE_POINT_INCREASE
                        );
                    });

                }
            }
        }
    }
}
