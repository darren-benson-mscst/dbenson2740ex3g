package ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.DefaultListModel;

public class PayrollObjMapper {

	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	public PayrollObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile(){
		boolean fileOpened = false;
		
		 // Open the file. (Copied from Ex3D)
	    try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
			
			if (fileOpened) {
				// Open the file.
			    this.inputFile = new Scanner(file);

			 }
	    }
	    catch (IOException e) {}
		
		return fileOpened;
	}
	
	public boolean openOutputFile(){
		// Copied from Ex3E
		boolean fileOpened = false;
		try{
			this.outputFile = new PrintWriter(fileName);
			fileOpened = true;
		}
		catch (IOException e) {}
		
		return fileOpened;
	}
	
	public void closeInputFile(){
		if (this.inputFile != null) this.inputFile.close();
	}
	
	public void closeOutputFile(){
		
		outputFile.close();
	}
	
	
	public Payroll getNextPayroll(){
		
		Payroll p = null;
		
		String textLine = inputFile.nextLine();
		int id = Integer.parseInt(textLine);
		String name = inputFile.nextLine();
		textLine = inputFile.nextLine();
		double payRate = Double.parseDouble(textLine);
		textLine = inputFile.nextLine();
		double hours = Double.parseDouble(textLine);
		
		p = new Payroll(id, name, payRate, hours);
		
		
		// Cleaner way to approach this. Helps clean up possible errors that we may run into in the file.
//		int id = 0;							
//		String name = "";
//		double payRate = 0.0;
//		double hours = 0.0;
//		
//		try {
//			String textline = inputFile.nextLine();
//			id = Integer.parseInt(textLine);
//			name = inputFile.nextLine();
//			textline = inputFile.nextLine();
//			payRate = Double.parseDouble(textLine);
//			textline = inputFile.nextLine();
//			hours = Double.parseDouble(textLine);
//			p = new Payroll(id, name, payRate, hours);
//			
//		}
//		catch (NoSuchElementException e) { }  
//		catch (NumberFormatException e) { }
		
		
		return p;
	}
	
	public void writePayroll(Payroll payroll){
		if (this.outputFile != null && payroll != null) {
			outputFile.println(payroll.getId());
			outputFile.println(payroll.getName());
			outputFile.println(payroll.getPayRate());
			outputFile.println(payroll.getHours());
		}
	}
	
	
	public DefaultListModel getAllPayroll(){
		
		DefaultListModel payrollCollection = new DefaultListModel();
		
		if (this.openInputFile())
		{
			while (this.inputFile.hasNext()) {
//				Payroll p = this.getNextPayroll();   //cleaner way to approach this
//				if (p != null)
					payrollCollection.addElement(this.getNextPayroll());
			}
		}
		
		this.closeInputFile();
		return payrollCollection;
		
	}
	
	public void writeAllPayroll(DefaultListModel payrollCollection){
		
		//Loop all elements of payrollCollection, call this.writePayroll
		if (this.openOutputFile()) {
			payrollCollection.getSize();
			int counter = 0;
			
			while (counter < payrollCollection.getSize()){
				
				Payroll p = (Payroll) payrollCollection.get(counter);
				this.writePayroll(p);
				counter++;
				
			}

		}
		this.closeOutputFile();
	}
	
}
