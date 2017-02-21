package LinkedList;

public class SolutionLL {
	/**
	 * Definition for singly-linked list.*/
	public class ListNode {
	    public int val;
	    public ListNode next;
	    public ListNode(int x) { val = x; }
	}
	public ListNode swapPairs(ListNode head) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode result=head.next;
		ListNode temp;
		ListNode before=head;
		while(head!=null&&head.next!=null){
			//交换
			if(head.next.next==null){
				temp=null;
			}else{
				temp=head.next.next;
			}
			//同时连上前端
			before.next=head.next;
			//进行后端交换
			head.next.next=head;
			head.next=temp;			
			//不交换
			before=head;
			head=head.next;
		}
		return result;
	}
    public ListNode oddEvenList(ListNode head) {
    	if(head==null||head.next==null){
			return head;
		}
    	//ListNode evenend=head.next;
    	//ListNode oddend=head;
    	//ListNode temp;
    	//ListNode[3]
    	ListNode[] storge=new ListNode[4];
    	storge[0]=head.next;
    	storge[1]=head;
    	storge[2]=null;
    	storge[3]=head.next;
    	while(storge[0]!=null&&storge[0].next!=null){
    		storge[2]=storge[0].next.next;
    		storge[1].next=storge[0].next;
    		storge[1].next.next=storge[3];
    		storge[0].next=storge[2];
    		storge[1]=storge[1].next;
    		storge[0]=storge[0].next;
    	}
    	return head;
    }
    
    public boolean isPalindrome(ListNode head) {
    	/*
        //找中点,中点左右判断回文
        //计数长度找中点，需2次
        //回文特性找中点，1次，记录一前一后两个，判断出现回文即中点，否则遍历完则false
    	//认为空或1为回文
    	if(head==null||head.next==null){
			return true;
		}
        ListNode left=head;
        ListNode right=head.next;
        //长度为2
        if(right.next==null){
        	return left.val==right.val?true:false;
        }
        //遍历找中点
        while(right.next!=null){
        	//以及right.next.next为Null情况
        	if(right.next.next!=null){
        		if(right.next.val==right.val&&right.next.next.val==left.val){
        			break;
            	}else if(right.next.val==left.val){            		
            		break;
            	}else{
            		left=right;
            		right=right.next;
            	}
        	}else{
                if(right.next.val==left.val){            		
                	break;
            	}else{
            		left=right;
            		right=right.next;
            	}
        	}
        	
        }
        if(right.next==null){
        	return false;
        }
        //两侧遍历
        //先初始化
        if(right.next.val==left.val){
        	right=right.next;
        }else{
        	right=right.next.next;
        }
        //但两侧是单链、、
        */
    	//只能翻转链表
    	//快慢指针找中点
    	
    	ListNode slow=head;  
    	ListNode fast=head;  
    	   
    	if(fast==null||fast.next==null)//0个节点或是1个节点  
    	     return true;  
    	  
    	  
    	while(fast.next!=null&&fast.next.next!=null)  {  
    	    fast=fast.next.next;  
    	    slow=slow.next;  
    	}  
    	//对链表后半段进行反转  
    	ListNode midNode=slow;  
    	ListNode firNode=slow.next;//后半段链表的第一个节点  
    	ListNode cur=firNode.next;//插入节点从第一个节点后面一个开始  
    	firNode.next=null;//第一个节点最后会变最后一个节点  
    	while(cur!=null){  
    	    ListNode nextNode=cur.next;//保存下次遍历的节点  
    	    cur.next=midNode.next;  
    	    midNode.next=cur;  
    	    cur=nextNode;  
    	}  
    	   
    	//反转之后对前后半段进行比较  
    	slow=head;  
    	fast=midNode.next;  
    	while(fast!=null){  
    	    if(fast.val!=slow.val)  
    	        return false;  
    	    slow=slow.next;  
    	    fast=fast.next;  
    	}  
    	return true;
    }
    public ListNode reverseList(ListNode head) {
    	if(head==null||head.next==null){
    		return head;
    	}
        ListNode reverseHead=head;
        ListNode reverseEnd=head;
        ListNode temp1,temp2;
        while(reverseEnd.next!=null){
        	temp1=reverseEnd.next;
            temp2=reverseEnd.next.next;
            reverseEnd.next.next=reverseHead;
            reverseEnd.next=temp2;
            reverseHead=temp1;
        }
        return reverseHead;
    }
    public ListNode removeElements(ListNode head, int val) {
        if(head==null){
        	return null;
        }
        //find the head first
        ListNode result=head;
        while(head!=null&&head.val==val){
        	//保存
        	result=head.next;
        	//去除垃圾点
        	head.next=null;
        	//转换
        	head=result;
        }
        //find head and then find
        ListNode left=null,right=null;
        if(result!=null){
        	left=result;
            right=result.next;
        }
        while(right!=null){
        	if(right.val==val){
        		left.next=right.next;
        		right.next=null;
        		right=left.next;
        	}else{
        		left=left.next;
        		right=right.next;
        	}
        }
        return result;
        
       
        
    }
    //A,B不断走环定成圈相遇
    //记录A,B长度，再进行吧,但这样就是后面必须并成一条，不能再分开？
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    	int resultA=0,resultB=0,i=0;
    	ListNode startA=headA;
    	ListNode startB=headB;
    	//长度
    	while(headA!=null){
    		resultA+=1;
    		headA=headA.next;
    	}
    	while(headB!=null){
    		resultB+=1;
    		headB=headB.next;
    	}
    	//起点更改
    	if(resultA>=resultB){
    		while(i<resultA-resultB){
    			startA=startA.next;
    			i++;
    		}
    	}else{
    		while(i<resultB-resultA){
    			startB=startB.next;
    			i++;
    		}
    	}
    	//比较
    	ListNode result=null;
    	while(startB!=null){
    		if(startA==startB){
    			return startA;
    		}else{
    			startA=startA.next;
    			startB=startB.next;
    		}
    	}
    	return result;
            
    }
    public ListNode reverseKGroup(ListNode head, int k) {
    	//长度
    	int length=0;
    	ListNode measure=head;
    	ListNode[] res=new ListNode[2]; 
    	while(measure!=null){
    		length++;
    		measure=measure.next;
    	}
    	if(length<k){
    		return head;
    	}
    	int num=length/k;
    	res=reverseList(head,k);
    	int i=1;
    	while(i<num){
    		ListNode[] tmp=reverseList(res[1].next,k);
    		res[1].next=tmp[0];
    		res[1]=tmp[1];
    		i++;
    	}	
    		
    	return res[0];
    }
	public ListNode[] reverseList(ListNode head, int k) {
		int count=1;
		ListNode[] res=new ListNode[2];//0头1尾
		if(head==null||head.next==null){
    		res[0]=head;
    		res[1]=res[0];
    		return res;
    	}
        ListNode reverseHead=head;
        ListNode reverseEnd=head;
        ListNode temp1,temp2;
        while(reverseEnd.next!=null && count<k){
        	temp1=reverseEnd.next;
            temp2=reverseEnd.next.next;
            reverseEnd.next.next=reverseHead;
            reverseEnd.next=temp2;
            reverseHead=temp1;
            count++;
        }
        res[0]=reverseHead;
        res[1]=reverseEnd;
        return res;
		
	}

}
