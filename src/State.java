import java.util.ArrayList;
import java.util.List;

public class State {
	private boolean initial;
	private boolean afinal;
	private int stateName;
	private List<Integer> outgoing;
	private List<String> condition;

	public State(boolean initial, boolean afinal, int stateName) {
		super();
		this.stateName = stateName;
		this.initial = initial;
		this.afinal = afinal;
		this.outgoing = new ArrayList<Integer>();
		this.condition = new ArrayList<String>();

	}

	public void addTransition(String tempCharacter, int destination) {
		this.condition.add(tempCharacter);
		this.outgoing.add(destination);	
	}

	public void printStateInfo() {//helps with debugging
		System.out.print("\n *** \n" + stateName);
		if(initial)
			System.out.print(" Is initial ");
		if(afinal)
			System.out.print(" Is a final ");
		System.out.println();
		int i;
		for(i=0;i<outgoing.size();i++) {
			System.out.println(stateName + "|" + condition.get(i) + "|" + outgoing.get(i));
		}


	}

	public int getStateName() {
		return stateName;
	}
	
	private void Transition() {
		//TODO

	}




}
