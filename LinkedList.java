package semester2;
public class LinkedList
{
	ListNode head;// Reference to the first Node in the list
	
	//creating a constructor
	public LinkedList()
	{
		this.head=null;
	}
	//a constructor function with a parameter of integer 
	//that creates a class with that number of nodes and changing the value in the middle to true or 1.
	
	public LinkedList(int size) //specify the number of cell in the automation
	{
		//Setting the Linked list with 'Size' numbers of nodes
		if(size<=0) 
		{
			throw new IllegalArgumentException("The size must be a postive integer");
		}
		
		//Creating the first Node
		head=new ListNode(0);
		ListNode current=head;
		
		//Creating the remaining nodes
		for(int i=1; i<size; i++) 
		{
			current.next=new ListNode(0);
			current.next.previous=current;
			current=current.next;
		}
		
		//Setting the value of the middle node to be 1
		int middle=size/2;
		current=head;
		
		for(int i=0; i<middle; i++) 
		{
			current=current.next;
		}
		current.value=1;
		
	}
	
	//Adding to the head of the linked list
	public void addToHead(int data) {
		ListNode newNode= new ListNode(data);
		
		if(head==null) {
			this.head=newNode; // meaning if the list is empty 
		}
		else {
			newNode.next=head;
			head.previous=head;
			head=newNode;
		}
	}
	
	public void add(String value) 
	  {
		    int intValue;
		    try {
		        intValue = Integer.parseInt(value);
		    } catch (NumberFormatException e) {
		        System.err.println("Error: String value cannot be parsed as an integer");
		        return; 
		    }
		    
		    // Create a new node with the parsed integer value
		    ListNode newNode = new ListNode(intValue);
		    
		    if (head == null) {
		        head = newNode; // Set head to the new node if list is empty
		    } else {
		        newNode.next = head;
		        head.previous = newNode;
		        head = newNode;
		    }
		}
	public int rule30(int left, int center, int right) {
        // Implement Rule 30 logic here
        if ((left == 1 && center == 1 && right == 1) ||
            (left == 1 && center == 1 && right == 0) ||
            (left == 1 && center == 0 && right == 1) ||
            (left == 0 && center == 0 && right == 0)) {
            return 0;
        } else {
            return 1;
        }
    }
	
	// a calculate() function that will calculate next generation of cells according to current elements of the list
	public LinkedList calculateNextGeneration() {
	    LinkedList nextGeneration = new LinkedList();
	    ListNode current = head;
	    ListNode nextCurrent = null; // Initialize nextCurrent to null

	    while (current != null) {
	        int leftState = current.previous != null ? current.previous.value : 0;
	        int currentState = current.value;
	        int rightState = current.next != null ? current.next.value : 0;

	        int nextState = rule30(leftState, currentState, rightState);

	        // Add the nextState to the next generation
	        if (nextCurrent == null) {
	            nextGeneration.head = new ListNode(nextState);
	            nextCurrent = nextGeneration.head;
	        } else {
	            nextCurrent.next = new ListNode(nextState);
	            nextCurrent.next.previous = nextCurrent;
	            nextCurrent = nextCurrent.next; // Move nextCurrent to the newly created node
	        }

	        current = current.next;
	    }

	    return nextGeneration;
	}	 
	//Clear Function it reset all cells to zero
	public void clear() 
	{
		//Looping through each node and setting the value to Zero
		ListNode current=head;
		while(current!=null)
		{
			current.value=0;
			current=current.next;
		}
	}
	
	//print info function
	  public String printInfo() 
	  {
			String temp="";
	        ListNode current = head;
	        while (current != null) 
	        {
	            System.out.print(current.value + " ");
				temp+=current.value;
	            current = current.next;
	        }
	        System.out.println();
			return temp;
	    }

	}