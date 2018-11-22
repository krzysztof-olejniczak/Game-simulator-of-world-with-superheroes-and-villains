package pl.resources;

/**
 * Defines paths to primary files needed in game
 *
 * @author Krzysztof Olejniczak
 */
public class FilesPaths {

    /**
     * Path of folder consisting primary images
     */
    public static final String IMAGES_BASE_PATH = "assets/images/";

    /**
     * Extension of all image files
     */
    public static final String EXTENSION = ".png";

    /**
     * Name of city image file
     */
    public static final String CITY_IMAGE_NAME = "city";

    /**
     * Name of destroy city image file
     */
    public static final String DESTROYED_CITY_IMAGE_NAME = "destroyed_city";

    /**
     * Names of civilian image files
     */
    public static final String[] CIVILIAN_IMAGES_NAMES = {
        "civilian1", "civilian2", "civilian3", "civilian4",
        "civilian5", "civilian6", "civilian7", "civilian8"
    };

    /**
     * Name of dead civilian image file
     */
    public static final String CIVILIAN_DEAD_IMAGE_NAME = "dead_civilian";

    /**
     * Name of superhero image file
     */
    public static final String SUPERHERO_IMAGE_NAME = "superhero";

    /**
     * Name of fighting superhero image file
     */
    public static final String SUPERHERO_FIGHTING_IMAGE_NAME = "fighting_superhero";

    /**
     * Name of dead superhero image file
     */
    public static final String SUPERHERO_DEAD_IMAGE_NAME = "dead_superhero";

    /**
     * Name of villain image file
     */
    public static final String VILLAIN_IMAGE_NAME = "villain";

    /**
     * Name of fighting villain image file
     */
    public static final String VILLAIN_FIGHTING_IMAGE_NAME = "fighting_villain";

    /**
     * Name of dead villain image file
     */
    public static final String VILLAIN_DEAD_IMAGE_NAME = "dead_villain";

    /**
     * Path to non-image files
     */
    public static final String FILES_BASE_PATH = "assets/";

    /**
     * Name and extension of results file
     */
    public static final String RESULTS_FILE_NAME = "results.xml";

    /**
     * Gets path, name and extension to given image file
     *
     * @param imageName image file name
     * @return path to image file
     */
    public static String getImagePath(String imageName) {
        return IMAGES_BASE_PATH + imageName + EXTENSION;
    }

    /**
     * Gets path, name and extension to given non-image file
     *
     * @param fileName file name
     * @return path to file
     */
    public static String getFilePath(String fileName) {
        return FILES_BASE_PATH + fileName;
    }

}
