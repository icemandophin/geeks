/*
randomly choosing k samples from a list of n items
usually n is too large to put into main memory

1 create an array reservoir[0..k-1] and copy first k items of stream[] to it
2 for each item a[i] from k+1 to n:
 1) generate a random number j from 0 to i (cur index)
 2) If j is in range 0 to k-1, replace reservoir[j] with arr[i]
*/
int[] randomlySelectKItems(int stream[], int n, int k)
{
    // index for elements in stream[]
    int i;
    // reservoir[] is the output array. Initialize it with
    // first k elements from stream[]
    int reservoir[k];
    for (i = 0; i < k; i++)
        reservoir[i] = stream[i];

    // Use a different seed value so that we don't get
    // same result each time we run this program
    srand(time(NULL));

    // Iterate from the (k+1)th element to nth element
    for (; i < n; i++)
    {
        // Pick a random index from 0 to i.
        int j = rand() % (i+1);

        // If the randomly  picked index is smaller than k, then replace
        // the element present at the index with new element from stream
        if (j < k)
          reservoir[j] = stream[i];
    }

    return reservoir;
}
