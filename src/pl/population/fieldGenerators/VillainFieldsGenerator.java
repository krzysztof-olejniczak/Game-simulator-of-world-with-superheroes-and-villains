package pl.population.fieldGenerators;

import java.awt.image.BufferedImage;
import pl.resources.Files;

/**
 * Generates random objects for new villains
 *
 * @author Krzysztof Olejniczak
 */
public class VillainFieldsGenerator {

    private static final String[] VILLAIN_NAMES = {
        "Voldemort", "Barbossa", "Gargamel", "Joker", "Creeper",
        "Buka", "Hans", "Gothel", "Gaston", "Pablo"
    };

    /**
     * Generates random name for villain
     *
     * @return random name for villain
     */
    public static String generateName() {
        return VILLAIN_NAMES[(int) (Math.random() * VILLAIN_NAMES.length % VILLAIN_NAMES.length)];
    }

    /**
     * Generates random image for villain from resources. Current implementation
     * returns the same image every time
     *
     * @return random image for villain
     */
    public static BufferedImage generateImage() {
        return Files.getImage(Files.VILLAIN_IMAGE_FILE);
    }
}
