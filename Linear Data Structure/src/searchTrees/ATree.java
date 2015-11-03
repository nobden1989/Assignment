package searchTrees;

import interfaces.ICollection;

public abstract class ATree<AnyType extends Comparable<? super AnyType>>
		implements ICollection<AnyType> {

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

	public abstract void printTree();

}
