#include <iostream>
#include <vector>

using namespace std;

class Solution {

public :

  Solution(vector<vector<int>> &vec) {
      row = vec.begin();
      end = vec.end();
      if(row != end)
          col = vec[0].begin();
  }

  bool hasNext(){
      while(row != end && col == row->end()){
          row++;
          col = row->begin();
      }

      return row != end;
  }

  int next(){
      while(row != end && col == row->end()){
          row++;
          col = row->begin();
      }

      pre = col;
      col++;
      return *pre;
  }

  void remove(){
      row->erase(pre);
      col--;
  }

private :
  vector<vector<int>>::iterator row, end;
  vector<int>::iterator col, pre;
};
