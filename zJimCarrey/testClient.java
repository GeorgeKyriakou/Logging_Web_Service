package zJimCarrey;

import java.io.FileNotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class testClient {

	public static void main(String[] args) throws FileNotFoundException {

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:3000");
		String arr = target.request(MediaType.TEXT_XML).get(String.class);
		final String[] array = arr.split("</br>");
		
		String[] time = new String[array.length];
		String[] date= new String[array.length];
		for (int i = 0; i < array.length; i++){
			
			time[i] = array[i].substring(array[i].indexOf("T") + 1, array[i].indexOf("Z"));
			date[i] = array[i].substring( 0 , array[i].indexOf("T"));
			
			System.out.println(i+") "+ time[i]+"/ "+date[i]);
		}
		


	 new motionDetectedChart("AXIS Event Notifier",time,date);

	}

}
