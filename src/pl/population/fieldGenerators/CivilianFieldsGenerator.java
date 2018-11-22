package pl.population.fieldGenerators;

import java.awt.image.BufferedImage;
import pl.resources.FilesPaths;
import pl.resources.Files;

/**
 * Generates random objects for new civilians
 *
 * @author Krzysztof Olejniczak
 */
public class CivilianFieldsGenerator {

    private static final String[] CIVILIAN_NAMES = {
        "Simon", "Christopher", "John", "Richard", "Alexarnder",
        "Claire", "Kevin", "Mery", "Nataly", "Julia"
    };

    private static final String[] CIVILIAN_SURNAMES = {
        "Smith", "McArthur", "Brett", "Bush", "Cole",
        "Nelson", "Norris", "Page", "Simons", "Scarlett"
    };

    /**
     * Generates random name for civilian
     *
     * @return random name for civilian
     */
    public static String generateName() {
        return CIVILIAN_NAMES[(int) (Math.random() * CIVILIAN_NAMES.length % CIVILIAN_NAMES.length)];
    }

    /**
     * Generates random surname for civilian
     *
     * @return random surname for civilian
     */
    public static String generateSurname() {
        return CIVILIAN_SURNAMES[(int) (Math.random() * CIVILIAN_SURNAMES.length % CIVILIAN_SURNAMES.length)];
    }

    /**
     * Generates random image for civilian from resources
     *
     * @return random image for civilian
     */
    public static BufferedImage generateImage() {
        return Files.getImage(
                Files.CIVILIAN_IMAGES_FILES[(int) (Math.random() * FilesPaths.CIVILIAN_IMAGES_NAMES.length
                % FilesPaths.CIVILIAN_IMAGES_NAMES.length)]
        );
    }
}
