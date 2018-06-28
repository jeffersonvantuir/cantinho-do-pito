package Helpers;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


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
