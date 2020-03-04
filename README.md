# package-delivery
-----------------------------------------------------------------------------------
This is an simple console application for processign packages (as simulation)

# Installation

The app is build using Gradle tool. You dont have to install a gradle. You can build and run program by just using the Gradle wrapper
which is included in the project source code.

You can build the project with: `./gradlew clean build`

# Run

You can run the program using Gradle run, I recommend to use --console=plain to have a clean console output.

`./gradlew run --console=plain`

# App arguments
  1 - filename -> path to a file where are stored packages which then are preloaded on the start

# Run with arguments

 `./gradlew run --args='/path/to/the/input/file' --console=plain`

# Input Data
Date format for an input is: 
* Package weight -> Decimal value, with maximum three decimal points
* Postal Number -> String consisting of 5 digits

Example: `3.5 13000`

Data can be loaded from the file at the start or interactivly from console (user needs to confirm the input by ENTER)

# Output

Output is emitted every 60 seconds as sum of weights aggregated by their postal number

Example: `13000 10.0`

# Exiting a Program

You can exit the program by typing `quit`

-------------------------------------------------------------------
Have a fun!!!



