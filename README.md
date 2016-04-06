#KeyBarricade

Key Barricade is a small game project for the SE students at the HHS.



#####Compiling Requirements:
>- JDK 1.8
>- Maven
>- Git/GitHub (Optional)

#####How to compile the project on Windows and Linux:
>- Clone the project with Git/GitHub
>- Select the `windows` or `linux` profile.
>- Compile with `mvn clean install -B`
>- Execute the `KeyBarricade-*.jar` file.

#####How to compile and run the project on Mac OS X:
_Due to a library starting the application with Java on the main thread is required, use this method to achive this:_
>- Clone the project with Git/GitHub
>- Select the `mac` profile.
>- Compile and run with `mvn clean install -B exec:exec`
You can manually run the executable JAR with the `-XstartOnFirstThread` parameter for JVM to solve this issue.

#####Running Requirements:
>- Java 1.8 or higher