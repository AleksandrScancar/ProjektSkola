import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class MainApp {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp window = new MainApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 413);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JTextArea textArea = new JTextArea();
		JLabel lblKodovaka = new JLabel("Kodova\u010Dka");
		lblKodovaka.setFont(new Font("Arial Black", Font.PLAIN, 23));
		FileDialog fd = new FileDialog(new JFrame(), "Choose a file", FileDialog.LOAD);
		
		JButton btnVyberSouboru = new JButton("Nahrat soubor");
		btnVyberSouboru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fd.setDirectory("C:\\");
				fd.setBounds(100, 100, 590, 413);
				fd.setTitle("Vyber soubor");
				fd.setName(".txt");
				fd.setVisible(true);
			}
		});
		
		JButton btnVymenSoubor = new JButton("Zakoduj");
		btnVymenSoubor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename = fd.getDirectory()+fd.getFile();
				try {
					File  f = XOR.encrypt(filename, textArea.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(215)
					.addComponent(lblKodovaka, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.addGap(215))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(216)
					.addComponent(btnVymenSoubor, GroupLayout.PREFERRED_SIZE, 137, Short.MAX_VALUE)
					.addGap(216))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(218)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
						.addComponent(btnVyberSouboru, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(218))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblKodovaka)
					.addGap(38)
					.addComponent(btnVyberSouboru, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
					.addGap(42)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnVymenSoubor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(91))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
