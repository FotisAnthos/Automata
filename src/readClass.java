import java.io.*;
import java.util.*;


public class readClass {
	ArrayList<State> states;
	ArrayList<String> language;
	int transitionLength;

	public readClass(String inputLocation) {
		language = new ArrayList<String>();//contains all words that can cause a transition
		language.add("@");
		if(!retrieve(inputLocation))
			System.out.println("Read error");
	}

	public boolean retrieve(String inputLocation) {

		//open file
		try {
			String temp;
			FileReader fileReader = new FileReader(inputLocation);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			//file read
			int noOfStates = Integer.valueOf(bufferedReader.readLine());//Always 1 number -- 1st line


			temp = bufferedReader.readLine();//2nd line
			String[] starts = temp.split(" ");
			int noOfStarts = starts.length;

			int noOfEnds = Integer.valueOf(bufferedReader.readLine());//Always 1 number -- 3rdline
			temp = bufferedReader.readLine();//4th line
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

			int noOfTransitions = Integer.valueOf(bufferedReader.readLine());//Always 1 number -- 5th line

			//transition information to states
			transitionLength = 0;
			for(i=0;i<noOfTransitions;i++) {
				temp = bufferedReader.readLine();

				String[] templist = temp.split(" ");

				int sourceState = Integer.valueOf(templist[0]);
				String tempCharacter = templist[1];//transition variable
				if (tempCharacter.length() > transitionLength) transitionLength = tempCharacter.length();
				if(!language.contains(tempCharacter)) language.add(tempCharacter);
				int destination = Integer.valueOf(templist[2]);


				for(State state : states) {
					if(state.getStateName() == sourceState) {
						//System.out.println(sourceState + "|" + tempCharacter + "|" + destination);
						state.addTransition(tempCharacter, destination);
						break;
					}
				}
			}
			/*Debugging part
			System.out.println(noOfStates + " : Number of States");
			System.out.println(noOfStarts + " : Number of Initial States");
			System.out.println(noOfEnds + " : Number of Ending States");
			System.out.println(noOfTransitions + " : Number of Transitions");
			for(State state : states) {
				state.printStateInfo();
			}
			System.out.println(transitionLength);
			*/
			
			//Closing the door
			bufferedReader.close();
			fileReader.close();
			return true;
		}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" +inputLocation + "'");    
			return false;
		}
		catch(IOException ex) {
			System.out.println("Error reading file '" + inputLocation + "'");                  
			ex.printStackTrace();
			return false;
		}   
		
	}

	public ArrayList<State> getStates() {
		return states;
	}

	public ArrayList<String> getLanguage() {
		return language;
	}

	public int getTransitionLength() {
		return transitionLength;
	}
	
	

}
