import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// basic calculator using swing
public class CalculatorApp extends JFrame implements ActionListener {

    private JTextField display;  // to show numbers and result
    private double num1, num2;   // stores the two numbers
    private String operator;     // stores the operator (+, -, etc.)

    public CalculatorApp() {
        // setting up the main window
        setTitle("calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // display at the top
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);  // user can’t type directly
        add(display, BorderLayout.NORTH);

        // create buttons
        JPanel panel = new JPanel(new GridLayout(4, 4));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // add buttons to the panel
        for (String b : buttons) {
            JButton btn = new JButton(b);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);  // button will react when clicked
            panel.add(btn);
        }

        // add panel to center of the window
        add(panel, BorderLayout.CENTER);
        setVisible(true);  // show the window
    }

    // this runs when a button is clicked
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();  // get the text on the button

        if (cmd.matches("[0-9]")) {
            // if it's a number, add it to the display
            display.setText(display.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            // if it's an operator, save the first number and the operator
            num1 = Double.parseDouble(display.getText());
            operator = cmd;
            display.setText("");
        } else if (cmd.equals("=")) {
            // when equals is clicked, do the math
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": num1 += num2; break;
                case "-": num1 -= num2; break;
                case "*": num1 *= num2; break;
                case "/": num1 = (num2 == 0) ? 0 : num1 / num2; break;  // avoid divide by 0
            }
            display.setText(String.valueOf(num1));
        } else if (cmd.equals("C")) {
            // clear everything
            display.setText("");
            num1 = num2 = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        // start the calculator
        new CalculatorApp();
    }
}
