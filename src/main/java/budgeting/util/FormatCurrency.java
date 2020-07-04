package budgeting.util;

public class FormatCurrency {
    public static String format(int value){
        String string = value + "";

        if(value < 100){
            if(value < 10){
                return "0.0" + value;    
            }
            return "0." + string;
        }

        return string.substring(0, string.length() - 2) + "." + string.substring(string.length() - 2);
    }
}