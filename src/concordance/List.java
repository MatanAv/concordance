package concordance;

public class List {
	
	private final int ENLARGE_FACTOR = 2;
	private Word[] arr;
	private int currentSize;
	
	public List() {
		arr = new Word[1];
		currentSize = 0;
	}
	
	
	// ---- QuickSort ---- //
	
	public void sort() {			// O(n lg n)
		sort(0, arr.length-1);
	}
	
	private void sort(int low, int high) {	
		
		if (low < high) {
			
			int p = partition(low, high);
			sort(low, p-1);
			sort(p+1, high);
		
		}
		
	}
	
	private int partition(int low, int high) {

		int pivot = high;  
		int i = (low-1);			// index of smaller element 

		for (int j = low; j < high; j++) { 
			
			if (arr[j].value.compareToIgnoreCase(arr[pivot].value) < 0) {			// If current element is smaller than the pivot 
				i++; 
				swap(i, j);
			} 
		} 

		swap(i+1, pivot);

		return i+1;

	} 
	
	private void swap(int a, int b) {
		
		Word temp = arr[a];
		
		arr[a] = arr[b];
		arr[b] = temp;

	}
	
	
	// ------ //
	
	
	public void add(Word word) {		// O(1)
		
		if (currentSize == (arr.length-1))
			EnlargeArray();
		
		arr[currentSize++] = word;
		
	}
	
	public Word get(int i) {		// O(1)
		
		if (i < arr.length)
			return arr[i];
		else
			return null;
		
	}
	
	public void remove(int i) {
		
		arr[i] = null;
		currentSize--;

	}
	
	public int size() {
		return currentSize;
	}
	
	public Word find(String word) {		// O(n)
		
		for (int i = 0; i < arr.length && arr[i] != null; i++)
			if (arr[i].value.equalsIgnoreCase(word))
				return arr[i];
		return null;
		
	}
	
	public boolean isEmpty() {
		
		if (currentSize == 0)
			return true;
		else
			return false;
		
	}
	
	public void trimToSize() {		// O(n)
		
		Word[] newArr = new Word[currentSize];
		
		for (int i = 0; i < newArr.length; i++)
			newArr[i] = arr[i];
		
		arr = newArr;
		
	}
	
	private void EnlargeArray() {		// O(n)
		
		Word[] temp = new Word[arr.length * ENLARGE_FACTOR];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
		
	}


	@Override
	public String toString() {			// O(n*k)	lines*words
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < arr.length; i++)
			sb.append(i+1 + "." + "\t" + arr[i].value + "\t\t\t\t" + "lines:" + "\t" + arr[i].printLines() + "\n");
		
		return sb.toString();
		
	}
	
	
	
}
