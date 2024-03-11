package com.yupi.maker.generator.main;

public class MainGenerator extends GenerateTemplate{

	// 重写精简版生成，不再生成精简版文件
	@Override
	protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
		System.out.println("不再生成精简版文件");
	}


}
