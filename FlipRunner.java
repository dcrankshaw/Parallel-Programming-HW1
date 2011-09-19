
package edu.jhu.crankshaw.cs.pp420.hw1;

import java.util.concurrent;
import java.util.concurrent.*;
import java.util.Random;


class FlipRunner implements Runnable
{

    private int flips;
    /*the number of rounds that come up with heads*/

    private int numHeads;

    public FlipRunner(int nflips)
    {
	flips = nflips;
	numHeads = 0;
    }

    public void run()
    {
	
	Random rand = new Random();
	int result;
	for(int i = 0; i < nflips; i++)
	{
	    //flip a coin, if it is heads (1), it increments numHeads
	    numHeads += rand.nextInt(2);
	}
    }
}




