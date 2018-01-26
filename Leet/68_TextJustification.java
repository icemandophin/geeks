/*
* 这道题关键在于仔细的处理每一个步骤：
* 1、每一行选择K的单词，K个单词的长度+K-1个空格的长度必须要小于maxWidth，这里每次选择满足这个条件的最大值就可以
* 2、对于已经选定了K个单词，首先计算基本空格，也就是space=（maxWidth-所有单词的长度）/ （k-1），但是还有多余出一部分空格，那么就在附加空格的时候，从左边开始每次多加一个，满足题目的左边的空格大于等于右边的（至多多一个）
* 3、注意只有1个单词的场景
* 4、最后一行需要调整，最后一行单词之间的空格只有1个，末尾再用空格补足长度“
*/

public class Solution {
    /**
     * 这道题关键在于仔细的处理每一个步骤：
     * 1、每一行选择K的单词，K个单词的长度+K-1个空格的长度必须要小于maxWidth，这里每次选择满足这个条件的最大值就可以
     * 2、对于已经选定了K个单词，首先计算基本空格，也就是space=（maxWidth-所有单词的长度）/ （k-1），但是还有多余出一部分空格，那么就在附加空格的时候，从左边开始每次多加一个，满足题目的左边的空格大于等于右边的（至多多一个）
     * 3、注意只有1个单词的场景
     * 4、最后一行需要调整，最后一行单词之间的空格只有1个，末尾再用空格补足长度“
     * */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<String>();
        int start=0,end=1,n=words.length;
        while(start<n){
            int compulsorySpaces=0; //必须的空格，为当前选中单词数量-1
            int wordLength=words[start].length();//当前单词的数量
            while(end<n && compulsorySpaces+1+wordLength+words[end].length()<=maxWidth){ //试探选择最大的单词数量
                compulsorySpaces++;
                wordLength+=words[end].length();
                end++;
            }
            if(end==n){ //末行特殊处理
                StringBuilder sb=new StringBuilder(words[start]);
                for(int k=start+1;k<end;k++) sb.append(" "+words[k]);
                for(int k=wordLength+compulsorySpaces;k<maxWidth;k++) sb.append(" ");
                result.add(sb.toString());
                break;
            }
            if(end-start==1){ //只选中的一个的特殊处理，因为计算空格未出现除数为0的状况
                StringBuilder sb=new StringBuilder(words[start]);
                for(int k=wordLength;k<maxWidth;k++)
                    sb.append(" ");
                result.add(sb.toString());
            } else{//处理多个空格
                int space = (maxWidth-wordLength)/(end-start-1); //基本的空格
                int remains = maxWidth-wordLength-(end-start-1)*space; //因为整除未能分配的空格数量
                StringBuilder sb=new StringBuilder(words[start]);
                for(int k=start+1;k<end;k++){
                    for(int l=0;l<space;l++) sb.append(" ");
                    if(remains-->0) sb.append(" "); //在大于0，也就是还需要在左边多加空格的时候，多给一个
                    sb.append(words[k]);

                }
                result.add(sb.toString());
            }
            start=end;
            end=end+1;
        }
        return result;
    }
}
