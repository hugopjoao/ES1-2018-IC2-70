import static org.junit.Assert.*;

import org.junit.Test;

public class MailTest {

	@Test
	public void test() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		Mail m =new Mail("Ricardo","iscte",gui);
		String output= m.username();
		assertEquals("Ricardo",output);
	}
	
	public void test1() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		Mail m =new Mail("Ricardo","iscte",gui);
		String output= m.password();
		assertEquals("iscte",output);
	}

}
