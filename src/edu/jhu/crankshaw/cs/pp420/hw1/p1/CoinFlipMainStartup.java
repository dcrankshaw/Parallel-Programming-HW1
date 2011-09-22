
package edu.jhu.crankshaw.cs.pp420.hw1.p1;

import java.util.Date;
import java.util.*;

class CoinFlipMainStartup
{
    
    
    private static final int NUM_ARGS = 2;
    private static final int DEFAULT_NUM_THREADS = 1;
    long endTime;
    
    static long numHeads = 0;

    public static void main(String args[])
    {

	for(int threads = 1; threads <= 100000; threads+=100)
	{

	long startTime = System.currentTimeMillis();
	long coinFlips = 0;
	int numThreads = 0;
	boolean done = false;

	
	if(args.length == NUM_ARGS) {
	    try {
		coinFlips = Long.parseLong(args[0]);
		numThreads = Integer.parseInt(args[1]);
	    }
	    catch (NumberFormatException e) {
		System.out.println("Error: args must be integers");
		printUsage();
		done = true;
	    }	
	}

	else if(args.length == 1) {
	    try {
		coinFlips = Long.parseLong(args[0]);
		numThreads = DEFAULT_NUM_THREADS;
	    }
	    catch(NumberFormatException e) {
		System.out.println("Error: args must be integers");
		printUsage();
		done = true;
	    }
	   
	}
	else {
	    //printUsage();
	    done = true;
	}
	
	long endTime = flipCoins(1, threads);
	System.out.println("Threads: " + threads + "\tTime: " + (endTime - startTime));

	}

    }


    public static void printUsage()
    {
	String usage = "java CoinFlip NUMCOINS [NUMTHREADS]";
	System.out.println(usage);
    }

    public static long flipCoins(long coinFlips, int numThreads)
    {
	long endtime;
	FlipRunner[] flipper = new FlipRunner[numThreads];
	boolean success = true;
	Thread[] thread = new Thread[numThreads];
	for(int i = 0; i < numThreads; i++) {
	    flipper[i] = new FlipRunner(((long) coinFlips/numThreads), i);
	    thread[i] = new Thread(flipper[i]);
	    thread[i].start();
	}
	endtime = System.currentTimeMillis();

	for(int i = 0; i < numThreads; i++) {
	    try {

		thread[i].join();
		numHeads += flipper[i].getNumHeads();
	    }
	    catch(InterruptedException e) {
		System.out.println("Join on thread [" + i + "] interrupted");
		success = false;
		break;
	    }
	}
	return endtime;
    }



}