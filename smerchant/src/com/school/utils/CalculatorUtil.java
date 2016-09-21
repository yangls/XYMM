package com.school.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 计算器
 * @author acode
 *
 */
public class CalculatorUtil {

	/**
	 * 2数相加
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double add(double d1,double d2){
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		
		return b1.add(b2).doubleValue();
		
	}
	/**
	 * d1-d2
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static double subtra(double d1,double d2){
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
		
	}
	
	/**
     * 提供精确的乘法运算。
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积
     */
    public static double mul(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
	
	
	
	/**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
	public static double div(double v1,double v2,int scale){
		
		if(scale<0){
			scale=2;
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 格式化金额
	 * @param number
	 * @return 返回2位小数的金额 返回string
	 */
	public static String doubleDic(double number){
		DecimalFormat df=new DecimalFormat("0.00");
		return df.format(number);
		
	}
	/**
	 * 格式化金额 分转元
	 * @return
	 */
	public static String changerDic(String money){
		double m =Double.parseDouble(money);
		m=m/100;
		DecimalFormat df=new DecimalFormat("0.00");
		return df.format(m);
		
	}
	/**
	 * 保留2为小数(四舍五如)返回 double
	 * @param d
	 * @return
	 */
	public static double doubleFormat(double d){
		DecimalFormat df= new DecimalFormat("#.##");
		 double result=Double.parseDouble(df.format(d));
		 return result;
		
	}
	
	public static void main(String[] args) {
		double a1=23.0;
		double a2=-10;
		//System.out.println(subtra(a1,a2));
		//System.out.println(add(a1,a2));
		
	}
}
