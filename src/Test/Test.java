package Test;
import math.*;
import Array.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import digui.sol;
import DP.*;
import HashTable.SolutionHs;
import LinkedList.SolutionLL;
import String.SolutionStr;
import Tree.SolutionTree;
import DivideandConquer.*;
public class Test {

	public static void main(String[] args) {
//		int[][] Tri={{7},{3,8},{8,1,0},{2,7,4,4},{4,5,2,6,5}};
//		System.out.println(new Solution1().findTriMax(Tri));
//		int[][] Matrix1={{1,-1,1,0},{2,-2,4,2},{3,5,1,-90}};
//		int[][] Matrix2={{1,1}};
//		System.out.println(new Solution1().findMatrixMax(Matrix2));
//		Solution1.Node Node1=new Solution1().new Node(3,13);
//		Solution1.Node Node2=new Solution1().new Node(44,7);
//		Solution1.Node Node3=new Solution1().new Node(8,8);
//		Solution1.Node Node4=new Solution1().new Node(4,4);
//		Solution1.Node Node5=new Solution1().new Node(2,1);
//		ArrayList<Solution1.Node> list=new ArrayList<Solution1.Node>();
//		list.add(Node1);
//		list.add(Node2);
//		list.add(Node3);
//		list.add(Node4);
//		list.add(Node5);
		/*
		//ArrayList4种遍历
		//方法1
        Iterator it1 = list.iterator();
        while(it1.hasNext()){
            System.out.println(it1.next());
        }
        //方法2
        for(Iterator it2 = list.iterator();it2.hasNext();){
             System.out.println(it2.next());
        }
        //方法3
        for(String tmp:list){
            System.out.println(tmp);
        }
        //方法4
        for(int i = 0;i < list.size(); i ++){
            System.out.println(list.get(i));
        }
        */
//		System.out.println(new Solution1().insertMatrix2(list));
//		System.out.println(new Solution1().insertMatrix1(list));
//		int[][] Graph1=new Solution1().constructGraph(list);
//		for(int i=0;i<Graph1.length;i++){
//			System.out.println(" ");
//			for(int j=0;j<Graph1[i].length;j++){
//				System.out.print(Graph1[i][j]+" ");
//			}
//		}
//		System.out.println(" ");
		//int[] Stones=new int[]{1,2,3,4,5};
		//System.out.println(new Solution1().mergeStone(Stones));
		//int[] Coins=new int[]{65,451,124,70,480,441};
		//for(int coin:Coins){
			//System.out.print(coin+" ");
		//}
		//System.out.println(new Solution1().maxCoins(Coins, 11));
		//System.out.println("\n"+new Solution1().minCoins(Coins, 200));
		//System.out.println(new Solution1().canIWin1(10, 40));
//		leetcode l1=new leetcode();
//		int[] stones=new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,201,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238,239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,271,272,273,274,275,276,277,278,279,280,281,282,283,284,285,286,287,288,289,290,291,292,293,294,295,296,297,298,299,300,301,302,303,304,305,306,307,308,309,310,311,312,313,314,315,316,317,318,319,320,321,322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,343,344,345,346,347,348,349,350,351,352,353,354,355,356,357,358,359,360,361,362,363,364,365,366,367,368,369,370,371,372,373,374,375,376,377,378,379,380,381,382,383,384,385,386,387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,406,407,408,409,410,411,412,413,414,415,416,417,418,419,420,421,422,423,424,425,426,427,428,429,430,431,432,433,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478,479,480,481,482,483,484,485,486,487,488,489,490,491,492,493,494,495,496,497,498,499,500,501,502,503,504,505,506,507,508,509,510,511,512,513,514,515,516,517,518,519,520,521,522,523,524,525,526,527,528,529,530,531,532,533,534,535,536,537,538,539,540,541,542,543,544,545,546,547,548,549,550,551,552,553,554,555,556,557,558,559,560,561,562,563,564,565,566,567,568,569,570,571,572,573,574,575,576,577,578,579,580,581,582,583,584,585,586,587,588,589,590,591,592,593,594,595,596,597,598,599,600,601,602,603,604,605,606,607,608,609,610,611,612,613,614,615,616,617,618,619,620,621,622,623,624,625,626,627,628,629,630,631,632,633,634,635,636,637,638,639,640,641,642,643,644,645,646,647,648,649,650,651,652,653,654,655,656,657,658,659,660,661,662,663,664,665,666,667,668,669,670,671,672,673,674,675,676,677,678,679,680,681,682,683,684,685,686,687,688,689,690,691,692,693,694,695,696,697,698,699,700,701,702,703,704,705,706,707,708,709,710,711,712,713,714,715,716,717,718,719,720,721,722,723,724,725,726,727,728,729,730,731,732,733,734,735,736,737,738,739,740,741,742,743,744,745,746,747,748,749,750,751,752,753,754,755,756,757,758,759,760,761,762,763,764,765,766,767,768,769,770,771,772,773,774,775,776,777,778,779,780,781,782,783,784,785,786,787,788,789,790,791,792,793,794,795,796,797,798,799,800,801,802,803,804,805,806,807,808,809,810,811,812,813,814,815,816,817,818,819,820,821,822,823,824,825,826,827,828,829,830,831,832,833,834,835,836,837,838,839,840,841,842,843,844,845,846,847,848,849,850,851,852,853,854,855,856,857,858,859,860,861,862,863,864,865,866,867,868,869,870,871,872,873,874,875,876,877,878,879,880,881,882,883,884,885,886,887,888,889,890,891,892,893,894,895,896,897,898,899,900,901,902,903,904,905,906,907,908,909,910,911,912,913,914,915,916,917,918,919,920,921,922,923,924,925,926,927,928,929,930,931,932,933,934,935,936,937,938,939,940,941,942,943,944,945,946,947,948,949,950,951,952,953,954,955,956,957,958,959,960,961,962,963,964,965,966,967,968,969,970,971,972,973,974,975,976,977,978,979,980,981,982,983,984,985,986,987,988,989,990,991,992,993,994,995,996,997,998,1035};
//		boolean b=l1.canCross(stones);
//		int k=l1.nthUglyNumber(25);
//		int[] A=new int[]{1, 2, 3, 8, 9, 10};
//		System.out.println(l1.numberOfArithmeticSlices(A));
//		Solution s=new Solution();
//		int[] nums=new int[]{1,2};
//		System.out.print(s.thirdMax(nums));
//		int[] nums=new int[]{1,3,2,4,3};
//		System.out.print(s.findDuplicate(nums));
		/*
		Map<Integer,Integer> test=new TreeMap<Integer,Integer>();
		test.put(7,9);
		test.put(1234,8);
		test.put(4345,3);
		//方法2或者1常用
		//遍历方法1
		for(Map.Entry<Integer, Integer> entry:test.entrySet()){    
		     System.out.println(entry.getKey()+"--->"+entry.getValue());    
		} 
		//遍历方法2
		for (Integer key : test.keySet()) {  			  
		    System.out.println("Key = " + key);  		  
		}
		for (Integer value : test.values()) {  			  
		    System.out.println("Value = " + value);  		  
		}  
		//遍历方法3
		Iterator<Map.Entry<Integer, Integer>> entries = test.entrySet().iterator(); 
		while (entries.hasNext()) {  			  
		    Map.Entry<Integer, Integer> entry = entries.next();  		  
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());  		  
		} 
		//遍历方法4-键找值，效率低
		for (Integer key : test.keySet()) {  			  
		    Integer value = test.get(key);  		  
		    System.out.println("Key = " + key + ", Value = " + value);  		  
		}
		*/
//		int[][] grid=new int[][]{};
//		SolutionHs hs=new SolutionHs();
//		System.out.print(hs.islandPerimeter(grid));
		
//		SolutionLL l=new SolutionLL();
//		SolutionLL.ListNode node1=l.new ListNode(1);
//		SolutionLL.ListNode node2=l.new ListNode(2);
//		SolutionLL.ListNode node3=l.new ListNode(3);
//		SolutionLL.ListNode node4=l.new ListNode(4);
//		SolutionLL.ListNode node5=l.new ListNode(5);
//		SolutionLL.ListNode node6=l.new ListNode(6);
//		SolutionLL.ListNode node7=l.new ListNode(7);
//		SolutionLL.ListNode node8=l.new ListNode(8);
//		node1.next=node2;
//		node2.next=node3;
//		node3.next=node4;
//		node4.next=node5;
//		node5.next=node6;
////		node6.next=node7;
//		SolutionLL.ListNode ans=l.reverseKGroup(node1, 3);
		//SolutionLL.ListNode ans=l.reverseList(node1, 3)[1];
//		while(ans!=null){
//			System.out.print(ans.val);
//			ans=ans.next;
//		}
		
//		node2=node3;
//		System.out.println(node2.val);
		//node2.next=node3;
		//node3.next=node4;
//		node4.next=node5;
//		node5.next=node6;
//		node6.next=node7;
//		node7.next=node8;
		//test
		/*
		SolutionLL.ListNode it=node1.next;
		SolutionLL.ListNode temp=node1.next.next;
		node1.next.next=node1;
		node1.next=temp;
		while(it.next!=null){
			System.out.println(it.val);
			//可以这样遍历
			it=it.next;
		}
		System.out.println(node2.next.next.next.val);*/
		//SolutionLL.ListNode it=l.swapPairs(node1);
//		SolutionLL.ListNode it=l.oddEvenList(node1);
//		while(it!=null){
//			System.out.println(it.val);
//			it=it.next;
//		}
		//System.out.println(it.next.next.next.val);
		//System.out.print(l.isPalindrome(node1));
		//SolutionLL.ListNode it=l.reverseList(node1);
		/*
		SolutionLL.ListNode it=l.removeElements(node1, 2);
		while(it!=null){
			System.out.println(it.val);
	 		it=it.next;
	    }*/
//		SolutionDc dc=new SolutionDc();
//		//int start=0;
//		int[] nums=new int[]{5,4,5};
//		//int target=nums[0];
//		for(int i:dc.countSmaller1(nums)){
//			System.out.println(i);
//		}
		//System.out.println(dc.findSmaller1(nums, start, target));
		/*SolutionTree t=new SolutionTree();
		SolutionTree.TreeNode n1=t.new TreeNode(1);
		SolutionTree.TreeNode n2=t.new TreeNode(2);
		SolutionTree.TreeNode n3=t.new TreeNode(3);
		SolutionTree.TreeNode n4=t.new TreeNode(4);
		SolutionTree.TreeNode n5=t.new TreeNode(5);
		SolutionTree.TreeNode n6=t.new TreeNode(6);
		SolutionTree.TreeNode n7=t.new TreeNode(7);
		n5.left=n3;
		n5.right=n6;
		n3.left=n1;
		n3.right=n4;
//		n6.right=n7;
		SolutionTree.TreeNode result=t.deleteNode(n5, 4);*/
		
		/*Map<Integer, String> record=new HashMap<Integer, String>();
		String s="adgh";
		record.put(0, s.charAt(0)+"");
		record.put(1,record.get(0)+s.charAt(1));
		System.out.println(record.get(1).length());*/
		//SolutionStr ss=new SolutionStr();
		//int h=ss.lengthOfLongestSubstring("ddf");
		//String h=ss.convert("Aiugj", 5);
		//System.out.print(h);
//		solution x=new solution();
//		int[] nums={0,4,3,0};
//		System.out.print(x.twoSum(nums, 0)[0]+" "+x.twoSum(nums, 0)[1]);
		
//		SolutionStr s1=new SolutionStr();
//		System.out.print(Math.abs(-2147483647));
		//solution ma=new solution();
//		System.out.print(ma.hammingDistance(4, 2));
//		sol so=new sol();
//		int[] nums={1,2,3};
//		System.out.print(so.Helper(nums, 3));
//		String s="abcd";
//		System.out.print(s.substring(0,2));
//		int[] nums={1};
//		System.out.println(ma.findRotateIndex(nums));
//		System.out.print(ma.search(nums, 0));
//		String digits="";
//		System.out.print(digits.length());
//		int[][] matrix={{1,2,3},{4,5,6}};
//		Solution zz=new Solution();
//		int n=3;
//		int[][] res=zz.generateMatrix(n);
//		for(int i=0;i<n;i++){
//			System.out.println("");
//			for(int j=0;j<n;j++){
//				System.out.print(res[i][j]+" ");
//			}
//		}
		SolutionStr zz=new SolutionStr();
		//System.out.print(zz.maxWindow("ADOBECODEBANC", "ABC"));
		/*
		char:16
        int:32
        short:16
        long:64
        byte:8
        float:32
        double:64
		 */
//		char x=(int) 8190;
//		System.out.print(x);
//		x^=64;//异或运算
//		x^=64;
//		char x2='j';
//		x2*=256;
//		x2*=256;
//		int i=2;
//		i^=256;
//		System.out.print(x);
		Solution arr=new Solution();
		int[] nums={1,1,1,2,2,2};
		System.out.print(arr.removeDuplicates2(nums));
	}

}
