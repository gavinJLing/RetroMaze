package uk.gov.dwp.maze;



import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import uk.gov.dwp.maze.model.Maze;
import uk.gov.dwp.maze.utils.MazeStats;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MazeLoadingTests {

	@Autowired
	Maze maze;
	
	@Autowired
    MazeStats mazeStats;
	
	@Test 
	public void loadGoodMap() {
		 maze.load("Maze1.txt");
		 assertThat(mazeStats.getMazeColCount(), is(15) );
		 assertThat(mazeStats.getMazeRowCount(), is(15) );
		
	}
	
	@Test
	public void loadDuplicateStarts() {
		try {
		 maze.load("Maze2.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Duplicate Start 'S' located.") );
		}
		 
	}

	@Test
	public void loadDuplicateFinish() {
		try {
		 maze.load("Maze3.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Duplicate Finish 'F' located.") );
		}
		 
	}

	@Test
	public void loadMisingStart() {
		try {
		 maze.load("Maze4.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),is("Error, Maze must contain a 'S'tart and a 'F'inish.") );
		}
		 
	}
	@Test
	public void loadMisingFinish() {
		try {
		 maze.load("Maze5.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),is("Error, Maze must contain a 'S'tart and a 'F'inish.") );
		}
		 
	}
	
	@Test
	public void loadMisingStartAndFinish() {
		try {
		 maze.load("Maze6.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),is("Error, Maze must contain a 'S'tart and a 'F'inish.") );
		}
		 
	}
	
	@Test
	public void loadShortLine() {
		try {
		 maze.load("Maze7.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Maze width ") );
		}
		 
	}
	
	@Test
	public void loadLongLine() {
		try {
		 maze.load("Maze8.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Maze width ") );
		}
		 
	}
	
	@Test
	public void loadBadSyntax() {
		try {
		 maze.load("Maze9.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Invalid syntax detected.") );
		}
		 
	}
	
	@Test
	public void loadBlankLine() {
		try {
		 maze.load("Maze10.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Maze width 0") );
		}
		 
	}
	
	@Test
	public void loadBlankFile() {
		try {
		 maze.load("Maze11.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),is("Error, Maze must contain a 'S'tart and a 'F'inish.") );
		}
		 
	}
	
	@Test
	public void loadMissingFile() {
		try {
		 maze.load("MazeUnknown.txt");
		 fail("Expected an exception here.");
		} catch( IllegalStateException ise) {
			assertThat(ise.getMessage(),containsString("Error, reading Maze map file") );
		}
		 
	}
}

