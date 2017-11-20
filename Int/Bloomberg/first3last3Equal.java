/*
可以用dp做，生成两个dp matrix, 一个记前3位，一个记后3位。dp[j] 就是前i位digit 和为j的方案数。  两个dp matrix 初始化稍微不一样。. 涓€浜�-涓夊垎-鍦帮紝鐙鍙戝竷
时间复杂度O(3*28*10)
*/
#include <iostream>
#include <vector>
using namespace std;
int sumDigit(int x) {
    int ans = 0;
    while(x != 0) {
        ans += x % 10;
        x /= 10;
    }
    return ans;
}
int brutalForce() {
    int ans = 0;
    for(int i = 100; i <= 999; i++) {
        for(int j = 1; j <= 999; j++) {
            if(sumDigit(i) == sumDigit(j)) {
                ans++;
            }
        }
    }
    return ans;
}

int betterSoln() {
    vector<vector<int>> firstHalf(3, vector<int>(28, 0)), secondHalf(3, vector<int>(28, 0)); // firstHalf[i][j], 前i位digit 和为j的方案. 1point3acres.com/bbs
    for(int j = 0; j <= 9; j++) {
        if(j > 0) firstHalf[0][j] = 1;
        secondHalf[0][j] = 1;
    }
    for(int i = 1; i < 3; i++) {
        for(int j = 0; j <= 27; j++) {
            for(int d = 0; d <= 9; d++) {
                if(j-d >= 0) {-google 1point3acres
                    firstHalf[i][j] += firstHalf[i-1][j-d];
                    secondHalf[i][j] += secondHalf[i-1][j-d];
                }
            }
        }
    }
    int ans = 0;-google 1point3acres
    for(int s = 1; s <= 27; s++) {
        ans += firstHalf[2][s] * secondHalf[2][s];
    }
    return ans;
}

int main(int argc, const char * argv[]) {
    // insert code here...
    cout << brutalForce() << endl;
    cout << betterSoln() << endl;
}
