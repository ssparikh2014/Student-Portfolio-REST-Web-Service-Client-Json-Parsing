/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentportfolioclient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import java.util.Set;
import javax.json.*;
import javax.json.JsonValue.ValueType;
public class StudentPortfolioClient {

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) {

	  try {

		URL url = new URL("http://localhost:8080/StudentPortfolioWeb/webresources/entity.student/15MCA01");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");
                
		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));
                
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
                        System.out.println("\n");
                }       
                  // Code to parse the JSON Data.
		       JsonReader rdr = Json.createReader(br);
                       JsonObject ja = rdr.readObject();
                        //System.out.println(ja.size());
                       Set<Entry<String, JsonValue>> s = ja.entrySet();
                       System.out.println(s.size());
                       Iterator<Entry<String, JsonValue>> i = s.iterator();
                       while(i.hasNext()) {
                           Entry<String, JsonValue> e = i.next();
                           if(e.getValue().getValueType().equals(ValueType.OBJECT)) {
                               JsonObject jo = (JsonObject) e.getValue();
                                Set<Entry<String, JsonValue>> s1 = jo.entrySet();
                                System.out.println(s1.size());
                                Iterator<Entry<String, JsonValue>> i1 = s1.iterator();
                                 while(i1.hasNext()) {
                                     Entry<String, JsonValue> e1 = i1.next();
                                     System.out.println("Key: "+ e1.getKey());
                                     System.out.println("Value: "+e1.getValue());
                                 }
                           }
                           else {
                           System.out.println(e.getKey());
                           System.out.println(e.getValue());
                           }
                       }
                       
                       
                        
                        conn.disconnect();
 	

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	}

}