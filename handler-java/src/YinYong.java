
public class YinYong {

	public static void main(String[] args){
		Student s = new Student();
		s.setAge(10);
		s.setName("Handler");
		YinYong yy = new YinYong();
		yy.changeStudent(s);
		System.out.println(s.getName());
		int age = 10;
		yy.changeAge(age);
		System.out.println(age);
		
		String name = "dd";
		String name1 = "dd";
		System.out.println(name == name1);
		
		
	}
	
	public void changeStudent(Student student){
		student.setName("Handler-changed");
	}
	
	public void changeAge(int age){
		age = 12;
	}
	
	public void changeName(String name){
		name = "Handler-changed";
	}
}
