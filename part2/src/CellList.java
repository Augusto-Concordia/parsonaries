public class CellList {

    private class CellNode {
        private CellPhone cellPhone;
        private CellNode next;

        public CellNode() {
            this(null, null);
        }

        public CellNode(CellPhone cellPhone, CellNode next) {
            this.cellPhone = cellPhone;
            this.next = next;
        }

        public CellNode(CellNode cellNode) {
            this(new CellPhone(cellNode.cellPhone, cellNode.cellPhone.getSERIAL_NUM()), cellNode == null || cellNode.next == null ? null : new CellNode(cellNode.next));
        }

        public CellPhone getCellPhone() {
            return cellPhone;
        }

        public void setCellPhone(CellPhone cellPhone) {
            this.cellPhone = cellPhone;
        }

        public CellNode getNext() {
            return next;
        }

        public void setNext(CellNode next) { //todo aren't these useless cuz we can access the variables in the outer class
            this.next = next;
        }

        public CellNode clone(long SERIAL_NUM) {
            return new CellNode(new CellPhone(cellPhone, SERIAL_NUM), next);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CellNode)) return false;

            CellNode cellNode = (CellNode) o;

            if (!cellPhone.equals(cellNode.cellPhone)) return false;

            return next != null ? next.equals(cellNode.next) : cellNode.next == null;
        }

        @Override
        public int hashCode() {
            int result = cellPhone.hashCode();
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return cellPhone.toString() + " => " + next.cellPhone.getSERIAL_NUM();
        }
    }

    private int size;
    private CellNode head;

    public CellList() {
        this(0, null);
    }

    public CellList(CellList cellList) {
        this.size = cellList.size;
        this.head = new CellNode(cellList.head);
    }

    private CellList(int size, CellNode head) {
        this.size = size;
        this.head = head;
    }

    public void addToStart(CellPhone newHead) {
        if (find(newHead.getSERIAL_NUM(), false) != null) return;
        size++;

        if (head == null) {
            head = new CellNode(newHead, null);
            return;
        }

        head = new CellNode(newHead, head);
    }

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

    public void deleteFromStart() {
        head = head == null || head.getNext() == null ? head : head.getNext();
        size--;
    }

    public void replaceAtIndex(CellPhone cellPhone, int index) throws NoSuchElementException {
        if (index < 0 || index > size - 1) throw new NoSuchElementException();
        if ( head == null) return;

        CellNode temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        temp.setCellPhone(cellPhone);
    }

    public CellNode findAtIndex(int index) {
        if (index < 0 || index > size || head == null) return null;

        CellNode prev = null;
        CellNode temp = head;

        for (int i = 0; i < index; i++) {
            prev = temp;

            if (temp.getNext() == null) break;

            temp = temp.getNext();
        }
        return temp;
    }

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

    public boolean contains(long serialNum) {
        CellNode temp = head;

        while (temp.getCellPhone().getSERIAL_NUM() != serialNum) {
            temp = temp.getNext();

            if (temp == null) return false;
        }

        return true;
    }

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
