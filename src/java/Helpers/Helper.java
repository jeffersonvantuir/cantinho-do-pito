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
    
    public boolean upload(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
	String filePath = "";
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	System.out.println("isMultipart="+isMultipart);
	File root_directory = new File(request.getServletContext().getRealPath("/")); 
	root_directory = root_directory.getParentFile();
	System.out.println("root_directory->?"+root_directory);
	filePath = root_directory.toString()+"//Your_Desired_Folder//file_uploads//";
	/*FileItemFactory factory = new DiskFileItemFactory();
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	List items=null;
	
	try
	 {
		items = upload.parseRequest(request);
	} catch (FileUploadException e) 
	{
		e.printStackTrace();
	}
	Iterator itr = items.iterator();
	while (itr.hasNext())
	 {
	FileItem item = (FileItem)(itr.next());
		if (item.isFormField()) 
		{
			try{
			String field=item.getFieldName();
			String value=item.getString();
			System.out.println("field="+value);
			}
			catch(Exception e)
			{
				System.out.println("Exception "+e);		
			}
		} 
		else {
			try
			 {
			
			String itemName = item.getName();
			System.out.println("\n FileName:"+itemName);
			File savedFile = new File(filePath+itemName);
			item.write(savedFile);  
			return true;
			} 
			catch (Exception e) 
			{
			e.printStackTrace();
			}
		}
	}*/
        return false;
    }
    
}
