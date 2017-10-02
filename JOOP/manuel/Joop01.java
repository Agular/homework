public class Joop01 {
	public static void main(String args[]){
		
		System.out.println("See on staatiline meetod, see ei ole subjekt. Uhtlasi on see main meetod.\n");
		
		
		StudentProc.addStudent("Jaanus");
		StudentProc.addStudent("Priit");
		StudentProc.addStudent("Maali");
		
		StudentProc.addPoints("Jaanus", 23);
		StudentProc.addPoints("Priit", 34);
		StudentProc.addPoints("Maali", 30);
		
		System.out.println("Jaanus: " + StudentProc.getPoints("Jaanus"));
		System.out.println("Priit: " + StudentProc.getPoints("Priit"));
		System.out.println("Maali: " + StudentProc.getPoints("Maali"));
		
		System.out.println("\nNyyd me tegeleme objektidega\n");
		
		Student st1 = new Student("Hektor");
		Student st2 = new Student("Hugo");
		Student st3 = new Student("Aleksander");
		
		st1.addPoints(5);
		st2.addPoints(47);
		st3.addPoints(37);
		
		System.out.println("Hektor: " + st1.getPoints());
		System.out.println("Hugo: " + st2.getPoints());
		System.out.println("Aleksander: " + st3.getPoints());
		
		//Lisame juurde sama nimega tudengeid, nt Maali ja Hugo. Teeme seda esmalt staatilise meetodiga.
		//Praeguse meetodil me ülekirjutame Maali ainepunktide väärtuse ning tegelikult on meil endisel 1 Maali.
		//Yhel viisil realiseerimine toimuks, kui me kasutame kahte ArrayListi, kus ühes on nimed ja teised on ainepuntktid.
		//Praeguses versioonis see implementatsioon puudub.
		System.out.println("\nStaatiline meetod");
		
		StudentProc.addStudent("Maali");
		StudentProc.addPoints("Maali", 50);
		System.out.println("Maali: " + StudentProc.getPoints("Maali"));
		
		//Nyyd teeme seda objektorienteeritud viisil, mis on palju LIHTSAM! Me teeme lihtsalt uue objekti.
		System.out.println("\nOOP meetod");
		Student hektor = new Student("Hektor");
		hektor.addPoints(50);
		System.out.println("Esimene Hektor: " + st1.getPoints());
		System.out.println("Teine Hektor: " + hektor.getPoints());
	}
}