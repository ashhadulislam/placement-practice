package strings;

import java.util.*;


public class Boggle {


    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        char board[][]={
            /*{'s','t','k'},
            {'m','u','c'},
            {'b','a','n'}
            */
                         {'t','a','s','g'},
                         {'l','u','n','h'},
                         {'t','e','i','a'},
                         {'a','w','s','r'}
            
        };
        String[] words={"tushar","anisweta"};
         List< String > wordsList = Arrays.asList(words);
        Set<String> wordsSet=new TreeSet<String>(wordsList);
        StringBuffer buffer=new StringBuffer();
        
        boolean isVisited[][]=new boolean [board.length][board[0].length];
        
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                DFSBoggle(board,isVisited,wordsSet,i,j,buffer);        
            }
        }
        
    }
    
    public static void DFSBoggle(char board[][],boolean isVisited[][],Set<String> wordsSet,int i,int j,StringBuffer buffer){
        //check if i and j are within bounds
        if(i<0||i>=board.length||j<0||j>=board[i].length)
            return;
        //check if cell is visited
        if(isVisited[i][j])
            return;
        
        buffer.append(board[i][j]);
        isVisited[i][j]=true;
        
        if(wordsSet.contains(buffer.toString())){
            System.out.println(buffer.toString());
        }
        
        //now to check the surroundings
        
        for(int k=i-1;k<=i+1;k++){
            for(int l=j-1;l<=j+1;l++){
                DFSBoggle(board,isVisited,wordsSet,k,l,buffer);
            }
        }
        //once all the surrounding is visited
        //remove the current character from the buffer
        buffer.deleteCharAt(buffer.length()-1);
        //and make it unvisited
        isVisited[i][j]=false;
        
        
        
    }

}
