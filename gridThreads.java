/**
 * Created by kevin on 3/5/2017.
 */
public class gridThreads implements Runnable  {

    Thread thread1 ;

    gridThreads()
    {
        thread1 = new Thread();
        thread1.start();
    }


    public void run()
    {
        try {
            solver solving = new solver();
            solving.check3x3(0, 0);
            solving.check3x3(3, 0);
            solving.check3x3(6, 0);
            solving.check3x3(0, 3);
            solving.check3x3(3, 3);
            solving.check3x3(6, 3);
            solving.check3x3(0, 6);
            solving.check3x3(3, 6);
            solving.check3x3(6, 6);


        }catch (Exception e)
        {
            System.out.println("error");
        }
    }
}
