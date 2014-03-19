package com.amos.model;

import java.math.BigDecimal;

/** 
* @ClassName: Calculator 
* @Description: 计算器
* @author: amosli
* @email:amosli@infomorrow.com
* @date Mar 20, 2014 1:04:59 AM  
*/
public class Calculator {
	private String firstnum="0";
	private String secondnum="0";
	private char operator='+';
	private String result;

	public String getResult() {
		return result;
	}

	public char getOperator() {
		return operator;
	}

	public void setOperator(char operator) {
		this.operator = operator;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFirstnum() {
		return firstnum;
	}

	public void setFirstnum(String firstnum) {
		this.firstnum = firstnum.trim();
	}

	public String getSecondnum() {
		return secondnum;
	}

	public void setSecondnum(String secondnum) {
		this.secondnum = secondnum.trim();
	}

	public void calculate() {
		BigDecimal a = new BigDecimal(this.firstnum);
		BigDecimal b = new BigDecimal(this.secondnum);
		switch (this.operator) {
		case '+':
			this.result = a.add(b).toString();
			break;
		case '-':
			this.result = a.subtract(b).toString();
			break;
		case '*':
			this.result = a.multiply(b).toString();
			break;
		case '/':
			if (b.doubleValue()==0) {
				throw new RuntimeException("被除数不能为零");
			}
			this.result = a.divide(b,10,BigDecimal.ROUND_HALF_DOWN).toString();
			break;
		default:
			break;
		}
	}
}
