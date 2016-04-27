# VoxelTex Engine - Example two
This is an continuation of the first example [which is available here](https://github.com/timvisee/VoxelTex-Engine/tree/master/src/main/java/com/timvisee/voxeltex/example/example1#voxeltex-engine---example-one).



### Introduction
In this example, we'll be using the basis of the previous example.
This example add's a few new features to the scene we left off with.
These additional things make the set up just a little more complex while giving great visual results.
Even though the set up is similar, we'll be using a new set of classes to ensure we've a clean start.
We'll be using the `Example2...` files for this example.


### Our second scene
Once again leave the engine and scene loader classes as they are so we can get straight into the set up of the scene itself.
Please open the `Example2Scene` file to start.

You'll probably recognize various parts of this scene from the previous example.
Things like the light simulating the sun is still in there and are left unchanged.
By the way, you'll be seeing a light simulating the sun in most, if not all scenes, since it's a great light source to start with.

We've made some minor improvements to this scene. We'll go over them now:

* After the creation of the _sun_ light, we're adding a `GroundPrefab` to the scene.
  This is another great example of the prefab usage.
  This prefab adds a ground/surface to our scene we can put other objects on.
  The awesome thing about this prefab is that it's textured by default with a ground texture provided with the VoxelTex engine.
  In this example, we're just adding it for ascetics so we can see the visual effects of the following objects better.

* The _ground_ object is followed by the FPS camera.
  This is exactly the same as the previous example, except for the fact that we've moved it upwards a bit.
  Remember, the default position of all objects is `(0, 0, 0)`.
  The ground will be at this exact position since we haven't specified it to something else.
  The camera in our previous example, was at a height of `(y = 0)`.
  If we'd be using the same position now, the camera would spawn inside the ground.
  Although it's perfectly fine, it doesn't look too good.
  So, move it up a bit, and problem is covered!

* The next part is where we spawn a row of 10 cubes.
  Spawning a row of cubes is quite simple, just use the building blocks the Java programming language provides you, the `for-loop`.
  In this case we've created a for-loop from 0 to 9 spawning our cubes.
  Of course, you've to use the loop's index value for the position of each cube.
  All cubes would spawn in the same position (inside each other) if we didn't.
  By using `box.getTransform().setPosition(0.5f + 2.0f * i, 0.5f, 0.5f);` we ensure the series of cubes is spawned with a spacing of two on the x-axis.
  The engine takes care of the boxes we create after adding them to the scene using `addGameObject(box);`.

* To make things a little bit more exciting, we'll make each of the cubes emit light.
  We can achieve this by adding a `LightSourceComponent` to the game object as we've mentioned in the first example.
  After we've called `addGameObject(box);` we're creating and adding a light source component to the spawned box using
  `box.addComponent(new LightSourceComponent(Color.random()));`.
  The light source component constructor takes a few parameters, in this case, the color of the light we want the box to emit.
  To give the box a random lighting color, you can use `Color.random()` which gives us a random color to work with.
  Now we've added a light source component to the game object with a random color.
  Each box should now emit a random light color. Yes, it was _that_ easy, how cool!

  You might be wondering if adding a light source component after adding the game object works,
  because we've added the game object to the scene, so VoxelTex takes care of it.
  Then we're adding the light source component after adding it, that's wrong!
  Actually, no. Adding components or children, removing them, modifying them or anything like that after the game object has been added is perfectly fine.
  Even if the scene has been running for minutes, no problemo.
  We'll be getting into dynamically changing components and children at runtime later.
  Anyhow, VoxelTex takes care of it and does the heavy work to ensure everything goes the way you'd like it to be!



### Viewing our scene
We've now upgraded our scene with a ground, a series of boxes and some fancy lighting.
Yes, we've been able to achieve all of this simply by adding some lines of code.
Time to see the result:

Run the `Example2` class the same way you ran the first example, let the scene load and watch the magic happen!


### Additional examples and tutorials
This is it for now, regarding the examples and tutorials for the VoxelTex engine.
Of course, you can tweak as much as you like on the given examples to find some amazing results or create some awesome game project!

If you'd like to see more of these to help you out understanding the engine, please contact @timvisee .