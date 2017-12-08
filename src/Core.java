import java.util.*;

public class Core {

	ArrayList<State> states;
	ArrayList<State> currStates;

	public Core(ArrayList<State> states) {
		//Transporting states list
		this.states = states;
		this.currStates = new ArrayList<State>();
		//setting the current states
		int sum = 0;
		for(State state : states) {
			state.printStateInfo();
			if(state.isInitial()) {
				currStates.add(state);
				sum++;
			}
		}
		/*
		if(sum<1)
			System.out.println("initial states <1");
		else if(sum == 1)
			System.out.println("ONE initial state");
		else
			System.out.println("Multiple initial states");
		 */
	}

	public void addition(String s) {
		int index;
		for(State st : currStates) {
			currStates.remove(st);
			index = st.Transition(s);
			if(index > 0) {
				currStates.add(states.get(index));
			}
		}
		// TODO Auto-generated method stub

	}

	private void deletion() {
		// TODO Auto-generated method stub

	}





}
