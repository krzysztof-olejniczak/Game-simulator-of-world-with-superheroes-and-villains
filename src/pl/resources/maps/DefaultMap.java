package pl.resources.maps;

import java.awt.image.BufferedImage;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pl.board.Board;
import pl.resources.Files;

/**
 * Information about resources of Default Map
 *
 * @author Krzysztof Olejniczak
 */
public class DefaultMap implements MapDefinition {

    private final String PATH = "assets/DefaultMap/";
    private final String BOARD_FILE_NAME = "board.xml";
    private final String BACKGROUND_FILE_NAME = "background.png";

    private final File BOARD_FILE = new File(PATH + BOARD_FILE_NAME);
    private final File BACKGROUND_FILE = new File(PATH + BACKGROUND_FILE_NAME);

    /**
     * Reads board from XML
     *
     * @return board read
     */
    @Override
    public Board readXML() {
        XMLDecoder input = null;
        try {
            input = new XMLDecoder(new FileInputStream(BOARD_FILE));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
        Board board = (Board) input.readObject();
        input.close();
        return board;
    }

    /**
     * Gets background image of map
     *
     * @return background image
     */
    @Override
    public BufferedImage getBackgroundImage() {
        try {
            return ImageIO.read(BACKGROUND_FILE);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
