package com.util.mina;

import java.util.Random;

public class RandomUtil {

	public static int RandomInt(int max) // �������[0,max)�ڵ�����
	{
		Random ran = new Random();
		return (ran.nextInt(max));
	}

	

	// �������һ��(-0.1,0.1)֮���double��

	public static double RandomDouble() {
		Random random = new Random();
		Random random2 = new Random();
		int randonNum = random.nextInt(10);	 
		int index = 0 - randonNum; // ����һ���������
		return ((random2.nextInt(10) + index)/10.0);

	}

}