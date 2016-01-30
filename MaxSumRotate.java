package arrays;

import java.util.*;



public class MaxSumRotate{

public static void main(String[] args) {
        //Find maximum value of Sum( i*arr[i]) with only rotations on given array allowed
        //http://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/
                
        
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner uIn=new Scanner(System.in);
        int numOfElements=uIn.nextInt();
        int[] arr=new int[numOfElements];
        int arrSum=0;
        for(int i=0;i<numOfElements;i++){
            arr[i]=uIn.nextInt();
            arrSum+=arr[i];
            //finding sum of elements
            //parallely
        }        
        //a rotation array to keep value of
        //each rotation
        int[] rVal=new int[numOfElements];
        
        //calculate the first rotation value
        for(int i=0;i<numOfElements;i++){
            rVal[0]+=arr[i]*i;
        }
        
        //we know that
        //rotationVal[j]-rotationVal[j-1]=sumOfArrElements-(n)arr[n-j]
        
        //now to calculate the values for every other rotation
        for(int i=1;i<numOfElements;i++){
            rVal[i]=arrSum-(numOfElements)*arr[numOfElements-i]+rVal[i-1];
        }        
        System.out.println(Arrays.toString(rVal));        
        int max=0;
        for(int i=0;i<numOfElements;i++){
            if(max<rVal[i]){
                max=rVal[i];
            }
        }
        System.out.println(max);                
    }
}
