import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class WordInputPane extends JPanel {

	private JFrame frame;
	private gui test;
	private Core core;
	
	public WordInputPane(JFrame frame, Core c, gui test) {
		
		this.test = test;
		this.frame = frame;
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
		btnPrevious.addMouseListener(new MouseAdapter() {//TODO sent to frame a signal to move back
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//TODO go back to input pane
				test.TempToPrevious();//TODO remove this and "gui test" from this class
			}
		});
		add(btnPrevious);

		JEditorPane inputEditor = new JEditorPane();
		inputEditor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {//TODO 
				char input = e.getKeyChar();
				String sInput = Character.toString(e.getKeyChar());
				if(input == '\n') {
					//TODO give the sum up and another chance at entering new input
					System.out.println("ENTER PRESSED");
				}
				else if(input == '\b') {
					core.deletion();
					System.out.println("BACKSPACE PRESSED");
				}
				else if(core.getLanguage().contains(sInput)) {
					core.addition(sInput);
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
		springLayout.putConstraint(SpringLayout.SOUTH, inputEditor, -28, SpringLayout.NORTH, btnExit);
		springLayout.putConstraint(SpringLayout.EAST, inputEditor, 0, SpringLayout.EAST, txtpnInputTheWord);
		add(inputEditor);
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		    	  inputEditor.requestFocus();
		      }
		    });
		
		setVisible(true);
		frame.getContentPane().add(this);
	}
	
	
	
	

	
	

	
	
}
