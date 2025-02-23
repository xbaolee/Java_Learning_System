/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adt;

import java.io.Serializable;

/**
 *
 * @author lee xue bao
 *
 */
public class CircularLinkedList<T> implements ListInterface<T>, Serializable {

    private Node<T> head;

    private int getNumberOfEntries = 0;

    public class Node<T> implements Serializable {

        /* Data item in the node */
        T item;

        /* Pointer to next node */
        Node<T> next;

        /* Constructor to create a node */
        public Node(T item) {
            this.item = item;
        }

    }

    public void insertAtBeginning(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            head = newNode;
            head.next = head;
        } else {
            Node<T> temp = head;
            newNode.next = temp;
            head = newNode;
        }
        getNumberOfEntries++;
    }

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<T>(value);
        if (head == null) {
            /* When list is empty */
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        newNode.next = head;
        getNumberOfEntries++;
    }

    @Override
    public boolean add(int position, T value) {
        if (position < 0 || position > getNumberOfEntries) {
            System.out.println("Position is Invalid");
            return false;
        }

        Node<T> newNode = new Node<>(value);
        Node<T> tempNode = head;
        Node<T> prevNode = null;
        for (int i = 0; i < position; i++) {
            if (tempNode.next == head) {
                break;
            }
            prevNode = tempNode;
            tempNode = tempNode.next;
        }
        prevNode.next = newNode;
        newNode.next = tempNode;
        getNumberOfEntries++;
        return true;
    }

    public void deleteFromBeginning() {

        Node<T> temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = head.next;
        head = head.next;
        getNumberOfEntries--;
    }

  
    @Override
public void remove(int position) {
    if (isEmpty()) {
        System.out.println("List is empty");
        return;
    }

    if (position < 0 || position >= getNumberOfEntries()) {
        System.out.println("Invalid position");
        return;
    }

    if (position == 0) {
        deleteFromBeginning();
        return;
    }

    Node<T> current = head;
    for (int i = 0; i < position - 1; i++) { 
        current = current.next;
    }

   
    current.next = current.next.next;

    if (current.next == head) {
        head = current;
    }

    getNumberOfEntries--;
}


    public Node<T> searchByIndex(int index) {
        if (index < 0 || index >= getNumberOfEntries) {
            System.out.println("Position is Invalid");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    @Override
    public boolean replace(int index, T newEntry) {
        if (index < 0 || index >= getNumberOfEntries) {
            System.out.println("Position is Invalid");
            return false;
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;

        }
        temp.item = newEntry;
        return true;
    }

    @Override
    public T getEntry(int index) {
        if (index < 0 || index >= getNumberOfEntries) {
            System.out.println("Position is Invalid");
        }
        Node<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    public int getIndex(T value) {
        Node<T> temp = head;
        int i = 0;
        while (null != temp && temp.item != value) {
            temp = temp.next;
            i++;
        }
        if (temp.item == value) {
            return i;
        }
        return i;
    }

    @Override
    public boolean contains(T value) {
        Node<T> temp = head;
        while (temp != null) {
            if (((T) temp.item).equals(value)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public int getNumberOfEntries() {
        return getNumberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return getNumberOfEntries == 0;
    }

    @Override
    public void clear() {
        getNumberOfEntries = 0;
    }

    @Override
    public boolean isListEmpty() {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public boolean isListFull() {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public int totalNumberOfObject() {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public T getObject(int positionIndex) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public boolean replace(T anObject, T newObject) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public boolean remove(T anObject) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public boolean addObject(T newObject) {
        throw new UnsupportedOperationException("Invalid operation");
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        if (head == null) {
            System.out.println("List is Empty !!");

        } else {
            Node<T> temp = head;
            buf.append(" ");
            while (temp.next != head) {
                buf.append(temp.item).append(" ");
                temp = temp.next;
            }
            buf.append(temp.item).append(" ");

        }
        return buf.toString();

    }

}
