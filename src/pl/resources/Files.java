package pl.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Primary files of resources needed in game
 *
 * @author Krzysztof Olejniczak
 */
public class Files {

    /**
     * File of city image
     */
    public static final File CITY_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.CITY_IMAGE_NAME));

    /**
     * File of destroyed city image
     */
    public static final File DESTROYED_CITY_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.DESTROYED_CITY_IMAGE_NAME));

    /**
     * Files of civilians images
     */
    public static final File[] CIVILIAN_IMAGES_FILES
            = createImagesFiles(FilesPaths.CIVILIAN_IMAGES_NAMES);

    /**
     * File of dead civilian
     */
    public static final File CIVILIAN_DEAD_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.CIVILIAN_DEAD_IMAGE_NAME));

    /**
     * File of superhero image
     */
    public static final File SUPERHERO_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.SUPERHERO_IMAGE_NAME));

    /**
     * File of fighting superhero image
     */
    public static final File SUPERHERO_FIGHTING_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.SUPERHERO_FIGHTING_IMAGE_NAME));

    /**
     * File of dead superhero image
     */
    public static final File SUPERHERO_DEAD_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.SUPERHERO_DEAD_IMAGE_NAME));

    /**
     * File of villain image
     */
    public static final File VILLAIN_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.VILLAIN_IMAGE_NAME));

    /**
     * File of fighting villain image
     */
    public static final File VILLAIN_FIGHTING_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.VILLAIN_FIGHTING_IMAGE_NAME));

    /**
     * File of dead villain image
     */
    public static final File VILLAIN_DEAD_IMAGE_FILE
            = new File(FilesPaths.getImagePath(FilesPaths.VILLAIN_DEAD_IMAGE_NAME));

    /**
     * File of game results
     */
    public static final File RESULTS_FILE
            = new File(FilesPaths.getFilePath(FilesPaths.RESULTS_FILE_NAME));

    private static File[] createImagesFiles(String[] imagesNames) {
        File[] imagesFiles = new File[imagesNames.length];
        for (int i = 0; i < imagesNames.length; i++) {
            imagesFiles[i] = new File(FilesPaths.getImagePath(imagesNames[i]));
        }
        return imagesFiles;
    }

    /**
     * Gets image of taken file
     *
     * @param file file to get image from
     * @return image
     */
    public static BufferedImage getImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException ex) {
            Logger.getLogger(Files.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
