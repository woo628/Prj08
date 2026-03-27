package ex02;

import java.time.LocalDate;
import java.util.Scanner;

//입력 1줄 -> 결과 1줄
//입력data : 사번,이름,입사일,월급,부서번호
//출력     : 사번,이름,입사일,월급,보너스,수령액,부서명

//금액은 소수이하 두자리로 반올림
//보너스   =  근무연수에 따라 월급의 0.5% 로 계산한다
//수령액   =  월급 + 보너스
//부서명   =  10:인사,20:자재,30:총무,40:연구개발,50:생산,60:서비스

//모든기능은 class에 구현한다.
//입력data
//사번,이름,입사일,월급,부서번호
/*
100,사나,20110101,300.0,10      
200,모모,20120301,270.0,20      
300,정연,20091003,250.0,30      
400,나연,20110105,220.0,40      
500,미나,20180401,170.0,60      
600,쯔위,20150801,200.0,50      
*/

interface Mem {
	void input();
	void process();
	void output();
}

class MemVo {
	private String num;
	private String name;
	private String date;
	private double money;
	private int bnum;
	
	private double bonus;
	private double pay;
	private String bname;
	
	public MemVo(String num, String name, String date, double money, int bnum) {
		this.num = num;
		this.name = name;
		this.date = date;
		this.money = money;
		this.bnum = bnum;
	}

	@Override
	public String toString() {
		return "MemVo [num=" + num + ", name=" + name + ", date=" + date + ", money=" + money + ", bnum=" + bnum
				+ ", bonus=" + bonus + ", bname=" + bname + ", pay=" + pay + "]";
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	
}

class Member implements Mem{

	private MemVo  m;
	
	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("사번,이름,입사일,월급,부서번호를 입력하시오");
		String line = sc.nextLine();
		String[] li = line.trim().split(",");
		String num =  li[0].trim();
		String name = li[1].trim();
		String date = li[2].trim();
		double money = Double.parseDouble(li[3].trim());
		int bnum = Integer.parseInt(li[4].trim());
		
		m = new MemVo(num, name, date, money, bnum);
		//System.out.println(m);
		
		sc.close();
	}
	
	@Override
	public void process() {
		// bnum -> bname
		int bnum = m.getBnum();
		String bname = "";
		switch (bnum) {
		case 10 : bname = "인사"; break; 
		case 20 : bname = "자재"; break; 
		case 30 : bname = "총무"; break; 
		case 40 : bname = "연구개발"; break; 
		case 50 : bname = "생산"; break; 
		case 60 : bname = "서비스"; break; 
		default:
			throw new IllegalArgumentException("Unexpected value: " + bnum);
		}
		
		m.setBname(bname);
		
		// 근무연수
		LocalDate now = LocalDate.now();
		int y = Integer.parseInt(m.getDate().substring(0,4));
		int rdate = now.getYear() - y;
		
		// bonus = 근무연수 *(money * 0.5%)
		m.setBonus(rdate*(m.getMoney()*0.005));
		
		// pay = bonus + money
		m.setPay(m.getMoney()+ m.getBonus());
		
	}

	@Override
	public void output() {
		String fmt = "사번:%s 이름:%s 입사일:%s 월급:%.2f만원 보너스:%.2f만원 수령액:%.2f만원 부서명:%s ";
		System.out.printf(fmt,m.getNum(),m.getName(),m.getDate(),m.getMoney(),m.getBonus(),m.getPay(),m.getBname());
	}
	
	
}

public class TestProject {

	public static void main(String[] args) {
		
		Member m = new Member();
		m.input();
		m.process();
		m.output();
		
	}

}
