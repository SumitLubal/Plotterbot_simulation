package test;

import java.awt.Graphics;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements Runnable {
	double actualRadiusOfA = 10, actualRadiusOfB = 10;
	double maxXLen, maxYLen;

	enum type {
		left, right
	}

	double radius = 2; // cm
	double stepPerRotation = 200;
	double perimeter;
	double perStepDistance;
	private double stepAIncreament;
	private double stepBIncreament;
	private double increamentLenA;
	private double increamentLenB;

	public double lenthToStep(double lenth) {
		return lenth / perStepDistance;
	}

	public void drawLine(int x1, int x2, int y1, int y2) {
		double stepA, stepB;
		gotoxy(x1, y1);
		double initiala = getStringLenght(x1, y1, type.left);
		double initialb = getStringLenght(x1, y1, type.right);
		double finala = getStringLenght(x2, y2, type.left);
		double finalb = getStringLenght(x2, y2, type.right);
		double deltaA = Math.abs(initiala - finala);
		double deltaB = Math.abs(initialb - finalb);
		int counter = 0;
		if (deltaA > deltaB) {
			stepAIncreament = lenthToStep(deltaA) / lenthToStep(deltaA);
			stepBIncreament = lenthToStep(deltaB) / lenthToStep(deltaA);
			counter = (int) deltaA;
			increamentLenA = deltaA / deltaA;
			increamentLenB = deltaB / deltaA;
			System.out.println("delta A is greater");
		} else {
			stepBIncreament = lenthToStep(deltaB) / lenthToStep(deltaB);
			stepAIncreament = lenthToStep(deltaA) / lenthToStep(deltaB);
			counter = (int) deltaB;

			increamentLenB = deltaB / deltaB;
			increamentLenA = deltaA / deltaB;
			System.out.println("delta B is greater");

		}
		System.out.println("deltaA " + deltaA + " deltaB " + deltaB);
		System.out.println("Factorchange for A " + increamentLenA + " for B "
				+ increamentLenB);
		System.out.println("radius for A before " + initiala + " after "
				+ finala);
		System.out.println("radius for B before " + initialb + " after "
				+ finalb);
		int directionA = 0, directionB = 0;
		if (finala > initiala) {
			directionA = 1;
			System.out.println("increasing A");
		} else if (finala < initiala) {
			directionA = -1;
			System.out.println("deacreasing A");
		}
		if (finalb > initialb) {
			directionB = 1;
			System.out.println("increasing b");
		} else if (finalb < initialb) {
			directionB = -1;
			System.out.println("decreasing b");
		}
		for (int i = 0; i < counter; i++) {
			actualRadiusOfA =  (actualRadiusOfA + (directionA * increamentLenA));
			actualRadiusOfB =  (actualRadiusOfB + (directionB * increamentLenB));
			try {
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("After processing radiusA = " + actualRadiusOfA
				+ " radiusB =" + actualRadiusOfB);

	}

	private double getStringLenght(double x, double y, type t) {
		double distance = 0;
		if (t == type.left) {
			distance = Math.sqrt((x * x) + (y * y));
			System.out.println("got left" + distance + " x=" + x + " y = " + y
					+ " type=" + t);
		} else {
			double tmp = maxXLen - x;
			distance = Math.sqrt((tmp * tmp) + (y * y));
			System.out.println("got right" + distance + " x=" + tmp + " y = "
					+ y + " type=" + t);
		}
		return distance;
	}

	public void gotoxy(int x, int y) {
		actualRadiusOfA = (int) getStringLenght(x, y, type.left);
		actualRadiusOfB = (int) getStringLenght(x, y, type.right);
		System.out.println("Jumping radius to" + actualRadiusOfA + " & "
				+ actualRadiusOfB);
	}

	public MyPanel() {
		// TODO Auto-generated constructor stub
		perimeter = (2 * Math.PI * radius);
		perStepDistance = perimeter / stepPerRotation;
		maxXLen = getWidth();
		maxYLen = getHeight();
		new Thread(this).start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				repaint();
				Thread.sleep(60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		drawCircle(g, 0, 0, (int) actualRadiusOfA);
		drawCircle(g, getWidth(), 0, (int) actualRadiusOfB);
	}

	private void drawCircle(Graphics g, int x, int y, int radius) {
		g.drawOval(x - radius, y - radius, radius * 2, radius * 2);
	}

	public void refreshComponents() {
		maxXLen = getWidth();
		maxYLen = getHeight();
	}
}
