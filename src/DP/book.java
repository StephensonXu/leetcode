package DP;

import java.util.Scanner;
//背包问题另一种想法
/*
public class book {
	static class GType{
		double value;
		double weight;
		char isSelect;
	}
	static double maxvalue;
	static double totalvalue;
	static double maxwt;
	static int num;
	static char[] seltemp;
	static void backpack(GType[] goods,int i,double wt,double vt){
		if(wt+goods[i].weight<=maxwt){
			seltemp[i]=1;
			if(i<num-1){
				backpack(goods,i+1,wt+goods[i].weight,vt);
			}else{
				for(int k=0;k<num;++k){
					goods[k].isSelect=seltemp[k];
				}
				maxvalue=vt;
			}
		}
		seltemp[i]=0;
		if(vt-goods[i].value>maxvalue){
			if(i<num-1){
				backpack(goods,i+1,wt,vt-goods[i].value);
			}else{
				for(int k=0;k<num;++k){
					goods[k].isSelect=seltemp[k];					
				}
				maxvalue=vt-goods[i].value;
			}
		}
	}
	public static void main(String[] args){
		double sumweight;
		int i;
		System.out.println("能容纳最大重量");
		Scanner input=new Scanner(System.in);
		maxwt=input.nextDouble();
		System.out.println("可选物品数量");
		num=input.nextInt();
		GType[] goods=new GType[num];
		seltemp=new char[num];
		totalvalue=0;
		for(i=0;i<num;i++){
			GType t=new GType();
			System.out.println("输入第"+(i+1)+"号物品价值和重量");
			t.weight=input.nextDouble();
			t.value=input.nextDouble();
			totalvalue+=t.value;
			goods[i]=t;
		}
		System.out.println("背包最多装的重量为");
		for(int k=0;k<num;k++){
			System.out.println("第"+(k+1)+"号物品重:"+goods[k].weight+"，价值"+goods[k].value);
		}
		for(int k=0;k<num;k++){
			seltemp[k]=0;
		}
		maxvalue=0;
		backpack(goods,0,0.0,totalvalue);
		System.out.println(maxvalue);
	}

}
*/