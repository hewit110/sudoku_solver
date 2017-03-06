/**
 * Created by kevin on 3/5/2017.
 */
public class rowThreads implements Runnable{

    Thread t ;

    rowThreads() {
        try {
            t = new Thread();
            t.start();
            t.join();
        } catch (Exception e) {

        }
    }

    public void run()
    {
        try {
            solver solving = new solver();
            solving.checkRows(0);
            solving.checkColumns(0);

        }catch (Exception e)
        {
            System.out.println("error");
        }
    }
}