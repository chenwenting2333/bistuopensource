import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
class Loan {
	private double annualInterestRate;
	private int numberOfYears;
	private double loanAmount;
	
	
	public Loan(){}
	
	public Loan(double annualInterestRate,int numberOfYears, double loanAmount){
		this.annualInterestRate = annualInterestRate;
		this.numberOfYears = numberOfYears;
		this.loanAmount = loanAmount;
	}

	public double getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(double annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public int getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYears(int numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	
	public double getMonthlyPayment(){
		double monthlyInterestRate = annualInterestRate / 1200;
		return loanAmount * monthlyInterestRate/(1-Math.pow(1/(1+monthlyInterestRate), numberOfYears * 12));
	}
	
	public double getTotalPayment(){
		return getMonthlyPayment() * numberOfYears * 12;
	}
}
public class LoanCalculator extends Application{

	    private TextField tfAnnualInterestRate = new TextField();
	    private TextField tfNumberOfYears = new TextField();
	    private TextField tfLoanAmount = new TextField();
	    private TextField tfMonthPayment = new TextField();
	    private TextField tfTotalPayment = new TextField();
	   
		public void start(Stage primaryStage){
			GridPane pane = new GridPane();
			pane.setPadding(new Insets(11));
			pane.setHgap(5);
			pane.setVgap(5);
			
			pane.add(new Label("年利率"),0, 0);
			pane.add(tfAnnualInterestRate, 1, 0);
			pane.add(new Label("贷款年限"),0, 1);
			pane.add(tfNumberOfYears, 1, 1);
			pane.add(new Label("贷款总额"), 0, 2);
			pane.add(tfLoanAmount, 1, 2);
			pane.add(new Label("月还款额"), 0, 3);
			pane.add(tfMonthPayment, 1, 3);
			pane.add(new Label("总还款额"), 0, 4);
			pane.add(tfTotalPayment, 1, 4);
			
			Button btAdd = new Button("计算");
			GridPane.setHalignment(btAdd, HPos.RIGHT);
			btAdd.setOnAction(e->calculateLoanPayment());
			pane.add(btAdd, 1, 5);
					
			Scene scene = new Scene(pane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("贷款计算器");
			primaryStage.show();
		}
		
		public void calculateLoanPayment(){

double interest = Double.parseDouble(tfAnnualInterestRate.getText());
			int year = Integer.parseInt(tfNumberOfYears.getText());
			double totalAmount = Double.parseDouble(tfLoanAmount.getText());
			

			Loan loan = new Loan(interest, year, totalAmount);


			tfMonthPayment.setText("" + loan.getMonthlyPayment());
			tfTotalPayment.setText("" + loan.getTotalPayment());
		}
		
		public static void main(String[] a){
			Application.launch(a);
		}
}