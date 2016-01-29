package arrays;

import java.util.*;

//http://www.geeksforgeeks.org/count-triplets-with-sum-smaller-that-a-given-value/

//Count triplets with sum smaller than a given value


public class CountTriplets{

public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner uIn=new Scanner(System.in);        
        int length=uIn.nextInt();        
        int[] arr=new int[length];for(int i=0;i<length;i++){
            arr[i]=uIn.nextInt();            
        }
        int maxSum=uIn.nextInt();            
        int countTriplets=0;        
        Arrays.sort(arr);
        for(int i=0;i<arr.length-2;i++){                   
            //take two pointers
            int j=i+1;
            //right after i            
            int k=arr.length-1;
            //the last element            
            while(j<k){                
                if (arr[i]+arr[j]+arr[k]<maxSum)
                {
                    //this is the magic
                    countTriplets+=k-j;
                    //covers all the elements between k and j                    
                    j++;
                }
                else{
                    k--;
                }
            }
        }
        System.out.println(countTriplets);
    }
}
