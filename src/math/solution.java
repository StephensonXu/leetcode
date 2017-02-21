package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class solution {
    public int reverse(int x) {
    	int result=0;
        while(x!=0){
        	int tail=x%10;
        	int newResult=result*10+tail;
        	//判断溢栈时则输出0
        	if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
        	x=x/10;
        	/*
        	if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
            */
        }
        return result;
    }
    public boolean isPalindrome(int x) {
    	int result=0;
    	int m=x;
    	if(x<0){
            return false;
        }
        while(x!=0){
        	int tail=x%10;
        	int newResult=result*10+tail;
        	//判断溢栈时则输出0
        	if ((newResult - tail) / 10 != result)
            { return false; }
            result = newResult;
        	x=x/10;
                
        }
        return (result==m)?true:false;
    }
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);//升序
        int result=Integer.MAX_VALUE;
        int retResult=Integer.MAX_VALUE;
        for(int i=0;i<nums.length;i++){
        	int j1=i+1;
        	int j2=nums.length-1;
        	while(j1<j2){
        		int count=target-nums[i]-nums[j1]-nums[j2];
        		result=Math.min(result,Math.abs(count));
        		if(result==Math.abs(count)) retResult=target-count;
        		if(count==0) return target;
        		else if(count>0) j1++;
        		else j2--;
        	}
        }
        return retResult;
    }
    public List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);//升序
    	List<List<Integer>> result=new ArrayList<List<Integer>>();
    	Set<List<Integer>> mid=new HashSet<List<Integer>>();
    	for(int i=0;i<nums.length;i++){
        	int j1=i+1;
        	int j2=nums.length-1;
        	while(j1<j2){
        		//System.out.println(j1+" "+j2);
        		int count=nums[i]+nums[j1]+nums[j2];
        		if(count==0){
        			List<Integer> tmp=new ArrayList<Integer>();
        		    tmp.add(nums[i]);
        		    tmp.add(nums[j1]);
        		    tmp.add(nums[j2]);
        		    //System.out.print(tmp);
        		    /*
        		     //TLE
        		    if(!result.contains(tmp)) result.add(tmp); 
        		     */
        		    mid.add(tmp);
        		    j1++;
        		    /*不行
        		    int check=nums[j1];
        		    while(j1<j2&&check==nums[j1++]){
        		    	j1++;
        		    }
        		    */
        		}
        		else if(count<0) {
        			j1++;
        		}
        		else {
        			j2--;
        		}
        	}
        }
    	/*
    	//去除重复元素 TLE
    	HashSet h=new HashSet(result);     
        result.clear();     
        result.addAll(h); 
        */
    	result.addAll(mid);
    	return result;
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
    	Arrays.sort(nums);//升序
    	List<List<Integer>> result=new ArrayList<List<Integer>>();
    	Set<List<Integer>> mid=new HashSet<List<Integer>>();
    	for(int i=0;i<nums.length;i++){
    		for(int j=i+1;j<nums.length;j++){
    			int j1=j+1;
            	int j2=nums.length-1;
            	while(j1<j2){
            		int count=nums[i]+nums[j]+nums[j1]+nums[j2];
            		if(count==target){
            			List<Integer> tmp=new ArrayList<Integer>();
            		    mid.add(Arrays.asList(nums[i],nums[j],nums[j1],nums[j2]));
            		    j1++;
            		}
            		else if(count<target) j1++;
            		else j2--;
            	}
    		}
        }
    	/*
    	//去除重复元素 TLE
    	HashSet h=new HashSet(result);     
        result.clear();     
        result.addAll(h); 
        */
    	result.addAll(mid);
    	return result;
    }
    public int[] twoSum(int[] nums, int target) {
    	int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
    public class ListNode {
        int val;
    	ListNode next;
    	ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int result=0;
        //爆栈？
        int tmp1=0,tmp2=0;
        ListNode head=new ListNode(0);
        head.next=null;
        while(l1!=null){
        	tmp1=10*tmp1+l1.val;
        	l1=l1.next;
        }
        int j=0;
        while(l2!=null){
        	tmp2+=(int) (l2.val*Math.pow(10,j));
        	j++;
        	l2=l2.next;
        }
        result=tmp1+tmp2;
        if(result==0){
        	return new ListNode(0);
        }
        while(result!=0){
        	ListNode tmp=new ListNode(result%10);
        	tmp.next=head.next;
        	head.next=tmp;
        	result=result/10;
        }
        return head.next;
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	/*//爆栈？
        int result=0;
        
        int tmp1=0,tmp2=0;
        int j1=0,j2=0;
        while(l1!=null){
        	tmp1+=(int) (l1.val*Math.pow(10,j1));
        	j1++;
        	l1=l1.next;
        }
        while(l2!=null){
        	tmp2+=(int) (l2.val*Math.pow(10,j2));
        	j2++;
        	l2=l2.next;
        }
        result=tmp1+tmp2;
        if(result==0){
        	return new ListNode(0);
        }
        //反链接
        ListNode run=new ListNode(result%10);
        ListNode head=run;
        result=result/10;
        while(result!=0){
        	ListNode tmp=new ListNode(result%10);
        	run.next=tmp;
        	run=tmp;
        	result=result/10;
        }
        return head;
        */
        //顺序链
        /*
        ListNode head=new ListNode(0);
        head.next=null;
        while(result!=0){
        	ListNode tmp=new ListNode(result%10);
        	tmp.next=head.next;
        	head.next=tmp;
        	result=result/10;
        }
        return head.next;
        */
    	int res;
		int jinwei=0;//0不进位
		ListNode run;
		if(l1.val+l2.val>=10){
			run=new ListNode(l1.val+l2.val-10);
			jinwei=1;
		}else{
			run=new ListNode(l1.val+l2.val);
		}
		l1=l1.next;
		l2=l2.next;
		ListNode head=run;
    	while(l1!=null || l2!=null){    		
    		if(l1==null){
    			if(jinwei==0) res=l2.val;
    			else {
    				jinwei=0;
    				res=l2.val+1;
    			}
    			if(res>=10){
    				jinwei=1;
    				res=res-10;
    			}  			
    			l2=l2.next;
    		}else if(l2==null){
    			if(jinwei==0) res=l1.val;
    			else {
    				jinwei=0;
    				res=l1.val+1;
    			}
    			if(res>=10){
    				jinwei=1;
    				res=res-10;
    			}  			
    			l1=l1.next;
    		}else{
    			if(jinwei==0) res=l1.val+l2.val;
    			else {
    				jinwei=0;
    				res=l1.val+l2.val+1;
    			}
    			if(res>=10){
    				jinwei=1;
    				res=res-10;
    			}  			
    			l1=l1.next;
    			l2=l2.next;
    		}
    		ListNode tmp=new ListNode(res);
    		run.next=tmp;
    		run=tmp;
    	}
    	if(jinwei==1){
    		run.next=new ListNode(1);
    	}
    	return head;
    }
    //不用乘除模，溢出用INT.MAX
    public int divide(int dividend, int divisor) {
    	//当然这样一个个减速度慢了 TLE,并下面的递归进行除法
    	int ans=0;
    	if(divisor==0){
    		return Integer.MAX_VALUE;
    	}
    	if(dividend==-2147483648){
    		if(divisor>0){
    			divisor=0-divisor;
    			while(dividend<=divisor){
        			dividend-=divisor;
        			ans-=1;
        			if(ans<=Integer.MIN_VALUE){
        				return Integer.MIN_VALUE;
        			}
        		}
    		}else if(divisor==-2147483648){
    			System.out.print("x");
    			return 1;
    		}else{
    			while(dividend<=divisor){
        			dividend-=divisor;
        			ans+=1;
        			if(ans>=Integer.MAX_VALUE){
        				return Integer.MAX_VALUE;
        			}
        		}
    		}    		
    	}
    	if(divisor==-2147483648){
    		return 0;
    	}
    	if(dividend>=0&&divisor<0 || dividend<=0&&divisor>0){
    		dividend=Math.abs(dividend);
    		divisor=Math.abs(divisor);
    		while(dividend>=divisor){
    			dividend-=divisor;
    			ans-=1;
    			if(ans<=Integer.MIN_VALUE){
    				return Integer.MIN_VALUE;
    			}
    		}
    	}else{
    		dividend=Math.abs(dividend);
    		divisor=Math.abs(divisor);
    		while(dividend>=divisor){
    			dividend-=divisor;
    			ans+=1;
    			if(ans>=Integer.MAX_VALUE){
    				return Integer.MAX_VALUE;
    			}
    		}
    	}
    	return ans;

        
    }
    public int dividebetter(int dividend, int divisor) {
    	//Reduce the problem to positive long integer to make it easier.
    	//Use long to avoid integer overflow cases.
    	int sign = 1;
    	if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0))
    		sign = -1;
    	long ldividend = Math.abs((long) dividend);
    	long ldivisor = Math.abs((long) divisor);
    	
    	//Take care the edge cases.
    	if (ldivisor == 0) return Integer.MAX_VALUE;
    	if ((ldividend == 0) || (ldividend < ldivisor))	return 0;
    	
    	long lans = ldivide(ldividend, ldivisor);
    	
    	int ans;
    	if (lans > Integer.MAX_VALUE){ //Handle overflow.
    		ans = (sign == 1)? Integer.MAX_VALUE : Integer.MIN_VALUE;
    	} else {
    		ans = (int) (sign * lans);
    	}
    	return ans;
    }

    private long ldivide(long ldividend, long ldivisor) {
    	// Recursion exit condition
    	if (ldividend < ldivisor) return 0;
    	
    	//  Find the largest multiple so that (divisor * multiple <= dividend), 
    	//  whereas we are moving with stride 1, 2, 4, 8, 16...2^n for performance reason.
    	//  Think this as a binary search.
    	long sum = ldivisor;
    	long multiple = 1;
    	while ((sum+sum) <= ldividend) {
    		sum += sum;
    		multiple += multiple;
    	}
    	//Look for additional value for the multiple from the reminder (dividend - sum) recursively.
    	return multiple + ldivide(ldividend - sum, ldivisor);
    }
    public int hammingDistance(int x, int y) {
        return count_one(x^y);
    }
    private int count_one(int x){
        int count=0;
        while(x!=0){
            count++;
            x=x&(x-1);
        }
        return count;
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int N1 = nums1.length;
        int N2 = nums2.length;
        if (N1 < N2) return findMedianSortedArrays(nums2, nums1);	// Make sure A2 is the shorter one.
        
        if (N2 == 0) return ((double)nums1[(N1-1)/2] + (double)nums1[N1/2])/2;  // If A2 is empty
        
        int lo = 0, hi = N2 * 2;
        while (lo <= hi) {
            int mid2 = (lo + hi) / 2;   // Try Cut 2 
            int mid1 = N1 + N2 - mid2;  // Calculate Cut 1 accordingly
            
            double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];	// Get L1, R1, L2, R2 respectively
            double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[(mid1)/2];
            double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[(mid2)/2];
            
            if (L1 > R2) lo = mid2 + 1;		// A1's lower half is too big; need to move C1 left (C2 right)
            else if (L2 > R1) hi = mid2 - 1;	// A2's lower half too big; need to move C2 left.
            else return (Math.max(L1,L2) + Math.min(R1, R2)) / 2;	// Otherwise, that's the right cut.
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int index=findRotateIndex(nums);
        if(index==-1){
        	index=0;
        }
        int min=0,max=nums.length-1;
        while(min<max){
        	int mid=(min+max)/2;
        	int tmp=mid+index;
        	tmp=tmp<=nums.length-1?tmp:tmp-(nums.length);
        	if(nums[tmp]<target){
        		min=mid+1;
        	}else if(nums[tmp]>target){
        		max=mid-1;
        	}else{
        		return tmp;
        	}
        }
        int tmp=(min+index)<=(nums.length-1)?min+index:(min+index-nums.length);
        if(nums[tmp]==target){
        	return tmp;
        }
    	return -1;
    	
    	
    }
    public int findRotateIndex(int[] nums){
    	int min=0,max=nums.length-1;
    	while(min<max){
    		int tmp=(min+max)/2;
    		if(nums[tmp]<nums[min]){
    			max=tmp;
    		}else if(nums[tmp]>nums[min]){
    			min=tmp;
    		}else{
    			if(nums[tmp+1]<nums[tmp]){
    				return tmp+1;
    			}else{
    				return -1;
    			}
    		}
    	}
    	if(min+1<nums.length&&nums[min+1]<nums[min]){
    		return min+1;
    	}
    	return -1;
    }
    

}
