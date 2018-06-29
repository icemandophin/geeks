/*
给一堆Chunk和一个file size,问给定的一堆Chunk能不能组成complete file.
Chunk就是一个左开右闭的区间, 如[0, 4)表示这个chunk包含0, 1, 2, 3这4个byte.
给定的size是这个文件大小.
boolean isCompleteFile(List<Chunk> chunks, int size)
例如:. more info on 1point3acres
chunks = [[0, 1), [1, 3), [3, 4)]  size = 4   => 结果是true
chunks = [[0, 1), [1, 3), [3, 4)]  size = 5   => 结果是false
chunks = [[0, 1), [2, 3), [3, 4)]  size = 4   => 结果是false

followup是建一个class接收one block at a time，所以不是一下子拿到所有的chunk让你来比较，
自己定一个data structure来存chunks。
class Downloader {
    public Downloader(int size) {
    // save size somewhere  }

  public void addBlock(Range r) {
    // TODO: implement   }

  public boolean isDone() {
    // TODO: implement   }
}
*/
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 * If you need more classes, simply define them inline.
 */
class Range {
  int start;
  int end;
}

// 0, 1,2,3,. ..7
/* isFileDone([[3, 7), [0, 1), [2, 5), [6, 8)], 8) -> false */
/* isFileDone([[3, 7), [0, 2), [2, 5), [6, 8)], 8) -> true */
//blocks: [[0, 2), [2, 5), [3, 7),[6, 8)]
class Solution {
  public boolean isFileDone(List<Range> blocks, int size) {
    Collections.sort(blocks, new Comparator<Range>() {
      @Override
      public compare(Range a, Range b) {
         return a.start - b.start;
      }
    });
    Range pre = blocks.get(0);
    int i=1;
    while(i<blocks.size() && blocks.get(i).start<=pre.end) {
      pre.end = Math.max(pre.end,blocks.get(i).end);
      i++;
    }
    if(pre.start>0 || pre.end<size) return false;
    return true;
  }

}
/*
followUp:
*/
class Downloader {

  PriorityQueue<Range> pq;
  int size;

  public Downloader(int size) {
    // save size somewhere
    pq = new PriorityQueue(new Comparator<Range>() {
      @Override
      public compare(Range a, Range b) {
        return a.start - b.start;
      }
    });
    this.size = size;
  }

  public void addBlock(Range r) {
    pq.add(r);
    if(pq.size()>1) {
      Range pre = pq.poll();
      while(!pq.isEmpty() && pq.peek().start<=pre.end) {
        pre.end = Math.max(pre.end,pq.poll().end);
      }
      pq.add(pre);
    }
  }

  public boolean isDone() {
    // TODO: implement
    if(pq.isEmpty()) return false;
    Range minB = pq.peek();
    if(minB.start>0 || minB.end<size) return false;
    return true;
  }
}
