import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calc extends JFrame {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton, clearButton;
    private JPanel panel;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;
    public calc() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
        }
        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        equalsButton = new JButton("=");
        clearButton = new JButton("Clear");
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.add(textField);
        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]);
        }
        panel.add(operatorButtons[0]); // +
        panel.add(numberButtons[0]);
        panel.add(operatorButtons[1]); // -
        panel.add(operatorButtons[2]); // *
        panel.add(numberButtons[0]);
        panel.add(operatorButtons[3]); // /
        panel.add(equalsButton);
        panel.add(clearButton);
        add(panel);
        addListeners();
        setVisible(true);
    }

    private void addListeners() {
        for (JButton button : numberButtons) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String currentText = textField.getText();
                    textField.setText(currentText + button.getText());
                }
            });
        }

// Add action listeners for operator buttons
        for (JButton button : operatorButtons) {
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    num1 = Double.parseDouble(textField.getText());
                    operator = button.getText().charAt(0);
                    textField.setText("");
                }
            });
        }
        equalsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                num2 = Double.parseDouble(textField.getText());
                switch (operator) {
                    case '+':
                        result = num1 + num2;
                        break;
                    case '-':
                        result = num1 - num2;
                        break;
                    case '*':
                        result = num1 * num2;
                        break;
                    case '/':
                        if (num2 != 0)
                            result = num1 / num2;
                        else
                            result = Double.POSITIVE_INFINITY;
                        break;
                }
                textField.setText(String.valueOf(result));
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                num1 = 0;
                num2 = 0;
                result = 0;
            }
        });
    }

    public static void main(String[] args) {
        new calc();
    }
}
