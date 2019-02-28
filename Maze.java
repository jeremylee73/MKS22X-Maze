import java.util.*;
import java.io.*;
public class Maze{
    private char[][]maze;
    private boolean animate;//false by default
    private int numRows = 0;
    private int numCols = 0;
    private int numAt = 0;

    /*Constructor loads a maze text file, and sets animate to false by default.
      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)
      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!
      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */
    public Maze(String filename) throws FileNotFoundException{
      File maze1 = new File("Maze1.txt");
      Scanner inf = new Scanner(maze1);
      while (inf.hasNextLine()){
          String line = inf.nextLine();
          numRows++;
          numCols = line.length();
      }
      maze = new char[numRows][numCols];
      fillMaze(filename, maze);
      int foundE = 0;
      int foundS = 0;
      for (int i=0; i<maze.length; i++){
        for (int j=0; j<maze[i].length; j++){
          if (maze[i][j] == 'E'){
            foundE++;
          }
          if (maze[i][j] == 'S'){
            foundS++;
          }
        }
      }
      if (foundE != 1 || foundS != 1){
        throw new IllegalStateException();
      }
    }

    private void fillMaze(String filename, char[][] maze) throws FileNotFoundException{
      int index = 0;
      File maze1 = new File("Maze1.txt");
      Scanner inf = new Scanner(maze1);
      while (inf.hasNextLine()){
          String line = inf.nextLine();
          for (int c=0; c<line.length(); c++){
            maze[index][c] = line.charAt(c);
          }
          index++;
      }
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }

    public void setAnimate(boolean b){
        animate = b;
    }

    public void clearTerminal(){
        //erase terminal, go to top left of screen.
        System.out.println("\033[2J\033[1;1H");
    }

   /*Return the string that represents the maze.
     It should look like the text file with some characters replaced.
    */
    public String toString(){
      String ans = "";
      for (int i=0; i<maze.length; i++){
        for (int j=0; j<maze[i].length; j++){
          ans += maze[i][j];
        }
        if (i != maze.length - 1){
          ans += "\n";
        }
      }
      return ans;
    }

    /*Wrapper Solve Function returns the helper function
      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){
        //find the location of the S.
        int locx = 0;
        int locy = 0;
        for (int i=0; i<maze.length; i++){
          for (int j=0; j<maze[i].length; j++){
            if (maze[i][j] == 'S'){
              locx = i;
              locy = j;
            }
          }
        }
        //erase the S
        maze[locx][locy] = '@';
        //and start solving at the location of the s.
        //return solve(???,???);
        return solve(locx, locy);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.

      Postcondition:
        The S is replaced with '@' but the 'E' is not.
        All visited spots that were not part of the solution are changed to '.'
        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private

      //automatic animation! You are welcome.
      if(animate){
          clearTerminal();
          System.out.println(this);
          wait(20);
      }

      //COMPLETE SOLVE
      if (maze[row][col] == 'E'){
        return numAt;
      }
      maze[row][col] = '@';
      if (maze[row-1][col] == ' '){
        numAt++;
        return solve(row-1, col);
      } else if (maze[row+1][col] == ' '){
        numAt++;
        return solve(row+1, col);
      } else if (maze[row][col-1] == ' '){
        numAt++;
        return solve(row, col-1);
      } else if (maze[row][col+1] == ' '){
        numAt++;
        return solve(row, col+1);
      } else {
        maze[row][col] = '.';
        if (maze[row-1][col] == '@'){
          return solve(row-1, col);
        } else if (maze[row+1][col] == '@'){
          return solve(row+1, col);
        } else if (maze[row][col-1] == '@'){
          return solve(row, col-1);
        } else if (maze[row][col+1] == '@'){
          return solve(row, col+1);
        } else {
          return -1;
        }
      }
    }
}
