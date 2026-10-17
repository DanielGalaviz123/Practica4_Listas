package com.example.practica4_listas.logic;

public class CircularList<T> {

    private DoubleNode<T> first;
    private DoubleNode<T> last;


    public CircularList() {
        first=null;
        last=null;
    }

    public void addFirst(T data) {
        DoubleNode<T> n = new DoubleNode<>(data);

        if(first == null) {
            first=last=n;
            n.setNext(first);
            n.setPrev(last);
        }
        else {
            n.setNext(first);
            n.setPrev(last);
            first.setPrev(n);
            last.setNext(n);
            first=n;
        }
    }

    public void addLast(T data) {
        DoubleNode<T> n=new DoubleNode<>(data);

        if(first==null) {
            first=last=n;
            n.setNext(first);
            n.setPrev(last);
        }
        else {
            n.setNext(first);
            n.setPrev(last);

            last.setNext(n);
            first.setPrev(n);
            last=n;
        }
    }


    public T removeFirst() {
        if(first == null)
            return null;

        T data=first.getData();

        if(first==last) {
            first=null;
            last=null;
        }
        else {
            first=first.getNext();
            last.setNext(first);
            first.setPrev(last);
        }
        return data;
    }

    public T removeLast() {
        if(first==null)
            return null;

        T data=last.getData();

        if(first == last) {
            first=last=null;
        }
        else {
            last=last.getPrev();
            last.setNext(first);
            first.setPrev(last);
        }

        return data;
    }


}