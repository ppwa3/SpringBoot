package com.edu.springboot;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;

@Controller
public class MainController {
   
   @RequestMapping("/")
   public String main() {
      return "main";
   }
   
   //파일업로드 폼 매핑
   @GetMapping("/fileUpload.do")
   public String fileUpload() {
      return "fileUpload";
   }
   
   //파일업로드 처리
   @PostMapping("/uploadProcess.do")
   public String uploadProcess(HttpServletRequest req, Model model) {
      try {
         //업로드 할 디렉토리의 물리적 경로를 얻어온다.
         String uploadDir = ResourceUtils
               .getFile("classpath:static/uploads/").toPath().toString();
         System.out.println("물리적경로 : " + uploadDir);
         
         //전송된 첨부파일을 통해 Part객체를 생성한다.
         Part part = req.getPart("ofile");
         //파일명 확인을 위해 헤더값을 얻어온다.
         String partHeader = part.getHeader("content-disposition");
         System.out.println("partHeader = " + partHeader);
         
         //헤더값에서 파일명을 추출하기 위해 문자열을 split한다.
         String[] phArr = partHeader.split("filename=");
         //따옴표를 제거해서 원본파일명을 추출한다.
         String originalFileName = phArr[1].trim().replace("\"", "");
         //전송됨 파일이 있다면 서버에 저장한다.
         if (!originalFileName.isEmpty()) {
            part.write(uploadDir + File.separator + originalFileName);
         }
         
         //서버에 저장된 파일의 파일명을 UUID로 생성된 문자열로 변경한다.
         String savedFileName =
               MyFunctions.renameFile(uploadDir, originalFileName);
         
         //JDBC 연동을 하지 않으므로, 영역에 저장한 후 View에서 출력한다.
         model.addAttribute("originalFileName", originalFileName);
         model.addAttribute("savedFileName", savedFileName);
         model.addAttribute("title", req.getParameter("title"));
         model.addAttribute("cate", req.getParameterValues("cate"));
         
      }
      catch (Exception e) {
         System.out.println("업로드 실패");
      }
      
      return "fileUploadOK";
   }
   
   
   
   // 멀티파일업로드 폼 매핑
   @GetMapping("/multiFileUpload.do")
   public String multiFileUpload() {
      return "multiFileUpload";
   }
   
   //멀티파일 업로드 처리
   @PostMapping("/multiUploadProcess.do")
   public String multimultiUploadProcess(HttpServletRequest req, Model model) {
      try {
         // 물리적 경로 얻어오기
         String uploadDir = ResourceUtils.getFile("classpath:static/uploads/")
                           .toPath().toString();
         System.out.println("물리적경로:"+uploadDir);
         
         /*
         파일명 저장을 위한 Map생성. Key는 원본파일명, Value는 서버에
         저장된 파일명을 저장한다.
          */
         Map<String, String> saveFileMaps = new HashMap<>();
         
         // 2개 이상의 파일이므로 getParts()를 통해 폼값을 얻어온다.
         Collection<Part> parts = req.getParts();
         // Collection타입으로 얻어오므로 반복할 수 있다.
         for(Part part: parts) {
            /*
            여러 폼값 중 파일인 경우에만 업로드 처리를 위해 하위 문장을
            실행한다. 파일이 아닌 경우에는 반복문의 처음으로 돌아간다.
             */
            if(!part.getName().equals("ofile")) {
               continue;
            }
            
            // 헤더값을 통해 파일명을 얻어온다.
            String partHeader = part.getHeader("content-disposition");
            System.out.println("partHeader="+partHeader);
            String[] phArr = partHeader.split("filename=");
            String originalFileName = phArr[1].trim().replace("\"", "");
            // 원본파일명으로 서버에 저장한다.
            if(!originalFileName.isEmpty()) {
               part.write(uploadDir+File.separator+originalFileName);
            }
            // 파일이 저장되면 UUID로 생성한 새로운 파일명으로 변경한다.
            String savedFileName = MyFunctions.renameFile(uploadDir, originalFileName);
            // 원본 및 변경된 파일명을 Map에 저장한다.
            saveFileMaps.put(originalFileName, savedFileName);
                  
         }
         // 파일의 정보를 저장
         model.addAttribute("saveFileMaps",saveFileMaps);
         // 파일 외 나머지 폼값도 저장
         model.addAttribute("title",req.getParameter("title"));
         model.addAttribute("cate",req.getParameter("cate"));
      }
      catch(Exception e) {
         System.out.println("업로드 실패");
      }
      
      return "multiFileUploadOk";
   }
   
}
