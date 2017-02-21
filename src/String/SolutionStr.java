package String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Map;

public class SolutionStr {
    public boolean canConstruct(String ransomNote, String magazine) {
    	int[] arr=new int[26];
    	for(int i=0;i<magazine.length();i++){
    		arr[magazine.charAt(i)-'a']++;
    	}
    	for(int i=0;i<ransomNote.length();i++){
    		arr[ransomNote.charAt(i)-'a']--;
    		if(arr[ransomNote.charAt(i)-'a']<0){
    			return false;
    		}
    		//上述可直接写
    		/*
    		if(--arr[ransomNote.charAt(i)-'a']<0){
			    return false;
		    }
    		*/
    	}
    	return true;
	}
    public String reverseVowels(String s) {
    	List<Integer> index=new ArrayList<Integer>();
    	//Set<Character> tofind=new HashSet<Character>();
    	//tofind.add('a');
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u'||s.charAt(i)=='A'||s.charAt(i)=='E'||s.charAt(i)=='I'||s.charAt(i)=='O'||s.charAt(i)=='U'){
        		index.add(i);
        	}
        	//if(tofind.contains(s.charAt(i))){
        		
        	//}
        }
        int n=index.size();
        int[] _index=new int[n];
        for(int i=0;i<index.size();i++){
        	_index[n-i-1]=index.get(i);
        }
        StringBuilder result=new StringBuilder();
        int j=0;
        for(int i=0;i<s.length();i++){
        	if(s.charAt(i)=='a'||s.charAt(i)=='e'||s.charAt(i)=='i'||s.charAt(i)=='o'||s.charAt(i)=='u'||s.charAt(i)=='A'||s.charAt(i)=='E'||s.charAt(i)=='I'||s.charAt(i)=='O'||s.charAt(i)=='U'){
        		result.append(s.charAt(_index[j]));
        		j++;
        	}else{
        		result.append(s.charAt(i));
        	}
        }
        return result.toString();
        
        /*better
    if(s == null || s.length()==0) return s;
    String vowels = "aeiouAEIOU";
    char[] chars = s.toCharArray();
    int start = 0;
    int end = s.length()-1;
    while(start<end){
        
        while(start<end && !vowels.contains(chars[start]+"")){
            start++;
        }
        
        while(start<end && !vowels.contains(chars[end]+"")){
            end--;
        }
        
        char temp = chars[start];
        chars[start] = chars[end];
        chars[end] = temp;
        
        start++;
        end--;
    }
    return new String(chars);
         */
    }
    public int lengthOfLongestSubstring(String s) {
    	if(s==null ||s.length()==0){
    		return 0;
    	}
        Map<Integer, String> record=new HashMap<Integer, String>();
        record.put(0, s.charAt(0)+"");
        int result=1;
        for(int i=1;i<s.length();i++){
        	String get=record.get(i-1);
        	String compare=s.charAt(i)+"";
        	if(get.contains(compare)){        		
        		record.put(i, get.substring(get.indexOf(compare)+1)+compare);
        	}else{
        		record.put(i,get+compare);
        		if(record.get(i).length()>result){
        			result=record.get(i).length();
        		}
        	}
        }
        
        for(Integer i:record.keySet()){
        	System.out.println(record.get(i));
        }
        return result;
    }
    public String convert(String s, int numRows) {
    	if(numRows==1 || s.length()==0 || s==null){
    		return s;
    	}
        int nums=(2*numRows-2);
        int index=s.length()/nums;
        int other=s.length()%nums;
        char[] chars=s.toCharArray();
        String[] result=new String[numRows];
        for(int i=0;i<numRows;i++){
        	result[i]="";
        }
        //数组初始化
        //int []arr1=new int [5];
        //Array.fill(arr1,5);
        for(int i=0;i<index;i++){
        	for(int j=0;j<numRows;j++){
        		if(j!=0&&j!=numRows-1){
        			result[j]+=chars[i*nums+j];
        			result[j]+=chars[i*nums+nums-j];
        		}else if(j==0){
        			result[j]+=chars[i*nums];
        		}else{
        			result[j]+=chars[i*nums+j];
        		}       		
        	}        	
        }
        if(other>0&&other<=numRows){
        	for(int j=0;j<other;j++){
        	    result[j]+=chars[index*nums+j];     		
        	}
        }else if(other>numRows){
        	for(int j=0;j<numRows;j++){
        	    result[j]+=chars[index*nums+j];        	    
        	}
        	for(int i=0;i<other-numRows;i++){
        		result[numRows-2-i]+=chars[index*nums+numRows+i];
        	}        	
        }
        String finalResult="";
        for(int i=0;i<numRows;i++){
        	if(result[i]!=null){
        		finalResult+=result[i];
        	}   	
        }
        return finalResult;
    }
    //Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
        /* implements//但有更好的优化方法，忘记名字了，对之前的比较有记录
         for (int i = 0; ; i++) {
             for (int j = 0; ; j++) {
                 if (j == needle.length()) return i;
                 if (i + j == haystack.length()) return -1;
                 if (needle.charAt(j) != haystack.charAt(i + j)) break;
             }
         }
         */
    }
    int maxlen=0,start,end;
    public String longestPalindrome(String s){
    	int len=s.length();
    	if(len<2){
    		return s;
    	}
    	for(int i=0;i<len;i++){
    		Helper(s,i,i);
    		Helper(s,i,i+1);
    	}
    	return s.substring(start, end);
    }
    private void Helper(String s,int i,int j){
    	while(i>=0&&j<s.length()&&s.charAt(i)==s.charAt(j)){
    		i--;
    		j++;
    	}
    	if(maxlen<j-i-1){
    		maxlen=j-i-1;
    		start=i+1;
    		end=j;//=maxlen+start
    	}
    }
    public List<String> letterCombinations(String digits) {
    	LinkedList<String> ans = new LinkedList<String>();
    	if(digits.length()==0) return ans;
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        for(int i =0; i<digits.length();i++){
            int x = Character.getNumericValue(digits.charAt(i));
            if(x==-1) return new LinkedList<String>();
            while(ans.peek().length()==i){
                String t = ans.remove();
                for(char s : mapping[x].toCharArray())
                    ans.add(t+s);
            }
        }
        return ans;
        
    }
    //注意用个list不断remove保持次序
    public String getPermutation(int n, int k) {
    	List<Integer> num = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) num.add(i);
        int[] fact = new int[n];  // factorial
        fact[0] = 1;
        for (int i = 1; i < n; i++) fact[i] = i*fact[i-1];
        k = k-1;
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--){
            int ind = k/fact[i-1];
            k = k%fact[i-1];
            sb.append(num.get(ind));
            num.remove(ind);
        }
        return sb.toString();
    }
    //垃圾题目
    public boolean isNumber(String s) {
    	s = s.trim();
        
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for(int i=0; i<s.length(); i++) {
            if('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
                numberAfterE = true;
            } else if(s.charAt(i) == '.') {
                if(eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if(s.charAt(i) == 'e') {
                if(eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if(s.charAt(i) == '-' || s.charAt(i) == '+') {
                if(i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        return numberSeen && numberAfterE;
    }
    public String addBinary(String a, String b) {
        //末尾加，遇2进1
    	StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
    public String minWindow(String s, String t) {
        int[] map=new int[256];
        for(int i=0;i<t.length();i++){
        	map[(int)t.charAt(i)]++;
        }
        int counter=t.length(),head=0,end=0,length=Integer.MAX_VALUE,begin=0;
        while(end<s.length()){     	
        	if(map[(int)s.charAt(end++)]-->0) counter--;//先比较>0再--   	
        	while(counter==0){
        		length=(end-head)<length?(end-(begin=head)):length;
//        		if((end-head)<length){
//        			length=(end-head);
//        			begin=head;
//        		}
        		if(map[(int)s.charAt(head++)]++==0) counter++;
        	}
        }
        return length==Integer.MAX_VALUE?"":s.substring(begin,begin+length);
    }
    public String maxWindow(String s, String t) {
        int[] map=new int[256];
        for(int i=0;i<t.length();i++){
        	map[(int)t.charAt(i)]++;
        }
        int counter=t.length(),head=0,end=0,length=0,begin=0;
        while(end<s.length()){     	
        	if(map[(int)s.charAt(end++)]-->0) counter--;//先比较>0再--   	
        	while(counter==0){
        		length=(end-head)>length?(end-(begin=head)):length;
//        		if((end-head)<length){
//        			length=(end-head);
//        			begin=head;
//        		}
        		if(map[(int)s.charAt(head++)]++==0) counter++;
        	}
        }
        return length==0?"":s.substring(begin,begin+length);
    }
//template
//    int findSubstring(String s){
//    	  int map[128] = {0};
//        int counter; // check whether the substring is valid
//        int begin=0, end=0; //two pointers, one point to tail and one  head
//        int d; //the length of substring
//
//        for() { /* initialize the hash map here */ }
//
//        while(end<s.size()){
//
//            if(map[s[end++]]-- ?){  /* modify counter here */ }
//
//            while(/* counter condition */){ 
//                 
//                 /* update d here if finding minimum*/
//
//                //increase begin to make it invalid/valid again
//                
//                if(map[s[begin++]]++ ?){ /*modify counter here*/ }
//            }  
//
//            /* update d here if finding maximum*/
//        }
//        return d;
//     }
    //最长无重复
    public int lengthOfLongestSubstring2(String s) {
    	int[] map=new int[128];
    	int begin=0,end=0,length=0,counter=0;
    	while(end<s.length()){
    		if(map[s.charAt(end++)]--<0) counter++;
    		while(counter>0) if(map[s.charAt(begin++)]++<-1) counter--;
    		length=Math.max(length, end-begin);
    	}
    	return length;
    
    }
    public int lengthOfLongestSubstringTwoDistinct(String s){
    	int[] map=new int[128];
    	int begin=0,end=0,length=0,counter=0;
    	while(end<s.length()){
    		if(map[s.charAt(end++)]--==0) counter++;
    		while(counter>2) if(map[s.charAt(begin++)]++==-1) counter--;
    		length=Math.max(length, end-begin);
    	}
    	return length;
    }
     
}
