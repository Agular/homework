public class Kodutoo01{
	public static void main(String args[]){
		Student st1 = new Student("Hektor");
		Student st2 = new Student("Hugo");
		Student st3 = new Student("Aleksander");
		
		st1.addPoints(5);
		st2.addPoints(47);
		st3.addPoints(37);
		
		st1.setYearOfBirth(1996);
		st2.setYearOfBirth(1986);
		st3.setYearOfBirth(1976);
		
		System.out.println("Hektor: " + st1.getPoints() + "  YOB:" + st1.getYearOfBirth());
		System.out.println("Hugo: " + st2.getPoints() + "  YOB:" + st2.getYearOfBirth());
		System.out.println("Aleksander: " + st3.getPoints() + "  YOB:" + st3.getYearOfBirth());
		
		Student student = new Student("Achilles", "IAPB");
		student.addPoints(9000);
		student.setYearOfBirth(50);
		
		System.out.println("Achilles: Points - " + student.getPoints() + " YOB - " + student.getYearOfBirth() + " Studycourse - " + student.getStudycourse());
	}
}