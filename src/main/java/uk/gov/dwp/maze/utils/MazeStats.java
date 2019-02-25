package uk.gov.dwp.maze.utils;

import org.springframework.stereotype.Component;

import uk.gov.dwp.maze.model.Cell;

/**
 * A stats class to retain stats generated during the construction of the maze
 * and reused during the maze rendering.
 * 
 * @author gavinling
 *
 */
@Component
public class MazeStats {
    // Maze size
    private int mazeColCount;
    private int mazeRowCount;

    // stats
    int wallCount = 0;
    int pathCount = 0;
    int unknownCount = 0;
    int userTurnCount = 0;

    private Cell startCell;

    public void init() {
        mazeColCount = 0;
        mazeRowCount = 0;

        // stats
        wallCount = 0;
        pathCount = 0;
        unknownCount = 0;
        userTurnCount = 0;

        Cell startCell = null;
    }

    public int getMazeColCount() {
        return mazeColCount;
    }

    public void setMazeColCount(int mazeColCount) {
        this.mazeColCount = mazeColCount;
    }

    public int getMazeRowCount() {
        return mazeRowCount;
    }

    public int getWallCount() {
        return wallCount;
    }

    public int getPathCount() {
        return pathCount;
    }

    public int getUnknownCount() {
        return unknownCount;
    }

    public int getGameMoves() {
        return userTurnCount;
    }

    public Cell getStartCell() {
        return startCell;
    }

    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    public void incrementUserTurnCount() {
        userTurnCount++;
    }

    public void incrementUnknownCount() {
        unknownCount++;
    }

    public void incrementWallCount() {
        wallCount++;
    }

    public void incrementPathCount() {
        pathCount++;
    }

    public void incrementMazeRowCount() {
        mazeRowCount++;

    }

}
