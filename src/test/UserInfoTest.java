package test;

import javax.ws.rs.core.MultivaluedMap;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class UserInfoTest {
	

	private String baseUrl = "http://127.0.0.1:8080/YXYS/services";
	final Client client = Client.create(); 
	private String mwsUrl = "";
	
	
	@Test
	public void testFindByDuty() throws Exception{
		 mwsUrl = baseUrl+"/worker/";
	 	 testPost("findByDuty", "duty","1"); 
	}
	
	//@Test
	public void TestFindUserById() throws Exception{
		 mwsUrl = baseUrl+"/userinfo/";
	 	 testPost("findUserInfoById", "id","1"); 
	}

	//@Test
	public void checkUserPassword() throws Exception {  
  	   	mwsUrl = baseUrl+"/userinfo/";
  	   	testPost("checkUserPassword", "shop_jz","123456"); 
    } 
	
//	@Test
	public void findAllUserInfo() throws Exception {  
		mwsUrl = baseUrl+"/userinfo/";
		testPost("findAllUserInfo","",""); 
	} 

//	@Test
	public void findAllUserType() throws Exception {  
		mwsUrl = baseUrl+"/userinfo/";
		testPost("findAllUserType","",""); 
	} 
	
	
	@SuppressWarnings("unchecked")
	private void testPost(String method, String paramName ,String param )throws Exception{
		String url = mwsUrl +method;
	    WebResource webResource = client.resource(url); 
		MultivaluedMap formData = null;
		if (null !=param) {
		    formData = new MultivaluedMapImpl();
		    formData.add(paramName, param);
		} 
			  
	    ClientResponse response = webResource.type("application/x-www-form-urlencoded").post(ClientResponse.class, formData);
	    int status = response.getStatus();
	    if (status == 200) {
	    	System.out.println(response.getEntity(String.class));
	    	status = 0;
	    }else if (status ==204) {
	    	System.out.println("操作成功！");
	    	status = 0;
	    }		
	    
	    Assert.assertEquals(0, status);
	}

}
