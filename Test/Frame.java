package Test;
import javax.swing.JFrame;

public class Frame {
	public static void main(String[] args) {
		JFrame frame=new JFrame("JFrame Demo");
		Panel panel=new Panel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Garbage collection
		frame.setSize(320,240);
		frame.setVisible(true);
	}
}
