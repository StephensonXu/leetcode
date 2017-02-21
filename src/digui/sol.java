package digui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class sol {
    public List<List<Integer>> permute(int[] nums) {
    	if(nums.length==0) return null;
    	List<List<Integer>> result=new ArrayList<List<Integer>>();
    	result.addAll(Helper(nums,nums.length));
    	return result;
    }
    public List<ArrayList<Integer>> Helper(int[] nums,int length){
    	List<ArrayList<Integer>> result=new ArrayList<ArrayList<Integer>>();
    	System.out.print(length);
        if(length==1){        	
        	ArrayList<Integer> tmp=new ArrayList<Integer>();
        	tmp.add(nums[0]);
        	result.add(tmp);
        	System.out.print("true");
        	return result;
        }

        result=Helper(nums,length-1);
        System.out.print(result);
        int n=result.size();
        System.out.print(n);
        for(int m=0;m<n;m++){ 
        	ArrayList<Integer> i=result.get(m);
        	System.out.print(i.size());
        	for(int j=0;j<i.size()+1;j++){
        		ArrayList<Integer> tmp=i;
        		tmp.add(j,nums[length-1]);
        		result.add(tmp);
        		System.out.print(i.size());
        	}
        	result.remove(i);
        }
        return result;
    }

}
