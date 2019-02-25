package uk.gov.dwp.maze;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import uk.gov.dwp.maze.model.Cell;
import uk.gov.dwp.maze.model.Maze;
import uk.gov.dwp.maze.utils.CellType;
import uk.gov.dwp.maze.utils.Direction;
import uk.gov.dwp.maze.utils.MazeStats;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MazeModelTests {

	@Autowired
	Maze maze;
	
	@Autowired
    MazeStats mazeStats;
    
	
	/**
	 * These test assume Maze1.txt is in the expected Map.
	 * Filename: Maze1.txt
	 *                 North
	 *                       11111
	 *             012345678901234
	 *  
     *       0     XXXXXXXXXXXXXXX
     *       1     X             X
     *       2     X XXXXXXXXXXXXX
     *       3     X XS        X X
     *       4     X XXXXXXXXX X X
     *       5  w  X XXXXXXXXX X X   E
     *       6  e  X XXXX      X X   a
     *       7  s  X XXXX XXXX X X   s 
     *       8  t  X XXXX XXXX X X   t
     *       9     X X    XXXXXX X
     *       10    X X XXXXXXXXX X
     *       11    X X XXXXXXXXX X
     *       12    X X         X X
     *       13    X XXXXXXXXX   X
     *       14    XFXXXXXXXXXXXXX
     *       
     *                  south
     *         
	 */
	@Before
	public void setup() {
	    maze.load("Maze1.txt");
	}
	
	@Test 
	public void loadGoodMap() {
		 
		 assertThat(mazeStats.getMazeColCount(), is(15) );
		 assertThat(mazeStats.getMazeRowCount(), is(15) );
//		 
		 // test top Left corner.
		 Cell topLeftCell = maze.getCell(0,0);
		 assertThat( topLeftCell.getCellInDirection(Direction.NORTH).isUnknown()  , is(true));
		 assertThat( topLeftCell.getCellInDirection(Direction.WEST).isUnknown()   , is(true));
		 assertThat( topLeftCell.getCellInDirection(Direction.EAST).getCellType() , is(CellType.WALL));
		 assertThat( topLeftCell.getCellInDirection(Direction.SOUTH).getCellType(), is(CellType.WALL));
         
		 // test Bottom Right corner.
         Cell bottomRightCell = maze.getCell(mazeStats.getMazeColCount()-1,mazeStats.getMazeRowCount()-1);
         assertThat( bottomRightCell.getCellInDirection(Direction.NORTH).getCellType(), is(CellType.WALL));
         assertThat( bottomRightCell.getCellInDirection(Direction.WEST).getCellType() , is(CellType.WALL));
         assertThat( bottomRightCell.getCellInDirection(Direction.EAST).isUnknown()   , is(true));
         assertThat( bottomRightCell.getCellInDirection(Direction.SOUTH).isUnknown()  , is(true));
         
         // test Start Cell.
         Cell startCell = maze.getStartCell();
         assertThat( startCell.getCellInDirection(Direction.NORTH).getCellType(), is(CellType.WALL));
         assertThat( startCell.getCellInDirection(Direction.WEST).getCellType() , is(CellType.WALL));
         assertThat( startCell.getCellInDirection(Direction.EAST).getCellType() , is(CellType.PATH));
         assertThat( startCell.getCellInDirection(Direction.SOUTH).getCellType(), is(CellType.WALL));
         
	}
	
	
}

