package se.fpt.ft;

import java.util.Calendar;

import android.text.format.DateFormat;

public class Västerås extends Ticket {

	public Västerås() {
		timePiece.add(Calendar.MINUTE, 60);
		boolean isSkolungdom = mCurrentSettings.contains("Västerås" + ":" + String.valueOf(R.id.skolungdom))? true : false;
		contentInboxValues.put("address", "VL" + String.valueOf(numberTail));
		contentInboxValues.put(
				"body",
				numberTail + " VL\n\n"
						+ (isSkolungdom ? "VS SKOLUNGDOM" : "VV VUXEN")
						+ " Giltig till "
						+ DateFormat.format("kk:mm yyyy-MM-dd", timePiece)
						+ "\n" + "Västerås\n\n"
						+ String.valueOf(isSkolungdom ? 12 : 25)
						+ " SEK (6% MOMS)\n" + seed + "\n\n"
						+ generateRandomAEOXStringBlock(seed));

		contentOutboxValues.put("address", String.valueOf("0739304050"));
		contentOutboxValues.put("body", isSkolungdom ? "VS" : "VV");
		contentOutboxValues.put("date",
				new java.util.Date().getTime() - 60 * 1000);
	}

}
