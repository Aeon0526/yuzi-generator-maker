package com.yupi.maker.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.yupi.maker.generator.file.FileGenerator;
import lombok.Data;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "generate", mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Callable<Integer> {
	/**
	 * 是否生成循环(开关)
	 */
	@CommandLine.Option(names = {"-l", "--loop"}, description = "是否循环", arity = "0..1", interactive = true, echo = true)
	private boolean loop;

	/**
	 * 作者注释(字符串填充值)
	 */
	@CommandLine.Option(names = {"-a", "--author"}, description = "作者名称", arity = "0..1", interactive = true, echo = true)
	private String author = "yupi";

	/**
	 * 输出信息
	 */
	@CommandLine.Option(names = {"-o", "--outputText"}, description = "输出文本", arity = "0..1", interactive = true, echo = true)
	private String outputText = "sum = ";

	@Override
	public Integer call() throws Exception {
		com.yupi.maker.model.DataModel dataModel = new com.yupi.maker.model.DataModel();
		BeanUtil.copyProperties(this, dataModel);
		System.out.println("配置信息：" + dataModel);
		FileGenerator.doGenerate(dataModel);
		return 0;
	}
}