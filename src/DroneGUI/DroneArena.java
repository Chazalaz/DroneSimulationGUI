package DroneGUI;

import javafx.scene.image.Image;

import java.util.Random;
import java.util.ArrayList;
/** Below are predefined variables, arenaX and arenaY are coordinates of the drone arena, created an arraylist for
 * DroneGUI.Drone to store all the Drones and their information. Defined a drone: d. Finally defined valX and valY which
 * act as the drones coordinates in addDrone(). The last two were added later on in the project as an unresolved
 * error appeared and the only way to fix this issue was to pre define the variables.
 */

public class DroneArena {
    int arenaX;
    int arenaY;
    ArrayList<Drone> droneList = new ArrayList<Drone>();
    ArrayList<Obstacle> obstacleList = new ArrayList<>();
    Drone d;
    Obstacle o;
    int valX;
    int valY;
    Drone count;


    /** Setters and getters to return the arena coordinates
     */

    public int getX() {
        return arenaX;
    }
    public int getY() {
        return arenaY;
    }


    /** A method to define arenaX and arenaY as X and Y respectively to be used in certain functions such as getDroneAt
     * below.
     */

    public DroneArena(int X, int Y){
        arenaX = X;
        arenaY = Y;
    }


    /** The addDrone function adds a drone to the droneList, it first creates a variable called random through of type
     * Random, this gives the newly added drone a random set of coordinates. The if statement sees if the size of
     * droneList is smaller than the product of the X and Y coordinates of arena. It then uses a do loop to create
     * random X and Y coordinates. Then a while loop to get the drone coordinates using getDroneAt while they are not
     * equal to null to make sure the coordinates are valid, it then adds d as a new DroneGUI.Drone with the newly created
     * coordinates, as well as the randomly created direction seen later, it then adds a drone d to droneList
     */

    public void addDrone() {
        Random random;
        random = new Random();

        if(droneList.size() < (arenaX * arenaY)){
            valX = random.nextInt(arenaX);
            valY = random.nextInt(arenaY);
            d = new Drone(valX, valY, Direction.getRandomDir());
            droneList.add(d);
        }
    }

    /**
     * Add Obstacle like Add drone above just adds an obstacle to the obstacleList and gives it a random set of
     * coordinates.
     */
    public void addObstacle() {
        Random random;
        random = new Random();

        if(obstacleList.size() < (arenaX * arenaY)){
            valX = random.nextInt(arenaX);
            valY = random.nextInt(arenaY);
            o = new Obstacle(valX, valY, Direction.getRandomDir());
            obstacleList.add(o);
        }
    }

    /**
     * toString function to return the arena size within the simulation
     * @return the message with the arena size.
     */
    public String toString() {
        StringBuilder s = new StringBuilder("The arena size is " + arenaX + " x " + arenaY + " and: " + "\n");
        for (Drone d : droneList) {// All the drones in the droneList will be used here and will show all their information
           s.append(d.toString()).append("\n");
        }
        for(Obstacle o : obstacleList){
            s.append(o.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     *  Gets the drone and its coordinates.
     * @param X X coordinate for the drones in the drone list
     * @param Y Y coordinate for the drones in the drone list
     * @return returns the drone that is in the list
     */

    public Drone getDroneAt(int X, int Y){
        Drone d = null;
        for(Drone a : droneList){
            if(a.isHere(X, Y)){
                return a = d;
            }else{
                return d;
            }
        }
        return d;
    }

    /**
     * Checks if a drone can move to a certain set of coordinates or not by checking if there are other drones there.
     * @return either true or false
     */

    public boolean canMoveHere(int valX, int valY){
        return getDroneAt(valX, valY) == null && valX < arenaX && valY < arenaY && valX >= 0 && valY >= 0;
    }


    /** This function attempts to move all the drones in the drone list which are iterated through with a for loop
     * it uses the function tryToMove, which is found in Drone
     */

    public void moveAllDrones(){
        for(Drone d : droneList){
            d.tryToMove(this);
        }
    }

    /** The above method basically iterates through all the obstacles in the array list and for every obstacle
     * @param(d) it prints it out onto the canvas.
     */

    public void drawSystem(MyCanvas mc) {
        for (Drone d : droneList) {
            d.displayDrone(mc);
        }
    }

    /** The above method basically iterates through all the obstacles in the array list and for every obstacle
     * @param(o) it prints it out onto the canvas.
     */
    public void drawObs(MyCanvas mc){
        for (Obstacle o : obstacleList){
            o.displayObstacle(mc);
        }
    }
}
