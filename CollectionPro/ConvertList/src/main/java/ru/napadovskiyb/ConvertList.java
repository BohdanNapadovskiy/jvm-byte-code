package ru.napadovskiyb;


/**
 * Package of CollectionPro dop task.
 * Class convert list.
 * @param <T> generic.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 28.08.2017

 */
public class ConvertList<T>  {


    /**
     * root element.
     */
    private Node root;

    /**
     * last element.
     */
    private Node lastElem;

    /**
     * Method return last element.
     * @return last element.
     */
    public Node returnLastElement() {
        return this.lastElem;
    }


    /**
     * Method add element to list.
     * @param elem element.
     */
    public void add(Node elem) {
        if (this.root == null) {
            this.root = elem;
        } else {
            this.lastElem.setNext(elem);
        }
        this.lastElem = elem;
    }

    /**
     * Method convert list.
     * @param root root element.
     */
    public void convert(Node root) {
        Node tmpRoot = root.getNext();
        this.lastElem = root;
        Node prev = root;
        prev.setNext(null);
        while (tmpRoot != null) {
            Node tmpNext = tmpRoot.getNext();
            this.root = tmpRoot;
            tmpRoot.setNext(prev);
            prev = tmpRoot;
            tmpRoot = tmpNext;
        }

    }



}
