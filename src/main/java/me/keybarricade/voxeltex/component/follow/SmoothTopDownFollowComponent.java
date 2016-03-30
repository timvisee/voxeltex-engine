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

package me.keybarricade.voxeltex.component.follow;

import me.keybarricade.voxeltex.component.BaseComponent;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.global.Time;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class SmoothTopDownFollowComponent extends BaseComponent {

    /**
     * Target game object to follow.
     */
    private GameObject target;

    /**
     * Height to hover above the followed game object.
     */
    private float height = 12.0f;

    /**
     * Positional damping.
     */
    private float positionDamping = 4.5f;

    /**
     * Rotational damping.
     */
    private float rotationDamping = 3f;

    /**
     * Target rotation, or null to ignore rotation.
     */
    private Quaternionf targetRotation = null;

    /**
     * Constructor.
     *
     * @param target Target.
     */
    public SmoothTopDownFollowComponent(GameObject target) {
        this.target = target;
    }

    /**
     * Constructor.
     *
     * @param target Target.
     */
    public SmoothTopDownFollowComponent(GameObject target, float height) {
        this.target = target;
        this.height = height;
    }

    /**
     * Constructor.
     *
     * @param target Target.
     */
    public SmoothTopDownFollowComponent(GameObject target, float height, float positionDamping) {
        this.target = target;
        this.height = height;
        this.positionDamping = positionDamping;
    }

    /**
     * Constructor.
     *
     * @param target Target.
     */
    public SmoothTopDownFollowComponent(GameObject target, float height, float positionDamping, float rotationDamping) {
        this.target = target;
        this.height = height;
        this.positionDamping = positionDamping;
        this.rotationDamping = rotationDamping;
    }

    /**
     * Constructor.
     *
     * @param target Target.
     */
    public SmoothTopDownFollowComponent(GameObject target, float height, Quaternionf targetRotation) {
        this.target = target;
        this.height = height;
        this.targetRotation = targetRotation;
    }

    /**
     * Constructor.
     *
     * @param target Target.
     */
    public SmoothTopDownFollowComponent(GameObject target, float height, float positionDamping, float rotationDamping, Quaternionf targetRotation) {
        this.target = target;
        this.height = height;
        this.positionDamping = positionDamping;
        this.rotationDamping = rotationDamping;
        this.targetRotation = targetRotation;
    }

    @Override
    public void create() {
        targetRotation = new Quaternionf(-1, 0, 0);
    }

    @Override
    public void start() {
        // Move the camera to it's initial position
        smoothUpdate(1f, 1f);
    }

    @Override
    public void update() {
        // Update the position
        smoothUpdate();
    }

    @Override
    public void destroy() { }

    /**
     * Smoothly update the position with the default parameters.
     */
    public void smoothUpdate() {
        smoothUpdate(this.positionDamping * Time.deltaTimeFloat, this.rotationDamping * Time.deltaTimeFloat);
    }

    /**
     * Smoothly update the position with the given position and rotation lerp factor.
     *
     * @param positionFactor Positional lerp factor.
     * @param rotationFactor Rotational lerp factor.
     */
    public void smoothUpdate(float positionFactor, float rotationFactor) {
        // Calculate the target position
        Vector3f targetPos = new Vector3f(target.getTransform().getWorldPosition());
        targetPos.add(0, height, 0);

        // Lerp to the target position with the specified position damping
        getTransform().getPosition().lerp(targetPos, positionFactor);

        // Lerp to the target rotation with the specified rotation damping if set
        if(this.targetRotation != null)
            getTransform().getRotation().nlerp(targetRotation, rotationFactor);
    }

    /**
     * Get the target game object that is being followed.
     *
     * @return Target game object.
     */
    public GameObject getTarget() {
        return this.target;
    }

    /**
     * Set the target game object that is being followed.
     *
     * @param target Target game object.
     */
    public void setTarget(GameObject target) {
        this.target = target;
    }

    /**
     * Get the height to hover above the target game object.
     *
     * @return Height.
     */
    public float getHeight() {
        return this.height;
    }

    /**
     * Set the height to hover above the target game object.
     *
     * @param height Height.
     */
    public void setHeight(float height) {
        this.height = height;
    }

    /**
     * Get the positional lerp damping factor.
     *
     * @return Positional lerp damping factor.
     */
    public float getPositionDamping() {
        return this.positionDamping;
    }

    /**
     * Set the positional lerp damping factor.
     *
     * @param positionDamping Positional lerp damping factor.
     */
    public void setPositionDamping(float positionDamping) {
        this.positionDamping = positionDamping;
    }

    /**
     * Get the rotational lerp damping factor.
     *
     * @return Rotational lerp damping factor.
     */
    public float getRotationDamping() {
        return this.rotationDamping;
    }

    /**
     * Set the rotational lerp damping factor.
     *
     * @param rotationDamping Rotational lerp damping factor.
     */
    public void setRotationDamping(float rotationDamping) {
        this.rotationDamping = rotationDamping;
    }

    /**
     * Get the target rotation if specified.
     *
     * @return Target rotation, or null to ignore rotation.
     */
    public Quaternionf getTargetRotation() {
        return this.targetRotation;
    }

    /**
     * Set the target rotation.
     *
     * @param targetRotation Target rotation, or null to ignore rotation.
     */
    public void setTargetRotation(Quaternionf targetRotation) {
        this.targetRotation = targetRotation;
    }
}
