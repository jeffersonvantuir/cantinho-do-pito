package Helpers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 *
 * @author jefferson
 */
public class Helper {
    
    public String formatterDateDB(String d) throws ParseException
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(d, format);
        return String.valueOf(date);
    }
    
    public String formatterDateUsage(String d) throws ParseException
    {
        SimpleDateFormat formatReceived = new SimpleDateFormat("yyyy-MM-dd");
        java.sql.Date date = new java.sql.Date(formatReceived.parse(d).getTime());
        SimpleDateFormat formatterOutput = new SimpleDateFormat("dd-MM-yyyy");

        return formatterOutput.format(date);
    }
    
    public String md5(String password) throws NoSuchAlgorithmException
    {
        String s1 = "ecommercecantinhodopito";
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(password.getBytes()));
        return hash.toString(16);
    }
}
