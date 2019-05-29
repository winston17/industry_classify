package com.yzc.industry_classify.util.baseUtil;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取config.properties配置文件中的数据
 * 
 * @author Administrator
 *
 */
public class ConfigUtilG
{
	private static Properties pro = new Properties();

	static
	{
		Resource resource = new ClassPathResource("config.properties");
		InputStream ips = null;
		try
		{
			ips = resource.getInputStream();
			pro.load(ips);
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			try
			{
				if (ips != null)
				{
					ips.close();
				}
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	// 获取配置文件的中配置的值
	public static String getValue(String key)
	{
		return pro.getProperty(key).trim();
	}
}
