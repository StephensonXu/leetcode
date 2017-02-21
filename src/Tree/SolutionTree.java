package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import Tree.SolutionTree.TreeNode;

public class SolutionTree {
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode tmp=root;
        TreeNode[] Node=new TreeNode[2];//Node[0] parent,Node[1] node
        //遍历寻找,先序、中序、后序或层序
        Node=findTreeNode(tmp,key);
        //找不到
        if(Node==null){
        	return root;
        }
        /*
        //找到节点父亲//判断是否根
        //节点非根有父亲
        //更替方法，节点的左子树提上去，右子树进行保存放到节点父亲的右子树的最左边//太麻烦
        //节点为根
        //找到根右子树最小值代替根即可
         */
        //找到节点左子树最大值或右子树最小值代替该节点即可
        //节点非根/根,优先左max
        //Node=Parent.left?right?
        
        tmp=Node[1];
        //叶子节点
        if(tmp.left==null&&tmp.right==null){
        	//根节点与非根叶子
    		if(Node[0]==null){
    			return null;
    		}else{
    			if(Node[0].left==Node[1]){
        			Node[0].left=null;
        		}else{
        			Node[0].right=null;
        		}
    		}
    		return root;
		}
    	if(tmp.left!=null){
    		TreeNode tmpParent=tmp;
    		tmp=tmp.left;
    		while(tmp.right!=null){
    			tmpParent=tmp;
    			tmp=tmp.right;
    		}
    		//万一node左边存在
    		if(tmpParent!=Node[1]){
    			tmpParent.right=tmp.left;	
    		} 		
    	}else{
    		TreeNode tmpParent=tmp;
    		tmp=tmp.right;
    		while(tmp.left!=null){
    			tmpParent=tmp;
    			tmp=tmp.left;
    		}
    		//万一node右边存在
    		if(tmpParent!=Node[1]){
    			tmpParent.left=tmp.right;	
    		} 
    	}
    	if(tmp!=Node[1].left){
    		tmp.left=Node[1].left;
    	}
    	if(tmp!=Node[1].right){
    		tmp.right=Node[1].right;
    	}
        if(Node[0]!=null){     	
    		if(Node[0].left==Node[1]){
    			Node[0].left=tmp;
    		}else{
    			Node[0].right=tmp;
    		}   		
        }else{
        	return tmp;
        }
        return root;
    }

    //因为是二叉排序树比较方便父和子，否则不太清楚怎么写
    private TreeNode[] findTreeNode(TreeNode root, int key) {
		TreeNode[] findTreeNode=new TreeNode[2];
		findTreeNode[0]=null;
		findTreeNode[1]=root;
		while(findTreeNode[1]!=null){
			if(key==findTreeNode[1].val){
				return findTreeNode;
			}else if(key>findTreeNode[1].val){
				findTreeNode[0]=findTreeNode[1];
				findTreeNode[1]=findTreeNode[1].right;
			}else{
				findTreeNode[0]=findTreeNode[1];
				findTreeNode[1]=findTreeNode[1].left;
			}
		}
		return null;
	}
    //遍历呗
    public int sumOfLeftLeaves(TreeNode root) {
        int sum=0;
        //结束标识
        if(root==null){
        	return 0;
        }
        if(root.left==null){
        	sum+=sumOfLeftLeaves(root.right);
        	return sum;
        }
        if(root.left.left==null&&root.left.right==null){
        	sum+=root.left.val;
        	sum+=sumOfLeftLeaves(root.right);
        }else{
        	sum+=sumOfLeftLeaves(root.left);
        	sum+=sumOfLeftLeaves(root.right);
        }
        return sum;
        
    }
    //二叉树最深度，递归呗
    public int maxDepth(TreeNode root) {
        int result=0;
        if(root==null){
        	return 0;
        }
        //TLE
        //result=(maxDepth(root.left)>maxDepth(root.right)?maxDepth(root.left):maxDepth(root.right))+1;
        //简单修改,TLE.mgj的，原来是三元表达式里面不要复杂计算。
        if(root.left==null&&root.right!=null){
        	result=maxDepth(root.right)+1;
        }else if(root.left!=null&&root.right==null){
        	result=maxDepth(root.left)+1;
        }else{
        	int temp1=maxDepth(root.left);
        	int temp2=maxDepth(root.right);
        	result=(temp1>temp2?temp1:temp2)+1;
        }
        return result;
    }
    //1，3，5层。。或2，4，6层。。
    /*
    Queue<Integer> temp=new LinkedBlockingQueue<Integer>(); 
    public int rob(TreeNode root) {
    	TreeNode p=root;
    	temp.add(p.val);
        //层序遍历
    	if(p.left!=null){
    		temp.add(p.left.val);
    	}
    	if(p.right!=null){
    		temp.add(p.right.val);
    	}
    	temp.peek()
    	
    }
    */
	public class TreeNode {
	    public int val;
	    public TreeNode left;
	    public TreeNode right;
	    public TreeNode(int x) { val = x; }
    }
	
    public List<String> binaryTreePaths(TreeNode root) {
    	List<String> result=new LinkedList<String>();
    	if(root==null){
    		return result;
    	}
    	//List<String> result=new LinkedList<String>();
    	List<String> left=binaryTreePaths(root.left);
    	List<String> right=binaryTreePaths(root.right);
    	if(left.size()==0 && right.size()==0){
    		result.add(((Integer)root.val).toString());
    	}else if(left.size()!=0 && right.size()==0){
    		for(String m:left){
        		result.add(root.val+"->"+m);
        	}
    	}else if(left.size()==0 && right.size()!=0){
    		for(String m:binaryTreePaths(root.right)){
        		result.add(root.val+"->"+m);
        	}	
    	}else{
    		for(String m:left){
        		result.add(root.val+"->"+m);
        	}
    		for(String m:binaryTreePaths(root.right)){
        		result.add(root.val+"->"+m);
        	}
    	}
    	
    	
    		
        return result;
    }
    //从一条路径来看，到达sum的次数，Map<sum,个数>,所以sum-target=sum_i,总的个数加1。
    public int pathSum3(TreeNode root, int sum) {
    	Map<Integer,Integer> preSum=new HashMap<Integer,Integer>();
    	preSum.put(0, 1);
    	Helper(root, 0, sum, preSum);
    	return count;
    }
    int count=0;
	private void Helper(TreeNode root, int sum, int target,
			Map<Integer, Integer> preSum) {
		if(root==null){
			return;
		}
		sum+=root.val;
		if(preSum.containsKey(sum-target)){
			count+=preSum.get(sum-target);
		}
		if(preSum.containsKey(sum)){
			preSum.put(sum, preSum.get(sum)+1);
		}else{
			preSum.put(sum, 1);
		}
		
		Helper(root.left,sum,target,preSum);
		Helper(root.right,sum,target,preSum);
		preSum.put(sum, preSum.get(sum)-1);//在左树到叶后不影响右子树
		return;
	}
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
        	return false;
        }
        if(root.left==null&&root.right==null){
        	return sum-root.val==0?true:false;
        }else if(root.left!=null && root.right!=null){
        	if(hasPathSum(root.left,sum-root.val)){
        		return true;
        	}else{
        		return hasPathSum(root.right,sum-root.val);
        	}
        }else if(root.left==null && root.right!=null){
        	return hasPathSum(root.right,sum-root.val);
        }else{
        	return hasPathSum(root.left,sum-root.val);
        }
        /*
         if(root==null) return false;
         if(root.left==null && root.right==null && (sum-root.val)==0) return true;
         return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
         */
    }
    public List<List<Integer>> pathSum2(TreeNode root, int sum){
    	List<List<Integer>> result  = new LinkedList<List<Integer>>();
    	List<Integer> currentResult  = new LinkedList<Integer>();
    	pathSum2(root,sum,currentResult,result);
    	return result;
    }

    public void pathSum2(TreeNode root, int sum, List<Integer> currentResult,
    		List<List<Integer>> result) {

    	if (root == null)
    		return;
    	currentResult.add(new Integer(root.val));
    	if (root.left == null && root.right == null && sum == root.val) {
    		result.add(new LinkedList(currentResult));
    		currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
    		return;
    	} else {
    		pathSum2(root.left, sum - root.val, currentResult, result);
    		pathSum2(root.right, sum - root.val, currentResult, result);
    	}
    	currentResult.remove(currentResult.size() - 1);
    }
    //理解错，只能一条通路，不能交叉的
    /*
    public int maxPathSum(TreeNode root) {
        //左子树max，右子树max, root XX并不行。
    	int[] result=helperSum(root);
    	return result[0]>result[1]?result[0]:result[1];
    }
    private int[] helperSum(TreeNode root){
    	int[] result=new int[2];//0为max,1为过root点max
    	if(root.left==null && root.right == null){
    		result[0]=root.val;
    		result[1]=root.val;
    		return result;
    	}else if(root.left==null && root.right!=null){
        	int right0=helperSum(root.right)[0];
        	int right1=helperSum(root.right)[1];
        	//如果小于0，则需要后面比较是否通过root会好   	
            result[1]=Math.max(root.val, right1+root.val);
            result[0]=result[1]>right0?result[1]:right0;
    	}else if(root.left!=null && root.right==null){
    		int left0=helperSum(root.left)[0];
        	int left1=helperSum(root.left)[1];
        	//如果小于0，则需要后面比较是否通过root会好   	
            result[1]=Math.max(root.val, left1+root.val);
            result[0]=result[1]>left0?result[1]:left0;
    	}else{
    		int left0=helperSum(root.left)[0];
        	int right0=helperSum(root.right)[0];
        	int left1=helperSum(root.left)[1];
        	int right1=helperSum(root.right)[1];
        	//如果小于0，则需要后面比较是否通过root会好   	
            result[1]=Math.max(Math.max(left1+root.val, right1+root.val), Math.max(root.val,left1+right1+root.val));
            result[0]=result[1]>Math.max(left0, right0)?result[1]:Math.max(left0, right0);
    	}
    	return result;
    }
    */
    int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }
    
    private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;
    }

}
