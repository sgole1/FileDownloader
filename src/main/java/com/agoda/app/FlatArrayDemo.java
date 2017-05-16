package com.agoda.app;

import com.agoda.util.CommonUtil;

public class FlatArrayDemo {

	public static void main(String[] args) {
		
		int[][] nestedData = {{1},{2,3},{5,6,7}};
		Integer[] flatArray = CommonUtil.getFlatArray(nestedData);
		System.out.println("Flat array from nested array :");

		for(int i = 0;i <flatArray.length-1 ;i++){
			System.out.print(flatArray[i]+",");
		}
		
		System.out.print(flatArray.length-1);
	}
}
