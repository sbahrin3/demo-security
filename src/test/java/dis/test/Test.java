package dis.test;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		String url = "http://10.10.110.123:8080/MMDIS_DIS/vessel/owner?id=538563V"; //&type=101

		
		URL obj = new URL(url);
		URLConnection conn = obj.openConnection();
		
		//get all headers
		Map<String, List<String>> map = conn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + 
	                 " ,Value : " + entry.getValue());
		}
		
		String value = conn.getHeaderField("Message");
		System.out.println(value);
		
		/*
		String url = "http://localhost:8080/dis/test";
		url = "http://10.10.110.123:8080/MMDIS_DIS/vessel/owner?id=538563V&type=101";
		InputStream is = null;
		try {
			is = new URL(url).openStream();

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	        String jsonText = "";
			try {
				jsonText = readAll(rd);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        JSONObject json = new JSONObject(jsonText);
	        
	        System.out.println(jsonText);
	        
	      } finally {
	        try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
		
		*/
		
	}
	
	  private static String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }

}
