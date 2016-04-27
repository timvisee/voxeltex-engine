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

package com.timvisee.voxeltex.architecture.scene;

import com.timvisee.voxeltex.architecture.component.collider.primitive.SphereColliderComponent;
import com.timvisee.voxeltex.architecture.component.drawable.line.AxisDrawComponent;
import com.timvisee.voxeltex.architecture.component.light.LightSourceComponent;
import com.timvisee.voxeltex.architecture.component.mesh.filter.MeshFilterComponent;
import com.timvisee.voxeltex.architecture.component.mesh.renderer.MeshRendererComponent;
import com.timvisee.voxeltex.architecture.component.overlay.gui.GuiPanelComponent;
import com.timvisee.voxeltex.architecture.component.overlay.gui.menu.ToggleableMenuComponent;
import com.timvisee.voxeltex.architecture.component.rigidbody.RigidbodyComponent;
import com.timvisee.voxeltex.architecture.gameobject.GameObject;
import com.timvisee.voxeltex.architecture.prefab.camera.FpsCameraPrefab;
import com.timvisee.voxeltex.architecture.prefab.gui.GuiButtonPrefab;
import com.timvisee.voxeltex.architecture.prefab.gui.GuiLabelPrefab;
import com.timvisee.voxeltex.architecture.prefab.light.LightPrefab;
import com.timvisee.voxeltex.architecture.prefab.primitive.CubePrefab;
import com.timvisee.voxeltex.architecture.prefab.primitive.QuadPrefab;
import com.timvisee.voxeltex.engine.light.Light;
import com.timvisee.voxeltex.module.Color;
import com.timvisee.voxeltex.module.material.Material;
import com.timvisee.voxeltex.module.mesh.Mesh;
import com.timvisee.voxeltex.module.model.loader.ObjModelLoader;
import com.timvisee.voxeltex.module.resource.bundle.EngineResourceBundle;
import com.timvisee.voxeltex.module.shader.ShaderManager;
import com.timvisee.voxeltex.module.texture.Texture;
import com.timvisee.voxeltex.module.transform.rectangle.RectangleTransform;
import com.timvisee.voxeltex.module.transform.rectangle.anchor.RectangleTransformAnchor;
import com.timvisee.voxeltex.module.transform.rectangle.anchor.VerticalTransformAnchorType;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.glClearColor;

public class TestEnvironmentScene extends Scene {

    @Override
    public void load() {
        // Load the super
        super.load();

        // Set the skybox color
        // TODO: Move this to a better spot!
        glClearColor(0.9f, 0.9f, 0.9f, 1.0f);

        // Load the box texture
        Material boxMaterial = EngineResourceBundle.getInstance().MATERIAL_BOX;

        GameObject suzanneRoot = new GameObject("SuzanneRoot");
        suzanneRoot.getTransform().getAngularVelocity().set(0, 0.5f, 0);
        addGameObject(suzanneRoot);

        // Create a model that is loaded from a file
        for(int i = 0; i < 5; i++) {
            GameObject suzanneObject = new GameObject("Suzanne");
            suzanneObject.addComponent(new MeshFilterComponent(new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/suzanne.obj"))));
            suzanneObject.addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.RED, 1, 1))));
            suzanneObject.getTransform().getPosition().set(0, 1f, -2.0f + -6f * (i + 1));
            suzanneObject.getTransform().getAngularVelocity().set(0, -0.5f, 0);
            suzanneRoot.addChild(suzanneObject);
        }

        // Load the sphere mesh
        Mesh sphereMesh = new Mesh(ObjModelLoader.loadModelFromEngineAssets("models/sphere.obj"));

        // Spawn some spheres as collision test
        for(int i = 0; i < 16; i++) {
            if(Math.random() > 0.2) {
                GameObject sphereObject = new GameObject("Sphere" + i);
                sphereObject.addComponent(new MeshFilterComponent(sphereMesh));
                sphereObject.addComponent(new MeshRendererComponent(new Material(Texture.fromColor(Color.ORANGE, 1, 1))));
                sphereObject.addComponent(new RigidbodyComponent());
                sphereObject.addComponent(new SphereColliderComponent());
                sphereObject.getTransform().getPosition().set(-8.5f + (float) (Math.random() * 1.0f), 3f + (i * 2.1f), -0.5f + (float) (Math.random() * 1.0f));
                sphereObject.getTransform().getAngularVelocity().set(0, -0.5f + (float) (Math.random() * 1), 0);
                addGameObject(sphereObject);

            } else {
                CubePrefab cubeObject = new CubePrefab("Cube" + i);
                cubeObject.addComponent(new RigidbodyComponent());
                cubeObject.setMaterial(new Material(Texture.fromColor(Color.RED, 1, 1)));
                cubeObject.getTransform().getPosition().set(-8.5f + (float) (Math.random() * 1.0f), 3f + (i * 2.1f), -0.5f + (float) (Math.random() * 1.0f));
                cubeObject.getTransform().getAngularVelocity().set(0, -0.5f + (float) (Math.random() * 1), 0);
                addGameObject(cubeObject);
            }
        }

        // Light source object
        GameObject lightObject = new GameObject("Light");
        lightObject.addComponent(new LightSourceComponent(Light.LIGHT_TYPE_POINT, new Vector3f(0, 0, 1), 3));
        lightObject.getTransform().getPosition().set(-4, 1, 6);
        addGameObject(lightObject);

        // Create an object to render the center axis and grid
        GameObject gridObject = new GameObject("AxisGridRenderer");
        //gridObject.addComponent(new GridDrawComponent());
        gridObject.addComponent(new AxisDrawComponent());
        addGameObject(gridObject);

        // Create a basic cube
        CubePrefab baseObject = new CubePrefab();
        baseObject.getTransform().setPosition(new Vector3f(0, 0.5f, -3f));
        baseObject.getTransform().setAngularVelocity(new Vector3f(0, 0.5f, 0));
        baseObject.setMaterial(boxMaterial);
        addGameObject(baseObject);

        // Add a basic sub cube
        CubePrefab subObject1 = new CubePrefab();
        subObject1.getTransform().setAngularVelocity(new Vector3f(0.0f, 2.5f, 0.0f));
        subObject1.getTransform().setPosition(new Vector3f(1.5f, 1.5f, 0));
        subObject1.setMaterial(boxMaterial);
        baseObject.addChild(subObject1);

        // Add a basic sub cube
        CubePrefab subObject2 = new CubePrefab();
        subObject2.getTransform().setAngularVelocity(new Vector3f(0.0f, 3.0f, 0.0f));
        subObject2.getTransform().setPosition(new Vector3f(1.5f, 1.5f, 0));
        subObject2.setMaterial(boxMaterial);
        subObject1.addChild(subObject2);

        // Add a basic sub cube
        CubePrefab subObject3 = new CubePrefab();
        subObject3.getTransform().setPosition(new Vector3f(-1.5f, 1.5f, 0));
        subObject3.getTransform().setRotation(new Quaternionf(0.25f, 0, 0));
        subObject3.getTransform().setAngularVelocity(new Vector3f(0, -3.3f, 0));
        subObject3.setMaterial(boxMaterial);
        subObject1.addChild(subObject3);

        // Add a basic sub cube
        CubePrefab subObject4 = new CubePrefab();
        subObject4.getTransform().setPosition(new Vector3f(0, 1.35f, 0));
        subObject4.getTransform().setAngularVelocity(new Vector3f(3.1f, 4.2f, 2.9f));
        subObject4.setMaterial(boxMaterial);
        subObject3.addChild(subObject4);

        // Add a matrix of boxes
        for (int j = 0; j < 10; j++) {
            for(int i = 0; i < 15; i++) {
                // Load the texture shader
                CubePrefab matrixBox = new CubePrefab();
                matrixBox.getTransform().setPosition(new Vector3f(2 + 1.5f * j, 1, 7 - 1.5f * i));
                matrixBox.setMaterial(boxMaterial);
//                matrixBox.addComponent(new RigidbodyComponent());
                addGameObject(matrixBox);
            }
        }

        // Add some scaled boxes
        CubePrefab scaledBoxA = new CubePrefab();
        scaledBoxA.setMaterial(boxMaterial);
        scaledBoxA.getTransform().setPosition(new Vector3f(-6f, 1.5f, 4));
        scaledBoxA.getTransform().getScale().set(.5f, 1.5f, .5f);

        CubePrefab scaledBoxB = new CubePrefab();
        scaledBoxB.setMaterial(boxMaterial);
        scaledBoxB.getTransform().setPosition(new Vector3f(0, 1, 0));
        scaledBoxB.getTransform().getScale().set(1, 1, 2);
        scaledBoxA.addChild(scaledBoxB);

        CubePrefab scaledBoxC = new CubePrefab();
        scaledBoxC.setMaterial(boxMaterial);
        scaledBoxC.getTransform().setPosition(new Vector3f(0, 1, 0));
        scaledBoxC.getTransform().getScale().set(1, 1, 1);
        scaledBoxB.addChild(scaledBoxC);
        addGameObject(scaledBoxA);

        // Load the sand texture
        Texture sandTexture = EngineResourceBundle.getInstance().TEXTURE_GROUND;
        Material sandMaterial = new Material(ShaderManager.SHADER_DEFAULT_TEXTURED, sandTexture);
        sandMaterial.getTiling().set(10.0f);

        QuadPrefab quad = new QuadPrefab("SandSurface", new Vector2f(20.0f, 20.0f));
        quad.setMaterial(sandMaterial);
        quad.addComponent(new RigidbodyComponent(true));
        addGameObject(quad);

        // Add a light
        LightPrefab pointLight = new LightPrefab("LightPrefab", Light.LIGHT_TYPE_POINT, new Color(0xFFF4D6).toVector3f(), 25);
        pointLight.getTransform().getPosition().set(0, 15, 0);
        addGameObject(pointLight);

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFFF4D6).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create the main camera object
        FpsCameraPrefab fpsCameraPrefab = new FpsCameraPrefab();
        fpsCameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
        addGameObject(fpsCameraPrefab);

        // Overlay test
        GameObject menuPanel = new GameObject("MenuPanel");
        menuPanel.addComponent(new RectangleTransform(
                new Vector2f(0, 0),
                new Vector2f(350, 165),
                new RectangleTransformAnchor(0.5f, 0.6f, 0.5f, 0.6f)
        ));
        menuPanel.addComponent(new GuiPanelComponent());
        addGameObject(menuPanel);

        GuiLabelPrefab menuTitle = new GuiLabelPrefab("MenuLabel", "Menu");
        menuTitle.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        menuTitle.getRectangleTransform().setPositionTop(-(20 + 16)); // TODO: Invert this when stretched?
        menuTitle.setColor(Color.WHITE);
        menuPanel.addChild(menuTitle);

        GuiButtonPrefab button = new GuiButtonPrefab("NewGameButton", "New Game");
        button.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8))); // TODO: Invert this when stretched?
        menuPanel.addChild(button);

        GuiButtonPrefab button2 = new GuiButtonPrefab("ExitButton", "Exit");
        button2.getRectangleTransform().setVerticalAnchorPreset(VerticalTransformAnchorType.TOP);
        button2.getRectangleTransform().setPositionTop(-(20 + 16 + (40 + 8) * 2)); // TODO: Invert this when stretched?
        menuPanel.addChild(button2);

        // Create a menu controller
        GameObject menuController = new GameObject("MenuController");
        menuController.addComponent(new ToggleableMenuComponent(menuPanel));
        addGameObject(menuController);
    }
}
