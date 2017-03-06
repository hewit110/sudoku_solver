
import java.util.Scanner;
import java.io.*;
public class main
{

    public static void main(String args[])
    {
        solver sudokusolver = new solver();
        solver.readFile();

        rowThreads t1 = new rowThreads();
        gridThreads t2 = new gridThreads();
        solver.printPuzzle();
    }

}
