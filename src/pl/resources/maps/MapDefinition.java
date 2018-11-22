package pl.resources.maps;

import java.awt.image.BufferedImage;
import pl.board.Board;

/**
 * Defines methods requirements for basic map for game
 *
 * @author Krzysztof Olejniczak
 */
public interface MapDefinition {

    /**
     * Gets background image for game
     *
     * @return background image for game
     */
    public BufferedImage getBackgroundImage();

    /**
     * Reads board from XML
     *
     * @return board of game
     */
    public Board readXML();

}
