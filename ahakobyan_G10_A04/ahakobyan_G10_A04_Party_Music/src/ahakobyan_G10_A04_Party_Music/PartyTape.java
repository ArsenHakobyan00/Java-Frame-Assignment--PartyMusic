package ahakobyan_G10_A04_Party_Music;
/**
 * Description: This class contains methods, used in PartyMusicFrame.java, to  store data from the frame and the file input.
 * @author Arsen Hakobyan
 * Course Number: 420-G10
 * Assignment: 4 (final)
 * Date: 30 December 2019
 */
public class PartyTape {

	private String collectionName;
	private int tapeLength;
	private int totalSecondsUsed;
	private int totalSecondsLeft;

	public PartyTape() {
		collectionName = "Unknown";
		tapeLength = 0;
		totalSecondsUsed = 0;
		totalSecondsLeft = 0;
	}// PartyTape() default constructor

	public PartyTape(String name, int length) {
		collectionName = name;
		tapeLength = length*60;
		totalSecondsUsed = 0;
		totalSecondsLeft = 0;
	}// PartyTape(String, int) constructor

	public String getCollectionName() {
		return collectionName;
	}// getCollectionName()

	public void setCollectionName(String name) {
		collectionName = name;
	}// setCollectionName(String)

	public int getTapeLength() {
		return tapeLength;
	}// getTapeLength()

	public void setTapeLength(int length) {
		tapeLength = length;
	}// setTapeLength(int)

	public int getMinutesUsed() {
		return totalSecondsUsed / 60;
	}// getMinutesUsed()

	public int getSecondsUsed() {
		return totalSecondsUsed % 60;
	}// getSecondsUsed()

	public int getMinutesLeft() {
		return totalSecondsLeft / 60;
	}// getMinutesLeft()

	public int getSecondsLeft() {
		return totalSecondsLeft;
	}// getSecondsLeft()

	public int convertToSeconds(int min, int sec) {
		int seconds = min * 60 + sec;
		return seconds;
	}// convertToSeconds(int, int)

	public Boolean addSong(int min, int sec) {
		Boolean roomLeft = null;
		int seconds = convertToSeconds(min, sec);
		totalSecondsLeft = getTapeLength();
		if (getSecondsLeft() - seconds > 0) {
			roomLeft = true;
			totalSecondsUsed += seconds;
			totalSecondsLeft -= seconds;
			setTapeLength(totalSecondsLeft);
		}
		else {
			roomLeft = false;
		}
		return roomLeft;
	}// addSong(int, int)
	
}// PartyTape() class
