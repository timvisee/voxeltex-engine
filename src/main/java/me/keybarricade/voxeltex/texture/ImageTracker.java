package me.keybarricade.voxeltex.texture;

import java.util.ArrayList;
import java.util.List;

public class ImageTracker {

    /**
     * List of images managed by the engine.
     */
    private static List<Image> images = new ArrayList<>();

    /**
     * Add a new image to the tracker.
     *
     * @param image Image to add.
     */
    public static void trackImage(Image image) {
        images.add(image);
    }

    /**
     * Get the list of images.
     *
     * @return List of images.
     */
    public static List<Image> getImages() {
        return images;
    }

    /**
     * Get the number of managed images.
     *
     * @return Image count.
     */
    public static int getImageCount() {
        return images.size();
    }

    /**
     * Remove the given image from the tracker.
     *
     * @param image Image to remove.
     *
     * @return True if a image was removed from the tracker, false othewrise.
     */
    public static boolean untrackImage(Image image) {
        return images.remove(image);
    }

    /**
     * Remove the image from the tracker at the position of the given index.
     *
     * @param i Image index.
     *
     * @return Removed image.
     */
    public static Image untrackImage(int i) {
        return images.remove(i);
    }

    /**
     * Dispose all tracked images and remove them from the tracker.
     */
    public static void disposeAll() {
        // Loop through all images and dispose them
        // Loop through all textures and dispose them
        for(int i = getImageCount() - 1; i >= 0; i--)
            images.get(i).dispose();

        // Remove all images (should have been removed already)
        images.clear();
    }
}
