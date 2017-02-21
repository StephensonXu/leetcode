package HashTable;

import java.util.HashMap;
import java.util.Map;


public class SolutionHs {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        //最蠢的穷举法，n^4
    	//空间换时间,hashMap
    	Map<Integer,Integer> AB=new HashMap<Integer,Integer>();
    	for(int a:A){
    		for(int b:B){
    		    AB.put(a+b,AB.getOrDefault(a+b, 0)+1);
    		}
    	}
    	int res=0;
    	for(int c:C){
    		for(int d:D){
    			int part2=(c+d)*-1;
    			res+=AB.getOrDefault(part2, 0);
    		}
    		
    	}
    	return res;
    }
    public int islandPerimeter(int[][] grid) {
    	//太蠢了，都不想写
    	/*
    	int k1=0,k2=0;//=start,=end 横
    	int m1,m2;//=start,=end 竖
    	int maxlength,add=0;
    	boolean findland=false;
    	boolean findfirstend=false;
    	boolean findocean=false;
    	boolean findstart=false;    	 
        for(int i=0;i<grid.length;i++){
        	//终点
        	if(findland&&findocean){
        		m2=i;
        	}
        	findocean=true;
        	for(int j=0;j<grid[0].length;j++){
        		//起点
        		if(grid[i][j]==1&&!findland){
        			k1=j;
        			m1=i;
        			findland=true;
        			findocean=false;
        		}
        		//第一行终点
        	    //如果是到边上也无,则记为j
        		if(grid[i][j]==0&&findland&&!findfirstend){
        			k2=j;
        			maxlength=k2-k1;
        			findfirstend=true;
        		}
        		//后面陆地起点
        		if(grid[i][j]==1&&findland&&!findstart){
        			if(j>k1){
        				add+=2*(j-k1);
        				k1=j;
        			}
        		}
        		//后面陆地终点
        		if(grid[i][j]==0&&findland&&!findfirstend){
        			if(j<k2){
        				add+=2*(k2-j);
        				
        			}
        		}
        	}
        }
        */
    	//直接最外面铺一层，向右向下，有不同则加一
    	if(grid.length==0){
    		return 0;
    	}
    	int[][] realworld=new int[grid.length+2][grid[0].length+2];
    	int result=0;
    	for(int i=1;i<grid.length+1;i++){
    		for(int j=1;j<grid[0].length+1;j++){
    			realworld[i][j]=grid[i-1][j-1];
    		}
    	}
    	for(int i=0;i<grid.length+1;i++){
    		for(int j=0;j<grid[0].length+1;j++){
    			//下
    			if(!((realworld[i][j]^realworld[i+1][j])==0)){
    				result+=1;
    			}
    			//右
    			if(!((realworld[i][j]^realworld[i][j+1])==0)){
    				result+=1;
    			}
    		}
    	}
        return result;    	
    }
}
