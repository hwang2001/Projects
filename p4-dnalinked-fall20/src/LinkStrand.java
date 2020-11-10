/**
 * This utility class creates a link strand
 *
 * @author Jeffery Tan and Harry Wang
 *
 *
 */

public class LinkStrand implements IDnaStrand{
    /**
     * Node class used to implement LinkStrand
     *
     * String s is the string used to store in info for the node
     */
    private class Node {
        String info;
        Node next;
        public Node (String s){
            info = s;
            next = null;
        }
    }
    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex, myLocalIndex;
    private Node myCurrent;


    public LinkStrand(){
        this("");
    }
    /**
     * Create a Link strand representing s. No error checking is done to see if s
     * represents valid genomic/DNA data.
     *
     * @param s

     */

    public LinkStrand(String s) {
        initialize(s);
    }
    /**
     * Initialize by creating node from the input string
     * replacing any data that was stored. The parameter should contain only
     * valid DNA characters, no error checking is done by the this method.
     * @param source
     *            is the string used to initialize this strand
     *
     * creates a new node using source and sets myFirst as the node
     * initializes myAppends to 0
     * sets mySize to the length of source
     */
    @Override
    public void initialize(String source){
        this.myFirst = new Node(source);
        this.myLast = myFirst;
        myAppends = 0;
        mySize = source.length();
        myIndex = 0;
        myCurrent = myFirst;
        myLocalIndex = 0;
    }
    /**
     * Return this object, useful to obtain
     * an object without knowing its type, e.g.,
     * calling dna.getInstance() returns an IDnaStrand
     * that will be the same concrete type as dna
     * @param source is data from which object constructed
     * @return an IDnaStrand whose .toString() method will be source
     */
    @Override
    public IDnaStrand getInstance(String source){
        return new LinkStrand(source);
    }
    public long size(){
        return mySize;
    }

    /**
     * Simply append a node of dna data to this linnked list. No error checking is
     * done. This method is  efficient using a linked list
     *
     * increment number of Appends
     * increment size by length of dna appended
     *
     * @param dna
     *            is the String appended to this strand
     *
     */
    @Override
    public IDnaStrand append(String dna) {
        Node newLast = new Node(dna);
        myLast.next = newLast;
        mySize += dna.length();
        myAppends++;
        return this;
    }
    @Override
    public int getAppendCount(){
        return myAppends;
    }
    /**
     * Adds info in list to StringBuilder
     * @return DNA as string
     *
     */
    @Override
    public String toString(){
        Node list = this.myFirst;
        StringBuilder ret = new StringBuilder();
        while(list != null){
            ret.append(list.info);
        }
        return ret.toString();
    }

    /**
     * Creates new IdnaStrand that is the reverse. It also reverses the dna string inside the new IdnaStrand
     * e.g., for "ABC" it becomes "CBA"
     * @return reversed strand
     */
    @Override
    public IDnaStrand reverse() {
        LinkStrand copy = this; //creates copy of linked list to iterate through
        StringBuilder revnode = new StringBuilder(myFirst.info); //creates String Builder of first node
        //will use revnode to reverse DNA strands in each node
        revnode.reverse();

        LinkStrand ret = new LinkStrand(revnode.toString()); //linkStrand to return


        while(ret.size() < mySize){
            copy.myFirst = copy.myFirst.next;
            Node tmp = copy.myFirst;
            revnode = new StringBuilder();
            revnode.append(tmp.info);
            revnode.reverse();
            ret.append(revnode.toString());
        }

        return ret;

    }
    @Override
    public char charAt(int index){
        if (index < 0 || index > mySize - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (myIndex >= index) {
            int count = 0;
            int dex = 0;
            Node list = myFirst;
        } else {
            int count = myIndex;
            int dex = myLocalIndex;
            Node list = myCurrent;
        }
        while (count < index) {
            count++;
            dex++;
            if (dex >= list.info.length()) {
                dex = 0;
                list = list.next;
            }
        }
        myIndex = index;
        myLocalIndex = dex;
        myCurrent = list;
        return list.info.charAt(dex);
    }
}

