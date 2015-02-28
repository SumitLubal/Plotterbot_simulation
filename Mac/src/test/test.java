package test;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class test extends JFrame implements Runnable {
	JTextField x, y;
	MyPanel pan;
	JPanel panel;

	public static void main(String a[]) {
		new test();
	}

	test() {
		x = new JTextField();
		y = new JTextField();
		pan = new MyPanel();
		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		setLayout(new GridLayout(2, 1));
		panel.add(x);
		panel.add(y);
		add(panel);
		add(pan);
		
		setVisible(true);
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				if (x.getText().contains(",") && y.getText().contains(",")) {
					pan.refreshComponents();
					int x1, x2, y1, y2;
					x1 = Integer.parseInt(x.getText().split(",")[0]);
					y1 = Integer.parseInt(x.getText().split(",")[1]);
					x2 = Integer.parseInt(y.getText().split(",")[0]);
					y2 = Integer.parseInt(y.getText().split(",")[1]);
					pan.drawLine(x1, x2, y1, y2);
					//pan.gotoxy(x1, y1);
					//Thread.sleep();
				}
				Thread.sleep(60);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
