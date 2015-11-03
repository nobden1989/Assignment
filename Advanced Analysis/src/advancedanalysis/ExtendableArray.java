package advancedanalysis;

public class ExtendableArray {

   public static void main( String [ ] args )
   {
	   int NITEMS = 100000;
	   int N = 10; // capacity of array list
	   int n = 0; // number of elements in extendable array
	   int c = 10;
	   
	   long startTime = System.currentTimeMillis();

	   int[] exa = new int[N];
	   for( int i = 0; i < NITEMS; i++ )
	   {
		   exa[i] = i;
		   n++;
		   //System.out.println("size = " + exa.length);
		   if (n == exa.length)
		   {
			   N = N*2;
			   int[] b = new int[N];
			   for (int j=0; j<n; j++)
			   {
				   b[j] = exa[j];
			   }
			   exa = b;
		   }
	   }
	   
	   long totalTime = System.currentTimeMillis() - startTime; 
	   System.out.println("totalTime = " + totalTime);
   }
}