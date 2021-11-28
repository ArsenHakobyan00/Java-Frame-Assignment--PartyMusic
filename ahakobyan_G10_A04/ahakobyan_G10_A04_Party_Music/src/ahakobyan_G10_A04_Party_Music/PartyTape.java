/**
 * Description: This class contains methods, used in PartyMusicFrame.java, to
 * store data from the frame and the file input.
 * 
 * @author Arsen Hakobyan
 *         Course Number: 420-G10
 *         Assignment: 4 (final)
 *         Date: 30 December 2019
 */
public class PartyTape {

	private String collectionName;
	private int tapeLength;
	private int totalSecondsUsed;
	private int totalSecondsLeft;

	public PartyTape() {
		setCollectionName("Unknown");
		setTapeLength(0);
		setSecondsUsed(0);
		setSecondsLeft(0);
	}// PartyTape() default constructor

	public PartyTape(String name, int length) {
		setCollectionName(name);
		setTapeLength(length * 60);
		setSecondsUsed(0);
		setSecondsLeft(0);
	}// PartyTape(String, int) constructor

	public String getCollectionName() {
		return collectionName;
	}// getCollectionName()

	private void setCollectionName(String name) {
		this.collectionName = name;
	}// setCollectionName(String)

	public int getTapeLength() {
		return tapeLength;
	}// getTapeLength()

	private void setTapeLength(int length) {
		this.tapeLength = length;
	}// setTapeLength(int)

	public int getSecondsUsed() {
		return totalSecondsUsed % 60;
	}// getSecondsUsed()

	private void setSecondsUsed(int secondsUsed) {
		this.totalSecondsUsed = secondsUsed;
	}// setSecondsUsed(int)

	public int getSecondsLeft() {
		return totalSecondsLeft;
	}// getSecondsLeft()

	private void setSecondsLeft(int secondsLeft) {
		this.totalSecondsLeft = secondsLeft;
	}// setSecondsLeft()

	public int getMinutesUsed() {
		return totalSecondsUsed / 60;
	}// getMinutesUsed()

	public int getMinutesLeft() {
		return totalSecondsLeft / 60;
	}// getMinutesLeft()

	private int convertToSeconds(int min, int sec) {
		return min * 60 + sec;
	}// convertToSeconds(int, int)

	public Boolean addSong(int min, int sec) {
		int seconds = convertToSeconds(min, sec);
		totalSecondsLeft = getTapeLength();

		if (getSecondsLeft() - seconds > 0) {
			// Update the total seconds used and left
			totalSecondsUsed += seconds;
			totalSecondsLeft -= seconds;
			setTapeLength(totalSecondsLeft);
			return true;
		}
		return false;
	}// addSong(int, int)

}// PartyTape() class