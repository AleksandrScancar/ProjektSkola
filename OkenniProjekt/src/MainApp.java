import java.awt.EventQueue;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
/**
 * <h1>Main</h1>
 * @author aleksandr scancar
 * <p>Aplikace vytvoøena zapomocí WindowBuilder<p>
 */
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
					JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Chyba",
							   JOptionPane.ERROR_MESSAGE);
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
		frame.setTitle("Kodova\u010Dka");
		frame.setResizable(false);
		frame.setBounds(100, 100, 724, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/**
		 * Button pro ulozeni souboru
		 */
		JButton btnVymenSoubor = new JButton("Zakoduj");
		btnVymenSoubor.setFont(new Font("Tahoma", Font.PLAIN, 22));
		/**
		 * TextArea podle ktere se ma kodovat soubor
		 */
		JTextArea textArea = new JTextArea();
		textArea.setTabSize(2);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 19));
		/**
		 * Nadpis v aplikaci-text
		 */
		JLabel lblKodovacka = new JLabel("Kodova\u010Dka");
		lblKodovacka.setHorizontalAlignment(SwingConstants.CENTER);
		lblKodovacka.setFont(new Font("Arial Black", Font.PLAIN, 27));
		/**
		 * Vyber souboru dialog
		 */
		FileDialog fd = new FileDialog(frame, "Choose a file", FileDialog.LOAD);
		/**
		 * Button pro otevreni dialogu s vyberem souboru
		 */
		JButton btnVyberSouboru = new JButton("Nahrat soubor");
		btnVyberSouboru.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnVyberSouboru.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fd.setDirectory("C:\\");
				fd.setBounds(100, 100, 590, 413);
				fd.setTitle("Vyber soubor");
				fd.setFile("*.txt");
				fd.setVisible(true);
			}
		});
		btnVymenSoubor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filename = fd.getDirectory()+fd.getFile();
				FileDialog fileDialog = new FileDialog(frame, "Ulož zakodovaný", FileDialog.SAVE);
					try {
						switch (1) {
						case 1:  
							fileDialog.setFile("*.txt");
					        fileDialog.setVisible(true);
						case 2:
							if(fileDialog.getFile().endsWith(".txt")) {	
								XOR.encrypt(filename, textArea.getText(), fileDialog.getDirectory()+fileDialog.getFile());
							}else {
								XOR.encrypt(filename, textArea.getText(), fileDialog.getDirectory()+fileDialog.getFile()+".txt");
							}
				        break;
						}
					}catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "Nezadal si žádný klíè nebo nevybral soubor", "Chyba",
								JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		/**
		 * Layout/rozlozeni prvku v aplikaci 
		 * nasledne pridani
		 */
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(215)
					.addComponent(lblKodovacka, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
					.addGap(215))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(216)
					.addComponent(btnVymenSoubor, GroupLayout.PREFERRED_SIZE, 286, Short.MAX_VALUE)
					.addGap(216))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(218)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, Alignment.LEADING)
						.addComponent(btnVyberSouboru, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
					.addGap(218))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(28)
					.addComponent(lblKodovacka)
					.addGap(38)
					.addComponent(btnVyberSouboru, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(32)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addComponent(btnVymenSoubor, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
					.addGap(91))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
