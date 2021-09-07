import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.PersonBeanRemote;
//import message.HelloWorld;
//import message.HelloWorldRemote;
import model.Person;



public class hello {

	public static void main(String[] args) throws Exception {
		
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
		     "com.ibm.websphere.naming.WsnInitialContextFactory");
		env.put(Context.PROVIDER_URL, "iiop://localhost:2811");
		Context initialContext = new InitialContext(env);
		
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		
		PersonBeanRemote hw = (PersonBeanRemote)initialContext.lookup("ejb/PersonRemote");
		Person p = new Person();
		p.setName("Jedaias");
		p.setBirthdayDate(sdf.parse("19/03/1998"));
		
		p = hw.save(p);
		System.out.println(p.getId());
		
	}

}
