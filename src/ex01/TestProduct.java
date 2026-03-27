package ex01;

import java.util.Scanner;

/* csv format- comma seperator variable
1,P01,10,1250.0
2,P02,15,2000.0
3,P03,30,1500.0
5,P04,25,2000.0
4,P05,28,1900.0
*/
  // 입력:번호,제품코드,수량,단가
  //      num, pcode, amount, price
  // P01:새우깡, P02:빅파이, P03:짱구, P04:초코파이, P05:수박맛초코파이
  // 출력:번호,제품명,수량,단가,금액(수량*단가)
  //      num, pname, amount, price, kum 

interface Ipo {
	void input();
	void process();
	void output();
}

// Data 담당 class
// DTO : Data Transfer Object - 순수 data
// VO :  Value Object - 가공 data
class ProdVo {
	// Filed
	private int num;
	private String pcode;
	private int amount;
	private double price;
	
	private String pname;
	private double kum;
	
	// Constructor
	public ProdVo(int num, String pcode, int amount, double price) {
		this.num = num;
		this.pcode = pcode;
		this.amount = amount;
		this.price = price;
	}

	// toString
	@Override
	public String toString() {
		return "ProdVo [num=" + num + ", pcode=" + pcode + ", amount=" + amount + ", price=" + price + ", pname="
				+ pname + ", kum=" + kum + "]";
	}
	
	// getter & setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getKum() {
		return kum;
	}

	public void setKum(double kum) {
		this.kum = kum;
	}
	
}

// 업무처리 class
class Product implements Ipo {
	
	private ProdVo p;
	
	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("입력:번호,제품코드,수량,단가");
		String line = sc.nextLine();
		String[] li = line.trim().split(",");
		int num = Integer.parseInt(li[0].trim());
		String pcode = li[1].trim();
		int amount = Integer.parseInt(li[2].trim());
		double price = Double.parseDouble(li[3].trim());
		
		p =  new ProdVo(num, pcode, amount, price);
		//System.out.println(p);
		
		sc.close();
	}

	@Override
	public void process() {
		// pcode → pname 
		String pcode = p.getPcode().toUpperCase();
		String pname = "";
		switch (pcode) {
		case "P01": pname = "새우깡"; break;
		case "P02": pname = "빅파이"; break;
		case "P03": pname = "짱구"; break;
		case "P04": pname = "초코파이"; break;
		case "P05": pname = "수박맛오예스"; break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + pcode);
		}
		p.setPname(pname);
		
		//kum 계산 (amount * price)
		p.setKum(p.getAmount() * p.getPrice());
	}

	@Override
	public void output() {
		String title = "번호, 제품명, 수량, 단가, 금액(수량*단가)";
		String fmt = " %d %s %d %.2f원 %.2f원";
		System.out.println(title);
		System.out.printf(fmt,p.getNum(),p.getPname(),p.getAmount(),p.getPrice(),p.getKum());
	}
	
	
	
}

public class TestProduct {

	public static void main(String[] args) {
		
		Product p = new Product();
		p.input();
		p.process();
		p.output();
	}

}
