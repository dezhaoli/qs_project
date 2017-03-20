<%@ page import="java.io.*" %>
<%
	String fileName = (String)request.getAttribute("fileName")==null?"":(String)request.getAttribute("fileName");
	if(fileName.equals("")){
		fileName = (String)request.getParameter("fileName")==null?"":(String)request.getParameter("excelName");
	}
	
	
	String filePath = (String)request.getAttribute("filePath")==null?"":(String)request.getAttribute("filePath");
	if(filePath.equals("")){
   		fileName = (String)request.getParameter("filePath")==null?"":(String)request.getParameter("filePath");
	}
	

 	System.out.println(filePath+ fileName); 

	String message  = "文件下载";
	String message_temp = "";
	message_temp = new String(message.getBytes("ISO8859-1"),"UTF-8");
	FileInputStream fis = null;
  	OutputStream os = null;
	try{
		fis = new FileInputStream(filePath + fileName);
		os = response.getOutputStream();
		if(fis!=null){
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\"");
	
          	byte[] b = new byte[1024];
          	int i = 0;

          	while((i =fis.read(b)) > 0){
          		os.write(b, 0, i);
          	}
            
          	os.flush();
          	
          	out.clear();
          	out = pageContext.pushBody(); 
		}
	}catch(java.io.FileNotFoundException fex){
		response.setContentType("text/html;charset=UTF-8");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>文件下载</title>");
		out.println("<script type='text/javascript'>");
		out.println("alert('"+message_temp+"');");
		out.println("</script>");
		out.println("</head>");		
		out.println("</html>");
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if(fis != null){
			fis.close();
            fis = null;
   		}
        if(os != null){
        	os.close();
            os = null;
        }
	}
%>
