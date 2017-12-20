import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class WordInputPane extends JPanel {

	private Core core;
	private JTextField statusTextField;

	public WordInputPane(JFrame frame, Core c, gui test) {
		this.core = c;

		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JTextPane txtpnInputTheWord = new JTextPane();
		txtpnInputTheWord.setText("Input the word you want to check. Input will be considered completed when \"Enter\" is pressed.");
		txtpnInputTheWord.setBackground(UIManager.getColor("ColorChooser.background"));
		txtpnInputTheWord.setEditable(false);
		springLayout.putConstraint(SpringLayout.NORTH, txtpnInputTheWord, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, txtpnInputTheWord, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, txtpnInputTheWord, 43, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, txtpnInputTheWord, 440, SpringLayout.WEST, this);
		add(txtpnInputTheWord);

		JButton btnExit = new JButton("Exit");
		springLayout.putConstraint(SpringLayout.SOUTH, btnExit, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnExit, -29, SpringLayout.EAST, this);
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int choice =JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				if(choice == JOptionPane.YES_OPTION)
					frame.dispose();
			}
		});
		add(btnExit);

		JButton btnPrevious = new JButton("Previous");
		springLayout.putConstraint(SpringLayout.NORTH, btnPrevious, 0, SpringLayout.NORTH, btnExit);
		springLayout.putConstraint(SpringLayout.EAST, btnPrevious, -6, SpringLayout.WEST, btnExit);
		btnPrevious.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				test.TempToPrevious();
			}
		});
		add(btnPrevious);

		JEditorPane inputEditor = new JEditorPane();
		springLayout.putConstraint(SpringLayout.SOUTH, inputEditor, -94, SpringLayout.NORTH, btnExit);
		inputEditor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char input = e.getKeyChar();
				String sInput = Character.toString(e.getKeyChar());
				if(input == '\n') {
					//System.out.println("ENTER PRESSED");
					String out = c.currentStatesInfo();
					boolean isFinal = c.getFlag();
					if(isFinal) {
						out += " IS A FINAL STATE";
					}
					else {
						out += " NOT A FINAL STATE";
					}
					JOptionPane.showMessageDialog(frame, out);
					int choice =JOptionPane.showConfirmDialog(frame, "Do you want to enter a new word?", "Warning", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
					if(choice == JOptionPane.YES_OPTION) {
						test.TempToPrevious();
					}

				}
				else if(input == '\b') {
					//System.out.println("BASKSPACE PRESSED");
					if(inputEditor.getText().length()>0) {
						setCurrentStatesInfo(core.deletion());
					}
				}
				else if(core.getLanguage().contains(sInput)) {
					setCurrentStatesInfo(core.addition(sInput));
				}
				else {
					String info = "Acceptable characters are: " + core.getLanguage().toString();
					JOptionPane.showMessageDialog(frame, info, "Error", JOptionPane.WARNING_MESSAGE);
					e.consume();
				}
			}
		});
		inputEditor.setToolTipText("Input your characters & words here");
		springLayout.putConstraint(SpringLayout.NORTH, inputEditor, 34, SpringLayout.SOUTH, txtpnInputTheWord);
		springLayout.putConstraint(SpringLayout.WEST, inputEditor, 0, SpringLayout.WEST, txtpnInputTheWord);
		springLayout.putConstraint(SpringLayout.EAST, inputEditor, 0, SpringLayout.EAST, txtpnInputTheWord);
		add(inputEditor);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				inputEditor.requestFocus();
			}
		});

		setVisible(true);
		frame.getContentPane().add(this);

		statusTextField = new JTextField();
		statusTextField.setEditable(false);
		statusTextField.setText("Current States: " + core.currentStatesInfo());
		springLayout.putConstraint(SpringLayout.NORTH, statusTextField, 12, SpringLayout.SOUTH, inputEditor);
		springLayout.putConstraint(SpringLayout.WEST, statusTextField, -430, SpringLayout.EAST, txtpnInputTheWord);
		springLayout.putConstraint(SpringLayout.SOUTH, statusTextField, 64, SpringLayout.SOUTH, inputEditor);
		springLayout.putConstraint(SpringLayout.EAST, statusTextField, 0, SpringLayout.EAST, txtpnInputTheWord);
		add(statusTextField);
		statusTextField.setColumns(10);
	}

	public void setCurrentStatesInfo(String s) {
		statusTextField.setText("Current States: " + s);
	}
}


