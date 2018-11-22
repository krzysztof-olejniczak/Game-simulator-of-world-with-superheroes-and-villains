package pl.gui;

import pl.board.City;
import pl.population.Character;
import pl.population.Civilian;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import pl.board.Board;
import pl.population.Human;
import pl.population.Population;
import pl.resources.Files;

/**
 * *
 * Panel shown in the main window, on which are drawn: population and board
 *
 * @author Krzysztof Olejniczak
 */
public class ObjectsPanel extends JPanel implements MouseListener {

    private final BufferedImage background;
    private final MainWindow mainWindow;

    private final Board board;
    private final Population population;

    /**
     * *
     * Main panel constructor
     *
     * @param background background image of map
     * @param mainWindow main window, on which panel is shown
     * @param board board of game
     * @param population population of humans
     */
    public ObjectsPanel(BufferedImage background, MainWindow mainWindow,
            Board board, Population population) {
        this.background = background;
        this.mainWindow = mainWindow;
        this.board = board;
        this.population = population;

        addMouseListener(this);
    }

    /**
     * Repainting not constant elements - humans and destroyed cities
     *
     * @param graphic graphic on which repaint is performed
     */
    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(this.background, 0, 0, mainWindow);

        board.getDestroyedCities().forEach((city) -> {
            graphic.drawImage(Files.getImage(Files.DESTROYED_CITY_IMAGE_FILE),
                    city.getX(), city.getY(), this);
        });

        population.getWholePopulation().stream().filter((human) -> (human.getImage() != null))
                .forEachOrdered((human) -> {
                    graphic.drawImage(human.getImage(), human.getX(), human.getY(), this);
                });
    }

    /**
     * Method ran in case of mouse click, runs object info windows
     *
     * @param event event object
     */
    @Override
    public void mouseReleased(MouseEvent event) {
        for (Human human : population.getWholePopulation()) {
            if (human.getX() <= event.getX() && event.getX() <= human.getX() + board.getHumanWidth()
                    && human.getY() <= event.getY() && event.getY() <= human.getY() + board.getHumanHeight()) {
                javax.swing.JFrame window = null;

                if (human.getInfoWindow() == null) {
                    if (human instanceof Character) {
                        window = new CharacterInfoWindow((Character) human, board);

                        Thread thread = new Thread((CharacterInfoWindow) window);
                        thread.start();

                    } else if (human instanceof Civilian) {
                        window = new CivilianInfoWindow((Civilian) human, board);

                        Thread thread = new Thread((CivilianInfoWindow) window);
                        thread.start();
                    }
                    window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    window.setVisible(true);
                    human.setInfoWindow(window);
                } else {
                    human.getInfoWindow().toFront();
                }
                return;
            }
        }

        for (City city : board.getCities()) {
            if ((city.getX() <= event.getX() && event.getX() <= city.getX() + city.getSideLength() * board.getPixelsPerField()
                    && city.getY() <= event.getY() && event.getY() <= city.getY() + city.getSideLength() * board.getPixelsPerField())
                    || (city.getSubtitlesX() <= event.getX() && event.getX() <= city.getSubtitlesX() + city.getSubtitlesWidth()
                    && city.getSubtitlesY() <= event.getY() && event.getY() <= city.getSubtitlesY() + city.getSubtitlesHeight())) {
                if (city.getInfoWindow() == null) {
                    CityInfoWindow window = new CityInfoWindow(city);

                    Thread thread = new Thread(window);
                    thread.start();

                    window.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                    window.setVisible(true);
                    city.setInfoWindow(window);

                    return;
                } else {
                    city.getInfoWindow().toFront();
                }
            }
        }
    }

    /**
     * Method doing nothing - not needed
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * Method doing nothing - not needed
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * Method doing nothing - not needed
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Method doing nothing - not needed
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
