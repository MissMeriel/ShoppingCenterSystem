package master;

/**
 * This class represents a Keyed Item.
 * Keyed items are comparable by their key which is set upon constructing.
 * @author Jon
 *
 * @param <KT>
 */
public abstract class KeyedItem<KT extends Comparable<? super KT>> {
	/**
	 * The key for a keyed item
	 */
	private KT key;

	/**
	 * Constructor for a keyed item
	 * @param key the key to set for this item
	 */
	public KeyedItem(KT key) {
		this.key = key;
	} // end constructor

	/**
	 * Standard Accessor - for an item's key
	 * @return the key for an item
	 */
	public KT getKey() {
		return key;
	} // end getKey
} // end KeyedItem
