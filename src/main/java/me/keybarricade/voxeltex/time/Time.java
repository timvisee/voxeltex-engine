package me.keybarricade.voxeltex.time;

public class Time {

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
    public static double time = 0;

    /**
     * The time in nanoseconds the frame started rendering.
     */
    public static long timeNano = 0;

    /**
     * The time between the last frame and this frame in seconds.
     */
    public static double deltaTime = 0;

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
     */
    public static void update() {
        // Move the time and nano time to the last time
        double lastTime = time;
        long lastTimeNano = timeNano;

        // Store the current times
        time = Time.timer.getElapsedTime();
        timeNano = Time.timer.getElapsedNano();

        // Calculate the delta times
        if(!isFirst) {
            deltaTime = time - lastTime;
            deltaTimeNano = timeNano - lastTimeNano;

        } else {
            deltaTime = 0;
            deltaTimeNano = 0;
            isFirst = false;
        }
    }
}
