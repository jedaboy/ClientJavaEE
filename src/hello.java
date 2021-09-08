import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ejb.PersonBeanRemote;
//import message.HelloWorld;
//import message.HelloWorldRemote;
import model.Person;

public class hello {

	public static void main(String[] args) throws NamingException {

		Scanner sc = new Scanner(System.in);
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.ibm.websphere.naming.WsnInitialContextFactory");
		env.put(Context.PROVIDER_URL, "iiop://localhost:2811");
		Context initialContext = new InitialContext(env);
		int option = 0;

		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		PersonBeanRemote hw = (PersonBeanRemote) initialContext.lookup("ejb/PersonRemote");

		Person p = new Person();
		String nome;
		String data;
		Long id;

		do {
			try {
				System.out.println("Escolha uma das opções abaixo" + "\n" + "1 - inserir usuario" + "\n"
						+ "2 - atualizar usuario" + "\n" + "3 - deletar usuario" + "\n" + "4 - Consultar usuario" + "\n"
						+ "5 - Encerrar");
				sc.nextLine();
				option = sc.nextInt();
				switch (option) {

				case 1:
					System.out.println("Informe o nome do novo usuario");
					nome = sc.next();
					System.out.println("Informe a data de nascimento do novo usuario");
					data = sc.next();

					p.setName(nome);
					p.setBirthdayDate(sdf.parse(data));

					p = hw.save(p);

					System.out.println("usuario cadastrado " + p.getId());

					break;

				case 2:
					try {
						System.out.println("Informe o ID para ser atualizado");
                        p = null;
						id = sc.nextLong();

						p = hw.queryById(id);

						nome = sc.next();
						data = sc.next();

						p.setName(nome);
						p.setBirthdayDate(sdf.parse(data));
						p = hw.save(p);

						System.out.println(p.getId());
						System.out.println("Cadastro atualizado!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("Erro ao atualizar dados do usuario!");
					}
					break;
				case 3:
					System.out.println("Informe o ID do Usuario a ser deletado");
					id = sc.nextLong();
					hw.remove(id);
					System.out.println("Usuario Removido!");
					break;
				case 4:

					System.out.println("Informe o ID desejado");
					id = sc.nextLong();
					p = hw.queryById(id);
					System.out.println(p.getName() + " " + sdf.format(p.getBirthdayDate()));
					break;
				case 5:
					System.out.println("arrivederci");

					break;
				default:
					System.out.println("error!");
				}
			} catch (Exception e) {
				
				System.out.println("entrada invalida");
			}

		} while (option != 5);

		System.out.println("Fim");
	}

}
