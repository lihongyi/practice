import java.util.*;

public class Practice3{

	public class Employee{
		private int satisfaction;
		private LinkedList<Employee> subordinates;
		public Employee(int satisfaction){
			this.satisfaction = satisfaction;
			this.subordinates = new LinkedList<Employee>();
		}

		public void addWorker(Employee employee){
			this.subordinates.add(employee);
		}

		public LinkedList<Employee> getWorkers(){
			return this.subordinates;
		}
	}

	public int bestEmployeePath(Employee root){
		if(root == null){
			return 0;
		} else {
			int best = 0;
			for(Employee e : root.getWorkers()){
				int pathVal = bestEmployeePath(e);
				if(pathVal > best){
					best = pathVal;
				}
			}
			return best + root.satisfaction;
		}
	}

	public int bestBosslessEmployeeSatisfaction(Employee root){
		
	}



	public int letterToNumber(char letter){
		switch(letter){
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default: return -1000000;
		}
	}

	public int romanToArabic(String roman){
		int result = 0;
		int lastValue = 0;
		for(int i = roman.length()-1; i >= 0; i--){
			char letter = roman.charAt(i);
			int currValue = letterToNumber(letter);
			if(lastValue > currValue){
				result -= currValue;
				lastValue = currValue;
			} else {
				result += currValue;
				lastValue = currValue;
			}
		}
		return result;
	}

	
	

 
	public static void main(String[] args){
		System.out.println("hello world\n=====================");
		Practice3 prac = new Practice3();
		String roman = "MCDLXIV";
		System.out.println("ROMAN FOR "+roman + " is "+prac.romanToArabic(roman));
		System.out.println("===wealthfront employee satisfaction question");
		Employee e1 = prac.new Employee(8);
		Employee e2 = prac.new Employee(5);
		Employee e3 = prac.new Employee(2);
		Employee e4 = prac.new Employee(3);
		e1.addWorker(e2); e1.addWorker(e3); e1.addWorker(e4);
		Employee e5 = prac.new Employee(4);
		Employee e6 = prac.new Employee(7);
		Employee e7 = prac.new Employee(6);
		e2.addWorker(e5); e2.addWorker(e7); e5.addWorker(e6);
		Employee e8 = prac.new Employee(3);
		Employee e9 = prac.new Employee(4);
		Employee e10 = prac.new Employee(1);
		e4.addWorker(e10); e7.addWorker(e8); e7.addWorker(e9);
		System.out.println("maxEmployeePath: " + prac.bestEmployeePath(e1)); 

	}


}