public class DList {
	
	public DListNode head;
	public DListNode tail;
	public static int size = 0;

	public DList() {
		head = null;
		tail = null;
		size = 0;
	}

	public DList(int[] a) {
		head = new DListNode();
		tail = head;
		head.item = a;
		size++;
	}

	public DList(int[] a, int[] b) {
		head = new DListNode();
		head.item = a;
		tail = new DListNode();
		tail.item = b;
		head.next = tail;
		size = size + 2;
	}

	public void insertFront(int[] i) {
		DListNode added = new DListNode(i);
		if (head == null) {
			head = added;
			tail = added;
		}else {
			added.next = head;
			head.prev = added;
			head = added;
		}
		size++;
	}

	public void insertEnd(int[] j) {
		DListNode added = new DListNode(j);
		if (head == null) {
			head = added;
			tail = added;
		}else {
			tail.next = added;
			added.prev = tail;
			tail = added;
		}
		size++;
	}

	public void removeFront() {
		if(size != 0) {
			if (size == 1) {
				head = null;
				tail = null;
				size--;
			}else {
				head = head.next;
				head.prev = null;
				size--;
			}
		}
	}

	public String toString() {
		String result = "[ ";
		DListNode current = head;
		while (current != null) {
			result = result + "(" + current.item[0] + "," + current.item[1] + "," + current.item[2] + "," + current.item[3] + ")" + " ";
			current = current.next;
		}
		return result + "]";
	}
	

	public static void main(String[] args) {
		DList l = new DList();
		System.out.println("### TESTING insertFront ###\nEmpty list is " + l);


		int[] testArray = {1,2,3,4};
		int[] testArray2 = {1,3,5,7};
		int[] testArray3 = {2,4,6,8};
		
		l.insertEnd(testArray);
    	System.out.println("\nInserting testArray at end.\nList with testArray is " + l);
    	if (l.head == null) {
      		System.out.println("head is null.");
    	} else {
      		if (l.head.item != testArray) {
        		System.out.println("head.item is wrong.");
      		}
      		if (l.head.prev != null) {
        		System.out.println("head.prev is wrong.");
      		}
 
    	if (l.tail == null) {
    		System.out.println("tail is null");
    	}else {
    		if (l.tail.item != testArray) {
    			System.out.println("tail.item is wrong");
    		}
    		if (l.tail.next != null) {
    			System.out.println("tail.next is wrong");
    		}
    	}
    	if (l.size != 1) {
    		System.out.println("size is wrong");
    	}

    	l.insertEnd(testArray2);
    	System.out.println("\nInserting testArray2 at end.\nList with testArray2 is " + l);
    	if (l.head == null) {
      		System.out.println("head is null.");
    	} else {
      		if (l.head.item != testArray) {
        		System.out.println("head.item is wrong.");
      		}
      		if (l.head.prev != null) {
        		System.out.println("head.prev is wrong.");
      		}
 
    	if (l.tail == null) {
    		System.out.println("tail is null");
    	}else {
    		if (l.tail.item != testArray2) {
    			System.out.println("tail.item is wrong");
    		}
    		if (l.tail.next != null) {
    			System.out.println("tail.next is wrong");
    		}
    	}
    	if (l.size != 2) {
    		System.out.println("size is wrong");
    	}
	}
}
}
}















