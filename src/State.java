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

	public void addTransition(String condition, int destination) {
		this.condition.add(condition);
		this.outgoing.add(destination);	
	}
	
	public void printStateInfo() {
		System.out.println(stateName);
		if(initial)
			System.out.print("Is initial ");
		if(afinal)
			System.out.print("Is a final ");
		System.out.println();
	}



}
