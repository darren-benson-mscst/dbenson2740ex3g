package ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {
	
	private Payroll employee; 

	private JPanel contentPane;
	private JList listPayroll;
	private JTextField hoursTextField;
	private JLabel lblTotalHours;
	private JLabel lblGrossPay;
	private DefaultListModel listPayrollModel;
	private JTextField empIDTextField;
	private JTextField empNameTextField;
	private JTextField payRateTextField;
	private JButton updateButton;
	private JButton addHoursButton;
	private JButton clearHoursButton;
	private PayrollObjMapper payrollObjMapper;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				do_this_windowClosing(arg0);
			}
		});
		setTitle("Dbenson 2740 Ex3G Payroll & Overtime");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSelectEmployee = new JLabel("Select Employee:");
		lblSelectEmployee.setBounds(32, 11, 96, 14);
		contentPane.add(lblSelectEmployee);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(33, 36, 350, 109);
		contentPane.add(scrollPane);
		
//		listPayrollModel = new DefaultListModel();
//		listPayrollModel.addElement(new Payroll(101, "Mark Swanson", 10.0));
//		listPayrollModel.addElement(new Payroll(102, "Patti Weigand", 20.0));
//		listPayrollModel.addElement(new Payroll(103, "Lyle Stelter", 30.0));
//		listPayrollModel.addElement(new Payroll(104, "Neva Burdick", 40.0));
//		listPayrollModel.addElement(new Payroll(105, "Lisa Laing", 50.0));
		payrollObjMapper = new PayrollObjMapper("exercise3g.txt");
		listPayrollModel = payrollObjMapper.getAllPayroll();
		
		listPayroll = new JList(listPayrollModel);
		listPayroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				do_listPayroll_mouseClicked(e);
			}
		});
		listPayroll.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listPayroll);
		
		JLabel lblEmployeeId = new JLabel("Employee ID(>100):");
		lblEmployeeId.setBounds(32, 165, 119, 14);
		contentPane.add(lblEmployeeId);
		
		JLabel lblEmployeeName = new JLabel("Employee Name:");
		lblEmployeeName.setBounds(32, 190, 96, 14);
		contentPane.add(lblEmployeeName);
		
		JLabel lblPayRate = new JLabel("Pay Rate (7.25 - 100):");
		lblPayRate.setBounds(32, 216, 119, 14);
		contentPane.add(lblPayRate);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (0.1 - 20.0):");
		lblEnterHours.setBounds(32, 244, 136, 14);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				do_hoursTextField_focusGained(arg0);
			}
		});
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setText("0.00");
		hoursTextField.setBounds(198, 238, 90, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		JLabel labelTotalHours = new JLabel("Total Hours:");
		labelTotalHours.setBounds(32, 269, 64, 14);
		contentPane.add(labelTotalHours);
		
		lblTotalHours = new JLabel("0.00");
		lblTotalHours.setBounds(260, 269, 89, 14);
		contentPane.add(lblTotalHours);
		
		JLabel labelGrossPay = new JLabel("Gross Pay:");
		labelGrossPay.setBounds(32, 294, 64, 14);
		contentPane.add(labelGrossPay);
		
		lblGrossPay = new JLabel("$0.00");
		lblGrossPay.setBounds(253, 294, 80, 14);
		contentPane.add(lblGrossPay);
		
		JButton btnClearForm = new JButton("Clear Form");
		btnClearForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_btnClearForm_actionPerformed(arg0);
			}
		});
		btnClearForm.setBounds(294, 337, 89, 23);
		contentPane.add(btnClearForm);
		
		addHoursButton = new JButton("+");
		addHoursButton.setEnabled(false);
		addHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_addHoursButton_actionPerformed(arg0);
			}
		});
		addHoursButton.setBounds(298, 235, 46, 23);
		contentPane.add(addHoursButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_clearHoursButton_actionPerformed(arg0);
			}
		});
		clearHoursButton.setBounds(354, 235, 64, 23);
		contentPane.add(clearHoursButton);
		
		updateButton = new JButton("Update");
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				do_updateButton_actionPerformed(arg0);
			}
		});
		updateButton.setBounds(180, 337, 89, 23);
		contentPane.add(updateButton);
		
		empIDTextField = new JTextField();
		empIDTextField.setText("000");
		empIDTextField.setBounds(198, 162, 89, 20);
		contentPane.add(empIDTextField);
		empIDTextField.setColumns(10);
		
		empNameTextField = new JTextField();
		empNameTextField.setBounds(138, 187, 149, 20);
		contentPane.add(empNameTextField);
		empNameTextField.setColumns(10);
		
		payRateTextField = new JTextField();
		payRateTextField.setBounds(198, 213, 89, 20);
		contentPane.add(payRateTextField);
		payRateTextField.setColumns(10);
	}
	protected void do_listPayroll_mouseClicked(MouseEvent e) {
		Payroll empName = (Payroll) listPayroll.getSelectedValue();
		this.empIDTextField.setText(Integer.toString(empName.getId()));
		this.empNameTextField.setText(empName.getName());
		DecimalFormat dollarFmt1 = new DecimalFormat("#,###.00");
		this.payRateTextField.setText(dollarFmt1.format(empName.getPayRate()));
		DecimalFormat dollarFmt3 = new DecimalFormat("###0.00");
		this.lblTotalHours.setText(dollarFmt3.format(empName.getHours()));
		DecimalFormat dollarFmt4 = new DecimalFormat("$#,##0.00");
		this.lblGrossPay.setText(dollarFmt4.format(empName.calcGrossPay()));
		this.addHoursButton.setEnabled(true);
		this.updateButton.setEnabled(true);
		this.clearHoursButton.setEnabled(true);
	}
	protected void do_addHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll employee = (Payroll) listPayroll.getSelectedValue();
		double workHours = Double.parseDouble(hoursTextField.getText());
		if (employee.addHours(workHours)) {
			DecimalFormat dollarFmt = new DecimalFormat("###0.00");
			this.lblTotalHours.setText(dollarFmt.format(employee.getHours()));
			DecimalFormat dollarFmt2 = new DecimalFormat("$#,##0.00");
			this.lblGrossPay.setText(dollarFmt2.format(employee.calcGrossPay()));
			hoursTextField.setText("0.00");
		}
		else {
			JOptionPane.showMessageDialog(null, "Your hours are incorrect. \nMust be between 0.1 and 20.0");
		}
		this.hoursTextField.requestFocus();
		
	}
	protected void do_clearHoursButton_actionPerformed(ActionEvent arg0) {
		Payroll employee = (Payroll) listPayroll.getSelectedValue();
		employee.setHours(0);
		lblTotalHours.setText("0.00");
		lblGrossPay.setText("$0.00");
		hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
	}
	protected void do_btnClearForm_actionPerformed(ActionEvent arg0) {
		empIDTextField.setText("0");
		empNameTextField.setText("");
		payRateTextField.setText("$0.00");
		lblTotalHours.setText("0.00");
		lblGrossPay.setText("$0.00");
		hoursTextField.setText("0.00");
		this.hoursTextField.requestFocus();
		this.listPayroll.clearSelection();
		this.addHoursButton.setEnabled(false);
		this.updateButton.setEnabled(false);
		this.clearHoursButton.setEnabled(false);
	}
	
	protected void do_hoursTextField_focusGained(FocusEvent arg0) {
		hoursTextField.selectAll();
	}
	protected void do_updateButton_actionPerformed(ActionEvent arg0) {
		int id = Integer.parseInt(empIDTextField.getText());
		double payRate = Double.parseDouble(payRateTextField.getText());
		String name = empNameTextField.getText();
		Payroll employee = (Payroll) listPayroll.getSelectedValue();
		employee.setId(id);
		employee.setName(name);
		employee.setPayRate(payRate);
		if (!employee.setId(id)) {
			JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
			empIDTextField.setText("101");
			empIDTextField.requestFocus();
		}
		else if (!employee.setName(name)) {
				JOptionPane.showMessageDialog(null, "Employee name missing. \nPlease enter a name");
				empNameTextField.setText(employee.getName());
				empNameTextField.requestFocus();
		}
		else if (!employee.setPayRate(payRate)) {
				JOptionPane.showMessageDialog(null, "Invalid pay rate. \nMust be between 7.25 and 100.00");
				DecimalFormat rateFmt = new DecimalFormat("##0.00");
				payRateTextField.setText(rateFmt.format(employee.getPayRate()));
				payRateTextField.requestFocus();
		}
		else {
			employee.setId(id);
			employee.setPayRate(payRate);
			employee.setName(name);
		}
		
		listPayroll.repaint();
			
	}
	protected void do_this_windowClosing(WindowEvent arg0) {
		if (payrollObjMapper != null)
			payrollObjMapper.writeAllPayroll(listPayrollModel);
	}
}
