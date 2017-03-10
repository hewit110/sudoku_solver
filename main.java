/**
 *	multithreaded Sudoku solver program
 *	Takes file input via code in readFile() method in solver class
 *	Checks for errors in the input file using 4 threads
 *  Two threads check rows and columns
 *	Thread three checks all 3x3 sub-grids for errors after the first two have completed
 *	@author: Kevin Hewitt
 *  @date: 3/9/2017
 *  @version: 1.3
 */

public class main
{

    public static void main(String args[])
    {
        solver sudokusolver = new solver();
        sudokusolver.readFile();

        Thread t1 = new Thread()
        {
            @Override
            public void run()
            {
                sudokusolver.checkColumns(0,0);
            }
        };

        Thread t2 = new Thread()
        {
            @Override
            public void run()
            {
                sudokusolver.checkRows(0,0);
            }
        };

        Thread t3 = new Thread()
        {
            @Override
            public void run()
            {
                sudokusolver.check3x3(0, 0);
                sudokusolver.check3x3(3, 0);
                sudokusolver.check3x3(6, 0);
                sudokusolver.check3x3(0, 3);
                sudokusolver.check3x3(3, 3);
                sudokusolver.check3x3(6, 3);
                sudokusolver.check3x3(0, 6);
                sudokusolver.check3x3(3, 6);
                sudokusolver.check3x3(6, 6);
            }
        };

        t1.start();
        t2.start();

        try{
        t3.join();
        }
        catch (Exception e) {
            System.out.println("Something occurred with threads 1 or 2");
            e.printStackTrace();
        }
        try{
            t3.join();
        }
        catch (Exception e) {
            System.out.println("Something occurred with thread 3");
            e.printStackTrace();
        }

        sudokusolver.printPuzzle();
    }

}
