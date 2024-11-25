package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	
	public static String uploadFile(HttpServletRequest req, String sDirectory)
					throws ServletException, IOException{
		Part part = req.getPart("ofile");
		String partHeader = part.getHeader("content-disposition");
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory+ File.separator + originalFileName);
		}

	return originalFileName;
}

public static String renameFile(String sDirectory, String fileName) {
	String ext = fileName.substring(fileName.lastIndexOf(".")); //확장자 잘라내기
	String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
	
	String newFileName = now + ext; //새로운 파일명 생성
	
	File oldFile = new File(sDirectory + File.separator + fileName);
	File newFile = new File(sDirectory + File.separator + newFileName);
	oldFile.renameTo(newFile);
	
	return newFileName;
}
}