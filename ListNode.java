package semester2;

public class ListNode {
    int value;
    ListNode next;
    ListNode previous;
    ListNode neighborhood;

    public ListNode(int value) {
        this.value = value;
        this.next = null;
        this.previous = null;
        this.neighborhood = null;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }

    public ListNode getPrevious() {
        return previous;
    }

    public void setPrevious(ListNode previous) {
        this.previous = previous;
    }

    public ListNode getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(ListNode neighborhood) {
        this.neighborhood = neighborhood;
    }

    public void neighborhood(int radius) {
       
        ListNode neighborhoodStart = this;
        ListNode neighborhoodEnd = this;
        
      
        for (int i = 0; i < radius; i++) {
            if (neighborhoodStart.getPrevious() != null) {
                neighborhoodStart = neighborhoodStart.getPrevious();
            }
        }
        
      
        for (int i = 0; i < radius; i++) {
            if (neighborhoodEnd.getNext() != null) {
                neighborhoodEnd = neighborhoodEnd.getNext();
            }
        }
        
      
        this.setNeighborhood(neighborhoodStart);
        neighborhoodStart.setNext(neighborhoodEnd);
    }

    
    public int rule30(int left, int center, int right) {
       
        if ((left == 1 && center == 1 && right == 1) ||
            (left == 1 && center == 1 && right == 0) ||
            (left == 1 && center == 0 && right == 1) ||
            (left == 0 && center == 0 && right == 0)) {
            return 0;
        } else {
            return 1;
        }
    }
}
