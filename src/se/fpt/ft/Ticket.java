package se.fpt.ft;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.content.ContentValues;

public class Ticket {
	private final Random random = new Random();
	
	protected static String generateRandomAEOXStringBlock(String seed) {
		String[] hexAEOX = { "OO", "OX", "OA", "OE", "XO", "XX", "XA",
				"XE", "AO", "AX", "AA", "AE", "EO", "EX", "EA", "EE"};

		String tail = hexAEOX[Integer.valueOf(seed.substring(4, 5))]
					+ hexAEOX[Integer.valueOf(seed.substring(2, 3))];
		
		String hexString = Long.toHexString(Long.valueOf(seed));
		String sAEOX = "";
		for (int i = 0; i < hexString.length(); i++) {
			sAEOX += hexAEOX[Integer.valueOf(
					String.valueOf(hexString.charAt(i)),
					16)];
		}
		String AEOX = "E" + sAEOX.substring(0, 9) + "\n"
					+ "E" + sAEOX.substring(9, 18) + "\n"
					+ "E" + sAEOX.substring(18, 24) + tail.substring(1) + "\n"
					+ "EEEEEEEEEE";
		return AEOX;
	}
	

	protected final static ContentValues contentInboxValues = new ContentValues();
	protected final static ContentValues contentOutboxValues = new ContentValues();
	protected static String errorMessages = null;
	protected static Set<String> mCurrentSettings = new HashSet<String>();
	protected final Calendar timePiece = Calendar.getInstance();
	protected final int numberTail = random.nextInt(900) + 100;
	protected final String seed = String.valueOf(random.nextInt(999999))
			+ String.valueOf(random.nextInt(999999))
			+ String.valueOf(numberTail);
	
	public static ContentValues getContentInboxValues() {
		return contentInboxValues;
	}

	public static ContentValues getContentOutboxValues() {
		return contentOutboxValues;
	}

	public static String getError() {
		String mErrorMessages = errorMessages;
		errorMessages = null;
		return mErrorMessages;
	}

	
	public static void setCurrentSettings(Set<String> currentSettings) {
		mCurrentSettings = currentSettings;
	}

}
