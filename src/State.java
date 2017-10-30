import java.util.ArrayList;
import java.util.List;

public class State {
	private boolean initial;
	private boolean afinal;
	private List<Integer> outgoing;
	private List<String> condition;

	public State(boolean initial, boolean afinal) {
		super();
		this.initial = initial;
		this.afinal = afinal;
		this.outgoing = new ArrayList<Integer>();
		this.condition = new ArrayList<String>();

	}

	public void addTransition(String condition, int destination) {
		this.condition.add(condition);
		this.outgoing.add(destination);	
	}



}
