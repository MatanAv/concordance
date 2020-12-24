package concordance;

public class Word {

	protected String value;
	protected LinkedList lines;
	
	public Word(String value, int line) {
		this.value = value;
		lines = new LinkedList(new Node());
		lines.head.data = line;
	}
	
	public void addLine(int line) {			// O(1)
		
		if (!(lines.last.data == line)) {
			
			Node newLine = new Node(line);
			lines.last.next = newLine;
			lines.last = newLine;
			
		}
		
	}
	
	public String printLines() {		// O(n)
		
		StringBuffer sb = new StringBuffer();
		Node itr = lines.head;
		
		while (itr != null) {
			
			if (itr.next != null)
				sb.append(itr.data + ", ");
			else
				sb.append(itr.data);
			
			itr = itr.next;
			
		}
		
		return sb.toString();
		
	}
	
}
