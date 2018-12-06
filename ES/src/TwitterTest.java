import static org.junit.Assert.*;

import org.junit.Test;

import com.restfb.Facebook;

public class TwitterTest {

	@Test
	public void test() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		AppTwitter t= new AppTwitter("78363","iscte","Engenharia","software",gui);
		String output = t.consummerKey();
		assertEquals("78363",output);
		
	}
	
	public void test1() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		AppTwitter t= new AppTwitter("78363","iscte","Engenharia","software",gui);
		String output = t.consumerSecret();
		assertEquals("iscte",output);
		
	}
	
	public void test2() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		AppTwitter t= new AppTwitter("78363","iscte","Engenharia","software",gui);
		String output = t.accessToken();
		assertEquals("Engenharia",output);
		
	}
	
	public void test3() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		AppTwitter t= new AppTwitter("78363","iscte","Engenharia","software",gui);
		String output = t.tokenSecret();
		assertEquals("software",output);
		
	}	
	

}
