package linkedLists;

import java.util.NoSuchElementException;

public class DLDHDTList<E> extends AbstractDLList<E> {
	private DNode<E> header, trailer; 
	private int length; 
	
	public DLDHDTList() { 
		// ADD CODE HERE to generate empty linked list of this type 
	}
	
	public void addFirstNode(Node<E> nuevo) {
		addNodeAfter(header, nuevo); 
	}
	
	public void addLastNode(Node<E> nuevo) { 
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = trailer.getPrev();  
		nBefore.setNext(dnuevo); 
		trailer.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(trailer); 
		length++; 
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		DNode<E> dnuevo = (DNode<E>) nuevo; 
		DNode<E> nBefore = (DNode<E>) target; 
		DNode<E> nAfter = nBefore.getNext(); 
		nBefore.setNext(dnuevo); 
		nAfter.setPrev(dnuevo); 
		dnuevo.setPrev(nBefore); 
		dnuevo.setNext(nAfter); 
		length++; 
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// ADD CODE HERE
		DNode<E> dnuevo = (DNode<E>) nuevo;
		DNode<E> nAfter = (DNode<E>) target;
		DNode<E> nBefore = nAfter.getPrev();
		nBefore.setNext(dnuevo);
		nAfter.setPrev(dnuevo);
		dnuevo.setNext(nAfter);
		dnuevo.setPrev(nBefore);
		length++;
	}

	public Node<E> createNewNode() {
		return new DNode<E>();
	}

	public Node<E> getFirstNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return header.getNext();
	}

	public Node<E> getLastNode() throws NoSuchElementException {
		if (length == 0) 
			throw new NoSuchElementException("List is empty."); 
		return trailer.getPrev();
	}

	public Node<E> getNodeAfter(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if(target == this.getLastNode())
			throw new NoSuchElementException("getNodeAfter(): the target node is the last one");
		
		DNode<E> nNext = ((DNode<E>) target).getNext();
		return nNext; 
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NoSuchElementException {
		// ADD CODE HERE AND MODIFY RETURN ACCORDINGLY
		if(target == this.getFirstNode())
			throw new NoSuchElementException("getNodeBefore(): the target node is the first one");
		
		DNode<E> nPrev = ((DNode<E>) target).getPrev();
		return nPrev; 
	}

	public int length() {
		return length;
	}

	public void removeNode(Node<E> target) {
	
		// ADD CODE HERE to disconnect target from the linked list, reduce lent, clean target...
		if(target == this.getFirstNode()) {
			header.setNext(((DNode<E>) target).getNext());
		}
		else {
			DNode<E> nPrev = ((DNode<E>)getNodeBefore(target));
			nPrev.setNext(((DNode<E>) target).getNext());
		}
		((DNode<E>) target).clean();
		length--;
	}
	
	/**
	 * Prepares every node so that the garbage collector can free 
	 * its memory space, at least from the point of view of the
	 * list. This method is supposed to be used whenever the 
	 * list object is not going to be used anymore. Removes all
	 * physical nodes (data nodes and control nodes, if any)
	 * from the linked list
	 */
	private void destroy() {
		while (header != null) { 
			DNode<E> nnode = header.getNext(); 
			header.clean(); 
			header = nnode; 
		}
	}
	
	/**
	 * The execution of this method removes all the data nodes from
	 * the current instance of the list, leaving it as a valid empty
	 * doubly linked list with dummy header and dummy trailer nodes. 
	 */
	public void makeEmpty() { 
		// TODO
		destroy();
		header = null;
		trailer = null;
		length = 0;
	}
		
	protected void finalize() throws Throwable {
	    try {
			this.destroy(); 
	    } finally {
	        super.finalize();
	    }
	}
public <T> T[] toArray(T[] array) {
		
		return array;
		}
}
