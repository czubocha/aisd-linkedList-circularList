package aisd_3;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

public class MyLinkedList extends AbstractList<Object> implements List<Object> {

    Element head;

    @Override
    public boolean add(Object element) {
        if (head == null)
            head = new Element(element);
        else {
            head = new Element(element, head);
            for (Element temp = head.getNext(); temp != null; temp = temp.getNext())
                temp.setIndex(temp.getIndex() + 1);
        }
        return true;
    }

    @Override
    public boolean remove(Object element) {
        if (head == null) {
            return false;
        } else if (head.getValue() == element) {
            head = head.getNext();
            for (Element current = head; current != null; current = current.getNext())
                current.setIndex(current.getIndex() - 1);
            return true;
        } else {
            for (Element current = head; current.getNext() != null; current = current.getNext())
                if (current.getNext().getValue() == element) {
                    current.setNext(current.getNext().getNext());
                    for (Element temp = current.getNext(); temp != null; temp = temp.getNext())
                        temp.setIndex(temp.getIndex() - 1);
                    return true;
                }
        }
        return false;
    }

    @Override
    public Object get(int index) {
        for (Element current = head; current != null; current = current.getNext())
            if (current.getIndex() == index)
                return current;
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        int size = 0;
        for (Element current = head; current != null; current = current.getNext())
            size++;
        return size;
    }

    @Override
    public String toString() {
        if (head == null)
            return null;
        StringBuffer buffer = new StringBuffer();
        Iterator<Object> it = this.iterator();
        while (it.hasNext())
            buffer.append(it.next() + ", ");
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
            return remove;
        } else {
            for (Element current = head; head != null && current.getNext() != null; current = current.getNext())
                if (current.getNext().getIndex() == index) {
                    Element remove = current.getNext();
                    current.setNext(current.getNext().getNext());
                    for (Element current2 = current.getNext(); current2 != null; current2 = current2.getNext())
                        current2.setIndex(current2.getIndex() - 1);
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

    public Element func(Element a) {

        if (a != null && a._next != null) {

            Element pom = a;
            a = a._next;
            pom._next = func(a._next);
            a._next = pom;

        }

        return a;
    }

    class Element {
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
            return _value.toString();
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
    }
}
