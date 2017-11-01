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


			//states creation
			states = new ArrayList<State>();
			int i;
			int j;
			for(i=1;i<=noOfStates;i++) {
				boolean isStart = false;
				boolean isFinal = false;

				for(j=0;j<noOfStarts;j++) {
					if(Integer.valueOf(starts[j]) == i) {
						isStart = true;
					}
				}

				for(j=0;j<noOfEnds;j++) {
					if(Integer.valueOf(ends[j]) == i) {
						isFinal = true;
					}
				}
				states.add(new State(isStart, isFinal, i));
			}
				
			int noOfTransitions = Integer.valueOf(bufferedReader.readLine());//Always 1 number

			//transition information to states
			for(i=0;i<noOfTransitions;i++) {
				temp = bufferedReader.readLine();

				String[] templist = temp.split(" ");

				int sourceState = Integer.valueOf(templist[0]);
				String tempCharacter = templist[1];
				int destination = Integer.valueOf(templist[2]);

				
				for(State state : states) {
					if(state.getStateName() == sourceState) {
						//System.out.println(sourceState + "|" + tempCharacter + "|" + destination);
						state.addTransition(tempCharacter, destination);
						break;
					}
				}
			}
			
			for(State state : states) {
				state.printStateInfo();
			}
			 		
			/*
			System.out.println(noOfStates + " : Number of States");
			System.out.println(noOfStarts + " : Number of Initial States");
			System.out.println(noOfEnds + " : Number of Ending States");
			System.out.println(noOfTransitions + " : Number of Transitions");
			 */
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
