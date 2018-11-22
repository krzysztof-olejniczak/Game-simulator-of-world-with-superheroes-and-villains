package pl.Main;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.board.Board;
import pl.resources.maps.DefaultMap;
import pl.gui.GameOverWindow;
import pl.gui.MainWindow;
import pl.population.Population;
import pl.population.villainCreator.VillainCreator;
import pl.powerSource.PowerSourcesUpdater;
import pl.resources.maps.MapDefinition;

/**
 * Main class, constructs game's properties
 *
 * @author Krzysztof Olejniczak
 */
public class Main {

    private static final int REPAINT_INTERVAL = 40;
    private static final int NUMBER_OF_CIVILIANS_GENERATED_IN_THE_BEGINNING = 40;

    private static MainWindow mainWindow;
    private static boolean gameRunning;

    private static MapDefinition map;
    private static Board board;
    private static Population population;

    private static int numberOfVillainsKilled;
    private static Date timeOfStart;
    private static Date timeOfStartBreak;
    private static int totalTimeOfBreaks;

    /**
     * Main method of game
     *
     * @param args
     */
    public static void main(String args[]) {

        map = new DefaultMap();
        board = map.readXML();
        population = new Population();
        population.addRandomCivilians(NUMBER_OF_CIVILIANS_GENERATED_IN_THE_BEGINNING, board);

        mainWindow = new MainWindow(map, board, population);
        mainWindow.setVisible(true);
        gameRunning = true;

        timeOfStart = new Date();
        numberOfVillainsKilled = 0;
        totalTimeOfBreaks = 0;

        Thread villainCreatorThread = new Thread(
                new VillainCreator(timeOfStart, board, population)
        );
        villainCreatorThread.start();

        Thread powerSourcesUpdaterThread = new Thread(
                new PowerSourcesUpdater(board)
        );
        powerSourcesUpdaterThread.start();

        boolean gameOverWindowRunning = false;

        while (true) {
            mainWindow.getObjectsPanel().repaint();

            if (board.getCapitalCity().isDestroyed() && !gameOverWindowRunning) {
                gameRunning = false;
                mainWindow.setButtonsNotVisible();

                GameOverWindow gameOverWindow = new GameOverWindow(numberOfVillainsKilled,
                        (int) (((new Date()).getTime() - timeOfStart.getTime()) / 1000.0)
                        - totalTimeOfBreaks);
                gameOverWindow.setVisible(true);
                gameOverWindowRunning = true;
            }

            try {
                Thread.sleep(REPAINT_INTERVAL);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Gets Main Window
     *
     * @return Main Window
     */
    public static MainWindow getMainWindow() {
        return mainWindow;
    }

    /**
     * Is game running
     *
     * @return true if game is running, false if game is stopped
     */
    public static boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Sets game stopped if game is running, sets game running if game is
     * stopped
     */
    public static void reverseGameRunning() {
        gameRunning = !gameRunning;
    }

    /**
     * Increments number of killed villains by 1
     */
    public static void increaseNumberOfVillainsKilled() {
        numberOfVillainsKilled++;
    }

    /**
     * Gets last time that game was stopped
     *
     * @return last time that game was stopped
     */
    public static Date getTimeOfStartBreak() {
        return timeOfStartBreak;
    }

    /**
     * Sets time of when game was stopped
     *
     * @param timeOfStartBreak time of when game was stopped
     */
    public static void setTimeOfStartBreak(Date timeOfStartBreak) {
        Main.timeOfStartBreak = timeOfStartBreak;
    }

    /**
     * Gets sum time of breaks in seconds
     *
     * @return sum time of breaks in seconds
     */
    public static int getTotalTimeOfBreaks() {
        return totalTimeOfBreaks;
    }

    /**
     * Increases sum time of breaks by value
     *
     * @param value sum time of breaks increase by value
     */
    public static void increaseTotalTimeOfBreaks(int value) {
        totalTimeOfBreaks += value;
    }

}
