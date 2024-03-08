package com.yupi.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.StrUtil;
import com.yupi.maker.generator.file.DynamicFileGenerator;
import com.yupi.maker.meta.Meta;
import com.yupi.maker.meta.MetaManager;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {
	public static void main(String[] args) throws TemplateException, IOException {
		Meta meta = MetaManager.getMetaObject();
		System.out.println(meta);

		// 输出的根路径
		String projectPath = System.getProperty("user.dir");
		String outputPath = projectPath + File.separator + "generated";
		if (!FileUtil.exist(outputPath)){
			FileUtil.mkdir(outputPath);
		}

		// 读取 resources 目录
		ClassPathResource classPathResource = new ClassPathResource("");
		String inputResourcePath = classPathResource.getAbsolutePath();

		// java 包的基础路径
		// com.yupi
		String outputBasePackage = meta.getBasePackage();
		// com/yupi
		String outputBasePackagePath = StrUtil.join("/",StrUtil.split(outputBasePackage,"."));
		// generated/src/main/java/com/yupi
		String outputJavaBasePath = outputPath + File.separator + "src/main/java" + outputBasePackagePath;

		String inputFilePath;
		String outputFilePath;

		// model.DataModel
		inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
		outputFilePath = outputJavaBasePath + File.separator + "model/DataModel.java";
		DynamicFileGenerator.doGenerate(inputFilePath,outputFilePath,meta);
	}
}
