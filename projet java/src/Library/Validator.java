package Library;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;
public class Validator 
{
	public static boolean estVide(String champ) {
        if (champ.compareToIgnoreCase("")==0 || champ.compareToIgnoreCase( " ")==0)
        {
            return false;
        }
        return true;
    }
	 public static boolean isNumeric(String str) { 
		 NumberFormat formatter = NumberFormat.getInstance();
		 ParsePosition pos = new ParsePosition(0);
		 formatter.parse(str.toLowerCase(), pos);
		 return str.length() == pos.getIndex();
	} 
	public static float isTaux(float tauxloc){
        return tauxloc;
    }
    public static boolean isEmail(String email)
    {
    	String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&-]+)@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
			if (email == null || email == "")
                return false;
            return pat.matcher(email).matches();
    }
    public static boolean isTel(String tel){
        String telRegex = "^(\\+|00)(221)(70|76|77|78|75|33)([0-9]{7})";

        Pattern pat = Pattern.compile(telRegex);
        if (tel == null || tel == "")
            return false;
        return pat.matcher(tel).matches();
    }
    public static boolean isNci(String nci)
    {
    	String nciRegex = "(^[1|2])([0-9]{3})(19[4-9]\\d|20[0-1]\\d|2020|2021)([0-9]{5})";

        Pattern pat = Pattern.compile(nciRegex);
        if (nci == null || nci == "")
            return false;
        
        return pat.matcher(nci).matches();
    }
    @SuppressWarnings("unused")
	public static boolean valDate(String DOB)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date BOD = null;
        df.setLenient(false);
        try 
        {
            BOD = df.parse(DOB);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    public static boolean valName(String nom)
    {
        return Pattern.matches("[A-Za-z]+''+[A-Z]" , nom);
    }
}
