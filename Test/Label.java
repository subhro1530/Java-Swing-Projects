package Test;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Label {
	public static void main(String[] args) {
		JFrame frame=new JFrame("JLabel demo");
		JLabel label=new JLabel();
		label.setText("Hello Everyone!");
		label.setFont(new Font("Consolas",Font.PLAIN,24));
		frame.add(label);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
