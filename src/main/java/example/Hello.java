package example;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Hello implements RequestHandler<Map<String, Object>, Response> {

	@Override
	public Response handleRequest(Map<String, Object> req, Context context) {
		Response res = new Response();
		ObjectMapper om = new ObjectMapper();
		RequestData qp=null;
		try {
			qp = om.readValue(req.get("body").toString(), RequestData.class);
		}catch(Exception ex) {}
		res.setBody("Hello RequestBody param#1 value = " + qp.getOne() + ", city name = " + qp.getCity().getName());
		res.setBase64Encoded(false);
		res.setStatusCode(200);
		return res;
	}
}

class RequestData {

	private String one;
	private String two;
	private City city;
	
	public RequestData() {}
	
	public RequestData(String one, String two) {
		this.one=one;
		this.two=two;
	}

	public String getOne() {
		return one;
	}

	public void setOne(String one) {
		this.one = one;
	}

	public String getTwo() {
		return two;
	}

	public void setTwo(String two) {
		this.two = two;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}

class City {
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	private String name;
	private String zipcode;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Request {

	private RequestData body;
	//private QueryParams queryStringParameters;
	
	private String qps;
	
	public Request() {
		
	}
	
	public Request(String qps) {
		this.qps=qps;
	}

	public RequestData getBody() {
		return body;
	}

	public void setBody(RequestData body) {
		this.body = body;
	}

//	public QueryParams getQueryStringParameters() {
//		return queryStringParameters;
//	}
//
//	public void setQueryStringParameters(QueryParams queryStringParameters) {
//		this.queryStringParameters = queryStringParameters;
//	}


}

class Response {
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	public boolean isBase64Encoded() {
		return base64Encoded;
	}

	public void setBase64Encoded(boolean base64Encoded) {
		this.base64Encoded = base64Encoded;
	}

	private String body;
	private int statusCode;
	private boolean base64Encoded;
	private Map<String, String> headers;
}

class ResponseBody {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}