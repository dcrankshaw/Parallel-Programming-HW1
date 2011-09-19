


class CoinFlip
{
    
    public static void main(String args[])
    {
	int coinFlips;
	int numThreads;
	private static int final numArgs = 2;
	private static int final defaultNumThreads = 1;
	boolean done = false;

	if(args.length == numArgs)
	{
	    try
	    {
		coinFlips = Integer.parseInt(args[0]);
		numThreads = Integer.parseInt(args[1]);
	    }
	    catch (NumberFormatException e)
	    {
		System.out.println("Error: args must be integers");
		printUsage();
		done = true;
	    }	
	}

	else if(args.length == 1)
	{
	    try
	    {
		coinFlips = Integer.parseInt(args[0]);
		numThreads = defaultNumThreads;
	    }
	    catch(NumberFormatException e)
	    {
		System.out.println("Error: args must be integers");
		printUsage();
		done = true;
	    }
	   
	}
	else
	{
	    printUsage();
	    done = true;
	}
	if(!done)
	{
	    flipCoins(coinFlips, numThreads;
	}


    }


    public static void printUsage()
    {
	String usage = "java CoinFlip NUMCOINS [NUMTHREADS]";
	System.out.println(usage);
    }

    public static void flipCoins(int coinFlips, int numThreads)
    {

    }



}