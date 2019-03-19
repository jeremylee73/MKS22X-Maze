import java.io.IOException;
import java.io.FileNotFoundException;
public class Driver{
  public static void main(String args[]){
    String[] files = {"data1.dat","data2.dat","data3.dat"};
    for (int i=0; i<files.length; i++){
      String filename = files[i];
      try{
        Maze f;
        f = new Maze(filename);//true animates the maze.

        f.setAnimate(true);
        f.solve();
        System.out.println(f);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
  }
}
