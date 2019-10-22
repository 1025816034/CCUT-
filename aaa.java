package a;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class aaa {

	
	public static int min = 0; 
	public static int fl = 1; 

	static int need_Negative(int flag, int max) {
		if (flag == 1) {
			min = -max;
		}
		return (max - min + 1);
	}
	
	static int get_num(int haveorno_fu) {
		Random auto_number = new Random();
		int a = auto_number.nextInt(haveorno_fu) + min;
		return a;
	}

	static float get_result(int nux, char op, int nuy) {

		float res = 0;

		if (op == '+') {
			res = nux + nuy;
			return res;
		} else if (op == '-') {
			res = nux - nuy;
			return res;
		} else if (op == '*') {
			res = nux * nuy;
			return res;
		} else if (op == '/') {
			return (float) Math.round(((float) nux / nuy) * 100) / 100;
		} else {
			return 0000;
		}
	}

	
	static char need_MulandDiv(int flag) {
		char x = 0;
		if (flag == 1) {
			char[] ch = { '+', '-', '*', '/' }; // 字符数组
			Random r = new Random();
			int index = r.nextInt(ch.length); // 随机数，小于数组的长度数, 0~3
			x = ch[index];// 打印随机字符
		} else if (flag == 0) {
			char[] ch = { '+', '-' }; // 字符数组
			Random r = new Random();
			int index = r.nextInt(ch.length); // 随机数，小于数组的长度数, 0~1
			x = ch[index];
		}
		return x;
	}

	
	static String get_minus(float numz) {
		if (numz < 0) {
			return "(" + numz + ")";
		}else {
			return numz + "";
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

		// TODO Auto-generated method stub
		try {

			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入要生成的练习题数量：");
			int expression_Count = scanner.nextInt();

			System.out.println("请输入操作数范围（如输入100，表示100以内）：");
			int max_toprange = scanner.nextInt();

			System.out.println("请输入是否需要负数（1代表是，0代表否）：");
			int zheng_fu = scanner.nextInt();
			int rand_num = need_Negative(zheng_fu, max_toprange);
			int haveorno_fu = rand_num;

			System.out.println("请输入是否包含乘法和除法（1代表是，0代表否）：");
			int cheng_chu = scanner.nextInt();

			int[] numx = new int[expression_Count + 1];
			char[] numc = new char[expression_Count];
			int[] numy = new int[expression_Count + 1];
			float[] numz = new float[expression_Count];

			int i = 0;

			while (fl <= expression_Count) {
				char operator = need_MulandDiv(cheng_chu);

				int a = get_num(haveorno_fu);
				int b = get_num(haveorno_fu);

				float resultz = get_result(a, operator, b);
				if (resultz <= max_toprange && resultz >= -max_toprange) {
					// 过滤重复算式
					if (i == 0) {// 第一题存入
						numx[i] = a;
						numc[i] = operator;
						numy[i] = b;
						numz[i] = get_result(a, operator, b);
						i++;
					} else {
						for (int j = 0; j < i; j++) {// 第二题开始与之前每一道做比较

							if (a != numx[j] || operator != numc[j] || b != numy[j]) {
								numx[i] = a;
								numc[i] = operator;
								numy[i] = b;
								numz[i] = get_result(a, operator, b);
							} else {
								fl--;// 如果有重复多加一道
							}
						}
						i++;
					}
					fl++;
				}

			}

			/*
			 * 存入文件
			 */
			File file = new File("E:\\result.txt");
			Writer out = new FileWriter(file);
			System.out.println("存入txt文件中.............");
			for (int m = 0; m < fl - 1; m++) {
				out.write(get_minus(numx[m]) + " " + numc[m] + " " + get_minus(numy[m]) + "  =  \r\n");
			}
			System.out.println("存入完成................");
			out.close();

			/*
			 * 打印答案
			 */
			System.out.println("请选择是否打印答案（1代表打印，0代表不打印）");
			int yesno_print = scanner.nextInt();
			if (yesno_print == 1) {
				for (int m = 0; m < fl - 1; m++) {
					System.out.println(get_minus(numx[m]) + " " + numc[m] + " " + get_minus(numy[m]) + "  =  " + get_minus(numz[m]));
				}
			}
			System.out.println("打印完成。");

		} catch (InputMismatchException e) {
			System.err.println("数据有误。请输入符合要求的整型数据");
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
}