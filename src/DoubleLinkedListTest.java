import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Iterator;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * DoubleLinkedListTest : Test class for the DoubleLinkedList implementation and its constituent ListIterator
 * inner class.
 */
public class DoubleLinkedListTest {

    /** Values to be added in linked list's setup */
    private static final String[] STRING_VALUES = {"first", "second", "third", "fourth" , "fifth"};
    private static final int[] INT_VALUES = {100, 200, 300, 400, 500};
    private static final char[] CHAR_VALUES = {'A', 'B', 'C', 'D', 'E'};
    private static final double[] DOUBLE_VALUES = {1.1, 2.2, 3.3, 4.4, 5.5};

    /** Expected values following the building of the linked lists */
    private static final String TO_STRING_EMPTY = "[]";
    private static final String[] TO_STRING_ADD1 = {"[first]",
            "[100]",
            "[A]",
            "[1.1]"};
    private static final String[] TO_STRING_ADD3 = {"[first, second, third]",
            "[100, 200, 300]",
            "[A, B, C]",
            "[1.1, 2.2, 3.3]"};

    /** Values inserted through linked list's add method */
    private static final String STRING_INSERT_VAL = "w00t";
    private static final int INT_INSERT_VAL = 555;
    private static final char CHAR_INSERT_VAL = 'X';
    private static final double DOUBLE_INSERT_VAL = -3.14;

    /** Index of linked list in which the insert value is added */
    private static final int STRING_INSERT_INDEX = 0, INT_INSERT_INDEX = 1, CHAR_INSERT_INDEX = 2, DOUBLE_INSERT_INDEX = 3;

    /** Expected values following the insertion of an additional element */
    private static final String[] TO_STRING_INSERT = {"[w00t, first, second, third]",
            "[100, 555, 200, 300]",
            "[A, B, X, C]",
            "[1.1, 2.2, 3.3, -3.14]"};

    /** Index of iterator by which the set. add and remove methods are called. stringList and intList will be modified
     *  following a call to next, whereas charList and doubleList will set following a call to previous. */
    private static final int STRING_SET_INDEX = 0, INT_SET_INDEX = 1, DOUBLE_SET_INDEX = 2, CHAR_SET_INDEX = 3;

    /** Expected values following the iterator's set or remove methods */
    private static final String[] TO_STRING_SET = { "[w00t, second, third]",
            "[100, 555, 300]",
            "[A, B, X]",
            "[1.1, -3.14, 3.3]"};

    /** Class implementing the List interface */
    private List<String> stringList;
    private List<Integer> intList;
    private List<Character> charList;
    private List<Double> doubleList;

    /** List inner class which implements the ListIterator interface */
    private ListIterator<String> stringIterator;
    private ListIterator<Integer> intIterator;
    private ListIterator<Character> charIterator;
    private ListIterator<Double> doubleIterator;

    /**
     * Helper method which builds linked lists of various data types.
     *
     * @param num the number of elements to be inserted into each of the four linked lists
     */
    public void buildLists(int num) {
        for (int i = 0; i < num; i ++) {
            stringList.add(STRING_VALUES[i]);
            intList.add(INT_VALUES[i]);
            charList.add(CHAR_VALUES[i]);
            doubleList.add(DOUBLE_VALUES[i]);
        }
    }

    /**
     * Creates four empty DoubleLinkedList objects.
     * Using the @Before tag, this method will be called before EVERY SINGLE @Test method that runs.
     */
    @Before
    public void setUp() {
        stringList = new DoubleLinkedList<String>();
        intList = new DoubleLinkedList<Integer>();
        charList = new DoubleLinkedList<Character>();
        doubleList = new DoubleLinkedList<Double>();
    }

    // region List tests ===============================================================================================
    // NOTE: IntelliJ IDEA users may toggle region fold sections and navigate ==========================================
    // with [Ctrl + Alt + Period] (Windows) or [Command + Alt + Period] (Mac) ==========================================

    @Test
    public void testNewLinkedListBySize() {
        assertTrue("String list should start as empty", stringList.isEmpty());
        assertTrue("Integer list should start as empty", intList.isEmpty());
        assertTrue("Character list should start as empty", charList.isEmpty());
        assertTrue("Double list should start as empty", doubleList.isEmpty());
    }

    @Test
    public void testAddingOneBySize() {
        buildLists(1);

        assertEquals("String list should have size of 1", 1, stringList.size());
        assertEquals("Integer list should have size of 1", 1, intList.size());
        assertEquals("Character list should have size of 1", 1, charList.size());
        assertEquals("Double list should have size of 1", 1, doubleList.size());
    }

    @Test
    public void testAddingThreeBySize() {
        buildLists(3);

        assertEquals("String list should have size of 3", 3, stringList.size());
        assertEquals("Integer list should have size of 3", 3, intList.size());
        assertEquals("Character list should have size of 3", 3, charList.size());
        assertEquals("Double list should have size of 3", 3, doubleList.size());
    }

    @Test
    public void testNewLinkedListByToString() {
        // Each new/empty list's toString should return "[]"
        assertEquals("String list expected toString doesn't match actual", TO_STRING_EMPTY, stringList.toString());
        assertEquals("Integer list expected toString doesn't match actual", TO_STRING_EMPTY, intList.toString());
        assertEquals("Character list expected toString doesn't match actual", TO_STRING_EMPTY, charList.toString());
        assertEquals("Double list expected toString doesn't match actual", TO_STRING_EMPTY, doubleList.toString());
    }

    @Test
    public void testAddingOneByToString() {
        buildLists(1);

        assertEquals("String list expected toString doesn't match actual", TO_STRING_ADD1[0], stringList.toString());
        assertEquals("Integer list expected toString doesn't match actual", TO_STRING_ADD1[1], intList.toString());
        assertEquals("Character list expected toString doesn't match actual", TO_STRING_ADD1[2], charList.toString());
        assertEquals("Double list expected toString doesn't match actual", TO_STRING_ADD1[3], doubleList.toString());
    }

    @Test
    public void testAddingThreeByToString() {
        buildLists(3);

        assertEquals("String list expected toString doesn't match actual", TO_STRING_ADD3[0], stringList.toString());
        assertEquals("Integer list expected toString doesn't match actual", TO_STRING_ADD3[1], intList.toString());
        assertEquals("Character list expected toString doesn't match actual", TO_STRING_ADD3[2], charList.toString());
        assertEquals("Double list expected toString doesn't match actual", TO_STRING_ADD3[3], doubleList.toString());
    }

    @Test
    public void testInsertingByToString() {
        buildLists(3);

        stringList.add(STRING_INSERT_INDEX, STRING_INSERT_VAL);
        intList.add(INT_INSERT_INDEX, INT_INSERT_VAL);
        charList.add(CHAR_INSERT_INDEX, CHAR_INSERT_VAL);
        doubleList.add(DOUBLE_INSERT_INDEX, DOUBLE_INSERT_VAL);

        assertEquals("String list expected toString doesn't match actual", TO_STRING_INSERT[0], stringList.toString());
        assertEquals("Integer list expected toString doesn't match actual", TO_STRING_INSERT[1], intList.toString());
        assertEquals("Character list expected toString doesn't match actual", TO_STRING_INSERT[2], charList.toString());
        assertEquals("Double list expected toString doesn't match actual", TO_STRING_INSERT[3], doubleList.toString());
    }

    @Test
    public void testClear() {
        buildLists(3);

        assertEquals("String list should have size of 3", 3, stringList.size());
        assertEquals("Integer list should have size of 3", 3, intList.size());
        assertEquals("Character list should have size of 3", 3, charList.size());
        assertEquals("Double list should have size of 3", 3, doubleList.size());

        stringList.clear();
        intList.clear();
        charList.clear();
        doubleList.clear();

        assertTrue("String list should be empty", stringList.isEmpty());
        assertTrue("Integer list should be empty", intList.isEmpty());
        assertTrue("Character list should be empty", charList.isEmpty());
        assertTrue("Double list should be empty", doubleList.isEmpty());
    }

    @Test
    public void testEquals() {
        buildLists(5);

        List<String> stringListJava = new LinkedList<String>();
        List<Integer> intListJava = new LinkedList<Integer>();
        List<Character> charListJava = new LinkedList<Character>();
        List<Double> doubleListJava = new LinkedList<Double>();

        for (int i = 0; i < 5; i ++) {
            stringListJava.add(STRING_VALUES[i]);
            intListJava.add(INT_VALUES[i]);
            charListJava.add(CHAR_VALUES[i]);
            doubleListJava.add(DOUBLE_VALUES[i]);
        }

        assertEquals("Expected String list (java.util.LinkedList) and Actual String list (your implementation) don't match", stringListJava, stringList);
        assertEquals("Expected Integer list (java.util.LinkedList) and Actual Integer list (your implementation) don't match", intListJava, intList);
        assertEquals("Expected Character list (java.util.LinkedList) and Actual Character list (your implementation) don't match", charListJava, charList);
        assertEquals("Expected Double list (java.util.LinkedList) and Actual Double list (your implementation) don't match", doubleListJava, doubleList);
    }

    @Test
    public void testContains() {
        buildLists(5);

        assertFalse("Expected value NOT to be in String list, but your code claims it is", stringList.contains(STRING_INSERT_VAL));
        assertFalse("Expected value NOT to be in Integer list, but your code claims it is", intList.contains(INT_INSERT_VAL));
        assertFalse("Expected value NOT to be in Character list, but your code claims it is", charList.contains(CHAR_INSERT_VAL));
        assertFalse("Expected value NOT to be in Double list, but your code claims it is", doubleList.contains(DOUBLE_INSERT_VAL));

        stringList.add(STRING_INSERT_VAL);
        intList.add(INT_INSERT_VAL);
        charList.add(CHAR_INSERT_VAL);
        doubleList.add(DOUBLE_INSERT_VAL);

        assertTrue("Expected value to be in String list, but your code claims it is NOT", stringList.contains(STRING_INSERT_VAL));
        assertTrue("Expected value to be in Integer list, but your code claims it is NOT", intList.contains(INT_INSERT_VAL));
        assertTrue("Expected value to be in Character list, but your code claims it is NOT", charList.contains(CHAR_INSERT_VAL));
        assertTrue("Expected value to be in Double list, but your code claims it is NOT", doubleList.contains(DOUBLE_INSERT_VAL));
    }

    @Test
    public void testGet() {
        buildLists(5);

        assertEquals("Expected value in String list doesn't match get() from your list", STRING_VALUES[3], stringList.get(3));
        assertEquals("Expected value in Integer list doesn't match get() from your list", new Integer(INT_VALUES[3]), intList.get(3));
        assertEquals("Expected value in Character list doesn't match get() from your list", new Character(CHAR_VALUES[3]), charList.get(3));
        assertEquals("Expected value in Double list doesn't match get() from your list", new Double(DOUBLE_VALUES[3]), doubleList.get(3));
    }

    @Test
    public void testGetError() {
        // Attempt get on empty lists
        try {
            stringList.get(0);
            fail("String list get() test should have thrown exception for out of bounds (empty list, index == 0)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }

        try {
            intList.get(1);
            fail("Integer list get() test should have thrown exception for out of bounds (empty list, index == 1)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }

        buildLists(5);

        // Attempt get from out-of-bounds indices
        try {
            charList.get(5);
            fail("Character list get() test should have thrown exception for out of bounds (index >= size)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }

        try {
            doubleList.get(-1);
            fail("Double list get() test should have thrown exception for out of bounds (index < 0)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }
    }

    @Test
    public void testIndexOf() {
        buildLists(4);

        assertEquals("Expected value in String list doesn't match indexOf() from your list", 0, stringList.indexOf(STRING_VALUES[0]));
        assertEquals("Expected value in Integer list doesn't match indexOf() from your list", 1, intList.indexOf(INT_VALUES[1]));
        assertEquals("Expected value in Character list doesn't match indexOf() from your list", 2, charList.indexOf(CHAR_VALUES[2]));
        assertEquals("Expected value in Double list doesn't match indexOf() from your list", 3, doubleList.indexOf(DOUBLE_VALUES[3]));
    }

    @Test
    public void testIndexOfRepeats() {
        buildLists(4);

        // Note the insertion of a repeated value- indexOf should return the FIRST value.
        stringList.add(1, STRING_VALUES[0]);
        stringList.add(5, STRING_VALUES[0]);
        System.out.println("new string list = " + stringList);

        intList.add(2, INT_VALUES[1]);
        intList.add(5, INT_VALUES[1]);
        System.out.println("new int list = " + intList);

        charList.add(3, CHAR_VALUES[2]);
        charList.add(5, CHAR_VALUES[2]);
        System.out.println("new char list = " + charList);

        doubleList.add(4, DOUBLE_VALUES[3]);
        doubleList.add(5, DOUBLE_VALUES[3]);
        System.out.println("new double list = " + doubleList);

        assertEquals("Expected value in String list doesn't match indexOf() from your list", 0, stringList.indexOf(STRING_VALUES[0]));
        assertEquals("Expected value in Integer list doesn't match indexOf() from your list", 1, intList.indexOf(INT_VALUES[1]));
        assertEquals("Expected value in Character list doesn't match indexOf() from your list", 2, charList.indexOf(CHAR_VALUES[2]));
        assertEquals("Expected value in Double list doesn't match indexOf() from your list", 3, doubleList.indexOf(DOUBLE_VALUES[3]));
    }

    @Test
    public void testLastIndexOf() {
        buildLists(4);

        assertEquals("Expected value in String list doesn't match lastIndexOf() from your list", 0, stringList.lastIndexOf(STRING_VALUES[0]));
        assertEquals("Expected value in Integer list doesn't match lastIndexOf() from your list", 1, intList.lastIndexOf(INT_VALUES[1]));
        assertEquals("Expected value in Character list doesn't match lastIndexOf() from your list", 2, charList.lastIndexOf(CHAR_VALUES[2]));
        assertEquals("Expected value in Double list doesn't match lastIndexOf() from your list", 3, doubleList.lastIndexOf(DOUBLE_VALUES[3]));
    }

    @Test
    public void testLastIndexOfRepeats1() {
        buildLists(4);

        // Note the insertion of a repeated value- indexOf should return the LAST value.
        stringList.add(1, STRING_VALUES[0]);
        System.out.println("new string list = " + stringList);

        intList.add(2, INT_VALUES[1]);
        System.out.println("new int list = " + intList);

        charList.add(3, CHAR_VALUES[2]);
        System.out.println("new char list = " + charList);

        doubleList.add(4, DOUBLE_VALUES[3]);
        System.out.println("new double list = " + doubleList);

        assertEquals("Expected value in String list doesn't match lastIndexOf() from your list", 1, stringList.lastIndexOf(STRING_VALUES[0]));
        assertEquals("Expected value in Integer list doesn't match lastIndexOf() from your list", 2, intList.lastIndexOf(INT_VALUES[1]));
        assertEquals("Expected value in Character list doesn't match lastIndexOf() from your list", 3, charList.lastIndexOf(CHAR_VALUES[2]));
        assertEquals("Expected value in Double list doesn't match lastIndexOf() from your list", 4, doubleList.lastIndexOf(DOUBLE_VALUES[3]));
    }

    @Test
    public void testLastIndexOfRepeats2() {
        buildLists(4);

        // Note the insertion of a repeated value- indexOf should return the LAST value.
        stringList.add(4, STRING_VALUES[0]);
        System.out.println("new string list = " + stringList);

        intList.add(4, INT_VALUES[1]);
        System.out.println("new int list = " + intList);

        charList.add(4, CHAR_VALUES[2]);
        System.out.println("new char list = " + charList);

        doubleList.add(4, DOUBLE_VALUES[3]);
        System.out.println("new double list = " + doubleList);

        assertEquals("Expected value in String list doesn't match lastIndexOf() from your list", 4, stringList.lastIndexOf(STRING_VALUES[0]));
        assertEquals("Expected value in Integer list doesn't match lastIndexOf() from your list", 4, intList.lastIndexOf(INT_VALUES[1]));
        assertEquals("Expected value in Character list doesn't match lastIndexOf() from your list", 4, charList.lastIndexOf(CHAR_VALUES[2]));
        assertEquals("Expected value in Double list doesn't match lastIndexOf() from your list", 4, doubleList.lastIndexOf(DOUBLE_VALUES[3]));
    }

    @Test
    public void testErrorsIndexOfLastIndexOf() {
        buildLists(5);

        assertEquals("Expected value in String list doesn't match indexOf() from your list", -1, stringList.indexOf(STRING_INSERT_VAL));
        assertEquals("Expected value in Integer list doesn't match indexOf() from your list", -1, intList.indexOf(INT_INSERT_VAL));
        assertEquals("Expected value in Character list doesn't match indexOf() from your list", -1, charList.indexOf(CHAR_INSERT_VAL));
        assertEquals("Expected value in Double list doesn't match indexOf() from your list", -1, doubleList.indexOf(DOUBLE_INSERT_VAL));

        assertEquals("Expected value in String list doesn't match lastIndexOf() from your list", -1, stringList.lastIndexOf(STRING_INSERT_VAL));
        assertEquals("Expected value in Integer list doesn't match lastIndexOf() from your list", -1, intList.lastIndexOf(INT_INSERT_VAL));
        assertEquals("Expected value in Character list doesn't match lastIndexOf() from your list", -1, charList.lastIndexOf(CHAR_INSERT_VAL));
        assertEquals("Expected value in Double list doesn't match lastIndexOf() from your list", -1, doubleList.lastIndexOf(DOUBLE_INSERT_VAL));
    }

    @Test
    public void testRemoveByIndex() {
        buildLists(5);

        assertEquals("Remove from String list was not successful, removed wrong value", STRING_VALUES[0], stringList.remove(0));
        assertEquals("Remove from Integer list was not successful, removed wrong value", new Integer(INT_VALUES[1]), intList.remove(1));
        assertEquals("Remove from Character list was not successful, removed wrong value", new Character(CHAR_VALUES[2]), charList.remove(2));
        assertEquals("Remove from Double list was not successful, removed wrong value", new Double(DOUBLE_VALUES[3]), doubleList.remove(3));
    }

    @Test
    public void testRemoveByValue() {
        buildLists(3);

        stringList.add(STRING_INSERT_INDEX,  STRING_INSERT_VAL);
        intList.add(INT_INSERT_INDEX,  INT_INSERT_VAL);
        charList.add(CHAR_INSERT_INDEX,  CHAR_INSERT_VAL);
        doubleList.add(DOUBLE_INSERT_INDEX,  DOUBLE_INSERT_VAL);

        System.out.println("before remove, string list = " + stringList); // Observe lists before remove through the console
        System.out.println("before remove, int list = " + intList);
        System.out.println("before remove, char list = " + charList);
        System.out.println("before remove, double list = " + doubleList);

        assertEquals("String list expected toString doesn't match actual", TO_STRING_INSERT[0], stringList.toString());
        assertEquals("Integer list expected toString doesn't match actual", TO_STRING_INSERT[1], intList.toString());
        assertEquals("Character list expected toString doesn't match actual", TO_STRING_INSERT[2], charList.toString());
        assertEquals("Double list expected toString doesn't match actual", TO_STRING_INSERT[3], doubleList.toString());

        assertTrue("String list remove returned false when value is actually present in list", stringList.remove(STRING_INSERT_VAL));
        assertTrue("Integer list remove returned false when value is actually present in list", intList.remove(new Integer(INT_INSERT_VAL)));
        assertTrue("Character list remove returned false when value is actually present in list", charList.remove(new Character(CHAR_INSERT_VAL)));
        assertTrue("Double list remove returned false when value is actually present in list", doubleList.remove(DOUBLE_INSERT_VAL));

        System.out.println("\nafter remove, string list = " + stringList); // Observe lists after remove through the console
        System.out.println("after remove, int list = " + intList);
        System.out.println("after remove, char list = " + charList);
        System.out.println("after remove, double list = " + doubleList);

        assertEquals("String list expected toString doesn't match actual, incorrect value removed", TO_STRING_ADD3[0], stringList.toString());
        assertEquals("Integer list expected toString doesn't match actual, incorrect value removed", TO_STRING_ADD3[1], intList.toString());
        assertEquals("Character list expected toString doesn't match actual, incorrect value removed", TO_STRING_ADD3[2], charList.toString());
        assertEquals("Double list expected toString doesn't match actual, incorrect value removed", TO_STRING_ADD3[3], doubleList.toString());
    }

    @Test
    public void testRemoveErrors() {
        buildLists(3);

        // Check for exceptions by remove(int)
        try {
            stringList.remove(3);
            fail("String list remove() test should have thrown exception for out of bounds (index >= size)");
        } catch (IndexOutOfBoundsException ioobe) { /* Test Passed */ }

        try {
            intList.remove(-1);
            fail("Integer list remove() test should have thrown exception for out of bounds (index < 0)");
        } catch (IndexOutOfBoundsException ioobe) { /* Test Passed */ }

        // Check for false by remove(Object)
        assertFalse("Character list remove returned true when value is NOT present in list", charList.remove(new Character(CHAR_INSERT_VAL)));
        assertFalse("Double list remove returned true when value is NOT present in list", doubleList.remove(DOUBLE_INSERT_VAL));
    }

    @Test
    public void testSet() {
        buildLists(5);

        stringList.set(STRING_INSERT_INDEX, STRING_INSERT_VAL);
        intList.set(INT_INSERT_INDEX, INT_INSERT_VAL);
        charList.set(CHAR_INSERT_INDEX, CHAR_INSERT_VAL);
        doubleList.set(DOUBLE_INSERT_INDEX, DOUBLE_INSERT_VAL);

        assertEquals("Expected value in String list doesn't match get() from your list", STRING_INSERT_VAL, stringList.get(STRING_INSERT_INDEX));
        assertEquals("Expected value in Integer list doesn't match get() from your list", new Integer(INT_INSERT_VAL), intList.get(INT_INSERT_INDEX));
        assertEquals("Expected value in Character list doesn't match get() from your list", new Character(CHAR_INSERT_VAL), charList.get(CHAR_INSERT_INDEX));
        assertEquals("Expected value in Double list doesn't match get() from your list", new Double(DOUBLE_INSERT_VAL), doubleList.get(DOUBLE_INSERT_INDEX));
    }

    @Test
    public void testSetError() {
        // Attempt set on empty lists
        try {
            stringList.set(0, STRING_INSERT_VAL);
            fail("String list get() test should have thrown exception for out of bounds (empty list, index == 0)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }

        try {
            intList.set(1,  INT_INSERT_VAL);
            fail("Integer list get() test should have thrown exception for out of bounds (empty list, index == 1)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }

        buildLists(5);

        // Attempt set at out-of-bounds indices
        try {
            charList.set(5,  CHAR_INSERT_VAL);
            fail("Character list get() test should have thrown exception for out of bounds (index >= size)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }

        try {
            doubleList.set(-1,  DOUBLE_INSERT_VAL);
            fail("Double list get() test should have thrown exception for out of bounds (index < 0)");
        } catch (IndexOutOfBoundsException ioobe) { /*Test Passed*/ }
    }

    // endregion List tests ============================================================================================
    // region ListIterator tests =======================================================================================

    @Test
    public void testIteratorConstructors() {
        buildLists(5);

        // Test iterator creation at index 0 of an empty list
        charList.clear();
        try {
            charIterator = charList.listIterator();
            /* Test passed */
        }
        catch (Exception e) { fail("Test failed - creation of an iterator at index 0 of an empty list should compile. "); }

        // Test iterator creation at beginning of list
        try {
            doubleIterator = doubleList.listIterator();
            /* Test passed */
        }
        catch (Exception e) { fail("Test failed - creation of an iterator at beginning of list should compile."); }

        // Test iterator creation at middle of list
        try {
            stringIterator = stringList.listIterator();
            /* Test passed */
        }
        catch (Exception e) { fail("Test failed - creation of an iterator at middle of list should compile."); }

        // Test iterator creation at end of list
        try {
            intIterator = intList.listIterator();
            /* Test passed */
        }
        catch (Exception e) { fail("Test failed - creation of an iterator at end of list should compile."); }
    }

    @Test
    public void testIteratorConstructorsErrors() {
        buildLists(5);

        // Test iterator creation failure at an out-of-bounds index < 0
        try {
            stringIterator = stringList.listIterator(-1);
            fail("Test failed - Invalid index (< 0) must throw an IndexOutOfBoundsException.");
        }
        catch (IndexOutOfBoundsException ioobe) { /* Test passed */ }

        // Test iterator creation failure at an out-of-bounds index > size
        try {
            intIterator = intList.listIterator(6);
            fail("Test failed - Invalid index ( > size) must throw an IndexOutOfBoundsException.");
        }
        catch (IndexOutOfBoundsException ioobe) { /* Test passed */ }

        doubleList.clear(); // Empty list
        try {
            doubleIterator = doubleList.listIterator(1);
            fail("Test failed - Invalid index ( > size) must throw an IndexOutOfBoundsException.");
        }
        catch (IndexOutOfBoundsException ioobe) { /* Test passed */ }
    }

    @Test
    public void testIteratorHasNext() {
        buildLists(3);

        // Test at beginning of list
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        assertTrue("Test failed - Iterator at the beginning of the list; hasNext must return true", stringIterator.hasNext());

        // Test at middle of list
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        assertTrue("Test failed - Iterator at middle of the list; hasNext must return true.", intIterator.hasNext());

        // Test at end of list
        charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3
        assertFalse("Test failed - Iterator at end of the list; hasPrevious must return false.", charIterator.hasNext());

        // Test new iterator on an empty list
        doubleList.clear();
        assertFalse("Test failed - Iterator on an empty list; hasNext must return false.", doubleList.listIterator().hasNext());
    }

    @Test
    public void testIteratorHasPrevious() {
        buildLists(3);

        // Test at beginning of list
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        assertFalse("Test failed - Iterator at the beginning of the list; hasPrevious must return false.", stringIterator.hasPrevious());

        // Test at middle of list
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        assertTrue("Test failed - Iterator at middle of the list; hasPrevious must return true.", intIterator.hasPrevious());

        // Test at end of list
        charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3
        assertTrue("Test failed - Iterator at end of the list; hasPrevious must return true.", charIterator.hasPrevious());

        // Test new iterator on an empty list
        doubleList.clear();
        assertFalse("Test failed - Iterator on an empty list; hasPrevious must return false.", doubleList.listIterator().hasPrevious());
    }

    @Test
    public void testIteratorNext() {
        buildLists(3);

        // Test at beginning of list
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        assertEquals("Test failed - the expected first element was not returned by next.", STRING_VALUES[0], stringIterator.next());

        // Test at middle of list
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        assertEquals("Test failed - the expected middle element was not returned by next.", new Integer(INT_VALUES[1]), intIterator.next());

        // Test at last element of list
        doubleIterator = doubleList.listIterator(DOUBLE_SET_INDEX); // index 2
        assertEquals("Test failed - the expected last element was not returned by next.", new Double(DOUBLE_VALUES[2]), doubleIterator.next());

        // Test all elements in a list
        charIterator = charList.listIterator(); // index 0
        for (int i = 0; i >= charList.size() - 1; i --) {
            assertEquals("Test failed - Unexpected value upon iterative call to previous.", new Character(CHAR_VALUES[i]), charIterator.previous());
        }
    }

    @Test
    public void testIteratorNextErrors() {
        buildLists(3);

        // Test call to next at the end of the list
        try {
            charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3
            charIterator.next();
            fail("Test failed - Iterator at the end of the list should throw NoSuchElementException when calling next.");
        } catch (NoSuchElementException nsee) { /* Test passed */ }

        stringList.clear();

        // Test call to next on an empty list
        try {
            stringList.listIterator().next();
            fail("Test failed - Iterator for an empty list throws NoSuchElementException.");
        } catch (NoSuchElementException nsee) { /* Test passed */ }
    }

    @Test
    public void testIteratorNextIndex() {
        buildLists(3);

        // Test nextIndex at beginning of list
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        // Returns the index of the element that would be returned by a subsequent call to next
        assertEquals("Test failed - Unexpected next index at beginning of list.", STRING_SET_INDEX, stringIterator.nextIndex());

        // Test nextIndex in middle of list
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        assertEquals("Test failed - Unexpected next index in middle of list.", INT_SET_INDEX, intIterator.nextIndex());

        // Test nextIndex at end of list
        charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3; next index should == the list's size
        assertEquals("Test failed - Unexpected next index value at end of list.", charList.size(), charIterator.nextIndex());

        // Test nextIndex on an empty list
        doubleList.clear();
        doubleIterator = doubleList.listIterator(); // index 0; next index should == the list's size
        assertEquals("Test failed - Unexpected next index value on an empty list.", 0, doubleIterator.nextIndex());
    }

    @Test
    public void testIteratorPrevious() {
        buildLists(3);

        // Test at beginning of list
        stringIterator = stringList.listIterator(STRING_SET_INDEX + 1); // index 1
        assertEquals("Test failed - expected first element was not returned by previous.", STRING_VALUES[0], stringIterator.previous());

        // Test at middle of list
        intIterator = intList.listIterator(INT_SET_INDEX + 1); // index 2
        assertEquals("Test failed - expected middle element was not returned by previous.", new Integer(INT_VALUES[1]), intIterator.previous());

        // Test at last element of list
        doubleIterator = doubleList.listIterator(DOUBLE_SET_INDEX + 1); // index 3
        assertEquals("Test failed - expected last element was not returned by previous.", new Double(DOUBLE_VALUES[2]), doubleIterator.previous());

        // Test all elements in a list
        charIterator = charList.listIterator(charList.size()); // index 3
        for (int i = charList.size() - 1; i >= 0; i --) {
            assertEquals("Test failed - Unexpected value upon iterative call to previous.", new Character(CHAR_VALUES[i]), charIterator.previous());
        }
    }

    @Test
    public void testIteratorPreviousErrors() {
        buildLists(3);

        // Test call to previous at the beginning of the list
        try {
            stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
            stringIterator.previous();
            fail("Test failed - Iterator must throw NoSuchElementException when calling previous at beginning of list.");
        }
        catch (NoSuchElementException nsee) { /* Test passed */ }

        intList.clear();

        // Test call to previous on an empty list
        try {
            intIterator = intList.listIterator(); // index 0
            intIterator.previous();
            fail("Test failed - Iterator must throw NoSuchElementException when calling previous on an empty list.");
        } catch (NoSuchElementException nsee) { /* Test passed */ }
    }

    @Test
    public void testIteratorPreviousIndex() {
        buildLists(3);

        // Test at beginning of list
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        assertEquals("Test failed - Unexpected previous index at beginning of list.", -1, stringIterator.previousIndex());

        // Test in middle of list
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        assertEquals("Test failed - Unexpected previous index in middle of list.", (INT_SET_INDEX - 1), intIterator.previousIndex());

        // Test at end of list
        charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3
        assertEquals("Test failed - Unexpected previous index value at end of list.", (CHAR_SET_INDEX - 1), charIterator.previousIndex());

        // Test on an empty list
        doubleList.clear();
        doubleIterator = doubleList.listIterator(); // index 0
        assertEquals("Test failed - Unexpected previous index value on an empty list.", -1, doubleIterator.previousIndex());
    }

    @Test
    public void testIteratorAdd() {
        buildLists(3);

        // Add to beginning of list
        stringIterator = stringList.listIterator(STRING_INSERT_INDEX); // index 0
        stringIterator.add(STRING_INSERT_VAL);
        // validate with list's toString method
        assertEquals("Test failed - Incorrect add to beginning of list.", TO_STRING_INSERT[0], stringList.toString());

        // Add to middle of list
        intIterator = intList.listIterator(INT_INSERT_INDEX); // index 1
        intIterator.add(INT_INSERT_VAL);
        assertEquals("Test failed - Incorrect add to middle of list.", TO_STRING_INSERT[1], intList.toString());

        // Add to end of list
        doubleIterator = doubleList.listIterator(DOUBLE_INSERT_INDEX); // index 3
        doubleIterator.add(DOUBLE_INSERT_VAL);
        assertEquals("Test failed - Incorrect add to end of list.", TO_STRING_INSERT[3], doubleList.toString());

        // Add to empty list
        charList.clear();
        charIterator = charList.listIterator();
        charIterator.add(CHAR_VALUES[0]);
        assertEquals("Test failed - Incorrect add to an empty list.", TO_STRING_ADD1[2], charList.toString());

    }

    @Test
    public void testIteratorSet() {
        buildLists(3);

        // Set value at beginning of list using next()
        System.out.println("String list before set: " + stringList);
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        stringIterator.next(); // Must be called prior to call to set
        stringIterator.set(STRING_INSERT_VAL);
        System.out.println("String list after set: " + stringList);
        // validate with toString
        assertEquals("Test failed - Unexpected value after setting first element.", TO_STRING_SET[0], stringList.toString());

        // Set value at middle of list using next()
        System.out.println("Int list before set: " + intList);
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        intIterator.next(); // Must be called prior to call to set
        intIterator.set(INT_INSERT_VAL);
        System.out.println("Int list after set: " + intList);
        // validate with toString
        assertEquals("Test failed - Unexpected value after setting middle element.", TO_STRING_SET[1], intList.toString());

        // Set value at middle of list using previous()
        System.out.println("Double list before set: " + doubleList);
        doubleIterator = doubleList.listIterator(DOUBLE_SET_INDEX); // index 2
        doubleIterator.previous(); // Must be called prior to call to set
        doubleIterator.set(DOUBLE_INSERT_VAL);
        System.out.println("Double list after set: " + doubleList);
        // validate with toString
        assertEquals("Test failed - Unexpected value after setting middle element.", TO_STRING_SET[3], doubleList.toString());

        // Set value at end of list using previous()
        System.out.println("Char list before set: " + charList);
        charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3
        charIterator.previous(); // Must be called prior to call to set
        charIterator.set(CHAR_INSERT_VAL);
        System.out.println("Char list after set: " + charList);
        // validate with toString
        assertEquals("Test failed - Unexpected value after setting last element.", TO_STRING_SET[2], charList.toString());

        // TODO:
        // test for adding to beginning with previous && adding to end with next, validating both with new constants.
    }

    @Test
    public void testIteratorSetError() {
        buildLists(3);

        try {
            System.out.println("String list before set: " + stringList);
            stringIterator = stringList.listIterator(STRING_SET_INDEX);
            stringIterator.set(STRING_INSERT_VAL);
            fail("Test failed - IllegalStateException expected without prior call to next or previous.");
        }
        catch (IllegalStateException ise) {
            System.out.println("String list after set: " + stringList); /* Test Passed */
        }
    }

    @Test
    public void testIteratorRemove() {
        buildLists(3);

        // Remove from beginning of list following call to next
        stringIterator = stringList.listIterator(STRING_SET_INDEX); // index 0
        System.out.println("String list before removal: " + stringList);
        stringIterator.next();
        stringIterator.remove();
        System.out.println("String list after removal: " + stringList);
        // Validate with list's contains method
        assertFalse("Test failed - Element at beginning of list was not removed.", stringList.contains(STRING_VALUES[0]));

        // Remove from middle of list following call to next
        intIterator = intList.listIterator(INT_SET_INDEX); // index 1
        System.out.println("Int list before removal: " + intList);
        intIterator.next();
        intIterator.remove();
        System.out.println("Int list after removal: " + intList);
        // Validate with list's contains method
        assertFalse("Test failed - Element at middle of list was not removed.", intList.contains(INT_VALUES[1]));

        // Remove from middle of list following call to previous
        doubleIterator = doubleList.listIterator(DOUBLE_SET_INDEX); // index 2
        System.out.println("Double list before removal: " + doubleList);
        doubleIterator.previous();
        doubleIterator.remove();
        System.out.println("Double list after removal: " + doubleList);
        // Validate with list's contains method
        assertFalse("Test failed - Element at middle of list was not removed.", doubleList.contains(DOUBLE_VALUES[1]));

        // Remove from end of list following call to previous
        charIterator = charList.listIterator(CHAR_SET_INDEX); // index 3
        System.out.println("Char list before removal: " + charList);
        charIterator.previous();
        charIterator.remove();
        System.out.println("Char list after removal: " + charList);
        // Validate with list's contains method
        assertFalse("Test failed - Element at end of list was not removed.", charList.contains(CHAR_VALUES[2]));
    }

    @Test
    public void testIteratorRemoveError() {
        buildLists(3);

        try {
            System.out.println("Int list before removal: " + intList);
            intIterator = intList.listIterator(INT_SET_INDEX);
            intIterator.remove();
            fail("Test failed - IllegalStateException expected without prior call to next or previous.");
        }
        catch (IllegalStateException ise) {
            System.out.println("Int list after removal: " + intList);
        }
    }

    // endregion ListIterator tests ====================================================================================
    // =================================================================================================================

} // End of class DoubleLinkedListTest