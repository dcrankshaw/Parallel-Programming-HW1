package edu.jhu.crankshaw.cs.pp420.hw1.p2;

import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;

import java.util.Random;

import java.io.PrintStream;

public class DecryptionRunner implements Runnable
{
    
    private SealedDES decrypter;
    private long lowestkey;
    private long highestkey;
    SealedObject encryptedPhrase;
    PrintStream p;
    int threadID;

    public DecryptionRunner(int id, SealedDES sealedDES, long minkey, long maxkey, SealedObject sealedObject)
    {
		threadID = id;
		decrypter = sealedDES;
		lowestkey = minkey;
		highestkey = maxkey;
		encryptedPhrase = sealedObject;
		p = new PrintStream(System.out);
		
    }
    
    public void run()
    {
		for ( long i = lowestkey; i <= highestkey; i++ )
		{
			// Set the key and decipher the object
			decrypter.setKey ( i );
			String decryptstr = decrypter.decrypt ( encryptedPhrase );
			
			// Does the object contain the known plaintext
			if (( decryptstr != null ) && ( decryptstr.indexOf ( "Hopkins" ) != -1 ))
			{
				//  Remote printlns if running for time.
				//p.printf("Thread[%d] found decrypt key %016x producing message: %s\n", threadID, i , decryptstr);
				//System.out.println (  "Found decrypt key " + i + " producing message: " + decryptstr );
			}
		}
    }

}
