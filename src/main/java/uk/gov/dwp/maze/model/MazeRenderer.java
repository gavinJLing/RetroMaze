package uk.gov.dwp.maze.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uk.gov.dwp.maze.exception.MazeException;
import uk.gov.dwp.maze.utils.MazeStats;

/**
 * A class responsible for drawing 2 similar views of the Maze.
 * (S)how Maze - Recreates the maze map form the maze modle.
 * (H)istory   - Recreates the maze showing visited locations. This implicitly includes
 *               the start as a visisted location.
 *               
 * This class renders a simple list with embedded CRLF chars. to present the maze as
 * a retro character game style.
 * 
 * @author gavinling
 *
 */
@Component
public class MazeRenderer {
    @Autowired
    private MazeStats stats;

    /**
     * Show the Maze
     */
    public String showMaze(Maze maze, boolean includeRouteTaken) {
        int visitCount = 0;

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%nThe Maze%s", includeRouteTaken ? " including users route." : "."));

        for (int r = 0; r < stats.getMazeRowCount(); r++) {

            sb.append(String.format("%n%-5d", r));
            for (int c = 0; c < stats.getMazeColCount(); c++) {
                Cell cell = maze.getCell(c, r);

                if (cell.isNavigable() && includeRouteTaken && cell.isVisited()) {
                    // show users navigation.
                    sb.append(".");
                } else {
                    // show the underlying maze
                    sb.append(cell.getCellType().getType());
                }

                // add dynamic stats
                if (cell.isVisited())
                    visitCount++;

            }

        }

        int pathCount = stats.getPathCount() + 2; // include start and finish.
        sb.append(String.format("%nMaze statistics:%nWalls:%s, Path:%s (Visited:%s, Game moves:%s)",
                stats.getWallCount(), pathCount, visitCount, stats.getGameMoves()));

        return sb.toString();

    }

    /**
     * Locate a specific Cell within the current Maze.
     * 
     * @param cellList
     * @param colNumber
     * @param rowNumber
     * @return
     */
    public Cell getCell(int colNumber, int rowNumber, Cell[] cellList) {
        try {
            int computedArrayIndex = rowNumber * stats.getMazeRowCount() + colNumber;
            return cellList[computedArrayIndex];

        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new MazeException(
                    String.format("Co-ordinate col:%s, row:%s exceeds maze bounds.", colNumber, rowNumber));
        }
    }
}
