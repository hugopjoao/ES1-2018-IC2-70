import static org.junit.Assert.*;

import org.junit.Test;

import com.restfb.Facebook;

public class FacebookTest {

	@Test
	public void test() {
		Interface_Grafica gui = new Interface_Grafica();//secalhar tenho de meter a interface em publico
		Facebook test= new Facebook("Ricardo", gui);
		String output=test.value();//ir buscar o nome ricardo
		assertEquals("Ricardo",output);
	}

}
