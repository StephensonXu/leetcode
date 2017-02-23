package DP;

import java.util.Arrays;

public class leetcode {
	//时间过长
    public boolean canCross(int[] stones) {
	    if(stones[1]!=1){
            return false;
        }
        //第i块石头k种可能阶数,-1表示不可能
	    int[][] result=new int[stones.length][stones.length];
	    //result[i][j]=result[j][1-n]符合;j<i
	    for(int i=0;i<2;i++){
	    	for(int j=0;j<stones.length;j++){
	    		if(i==1&&j==0){
	    			result[i][j]=1;
	    		}else{
	    			result[i][j]=0;
	    		}
	    		
	    	}
	    }
	    for(int i=2;i<stones.length;i++){
	    	for(int j=0;j<i;j++){
	    		for(int k=0;k<j;k++){
	    			if(Math.abs(stones[i]-stones[j]-result[j][k])<=1&&result[j][k]>0){
	    				result[i][j]=stones[i]-stones[j];
	    			}else if(result[i][j]<=0){
	    				result[i][j]=-1;
	    			}
	    		}
	    	}
	    }
	    for(int j=0;j<stones.length;j++){
	    	if(result[stones.length-1][j]>0){
	    		return true;
	    	}
	    }
	    return false;
    }
    //有2,3,5因子即可
    public int nthUglyNumber1(int n) {
        int[] primeList=new int[]{0,2,3,4,5,6,8,9,10,12,14,15,16,18,20,21,22,24,25,26,27,28};
        if(n==1){
            return 1;
        }else if(n<=22){
            return primeList[n-1];
        }else{
            int k=(n-23)%22;
            int a=(n-23)/22;
            return ((1+a)*30+primeList[k]);
        }
    }
    //全2,3,5因子
    public int nthUglyNumber(int n) {
        //按照初始想法，n-n+1，共10种变法，分别代表5,3,2指数,试过并不行，如81/80这种
    	int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
//      System.out.println("uglyNumbers[0]:1");
        
        int idx2 = 0;
        int idx3 = 0;
        int idx5 = 0;

        int counter = 1;
        while (counter < n) {

//          System.out.println("-----------");
//          System.out.println("idx2:" + idx2 + ";ugly[idx2]:" + uglyNumbers[idx2]);
//          System.out.println("idx3:" + idx3 + ";ugly[idx3]:" + uglyNumbers[idx3]);
//          System.out.println("idx5:" + idx5 + ";ugly[idx5]:" + uglyNumbers[idx5]);
//          System.out.println("idx2:" + idx2 + ";idx3:" + idx3 + ";idx5:" + idx5);
            
            int min = minOf(
                uglyNumbers[idx2] * 2, 
                uglyNumbers[idx3] * 3, 
                uglyNumbers[idx5] * 5);
            
            if (min == uglyNumbers[idx2] * 2) {
//              System.out.println("min==ugly[idx2]*2:" + uglyNumbers[idx2] * 2);
//              System.out.println("idx2:" + idx2 + "→" + (idx2 + 1));
                idx2++;
            }

            if (min == uglyNumbers[idx3] * 3) {
//              System.out.println("min==ugly[idx3]*3:" + uglyNumbers[idx3] * 3);
//              System.out.println("idx3:" + idx3 + "→" + (idx3 + 1));
                idx3++;
            }

            if (min == uglyNumbers[idx5] * 5) {
//              System.out.println("min==ugly[idx5]*5:" + uglyNumbers[idx5] * 5);
//              System.out.println("idx5:" + idx5 + "→" + (idx5 + 1));
                idx5++;
            }
            
            uglyNumbers[counter] = min;
//          System.out.println("uglyNumbers[" + counter + "]:" + min);
            counter++;
        }

//      System.out.println("-----------");
//      System.out.println("return:" + uglyNumbers[n - 1]);
        
        return uglyNumbers[n - 1];
    }
    
    /**
     * 求三个数字中最小的数字
     * @param a 数字a
     * @param b 数字b
     * @param c 数字c
     * @return a、b、c中最小的数字
     */
    private int minOf(int a, int b, int c) {
        int temp = a < b ? a : b;
        return temp < c ? temp : c; 
    }
    public int numSquares(int n) {
    	//这样很容易tle-超时
        int length=(int)Math.pow(n, 0.5);//向下取整
        int[] dp=new int[n+1];//dp[i]表示i组成个数
        //初始化
        for(int i=1;i<length+1;i++){
        	dp[(int) Math.pow(i,2)]=1;
        }
        //dp[i]=dp[k]+dp[i-k]
//        for(int i=2;i<n+1;i++){
//        	for(int j=1;j<i;j++){
//        		if(dp[j]>0&&dp[i-j]>0){
//        			if(dp[i]==0){
//        				dp[i]=dp[j]+dp[i-j];
//        			}else{
//        				int tmp=dp[j]+dp[i-j];
//            			dp[i]=dp[i]>tmp?tmp:dp[i];
//        			}       			
//        		}
//        	}
//        }
        for(int i=2;i<n+1;i++){
        	int i_length=(int)Math.pow(i, 0.5);
        	for(int j=1;j<=i_length;j++){
        		if(dp[i-(int) Math.pow(j,2)]>0){
        			if(dp[i]==0){
        				dp[i]=1+dp[i-(int) Math.pow(j,2)];
        			}else{
        				int tmp=1+dp[i-(int) Math.pow(j,2)];
            			dp[i]=dp[i]>tmp?tmp:dp[i];
        			}       			
        		}
        	}
        }
        return dp[n];
    }
    public int numberOfArithmeticSlices(int[] A) {
        //d[i]表示以i结尾切片个数
    	//初始即d[2]
    	//如果d[i]>0则至少有3连
    	    //如果i+1往前3个符合(包括i+1)则d[i]+1个
    	    //否则为0个
    	//如果d[i]=0则无三连
    	    //如果i+1往前3个符合(包括i+1)则1个
    	    //否则为0个
    	//判断
    	if(A.length<=2){
    		return 0;
    	}
    	//初始化
    	int[] dp=new int[A.length];
    	dp[0]=0;
    	dp[1]=0;
    	dp[2]=(A[0]+A[2]-(2*A[1]))==0?1:0;
    	for(int i=3;i<A.length;i++){
    		if((A[i]+A[i-2]-(2*A[i-1]))==0){
    			dp[i]=dp[i-1]+1;
    		}else{
    			dp[i]=0;
    		}
    	}
    	int sum=0;
    	for(int i=0;i<A.length;i++){
    		sum+=dp[i];
    	}
    	return sum;
    }
    public int uniquePaths(int m, int n) {
        //dp[i][j]=dp[i-1][j]+dp[i][j-1]
    	//矩阵第一排第一列都为1
    	int[][] dp=new int[m][n];
    	for(int i=0;i<m;i++){
    		for(int j=0;j<n;j++){
    			if(i==0||j==0){
    				dp[i][j]=1;
    			}
    		}
    	}
    	//逐行再逐列
    	for(int i=1;i<m;i++){
    		for(int j=1;j<n;j++){
    			dp[i][j]=dp[i-1][j]+dp[i][j-1];
    		}
    	}
    	return dp[m-1][n-1];
    	//或者总结下规律更快
    	//1-1；1-n;1-(n+1)*n/2;再如是而已，计算也需n^2
    }
    public int jump(int[] nums) {
    	//if 不加break出现TLE
        if(nums.length<=1){
            return 0;
        }
        int[] result=new int[nums.length];
        result[0]=0;
        for(int i=1;i<nums.length;i++){
            result[i]=Integer.MAX_VALUE;
            for(int j=0;j<i;j++){
                if(nums[j]>=i-j){
                    result[i]=Math.min(result[i],result[j]+1);
                    break;
                }
            }
        }
        return result[nums.length-1];
    }
    public boolean canJump(int[] nums) {
        if(nums.length<=1){
            return true;
        }
        if(nums[0]==0){
            return false;
        }
        int compare=0;
        for(int i=0;i<nums.length-1;i++){
            compare=Math.max(nums[i],compare-1);
            if(compare<=0) return false;
        }
        return true;
    }
    public int change(int amount, int[] coins) {
        /*
    	Arrays.sort(coins);
        //res[i]=res[i-coins[0]]+res[i-coins[1]]+...//重复
    	//res[i][j]表示用前i种硬币组成总量j的种数=res[i-1][j-coins[i]*0]+res[i][j-coins[j]*1]....
        int[][] res=new int[coins.length+1][amount+1];
        //init
        for(int i=0;i<=amount;i++){
        	res[0][i]=0;
        }
        for(int i=0;i<=coins.length;i++){
        	res[i][0]=1;
        }
        
        for(int i=1;i<=coins.length;i++){
        	for(int j=1;j<=amount;j++){
        		int counts=0;
        		while(j-coins[i-1]*counts>=0){
        			res[i][j]+=res[i-1][j-coins[i-1]*(counts++)];
        		}
        	}
        }
        return res[coins.length][amount];
        */
        //better
    	Arrays.sort(coins);
    	int[] res=new int[amount+1];
    	res[0]=1;
    	for(int coin:coins){
    		for(int j=coin;j<=amount;j++){
    			res[j]+=res[j-coin];
    		}
    	}
    	return res[amount];
    }
    /*
    public int countWays(int n) {  
        // write code here  
        int[] coins={1,2,5};  
        int[] dp= {0,0,0,0,0,0};         
        dp[0] = 1;  
        for(int i = 0;i < 3;++i){  
            for(int j = coins[i];j <= n;++j){  
                dp[j] =(dp[j]+dp[j-coins[i]])%1000000007;                 
            }  
        }  
        return dp[n];  
    }  
    */

}
