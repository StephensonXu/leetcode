package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int thirdMax(int[] nums) {
        List<Integer> result=new LinkedList<Integer>();
        result.add(nums[0]);
        //记录1,2,3大
        for(int num:nums){
        	if(result.size()<3){
        		if(!result.contains(num)){
        			//result.add(num);降序添加
        			if(num<result.get(result.size()-1)){
        				result.add(result.size(),num);
        			}else{
        				for(int i=0;i<result.size();i++){
            				if(num>result.get(i)){
            					result.add(i,num);
            					break;
            				}
            			}
        			}        			       			
        		}
        	}else{
        		if(!result.contains(num)){
        			if(num>result.get(0)){
        				result.add(0,num);
        				result.remove(3);
        			}else if(num<result.get(0)&&num>result.get(1)){
        				result.add(1,num);
        				result.remove(3);
        			}else if(num<result.get(1)&&num>result.get(2)){
        				result.add(2,num);
        				result.remove(3);
        			}
        		}
        	}
        }
        //如果没有3个，则返回最大的.
        return result.size()==3?result.get(2):result.get(0);
    }
    public int findDuplicate(int[] nums) {
        int slow=0;
        int fast=0;
        do{
        	slow=nums[slow];
        	fast=nums[nums[fast]];
        }while(fast!=slow);
        int find=0;
        do{
        	slow=nums[slow];
        	find=nums[find];
        }while(find!=slow);
        return find;
    }
    public int removeDuplicates(int[] nums) {
    	if(nums.length==0){
    		return 0;
    	}
    	int length=1;
        for(int i=1;i<nums.length;i++){
        	while(nums[i]==nums[i-1]&&i<nums.length-1){
        		i++;
        	}
        	nums[length]=nums[i];
        	if(nums[length]!=nums[length-1]){
        		length++;
        	}        	      	
        	
        }
        return length;
    }
    public int removeElement(int[] nums, int val) {
    	if(nums.length==0){
    		return 0;
    	}
    	Arrays.sort(nums);//升序
    	int length=0;
    	for(int i=0;i<nums.length;i++){
        	while(nums[i]==val&&i<nums.length-1){
        		i++;
        	}
        	nums[length]=nums[i];
        	if(nums[length]!=val){
        		length++;
        	}        	      	
        	
        }
        return length;
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (matrix.length == 0) {
            return res;
        }
        
        int rowBegin = 0;
        int rowEnd = matrix.length-1;
        int colBegin = 0;
        int colEnd = matrix[0].length - 1;
        
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // Traverse Right
            for (int j = colBegin; j <= colEnd; j ++) {
                res.add(matrix[rowBegin][j]);
            }
            rowBegin++;
            
            // Traverse Down
            for (int j = rowBegin; j <= rowEnd; j ++) {
                res.add(matrix[j][colEnd]);
            }
            colEnd--;
            
            if (rowBegin <= rowEnd) {
                // Traverse Left
                for (int j = colEnd; j >= colBegin; j --) {
                    res.add(matrix[rowEnd][j]);
                }
            }
            rowEnd--;
            
            if (colBegin <= colEnd) {
                // Traver Up
                for (int j = rowEnd; j >= rowBegin; j --) {
                    res.add(matrix[j][colBegin]);
                }
            }
            colBegin ++;
        }
        
        return res;
    }
    public List<Integer> spiralOrder1(int[][] matrix) {
    	List<Integer> res=new ArrayList<Integer>();
    	if(matrix.length==0) return res;
    	int n=matrix.length;
    	int m=matrix[0].length;
    	helper(matrix,0,0,n,m,res);
    	return res;
    
    }
    private List<Integer> helper(int[][] matrix,int beginx,int beginy,int rows,int cols,List<Integer> res){
    	if(rows==0||cols==0) return res;
    	//right
    	for(int i=beginy;i<beginy+cols;i++){
    		res.add(matrix[beginx][i]);
    	}
    	//if has down
    	if(rows==1) return res;
    	for(int i=beginx+1;i<beginx+rows;i++){
    		res.add(matrix[i][beginy+cols-1]);
    	}
    	//if has left
    	if(cols==1) return res;
    	for(int i=beginy+cols-2;i>=beginy;i--){
    		res.add(matrix[beginx+rows-1][i]);
    	}
    	//if has up
    	if(rows==2) return res;
    	for(int i=beginx+rows-2;i>=beginx+1;i--){
    		res.add(matrix[i][beginy]);
    	}
    	return helper(matrix,beginx+1,beginy+1,rows-2,cols-2,res);
    }
    public class Interval {
    	int start;
    	int end;
    	Interval() { start = 0; end = 0; }
    	Interval(int s, int e) { start = s; end = e; }
    }
    public List<Interval> merge(List<Interval> intervals) {
    	if (intervals.size() <= 1)
            return intervals;
        
        // Sort by ascending starting point using an anonymous Comparator
        intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
        List<Interval> result = new LinkedList<Interval>();
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        
        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                result.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        
        // Add the last interval
        result.add(new Interval(start, end));
        return result;
    }
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
    	if (intervals.size() == 0){
    		intervals.add(newInterval);
    		return intervals;
    	}
    	intervals.sort((i1, i2) -> Integer.compare(i1.start, i2.start));
    	String group=new String();
    	int startID=0;
    	int endID=0;
    	for(int i=0;i<intervals.size();i++){
    		if(newInterval.start<intervals.get(i).start){
    			group+="1";
    			startID=i;
    			break;
    		}
    		if(newInterval.start>=intervals.get(i).start&&newInterval.start<=intervals.get(i).end){
    			group+="2";
    			startID=i;
    			break;
    		}
    	}
    	for(int i=0;i<intervals.size();i++){
    		if(newInterval.end<intervals.get(i).start){
    			group+="3";
    			endID=i;
    			break;
    		}
    		if(newInterval.end>=intervals.get(i).start&&newInterval.end<=intervals.get(i).end){
    			group+="4";
    			endID=i;
    			break;
    		}
    	}
    	System.out.print(group);
    	switch(group){
    	case "13":
    		for(int i=startID;i<endID;i++){
    			intervals.remove(startID);
    		}
    		intervals.add(startID,newInterval);
    		break;
    	case "14":
    		Interval tmp1=new Interval(newInterval.start,intervals.get(endID).end);
    		for(int i=startID;i<endID+1;i++){
    			intervals.remove(startID);
    		}
    		intervals.add(startID,tmp1);
    	    break;
    	case "23":
    		Interval tmp2=new Interval(intervals.get(startID).start,newInterval.end);
    		for(int i=startID;i<endID;i++){
    			intervals.remove(startID);
    		}
    		intervals.add(startID,tmp2);
    		break;
    	case "24":
    		Interval tmp3=new Interval(intervals.get(startID).start,intervals.get(endID).end);
    		for(int i=startID;i<endID+1;i++){
    			intervals.remove(startID);
    		}
    		intervals.add(startID,tmp3);
    		break;  
    	case "1":
    	    int length1=intervals.size();
    		for(int i=startID;i<length1;i++){
    			intervals.remove(startID);
    		}
    		intervals.add(startID,newInterval);
    		break;
    	case "2":
    		Interval tmp4=new Interval(intervals.get(startID).start,newInterval.end);
    		int length2=intervals.size();
    		for(int i=startID;i<length2;i++){
    			intervals.remove(startID);
    		}
    		intervals.add(startID,tmp4);
    		break;
    	default:
    		intervals.add(newInterval);
    		break;		
    	}
    	return intervals;
    			
            
    }
    public int[][] generateMatrix(int n) {
        int[][] res=new int[n][n];
        int k=1;
        //even
        if(n%2==0){
        	for(int i=n-1;i>=1;i-=2){
        		int tmp=(n-i)/2;
        		//left
        		for(int j=tmp;j<=n-1-tmp;j++){
        			res[tmp][j]=k;
        			k++;
        		}
        		//down
        		for(int j=tmp+1;j<=n-1-tmp;j++){
        			res[j][n-1-tmp]=k;
        			k++;
        		}
        		//right
        		for(int j=n-2-tmp;j>=tmp;j--){
        			res[n-1-tmp][j]=k;
        			k++;
        		}
        		//up
        		for(int j=n-2-tmp;j>=tmp+1;j--){
        			res[j][tmp]=k;
        			k++;
        		}
        	}
        }else{
        	for(int i=n-1;i>1;i-=2){
        		int tmp=(n-i)/2;
        		//left
        		for(int j=tmp;j<=n-1-tmp;j++){
        			res[tmp][j]=k;
        			k++;
        		}
        		//down
        		for(int j=tmp+1;j<=n-1-tmp;j++){
        			res[j][n-1-tmp]=k;
        			k++;
        		}
        		//right
        		for(int j=n-2-tmp;j>=tmp;j--){
        			res[n-1-tmp][j]=k;
        			k++;
        		}
        		//up
        		for(int j=n-2-tmp;j>=tmp+1;j--){
        			res[j][tmp]=k;
        			k++;
        		}
        	}
        	res[(n-1)/2][(n-1)/2]=n*n;
        }
        return res;
    }
    public int removeDuplicates2(int[] nums) {
    	/*
    	if(nums.length==0){
    		return 0;
    	}
    	int end=0,length=0,counter=0,tmp=nums[0];
    	while(end<nums.length){
    		length++;
    		if(nums[end]==tmp) counter++;
    		else tmp=nums[end];
    		//System.out.println(end);
    		while(end<nums.length&&counter>=2){
    			if(++end<nums.length&&nums[end]!=tmp) {
    				tmp=nums[end];
    				length++;
    				counter=0;
    			}
    		}
    		end++;
    	}
    	return length;
    	*/
    	int i = 0;
    	for (int n : nums)
    		if (i < 2 || n > nums[i - 2])
    	         nums[i++] = n;
    	return i;
    }
}
