package no.uib.ii.inf102.f18.mandatory2;

/**
 * @author Carl August Gjørsvik
 *
 * @param <Key>
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements IIndexPQ<Key> {

    private final int NMAX;
    private int size;
    
    //1-indexed binary heap of references to indices in the keys array, sorted by values in keys array
    private int[] mpq;
    
    /**
     * An array to keep track of the key indices' position in the mpq.
     * Such that for the key at position x in keys[], its position in the heap mpq[] is inv[x]
     * Value is -1 if there is no key at that index.
     */
    private int[] inv;
    
    private Key[] keys;
    
    /**
     * Indexed minimum-priority queue
     * 
     * @param maxSize the maximum number of elements this queue can hold
     */
    @SuppressWarnings("unchecked")
    public IndexMinPQ(int maxSize) {
        if (maxSize<0) throw new IllegalArgumentException("Size of priority queue cannot be less than zero.");
        
        NMAX = maxSize;
        keys = (Key[]) new Comparable[maxSize];
        mpq = new int[maxSize+1];
        inv = new int[maxSize+1];
        
        for (int i=0; i<=NMAX; i++) {
            inv[i] = -1;
        }
    }

    public void add(int index, Key key) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (contains(index)) throw new IllegalArgumentException("index is already set. Use changeKey() instead");
        
        mpq[++size] = index;
        inv[index] = size;
        keys[index] = key;
        swim(size);
        
    }

    /**
     * Swap value at pos k with its parent while parent is greater
     * 
     * @param k position of value to move
     */
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            swap(k, k/2);
            k = k/2;
        }
    }
    
    /**
     * Swap value at pos k with the lesser of its children while it has children of lesser value
     * 
     * @param k position of value to move
     */
    private void sink(int k) {
        while (2*k <= size) {
            int j = 2*k;
            if (j < size && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }
    
    /**
     * Check if if key at position i in heap is greater than key at position j in heap
     * 
     * @param i
     * @param j
     * @return true if key at position i in heap > key at position j in heap
     */
    private boolean greater(int i, int j) {
        return keys[mpq[i]].compareTo(keys[mpq[j]]) > 0;
    }
    
    private void swap(int i, int j) {
        int temp = mpq[i];
        mpq[i] = mpq[j];
        mpq[j] = temp;
        inv[mpq[i]] = i;
        inv[mpq[j]] = j;
    }

    public void changeKey(int index, Key key) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Cannot change an index which does not exist.");
        
        keys[index] = key;
        swim(inv[index]);
        sink(inv[index]);
    }

    public boolean contains(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        return inv[index] != -1;
    }

    
    public void delete(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Cannot delete an index which does not exist.");
        
        int i = inv[index];
        swap(i, size--);
        swim(i);
        sink(i);
        keys[index] = null;
        inv[index] = -1;
    }

    
    public Key getKey(int index) {
        if (index < 0 || index >= NMAX) throw new IndexOutOfBoundsException();
        if (!contains(index)) throw new IllegalArgumentException("Given index is not associated with any key.");
        
        return keys[index];
    }

    
    public Key peekKey() {
        if (size==0) return null;
        return keys[mpq[1]];
    }

    
    public int peek() {
        if (size==0) return -1;
        return mpq[1];
    }

    
    public int poll() {
        if (size==0) return -1;
        
        int i = mpq[1];
        delete(i);
        return i;
    }

    
    public int size() {
        return size;
    }

    
    public boolean isEmpty() {
        return size==0;
    }

}
