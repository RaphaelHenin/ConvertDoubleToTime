import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class ConvertDoubleToTime {

	/**
	 * 
	 * @param time
	 *            = the double value that we wanna convert to time format
	 * @param temp
	 *            = Table string that will store the time
	 * @param i
	 * @return temp, the string table that have stored the time through all
	 *         recursive
	 */
	static ArrayList<String> convertDoubleToTime(Double time, ArrayList<String> temp) {
		String stringTime = time.toString();
		String[] numbers = stringTime.split("\\.");
		int firstUnit = Integer.parseInt(numbers[0]);
		if (time >= 60) {
			time = time / 60;
			String hourUnit = (Double.toString(time)).split("\\.")[0];
			temp.add(hourUnit);
			Double newValue = time - Double.parseDouble(hourUnit);
			if (newValue == 0)
				return temp;
			BigDecimal bd = new BigDecimal(newValue * 60);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			temp = convertDoubleToTime(bd.doubleValue(), temp);
		} else {
			if (temp.size() < 1)
				temp.add(0, null);
			temp.add(Integer.toString(firstUnit));
			Double newValue = time - firstUnit;
			if (newValue == 0)
				return temp;
			BigDecimal bd = new BigDecimal(newValue * 60);
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			temp = convertDoubleToTime(bd.doubleValue(), temp);
		}
		return temp;
	}

	/**
	 * @param String
	 *            table that stored the time calculated in the
	 *            convertDoubelToTime function
	 * @return "HH:MM:SS" time format (if HH, MM or SS are not null)
	 */
	static String displayTime(ArrayList<String> time) {
		String timeToDisplay = "";
		// If hour, display it
		if (time.get(0) != null)
			// If minute, display it
			if (time.size() > 1)
				// If second, display it ELSE just display minute without ":"
				// after.
				if (time.size() > 2)
					timeToDisplay += time.get(0) + ":" + time.get(1) + ":" + time.get(2) + " heures";
				else
					timeToDisplay += time.get(0) + ":" + time.get(1) + "  heures";

			else
				timeToDisplay += time.get(0) + " heures";

		else 
			if (time.get(1) != null)
				if (time.size() > 2)
					timeToDisplay += time.get(1) + ":" + time.get(2) + " minutes";
				else
					timeToDisplay += time.get(1) + " minute(s)";
			else 
				if (time.get(2) != null)
					timeToDisplay += time.get(2) + " seconde(s)";

		// milliseconds are not displayed
		return timeToDisplay;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<String> time = new ArrayList<>();
		time = convertDoubleToTime(0.1, time);
		System.out.println(displayTime(time));
	}

}
