
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator implements ActionListener {

    private static JTextField inputBox;

    private static void createWindow() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        userInterface(frame);
        frame.setSize(200, 200);
        frame.setLocationRelativeTo(null); //puts the popup in the center of the screen
        frame.setVisible(true);
    }

    private static void userInterface(JFrame frame) {
        JPanel panel = new JPanel();
        Calculator calculator = new Calculator();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints grid = new GridBagConstraints();
        panel.setLayout(layout);

        inputBox = new JTextField(10);
        inputBox.setEditable(false);

        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");

        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton divide = new JButton("/");
        JButton mult = new JButton("x");
        JButton clear = new JButton("C");
        JButton decimal = new JButton(".");
        JButton equals = new JButton("=");

        button1.addActionListener(calculator);
        button2.addActionListener(calculator);
        button3.addActionListener(calculator);
        button4.addActionListener(calculator);
        button5.addActionListener(calculator);
        button6.addActionListener(calculator);
        button7.addActionListener(calculator);
        button8.addActionListener(calculator);
        button9.addActionListener(calculator);
        button0.addActionListener(calculator);

        plus.addActionListener(calculator);
        minus.addActionListener(calculator);
        divide.addActionListener(calculator);
        mult.addActionListener(calculator);
        clear.addActionListener(calculator);
        decimal.addActionListener(calculator);
        equals.addActionListener(calculator);

        grid.fill = GridBagConstraints.HORIZONTAL;
        grid.gridx = 0;
        grid.gridy = 0;
        panel.add(button1, grid);
        grid.gridx = 1;
        grid.gridy = 0;
        panel.add(button2, grid);
        grid.gridx = 2;
        grid.gridy = 0;
        panel.add(button3, grid);
        grid.gridx = 3;
        grid.gridy = 0;
        panel.add(plus, grid);
        grid.gridx = 0;
        grid.gridy = 1;
        panel.add(button4, grid);
        grid.gridx = 1;
        grid.gridy = 1;
        panel.add(button5, grid);
        grid.gridx = 2;
        grid.gridy = 1;
        panel.add(button6, grid);
        grid.gridx = 3;
        grid.gridy = 1;
        panel.add(minus, grid);
        grid.gridx = 0;
        grid.gridy = 2;
        panel.add(button7, grid);
        grid.gridx = 1;
        grid.gridy = 2;
        panel.add(button8, grid);
        grid.gridx = 2;
        grid.gridy = 2;
        panel.add(button9, grid);
        grid.gridx = 3;
        grid.gridy = 2;
        panel.add(divide, grid);
        grid.gridx = 0;
        grid.gridy = 3;
        panel.add(decimal, grid);
        grid.gridx = 1;
        grid.gridy = 3;
        panel.add(button0, grid);
        grid.gridx = 2;
        grid.gridy = 3;
        panel.add(clear, grid);
        grid.gridx = 3;
        grid.gridy = 3;
        panel.add(mult, grid);
        grid.gridwidth = 3;

        grid.gridx = 0;
        grid.gridy = 4;
        panel.add(inputBox, grid);
        grid.gridx = 3;
        grid.gridy = 4;
        panel.add(equals, grid);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command.charAt(0)) {
            case 'C':
                inputBox.setText("");
                break;
            case '=':
                inputBox.setText(evaluate(inputBox.getText()));
                break;
            default:
                inputBox.setText(inputBox.getText() + command);
                break;
        }
    }

    public static String evaluate(String expression) {
        char[] arr = expression.toCharArray();
        String operand1 = "";
        String operand2 = "";
        String operator = "";
        double result = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= '0' && arr[i] <= '9' || arr[i] == '.') {
                if (operator.isEmpty()) {
                    operand1 += arr[i];
                } else {
                    operand2 += arr[i];
                }
            }

            if (arr[i] == '+' || arr[i] == '-' || arr[i] == '/' || arr[i] == '*') {
                operator += arr[i];
            }
        }

        switch (operator) {
            case "+":
                result = (Double.parseDouble(operand1) + Double.parseDouble(operand2));
                break;
            case "-":
                result = (Double.parseDouble(operand1) - Double.parseDouble(operand2));
                break;
            case "/":
                result = (Double.parseDouble(operand1) / Double.parseDouble(operand2));
                break;
            case "*":
                result = (Double.parseDouble(operand1) * Double.parseDouble(operand2));
                break;
            case "null":
                result = (Double.parseDouble(operand1));
                break;

        }
        return "" + result;
    }

    public static void main(String[] args) {
        createWindow();
    }
}
