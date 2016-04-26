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

package com.timvisee.voxeltex.architecture.component.overlay.gui.menu;

import com.timvisee.voxeltex.architecture.component.BaseComponent;
import com.timvisee.voxeltex.architecture.gameobject.AbstractGameObject;
import com.timvisee.voxeltex.runtime.global.Input;
import com.timvisee.voxeltex.runtime.input.mouse.MouseInputManager;
import org.lwjgl.glfw.GLFW;

public class ToggleableMenuComponent extends BaseComponent {

    /**
     * The key that can be used to toggle the menu.
     */
    private int toggleKey = GLFW.GLFW_KEY_ESCAPE;

    /**
     * True to show the menu, false to hide.
     */
    private boolean showMenu = false;

    /**
     * The game object containing the menu.
     */
    private AbstractGameObject menuObject;

    /**
     * Stores the original mouse cursor mode when the menu panel is shown.
     */
    private int originalMouseCursorMode = MouseInputManager.CURSOR_MODE_NORMAL;

    /**
     * Constructor.
     *
     * @param menuObject Menu game object.
     */
    public ToggleableMenuComponent(AbstractGameObject menuObject) {
        this.menuObject = menuObject;
    }

    @Override
    public void create() { }

    @Override
    public void start() {
        // Call the super
        super.start();
    }

    @Override
    public void update() {
        // Check whether the menu key is pressed
        if(Input.isKeyDownOnce(this.toggleKey))
            toggleMenuVisibility();

        // Set the enabled state of the menu object to match the show menu flag
        if(this.menuObject != null)
            this.menuObject.setEnabled(this.showMenu);
    }

    /**
     * Get the menu toggle key.
     *
     * @return Menu toggle key.
     */
    public int getToggleKey() {
        return this.toggleKey;
    }

    /**
     * Set the menu toggle key.
     *
     * @param toggleKey Menu toggle key.
     */
    public void setToggleKey(int toggleKey) {
        this.toggleKey = toggleKey;
    }

    /**
     * Check whether the menu is visible.
     *
     * @return True if the menu is visible, false if not.
     */
    public boolean isMenuVisible() {
        return this.showMenu;
    }

    /**
     * Set whether the menu is visible.
     *
     * @param showMenu True if visible, false if not.
     */
    public void setMenuVisible(boolean showMenu) {
        // Make sure this changes the show menu flag
        if(this.showMenu == showMenu)
            return;

        // Toggle the show menu flag
        if(showMenu) {
            // Set the show menu flag
            this.showMenu = true;

            // Store the original mouse cursor mode
            this.originalMouseCursorMode = Input.getMouseCursorMode();

            // Set the current mouse cursor mode to normal
            Input.setMouseCursorMode(MouseInputManager.CURSOR_MODE_NORMAL);

            // Center the mouse cursor
            Input.centerMouseCursor();

        } else {
            // Set the show menu flag
            this.showMenu = false;

            // Restore the original mouse cursor mode
            Input.setMouseCursorMode(this.originalMouseCursorMode);
        }
    }

    /**
     * Toggle the menu visibility.
     */
    public void toggleMenuVisibility() {
        setMenuVisible(!isMenuVisible());
    }

    /**
     * Get the game object holding the menu.
     *
     * @return Menu game object.
     */
    public AbstractGameObject getMenuObject() {
        return this.menuObject;
    }

    /**
     * Set the game object holding the menu.
     *
     * @param menuObject Menu game object.
     */
    public void setMenuObject(AbstractGameObject menuObject) {
        this.menuObject = menuObject;
    }
}
