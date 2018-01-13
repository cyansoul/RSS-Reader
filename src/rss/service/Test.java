package rss.service;

import org.apache.commons.lang3.StringEscapeUtils;

public class Test {
	public static void main(String[] args) {
		String ss = StringEscapeUtils.escapeJson("sdadad'sadasd'asdad'");
		System.out.println(ss);
	}
}
