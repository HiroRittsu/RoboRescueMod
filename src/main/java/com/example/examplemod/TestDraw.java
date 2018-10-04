package com.example.examplemod;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestDraw {

	JPanel panel;
	JFrame frame;

	public TestDraw(Polygon polygon) {

		this.frame = new JFrame("Demo");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.panel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;

				g2.setPaint(Color.LIGHT_GRAY);
				g2.draw(polygon);

			}
		};
	}

	public void startDraw() {

		this.frame.add(panel);
		this.frame.pack();
		this.frame.setSize(320, 160);
		this.frame.setVisible(true);

	}

}
