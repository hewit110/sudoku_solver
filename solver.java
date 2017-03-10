import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by kevin on 3/5/2017.
 */


public class solver {
    //global puzzle value to allow multiple threads to change it

    public  static int[][] puzzle = new int[9][9];

    public solver()
    {

    }


    public static void readFile()
    /*
    Reads a .txt file into a string one line at a time
    The string reads into a 2d array
    A new line in the .txt file trips a "row" counter to begin the new line in the array
    */
    {


        Scanner scan = null;try {
            String inputLine = "";
            int row = 0;
            scan = new Scanner
                    (new BufferedReader
                            (new FileReader
                                    ("C:\\Users\\kevin\\Desktop\\sudoku.txt")));
            while (scan.hasNextLine())
            {
                inputLine = scan.nextLine();
                inputLine = inputLine.replace("\n", "").replace("\r", "");
                String [] inArray = inputLine.split(",");

                for (int x = 0; x<inArray.length;++x)
                {
                    puzzle[row][x] = Integer.parseInt(inArray[x]);
                }
                row++;
            }
            System.out.println("Text file read successfully!");
        }catch (FileNotFoundException e) {
            System.out.println("The file was not found");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("The following error accurred" + e);
        }
    }

    public static void check3x3(int x, int y)
    /*
    Runs through the array one value at a time
    After a value is encountered, run through the array one element at a time beginning with the element in front of it
    Check both values against eachother
    If a duplicate is found, run a function to generate the replacement
        Set array element equal to replacement value
    */
    {
        for (int i = x; i < x + 3; ++i)
        {
            for (int j = y; j < y+3; ++j)
            {

                for (int k = i+1; k<3; ++k)
                {
                    for (int l = j+i; l<3; k++)
                    {
                        if (puzzle[i][j] == puzzle[l][k])
                        {
                            int newNum = replacement(x,y);
                            System.out.println("An error was found at [" + i + "],[" + j + "] " +
                                    " in the grid [" + x + "],[" + y + "] " +
                                    "and will be replaced with " + newNum);
                            puzzle[i][j] = newNum;
                        }
                    }
                }
            }
        }

    }

    public static void checkRows(int completed, int x)
    /*
    Checks row x for duplicates by running through the array
    All elements are checked against the remaining elements of the array starting with the beginning of the row
    If a duplicate is found, the first duplicate value is swapped with the value on the row below it
    If a duplicate is found in the final row, it is swapped with the corresponding value in the first row
    Function runs recursively until all rows have been checked without errors
    */
    {

        for (int i = 0; i <9; ++i)
        {
            for (int j = i+1; j < 9; ++j)
            {

                if (puzzle[x][i] == puzzle [x][j])
                {
                    System.out.println("A duplicate value was found at [" + x + "],[" + i + "] " +
                            "and will swap places with the value below it");
                    if (x == 8)
                    {
                        int temp = puzzle[x][i];
                        puzzle[8][i] = puzzle[0][i];
                        puzzle[0][i] = temp;

                    }
                    else
                    {
                        int temp = puzzle[x][i];
                        puzzle[x][i] = puzzle[x+1][i];
                        puzzle[x+1][i] = temp;
                    }
                    //if an error is found, reset the counter to 0
                    completed = 0;
                }
            }
        }

        completed++;


        /*
        NOTE: "completed" will always be incremented by one even after the counter is reset
        NOTE: to counteract this, I check for 9 iterations rather than 8 (the index size of a sudoku board)
        If not enough iterations have been completed, check the next row in the puzzle array
        If the row being check was the final row, reset the counter to start checking the beginning row
         */

        if (completed != 9)
        {
            if (x == 8) {
                x = 0;
            } else {
                checkRows(completed, x++);
            }
        }
        else
        {
            System.out.println("Vertical lines have been validated and are complete");
        }

    }

    public static void checkColumns(int completed, int x)
    /*
    Checks column x for duplicates by running through the array
    All elements are checked against the remaining elements of the array starting with the beginning of the column
    If a duplicate is found, the first duplicate value is swapped with the value on the column next to it
    If a duplicate is found in the final column, it is swapped with the corresponding value in the first column
    Function runs recursively until all column have been checked without errors
    */
    {

        for (int i = 0; i <9; ++i)
        {
            for (int j = i+1; j < 9; ++j)
            {

                if (puzzle[i][x] == puzzle [x][j])
                {

                    if (x == 8)
                    {
                        System.out.println("A duplicate value was found at [" + i + "],[" + x + "] " +
                                "and will swap places with the value beside it");
                        int temp = puzzle[i][x];
                        puzzle[i][8] = puzzle[i][0];
                        puzzle[i][0] = temp;

                    }
                    else
                    {
                        int temp = puzzle[i][x];
                        puzzle[i][x] = puzzle[i][x+1];
                        puzzle[i][x+1] = temp;
                    }
                    completed = 0;
                }

            }
        }
        completed++;

        /*
        NOTE: "completed" will always be incremented by one even after the counter is reset
        NOTE: to counteract this, I check for 9 iterations rather than 8 (the index size of a sudoku board)
        If not enough iterations have been completed, check the next row in the puzzle array
        If the row being check was the final row, reset the counter to start checking the beginning row
         */
            if (completed != 9)
            {
                if (x == 8) {
                    x = 0;
                } else {
                    checkColumns(completed, x++);
                }
            }
            else
            {
                System.out.println("Horizontal columns have been validated and are complete");
            }

        }


    public static int replacement(int x, int y)
    /*
    Calculates a replacement value for a 3x3 grid
    takes in 3x3 beginning coordinates as parameters
    checks corresponding 3x3 for a 1 - 9 value that is NOT currently used by the 3x3
    Returns the value to be the replacement for a duplicate
     */
    {
        //counter acts as the value that is being checked for in the 3x3
        int counter = 1;
        boolean valueMatch = false;
        int replacement = 0;

        //check for a value = to counter
        //if one is found, do nothing
        for (int k = 0; k < 9; ++k) {
            for (int i = x; i < x + 3; ++i) {
                for (int j = y; j < y + 3; ++j) {
                    if (puzzle[i][j] == counter) {
                        valueMatch = true;
                    }
                }
            }
            //if a cycle goes through the 3x3 without encountering a match;
            //return the value of the counter as the replacement
            //otherwise, check for the next value by incrementing "counter"
            if (!valueMatch) {
                replacement = counter;
            }
            counter++;
            valueMatch = false;

        }
        return replacement;
    }

    public static void printPuzzle()
    {
        for (int i = 0; i<9; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                System.out.print(puzzle[i][j]);
            }
            System.out.println("\n");
        }
    }
}
