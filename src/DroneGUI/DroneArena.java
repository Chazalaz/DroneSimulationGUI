package DroneGUI;

import javafx.scene.image.Image;

import java.util.Random;
import java.util.ArrayList;

public class DroneArena {
    int arenaX;
    int arenaY;
    ArrayList<Drone> droneList = new ArrayList<Drone>();
    Drone d;
    int valX;
    int valY;

    /** Above are predefined variables, arenaX and arenaY are coordinates of the drone arena, created an arraylist for
    * DroneGUI.Drone to store all the Drones and their information. Defined a drone: d. Finally defined valX and valY which
    * act as the drones coordinates in addDrone(). The last two were added later on in the project as an unresolved
    * error appeared and the only way to fix this issue was to pre define the variables.
     */

    public int getX() {
        return arenaX;
    }
    public int getY() {
        return arenaY;
    }
    /** Setters and getters to return the arena coordinates
     */

    public DroneArena(int X, int Y){
        arenaX = X;
        arenaY = Y;
    }
    /** A method to define arenaX and arenaY as X and Y respectively to be used in certain functions such as getDroneAt
    * below.
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
    /** The addDrone function adds a drone to the droneList, it first creates a variable called random through of type
    * Random, this gives the newly added drone a random set of coordinates. The if statement sees if the size of
    * droneList is smaller than the product of the X and Y coordinates of arena. It then uses a do loop to create
    * random X and Y coordinates. Then a while loop to get the drone coordinates using getDroneAt while they are not
    * equal to null to make sure the coordinates are valid, it then adds d as a new DroneGUI.Drone with the newly created
    * coordinates, as well as the randomly created direction seen later, it then adds a drone d to droneList
     */

    public String toString() {
        StringBuilder s = new StringBuilder("The arena size is " + arenaX + " x " + arenaY + " and: " + "\n");
        for (Drone d : droneList) {// All the drones in the droneList will be used here and will show all their information
           s.append(d.toString()).append("\n");
        }
        return s.toString();
    }

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

    public boolean canMoveHere(int valX, int valY){
        return getDroneAt(valX, valY) == null && valX < arenaX && valY < arenaY && valX >= 0 && valY >= 0;
    }

    public void moveAllDrones(){
        for(Drone d : droneList){
            d.tryToMove(this);
        }
    }


    public void drawSystem(MyCanvas mc) {
        for (Drone d : droneList) {
            d.displayDrone(mc);
        }
    }
    /** This toString uses the StringBuilder to create a new string s, this string then uses arenaX and arenaY to show
    * the size of the arena that was created. It then uses a for loop to iterate through the droneList and appends the
    * String s to add the toString for d that we saw previously, and that is also appended to add a new line.
     */

}
