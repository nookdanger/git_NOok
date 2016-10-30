
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;

public class RazunaUtil {

	public static HttpResponse getResponseFromRazuna(String url,
			String requestMethod, String method, String apiKey,
			Map<String, String> parameters) throws Exception {

		String scheme = "http";
		String port = "443";

		// remove http or https out of hostAddress
		if (url.toLowerCase().contains("https://")) {
			scheme = "https";
			port = "443";
			url = url.substring(8);
		} else if (url.toLowerCase().contains("http://")) {
			url = url.substring(7);
			port = "80";
		}

		HostnameVerifier hostnameVerifier = org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;

		DefaultHttpClient client = new DefaultHttpClient();

		final X509Certificate[] _AcceptedIssuers = new X509Certificate[] {};
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return _AcceptedIssuers;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		};
		ctx.init(null, new TrustManager[] { tm }, new SecureRandom());
		SSLSocketFactory ssf = new SSLSocketFactory(ctx,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = client.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));

		SSLSocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
		socketFactory
				.setHostnameVerifier((X509HostnameVerifier) hostnameVerifier);
		SingleClientConnManager mgr = new SingleClientConnManager(
				client.getParams(), sr);
		DefaultHttpClient httpclient = new DefaultHttpClient(mgr,
				client.getParams());

		// Set verifier
		HttpsURLConnection.setDefaultHostnameVerifier(hostnameVerifier);

		try {
			HttpHost target = new HttpHost(url, Integer.parseInt(port), scheme);

			StringBuilder requestBuilder = new StringBuilder(requestMethod);
			requestBuilder.append("?method=");
			requestBuilder.append(method);
			requestBuilder.append("&api_key=");
			requestBuilder.append(apiKey);

			if (parameters != null && !parameters.isEmpty()) {
				for (String parameterName : parameters.keySet()) {
					requestBuilder.append("&");
					requestBuilder.append(parameterName);
					requestBuilder.append("=");
					requestBuilder.append(parameters.get(parameterName));
				}
			}

			// specify the get request
			HttpGet getRequest = new HttpGet(requestBuilder.toString()
					.replaceAll("\"", ""));

			HttpResponse httpResponse = httpclient.execute(target, getRequest);

			if (httpResponse != null) {
				return httpResponse;
			}

		} catch (Exception e) {

		}

		return null;
	}

}
