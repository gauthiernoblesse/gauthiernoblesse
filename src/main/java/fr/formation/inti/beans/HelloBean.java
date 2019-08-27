package fr.formation.inti.beans;

public class HelloBean {

	public HelloBean() {
	}

	public void Hello() {
		System.out.println("Bonjour");
	}

	public static HelloBean createInstance() {
		System.out.println("création HelloBean par la methode createInstance");
		// cette methode va instancier donc il faut retourner qq chose
		return new HelloBean();
	}

}
