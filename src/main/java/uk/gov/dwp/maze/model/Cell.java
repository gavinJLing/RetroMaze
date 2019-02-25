package uk.gov.dwp.maze.model;

import uk.gov.dwp.maze.utils.CellType;
import uk.gov.dwp.maze.utils.Direction;

/**
 * The Cell class used within the Maze. It hold details of what it is made
 * of (Wall/Path) and if the user has visited it. But mainly it holds a reference
 * to its neighbouring cells.
 * The cells also contains its Cartesian co-ordinate within the Maze, however
 * this is just to provide feedback during the game play. 
 * 
 *  Cells are arranges in a simple array structure. The maze being rectlinear
 *  means that a simple expression can be be computed to desired location a cell
 *  occupies with the maze... 
 *  It may be interesting to randomise the position of the cells to make it more
 *  of a challenge to visualise. 3d maybe. Where also the top and bottom could be 
 *  used as additional navigational path ways. 
 * 
 * 
 * @author gavinling
 *
 */
public class Cell {
    private Cell [] connectedCells = new Cell[4];   // e.g. 0:North, 1:East, 2:South &  3:West
    private CellType cellType;                      // e.g. ' ':Path,  'X':wall, 'S':Start & 'F':Finish
    private boolean visited = false;
    private int rowIndex=-1;
    private int colIndex=-1;

    /**
     * Create a cell of type unknown type e.g. the void outside of the maze This
     * helps to define a path way around the perimeter of the maze.
     * 
     * @param colIndex
     * @param rowIndex
     * @param cellType2
     */
    private Cell() {
        this.cellType = CellType.UNKNOWN;

    }

    /**
     * Create a cell of a known type. At this point we do not have a view of its
     * connected neighbouring cells - so mark them as Unknown.
     * 
     * @param valueOfSymbol
     */
    protected Cell(CellType cellType, int rowIndex, int colIndex) {
        this.cellType = cellType; // Path, Wall,or beyond....
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
        this.visited = false;       // has the used set foot in this cell... for route history
        
        // default 'Unknown' refs.
        setCellInDirection(Direction.NORTH, new Cell());    
        setCellInDirection(Direction.EAST, new Cell());
        setCellInDirection(Direction.SOUTH, new Cell());
        setCellInDirection(Direction.WEST, new Cell());

    }

    public Cell getCellInDirection(Direction direction) {
        return this.connectedCells[direction.getIndex()];
    }
   
    public void setCellInDirection(Direction direction, Cell cell) {
         this.connectedCells[direction.getIndex()]= cell;
    }
    
    public CellType getCellType() {
        return this.cellType;
    }

    public boolean isNavigable() {
        return isPath() || isStart() || isFinish();
    }
    
    public boolean isStart() {
        return this.cellType == CellType.START;
    }

    public boolean isFinish() {
        return this.cellType == CellType.FINISH;
    }

    public boolean isPath() {
        return this.cellType == CellType.PATH;
    }
      
    public boolean isUnknown() {
        return this.cellType == CellType.UNKNOWN;
    }
   
    public boolean isWall() {
        return this.cellType == CellType.WALL;
    }
    
    public void visited() {
        this.visited = true;
    }

    public boolean isVisited() {
        return visited;
    }
    
    
    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public String toStringSummary() {
        if(isUnknown()) {
            return String.format("%s",  this.cellType); 
        } else {
            return String.format("%s:%s=%s", this.colIndex, this.rowIndex, this.cellType);
        }
    }

    @Override
    public String toString() {
        return String.format("Cell %s  links to [N:%s]-[E:%s]-[S:%s]-[W:%s]", this.toStringSummary(),
                this.connectedCells[ Direction.NORTH.getIndex() ].toStringSummary(),
                this.connectedCells[ Direction.EAST.getIndex()  ].toStringSummary(),
                this.connectedCells[ Direction.SOUTH.getIndex() ].toStringSummary(),
                this.connectedCells[ Direction.WEST.getIndex()  ].toStringSummary());

    }

}

