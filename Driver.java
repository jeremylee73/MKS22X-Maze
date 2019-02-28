import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Driver{
  public static void main(String args[]){
    try {
      Maze maze1 = new Maze("Maze.txt");
      maze1.setAnimate(true);
      System.out.println(maze1.solve());
    } catch (FileNotFoundException e){
      System.out.println("File Not Found");
    }
  }
}
