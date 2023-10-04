import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MortgageCalculator extends JFrame {

    JTextField loanAmountField, interestRateField, loanPeriodField, monthlyPaymentField;
    JButton calculateButton;
    JLabel errorLabel, titleLabel;

    public MortgageCalculator() {
        // Set up frame
        this.setTitle("Mortgage Calculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 350);
        this.setLayout(new BorderLayout());

        // Container JPanel with GridBagLayout to hold our components
        JPanel gridPanel = new JPanel(new GridBagLayout());
        gridPanel.setBackground(new Color(204, 229, 255)); // Light blue background

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // top, left, bottom, right padding

        // Setup components with units
        loanAmountField = new JTextField("Loan Amount (USD)", 20);
        interestRateField = new JTextField("Interest Rate (%)", 20);
        loanPeriodField = new JTextField("Loan Period (Years)", 20);
        monthlyPaymentField = new JTextField("Monthly Payment (USD)", 20);
        calculateButton = new JButton("Calculate");
        titleLabel = new JLabel("Mortgage Calculator", SwingConstants.CENTER);
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED); // Set error message color

        // Enhance UI aesthetics
        loanAmountField.setFont(new Font("Arial", Font.PLAIN, 16));
        interestRateField.setFont(new Font("Arial", Font.PLAIN, 16));
        loanPeriodField.setFont(new Font("Arial", Font.PLAIN, 16));
        monthlyPaymentField.setFont(new Font("Arial", Font.PLAIN, 16));
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26)); // Increased size and boldness
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        // Set action listener for calculate button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double loanAmount = Double.parseDouble(loanAmountField.getText().replaceAll("[^0-9.]", ""));
                    double interestRate = Double.parseDouble(interestRateField.getText().replaceAll("[^0-9.]", ""))
                            / 100 / 12;
                    int loanPeriod = Integer.parseInt(loanPeriodField.getText().replaceAll("[^0-9]", "")) * 12;

                    if (loanAmount <= 0 || interestRate <= 0 || loanPeriod <= 0) {
                        throw new NumberFormatException(); // Throw exception if numbers are not valid
                    }

                    double monthlyPayment = (loanAmount * interestRate) /
                            (1 - Math.pow(1 + interestRate, -loanPeriod));
                    monthlyPaymentField.setText(String.format("USD %.2f", monthlyPayment));
                    errorLabel.setText(""); // Clear error message upon successful calculation
                } catch (NumberFormatException exception) {
                    errorLabel.setText("Please enter valid positive numbers!"); // Error message for invalid input
                }
            }
        });

        // Add components to gridPanel
        gridPanel.add(titleLabel, gbc);
        gridPanel.add(loanAmountField, gbc);
        gridPanel.add(interestRateField, gbc);
        gridPanel.add(loanPeriodField, gbc);
        gridPanel.add(monthlyPaymentField, gbc);
        gridPanel.add(calculateButton, gbc);

        // Add gridPanel and errorLabel to frame
        this.add(gridPanel, BorderLayout.CENTER);
        this.add(errorLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        MortgageCalculator calculator = new MortgageCalculator();
        calculator.setVisible(true);
    }
}
