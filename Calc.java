import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calc extends JFrame {
    private JTextField textField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton equalsButton, clearButton;
    private JPanel panel;
    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calc() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        //font ha dar internet peyda shode
        Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 20);
        Font clearfont = new Font("Comic Sans MS", Font.BOLD, 12);
        Color backgroundColor = new Color(255, 204, 153);
        Color buttonColor = new Color(255, 153, 102);
        Color textColor = Color.WHITE;

        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(buttonFont);
            numberButtons[i].setBackground(buttonColor);
            numberButtons[i].setForeground(textColor);
        }

        operatorButtons = new JButton[4];
        String[] operators = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            operatorButtons[i] = new JButton(operators[i]);
            operatorButtons[i].setFont(buttonFont);
            operatorButtons[i].setBackground(buttonColor);
            operatorButtons[i].setForeground(textColor);
        }

        equalsButton = new JButton("=");
        equalsButton.setFont(buttonFont);
        equalsButton.setBackground(new Color(0,100,0));
        equalsButton.setForeground(textColor);

        clearButton = new JButton("Clear");
        clearButton.setFont(clearfont);
        clearButton.setBackground(new Color(100,0,0));
        clearButton.setForeground(textColor);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));
        panel.setBackground(backgroundColor); // Set background color
        panel.add(textField);

        for (int i = 1; i <= 9; i++) {
            panel.add(numberButtons[i]);
        }

        panel.add(operatorButtons[0]);
        panel.add(numberButtons[0]);
        panel.add(operatorButtons[1]);
        panel.add(operatorButtons[2]);
        panel.add(numberButtons[0]);
        panel.add(operatorButtons[3]);
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
        new Calc();
    }
}
