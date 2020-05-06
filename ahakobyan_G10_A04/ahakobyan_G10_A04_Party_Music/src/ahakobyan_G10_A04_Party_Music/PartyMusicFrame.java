package ahakobyan_G10_A04_Party_Music;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Description: This progam displays a number of songs from a specific text (.txt) file. 
 * @author Arsen Hakobyan
 * Course Number: 420-G10
 * Assignment: 4 (final)
 * Date: 30 December 2019
 * */
public class PartyMusicFrame extends JFrame implements ActionListener {
	
	PartyTape partyTape = new PartyTape();
	File file;
	Scanner fileScanner;
	
	// Tape Length
	private JLabel lblLength = new JLabel("Enter length of tape in minutes: ");
	private JTextField lengthField = new JTextField(10);
	
	// File
	private JLabel lblFile = new JLabel("File name: ");
	private JTextField fileField = new JTextField(10);
		
	// Record Button
	private JButton btnRec = new JButton("Record now!");
	
	// Panels
	private JPanel inputPanel = new JPanel();
	private JPanel btnPanel = new JPanel();
	
	// Display Area
	private JTextArea resultDisplay = new JTextArea(34, 650);
	
	public PartyMusicFrame() {
		setTitle("Music Recorder");
		inputPanel.add(lblLength);
		inputPanel.add(lengthField);
		inputPanel.add(lblFile);
		inputPanel.add(fileField);
		btnPanel.add(btnRec);
		resultDisplay.setEditable(false);
		resultDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
		getContentPane().add(inputPanel,BorderLayout.NORTH);
		getContentPane().add(btnPanel,BorderLayout.CENTER);
		getContentPane().add(resultDisplay,BorderLayout.SOUTH);
		btnRec.addActionListener(this);
	}// PartyMusicFrame()
	
	public void actionPerformed(ActionEvent e) {
		int tapeLength = 0;
		String	fileName = "";
		
		if (lengthField.getText().isEmpty()) { // if there's no tape length
			// Display an error message 
			JOptionPane.showMessageDialog(resultDisplay, "Please enter the length of tape.", "No length", JOptionPane.ERROR_MESSAGE);
		}
		
		else if (fileField.getText().isEmpty()) { // if the file field is empty
			// Display a warning
			JOptionPane.showMessageDialog(resultDisplay, "File name is being set to \"tape.txt\"", "No file name", JOptionPane.INFORMATION_MESSAGE);
			// Set the file to tape.txt
			fileName = "tape.txt";
			file = new File(fileName);
			// Get the length field
			tapeLength = Integer.parseInt(lengthField.getText());
			
			openFile(file, tapeLength);
		}
		
		else if (fileField.getText().contains(".txt") == false) { // if the file name has no .txt extension 
			// Concatenate a .txt to the name and set the file name
			fileName = fileField.getText().trim() + ".txt";
			file = new File(fileName);
			// Get the length field
			tapeLength = Integer.parseInt(lengthField.getText());
			
			openFile(file, tapeLength);
		}
		
		else {
			// Get the length field
			tapeLength = Integer.parseInt(lengthField.getText());
			// Get file name and instantiate a file object
			fileName = fileField.getText();
			file = new File(fileName);
			
			openFile(file, tapeLength);
		}
	}// actionPerformed()
	public void openFile(File fileName,int tapeLen) {
	
		try 
		{
			fileScanner = new Scanner(fileName);
			String collection = fileScanner.nextLine();
			partyTape = new PartyTape(collection, tapeLen);
                           			
			fileScanner.useDelimiter("~|\r?\n");
			
			String songName;
			int minutes;
			int seconds;
			
			resultDisplay.setText(String.format("%50s%n", collection));			        
			resultDisplay.append(String.format("%-30s%23s%21s%n","Song","Song Time","Total Time"));
			resultDisplay.append(String.format("%45s%10s%10s%10s%n", "Minutes","Seconds","Minutes","Seconds"));
			resultDisplay.append(String.format("%s%n", "-------------------------------------------------------------------------------------------"));
			
			while (fileScanner.hasNext()) {
				songName = fileScanner.next();
				try
				{
					minutes = fileScanner.nextInt();
					seconds = fileScanner.nextInt();
					if (partyTape.addSong(minutes,seconds) == true)
						resultDisplay.append(String.format("%-30s%15d%10d%10d%10d%n", songName,minutes,seconds,partyTape.getMinutesUsed(),partyTape.getSecondsUsed()));
					else
						resultDisplay.append(songName + " (" + minutes + " mins " + seconds + ") does not fit.\n");
				}// try to read the file
				catch (InputMismatchException e) 
				{	
					// Print error code and the song name
					System.err.println("Invalid Number Skipped");
					System.err.println("\tERROR CODE: -2  (at " + songName + ")");
					// Inform the user that a particular song has been skipped
					resultDisplay.append(String.format("%-30s%s%n",songName, "(SKIPPED)"));
				}// catch block 
				finally
				{
					if (fileScanner.hasNext()) { // if there's another line after the invalid song
						fileScanner.nextLine(); // Go to the next line
					}
				}// finally
			}// filescanner loop
			resultDisplay.append("\nThere are " + partyTape.getMinutesLeft() + " minutes " + partyTape.getSecondsLeft() % 60 + " seconds of tape left.");	
		}// try block
		
		catch (FileNotFoundException e) 
		{	
			System.err.println(file.getName() + " not found");
			System.err.println("\tERROR CODE: -1");
			JOptionPane.showMessageDialog(resultDisplay, "Your file name " + file + " does not exist","File Not Found", JOptionPane.ERROR_MESSAGE);
			
		}// catch block
		
	}// openFile(File, Int)
	
	public static void main(String[] args) {
		PartyMusicFrame frame = new PartyMusicFrame();
		frame.setSize(650, 700);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}// main()

}// PartyMusicFrame class
