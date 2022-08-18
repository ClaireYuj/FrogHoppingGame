package hoppers;

import java.util.*;

public class Record{
	Stack<Integer> records;

	
	public Record(){
		records=new Stack<Integer>();
		records.push(10);
		
		System.out.println(records.pop());
		}
}
