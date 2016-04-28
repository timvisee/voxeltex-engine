[![Build status of master branch](https://circleci.com/gh/timvisee/VoxelTex-Engine/tree/master.svg?style=svg)](https://circleci.com/gh/timvisee/VoxelTex-Engine/tree/master)

#VoxelTex Engine

VoxelTex is a game engine written in Java.
It uses OpenGL and various other technologies to render on the screen using the GPU to achieve the best performance with great visual results.
VoxelTex uses a Scene, GameObject and Component architecture for ultimate code structure and reusability.
The engine provides many resources to streamline the development process.



---



### KeyBarricade - A game running on VoxelTex

![KeyBarricade with VoxelTex v0.1 Alpha](http://i.imgur.com/6A82f72.jpg)

The KeyBarricade has been developed as game example, running on the VoxelTex engine.
You can find the source code and executable here:

* [Source code (GitHub)](https://github.com/timvisee/KeyBarricade)
* [Executable (Jenkins)](http://ci.xephi.fr/job/KeyBarricade/)



---



### Tutorial - Engine explanation and development examples
A basic tutorial has been included with the VoxelTex engine.
This tutorial explains the architecture of the engine and covers things like scene creation, object creation, camera handling and lighting.
[Please check out the tutorial here](https://github.com/timvisee/VoxelTex-Engine/tree/master/src/main/java/com/timvisee/voxeltex/example/example1#voxeltex-engine---example-one).




---



### Engine requirements
>- Java 1.8 or higher
>- OpenGL 2.1 or higher
>- Windows or Linux operating system

Note: Mac OS X support has been dropped, due to various problems related to the operating system. It's unlikely this platform will officially be supported again in the future.

### Compiling

#####Compiling requirements
>- JDK 1.8
>- Maven
>- Git/GitHub _(Optional)_

#####How to compile
>- Clone the project with Git/GitHub
>- Select the `windows` or `linux` profile.
>- Compile with `mvn clean install -B`
>- Execute the `KeyBarricade-*.jar` file.