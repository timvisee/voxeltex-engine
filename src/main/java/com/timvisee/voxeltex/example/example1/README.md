# VoxelTex Engine - Example one
This is the first VoxelTex usage example.

_Note: This is a basic example, and is meant to show some basic features of the VoxelTex engine.
This only covers a small part of the available features.
In-depth information about some topics handled bellow might be missing._



### Introduction
Let me give a little introduction about the VoxelTex engine before we get into the fun stuff.
Just to help you understand a little bit better how the engine handles things like _levels_ or _worlds_.
Then, we'll be getting into the creation of our first scene. You'll love it, I promise. Here we go:

##### VoxelTex's design pattern
The VoxelTex engine uses a `Scene`, `GameObject`, `Component` based design.
We'll explain what these these three different things are in a minute.
It's just important to remember that you should follow this design pattern when creating your own projects.
You aren't forced to use this, but it'll save you a headache later.
This design pattern really helps to keep your project organized, and it makes your code snippets reusable all over your project.
Besides, the basic features VoxelTex provides you with use pattern too. That's one type of puzzle piece that fits all.

##### Scenes
The engine loads a scene, which is comparable to a level, a map or a world. The engine then simulates and renders the
loaded scene. You can make one or more different scenes for the game or application you're building.
For example: a scene can be used for the main menu of your application, with a series of other scenes for each level.
So yeah, a scene can hold literally anything you'd like, not just a level for your game.
You can tell tell the engine to load a new scene, if you'd like to load a new level.

A scene would look something like this:
![Scene](http://i.imgur.com/6Gt92Ca.png)
The scene is represented as the green area. The blue things inside it are game objects, which you can add to a scene.

A scene can be created by making a new class which extends _Scene_ which is part of the engine's
development kit. This requires you to have a `load()` method in your scene class. This method is called automatically
when the VoxelTex engine is loading your scene.
Please note that you must load a scene on startup, or the engine wouldn't have anything to simulate or render.

##### Game objects
A scene by itself, is just an empty place. Everything you'll be adding to your scenes are game objects.
So, if you'd like to add a box to your scene, you've to add a game object to your scene that represents a box.
That might be hard to understand at first, but you'll see what I mean when we get into the coding.

If we'd be creating a racing game, you'd use a whole bunch of different game objects.
You'd use a game object for the car, and also for things like bystanders, the finish line and the road you're driving on.

A game object would look something like this:
![Game object](http://i.imgur.com/Jf2qVXA.png)
In this case, this is a Car, from the racing game we were talking about.
Note, that all game objects have a `Position`, `Rotation` and `Scale` property. These three properties combined are inside the `Transform` of the game object.
These define whether the game object is in the scene, how it is rotated and what sizing factor it has.
You'll discover how these work when we're starting with the code for our first scene.

The darker objects on the side (the Wheel objects, the engine and the door) are also game objects.
These objects aren't just added to the scene, instead, they're added as _Child_ to the Car object.
So, in this case the Car object has 6 children. These 6 objects are _inside_ the Car object.
When the car moves, the objects inside it automatically move along with it. If the car would be driving forward, the wheels would stay on the car.
If you'd just have added the wheels to the scene, the wheels would be left behind. You can of course, write some code that ensures the wheels stay on the car.
But why would you do that, if it can be a simple as adding the game objects to the car itself as children.
This also takes care of rotation, to ensure the wheels stay in the correct position on the car when it's rotating.
That would have been really complex if you had to make the wheels stay attached yourself. Of course, this wouldn't just work with the positional and rotational properties, scaling childs works too.

All child game objects have the position, rotation and scale properties too. These are relative to the parent.
If you'd give a wheel a `x = 1` position, that wheel would always be _one x_ in front of the car, even if the car was moving.

Children can have their own children too. Adding a game object to a wheel that represents a tire is possible for sure.
You can create one big family of wheels, tires, and so on!

Oh, and it's not just physical _things_ that are game objects.
If you would like to add some code that keeps track of the number of enemies in a level, you can put that in a game object too.
You might then want to name that object `EnemyTracker`.
The EnemyTracker object wouldn't be visible in the scene, nor would it allow you to interact with it, and that's perfectly fine.
The EnemyTracker would really by useless if it didn't do anything else than tracking enemies, but it might add/spawn new enemies if only a few are left.

##### Components
Game objects don't really do anything on their own.
They only have a position, rotation and scale, but they aren't interactable yet. Besides, you can't even see them.

You can add different components to a game object to achieve different things.
You can add a `RendererComponent` component to make the player visible.
Adding a `WasdMovementComponent` component makes the player movable with the W, A, S and D keys.
There are dozens of components provided by the VoxelTex engine, and of course, you can create your own.

Look at this example:
![Component](http://i.imgur.com/cRbeYz6.png)
Here we add the Renderer and Movement components to the game object to make it a 'player'.

Make as many components for your own project as you like.
You can attach these to any game object you'd like, which makes your code reusable, even over multiple projects.

And of course, if you'd like your player to emit light... All you have to do is add a `LightSourceComponent` component.
That might not be the _brightest_ idea for a player, but it's fun for sure.

##### Camera
In a scene, it's required to have a camera. A camera is a component (the `CameraComponent`) you can add to any game object.
This camera component is used to determine what perspective to render the scene from.
The engine wouldn't know what to render if no camera is added to the scene.
Now, if you'd like to add a human shaped player to a scene whom can look around by moving the mouse,
you can attach a camera to the head of the player.
By doing this, the engine knows it has to render the scene from the player's perspective, namely from it's eyes.
Then, you can move the viewing perspective by moving the camera object around.

##### Prefab's
The VoxelTex engine contains many predefined game objects. We call these prefab's (which stands for Prefabricated).
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

##### The floor is gone!
When running our created scene, you'll notice a floor is missing. The box is just floating in the air, like magic!
This is right, scenes are completely empty when created thus everything has to be added explicitly.
Simple things like a floor are also represented as game objects (the `GroundPrefab` prefab can be used for this), everything is!
That might sound weird at first, but this is actually really great and useful.
This allows you to literally make anything you'd like.
If you'd be making a space simulator, a _default_ floor could really get in the way.
We don't really have floating floors in our universe. ...right?
Simply add everything you'd need in your scene on creation


### Upgrading our scene
In the second part of this example, we'll be adding a series of boxes using a for-loop with some fancy lighting.
[Go to the continuation here](https://github.com/timvisee/VoxelTex-Engine/tree/master/src/main/java/com/timvisee/voxeltex/example/example2#voxeltex-engine---example-two).