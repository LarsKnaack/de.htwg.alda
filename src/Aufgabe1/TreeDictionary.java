package Aufgabe1;
 
 
public class TreeDictionary<K extends Comparable<? super K>,V> implements Dictionary<K, V> {
 
    private static class Node<K,V> {
        int height;
        K key;
        V value;
        Node<K,V> left;
        Node<K,V> right;
        private Node(K k,V v) {
            height = 0;
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }
 
    private Node<K,V> root;
    private V old;
 
    public Node<K, V> getSet() {
        return root;
    }
 
    private int getHeight(Node<K,V> p) {
        if (p == null) {
            return -1;
        }
        return p.height;
    }
 
    private int getBalance(Node<K,V> p) {
        if (p == null) {
            return 0;
        }
        return getHeight(p.right) - getHeight(p.left);
    }
 
    @Override
    public V insert(K key, V value) {
        root = insertR(key, value, root);
        return old;
    }
 
    private Node<K, V> insertR(K key, V value, Node<K,V> p) {
        if (p == null) {
            old = null;
            p = new Node<K,V>(key, value);
        } else if (key.compareTo(p.key) < 0) {
            p.left = insertR(key, value, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = insertR(key, value, p.right);
        } else {
            old = p.value;
            p.value = value;
        }
        return balance(p);
    }
 
    private Node<K, V> balance(Node<K, V> p) {
        if (p == null) {
            return null;
        }
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        if (getBalance(p) == -2) {
            if (getBalance(p) <= 0) {
                p = rotateRight(p);
            } else {
                p = rotateLeftRight(p);
            }
        } else if (getBalance(p) == +2) {
            if(getBalance(p) >= 0) {
                p = rotateLeft(p);
            } else {
                p = rotateRightLeft(p);
            }
        }
        return p;
    }
 
    private Node<K, V> rotateRightLeft(Node<K, V> p) {
        p.right = rotateRight(p.right);
        return rotateLeft(p);
    }
 
    private Node<K, V> rotateLeft(Node<K, V> p) {
        Node<K, V> q = p.right;
        p.right = q.left;
        q.left = p;
        p.height = Math.max(getHeight(p.right), getHeight(p.left)) + 1;
        q.height = Math.max(getHeight(q.right), getHeight(q.left)) + 1;
        return q;
    }
 
    private Node<K, V> rotateLeftRight(Node<K, V> p) {
        p.left = rotateLeft(p.left);
        return rotateRight(p);
    }
 
    private Node<K, V> rotateRight(Node<K, V> p) {
        Node<K, V> q = p.left;
        p.left = q.right;
        q.right = p;
        p.height = Math.max(getHeight(p.left), getHeight(p.right)) + 1;
        q.height = Math.max(getHeight(q.left), getHeight(q.right)) + 1;
        return q;
    }
 
    @Override
    public V search(K key) {
        Node<K, V> p = searchR(key, root);
        if (p == null) {
            return null;
        }
        return p.value;
    }
 
    private Node<K, V> searchR(K key, Node<K, V> p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) < 0) {
            return searchR(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            return searchR(key, p.right);
        }
        return p;
    }
 
    @Override
    public V remove(K key) {
        root = removeR(key, root);
        if (root == null) {
            return null;
        }
        return old;
    }
 
    private Node<K, V> removeR(K key, Node<K, V> p) {
        if (p == null) {
            old = null;
            return null;
        } else if (key.compareTo(p.key) < 0) {
            p.left = removeR(key, p.left);
        } else if (key.compareTo(p.key) > 0) {
            p.right = removeR(key, p.right);
        } else {
            old = p.value;
            if (p.left == null || p.right == null) {
                p = (p.left != null) ? p.left : p.right;
            }
            else {
                Node<K, V> q = getMin(p.right);
                p.key = q.key;
                p.value = q.value;
                p.right = removeR(p.key,p.right);
            }
        }
        return balance(p);
    }
 
    private Node<K, V> getMin(Node<K, V> p) {
        while(p.left != null) {
            p = p.left;
        }
        return p;
    }
 
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("TreeDictionary:\n");
        appendR(s, root);
        return s.toString();
    }
 
    private void appendR(StringBuilder s, Node<K, V> p) {
        if (p != null) {
            appendR(s,p.left);
            s.append(p.key).append(" ").append(p.value).append("\n");
            appendR(s,p.right);
        }
    }
}