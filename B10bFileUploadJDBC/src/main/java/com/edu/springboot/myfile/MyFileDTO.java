package com.edu.springboot.myfile;

import lombok.Data;

// myfile 테이블과 동일하게 멤버변수 정의
@Data
public class MyFileDTO {
   private String idx;
   private String title;
   private String cate;
   private Object ofile;
   private String sfile;
   private java.sql.Date postdate;
}
