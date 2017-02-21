package DP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//先来三角形
public class Solution1 {
	public class Node{
		private int x;
		private int y;
		public Node(int x,int y){
			this.x=x;
			this.y=y;
		}
		public Integer getX() {
			return x;
		}
		public int getY() {
			return y;
		}
		public void setX(int x) {
			this.x = x;
		}
		public void setY(int y) {
			this.y = y;
		}	
	}
	public int findTriMax(int[][] Tri){
		int m=Tri.length;
		int n=Tri[m-1].length;
		if(m==0){
			return 0;
		}
		int[][] dp=new int[m][n];
		//三角形状态转移方程，dp[i][j]=Tri[i][j]+max{dp[i+1][j],dp[i+1][j+1]}
		for(int i=0;i<n;i++){
			dp[m-1][i]=Tri[m-1][i];
		}
		for(int i=m-2;i>=0;i--){
			for(int j=0;j<=Tri[i].length-1;j++){
				int ans=(dp[i+1][j]>dp[i+1][j+1])?dp[i+1][j]:dp[i+1][j+1];
				dp[i][j]=Tri[i][j]+ans;
			}
		}
		return dp[0][0];
	}
	public int findMatrixMax(int[][] Matrix){
		int m=Matrix.length;
		int n=Matrix[m-1].length;
		if(m==0||n==0){
			return 0;
		}
		int[][] dp=new int[m][n];
		//矩阵状态转移，dp[i][j]=Matrix[i][j]+max{dp[i-1][j],dp[i+1][j],dp[i][j+1]},但这样不太好计算
		//按照题目要求，只能向右，下，上
		//从第一列第一个到第二列的任意，再第二列任意到第三列任意。。最后到最后列第一个
		//动态规划思想，（错误：从尾部思考，则最后列第一个到倒数第二列任意哪个最大；并非贪心算法）
		//从第一列到第二列再如此下去
		//所以要求分两种，从上走到这和从下走到这
		dp[0][0]=Matrix[0][0];
		for(int i=1;i<m;i++){
			dp[i][0]=dp[i-1][0]+Matrix[i][0];
		}
		//从上往下
		for(int j=1;j<n;j++){
			dp[0][j]=dp[0][j-1]+Matrix[0][j];
		}
		for(int j=0;j<n-1;j++){
			for(int i=1;i<m;i++){
				dp[i][j+1]=Matrix[i][j+1]+(dp[i-1][j+1]>dp[i][j]?dp[i-1][j+1]:dp[i][j]);
			}
		}
		//从下往上
		int[] tmp=new int[n];
		tmp[0]=dp[m-1][0];
		for(int j=1;j<n;j++){
			tmp[j]=tmp[j-1]+Matrix[m-1][j];
			dp[m-1][j]=tmp[j]>dp[m-1][j]?tmp[j]:dp[m-1][j];
		}
		for(int j=0;j<n-1;j++){
			for(int i=m-2;i>-1;i--){
				int ans=Matrix[i][j+1]+(dp[i+1][j+1]>dp[i][j]?dp[i+1][j+1]:dp[i][j]);
				dp[i][j+1]=ans>dp[i][j+1]?ans:dp[i][j+1];
			}
		}
		return dp[0][n-1];
	}
	public int climbStairs(int n){
		int[] result=new int[n+1];
		switch(n){
		case 0:
			return 0;
		case 1:
		    return 1;
		case 2:
			return 2;
		}
		result[0]=1;
		result[1]=1;
		result[2]=2;
		//%1000000007防止溢出
		for(int i=3;i<n+1;i++){
			result[i]=((result[i-1]+result[i-2])%1000000007+result[i-3])%1000000007;
		}
		return result[n];
	}
	public int insertMatrix1(ArrayList<Node> Matrixs){
		if(Matrixs.size()==0){
			return 0;
		}
		int[] dp=new int[Matrixs.size()];
		for(int i=0;i<Matrixs.size();i++){
			dp[i]=1;
		}
		Matrixs=SortNodeWidth(Matrixs);
		for(int i=0;i<Matrixs.size();i++){
			for(int j=0;j<i;j++){
				if(Matrixs.get(i).getY()>Matrixs.get(j).getY()){
					dp[i]=dp[i]>(dp[j]+1)?dp[i]:(dp[j]+1);
				}
			}			
		}
		return dp[Matrixs.size()-1];
	}
	public ArrayList<Node> SortNodeWidth(ArrayList<Node> Matrixs){
		//sort x,y in Node
		for(int i=0;i<Matrixs.size();i++){			
			if(Matrixs.get(i).getX()>Matrixs.get(i).getY()){
				int tmp=Matrixs.get(i).getX();
				Matrixs.get(i).setX(Matrixs.get(i).getY());
				Matrixs.get(i).setY(tmp);
			}
		}
		Collections.sort(Matrixs,new Comparator<Node>(){

			@Override
			public int compare(Node o1, Node o2) {				
				return o1.getX().compareTo(o2.getX());
			}
			
		});
		return Matrixs;
	}
	public int insertMatrix2(ArrayList<Node> Matrixs){
		//排序
		Matrixs=SortNodeWidth(Matrixs);
		//邻接矩阵图
		int[][] Graph=constructGraph(Matrixs);
		//计算,无向图最长,dp[i]表示第i个Node为起点最长
		int[] dp=new int[Matrixs.size()];
		for(int i=0;i<Matrixs.size();i++){
			dp[i]=1;
		}
		//还是必须进行排序
		for(int i=Matrixs.size()-1;i>=0;i--){
			//tmp=Graph[i][j]
			for(int j=0;j<Matrixs.size();j++){
				if(Graph[i][j]==1){
					dp[i]=(dp[j]+1)>(dp[i]+0)?(dp[j]+1):(dp[i]+0);
				}
			}
		}
		return dpMax(dp);
		//或者用记忆搜索，并不用排序
	}
	private int dpMax(int[] dp) {
		int max=dp[0];
		for(int i=0;i<dp.length;i++){
			max=max>dp[i]?max:dp[i];
		}
		return max;
	}
	public int[][] constructGraph(ArrayList<Node> Matrixs){
		int[][] Graph=new int[Matrixs.size()][Matrixs.size()];
		for(int i=0;i<Matrixs.size();i++){
			for(int j=0;j<Matrixs.size();j++){
				if(compareNode(Matrixs.get(i),Matrixs.get(j))){
					Graph[i][j]=1;
				}else{
					Graph[i][j]=0;
				}
				
			}
		}
		return Graph;
	}
	public boolean compareNode(Node a,Node b){
		return ((a.getX()<b.getX())&&(a.getY()<b.getY()))||((a.getY()<b.getX())&&(a.getX()<b.getY()));
	}
	public int mergeStone(int[] stones){
		int n=stones.length;
		if(n==0){
			return 0;
		}else if(n==1){
			return stones[0];
		}
		int[][] dp=new int[n][n];
		//初始化，本身动为0，否则为极大
		for(int i=0;i<n;i++){
			System.out.println("");
			for(int j=0;j<n;j++){
				if(i==j){
					dp[i][j]=0;
				}else {
					dp[i][j]=110000000;
				}
				System.out.print(dp[i][j]+" ");
			}
		}
		System.out.println("");
		//dp[i][j]=min{dp[i][k]+dp[k][j]}+sum(i,j)
		//矩阵斜着求,i表示相对横坐标增加的，1-n-1
		for(int i=1;i<n;i++){
			for(int j=0;j<n-i;j++){
				for(int k=j;k<j+i;k++){
					//dp[j][j+i]=dp[j][k]+dp[k+1][j+i];
					int tmp=dp[j][k]+dp[k+1][j+i];
					dp[j][j+i]=dp[j][j+i]>tmp?tmp:dp[j][j+i];
				}
				dp[j][j+i]=dp[j][j+i]+sum(stones,j,j+i);
			}
		}
		//打印看下
		for(int i=0;i<n;i++){
			System.out.println("");
			for(int j=0;j<n;j++){
				System.out.print(dp[i][j]+" ");
			}
		}
		System.out.println("");
		return dp[0][1];
	}
	private int sum(int[] stones,int j, int i) {
		int ans=0;;
		for(int k=j;k<i+1;k++){
			ans+=stones[k];
		}
		return ans;
	}
	//默认coins从大到小,最大的小于等于money,不能拼凑则返回-1
	public int maxCoins(int[] coins,int money){
		//dp[i]表示i钱时候最大硬币数
		//dp[i]=max{dp[k]+dp[i-k]}
		int n=coins.length;
		int[] dp=new int[money+1];
		for(int i=0;i<money+1;i++){
			dp[i]=-1;
		}
		for(int i=0;i<n;i++){
			dp[coins[i]]=1;
		}
		for(int i=coins[0];i<money+1;i++){
			for(int j=0;j<i+1;j++){
				if(dp[j]>0&&dp[i-j]>0){
					int tmp=dp[j]+dp[i-j];
					dp[i]=dp[i]>tmp?dp[i]:tmp;
				}				
			}
		}
//		for(int i=0;i<money+1;i++){
//			System.out.println(dp[i]);
//		}
		return dp[money];
	}
	//对money和coins大小没要求
	public int minCoins(int[] coins,int money){
		//dp[i]表示i钱时候最小硬币数
	    //dp[i]=min{dp[k]+dp[i-k]}
		if(money==0){
			return 0;
		}
		int n=coins.length;
		int[] dp=new int[money+1];
		for(int i=0;i<money+1;i++){
			dp[i]=-1;
		}
		for(int i=0;i<n;i++){
			if(coins[i]<=money){
				dp[coins[i]]=1;
			}			
		}
		for(int i=0;i<money+1;i++){
			for(int coin:coins){
				if(i-coin>0&&dp[i-coin]>0){
					if(dp[i]==-1){
						dp[i]=1+dp[i-coin];
					}else{
						int tmp=1+dp[i-coin];
						dp[i]=dp[i]>tmp?tmp:dp[i];
					}					
				}				
			}
		}
		return dp[money];
	}
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //状态转移方程dp[n][m]=!(dp[n-1][m-1]||dp[n-1][m-n])
		//n表示此时最大数，m表示目标数
		//则dp[i][j]:i>=j>=0,true
	    //则dp[0][j]:j>0,false?
		boolean[][] dp=new boolean[maxChoosableInteger+1][desiredTotal+1];		
		for(int i=0;i<=maxChoosableInteger;i++){
			System.out.println(" ");
			for(int j=0;j<=desiredTotal;j++){
				if(i==0&&j>0){
					dp[i][j]=false;
				}else if(i>=j&&j>=0){
					dp[i][j]=true;
				}else{
					dp[i][j]=false;
				}
				System.out.print(dp[i][j]+" ");
			}
		}
		System.out.println(" ");
		for(int i=1;i<=maxChoosableInteger;i++){
			for(int j=i+1;j<=desiredTotal;j++){
				if(j>((i+1)*i/2)){
					dp[i][j]=false;
				}else{
					boolean tmp1=dp[i-1][j-2];
					boolean tmp2=dp[i-1][j-i];
					dp[i][j]=(!tmp1)||(!tmp2);
				}
			}
		}
		for(int i=0;i<=maxChoosableInteger;i++){
			System.out.println(" ");
            for(int j=0;j<=desiredTotal;j++){
            	System.out.print(dp[i][j]+" ");
			}
		}			
		System.out.println(" ");
		return dp[maxChoosableInteger][desiredTotal];
    }
	Map<Integer, Boolean> map;
    boolean[] used;
    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        int sum = (1+maxChoosableInteger)*maxChoosableInteger/2;
        if(sum < desiredTotal) return false;
        if(desiredTotal <= 0) return true;
        
        map = new HashMap();
        used = new boolean[maxChoosableInteger+1];
        return helper(desiredTotal);
    }
    
    public boolean helper(int desiredTotal){
        if(desiredTotal <= 0) return false;
        int key = format(used);
        if(!map.containsKey(key)){
    // try every unchosen number as next step
            for(int i=1; i<used.length; i++){
                if(!used[i]){
                    used[i] = true;
     // check whether this lead to a win (i.e. the other player lose)
                    if(!helper(desiredTotal-i)){
                        map.put(key, true);
                        used[i] = false;
                        return true;
                    }
                    used[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key);
    }
   
// transfer boolean[] to an Integer 
    public int format(boolean[] used){
        int num = 0;
        for(boolean b: used){
            num <<= 1;
            if(b) num |= 1;
        }
        return num;
    }
    
    public int minPathSum(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] sum=new int[n][m];
        sum[0][0]=grid[0][0];
        for(int i=1;i<n;i++){
        	sum[i][0]=sum[i-1][0]+grid[i][0];
        }
        for(int j=1;j<m;j++){
        	sum[0][j]=sum[0][j-1]+grid[0][j];
        }
        //dp[n][m]=min(dp[n-1][m],dp[n][m-1])+grid[n][m];
        for(int i=1;i<n;i++){
        	for(int j=1;j<m;j++){
        		sum[i][j]=(sum[i-1][j]>sum[i][j-1]?sum[i][j-1]:sum[i-1][j])+grid[i][j];
        	}
        }
        return sum[n-1][m-1];
    }
 
}
