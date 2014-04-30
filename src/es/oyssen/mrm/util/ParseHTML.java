package es.oyssen.mrm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseHTML {
	private static String URL_PATTERN = "(http|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?";
	
	public static String parseStringToHTML(String text) {
		String html = new String(text);
		Pattern p = Pattern.compile(URL_PATTERN);
		Matcher matcher = p.matcher(text);		
		while (matcher.find()) {
			String url = matcher.group();
			String link = "<a href='" + url + "' target='_blank'>" + url + "</a>";
			int indexOf = html.indexOf(url);
			int end = url.length();
			String inicio = html.substring(0, indexOf);
			String fin = html.substring(end-1);
			html =  inicio + link + fin;
		}
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<body>" + html  +"</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
