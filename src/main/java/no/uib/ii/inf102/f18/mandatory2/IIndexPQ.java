package no.uib.ii.inf102.f18.mandatory2;

/**
 * An indexed priority queue is one where the elements
 * are non-negative integers sorted by a key. Each element
 * can only be associated to one key, and a good practice
 * is to make the key immutable.
 * 
 * @author Torstein Strømme
 */
public interface IIndexPQ<Key extends Comparable<Key>> {

    /**
     * Insert an index into the priority queue, sort it by the key
     *
     * @param index index with which to associate the key
     * @param key key to be inserted
     */
    void add(int index, Key key);

    /**
     * Update the key associated with the given index.
     *
     * @param index index to be updated (must exist)
     * @param key new key to associate with index
     */
    void changeKey(int index, Key key);

    /**
     * Check whether the given index exists in the
     * priority queue.
     *
     * @param index index to be checked
     * @return true if a key is associated with the index, false otherwise
     */
    boolean contains(int index);

    /**
     * Delete the index and associated key from the priority queue
     *
     * @param index index to be deleted
     */
    void delete(int index);

    /**
     * Return the key associated with the index
     *
     * @param index index to look up
     * @return element at index
     */
    Key getKey(int index);

    /**
     * Return the largest key in the priority queue.
     * Returns null if none exists. If more than one
     * key has the maximum value, no guarantee is
     * given as to which of them is returned
     *
     * @return the index associated to a largest key
     */
    Key peekKey();

    /**
     * Return the index associated with the largest key
     * in the priority queue.
     * Returns -1 if none exists. If more than one
     * key has the maximum value, no guarantee is
     * given as to which of those indices is returned
     *
     * @return the index associated to a largest key
     */
    int peek();

    /**
     * Return the index associated with the largest key
     * in the priority queue, and remove from priority queue.
     * Returns -1 if none exists. If more than one
     * key has the maximum value, no guarantee is
     * given as to which of those indices is returned
     *
     * @return the index associated to a largest key
     */
    int poll();

    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the size
     */
    int size();
    
    /**
     * Checks whether the priority queue is empty
     * 
     * @return true if the priority queue is empty, false otherwise
     */
    boolean isEmpty();

}
