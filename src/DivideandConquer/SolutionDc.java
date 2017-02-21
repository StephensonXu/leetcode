package DivideandConquer;

import java.util.ArrayList;
import java.util.List;

public class SolutionDc {
	public List<Integer> countSmaller(int[] nums) {
		List<Integer> result=new ArrayList<Integer>();
		int start=0;
		while(start<nums.length){
			for(int i:findSmaller(nums,start,nums[start])){
				result.add(i);
			}
			start+=1;
		}
		return result;
	        
	}
	public List<Integer> findSmaller(int[] nums,int start,int target){
		List<Integer> result=new ArrayList<Integer>();
		if(start<nums.length){			
			if(nums[start]<target){
				result.add(nums[start]);
			}
			for(int i:findSmaller(nums,start+1,target)){
				result.add(i);
			}			
		}
		return result;
	}
	
	//count进行记录逆序数
	int[] count;
	public List<Integer> countSmaller1(int[] nums) {
		/*List<Integer> result=new ArrayList<Integer>();
		int start=0;
		while(start<nums.length){
			result.add(findSmaller1(nums,start,nums[start]));
			start+=1;
		}
		return result;*/
		List<Integer> result=new ArrayList<Integer>();
		//最经典求逆序数为归并排序
		count=new int[nums.length];
		//归并排序下标准备
		//来记录排序，用下标来代替相应数-映射关系
		int[] indexs=new int[nums.length];
		for(int i=0;i<indexs.length;i++){
			indexs[i]=i;
		}
		//归并排序并记录count
		int start=0,end=nums.length-1;
		mergeSort(nums,start,end,indexs);
		//对变化后的count进行输出
		for(int i:count){
			result.add(i);
		}
		return result;
	
	}
	private void mergeSort(int[] nums,int start,int end,int[] indexs){
		if(end<=start){
			return;
		}
		int mid=(start+end)/2;
		mergeSort(nums,start,mid,indexs);
		mergeSort(nums,mid+1,end,indexs);
		merge(nums,start,end,indexs);
	}
	private void merge(int[] nums, int start, int end, int[] indexs) {
		//升序归并并记录逆序数
		int mid=(start+end)/2;
		int left=start;
		int right=(start+end)/2+1;
		//先把拍好的记录在这数组里
		int[] array=new int[end-start+1];
		int arrayindex=0;
		int Count=0;
		while(left<=mid&&right<=end){
			if(nums[indexs[left]]<=nums[indexs[right]]){
				array[arrayindex]=indexs[left];
				count[indexs[left]]+=Count;
				left++;
			}else{
				array[arrayindex]=indexs[right];
				right++;
				Count++;
			}
			arrayindex++;
		}
		while(left<=mid){
			array[arrayindex] = indexs[left];
			count[indexs[left]] += Count;
			left++;
			arrayindex++;
		}
		while(right<=end){
			array[arrayindex++] = indexs[right++];
		}
		for(int i = start; i <= end; i++){
			indexs[i] = array[i - start];
		}
	}
	/*
	public int findSmaller1(int[] nums,int start,int target){
		/*爆栈
		if(start==nums.length-1){
			return target>nums[start]?1:0;
		}else{
			return target>nums[start]?(1+findSmaller1(nums,start+1,target)):findSmaller1(nums,start+1,target);
		}
		
		/*TLE
		int result=0;
		while(start<nums.length){
			if(nums[start]<target){
				result+=1;
			}
			start+=1;
		}
		return result;
		
		return 0;
	}
    */
	

}
