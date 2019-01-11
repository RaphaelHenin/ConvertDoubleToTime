import java.io.IOException;

public class ConvertDoubleToTime {

	static String[] convertDoubleToTime(String time, String[] temp, int i) {
		String[] numbers = time.split("\\.");
		Double value = Double.parseDouble(time);
		int firstUnit = Integer.parseInt(numbers[0]);
		if (value > 60) {
			value = value / 60;
			String hourUnit = (Double.toString(value)).split("\\.")[0];
			temp[i] = hourUnit;
			Double newValue = value - Double.parseDouble(hourUnit);
			if (newValue == 0)
				return temp;
			temp = convertDoubleToTime(Double.toString(Math.round(newValue * 60)), temp, i);
		} else {
			temp[i + 1] = Integer.toString(firstUnit);
			Double newValue = value - firstUnit;
			if (newValue == 0)
				return temp;
			temp = convertDoubleToTime(Double.toString(Math.round(newValue * 60)), temp, i + 1);
		}
		return temp;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] time = new String[4];
		time = convertDoubleToTime("11.50", time, 0);
		for(int i=0;i<time.length;i++)
			if(time[i]==null)
				time[i] = "00";
		System.out.println(time[0]+"h"+time[1]+"m"+time[2]+"s"+time[3]);

	}

}
