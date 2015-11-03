package interfaces;

public interface ICollection<AnyType> {

	public String getType();

	public boolean myInsert(AnyType x);

	public boolean myContains(AnyType x);

	public boolean myRemove(AnyType x);
}
