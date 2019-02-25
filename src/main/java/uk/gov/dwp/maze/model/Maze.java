package uk.gov.dwp.maze.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import uk.gov.dwp.maze.exception.MazeException;
import uk.gov.dwp.maze.utils.Compass;
import uk.gov.dwp.maze.utils.Direction;
import uk.gov.dwp.maze.utils.MazeStats;

/**
 * The Maze model.
 * Held as a simple array of Cells. Where each Cell has a North, East, South & West
 * reference to its Neighbouring cells. 
 * The Maze has a CurrentLocation, which is the Explorers position within the maze. 
 * A 'compass' class is used to underatand the rotating list of directions as the user
 * turns left and right. Managing the relative left,Right and forward directions.
 * 
 * 
 * @author gavinling
 *
 */
@Component
@Scope("prototype")
public class Maze {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Compass compass;

    @Autowired
    private MazeStats mazeStats;

    @Autowired
    private MazeCreator mazeCreator;

    // the Maze cell data
    private Cell[] mazeCells;

    private Cell currentLocation;

    /**
     * Initialise a new maze. Validate the maze file syntax Establish max Col & Rows
     * Create Maze cells
     * 
     * @param fileName
     */

    public void load(String fileName) {
        initMap();

        mazeCells = mazeCreator.createMaze(fileName);
        
        compass.init(Direction.NORTH);

        currentLocation = mazeStats.getStartCell();
        currentLocation.visited();
        logger.info(String.format("Loaded map :%s",fileName));
    }

    public Cell getStartCell() {
        return mazeStats.getStartCell();
    }

    private void initMap() {
        mazeStats.init();
        this.mazeCells = null;

    }

    public Cell getCell(int colNumber, int rowNumber) {
        try {
            return mazeCells[(rowNumber * mazeStats.getMazeRowCount() + colNumber)];
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new MazeException(
                    String.format("Co-ordinate col:%s, row:%s exceeds maze bounds.", colNumber, rowNumber));
        }
    }

    public Cell getCurrentLocation() {
        return currentLocation;
    }

    public String directionAhead() {
        return compass.getDirectionAhead().name();
    }

    /**
     * change the direction we are facing.
     * 
     */
    public void turnLeft() {
        compass.turnLeft();
        mazeStats.incrementUserTurnCount();

    }

    /**
     * change the direction we are facing.
     * 
     */
    public void turnRight() {
        compass.turnRight();
        mazeStats.incrementUserTurnCount();
    }

    /**
     * change our location with the maze.
     * 
     */
    public void moveForward() {
        currentLocation = currentLocation.getCellInDirection(compass.getDirectionAhead());
        currentLocation.visited();
        mazeStats.incrementUserTurnCount();

    }

    /**
     * Can we move forwards ?
     * 
     * @return boolean
     */
    public boolean isForwardAvailable() {
        Cell forwardCell = currentLocation.getCellInDirection(compass.getDirectionAhead());
        return !(forwardCell.isWall() || forwardCell.isUnknown());
    }

    public boolean isFinish() {
        return currentLocation.isFinish();
    }

}
