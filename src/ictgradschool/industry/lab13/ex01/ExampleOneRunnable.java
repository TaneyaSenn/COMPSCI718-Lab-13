package ictgradschool.industry.lab13.ex01;

public class ExampleOneRunnable {

    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
        for (int i = 0; i <= 100000; i++) {
            System.out.println(i);
        }
        }
    };

    Thread myThread = new Thread(myRunnable);
    myThread.start();

    myThread.interrupt();

        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

}
