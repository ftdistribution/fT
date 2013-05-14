package se.fpt.ft;

import java.util.Calendar;
import android.text.format.DateFormat;

public class Örebro extends Ticket {

	public Örebro() {
		boolean isSkolungdom = mCurrentSettings.contains("Örebro" + ":" + String.valueOf(R.id.skolungdom))? true : false;
		timePiece.add(Calendar.MINUTE, 180);

		contentInboxValues.put("address", "LTO" + String.valueOf(numberTail));
		contentInboxValues.put("body", numberTail + " LTÖ\n\n" + "ÖS "
				+ (isSkolungdom ? "SKOLUNGDOM" : "VUXEN") + " Giltig till "
				+ DateFormat.format("kk:mm yyyy-MM-dd", timePiece) + "\n"
				+ "Örebro\n\n" + String.valueOf(isSkolungdom ? 10 : 20)
				+ " SEK (6% MOMS)\n" + seed + "\n\n"
				+ generateRandomAEOXStringBlock());

		contentOutboxValues.put("address", String.valueOf("0762778000"));
		contentOutboxValues.put("body", isSkolungdom ? "ÖS" : "ÖV");
		contentOutboxValues.put("date",
				new java.util.Date().getTime() - 60 * 1000);
	}
}
