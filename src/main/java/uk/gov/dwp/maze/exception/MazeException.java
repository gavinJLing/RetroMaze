package uk.gov.dwp.maze.exception;

public class MazeException extends RuntimeException {

	public MazeException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MazeException(String message) {
		super(message);
		
	}

}
