package myServlet;


import java.io.BufferedInputStream;  
import java.io.ByteArrayInputStream;
import java.io.File;  
import java.io.FileInputStream;
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;
import java.io.PrintWriter;  
import java.util.List;  
  





import javax.servlet.ServletConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;  
	    private ServletFileUpload upload;  
	    private final long MAXSize = 4194304*2L;//4*2MB  
	    private String filedir="/home/wanwan";  

	/**
	 * Constructor of the object.
	 */
	public MyServlet() {
		super();
	}
	public void init(ServletConfig config) throws ServletException {  
		    
	    }  

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
if(request.getParameter("secret").equals("12580")){
	OutputStream out = response.getOutputStream();
	String str="1";
	 InputStream is = new ByteArrayInputStream(str.getBytes());
     byte[] bytes = new byte[1024];
     int len = 0;
     while ((len = is.read(bytes)) != -1) {  //read不能保证bytes被写满，但会返回读取字节数
         out.write(bytes, 0, len);
     }
     is.close();
     out.close();
     System.out.print("OK");
     return;
}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    FileItemFactory factory = new DiskFileItemFactory();// Create a factory for disk-based file items  
        this.upload = new ServletFileUpload(factory);// Create a new file upload handler  
        this.upload.setSizeMax(this.MAXSize);// Set overall request size constraint 4194304  
     	OutputStream out = response.getOutputStream();
        System.out.println("filedir="+filedir);  
		       try {  
		            List<FileItem> items = this.upload.parseRequest(request);  
		            if(items!=null  && !items.isEmpty()){  
		                for (FileItem fileItem : items) {  
		                    String filename=fileItem.getName();  
		                    String filepath=filedir+File.separator+filename;  
		                    System.out.println("文件保存路径为:"+filepath);  
		                    File file=new File(filepath);  
		                   InputStream inputSteam=fileItem.getInputStream();  
		                   BufferedInputStream fis=new BufferedInputStream(inputSteam);  
		                   FileOutputStream fos=new FileOutputStream(file);  
		                   int f;  
		                   while((f=fis.read())!=-1)  
		                    {  
		                       fos.write(f);  
		                   }  
		                   fos.flush();  
		                    fos.close();  
		                    fis.close();  
		                    inputSteam.close();  
		                   System.out.println("文件："+filename+"上传成功!");  
		                }  
		            }  
		       
		            String str="上传文件成功!";
		       	 InputStream is = new ByteArrayInputStream(str.getBytes());
		            byte[] bytes = new byte[1024];
		            int len = 0;
		            while ((len = is.read(bytes)) != -1) {  //read不能保证bytes被写满，但会返回读取字节数
		                out.write(bytes, 0, len);
		            }
		            is.close();
		            System.out.print("OK");
		            
		        } catch (FileUploadException e) {  
		            e.printStackTrace();  
		            String str="上传文件失败:"+e.getMessage();
			       	 InputStream is = new ByteArrayInputStream(str.getBytes());
			            byte[] bytes = new byte[1024];
			            int len = 0;
			            while ((len = is.read(bytes)) != -1) {  //read不能保证bytes被写满，但会返回读取字节数
			                out.write(bytes, 0, len);
			            }
			            is.close();
			            System.out.print("OK");

		        }  
out.close();
	}


}
