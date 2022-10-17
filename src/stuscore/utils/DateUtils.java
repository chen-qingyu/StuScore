package stuscore.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**  
 *	时间工具类
 */
public class DateUtils {
	
	public static final String DATETIME_DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 日期格式化功能类
	 * @param format
	 * @return
	 */
	public static SimpleDateFormat getDateFormat(String format){
		return new SimpleDateFormat(format);
	}
	
	/**
	 * 日期格式化处理
	 * @param date 要格式化的日期
	 * @param format 格式化字符串
	 * @return 格式化后的日期
	 */
	public static String formatDateTime(Date date,String format ){
		return getDateFormat(format).format(date);
	}
	
	/**
	 * 获取默认格式的当前系统时间(当前时间)
	 * 时间格式： DATETIME_DEFAULT_FORMAT
	 * @return 当前系统时间
	 */
	public static String getNowDate(){
		return formatDateTime(new Date(), DATETIME_DEFAULT_FORMAT);
	}
	
	
}
