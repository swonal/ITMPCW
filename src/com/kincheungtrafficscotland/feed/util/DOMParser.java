//Kin Yick Cheung S1302770
package com.kincheungtrafficscotland.feed.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.StrictMode;
import android.util.Log;

public class DOMParser {


	public String getXmlFromUrl(String urlString)throws IOException{
		String result = "";
		InputStream anInStream = null;
    	int response = -1;
    	URL url = new URL(urlString);
    	URLConnection conn = url.openConnection();
    	
	
    	// Check that the connection can be opened
    	if (!(conn instanceof HttpURLConnection))
    			throw new IOException("Not an HTTP connection");
    	try
    	{
    		// Open connection
    		HttpURLConnection httpConn = (HttpURLConnection) conn;
    		httpConn.setAllowUserInteraction(false);
    		httpConn.setInstanceFollowRedirects(true);
    		httpConn.setRequestMethod("GET");
    		try{
    		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    		StrictMode.setThreadPolicy(policy);
    		httpConn.connect();
    		}catch (Exception e){
    			e.printStackTrace();
    		}

    		response = httpConn.getResponseCode();
    		
    		// Check that connection is Ok
    		if (response == HttpURLConnection.HTTP_OK)
    		{
    			// Connection is Ok so open a reader 
    			anInStream = httpConn.getInputStream();
    			InputStreamReader in= new InputStreamReader(anInStream);
    			BufferedReader bin= new BufferedReader(in);
    			
    			// Read in the data from the RSS stream
    			String line = new String();
    			// Read past the RSS headers
    			bin.readLine();
    			// Keep reading until there is no more data
    			while (( (line = bin.readLine())) != null)
    			{
    				result = result + "\n" + line;
    			}
    		}
    	}                
    	catch (Exception ex)
    	{
    			throw new IOException("Error connecting");
    	}
    	
    	return result;
    	
    }
		
		
	public Document getDomElement(String xml){
		Document doc = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			DocumentBuilder db = dbf.newDocumentBuilder();

			InputSource is = new InputSource();
		        is.setCharacterStream(new StringReader(xml));
		        doc = db.parse(is); 

			} catch (ParserConfigurationException e) {
				Log.e("Error: ", e.getMessage());
				return null;
			} catch (SAXException e) {
				Log.e("Error: ", e.getMessage());
	            return null;
			} catch (IOException e) {
				Log.e("Error: ", e.getMessage());
				return null;
			}

	        return doc;
	}
	

	 public final String getElementValue( Node elem ) {
	     Node child;
	     if( elem != null){
	         if (elem.hasChildNodes()){
	             for( child = elem.getFirstChild(); child != null; child = child.getNextSibling() ){
	                 if( child.getNodeType() == Node.TEXT_NODE  ){
	                     return child.getNodeValue();
	                 }
	             }
	         }
	     }
	     return "";
	 }
	 

	 public String getValue(Element item, String str) {		
			NodeList n = item.getElementsByTagName(str);		
			return this.getElementValue(n.item(0));
		}
}

