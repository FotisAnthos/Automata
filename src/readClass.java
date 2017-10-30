import java.io.*;
import java.util.*;


public class readClass {
	List<State> states;

	public readClass(String inputLocation) {
		retrieve(inputLocation);

	}




	public boolean retrieve(String inputLocation) {


		//open file
		try {
			String temp;
			FileReader fileReader = new FileReader(inputLocation);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//file read
			int noOfStates = Integer.valueOf(bufferedReader.readLine());//Always 1 number Το αυτόματο έχει τρεις καταστάσεις *\



			temp = bufferedReader.readLine();
			String[] starts = temp.split(" ");
			int noOfStarts = starts.length;

			int noOfEnds = Integer.valueOf(bufferedReader.readLine());//Always 1 number 
			temp = bufferedReader.readLine();
			String[] ends = temp.split(" ");

			states = new ArrayList<State>();
			for(int i=0;i<noOfStates;i++) {
				boolean isStart = false;
				boolean isFinal = false;
				for(int j=0;i<noOfStarts;i++) {
					if(Integer.valueOf(starts[j]) == i)
						isStart = true;
				}
				for(int j=0;i<noOfEnds;i++) {
					if(Integer.valueOf(ends[j]) == i)
						isFinal = true;
				}
				states.add(new State(isStart, isFinal));
			}//states creation

			int noOfTransitions = Integer.valueOf(bufferedReader.readLine());//Always 1 number

			//transition information to states
			for(int i=0;i<noOfTransitions;i++) {
				temp = bufferedReader.readLine();
				String[] templist = temp.split(" ");
				int sourceState = Integer.valueOf(templist[0]);
				states.get(sourceState).addTransition(templist[1], Integer.valueOf(templist[2]));
			}

			System.out.println(noOfStates);
			System.out.println(noOfStarts);
			System.out.println(noOfEnds);
			System.out.println(noOfTransitions);

			// Always close files.
			bufferedReader.close();    
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" +inputLocation + "'");                
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + inputLocation + "'");                  
			ex.printStackTrace();
		}   

		return true;
	}

	public List<State> getStates() {
		return states;
	}

	







}
