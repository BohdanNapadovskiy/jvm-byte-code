package ru.napadovskiyb;


import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class implements interface SimpleTree.
 * extends Comparable.
 * @param <E> generic
 */
class MySimpleTree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * root element.
     */
    private Node<E> root;

    /**
     * size of tree.
     */
    private int size = 0;

    /**
     *
     */
    private int indexLastElement = 0;

    /**
     *
     */
    private boolean isBinary;


    /**
     * Method check element with parent.
     * @param first element for check.
     * @param second element with check
     * @return result.
     */
    public Node<E> search(Node<E> first, E second) {
        Node<E> result = null;

        if (first.value.compareTo(second) == 0) {
            result = first;
        } else {
            for (Node<E> child : first.children) {
                result = search(child, second);
            }
        }

      return result;
    }

    /**
    * Method return list children of root.
    * @return child list.
    */
    public List<Node<E>> getListOfChild() {
        return root.getChildren();
    }

    /**
     * Method add element to collection.
     * @param parent parent.
     * @param child child.
     * @return result.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = true;
        if (this.root == null) {
            this.root = new Node<>(parent);
            this.root.children.add(new Node<>(child));
        } else {
            Node<E> root = search(this.root, parent);
            if (root != null) {
                result = root.children.add(new Node<>(child));
            } else {
                result = false;
            }
        }
        return result;
    }


    /**
     *Method return size of tree.
     * @return size tree.
     */
    public int getSize() {
        return size;
    }

    /**
     * Method feel ArrayList for iterator.
     * @param resultQueue list of iterator.
     * @param top parent.
     * @return list.
     */
    private Queue<Node<E>> filAllElementByTree(Queue<Node<E>> resultQueue, Node<E> top) {
        if (resultQueue.size() == 0) {
            resultQueue.add(top);
        }

        for (Node<E> tmpTop :top.getChildren()) {
            resultQueue.add(tmpTop);
            filAllElementByTree(resultQueue, tmpTop);
        }

        return resultQueue;
    }

    /**
     * Method check is binary tre..
     * @return result.
     */
    public boolean isBinary() {
        return checkBinary(this.root);
    }

    /**
     * Method che is tree binary.
     * @param top top element.
     * @return result.
     */
    private boolean checkBinary(Node<E> top) {
        boolean result = false;
         if (top.getChildren().size() >= 2) {
             result = true;
         }
        int index = 0;
        if ((!result) & (index < top.getChildren().size())) {
            Node<E> tmpTop = top.getChildren().get(index);
            result = checkBinary(tmpTop);
            index++;
        }
        return result;
    }

    /**
     * Method add element to tree.
     * @param e value.
     */
    public void add(E e) {
        Node<E> t = this.root;
        Node<E> parent;
        if (t == null) {
            this.root = new Node<>(e);
        } else {
            do {
                parent = t;
                if (e.compareTo(t.value) <= 0) {
                    t = t.left;
                } else if (e.compareTo(t.value) > 0) {
                    t = t.right;
                }
            } while (t != null);
            if (e.compareTo(parent.value) <= 0) {
                parent.left = new Node<>(e);
            } else {
                parent.right = new Node<>(e);
            }
        }
    }


    /**
    * Inner class for save element.
    * @param <E> generic.
    */
    private class Node<E> {
        /**
         * list with child element.
         */
        private List<Node<E>> children;

        /**
        * value element.
        */
        private E value;

        /**
         * right element.
         */
        private Node<E> right;

        /**
         * left element.
         */
        private Node<E> left;


        /**
         * index of element.
         */
        private int elementIndex = 0;

         /**
         * Constructor with parameters.
         * @param value for add.
         */
        Node(E value) {
            this.value = value;
            this.children = new LinkedList<>();
            this.elementIndex = indexLastElement++;
            this.right = null;
            this.left = null;
            size++;

        }

        /**
         * Method return list of children.
         * @return list.
         */
        public List<Node<E>> getChildren() {
            return children;
        }

     }

    /**
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int currentIndex = 0;

            private Queue<Node<E>> queue = filAllElementByTree(new LinkedList<Node<E>>(), root);

            /**
             *
             * @return
             */
            @Override
            public boolean hasNext() {
                return (!this.queue.isEmpty());
            }

            /**
             *
             * @return
             */
            @Override
            public E next() {
                E result = null;
                if (hasNext()) {
                    this.currentIndex++;
                    result = this.queue.poll().value;
                    return result;
                }
                throw new NoSuchElementException();
            }
        };
    }
}