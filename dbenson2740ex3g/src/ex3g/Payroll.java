package ex3g;
import java.text.DecimalFormat;

public class Payroll {
	private int id;
	private String name;
	private double payRate;
	private double hours;
	private double addHours;
	
	
	
	public Payroll(int id, String name, double payRate) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
	}
	
	public Payroll(int id, String name, double payRate, double hours) {
		super();
		this.id = id;
		this.name = name;
		this.payRate = payRate;
		this.hours = hours;
	}
	
	public int getId() {
		return this.id;
	}
	
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
	public String getName() {
		return this.name;
	}
	public boolean setName(String name) {
		if (name.isEmpty())
			return false;
		else {
			this.name = name;
			return true;
		}
		
	}
	public double getPayRate() {
		return this.payRate;
	}
	public boolean setPayRate(double payRate) {
		if (payRate >= 7.25 && payRate <= 100.0){
			this.payRate = payRate;
			return true;
		}
		else {
			return false;
		}
			
	}
	public double getHours() {
		return this.hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	@Override
	public String toString() {
		DecimalFormat dollarFmt = new DecimalFormat("#,###.00");
		return this.id + " " + name + "," + " Pay Rate=" + dollarFmt.format(payRate);
	}
	
	public double calcGrossPay() {
		
		double grossPay, overtimePay;
		
			   
		if (hours > 40)
		{	
			
			grossPay =  40 * payRate;
			
			
			overtimePay = (hours - 40) * (payRate * 1.5);
			
			
			grossPay += overtimePay;	
		}
		else
		{
			grossPay = payRate * hours;
		}
		
		return grossPay;
	}
	
	public boolean addHours(double hours) {
		if (hours >= 0.1 && hours <= 20.0) {
			this.hours += hours;
			return true;
		}
		else {
			return false;
		}
		
	}

	
/*
	
 */

}
