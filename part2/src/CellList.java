public class CellList {

    private class CellNode {
        private CellPhone cellPhone;
        private CellNode next;

        public CellNode() {
            cellPhone = null;
            next = null;
        }

        public CellNode(CellPhone cellPhone, CellNode next) {
            this.cellPhone = cellPhone;
            this.next = next;
        }

        public CellNode(CellNode cellNode) {
            this.cellPhone = cellNode.cellPhone;
            this.next = cellNode.next;
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

        public CellNode clone(CellNode cellNode) {
            return new CellNode(cellNode.cellPhone, cellNode.next);
        }
    }


    private int size;
    private CellNode head;

    public CellList() {
        size = 0;
        head = null;
    }

    public CellList(CellList cellList) {
        this.size = cellList.size;
        this.head = cellList.head;
    }

    public void addToStart(CellPhone newHead) {

    }

    public void insertAtIndex(CellPhone cellPhone, int index) {

    }

    public void deleteFromIndex(int index) {

    }

    public void deleteFromStart() {

    }

    public void replaceAtIndex(CellPhone cellPhone, int index) {

    }

    public CellNode find(long serialNum) {
        System.out.println("Took 0 iterations to figure out that you won't find anything in an incomplete class.");
        return null;
    }

    public boolean contains(long serialNum) {
        return false;
    }

    public String showContents() {
        return "";
    }

    public boolean equals(CellList list) {
        return false;
    }
}
