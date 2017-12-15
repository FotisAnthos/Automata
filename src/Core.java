import java.util.*;


public class Core {

	ArrayList<State> states;
	ArrayList<State> currStates;
	ArrayList<String> language;
	ArrayList<String> expressions;
	int transitionLength, textLength;
	boolean flag;

	public Core(ArrayList<State> states, ArrayList<String> language, int transitionLength) {
		//Transporting states list
		this.states = new ArrayList<State>();
		this.states = states;
		this.language = new ArrayList<String>();
		this.language = language;
		this.transitionLength = transitionLength;
		this.flag = false;
		this.expressions = new ArrayList<String>();
		//setting the current states
		this.currStates = new ArrayList<State>();
		for(State state : states) {
			//state.printStateInfo();
			if(state.isInitial()) {
				currStates.add(state);
				if(state.isAfinal()) {
					flag = true;
				}
			}
		}

	}

	public void addition(String aChar) {
		expressions.add(aChar);
		System.out.println(expressions.toString());
	}
	
	public void deletion() {
		expressions.remove(expressions.size()-1);
		System.out.println(expressions.toString());
	}

	private void expressionsArrangement() {//TODO actually test this shite
		//this *should* arrange the strings-expressions that must be checked and send them for transition checks
		ArrayList<String> tempExpressions = new ArrayList<String>(expressions); 
		for(int i=0; i<expressions.size(); i++) {
			String s = "";
			int index = i;
			int limit = (transitionLength > textLength) ? textLength : transitionLength;
			for(int j =0; j<limit; j++) {
				s.concat(tempExpressions.get(index));
				index += (j+1);
			}
			//wordCheck(s);//checks if the string s is a cause for a transition
			System.out.println(s);
		}

	}


	public void wordCheck(String s) {//addition of a character-string to check
		ArrayList<Integer> trans = new ArrayList<Integer>();
		ArrayList<State> tempCurrStates = new ArrayList<State>();
		for(State state : currStates) {
			currStates.remove(state);

			trans = state.Transition(s); //get all transitions from the current state state 
			if(!trans.isEmpty()) {//if there are transitions
				for(Integer index : trans) {//for each transition put the resulting state on the current states
					if(!tempCurrStates.contains(states.get(index))) {
						tempCurrStates.add(states.get(index));
					}
				}
			}
		}
		if(!currStates.isEmpty()) System.err.println("Core-- wordAddition-- currStates should be empty by now");
		currStates = tempCurrStates;
		flag = statusCheck();//update the "finality" of transitions
	}

	public boolean statusCheck(){ //return true if there is a final state in the current states 
		boolean flag = false;
		for(State state : currStates) {
			if(state.isAfinal())
				flag = true;
		}
		return flag;
	}

	public ArrayList<String> getLanguage() {
		return language;
	}
	
	

}
