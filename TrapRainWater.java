package arrays;

import java.util.*;


public class TrapRainWater{

public static void main(String []argv)
   {
       
       //http://www.geeksforgeeks.org/trapping-rain-water/
       //trapping rain water
       
       Scanner uIn=new Scanner(System.in);
       int num=uIn.nextInt();
       
       int[] arr=new int[num];
       for(int i=0;i<num;i++){
           arr[i]=uIn.nextInt();
       }
       
       //the arrays to contain max columnsize
       int[]left=new int[num];       
       int[] right=new int[num];
    
       //now update left array
       //leftmost will be same as arrays leftmost
       left[0]=arr[0];
       for(int i=1;i<num;i++){
           if(left[i-1]>arr[i]){
               left[i]=left[i-1];
           }
           else{
               left[i]=arr[i];
           }
       }
       
       //now update the right array
       //rightmost will be same as array rightmost
       right[num-1]=arr[num-1];
       for(int i=num-2;i>=0;i--){
           if(right[i+1]>arr[i]){
               right[i]=right[i+1];
           }
           else{
               right[i]=arr[i];
           }
       }
       
       int totalWater=0;
       //now to calculate the water at each pillar
       for(int i=0;i<num;i++){
           int maxWater=0;
           if(left[i]<right[i]){
               maxWater=left[i];
           }
           else{
               maxWater=right[i];
           }
           totalWater+=(maxWater-arr[i]);
       }
       
       System.out.println(totalWater);
   }
}