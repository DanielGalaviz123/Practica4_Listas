package com.example.practica4_listas.logic;

public class LinkedList<T> {

    private Node<T> first;

    public LinkedList() {
        first=null;
    }

    public void addFirst(T data) {
        Node<T> n = new Node<>(data);
        n.setNext(first);
        first=n;
    }

    public void addLast(T data) {
        Node<T> n=new Node<>(data);

        if(first == null) {
            first=n;
        }
        else {
            Node<T> r=first;

            while(r.getNext()!=null) {
                r=r.getNext();
            }
            r.setNext(n);
        }
    }


    public T removeFirst() {
        if(first==null)
            return null;

        T data=first.getData();
        first=first.getNext();

        return data;
    }

    public T removeLast() {
        if(first == null)
            return null;

        if(first.getNext()==null) {
            T data=first.getData();
            first=null;
            return data;
        }

        Node<T> r=first;
        Node<T> previous=null;

        while(r.getNext() != null) {
            previous=r;
            r=r.getNext();
        }

        previous.setNext(null);
        return r.getData();
    }


    public String showList() {
        String result="";

        if(first==null)
            return "Empty list";

        Node<T> r=first;
        while(r!=null) {
            result+=r.getData()+"\n";
            r=r.getNext();
        }
        return result;
    }

    public T get(int position) {

        Node<T> r=first;
        int i=0;

        while(r != null) {
            if(i == position)
                return r.getData();

            r=r.getNext();
            i++;
        }

        return null;
    }

    public int size() {
        int total=0;
        Node<T> r = first;

        while(r!=null) {
            total++;
            r = r.getNext();
        }
        return total;
    }

}