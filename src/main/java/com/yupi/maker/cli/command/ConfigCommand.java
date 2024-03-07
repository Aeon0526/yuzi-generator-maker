package com.yupi.maker.cli.command;

import cn.hutool.core.util.ReflectUtil;
import picocli.CommandLine;

import java.lang.reflect.Field;

@CommandLine.Command(name = "config", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
	@Override
	public void run(){
		// 获取MainTemplateConfig类的所有字段
		Field[] fields = ReflectUtil.getFields(com.yupi.model.DataModel.class);
		// 遍历并打印每个字段的信息
		for(Field field : fields){
			System.out.println("字段类型"+field.getType());
			System.out.println("字段名称"+field.getName());
			System.out.println("---");
		}
	}
}
