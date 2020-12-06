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

    public Direction nextDirection() {
        int sizeOf = values().length - 1;
        return ordinal() == sizeOf ? values()[0] : values()[ordinal() + 1];
    }

    /** This function takes the values() method from getRandomDir and equals it to a new int variable
    * the function then returns ordinal which is the position in the enum declarations above. It then uses
    * a ternary operator to see if ordinal is equal to sizeOf, if it is then ordinal is equalled to index
    * 0 in values(), else it is equal to ordinal's initial value plus 1.
     */
}