/* import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays; */
import java.util.*;


class lamport1{
	static Scanner scan=new Scanner(System.in);
	
	public static void main(String args[]){
		
		Map<String,ArrayList<String>> myMap = takeInput();
		
		
		//for checking correctness of inputs taken
		/* for (Map.Entry<String,ArrayList<String>> entry : myMap.entrySet()){
			
			ArrayList<String> myMap1=entry.getValue();
			
			for(String commands:myMap1) {
				System.out.println(commands);
			}
			
			System.out.println();
			
			//System.out.println("Key = " + entry.getKey() + ", Value = " + Arrays.toString(entry.getValue()));
			
		}
		*/
		
		printOutput(myMap);
		
		
		
	}
	static Map<String,ArrayList<String>> takeInput(){
		
		String input;
	
		Map<String,ArrayList<String>> myMap = new HashMap<String,ArrayList<String>>(); 
		
		while(true){
			input=scan.nextLine();
			ArrayList<String> a = new ArrayList<>();
			
			if(input.indexOf("begin")!=0){
				System.out.println("Input taken successfully");
				break;		
				
			}
			else{
				String process=input.substring(14);
				
				while(true){
					input=scan.nextLine();
					if(input.indexOf("end")==0){
						myMap.put(process,a);
						break;
						
					}
					a.add(input);
						
				}
				
				if(input.indexOf("end")==0) 
					continue;
			}
			
		}
		
		return myMap;
		
	}
	
	static void printOutput(){
		
	}
	
	
	
}