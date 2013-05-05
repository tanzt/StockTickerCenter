package com.util.mina;

import java.util.Random;

public class RandomUtil {

	public static int RandomInt(int max) // 随机生成[0,max)内的整数
	{
		Random ran = new Random();
		return (ran.nextInt(max));
	}

	

	// 随机生成一个(-0.1,0.1)之间的double数

	public static double RandomDouble() {
		Random random = new Random();
		Random random2 = new Random();
		int randonNum = random.nextInt(10);	 
		int index = 0 - randonNum; // 生成一个随机负数
		return ((random2.nextInt(10) + index)/10.0);

	}

}