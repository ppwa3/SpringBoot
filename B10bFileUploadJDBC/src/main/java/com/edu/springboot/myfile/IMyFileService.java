package com.edu.springboot.myfile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMyFileService {
	public int insertFile(MyFileDTO myFileDTO);
	public List<MyFileDTO> selectFile();
}
