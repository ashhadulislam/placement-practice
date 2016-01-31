package arrays;

import java.util.*;



public class ZigZagArray{

public static void main(String[] args) {
            //http://www.geeksforgeeks.org/convert-array-into-zig-zag-fashion/
            //Convert array into Zig-Zag fashion        
            Scanner uIn=new Scanner(System.in);
            int arrSize=uIn.nextInt();
            int[]arr=new int[arrSize];
            for(int i=0;i<arrSize;i++){
                arr[i]=uIn.nextInt();
            }
            boolean thisCase=true;        
            for(int i=0;i<arrSize-1;i++){
                if(thisCase){
                    if(arr[i]>arr[i+1]){
                        int temp=arr[i+1];
                        arr[i+1]=arr[i];
                        arr[i]=temp;                    
                    }
                       thisCase=false;
                }
                else{
                    if(arr[i]<arr[i+1]){
                        int temp=arr[i+1];
                        arr[i+1]=arr[i];
                        arr[i]=temp;                    
                    }
                       thisCase=true;
                }            
            }                                        
            System.out.println(Arrays.toString(arr));
        }
                   }
}