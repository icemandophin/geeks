Trying to find the median (middle number) in a series is going to require that 1 reducer is passed the entire range of numbers to determine which is the 'middle' value.

Depending on the range and uniqueness of values in your input set, you could introduce a combiner to output the frequency of each value - reducing the number of map outputs sent to your single reducer. Your reducer can then consume the sort value / frequency pairs to identify the median.

Another way you could scale this (again if you know the range and rough distribution of values) is to use a custom partitioner that distributes the keys by range buckets (0-99 go to reducer 0, 100-199 to reducer 2, and so on). This will however require some secondary job to examine the reducer outputs and perform the final median calculation (knowing for example the number of keys in each reducer, you can calculate which reducer output will contain the median, and at which offset)
