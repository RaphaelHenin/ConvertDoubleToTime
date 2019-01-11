import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
			BigDecimal bd = new BigDecimal(newValue * 60);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			temp = convertDoubleToTime(bd.toString(), temp, i);
		} else {
			temp[i + 1] = Integer.toString(firstUnit);
			Double newValue = value - firstUnit;
			if (newValue == 0)
				return temp;
			BigDecimal bd = new BigDecimal(newValue * 60);
		    bd = bd.setScale(2, RoundingMode.HALF_UP);
			temp = convertDoubleToTime(bd.toString(), temp, i + 1);
		}
		for(int y=0;y<temp.length;y++)
			if(temp[y]==null)
				temp[y] = "00";
		return temp;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] time = new String[4];
		time = convertDoubleToTime("11.98", time, 0);
		System.out.println(time[0]+"h"+time[1]+"m"+time[2]+"s"+time[3]);
	}

}
