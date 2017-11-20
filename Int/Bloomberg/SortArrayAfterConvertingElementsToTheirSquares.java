/*
brutal way: calculate square then sort - O(nlogn)
*/
class GFG
{
// Function to sort an square array
	public static void sortSquares(int arr[])
	{
		int n = arr.length;

		// First convert each array elements
		// into its square
		for (int i = 0 ; i < n ; i++)
			arr[i] = arr[i] * arr[i];

		// Sort an array using "inbuild sort function"
		// in Arrays class.
		Arrays.sort(arr);
	}

	// Driver program to test above function
	public static void main (String[] args)
	{
		int arr[] = { -6 , -3 , -1 , 2 , 4 , 5 };
		int n = arr.length;

		System.out.println("Before sort ");
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");

		sortSquares(arr);
		System.out.println("");
		System.out.println("After Sort ");
		for (int i = 0 ; i < n ; i++)
			System.out.print(arr[i] + " ");

	}
}

/*
optimize: order is kept for pure positive/negative subarray
=> calacuate negative and positive in separate, then merge sort 2 arrays
=>  O(n)
*/
class GFG 
{
   // Function to sort an square array
    public static void sortSquares(int arr[])
    {
        int n = arr.length;
       // first dived array into part negative and positive
       int k;
       for(k = 0; k < n; k++)
       {
           if(arr[k] >= 0)
             break;
       }

        // Now do the same process that we learn
        // in merge sort to merge to two sorted array
        // here both two half are sorted and we traverse
        // first half in reverse meaner because
        // first half contain negative element
        int i = k-1; // Initial index of first half
        int j = k; // Initial index of second half
        int ind = 0; // Initial index of temp array

        int[] temp = new int[n];
        while(i >= 0 && j < n)
        {
            if(arr[i] * arr[i] < arr[j] * arr[j])
            {
                temp[ind] = arr[i] * arr[i];
                i--;
            }
            else{

                temp[ind] = arr[j] * arr[j];
                j++;

            }
            ind++;
        }

        while(i >= 0)
        {
            temp[ind++] = arr[i] * arr[i];
            i--;
        }
        while(j < n)
        {
            temp[ind++] = arr[j] * arr[j];
            j++;
        }

       // copy 'temp' array into original array
        for (int x = 0 ; x < n; x++)
            arr[x] = temp[x];
    }
}
