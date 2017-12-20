import java.util.*;

public class Core {

	private ArrayList<State> states;
	private ArrayList<State> currStates;
	private ArrayList<ArrayList<State>> allCurrStates;
	private ArrayList<String> language;
	private ArrayList<String> userInput;
	private int transitionLength;
	private boolean isFinal;

	public Core(ArrayList<State> states, ArrayList<String> language, int transitionLength) {
		//Transporting states list
		this.states = new ArrayList<State>();
		this.states = states;
		this.language = new ArrayList<String>();
		this.language = language;
		this.transitionLength = transitionLength;
		this.isFinal = false;
		this.userInput = new ArrayList<String>();
		//setting the current states
		this.currStates = new ArrayList<State>();
		this.allCurrStates = new ArrayList<ArrayList<State>>();
		for(State state : states) {
			//state.printStateInfo();
			if(state.isInitial()) {
				currStates.add(state);
				if(state.isAfinal()) {
					isFinal = true;
				}
			}
		}
		allCurrStates.add(currStates);
		System.out.println("Transition Length:" + transitionLength);

	}

	public String addition(String aChar) {
		userInput.add(aChar);
		ArrayList<String> temp = expressions();
		wordCheck(temp);//checks if the expressions cause any transitions	
		return currentStatesInfo();
	}

	public String deletion() {
		userInput.remove(userInput.size()-1);//remove the last character
		allCurrStates.remove(allCurrStates.size()-1); //remove the last currState
		
		//System.out.println(userInput.toString());
		return currentStatesInfo();
	}


	private ArrayList<String> expressions(){
		ArrayList<String> expressions = new ArrayList<String>();//expressions will be created from what the user has inputed and sent for checking
		for(int i = 0; i < transitionLength; i++) {
			String exp = ""; 
			int count = 0;
			if(i<userInput.size()) {
				for(int j = (userInput.size()-1) - i; j< userInput.size(); j++) {
					exp += userInput.get(j);
					count++;
					if(count>i)
						break;
				}
			}
			if(!exp.equals("")) {
				expressions.add(exp);
			}
		}
		System.out.println("Expressions: " + expressions.toString());
		return expressions;
	}

	private void wordCheck(ArrayList<String> expressions) {//addition of a character-string to check
		ArrayList<Integer> trans = new ArrayList<Integer>();
		ArrayList<State> tempCurrStates = new ArrayList<State>();
		
		for(String exp : expressions) {//for each expression
			ArrayList<State> currStates = allCurrStates.get(allCurrStates.size() - exp.length());
			for(State st : currStates) {//in each state
				trans = st.Transition(exp); //get all transitions from the current state for the specific expression exp
				if(!trans.isEmpty()) {//if there are transitions
					for(Integer outName : trans) {//for each transition put the resulting state on the current states
						boolean check = false;
						for(State tempState : states) {//search the states, when you find the state with the name == outName (should be integers in this case) 
							if(tempState.getStateName() == outName) {
								check = true;//a state with the specific name is found
								if(!tempCurrStates.contains(tempState)) {
									tempCurrStates.add(tempState);
								}
							}
						}
						if(!check) {//check if the state was found or not
							System.err.println("State not found in states --Core -wordCheck");
						}
					}
				}
			}
			if(tempCurrStates.isEmpty()) System.out.println("tempCurrStates is empty, no current states created --Core -wordCheck");
			allCurrStates.add(tempCurrStates);
		}
		isFinal = statusCheck(allCurrStates.get(allCurrStates.size()-1));//update the "finality" of transitions
	}

	private boolean statusCheck(ArrayList<State> tempCurrStates){ //return true if there is a final state in the current states 
		boolean isFinal = false;
		for(State state : tempCurrStates) {
			if(state.isAfinal())
				isFinal = true;
		}
		return isFinal;
	}

	public String currentStatesInfo() {
		String temp = "";
		for(State st : allCurrStates.get(allCurrStates.size()-1)) {
			temp +=  st.getStateName() + "  ";
		}
		return temp;
	}

	public ArrayList<String> getLanguage() {
		return language;
	}

	public boolean getFlag() {
		return isFinal;
	}

}
