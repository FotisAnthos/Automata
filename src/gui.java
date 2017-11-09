import javax.swing.*;
import java.awt.*;




public class gui {

	private JFrame frame;

	public gui() {
		// Set System L&F
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Automata - it14112");
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		frame.setResizable(false);
		frame.getContentPane().add(new inputPanel(frame));
		frame.setVisible(true);
	}

	private void inputPanel() {
		//TODO
	}
}
