package edu.jhu.crankshaw.cs.pp420.hw1.p2;



public class DecryptionRunner implements Runnable
{
    
    private SealedDES decrypter;
    private int lowestkey;
    private int highestkey;
    SealedObject encryptedPhrase;

    public DecryptionRunner(SealedDES sealedDES, long minkey, long maxkey, SealedObject sealedObject)
    {
	decrypter = sealedDES;
	lowestkey = minkey;
	highestkey = maxkey;
	encryptedPhrase = sealedObject;
	
    }


}