import java.util.ArrayList;
import java.util.NoSuchElementException;

class LinkedList<T> {
    Node<T> first;
    Node<T> last;
    int size = 0;

    @SuppressWarnings("hiding")
    private class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    // insert after
    public void insertAfter(T item, T value) {
        Node<T> node = new Node<>(value);
        Node<T> currNode = first;

        if (isEmpty())
            addFirst(value);
        else {
            while (currNode.next != null && currNode.value != item)
                currNode = currNode.next;

            node.next = currNode.next;
            currNode.next = node;
        }
    }

    public void insertBefore(T item, T value) {
        Node<T> node = new Node<>(value);
        Node<T> currNode = first;

        Node<T> prev = null;
        while (currNode != null && currNode.value != item) {
            prev = currNode;
            currNode = currNode.next;
        }

        if (currNode == null || prev == null) {
            addFirst(value);
            return;
        }
        node.next = currNode;
        prev.next = node;

    }

    // add Last
    public void addLast(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty())
            first = last = node;
        else {
            last.next = node;
            last = last.next;
        }
        size++;
    }

    // add first
    public void addFirst(T item) {
        Node<T> node = new Node<>(item);
        if (isEmpty())
            first = last = node;
        else {
            node.next = first;
            first = node;
        }
        size++;
    }

    // index of
    public int indexOf(T item) {
        Node<T> current = first;
        int index = 0;

        while (current != null) {
            if (current.value == item)
                return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == last)
            first = last = null;
        else {
            first = first.next;
        }
        size--;
    }

    public void removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();
        if (first == null)
            first = last = null;
        else {
            Node<T> prevNode = first;
            while (prevNode.next != last)
                prevNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        }
        size--;
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public int size() {
        return size;
    }

    public ArrayList<T> toArray() {
        ArrayList<T> array = new ArrayList<>();
        Node<T> current = first;
        while (current != null) {
            array.add(current.value);
            current = current.next;
        }
        return array;
    }

    private boolean isEmpty() {
        return first == null;
    }

}

class DoublyLinkedList<T> {
    Node<T> first;
    Node<T> last;

    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public void addLast(T value) {
        Node<T> node = new Node<>(value);

        if (first == null) {
            first = last = node;
        } else {
            node.prev = last;
            last.next = node;
            last = node;
        }
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value);

        if (first == null) {
            first = last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    public void insertBefore(T key, T value) {
        Node<T> node = new Node<>(value);

        if (first == null)
            first = last = node;
        else {
            Node<T> currNode = first;
            while (currNode != null && currNode.value != key)
                currNode = currNode.next;
            if (currNode == null || currNode.value == first.value) {
                addFirst(value);
                return;
            }
            node.next = currNode;
            node.prev = currNode.prev;
            if (currNode.prev != null) {
                currNode.prev.next = node;
            }
            currNode.prev = node;
        }
    }

    public void insertAfter(T key, T value) {
        Node<T> node = new Node<>(value);

        if (first == null)
            first = last = node;
        else {
            Node<T> currNode = first;

            while (currNode != null && currNode.value != key)
                currNode = currNode.next;
            if(currNode == null || currNode.value == last.value){
                addLast(value);
                return;
            }
            node.prev = currNode;
            node.next = currNode.next;
            if(currNode.next != null){
                currNode.next.prev = node;
            }
            currNode.next = node;

        }
    }

    public void display() {
        Node<T> currNode = first;
        System.out.println("Forward");
        System.out.print("[");
        while (currNode != null) {
            System.out.print(currNode.value + ", ");
            currNode = currNode.next;
        }
        System.out.println("]");

        System.out.println("Backward");
        currNode = last;
        System.out.print("[");
        while (currNode != null) {
            System.out.print(currNode.value + ", ");
            currNode = currNode.prev;
        }
        System.out.println("]");

    }
}

public class Main {
    public static void main(String[] args) {
        // LinkedList<Integer> list = new LinkedList<>();
        // list.addLast(10);
        // list.addLast(20);
        // list.addLast(30);
        // list.addFirst(50);
        // list.insertAfter(20, 100);
        // list.insertBefore(100, 150);
        // System.out.println(list.toArray().toString());
        // System.out.println(list.size());
        // System.out.println(list.indexOf(20));
        // System.out.println(list.contains(20));
        // list.removeFirst();
        // System.out.println(list.size());
        // list.removeLast();
        // System.out.println(list.size());

        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        list.addLast(5);
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(40);
        list.insertBefore(30, 150);
        list.insertAfter(40, 200);
        list.display();
    }
}