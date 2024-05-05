# Minesweeper

## Setup and Run it
1. You must have the JDK 17
2. Open as project in any Java IDE, like IntelliJ, Eclipse, Netbeans...
3. Then you can run this project

## Functionalities
- Board Generation
- Mines Placement

## Architecture Design

### MVP Architecture
Develop using MVP architecture: 
```
Model (Game engine) --> Presenter --> View
```

### Singleton Design Pattern
Design that there was only one engine: 
```java
public static Engine getInstance(int gridWidth, int gridHeight, int numMines) {
    if (instance == null) {
        instance = new Engine(gridWidth, gridHeight, numMines);
    }
    return instance;
}
```