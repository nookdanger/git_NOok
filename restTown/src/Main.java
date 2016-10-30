import org.apache.http.HttpResponse;



public class Main {
	public static void main(String[] args) {
		RazunaUtil r = new RazunaUtil();
		try {
			HttpResponse res =r.getResponseFromRazuna("https://httpbin.org", "","get", "", null);
			System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
