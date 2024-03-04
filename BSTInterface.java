import java.util.Iterator;

public interface BSTInterface<T> extends Iterable<T> {
	public enum Traversal {Inorder, Preorder, Postorder};
	T min();
	T max();
	public boolean isEmpty();
	public boolean isFull();
	public int size();
	public boolean contains(T target);
	public T get(T item);
	public boolean add(T item);
	public boolean remove(T item);
	public Iterator<T> getIterator(Traversal orderType);
}
