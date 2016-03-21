package me.keybarricade.voxeltex.global;

import me.keybarricade.voxeltex.time.Timer;

public class Time {

    // TODO: Make sure none of these time values has an overflow!

    /**
     * Timer instance, used to calculate time.
     */
    private static Timer timer;

    /**
     * Defines whether this is the first time frame.
     */
    private static boolean isFirst = true;

    /**
     * The time in seconds the frame started rendering.
     */
    public static double time = 0.0;

    /**
     * The time in seconds the frame started rendering as a float.
     */
    public static float timeFloat = 0.0f;

    /**
     * The time in nanoseconds the frame started rendering.
     */
    public static long timeNano = 0;

    /**
     * The time between the last frame and this frame in seconds.
     */
    public static double deltaTime = 0.0;

    /**
     * The time between the last frame and this frame in seconds as a float.
     */
    public static float deltaTimeFloat = 0.0f;

    /**
     * The time between the last frame and this frame in nanoseconds.
     */
    public static long deltaTimeNano = 0;

    /**
     * Get the timer.
     *
     * @return Timer.
     */
    public static Timer getTimer() {
        return Time.timer;
    }

    /**
     * Initialize the Time object.
     * Required before being used.
     */
    public static void init() {
        // Set the timer instance
        Time.timer = new Timer(true);
    }

    /**
     * Update the times.
     * This should only be called once when the frame starts.
     */
    public static void update() {
        // Move the time and nano time to the last time
        double lastTime = Time.time;
        long lastTimeNano = Time.timeNano;

        // Store the current times
        time = Time.timer.getElapsedTime();
        timeFloat = (float) time;
        timeNano = Time.timer.getElapsedNano();

        // Calculate the delta times
        if(!Time.isFirst) {
            Time.deltaTime = Time.time - lastTime;
            Time.deltaTimeFloat = (float) Time.deltaTime;
            Time.deltaTimeNano = Time.timeNano - lastTimeNano;

        } else {
            Time.deltaTime = 0.0;
            Time.deltaTimeFloat = 0.0f;
            Time.deltaTimeNano = 0;
            Time.isFirst = false;
        }
    }
}
