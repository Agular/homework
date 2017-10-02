import java.util.HashMap;

public class StudentProc{
	
	public static HashMap<String, Integer> students = new HashMap<String, Integer>();
	
	public static void addStudent(String name){
		students.put(name, 0);
	}
	
	public static void addPoints(String studentName, int pointsToAdd){
		if(students.containsKey(studentName)){
			students.put(studentName, students.get(studentName) + pointsToAdd);
		}
	}
	
	public static int getPoints(String studentName){
		return students.get(studentName);
	}
}