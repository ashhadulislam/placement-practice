package arrays;

import java.util.*;

//http://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/

//Longest Span with same Sum in two Binary arrays


public class MaxSumSubZero{

public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner uIn=new Scanner(System.in);
        
        int length=Integer.parseInt(uIn.nextLine());
        
        int arr1[]=new int[length];
        int arr2[]=new int[length];
        
        for(int i=0;i<length;i++){
            arr1[i]=uIn.nextInt();
            
        }
        for(int i=0;i<length;i++){
            arr2[i]=uIn.nextInt();            
        }
        
        //size of difference array
        int diffLength=2*length+1;
        //the difference array
        int[] diff=new int[diffLength];
        
        for(int i=0;i<diffLength;i++){
            diff[i]=-1;
        }//set all elements to -1
        
        
        int maxLength=0;
        int prefix1=0;
        int prefix2=0;
        for(int i=0;i<length;i++){
            //cumulative addition of array elements
            prefix1+=arr1[i];
            prefix2+=arr2[i];            
            //difference between total of prefix
            int difference=prefix1-prefix2;
            
            if (difference==0){
                //no need to check
                //this is from beginning
                maxLength=i+1;
                //direct update maxLength
                continue;                
            }            
            //dealing with negative value
            difference+=length;
            
            
            if(diff[difference]!=-1){
                //same difference has come before
                
                //need to find the length
                int len=i-diff[difference];
                if(len>maxLength)maxLength=len;
            }
            else{
                //save the location of difference
                diff[difference]=i;
            }            
        }        
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(maxLength);
    }
}
