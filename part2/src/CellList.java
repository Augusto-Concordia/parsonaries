//COMP 249 - Assignment #4
//Due Date: April 15th
//Written by: Augusto Mota Pinheiro (40208080) & Michaël Gugliandolo (40213419)

/**
 * Linked list class containing cellphones
 */
public class CellList {
    /**
     * Node to be used in the outer class's linked list class.
     */
    private class CellNode {

        /**
         * Cellphone object
         */
        private CellPhone cellPhone;

        /**
         * Next node in the list
         */
        private CellNode next;

        /**
         * Default constructor
         */
        public CellNode() {
            this(null, null);
        }

        /**
         * Parametrized constructor for this node.
         * @param cellPhone object to be stored in this node
         * @param next next node in the linked list
         */
        public CellNode(CellPhone cellPhone, CellNode next) {
            this.cellPhone = cellPhone;
            this.next = next;
        }

        /**
         * Copy constructor for this node.
         * @param cellNode node to be copied
         */
        public CellNode(CellNode cellNode) {
            this(new CellPhone(cellNode.cellPhone, cellNode.cellPhone.getSERIAL_NUM()), cellNode.next == null ? null : new CellNode(cellNode.next));
        }

        /* GETTERS AND SETTERS */

        /**
         * Getter for the cell phone object
         * @return cell phone object
         */
        public CellPhone getCellPhone() {
            return cellPhone;
        }

        /**
         * Setter for the cell phone object
         * @param cellPhone cell phone object
         */
        public void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone;
        }

        /**
         * Getter for the next node
         * @return next node
         */
        public CellNode getNext() {
            return next;
        }

        /**
         * Setter for the next node
         * @param next next node
         */
        public void setNext(CellNode next) {
            this.next = next;
        }

        /**
         * Returns a copy of cell phone object with the given serial number.
         * @param SERIAL_NUM serial number of the cell phone object to be returned
         * @return cell phone object copy with the given serial number
         */
        public CellNode clone(long SERIAL_NUM) {
            return new CellNode(new CellPhone(cellPhone, SERIAL_NUM), next);
        }

        /**
         * Tests if this node is equal to the given object.
         * @param o object to be tested
         * @return true if the given object is equal to this node, false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CellNode)) return false;

            CellNode cellNode = (CellNode) o;

            if (!cellPhone.equals(cellNode.cellPhone)) return false;

            return next != null ? next.equals(cellNode.next) : cellNode.next == null;
        }

        /**
         * Returns a string representation of this node.
         * @return string representation of this node
         */
        @Override
        public String toString() {
            return cellPhone.toString() + " => " + next.cellPhone.getSERIAL_NUM();
        }
    }

    /**
     * Number of elements in the linked list.
     */
    private int size;

    /**
     * Head of the linked list
     */
    private CellNode head;

    /**
     * Default constructor
     */
    public CellList() {
        this(0, null);
    }

    /**
     * Copy constructor
     * @param cellList list to be copied
     */
    public CellList(CellList cellList) {
        this.size = cellList.size;
        this.head = new CellNode(cellList.head);
    }

    /**
     * Parametrized constructor
     * @param size number of elements in the list
     * @param head head of the linked list
     */
    private CellList(int size, CellNode head) {
        this.size = size;
        this.head = head;
    }

    /**
     * Adds a cell phone to the start of the list
     * @param newHead cell phone to be added
     */
    public void addToStart(CellPhone newHead) {
        if (find(newHead.getSERIAL_NUM(), false) != null) return;
        size++;

        if (head == null) {
            head = new CellNode(newHead, null);
            return;
        }

        head = new CellNode(newHead, head);
    }

    /**
     * Adds a cell phone to the specified position in the list
     * @param cellPhone cell phone to be added
     * @param index position in the list
     * @throws NoSuchElementException if the index is out of range
     */
    public void insertAtIndex(CellPhone cellPhone, int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) throw new NoSuchElementException();
        if (head == null) return;

        CellNode prev = null;
        CellNode temp = head;

        for (int i = 0; i < index; i++) {
            prev = temp;

            if (temp.getNext() == null) break;

            temp = temp.getNext();
        }

        temp = new CellNode(cellPhone, temp);
        if (prev != null) prev.setNext(temp);

        size++;
    }

    /**
     * Deletes the cell phone at the specified position in the list
     * @param index position in the list
     * @throws NoSuchElementException if the index is out of range
     */
    public void deleteFromIndex(int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) throw new NoSuchElementException();
        if ( head == null) return;

        CellNode prev = null;
        CellNode temp = head;

        for (int i = 0; i < index; i++) {
            prev = temp;

            if (temp.getNext() == null) break;

            temp = temp.getNext();
        }

        if (prev != null) prev.setNext(temp.getNext());
        else head = null;

        size--;
    }

    /**
     * Deletes the head of the list
     */
    public void deleteFromStart() {
        if (head == null) return;
        head = head.getNext() == null ? head : head.getNext();
        size--;
    }

    /**
     * Replaces the cell phone at the specified position in the list
     * @param cellPhone cell phone to be replaced
     * @param index position in the list
     * @throws NoSuchElementException if the index is out of range
     */
    public void replaceAtIndex(CellPhone cellPhone, int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) throw new NoSuchElementException();
        if ( head == null) return;

        CellNode temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        temp.setCellPhone(cellPhone);
    }

    /**
     * Finds the cell phone with the specified serial number
     * @param serialNum serial number of the cell phone
     * @param outputIterations if true, the number of iterations is printed to the console
     * @return cell phone with the specified serial number
     */
    public CellNode find(long serialNum, boolean outputIterations) {
        if (head == null) return null;

        CellNode temp = head;
        int iterations = 0;

        while (temp.getCellPhone().getSERIAL_NUM() != serialNum) {
            iterations++;
            temp = temp.getNext();

            if (temp == null) break;
        }

        if (outputIterations) System.out.println("Took " + iterations + " iterations to complete search.");

        return temp;
    }

    /**
     * Checks if the list contains the specified serial number
     * @param serialNum serial number of the cell phone
     * @return true if the list contains the specified serial number, false otherwise
     */
    public boolean contains(long serialNum) {
        CellNode temp = head;

        while (temp.getCellPhone().getSERIAL_NUM() != serialNum) {
            temp = temp.getNext();

            if (temp == null) return false;
        }

        return true;
    }

    /**
     * String representation of the contents of the list
     * @return string representation of the contents of the list
     */
    public String showContents() {
        if (head == null) return "[ EMPTY ]";

        StringBuilder builder = new StringBuilder().append("This list contains ").append(size).append(" elements. Here are the contents of the list:\n=======================================================\n");
        CellNode temp = head;

        while (temp != null) {
            builder.append(temp.getCellPhone()).append(" => ");
            temp = temp.getNext();
        }

        return builder.toString();
    }

    /**
     * Checks if the list is equal to another list
     * @param list list to be compared to
     * @return true if the lists are equal, false otherwise
     */
    public boolean equals(CellList list) {
        if (this == list) return true;
        if (list == null || list.size != this.size) return false;

        boolean isEqual = true;
        CellNode temp = head;
        CellNode listTemp = list.head;

        for (int i = 0; i < size; i++) {
            isEqual = temp.equals(listTemp);

            temp = temp.getNext();
            listTemp = listTemp.getNext();
        }

        return isEqual;
    }
}
