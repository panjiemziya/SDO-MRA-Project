//Author: Pnjie Mziya

package mziya.panjie;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Webservice
	{
		  private final String baseURL = "https://www.mra.mw/sandbox/programming/challenge/webservice/";
		  private final String loginURL = "auth/login";
		  private final String logoutURL = "auth/logout";
		  private final String addTaxpayerURL = "Taxpayers/add";
		  private final String getTaxpayerURL = "Taxpayers/getAll";
		  private final String editTaxpayerURL = "Taxpayers/edit";
		  private final String deleteTaxpayerURL = "Taxpayers/delete";
		  private final String emailKey = "\"Email\"";
		  private final String passwordKey = "\"Password\"";
		  private final String contentTypeKey = "Content-Type";
		  private final String contentType = "=application/json:";
		  private final String candidateIDKey = "candidateid";
		  private final String apikeyKey = "apikey";
		  private String param;
		  private String apikey;
		  private String candidateid;
		  private Client client;
		  private WebTarget target;
		  private Invocation.Builder builder;
		  private Response response;
		  private String result;
		  private Object object;
		  private JSONObject json;
		  private JSONArray jsons;
		  private String[][] details;
		  private String[] details2;
		  protected int rows;
		  private Long sucess;

		  public boolean login(String username, String password)throws ParseException
		  		{
				    this.param = ("{\"Email\":\"" + username + "\"," + "\"Password\"" + ":" + "\"" + password + "\"}");
				    this.client = ClientBuilder.newClient(new ClientConfig());
				    this.target = this.client.target("https://www.mra.mw/sandbox/programming/challenge/webservice/auth/login");
				    this.builder = this.target.request(new String[] { "application/json" });
				    this.response = this.builder.post(Entity.entity(this.param, "application/json"));
				    this.result = ((String)this.response.readEntity(String.class));
				    this.object = new JSONParser().parse(this.result);
				    this.json = ((JSONObject)this.object);
				    boolean authenticated = ((Boolean)this.json.get("Authenticated")).booleanValue();
				    
					    if (authenticated) 
					    	{
						      this.apikey = "3fdb48c5-336b-47f9-87e4-ae73b8036a1c";
						      this.candidateid = username;
					    	}
		
				return authenticated;
		  		
		  		}

		  public void logout()throws ParseException
			  {
				    this.param = ("{\"Email\":\"" + this.candidateid + "\"}");
				    this.client = ClientBuilder.newClient(new ClientConfig());
				    this.target = this.client.target("https://www.mra.mw/sandbox/programming/challenge/webservice/auth/logout");
				    this.builder = this.target.request(new String[] { "application/json" });
				    this.response = this.builder.post(Entity.entity(this.param, "application/json"));
				    this.result = ((String)this.response.readEntity(String.class));
				    this.object = new JSONParser().parse(this.result);
				    this.json = ((JSONObject)this.object);
					this.sucess = (Long)this.json.get("ResultCode");
					
				    if (sucess.longValue() == 1L) 
				    {
				      this.apikey = null;
				      this.candidateid = null;
				    }
			  }

  public String[][] getTaxpayers() throws ParseException {
    int x = 0;
    this.client = ClientBuilder.newClient(new ClientConfig());
    this.target = this.client.target("https://www.mra.mw/sandbox/programming/challenge/webservice/Taxpayers/getAll");
    this.builder = this.target.request(new String[] { "application/json" });
    this.builder.header("Content-Type", "=application/json:");
    this.builder.header("candidateid", this.candidateid);
    this.builder.header("apikey", this.apikey);
    this.response = this.builder.get();
    this.result = ((String)this.response.readEntity(String.class));
    this.jsons = ((JSONArray)JSONValue.parse(this.result));
    this.details2 = new String[this.jsons.size()];
    this.details = new String[this.details2.length][7];
    while (x < this.details2.length) {
      this.details2[x] = this.jsons.get(x).toString();
      this.json = ((JSONObject)JSONValue.parse(this.details2[x].toString()));

      this.details[x][0] = this.json.get("TPIN").toString();
      this.details[x][1] = this.json.get("BusinessCertificateNumber").toString();
      this.details[x][2] = this.json.get("TradingName").toString();
      this.details[x][3] = this.json.get("BusinessRegistrationDate").toString();
      this.details[x][4] = this.json.get("MobileNumber").toString();
      this.details[x][5] = this.json.get("Email").toString();
      this.details[x][6] = this.json.get("PhysicalLocation").toString();

      x++;

      this.rows = this.details.length;
    }

    return this.details;
  }

  public boolean deleteTaxpayer(String tpin)
    throws ParseException
  {
    String param = "{\"TPIN\":\"" + tpin + "\"}";
    this.client = ClientBuilder.newClient(new ClientConfig());
    this.target = this.client.target("https://www.mra.mw/sandbox/programming/challenge/webservice/Taxpayers/delete");
    this.builder = this.target.request(new String[] { "application/json" });
    this.builder.header("Content-Type", "=application/json:");
    this.builder.header("candidateid", this.candidateid);
    this.builder.header("apikey", this.apikey);
    this.response = this.builder.post(Entity.json(param));
    boolean deleted = Boolean.parseBoolean(this.result = (String)this.response.readEntity(String.class));
    return deleted;
  }

  public boolean addTaxpayer(String tpin, String cNo, String tName, String bRDate, String mNo, String email, String location) throws ParseException
  {
    boolean temp = false;
    String param = "{\"TPIN\":\"" + tpin + "\"," + 
      "\"BusinessCertificateNumber\":\"" + cNo + "\"," + 
      "\"TradingName\":\"" + tName + "\"," + 
      "\"BusinessRegistrationDate\":\"" + bRDate + "\"," + 
      "\"MobileNumber\":\"" + mNo + "\"," + 
      "\"Email\":\"" + email + "\"," + 
      "\"PhysicalLocation\":\"" + location + "\"," + 
      "\"Username\":\"" + this.candidateid + "\"," + 
      "}";

    this.client = ClientBuilder.newClient(new ClientConfig());
    this.target = this.client.target("https://www.mra.mw/sandbox/programming/challenge/webservice/Taxpayers/add");
    this.builder = this.target.request(new String[] { "application/json" });
    this.builder.header("Content-Type", "=application/json:");
    this.builder.header("candidateid", this.candidateid);
    this.builder.header("apikey", this.apikey);
    this.response = this.builder.post(Entity.json(param));
    this.result = ((String)this.response.readEntity(String.class));
    this.object = new JSONParser().parse(this.result);
    this.json = ((JSONObject)this.object);

    if (this.json.size() == 3) {
      temp = false;
    }
    else
    {
      temp = true;
    }
    return temp;
  }

  public boolean updateTaxpayer(String tpin, String cNo, String tName, String bRDate, String mNo, String email, String location)
    throws ParseException
  {
    boolean temp = false;
    String param = "{\"TPIN\":\"" + tpin + "\"," + 
      "\"BusinessCertificateNumber\":\"" + cNo + "\"," + 
      "\"TradingName\":\"" + tName + "\"," + 
      "\"BusinessRegistrationDate\":\"" + bRDate + "\"," + 
      "\"MobileNumber\":\"" + mNo + "\"," + 
      "\"Email\":\"" + email + "\"," + 
      "\"PhysicalLocation\":\"" + location + "\"," + 
      "\"Username\":\"" + this.candidateid + "\"," + 
      "}";
    this.client = ClientBuilder.newClient(new ClientConfig());
    this.target = this.client.target("https://www.mra.mw/sandbox/programming/challenge/webservice/Taxpayers/edit");
    this.builder = this.target.request(new String[] { "application/json" });
    this.builder.header("Content-Type", "=application/json:");
    this.builder.header("candidateid", this.candidateid);
    this.builder.header("apikey", this.apikey);
    this.response = this.builder.post(Entity.json(param));
    this.result = ((String)this.response.readEntity(String.class));
    this.object = new JSONParser().parse(this.result);
    this.json = ((JSONObject)this.object);
    if (this.json.size() == 3) {
      temp = false;
    }
    else
    {
      temp = true;
    }
    return temp;
  }

  public boolean checkConnection()
  {
    try
    {
      URL url = new URL("https://www.mra.mw/sandbox/programming/challenge/webservice/");
      URLConnection connection = url.openConnection();
      connection.connect();
      return true;
    }
    catch (IOException e) {
    }
    return false;
  }

  public boolean checkTpin(String tpin)
    throws ParseException
  {
    boolean exists = false;
    int x = 0;
    while (x < this.rows) {
      if (getTaxpayers()[x][0].equals(tpin)) {
        exists = true;
      }
      x++;
    }

    return exists;
  }
}

