#KeyBarricade

Key Barricade is a simple game made as challenge for college.
This game brings a custom rendering engine named VoxelTex written from scratch using OpenGL.
See the paragraph below for information on how to run the project.

![KeyBarricade with VoxelTex v0.1 Alpha](http://i.imgur.com/6A82f72.jpg)

---

### Running

#####Build:
The latest available runnable Key Barricade build can be downloaded from it's builds page: http://ci.xephi.fr/job/KeyBarricade/
You may also compile the project sources to create your very own build.

#####Running requirements:
>- Java 1.8 or higher
>- OpenGL 2.1 or higher
>- Windows or Linux operating system

Note: Mac OS X support has been dropped, due to various problems related to the operating system. It's unlikely this platform will officially be supported again in the future.

---

### Compiling

#####Compiling requirements:
>- JDK 1.8
>- Maven
>- Git/GitHub (Optional)

#####How to compile:
>- Clone the project with Git/GitHub
>- Select the `windows` or `linux` profile.
>- Compile with `mvn clean install -B`
>- Execute the `KeyBarricade-*.jar` file.

---

_I've included some VoxelTex engine usage examples.
This guide shows you step by step how to create your first few scenes, covering things like object creation and lighting:_

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
_[Continue the guide/tutorial here](https://github.com/timvisee/KeyBarricade/tree/master/src/main/java/com/timvisee/voxeltex/example/example1#voxeltex-engine---example-one)._