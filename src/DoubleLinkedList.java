import java.util.*;

public class DoubleLinkedList<E> extends AbstractSequentialList<E>
{  // Data fields
    private Node<E> head = null;   // points to the head of the list
    private Node<E> tail = null;   //points to the tail of the list
    private int size = 0;    // the number of items in the list

    public void add(int index, E obj){
        listIterator(index).add(obj);
    }

    public void addFirst(E obj) {
        listIterator(0).add(obj);
    }

    public void addLast(E obj) {
        listIterator(size-1).add(obj);
    }

    public E get(int index) {
        if (index < 0 || index > size -1)
            throw new IndexOutOfBoundsException();
        ListIterator<E> iter = listIterator(index);
        return iter.next();
    }
    public E getFirst() { return head.data;  }
    public E getLast() { return tail.data;  }

    public E set(int index, E obj)
    {
        if (index < 0 || index > size -1)
            throw new IndexOutOfBoundsException();
        E returnedE;
        ListIterator i;
        i = listIterator(index);
        returnedE = (E) i.next();
        i.set(obj);
        return returnedE;
    }

    public int size() {
        return size;
    }

    public E remove(int index)
    {
        E returnValue = null;
        ListIterator<E> iter = listIterator(index);
        if (iter.hasNext()) {
            returnValue = iter.next();
            iter.remove();
        }
        else {   throw new IndexOutOfBoundsException();  }
        return returnValue;
    }

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

        public ListIter(ListIter other) {
            nextItem = other.nextItem;
            index = other.index;
        }

        public boolean hasNext() {
            return nextItem != null;
        }
        public boolean hasPrevious() {
            return index > 0;
        }
        public int previousIndex() {
            return index - 1;
        }
        public int nextIndex() {
            return index;
        }

        public void set(E o)  {
            if (isEmpty() || lastItemReturned == null)
                throw new IllegalStateException();
            lastItemReturned.data = o;
            lastItemReturned = null;
        }

        public void remove(){
            if (isEmpty() || lastItemReturned == null)
                throw new IllegalStateException();
            if (size == 1)
                head = null;
            else if (lastItemReturned.prev == null){
                head = nextItem;
                lastItemReturned.next.prev = null;
            }
            else if (lastItemReturned.next == null){
                lastItemReturned.prev.next = null;
            }else{
                lastItemReturned.prev.next = lastItemReturned.next;
                lastItemReturned.next.prev = lastItemReturned.prev;
            }
            size--;
            lastItemReturned = null;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();
            E temp = nextItem.data;
            lastItemReturned = nextItem;
            nextItem = nextItem.next;
            index ++;
            return temp;
        }

        public E previous() {
            if (!hasPrevious())
                throw new NoSuchElementException();
            nextItem = (nextItem == null ? tail : nextItem.prev);
            index--;
            lastItemReturned = nextItem;
            return lastItemReturned.data;
        }

        public void add(E obj) {
            Node<E> newNode;
            if (head == null) {
                head = new Node<E>(obj);
                tail = head;
            }
            else if (nextItem == head) {
                newNode = new Node<E>(obj);
                newNode.next = nextItem;
                nextItem.prev = newNode;
                head = newNode;
            }
            else if (nextItem == null) {
                newNode = new Node<E>(obj);
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            } else {
                newNode = new Node<E>(obj);
                newNode.prev = nextItem.prev;
                nextItem.prev.next = newNode;
                newNode.next = nextItem;
                nextItem.prev = newNode;
            }
            lastItemReturned = null;
            size++;
            index++;
        }
    }// end of inner class ListIter
}// end of class DoubleLinkedList