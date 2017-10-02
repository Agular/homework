public class Student{
	
	String name;
	int points;
	int yearOfBirth;     // lisades static parameetri, muudame me selle muutuja globaalseks muutujaks, mis oleks k6ikidel klassidel ühine
	String studycourse;  // see tähendab, et iga selle parameetri muudatus on kõikides objektides nähtav, st k6ikile objektidele rakendub see muudatus.
	
	public Student(String name){
		this.name = name;
		points = 0;
		yearOfBirth = -1;
		studycourse = "";
	}
	
	public Student(String name, String studycourse){
		this.name = name;
		points = 0;
		yearOfBirth = -1;
		this.studycourse = studycourse;
	}
	
	public void addPoints(int pointsToAdd){
		points += pointsToAdd;
	}
	
	public int getPoints(){
		return points;
	}
	
	public void setYearOfBirth(int yearOfBirth){
		this.yearOfBirth = yearOfBirth;
	}
	
	public int getYearOfBirth(){
		return yearOfBirth;
	}
	
	public void setStudycourse(String studycourse){
		this.studycourse = studycourse;
	}
	
	public String getStudycourse(){
		return studycourse;
	}
}