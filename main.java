/**
 *	UDP Server Program
 *	Listens on a UDP port
 *	Receives a line of input from a UDP client
 * Receives a line from a 2nd client
 *	Establish communication between clients untill one enters "Goodbye"
 *
 *	@author: Kevin Hewitt
@	version: 1.2
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
