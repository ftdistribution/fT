package se.fpt.ft;

import java.util.Calendar;
import android.text.format.DateFormat;

public class Stockholm extends Ticket {

	public Stockholm() {
		final boolean reduced = mCurrentSettings.contains("Stockholm" + ":" + String.valueOf(R.id.reduced))? true : false;
		final boolean zoneA = mCurrentSettings.contains("Stockholm" + ":" + String.valueOf(R.id.zoneA))? true : false;
		final boolean zoneB = mCurrentSettings.contains("Stockholm" + ":" + String.valueOf(R.id.zoneB))? true : false;
		final boolean zoneC = mCurrentSettings.contains("Stockholm" + ":" + String.valueOf(R.id.zoneC))? true : false;
		final boolean zoneL = mCurrentSettings.contains("Stockholm" + ":" + String.valueOf(R.id.zoneL))? true : false;

		final String zones = (zoneA ? "A" : "") + (zoneB ? "B" : "")
				+ (zoneC ? "C" : "") + (zoneL ? "L" : "");

		if (!(zones.equals("A") || zones.equals("AB") || zones.equals("ABC")
				|| zones.equals("ABCL") || zones.equals("B")
				|| zones.equals("BC") || zones.equals("BCL")
				|| zones.equals("C") || zones.equals("CL") || zones.equals("L"))) {
			// Illegal zone combination.
			errorMessages = "Välj om dina zoner, efter att du valt Vuxen/Reducerad.";
			return;
		}

		int price = 10 + (zoneA ? 10 : 0) + (zoneB ? 10 : 0) + (zoneC ? 10 : 0);
		price += zoneL ? 20 : 0;

		String spacedSeed = new StringBuilder(seed).insert(6, " ").toString();

		timePiece.add(Calendar.MINUTE,
				zones.startsWith("ABC") || zones.equals("L") ? 120 : 75);
		contentInboxValues.put("address", "SL" + String.valueOf(numberTail));
		contentInboxValues.put(
				"body",
				(reduced ? "R" : "H")
						+ "-"
						+ (zoneA ? "A" : "")
						+ (zoneB ? "B" : "")
						+ (zoneC ? "C" : "")
						+ (zoneL ? "L" : "")
						+ " "
						+ DateFormat.format("kk:mm", timePiece)
						+ " "
						+ String.valueOf(numberTail)
						+ "\n"
						+ "\n"
						+ generateRandomAEOXStringBlock(seed)
						+ "\n\n"
						+ "SL biljett giltig till "
						+ DateFormat.format("kk:mm, yyyy-MM-dd", timePiece)
						+ "\n"
						+ (reduced ? "Red pris" : "Helt pris")
						+ " "
						+ String.valueOf(reduced ? price
								: (int) (price / (5d / 9d)))
						+ " kr ink 6% moms\n"
						+ spacedSeed + "\n"
						+ "m.sl.se");

		contentOutboxValues.put("address", String.valueOf("0767201010"));
		contentOutboxValues.put("body", (reduced ? "R" : "H") + zones);
		contentOutboxValues.put("date",
				new java.util.Date().getTime() - 60 * 1000);
	}

}
