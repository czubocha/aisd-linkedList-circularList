package aisd_3;

import java.util.AbstractList;
import java.util.List;

public class MyCircularLinkedList extends AbstractList<Object> implements List<Object> {

    static int size;
    Element head;

    @Override
    public boolean add(Object element) {
        if (head == null) { // case when list is empty
            head = new Element(element); // new head is created
            head.setNext(head); // head points at itself
        } else {
            Element last; // setting the last element
            for (last = head.getNext(); last.getNext() != head; last = last.getNext())
                ;
            head = new Element(element, head); // creating new element which
            // points at previous head
            last.setNext(head); // setting last element's next on new head
            for (Element current = head.getNext(); current != head; current = current.getNext())
                // increasing indexes
                current.setIndex(current.getIndex() + 1);
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (head == null) { // case when list is empty
            return false;
        } else if (head.getValue() == element) { // case when element to remove
            // is head
            if (head.getNext() == head) // case when list have only one element
                head = null;
            else {
                Element last; // setting the last element
                for (last = head.getNext(); last.getNext() != head; last = last.getNext())
                    ;
                head = head.getNext(); // setting head
                last.setNext(head); // setting last element next on head
                Element current = head.getNext(); // decreasing indexes
                while (current != head) {
                    current.setIndex(current.getIndex() - 1);
                    current = current.getNext();
                }
            }
            size--;
            return true;
        } else { // case when element to remove has index >1
            Element current = head;
            do {
                if (current.getNext().getValue() == element) {
                    current.setNext(current.getNext().getNext()); /*
                                   * deleting
								   * element by
								   * setting
								   * previous
								   * element's
								   * next to
								   * following
								   * element
								   */
                    for (Element temp = current.getNext(); temp != head; temp = temp.getNext())
                        // decreasing indexes after deleted element
                        temp.setIndex(temp.getIndex() - 1);
                    size--;
                    return true;
                }
                current = current.getNext();
            } while (current != head);
        }
        return false;
    }

    void przejdzXRazy(int x) {
        int counter = 0;
        Element current = head;
        while (counter < x) {
            System.out.println(current);
            current = current.getNext();
            if (current == head) {
                counter++;
                System.out.println("By³em w head " + counter + " raz.");
            }
        }
    }

    @Override
    public Object get(int index) {
        if (head == null)
            return null;
        Element current = head;
        int i = 0;
        while (i != index) {
            i++;
            current = current.getNext();
        }
        return current.getValue();

    }

    @Override
    public int size() {
        if (head == null)
            return 0;
        int size = 0;
        Element current = head;
        do {
            size++;
            current = current.getNext();
        } while (current != head);
        return size;
    }

    int size2() {
        return size;
    }

    @Override
    public String toString() {
        if (head == null)
            return null;
        StringBuffer buffer = new StringBuffer();
        Element current = head;
        do {
            buffer.append(current + ", ");
            current = current.getNext();
        } while (current != head);
        buffer.setLength(buffer.length() - 2);
        return buffer.toString();
    }

    @Override
    public Object remove(int index) {
        if (head != null && index == 0) {
            Element remove = head;
            head = head.getNext();
            for (Element current = head; current != null; current = current.getNext())
                current.setIndex(current.getIndex() - 1);
            size--;
            return remove;
        } else {
            for (Element current = head; head != null && current.getNext() != null; current = current.getNext())
                if (current.getNext().getIndex() == index) {
                    Element remove = current.getNext();
                    current.setNext(current.getNext().getNext());
                    for (Element current2 = current.getNext(); current2 != null; current2 = current2.getNext())
                        current2.setIndex(current2.getIndex() - 1);
                    size--;
                    return remove;
                }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > this.size() - 1)
            throw new IndexOutOfBoundsException();
        else if (index == 0) {
            head = new Element(element, head);
            for (Element current = head.getNext(); current != null; current = current.getNext())
                current.setIndex(current.getIndex() + 1);
            size++;
        } else {
            for (Element current = head; current.getNext() != null; current = current.getNext())
                if (current.getNext().getIndex() == index) {
                    current.setNext(new Element(element, current.getNext(), index));
                    for (Element current2 = current.getNext().getNext(); current2 != null; current2 = current2
                            .getNext())
                        current2.setIndex(current2.getIndex() + 1);
                }
        }
    }

    Element findPrev(Object value) {
        Element current = head;
        do {
            if (current.getNext().getValue() == value)
                break;
            current = current.getNext();
        } while (current.getNext() != head);
        return current;
    }

    @Override
    public Object set(int index, Object element) {
        Element onIndexPrev = findPrev(get(index));
        Element removed = onIndexPrev.getNext();
        onIndexPrev.setNext(new Element(element, onIndexPrev.getNext().getNext(), index));
        if (index == 0)
            head = onIndexPrev.getNext();
        return removed;

    }

    class Element extends Object implements Comparable<Element> {
        private Element _next;
        private Object _value;
        private int _index;

        Element() {

        }

        Element(Object value) {
            setValue(value);
        }

        Element(Object value, Element next) {
            setValue(value);
            setNext(next);
        }

        Element(Object value, Element next, int index) {
            setValue(value);
            setNext(next);
            setIndex(index);
        }

        @Override
        public String toString() {
            return this.getIndex() + " " + this.getValue();
        }

        Element getNext() {
            return _next;
        }

        void setNext(Element next) {
            this._next = next;
        }

        Object getValue() {
            return _value;
        }

        void setValue(Object value) {
            this._value = value;
        }

        int getIndex() {
            return _index;
        }

        void setIndex(int index) {
            this._index = index;
        }

        @Override
        public int compareTo(Element e) {
            return ((String) getValue()).compareTo((String) e.getValue());
        }
    }
}
