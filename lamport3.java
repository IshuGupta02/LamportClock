import java.util.*;

class lamport3{
	static Scanner scan=new Scanner(System.in);
	
	public static void main(String args[]){
		
		Map<String,ArrayList<String>> myMap = takeInput();
		
		printOutput(myMap);
		
	}	
	static Map<String,ArrayList<String>> takeInput(){
		
		String input;
		
		LinkedHashMap<String,ArrayList<String>> myMap = new LinkedHashMap<String,ArrayList<String>>(); 
		
		while(true){
			input=scan.nextLine();
			ArrayList<String> a = new ArrayList<>();
			
			if(input.indexOf("begin")!=0){
				
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
	
	static void printOutput(Map<String,ArrayList<String>> myMap){
	
		int[][] timestamps = new  int[myMap.size()][];
		
		int i=0;
		
		for (Map.Entry<String,ArrayList<String>> entry : myMap.entrySet()){
			
			ArrayList<String> arr_list=entry.getValue();
			
			timestamps[i]=new int[arr_list.size()];
			
			
			for(int j=0;j<arr_list.size();j++) {
				timestamps[i][j]=j;
					
			}
			
			i++;
			
		}
		
		
		i=0;
		
		for (Map.Entry<String,ArrayList<String>> entry : myMap.entrySet()){
			
			ArrayList<String> arr_list=entry.getValue();
			
			
			for(int j=0;j<arr_list.size();j++) {
				if(arr_list.get(j).indexOf("send")==0){
					String send_by=entry.getKey();
					String send_to=arr_list.get(j).substring(5,7);
					String message=arr_list.get(j).substring(8);
					
					int p=0;
					
					for (Map.Entry<String,ArrayList<String>> entry1 : myMap.entrySet()){
						
						if(entry1.getKey().equals(send_to)){
							ArrayList<String> arr_list_1=entry1.getValue();
							
							int q=0;
							
							for(String commands:arr_list_1) {
								if(commands.indexOf("recv")==0&&commands.indexOf(send_by)==5&&commands.indexOf(message)==8){
									if(timestamps[p][q]>timestamps[i][j]){
										
									}
									else{
										int diff=timestamps[i][j]-timestamps[p][q]+1;
										
										for(int s=q;s<arr_list_1.size();s++){
											timestamps[p][s]+=diff;
										}
										
									}
									
								}
								
								q++;
							}
						}
						
						p++;
					}
					
				}
				
					
			}
			
			i++;
			
		}
		
		

		
		for(int r=0; ; r++){
			boolean found=false;
			
			int y=0;
			
			for (Map.Entry<String,ArrayList<String>> entry : myMap.entrySet()){
				
				ArrayList<String> arr_list=entry.getValue();
				
				
				for(int w=0; w<arr_list.size() ; w++) {
					
					if(timestamps[y][w]==r){
						if(arr_list.get(w).indexOf("send")==0){
							System.out.println("sent "+entry.getKey()+" "+arr_list.get(w).substring(8)+" "+arr_list.get(w).substring(5,7)+" "+(r+1));
							found=true;
													
						}
						else if(arr_list.get(w).indexOf("recv")==0){
							System.out.println("received "+entry.getKey()+" "+arr_list.get(w).substring(8)+" "+arr_list.get(w).substring(5,7)+" "+(r+1));
							found=true;
							
						}
						else if(arr_list.get(w).indexOf("print")==0){
							System.out.println("printed "+entry.getKey()+" "+arr_list.get(w).substring(6)+" "+(r+1));
							found=true;
							
						}
					
					}
				
			
				
				}
				y++;
				
			
			}
			
			if(found==false){
				
				int z=0;
			
				for (Map.Entry<String,ArrayList<String>> entry : myMap.entrySet()){
				
					ArrayList<String> arr_list=entry.getValue();
					
					if(timestamps[z][arr_list.size()-1]>r){
						System.out.println("system deadlocked");
						return;
						
					}
				
					z++;
				}
				
				break;
				
			}
				
		
			
		}
	
	
	}
	
	
	
}