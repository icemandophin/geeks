/*
Q: Given a positive integer n, find the # of integers less than equal to n,
whose binary representation doesn't contain consecutive 1s
eg:
I/P : 4
O/P: 4 (0,1,2,4 Valid)
*/

int countBinary(int n) {
    int highbit=0; //the highest index of 1
    for(int i=0;i<31;i++){
    if(n&(1<<i)) highbit=i;
    }
    if(highbit==0) return n+1; //n=0?, n=1?
    if(highbit==1) return 3; //highbit=1 1?, 00, 01 ,10 -> 3
    int addpart=helper(highbit-1);
    int count=0;

    n&=~(1<<highbit);
    if(n&(1<<(highbit-1)))count=helper(highbit-2);
    else{
    n&=~(1<<(highbit-1));
    count=countBinary(n);
    }
    return count+addpart;
}

int helper(int k){
//count of valid number within smallest k bits
if(k==0)return 2; //0,1
if(k==1)return 3; //0,1,2
if(k==2)return 5; //0,1,2,4,5
return helper(k-1)+helper(k-2);
}
