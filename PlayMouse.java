package com.tanyusheng.Mouse;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class PlayMouse extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	private int num = 0;
	private JLabel back;
	private JLabel[] mouses;
	private ImageIcon imgMouse;
	private JLabel jtf;

	public PlayMouse() {
		this.setResizable(false);// 不能修改大小
		this.getContentPane().setLayout(null);
		this.setTitle("打地鼠游戏");
		this.setBounds(300, 100, 600, 438);
		back = new JLabel();
		ImageIcon icon = new ImageIcon(this.getClass().getResource("3.png"));
		back.setIcon(icon);
		back.setBounds(0, -35, 600, 438);
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().getImage("src/com/tanyusheng/Mouse/1.png"), new Point(), "self"));
		imgMouse = new ImageIcon(this.getClass().getResource("2.png"));
		mouses = new JLabel[9];
		for (int i = 0; i < 9; i++) {
			mouses[i] = new JLabel();
			mouses[i].setSize(imgMouse.getIconWidth(), imgMouse.getIconHeight());
			//mouses[i].setIcon(imgMouse);
			mouses[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) { // 加分功能
					Object object = e.getSource();
					if (object instanceof JLabel) {
						JLabel label = (JLabel) object;
						if (label.getIcon() != null) {
							num++;
							jtf.setText("谈玉胜您的得分是：" + num + "分");
						}
						label.setIcon(null);
					}

				}

			});
			this.getContentPane().add(mouses[i]);

		}
		mouses[0].setLocation(-130, -15);
		mouses[1].setLocation(80, -15);
		mouses[2].setLocation(300, -15);
		mouses[3].setLocation(-130, -120);
		mouses[4].setLocation(80, -120);
		mouses[5].setLocation(300, -120);
		mouses[6].setLocation(-130, -225);
		mouses[7].setLocation(80, -225);
		mouses[8].setLocation(300, -225);
		jtf = new JLabel();
		jtf.setBounds(360, 10, 230, 50);
		jtf.setFont(new Font("", 20, 20));
		jtf.setForeground(Color.green);
		jtf.setText("谈玉胜您的得分是：  分");
		this.getContentPane().add(jtf);
		this.getContentPane().add(back);
		this.setVisible(true);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(500);
				int index = (int) (Math.random() * 9);
				if (mouses[index].getIcon() == null) {
					mouses[index].setIcon(imgMouse);
					Thread.sleep(1000);// 反应时间，可以根据难度的不同设置时间
					if (mouses[index].isShowing()) {
						mouses[index].setIcon(null);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		PlayMouse p1 = new PlayMouse();
		Thread t1 = new Thread(p1);
		t1.start();
	}

}
