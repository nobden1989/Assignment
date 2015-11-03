package hashTable;

import interfaces.ICollection;

/**
 * Interface of HashTable
 * 
 * @author Xiaoliang Tang
 *
 * @param <AnyType>
 */
public abstract class AHashTable<AnyType> implements ICollection<AnyType> {

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}

}
