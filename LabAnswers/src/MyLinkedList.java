import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{

	private Node<T> head;
	private int size = 0;

	public int getSize() {
		return size;
	}

	public boolean remove(int index) {
		if (index > size || index < 0) {
			return false;
		}
		Node<T> nodeBefore = head;
		Node<T> nodeAfter;
		for (int i = 0; i < index - 1; i++) {
			nodeBefore = nodeBefore.getNext();
		}
		nodeAfter = nodeBefore.getNext().getNext();
		nodeBefore.setNext(nodeAfter);
		size--;
		return true;

	}

	public T get(int index) {
		if (index>size || index<0){return null;}
		Node<T> node = head;
		for (int i=0; i<index; i++){
			node = node.getNext();
		}
		return node.getData();
	}

	public void add(T obj) {
		if(size == 0){
			head = new Node<>(obj);
			size++;
			return;
		}
		Node<T> last = head;
		Node<T> afterLast = last.getNext();
		while (afterLast!=null){
			last = last.getNext();
			afterLast = last.getNext();
		}
		last.setNext(new Node<>(obj));
		size++;
	}

	//The methods below are bonus

	public void add(int index, T obj){
		if (index<=0){
			//If given an index of zero or less than zero, add the new node to the beginning of the list
			Node<T> newHead = new Node<>(obj);
			newHead.setNext(head);
			head = newHead;
			size ++;
		} else if (index>=size){
			//If given an index of the list's size or greater, add the new node to the end of the list.
			add(obj);
		} else {
			//If inserting somewhere in the middle
			Node<T> nodeBefore = head;
			Node<T> nodeAfter;
			for (int i=0; i<index-1; i++){
				nodeBefore = nodeBefore.getNext();
			}
			nodeAfter = nodeBefore.getNext().getNext();
			Node<T> newNode = new Node<>(obj);
			nodeBefore.setNext(newNode);
			newNode.setNext(nodeAfter);
			size++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new MyIterator<>(head);
	}

	private class MyIterator<T> implements Iterator<T> {

		private Node<T> node;

		public MyIterator(Node<T> node) {
			MyIterator.this.node = node;
		}
		@Override
		public boolean hasNext() {
			if (node.getNext()==null){
				return false;
			}
			return true;
		}

		@Override
		public T next() {
			node = node.getNext();
			return node.getData();
		}

		@Override
		public void remove() {
			Node <T> nodeFinder = (Node<T>) head;
			for (int i=0; i<size; i++) {
				if(nodeFinder == node){
					MyLinkedList.this.remove(i);
				}
				nodeFinder = nodeFinder.getNext();
			}
		}
	}
}
