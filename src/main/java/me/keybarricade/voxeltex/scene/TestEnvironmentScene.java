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

package me.keybarricade.voxeltex.scene;

import me.keybarricade.gameobject.KeyPickupPrefab;
import me.keybarricade.voxeltex.component.camera.CameraComponent;
import me.keybarricade.voxeltex.component.collider.primitive.SphereColliderComponent;
import me.keybarricade.voxeltex.component.drawable.line.AxisDrawComponent;
import me.keybarricade.voxeltex.component.light.LightSourceComponent;
import me.keybarricade.voxeltex.component.mesh.filter.MeshFilterComponent;
import me.keybarricade.voxeltex.component.mesh.renderer.MeshRendererComponent;
import me.keybarricade.voxeltex.component.overlay.font.BitmapFontOverlayComponent;
import me.keybarricade.voxeltex.component.overlay.gui.GuiButtonPrefab;
import me.keybarricade.voxeltex.component.overlay.gui.GuiPanelComponent;
import me.keybarricade.voxeltex.component.overlay.shape.LineOverlayComponent;
import me.keybarricade.voxeltex.component.overlay.shape.RectangleOverlayComponent;
import me.keybarricade.voxeltex.component.rigidbody.RigidbodyComponent;
import me.keybarricade.voxeltex.component.transform.HorizontalTransformAnchorType;
import me.keybarricade.voxeltex.component.transform.RectangleTransform;
import me.keybarricade.voxeltex.component.transform.RectangleTransformAnchor;
import me.keybarricade.voxeltex.component.transform.VerticalTransformAnchorType;
import me.keybarricade.voxeltex.gameobject.GameObject;
import me.keybarricade.voxeltex.light.Light;
import me.keybarricade.voxeltex.material.Material;
import me.keybarricade.voxeltex.mesh.Mesh;
import me.keybarricade.voxeltex.model.loader.ObjModelLoader;
import me.keybarricade.voxeltex.prefab.camera.FpsCameraPrefab;
import me.keybarricade.voxeltex.prefab.light.LightPrefab;
import me.keybarricade.voxeltex.prefab.primitive.CubePrefab;
import me.keybarricade.voxeltex.prefab.primitive.QuadPrefab;
import me.keybarricade.voxeltex.shader.ShaderManager;
import me.keybarricade.voxeltex.texture.Image;
import me.keybarricade.voxeltex.texture.Texture;
import me.keybarricade.voxeltex.util.Color;
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
        Texture boxTexture = Texture.fromImage(Image.loadFromEngineAssets("images/box.png"));
        Material boxMaterial = new Material(boxTexture);

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
        Texture sandTexture = Texture.fromImage(Image.loadFromEngineAssets("images/sand.png"));
        //Texture rockTextureNormal = Texture.fromImage(Image.loadFromEngineAssets("images/rock_normal.png"));
        Material sandMaterial = new Material(ShaderManager.SHADER_DEFAULT_TEXTURED, sandTexture);
        sandMaterial.getTiling().set(3.0f);

        QuadPrefab quad = new QuadPrefab("SandSurface", new Vector2f(20.0f, 20.0f));
        quad.setMaterial(sandMaterial);
        quad.addComponent(new RigidbodyComponent(true));
        addGameObject(quad);

        // Add a light
        LightPrefab pointLight = new LightPrefab("LightPrefab", Light.LIGHT_TYPE_POINT, new Color(0xFDDC5C).toVector3f(), 25);
        pointLight.getTransform().getPosition().set(0, 15, 0);
        addGameObject(pointLight);

        // Add a sun
        LightPrefab sunLight = new LightPrefab("Sun", Light.LIGHT_TYPE_DIRECTIONAL, new Color(0xFDDC5C).toVector3f(), 0.3f);
        sunLight.getTransform().getRotation().set(90, 45, 90).normalize();
        sunLight.getTransform().getPosition().set(-5, 1, -3);
        addGameObject(sunLight);

        // Create the main camera object
//        FpsCameraPrefab fpsCameraPrefab = new FpsCameraPrefab();
//        fpsCameraPrefab.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
//        addGameObject(fpsCameraPrefab);
        GameObject cameraObject = new GameObject("Camera");
        cameraObject.getTransform().setPosition(new Vector3f(0.5f, 1.50f, 5.0f));
        cameraObject.addComponent(new CameraComponent());
        addGameObject(cameraObject);

        // Add a key prefab
        KeyPickupPrefab keyObject = new KeyPickupPrefab();
        keyObject.getTransform().getPosition().set(-1, 0, 0);
        addGameObject(keyObject);

        // Overlay test
        GameObject overlayTest = new GameObject("OverlayTest");
        overlayTest.addComponent(new RectangleTransform(
                new Vector2f(0, 0),
                new Vector2f(400, 150),
                new RectangleTransformAnchor(0.5f, 0.75f, 0.5f, 0.75f)
        ));
        overlayTest.addComponent(new GuiPanelComponent());
        overlayTest.addComponent(new RectangleOverlayComponent(0.05f, 0.05f, 0.05f, 0.05f));
        overlayTest.addComponent(new LineOverlayComponent(0.05f, 0.05f, 0.05f, 0.05f));
        overlayTest.addComponent(new BitmapFontOverlayComponent(new Vector2f(0.1f, 0.1f), 0.075f, "GPU string rendering", Color.RED));
        addGameObject(overlayTest);

//        GameObject overlayTest2 = new GameObject("OverlayTest2");
//        overlayTest2.addComponent(new RectangleTransform(
//                new Vector2f(0, 0),
//                new Vector2f(50, 50),
//                HorizontalTransformAnchorType.CENTER, VerticalTransformAnchorType.MIDDLE
//        ));
//        overlayTest2.addComponent(new GuiPanelComponent());
//        overlayTest.addChild(overlayTest2);

        GuiButtonPrefab button = new GuiButtonPrefab("Button", "Button to awesomeness");
        overlayTest.addChild(button);
    }
}
