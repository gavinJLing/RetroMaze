package uk.gov.dwp.maze;



import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import uk.gov.dwp.maze.utils.CompassAlternative;
import uk.gov.dwp.maze.utils.Compass;
import uk.gov.dwp.maze.utils.Direction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MazeApplicationTests {

	@Autowired
	CompassAlternative compass;
	
	

	
	@Test
	public void compassTestTurnLeft() {
		// starting position - defauts to North, so...
		 assertThat(compass.getDirectionAhead(), is(Direction.NORTH) );
		 assertThat(compass.getDirectionLeft(), is(Direction.WEST) );
		 assertThat(compass.getDirectionRight(), is(Direction.EAST)  );
		 
		 compass.turnLeft();
		 assertThat(compass.getDirectionAhead(), is(Direction.WEST) );
		 assertThat(compass.getDirectionLeft(), is(Direction.SOUTH) );
		 assertThat(compass.getDirectionRight(), is(Direction.NORTH)  );
		
		 compass.turnLeft();
		 assertThat(compass.getDirectionAhead(), is(Direction.SOUTH) );
		 assertThat(compass.getDirectionLeft(), is(Direction.EAST) );
		 assertThat(compass.getDirectionRight(), is(Direction.WEST)  );
		 
		 compass.turnLeft();
		 assertThat(compass.getDirectionAhead(), is(Direction.EAST) );
		 assertThat(compass.getDirectionLeft(), is(Direction.NORTH) );
		 assertThat(compass.getDirectionRight(), is(Direction.SOUTH)  );
		 
		 compass.turnLeft();
		 assertThat(compass.getDirectionAhead(), is(Direction.NORTH) );
		 assertThat(compass.getDirectionLeft(), is(Direction.WEST) );
		 assertThat(compass.getDirectionRight(), is(Direction.EAST)  );
		 
	}


	@Test
	public void compassTestTurnRight() {
		 assertThat(compass.getDirectionAhead(), is(Direction.NORTH) );
		 assertThat(compass.getDirectionLeft(), is(Direction.WEST) );
		 assertThat(compass.getDirectionRight(), is(Direction.EAST)  );
		 
		 compass.turnRight();
		 assertThat(compass.getDirectionAhead(), is(Direction.EAST) );
		 assertThat(compass.getDirectionLeft(), is(Direction.NORTH) );
		 assertThat(compass.getDirectionRight(), is(Direction.SOUTH)  );
		 
		 compass.turnRight();
		 assertThat(compass.getDirectionAhead(), is(Direction.SOUTH) );
		 assertThat(compass.getDirectionLeft(), is(Direction.EAST) );
		 assertThat(compass.getDirectionRight(), is(Direction.WEST)  );
		 
		 compass.turnRight();
		 assertThat(compass.getDirectionAhead(), is(Direction.WEST) );
		 assertThat(compass.getDirectionLeft(), is(Direction.SOUTH) );
		 assertThat(compass.getDirectionRight(), is(Direction.NORTH)  );
		 
		 compass.turnRight();
		 assertThat(compass.getDirectionAhead(), is(Direction.NORTH) );
		 assertThat(compass.getDirectionLeft(), is(Direction.WEST) );
		 assertThat(compass.getDirectionRight(), is(Direction.EAST)  );
		 
	}
}
