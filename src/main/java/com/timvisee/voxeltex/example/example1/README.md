# VoxelTex Engine - Example one
This is the first VoxelTex usage example.



### Introduction
The engine loads a scene, which is comparable to a level, a map or a world. The engine then simulates and renders the
loaded scene. You can make one or more different scenes for the game or application you're building.
For example: a scene can be used for the main menu of your application, with a series of other scenes for each level.
You can tell tell the engine to load a new scene, if you'd like to load a new level.
Please note that you must load a scene on startup, or the engine wouldn't have anything to simulate or render.

##### Scenes
A scene can be created by making a new class which extends _Scene_ which is part of the engine's
development kit. This requires you to have a load() method in your scene class. This method is called automatically
when the VoxelTex engine is loading your scene.

##### Camera
In a scene, it's required to have a camera. This camera object is used to determine what perspective to render the scene from.
The engine wouldn't know what to render if no camera is added to the scene.
Now, if you'd like to add a human shaped player to a scene whom can look around by moving the mouse,
you can attach a camera to the head of the player.
By doing this, the engine knows it has to render the scene from the player's perspective, namely from it's eyes.
Then, you can move the viewing perspective by moving the camera object around.

##### Prefab's
The VoxelTex engine contains many predefined objects. We call these prefab's (which stands for Prefabricated).
For example, there already is a bunch of different camera's available.
This makes the development process much easier and allows us to create our first scene with all required components in a matter of minutes.



### Our first scene
In this example scene, our first scene, we'll be putting a box in the middle of the scene with a camera to look and move around.

##### Prefab's, prefab's and prefab's
For this, we'll be using some prefab's that VoxelTex provides to make our life a little bit easier.
This makes it as simple as `addGameObject(new FpsCameraPrefab());` to add a fancy movable camera to our scene.

##### Loading our example application with our scene
However, before we can create and load a scene, we've to initialize and start the VoxelTex engine since we can't just render a scene out of thin air.
This is a fairly similar progress for all projects that use the VoxelTex engine.
That's why we'll be using a template in all of the examples.
The `Example1` class has the main method we can start the example application with.
This class is quite simple and loads the `Example1App` class, this is the actual class which initializes the engine as we've mentioned.
After the engine has loaded, this class loads the `Example1Scene` scene.
From that point, the engine will start simulating and rendering our scene.
We won't get into the details of this initialization and starting progress, just so we can focus on the fun things, the scene we'll be creating.
_Please note that it's recommended to use this template for your own projects too._

##### Constructing our scene
Now, please open the scene class called `Example1Scene` which we'll be working with.
This scene already contains the example code, just to allow you to see how things are done.

We'll be going over what's going on in this scene now:

* Our scene class has to extend the `Scene` class provided by the VoxelTex so the engine can recognize it as scene class.
  Then, we've added the `load()` method which is required by the `Scene` class. This method is called as soon as the scene is loaded.
* First, we've added a light which is simulating the sun.
  This is done to ensure we'll be able to see anything through the camera in the scene.
  It's much like the real world, if our sun would suddenly go away, we wouldn't be able to see anything anymore.
  This of course, brings a whole bunch of other problems with it regarding the living conditions of humans,
  but whatever, that's not something to cover in this example.
* After we've added a light, we're adding an FPS camera. Here, we use a prefab again for ease of use.
  This 'FPS camera' is a camera object you can move around with the W, A, S and D keys and the mouse.
  Of course, in most games you'd lock the camera to a player or anything else to ensure the player can't look at enemy positions or anything like that.
  But in this case, it's just really useful because it allows us to inspect the scene from any perspective we'd like once it's loaded.

  You might have noticed that we're setting the position of the camera using `camera.getTransform().setPosition(0, 0, 4)`.
  The simple reason for that is, that we don't want the camera to be inside of the object we'll spawn with the next step.
  All objects added to a scene have a default position of `(0, 0, 0)` on the `(x, y, z)` axis.
* Lastly, we're creating a simple box in the middle of the scene.
  We simply achieve this by using the `CubePrefab` prefab. This prefab renders a cube for us at the position of the game object.
  Cube's don't really have a texture by default. They're completely black which makes them hard to see.
  That's why we're changing it's material to one that VoxelTex provides out of the box. This is done with the
  `box.setMaterial(EngineResourceBundle.getInstance().MATERIAL_BOX);` line. We won't really get into the details of this.

  So. I know... yeah... a cube... just so we've something to look at.
  There's nothing better than a simple yet beautiful cube we've created our self that we can view from whatever angle we'd like.



### Viewing our scene
That sums up the creation of our scene. Objects that are added to a scene stay in the scene, until they're explicitly removed.
That's very useful for our example because that means we don't have to do anything else to run and view our scene.
We don't have to code anything else manually to be able to view our scene. _No additional time required to get a headache._
So, everything is handled by the VoxelTex engine for us, how incredibly nice!

Like I've mentioned a second ago, our scene is ready to be viewed. Simply execute the example application by running the `Example1` class and enjoy!