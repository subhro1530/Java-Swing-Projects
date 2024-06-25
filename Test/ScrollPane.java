package Test;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ScrollPane extends JFrame{
	JLabel label=new JLabel();
	JScrollPane scrollPane=new JScrollPane();
	
	public ScrollPane() {
		label.setIcon(new ImageIcon("D://Downloads//test.jpg"));
		scrollPane.setViewportView(label);
		add(scrollPane);
	}

	public static void main(String[] args) {
		ScrollPane sp=new ScrollPane();
		sp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		sp.setSize(1024,800);
		sp.setVisible(true);
	}
}
