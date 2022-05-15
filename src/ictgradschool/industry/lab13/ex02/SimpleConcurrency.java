package ictgradschool.industry.lab13.ex02;

import java.util.ArrayList;
import java.util.List;

public class SimpleConcurrency {

    private int value = 0;

    private void start() throws InterruptedException {
        List< Thread > threads = new ArrayList<>();

        for (int i = 1; i <= 100 ; i ++) {
        final int toAdd = i;

        Thread t = new Thread ( new Runnable () {
            @Override
            public void run () {
                add ( toAdd ); }
        });

        t.start ();
        threads.add( t );
    }
        for ( Thread t : threads ) {
            t.join (); }
        System.out.println ( "value = " + value );
    }

    private void add ( int i ) {
        value = value + i ;
    }

    public static void main ( String [] args ) throws InterruptedException {
        new SimpleConcurrency().start();
    }

}
