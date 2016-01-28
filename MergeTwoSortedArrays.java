package strings;

import java.util.*;

//http://www.geeksforgeeks.org/longest-span-sum-two-binary-arrays/

//Merge two sorted arrays with O(1) extra space


public class MergeTwoSortedArrays{

    public static void main(String[] args) {
        int a1[]={1,5,9,10};
        int a2[]={2,3,8};
        int last=0;
        for(int i=a2.length-1;i>=0;i--){
            //chcek if there is an element in array1 bigger than the current element
            for(int j=0;j<a1.length;j++){
                if(a1[j]>a2[i]){
                    //j is the place where we will insert a1[i]
                    //but before that need to shift by one place
                    //to make space
                   
                    //preserve last
                    last=a1[a1.length-1];
                    //shift to make space
                    for(int k=a1.length-1;k>j;k--){
                        a1[k]=a1[k-1];                        
                    }
                    //insert accordingly
                    a1[j]=a2[i];
                    a2[i]=last;
                    break;
                }
            }            
        }        
        System.out.println(Arrays.toString(a1));
        System.out.println(Arrays.toString(a2));        
    }
}
