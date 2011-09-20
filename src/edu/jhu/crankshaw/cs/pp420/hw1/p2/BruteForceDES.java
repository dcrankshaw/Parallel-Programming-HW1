
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
		
		// Encrypt
		SealedObject sldObj = enccipher.encrypt ( plainstr );
		
		// Here ends the set-up.  Pretending like we know nothing except sldObj,
		// discover what key was used to encrypt the message.
		
		// Get and store the current time -- for timing
		long runstart;
		runstart = System.currentTimeMillis();
		
		// Create a simple cipher
		SealedDES deccipher = new SealedDES ();
		
		// Search for the right key
		for ( long i = 0; i < maxkey; i++ )
		{
			// Set the key and decipher the object
			deccipher.setKey ( i );
			String decryptstr = deccipher.decrypt ( sldObj );
			
			// Does the object contain the known plaintext
			if (( decryptstr != null ) && ( decryptstr.indexOf ( "Hopkins" ) != -1 ))
			{
				//  Remote printlns if running for time.
				p.printf("Found decrypt key %016x producing message: %s\n", i , decryptstr);
				//System.out.println (  "Found decrypt key " + i + " producing message: " + decryptstr );
			}
			
			// Update progress every once in awhile.
			//  Remote printlns if running for time.
			if ( i % 100000 == 0 )
			{ 
				long elapsed = System.currentTimeMillis() - runstart;
				System.out.println ( "Searched key number " + i + " at " + elapsed + " milliseconds.");
			}
		}
		
		// Output search time
		long elapsed = System.currentTimeMillis() - runstart;
		long keys = maxkey + 1;
		System.out.println ( "Completed search of " + keys + " keys at " + elapsed + " milliseconds.");
	}


}