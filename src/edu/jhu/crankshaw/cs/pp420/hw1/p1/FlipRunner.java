package edu.jhu.crankshaw.cs.pp420.hw1.p1;

import java.util.concurrent.*;
import java.util.*;
import java.util.Random;


class FlipRunner implements Runnable
{
    private long flips;
    private int threadID;

    /*the number of rounds that come up with heads*/
    private long numHeads;

    public FlipRunner(long nflips, int id)
    {
	flips = nflips;
	numHeads = 0;
	threadID = id;
    }
    public void run()
    {	
	Random rand = new Random();
	int result;
	for(long i = 0; i < flips; i++) {
	    //flip a coin, if it is heads (1), it increments numHeads
	    numHeads += rand.nextInt(2);
	}

    }

    public long getNumHeads()
    {
	return numHeads;
    }
}




