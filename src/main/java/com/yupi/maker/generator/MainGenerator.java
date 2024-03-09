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
		if (!FileUtil.exist(outputPath)) {
			FileUtil.mkdir(outputPath);
		}

		// 读取 resources 目录
		ClassPathResource classPathResource = new ClassPathResource("");
		String inputResourcePath = classPathResource.getAbsolutePath();

		// java 包的基础路径
		// com.yupi
		String outputBasePackage = meta.getBasePackage();
		// com/yupi
		String outputBasePackagePath = StrUtil.join("/", StrUtil.split(outputBasePackage, "."));
		// generated/src/main/java/com/yupi
		String outputBaseJavaPackagePath = outputPath + File.separator + "src/main/java" + outputBasePackagePath;

		String inputFilePath;
		String outputFilePath;

		// model.DataModel
		inputFilePath = inputResourcePath + File.separator + "templates/java/model/DataModel.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + File.separator + "model/DataModel.java";
		DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

		// cli.command.ConfigCommand
		inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ConfigCommand.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/cli/command/ConfigCommand.java";
		DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

		// cli.command.GenerateCommand
		inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/GenerateCommand.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/cli/command/GenerateCommand.java";
		DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

		// cli.command.ListCommand
		inputFilePath = inputResourcePath + File.separator + "templates/java/cli/command/ListCommand.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/cli/command/ListCommand.java";
		DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

		// cli.CommandExecutor
		inputFilePath = inputResourcePath + File.separator + "templates/java/cli/CommandExecutor.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/cli/CommandExecutor.java";
		DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

		// Main
		inputFilePath = inputResourcePath + File.separator + "templates/java/Main.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/Main.java";
		DynamicFileGenerator.doGenerate(inputFilePath, outputFilePath, meta);

		// generator.DynamicGenerator
		inputFilePath = inputResourcePath + File.separator + "templates/java/generator/DynamicGenerator.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/generator/DynamicGenerator.java";
		DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

		// generator.MainGenerator
		inputFilePath = inputResourcePath + File.separator + "templates/java/generator/MainGenerator.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/generator/MainGenerator.java";
		DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);

		// generator.StaticGenerator
		inputFilePath = inputResourcePath + File.separator + "templates/java/generator/StaticGenerator.java.ftl";
		outputFilePath = outputBaseJavaPackagePath + "/generator/StaticGenerator.java";
		DynamicFileGenerator.doGenerate(inputFilePath , outputFilePath, meta);
	}
}
