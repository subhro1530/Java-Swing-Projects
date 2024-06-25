package ChatGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ClassNote extends JFrame implements ActionListener {
	public ClassNote() {
		setTitle("My Chat Application");
		setSize(400,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(d.getWidth()/2),(int)(d.getHeight()/2));
		initComponents();
	}
	
	private void initComponents() {
		JPanel bottomPanel=new JPanel();
		JButton okBtn= new JButton("OK");
		okBtn.addActionListener(this);
//		add(okBtn); // Covers Full Window, adds in the Frame
		bottomPanel.add(okBtn,BorderLayout.NORTH);
		JButton cancelBtn= new JButton("CANCEL");
		bottomPanel.add(cancelBtn,BorderLayout.NORTH);
		cancelBtn.addActionListener(this);
		add(bottomPanel,BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		ClassNote F = new ClassNote();
		F.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd=e.getActionCommand();
		if(cmd.equals("OK")) {
			JOptionPane.showMessageDialog(this, "OK is clicked","Msg",JOptionPane.INFORMATION_MESSAGE);
		}
		if(cmd.equals("CANCEL")) {
			System.exit(ABORT);
		}
		System.out.println(cmd+" is Clicked");
	}
}