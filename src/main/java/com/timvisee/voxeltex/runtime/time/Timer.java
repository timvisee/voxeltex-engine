/******************************************************************************
 * Copyright (c) Tim Visee 2016. All rights reserved.                         *
 *                                                                            *
 * @author Tim Visee                                                          *
 * @website http://timvisee.com/                                              *
 *                                                                            *
 * Open Source != No Copyright                                                *
 *                                                                            *
 * Permission is hereby granted, free of charge, to any person obtaining a    *
 * copy of this software and associated documentation files (the "Software"), *
 * to deal in the Software without restriction, including without limitation  *
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,   *
 * and/or sell copies of the Software, and to permit persons to whom the      *
 * Software is furnished to do so, subject to the following conditions:       *
 *                                                                            *
 * The above copyright notice and this permission notice shall be included    *
 * in all copies or substantial portions of the Software.                     *
 *                                                                            *
 * You should have received a copy of The MIT License (MIT) along with this   *
 * program. If not, see <http://opensource.org/licenses/MIT/>.                *
 ******************************************************************************/

package com.timvisee.voxeltex.runtime.time;

public class Timer {

    /**
     * The time the timer has last started at in nanoseconds.
     */
    private long start = -1;

    /**
     * The time that has elapsed before the timer has been paused.
     */
    private long elapsed = -1;

    /**
     * Constructor.
     * Note: This doesn't start the timer.
     */
    public Timer() { }

    /**
     * Constructor.
     *
     * @param start True to immediately start the timer.
     */
    public Timer(boolean start) {
        // Start the timer
        if(start)
            start();
    }

    /**
     * Start the timer if it isn't running already.
     */
    public void start() {
        // Make sure the timer isn't currently running
        if(isRunning())
            return;

        // Set the start time
        this.start = System.nanoTime();
    }

    /**
     * Stop or pause the timer.
     */
    public void stop() {
        // Make sure the timer isn't running
        if(!isRunning())
            return;

        // Calculate the elapsed time, store it in the elapsed field and reset the start field
        this.elapsed += System.nanoTime() - this.start;
        this.start = -1;
    }

    /**
     * Restart the timer, this will reset the timer.
     */
    public void restart() {
        // Reset and start
        reset();
        start();
    }

    /**
     * Reset the whole timer. Calling this method will stop and reset the timer.
     * This will also reset the isStarted() flag.
     */
    public void reset() {
        // Reset the start and elapsed time
        this.start = -1;
        this.elapsed = -1;
    }

    /**
     * Check whether the timer is currently running.
     *
     * @return True if running, false if not.
     */
    public boolean isRunning() {
        return start != -1;
    }

    /**
     * Check whether the timer has ever been started. The timer doesn't have to start now.
     *
     * @return True if started, false if not.
     */
    public boolean isStarted() {
        return start != -1 || elapsed != -1;
    }

    /**
     * Get the number of elapsed nanoseconds while the timer was running.
     *
     * @return Number of nanoseconds.
     */
    public long getElapsedNano() {
        return (this.start != -1 ? System.nanoTime() - this.start : 0) + Math.max(this.elapsed, 0);
    }

    /**
     * Get the number of elapsed microseconds while the timer was running.
     *
     * @return Number of microseconds.
     */
    public long getElapsedMicro() {
        return getElapsedNano() / 1000;
    }

    /**
     * Get the number of elapsed milliseconds while the timer was running.
     *
     * @return Number of milliseconds.
     */
    public long getElapsedMillis() {
        return getElapsedMicro() / 1000;
    }

    /**
     * Get the elapsed time in seconds while the timer was running.
     *
     * @return Number of seconds.
     */
    public double getElapsedTime() {
        return (double) getElapsedNano() / 1000000000.0;
    }
}
