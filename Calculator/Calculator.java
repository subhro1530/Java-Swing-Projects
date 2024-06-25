package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private JPanel panel;
    private String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", ".", "=", "+" 
    };

    private Color blackColor = Color.BLACK;
    private Color violetColor = new Color(138, 43, 226); // Violet color
    private Color whiteColor = Color.WHITE;

    public Calculator() {
        // Frame settings
        setTitle("Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(blackColor);
        setLayout(new BorderLayout());
        
        // Display
        display = new JTextField();
        display.setPreferredSize(new Dimension(400, 100));
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(whiteColor);
        display.setForeground(blackColor);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);
        
        // Panel for buttons
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(blackColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Spacing between buttons
        gbc.fill = GridBagConstraints.BOTH;
        
        // Add buttons to panel
        int gridX = 0;
        int gridY = 0;
        for (String text : buttons) {
            CustomButton button = new CustomButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 30));
            button.setBackground(violetColor);
            button.setForeground(whiteColor);
            button.addActionListener(this);
            gbc.gridx = gridX;
            gbc.gridy = gridY;
            panel.add(button, gbc);
            gridX++;
            if (gridX > 3) {
                gridX = 0;
                gridY++;
            }
        }
        
        add(panel, BorderLayout.CENTER);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ("0123456789.".indexOf(command) >= 0) {
            display.setText(display.getText() + command);
        } else if (command.equals("=")) {
            try {
                display.setText(evaluate(display.getText()));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else {
            display.setText(display.getText() + " " + command + " ");
        }
    }

    private String evaluate(String expression) {
        // Evaluate the expression
        String[] tokens = expression.split(" ");
        if (tokens.length != 3) return "Error";
        
        double num1 = Double.parseDouble(tokens[0]);
        double num2 = Double.parseDouble(tokens[2]);
        String operator = tokens[1];
        
        switch (operator) {
            case "+":
                return String.valueOf(num1 + num2);
            case "-":
                return String.valueOf(num1 - num2);
            case "*":
                return String.valueOf(num1 * num2);
            case "/":
                if (num2 == 0) return "Error";
                return String.valueOf(num1 / num2);
            default:
                return "Error";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

class CustomButton extends JButton {
    public CustomButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 30, 30));
        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        // No border painting
    }

    @Override
    public void updateUI() {
        super.updateUI();
        setContentAreaFilled(false);
    }
}
