package edu.jhu.crankshaw.cs.pp420.hw1.p2;


import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

import java.util.Random;

import java.io.PrintStream;

public class BruteForceDES
{
    	// Program demonstrating how to create a random key and then search for the key value.
	public static void main ( String[] args )
	{
		if ( 2 != args.length )
		{
			System.out.println ("Usage: java SealedDES #KEYSIZE #NUMTHREADS");
			return;
		}
		
		// create object to printf to the console
		PrintStream p = new PrintStream(System.out);

		// Get the argument
		long keybits = Long.parseLong ( args[0] );
		int numThreads = Integer.parseInt(args[1]);

    long maxkey = ~(0L);
    maxkey = maxkey >>> (64 - keybits);
		
		// Create a simple cipher
		SealedDES enccipher = new SealedDES ();
		
		// Get a number between 0 and 2^64 - 1
		Random generator = new Random ();
		long key =  generator.nextLong();
		
		// Mask off the high bits so we get a short key
		key = key & maxkey;
		
		// Set up a key
		enccipher.setKey ( key ); 
		
		// Generate a sample string
		String plainstr = "Johns Hopkins afraid of the big bad wolf?";
		
		long runstart;
		runstart = System.currentTimeMillis();
		Thread[] threads = new Thread[numThreads];
		Runnable[] decrypters = new Runnable[numThreads];
		
		long keySpaceSize = 0;
		if(numThreads > 0)
		{
		    keySpaceSize = maxkey/numThreads;
		}
		
		for(int i = 0; i < numThreads; i++)
		{
			SealedObject sldObj = enccipher.encrypt ( plainstr );
			decrypters[i] = new DecryptionRunner(i, new SealedDES(), keySpaceSize*i, keySpaceSize*(i+1),
					sldObj);
			threads[i] = new Thread(decrypters[i]);
			threads[i].start();
		}
		
		for(int i = 0; i < numThreads; i++)
		{
			try {
				threads[i].join();
			}
			catch(InterruptedException e) {
			System.out.println("Join on thread [" + i + "] interrupted");
			break;
	    	}
		}
		
		
		
		// Output search time
		long elapsed = System.currentTimeMillis() - runstart;
		long keys = maxkey + 1;
		System.out.println ( "Completed search of " + keys + " keys at " + elapsed + " milliseconds.");
	}


}