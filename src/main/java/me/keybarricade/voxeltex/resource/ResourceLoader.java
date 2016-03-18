package me.keybarricade.voxeltex.resource;

import java.io.InputStream;
import java.util.Scanner;

public class ResourceLoader {

    public static InputStream loadResourceAsStream(String path) {
        return System.class.getResourceAsStream("/" + path);
    }

    public static String loadResourceAsString(String path) {
        Scanner s = new Scanner(loadResourceAsStream(path)).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
