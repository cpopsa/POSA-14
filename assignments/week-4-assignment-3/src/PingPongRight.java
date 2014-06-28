// Import the necessary Java synchronization and scheduling classes.

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @class PingPongRight
 *
 * @brief This class implements a Java program that creates two
 *        instances of the PlayPingPongThread and start these thread
 *        instances to correctly alternate printing "Ping" and "Pong",
 *        respectively, on the console display.
 */
public class PingPongRight {
    /**
     * Number of iterations to run the test program.
     */
    public static int mMaxIterations = 10;
    
    /**
     * Latch that will be decremented each time a thread exits.
     */
    public static CountDownLatch latch = new CountDownLatch(2); // TODO - You fill in here

    /**
     * @class PlayPingPongThread
     *
     * @brief This class implements the ping/pong processing algorithm
     *         using the SimpleSemaphore to alternate printing "ping"
     *         and "pong" to the console display.
     */
    public static class PlayPingPongThread extends Thread
    {
    	private final static int FIRST_SEMA = 0;
        private final static int SECOND_SEMA = 1;
        /**
         * Constructor initializes the data member.
         */
        public PlayPingPongThread (/* TODO - You fill in here */
        		SimpleSemaphore firstSema, SimpleSemaphore secondSema, String mStringToPrint)
        {
            // TODO - You fill in here.
        	mSemaphores[FIRST_SEMA] = firstSema;
        	mSemaphores[SECOND_SEMA] = secondSema;
        	this.mStringToPrint = mStringToPrint;
        }

        /**
         * Main event loop that runs in a separate thread of control
         * and performs the ping/pong algorithm using the
         * SimpleSemaphores.
         */
        public void run () 
        {
            // TODO - You fill in here.
        	for (int loopsDone = 1; loopsDone <= mMaxIterations; ++loopsDone) {
        		mSemaphores[FIRST_SEMA].acquireUninterruptibly();
        		System.out.println(mStringToPrint + "(" + loopsDone + ")");
        		mSemaphores[SECOND_SEMA].release();
        	}
        	latch.countDown();
        }

        /**
         * String to print (either "ping!" or "pong"!) for each
         * iteration.
         */
        // TODO - You fill in here.
        private String mStringToPrint;
        
        /**
         * The two SimpleSemaphores use to alternate pings and pongs.
         */
        // TODO - You fill in here.
        private SimpleSemaphore mSemaphores[] = new SimpleSemaphore[2];
    }

    /**
     * The main() entry point method into PingPongRight program. 
     */
    public static void main(String[] args) {
        try {         
            // Create the ping and pong SimpleSemaphores that control
            // alternation between threads.

            // TODO - You fill in here.
        	SimpleSemaphore pingSema = new SimpleSemaphore(1, true);
        	SimpleSemaphore pongSema = new SimpleSemaphore(0, true);
        	
            System.out.println("Ready...Set...Go!");

            // Create the ping and pong threads, passing in the string
            // to print and the appropriate SimpleSemaphores.
            PlayPingPongThread ping =
                new PlayPingPongThread(/* TODO - You fill in here */
                		pingSema, pongSema, "ping");
            PlayPingPongThread pong =
                new PlayPingPongThread(/* TODO - You fill in here */
                		pongSema, pingSema, "pong");
            
            // Initiate the ping and pong threads, which will call the
            // run() hook method.
            ping.start();
            pong.start();

            // Use barrier synchronization to wait for both threads to
            // finish.

            // TODO - replace replace the following line with a
            // CountDownLatch barrier synchronizer call that waits for
            // both threads to finish.
            latch.await();
        } 
        catch (java.lang.InterruptedException e)
            {}

        System.out.println("Done!");
    }
}
