import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    private Node<E> head = null;   // points to the head of the list
    private Node<E> tail = null;   //points to the tail of the list
    private int size = 0;    // the number of items in the list

    //Adds a new element at an index, with data obj
    public void add(int index, E obj){
        listIterator(index).add(obj);
    }

    //Adds a new element at 0, with data obj
    public void addFirst(E obj) {
        listIterator(0).add(obj);
    }

    //Adds a new element at size-1, with data obj
    public void addLast(E obj) {
        listIterator(size-1).add(obj);
    }

    //Returns the object at an index
    public E get(int index) {
        //Check to see if the index is out of bounds
        if (index < 0 || index > size -1)
            throw new IndexOutOfBoundsException();
        //Create a new iterator starting at index
        ListIterator<E> iter = listIterator(index);
        //Retrieve the element at the index
        return iter.next();
    }

    //Returns the first element in the linked list
    public E getFirst() { return head.data;  }

    //Returns the lats element in the linked list
    public E getLast() { return tail.data;  }

    //Sets the data at an index to obj
    public E set(int index, E obj) {
        //Check to see if the index is out of bounds
        if (index < 0 || index > size -1)
            throw new IndexOutOfBoundsException();
        //Create a temporary variable to store the new element
        E temp;
        //Create a new iterator at an index
        ListIterator i = listIterator(index);
        //Set the temp variable to the element at an index
        temp = (E) i.next();
        //Set the data at the current index to obj
        i.set(obj);
        //Return the temp variable
        return temp;
    }

    //Returns the size of the linked list
    public int size() {
        return size;
    }

    //Removes the element at an index
    public E remove(int index)
    {
        //Set up a temporary storage for return value
        E returnValue = null;
        //Create a new iterator at an index
        ListIterator<E> iter = listIterator(index);
        //Check to see if the index is valid
        if (iter.hasNext()) {
            //Set the return value to the element at an index
            returnValue = iter.next();
            //Remove the element at the current index
            iter.remove();
        }
        //If index is invalid, throw an error
        else {   throw new IndexOutOfBoundsException();  }
        //Return the temp variable
        return returnValue;
    }

    //Constructors for iterators
    public Iterator iterator() { return new ListIter(0);  }
    public ListIterator listIterator() { return new ListIter(0);  }
    public ListIterator listIterator(int index){return new ListIter(index);}
    public ListIterator listIterator(ListIterator iter)
    {     return new ListIter( (ListIter) iter);  }

    // Inner Classes
    private static class Node<E>
    {     private E data;
        private Node<E> next = null;
        private Node<E> prev = null;

        private Node(E dataItem)  //constructor
        {   data = dataItem;   }
    }  // end class Node

    public class ListIter implements ListIterator<E>
    {
        private Node<E> nextItem;      // the current node
        private Node<E> lastItemReturned;   // the previous node
        private int index = 0;   // 

        public ListIter(int i)  // constructor for ListIter class
        {
            if (i < 0 || i > size)
        {     throw new IndexOutOfBoundsException("Invalid index " + i); }
            lastItemReturned = null;
            if (i == size)     // Special case of last item
            {      index = size;     nextItem = null;      }
            else          // start at the beginning
            {
                nextItem = head;
                for (index = 0; index < i; index++)  nextItem = nextItem.next;
            }// end else
        }  // end constructor

        //Constructor using an already existing iterator
        public ListIter(ListIter other) {
            //Update the index and current item
            nextItem = other.nextItem;
            index = other.index;
        }

        //Check to see if we have a nextItem
        public boolean hasNext() {
            return nextItem != null;
        }

        //Check to see if we have a previous item
        public boolean hasPrevious() {
            return index > 0;
        }

        //Check the index of the previous item
        public int previousIndex() {
            return index - 1;
        }

        //Check the index of the next item
        public int nextIndex() {
            return index;
        }

        //Set the data at the current index to o
        public void set(E o)  {
            //Check the validity of the current item
            if (isEmpty() || lastItemReturned == null)
                throw new IllegalStateException();
            //Set the current data to o
            lastItemReturned.data = o;
            //Set the lastItemReturned to null
            lastItemReturned = null;
        }

        //Removed the current item
        public void remove(){
            //Check the validity of the current item
            if (isEmpty() || lastItemReturned == null)
                throw new IllegalStateException();
            //Check to see if there is only one item
            if (size == 1)
                //Remove the only item, the head
                head = null;
            //Check to see if the list is 2 items long
            else if (lastItemReturned.prev == null){
                //Set the current item to the head
                head = nextItem;
                //Remove the non-head item
                lastItemReturned.next.prev = null;
            }
            //Check to see if we are at the end of the list
            else if (lastItemReturned.next == null){
                //Remove the last item
                lastItemReturned.prev.next = null;
            }else{
                //Remove the current item, update the lastItemReturned variable
                lastItemReturned.prev.next = lastItemReturned.next;
                lastItemReturned.next.prev = lastItemReturned.prev;
            }
            //Update the size
            size--;
            //Set the lastItemReturned to null
            lastItemReturned = null;
        }

        //Increments the iterator by 1
        public E next() {
            //Check to see if we are at the end of the linked list
            if (!hasNext())
                throw new NoSuchElementException();
            //Create a temp variable for the current data
            E temp = nextItem.data;
            //Set the last item to the current item
            lastItemReturned = nextItem;
            //Set the current item to the next item
            nextItem = nextItem.next;
            //Increment the index
            index ++;
            //Return the old current data
            return temp;
        }

        //Decrement the iterator by 1
        public E previous() {
            //Check to see if we are at the beginning of the linked list
            if (!hasPrevious())
                throw new NoSuchElementException();
            //Set the next item to the previous item or the tail, if we are at the end of the linked list
            nextItem = (nextItem == null ? tail : nextItem.prev);
            //Decrement the index
            index--;
            //Set the last item to the current item
            lastItemReturned = nextItem;
            //Return the new current data
            return lastItemReturned.data;
        }

        //Add a new item at the current index
        public void add(E obj) {
            //Create a new node item
            Node<E> newNode;
            //Check to see if the list is empty
            if (head == null) {
                //Set the head to a new node
                head = new Node(obj);
                //Set the tail to the new head
                tail = head;
            }
            //Check to see if the list is only one item long
            else if (nextItem == head) {
                //Initialize the new node
                newNode = new Node(obj);
                //Set the new node's next item to the current item
                newNode.next = nextItem;
                //Set the current node's previous item to the new node
                nextItem.prev = newNode;
                //Set the head to the new node
                head = newNode;
            }
            //Check to see if we are at the end of the linked list
            else if (nextItem == null) {
                //Initialize the new node
                newNode = new Node(obj);
                //Set the tail's next item to the new node
                tail.next = newNode;
                //Set the new node's previous item to the tail
                newNode.prev = tail;
                //Set the tail to the new node
                tail = newNode;
            } else {
                //Initialize the new node
                newNode = new Node(obj);
                //Set the new node's previous item to the current item's previous item
                newNode.prev = nextItem.prev;
                //Set the current item's previous next item to the new node
                nextItem.prev.next = newNode;
                //Set the new node's next item to the current item
                newNode.next = nextItem;
                //Set the current item's previous item to the new node
                nextItem.prev = newNode;
            }
            //Set the lastItemReturned to null
            lastItemReturned = null;
            //Increment the size
            size++;
            //Increment the index
            index++;
        }
    }// end of inner class ListIter
}// end of class DoubleLinkedList