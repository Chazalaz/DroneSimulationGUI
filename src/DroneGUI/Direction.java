package DroneGUI;

import java.util.Random;

public enum Direction {
    North,
    East,
    South,
    West;

    /** This enumeration predefines constants for the direction that the drones are given
    * which in this case are the compass directions. This DroneGUI.Direction class is used later
    * in the DroneGUI.DroneArena and DroneInterface classes.
     */

    public static Direction getRandomDir() {
        Random randomDir = new Random();
        return values()[randomDir.nextInt(values().length)];
    }

    /** The getRandomDir function uses the 'Random' java library to choose a random direction
    * for the drones, this direction is given to a single drone, the next drone is given
    * another random direction
     */
}