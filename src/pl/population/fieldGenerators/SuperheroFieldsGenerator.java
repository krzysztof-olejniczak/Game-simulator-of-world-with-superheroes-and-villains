package pl.population.fieldGenerators;

import java.awt.image.BufferedImage;
import pl.resources.Files;

/**
 * Generates random objects for new superheroes
 *
 * @author Krzysztof Olejniczak
 */
public class SuperheroFieldsGenerator {

    private static final String[] SUPERHERO_NAMES = {
        "Batman", "Superman", "Spiderman", "Lara Croft", "Metro Man",
        "Mr. Incredible", "Elastigirl", "Gru", "Megamind", "Ariel"
    };

    /**
     * Generates random name for superhero
     *
     * @return random name for superhero
     */
    public static String generateName() {
        return SUPERHERO_NAMES[(int) (Math.random() * SUPERHERO_NAMES.length % SUPERHERO_NAMES.length)];
    }

    /**
     * Generates random image for superhero from resources. Current
     * implementation returns the same image every time
     *
     * @return random image for superhero
     */
    public static BufferedImage generateImage() {
        return Files.getImage(Files.SUPERHERO_IMAGE_FILE);
    }

}
