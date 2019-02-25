package uk.gov.dwp.maze.utils;

import org.springframework.stereotype.Component;

/**
 * A bi-directional circular linked list of nodes (Bearings). 
 * e.g. <...> North<-> EAST <-> SOUTH <-> WEST <-> NORTH <...> 
 * where: If all you did was turn left, then the nodes presented would 
 * simulate a round robin style selection of compass bearings.
 *
 * Why bother? I wanted an object that I could provide the correct directions
 * available no matter which of the 4 compass directions I was facing.
 * 
 * Example. If I was facing North and asked the following:
 * compass.getleftDirection() -> West compass.getRightDirection() -> East
 * 
 * and so on.
 * 
 * 
 * 
 * @author gavinling
 *
 */
@Component
public class Compass {
    // this compass only needs to understand a list of 4 bearings
    private Bearing north = new Bearing(Direction.NORTH);
    private Bearing east = new Bearing(Direction.EAST);
    private Bearing south = new Bearing(Direction.SOUTH);
    private Bearing west = new Bearing(Direction.WEST);

    private Bearing currentBearing = north;

    public Compass() {
        // construct the compass, linking the nodes to form a circular linked list of
        // bearing objects
        addBearing(north, west, east);
        addBearing(east, north, south);
        addBearing(south, east, west);
        addBearing(west, south, north); // adding North here makes the circular links back to the first node

        init(Direction.NORTH);
    }

    // for the links between nodes, forwards and backwards
    private void addBearing(Bearing thisBearing, Bearing leftBearing, Bearing rightBearing) {
        thisBearing.leftBearing = leftBearing;
        thisBearing.rightBearing = rightBearing;
    }

    public void init(Direction direction) {
        switch (direction) {
        case EAST:
            currentBearing = east;
            break;
        case SOUTH:
            currentBearing = south;
            break;
        case WEST:
            currentBearing = west;
            break;

        default:
            currentBearing = north;
            break;
        }
    }

    public Direction getDirectionAhead() {
        return currentBearing.direction;
    }

    public Direction getDirectionLeft() {
        return currentBearing.leftBearing.direction;
    }

    public Direction getDirectionRight() {
        return currentBearing.rightBearing.direction;
    }

    public Bearing turnLeft() {
        currentBearing = currentBearing.leftBearing;
        return currentBearing;
    }

    public Bearing turnRight() {
        currentBearing = currentBearing.rightBearing;
        return currentBearing;
    }

    // A Node in the circular linked list
    private class Bearing {
        // the main payload of this node
        private Direction direction; // direction of this bearing

        // the links to other nodes in a list
        private Bearing leftBearing = null; // provides turning left knowledge relative to current direction
        private Bearing rightBearing = null; // provides turning right knowledge relative to current direction

        private Bearing(Direction direction) {
            this.direction = direction;
        }
    }

}
