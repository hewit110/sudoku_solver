import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by kevin on 3/5/2017.
 */


public class solver {

    public  static int[][] puzzle = new int[9][9];

    public static void readFile()
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
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("The following error accurred" + e);
        }
    }

    public static void check3x3(int x, int y)
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
                            puzzle[i][j] = replacement(x,y);
                        }
                    }
                }
            }
        }

    }

    public static void checkRows(int x)
    {
        int completed = 0;

        for (int i = 0; i <9; ++i)
        {
            for (int j = i+1; j < 9; ++j)
            {

                if (puzzle[x][i] == puzzle [x][j])
                {

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
                    completed--;
                }
                completed++;
            }
        }

        if (completed != 8)
        {
            if (x == 8) {
                checkRows(0);
            } else {
                checkRows(++x);
            }
        }
        else
        {
            System.out.println("Vertical lines have been validated and are complete");
        }


    }

    public static void checkColumns(int x)
    {
        int completed = 0;


        for (int i = 0; i <9; ++i)
        {
            for (int j = i+1; j < 9; ++j)
            {

                if (puzzle[i][x] == puzzle [x][j])
                {

                    if (x == 8)
                    {
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
                    completed--;
                }
                completed++;
            }
        }

        if (completed != 8)
        {
            if (x == 8) {
                checkRows(0);
            } else {
                checkRows(++x);
            }
        }
        else
        {
            System.out.println("Horizontal lines have been validated and are complete");
        }


    }

    public static int replacement(int x, int y)
    {
        int counter = 1;
        boolean valueMatch = false;
        int replacement = 0;

        for (int k = 0; k < 9; ++k) {
            for (int i = x; i < x + 3; ++i) {
                for (int j = y; j < y + 3; ++j) {
                    if (puzzle[i][j] == counter) {
                        valueMatch = true;
                    }
                }
            }
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
