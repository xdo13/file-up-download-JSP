package fileupload;

import java.io.IOException;
import java.util.ArrayList;

import com.company.MyFileDAO;
import com.company.MyFileDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/MultipleProcess.do")
@MultipartConfig(
	maxFileSize = 1024 * 1024 * 1,
	maxRequestSize = 1024 * 1024 * 10
)
public class MultipleProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		try {
		    String saveDirectory = "D:/uploads";
		    
		    ArrayList<String> listFileName = FileUtil.multipleFile(req, saveDirectory);
	         
		    for(String originalFileName : listFileName) {
		        String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);
		        
		        insertMyFile(req, originalFileName, savedFileName);
		    }
		    
	        res.sendRedirect("FileList.jsp");
		}
		catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "파일 업로드 오류");
			req.getRequestDispatcher("MultiFileUploadMain.jsp").forward(req, res);
		}
	}
	
	private void insertMyFile(HttpServletRequest req, String oFileName, String sFileName) {
		String title = req.getParameter("title");
		String[] cateArray = req.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if (cateArray == null) {
			cateBuf.append("선택한 항목 없음");
		}
		else {
			for (String s : cateArray) {
				cateBuf.append(s + ", ");
			}
		}
		MyFileDTO dto = new MyFileDTO();
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(oFileName);
		dto.setSfile(sFileName);
		
		MyFileDAO dao = new MyFileDAO();
		dao.insertFile(dto);
		dao.close();
		
	}

}