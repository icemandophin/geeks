// optimize nested for loops with break
// when condition not met, break current inner loop
// and i auto move forward in next iteration
for (i = 0; i < N; ++i) {
    while (j < N) {
        if (condition met) {
            ++j;
            update status of j
        }
        else {
            break;
        }
    }
    update status of i
}
