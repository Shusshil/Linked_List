package Linked_List;

public class Link {
	public static void main(String[] args) {
		LinkedList list = new LinkedList();
        list.printLinkedList(list.head);
        list.insert(4);
        list.insert(8);
        list.insert(-2);
        list.insertAtHead(9);
        list.insert(3);
        list.insertAtHead(-12);
        list.insertAtIndex(5,2);

        list.printLinkedList(list.head);
        
        System.out.println(list.getsize());
        System.out.println(list.getValue(2));
        
        list.deleteValue(3);        
        list.printLinkedList(list.head);        
        System.out.println(list.getsize());
         
        list.deleteIndex(2);
        list.printLinkedList(list.head);
        System.out.println(list.getsize());
	}
}
class Node{
    int data;
    Node next;
}
class LinkedList{
    Node head;
    
    public void insertAtHead(int value){
        Node newNode = new Node();
        newNode.data = value;
        newNode.next = head;
        head = newNode;
    }
    
    public void insertAtIndex(int value, int index){
        Node temp = head;
        
        if(index == 0){
            insertAtHead(value);
        }
        else{
            while(index > 1){
                 temp = temp.next;
                 index--;
            }
            Node newNode = new Node();
            newNode.data = value;
            newNode.next = temp.next;
            temp.next = newNode;
        }
    }
    
    public void insert(int value){
        if(head==null){
            Node node = new Node();
            node.data = value;
            head = node;
        }
        else{
            Node node1 = head;
            while(node1.next != null){
                node1 = node1.next;
            }
            node1.next = new Node();
            node1.next.data = value;
        }
    }
    
    public void printLinkedList(Node headNode){
        if(headNode == null){
            System.out.println("linkedlist is empty");
            return;
        }
        else{
            while(headNode != null){
                System.out.println(headNode.data);
                headNode = headNode.next;
            }
        }
    }
    
    public int getsize(){
        int size=0;
        Node node1 = head;
        while(node1 != null){
            size++;
            node1 = node1.next;
        }
        return size;
    }
    
    public int getValue(int index){
        int size = getsize();
        Node node = new Node(); 
        if(index >= size){
            System.out.println("LinkedList is underflow");
        }
        else{
            node = head;
            while(index > 0){
                node = node.next;
                index--;
            }
        }
        return node.data;
    }
    
    public void deleteValue(int value){
        Node node = head;

        //if deleting head 
        if(node.data == value){
            head = node.next;
            node.next = null;
            System.out.println("deleted value = " + node.data);
        }
        else{
            while(node.next != null && node.next.data != value ){
                node = node.next;
            }
            if(node.next != null){
                System.out.println("deleted value = " + node.next.data);
                node.next = node.next.next;
            }
            else{
                System.out.println("value not found");
            }
        }
    }
    public void deleteIndex(int index){
        int size = getsize();
        if(index >= size || index < 0){
            System.out.println("invalid index");
            return;
        }

        Node node = head;
        if(index == 0){ // or size 1  
            head = node.next;
            node.next = null;
            System.out.println("deleted value = " + node.data);
        }
        else{
            while(index > 1){
                node = node.next;
                index--;
            }
            System.out.println("deleted value = " + node.next.data);
            Node tmp = node.next;
            node.next = node.next.next;
            tmp.next = null;
        }
    }
    public void deleteHead(){
        if(head == null){
            System.out.print("Not Present");
        }
        else{
            Node node = head;
            head = node.next;
            node.next = null;
        }
    }
    public void deleteTail(){
        Node node = head;
        Node prev = head;
        while(node.next != null){
            prev = node;
            node = node.next;
        }
        if(prev != head){
            prev.next = null;
        }
        else
            head = null;
    }
    
    public Node reverseR(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node tmp = reverseR(head.next);
        head.next.next = head;
        head.next = null;

        return tmp;
    }
    public Node reverse(Node head){
        Node curr = head;
        Node prev = null;
        Node next = null;

        while(curr != null){
           next = curr.next;
           curr.next = prev;
           prev = curr;
           curr = next; 
        }
        return prev;
    }
    public boolean isCyclePresent(Node head){
        if(head == null)
            return false;

        Node FastPointer = head;
        Node SlowPointer = head;

        while(FastPointer.next != null && SlowPointer != null){
            FastPointer = FastPointer.next.next; // fast is moving at 2X
            SlowPointer = SlowPointer.next; // slow is moving 1X
            if(FastPointer == SlowPointer){
                return true;
            }

        }

        return false;
    }
    public Node reverseInGroups(Node head, int k){
        if(head == null)
            return null;

        int size = getsize();
        if(size >= k){
            Node curr = head;
            Node next = null;
            Node prev = null;

            int count = 0;

            while(count < k && curr != null){
                next  = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }
            head.next = reverseInGroups(next, k);
            return prev;
        }
        else{
            return head;
        }

    }
}
