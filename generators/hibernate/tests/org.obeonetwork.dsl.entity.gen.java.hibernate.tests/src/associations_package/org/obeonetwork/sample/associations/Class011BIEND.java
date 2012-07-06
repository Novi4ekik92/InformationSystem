package org.obeonetwork.sample.associations;

// Start of user code for imports
import java.io.Serializable;


// End of user code for imports

/**
 * 
 */
public class Class011BIEND implements Serializable {

	/**
     * serialVersionUID is used for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Constant representing the name of the automatic primary key field.
     */
	public static final String PROP_ID = "id";
	
	/**
     * Constant representing the name of the field fakeAttr.
     */
	public static final String PROP_FAKEATTR = "fakeAttr";
	
	/**
     * Constant representing the name of the field source.
     */
	public static final String PROP_SOURCE = "source";
	
    /**
     * Automatic primary key.
     */
    private String id;
    
    /**
     * Field fakeAttr.
     */
	protected Float fakeAttr;

    /**
     * Field source.
     */
	protected Class011BI source;

	/**
	 * Default constructor.
	 */
	public Class011BIEND() {
		super();
	}
	
	/**
	 * Return the identifier.
	 * @return id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Set a value to parameter id.
	 * @param id Value of the identifier.
	 */
	public void setId(final String id) {
		this.id = id;
	}	

	/**
	 * Constructor with all parameters initialized.
	 * @param fakeAttr. 
	 * @param source. 
	 */
	public Class011BIEND(Float fakeAttr, Class011BI source) {
		this.fakeAttr = fakeAttr;
		this.source = source;
	}

	/**
	 * Return fakeAttr.
	 * @return fakeAttr
	 */
	public Float getFakeAttr() {
		return fakeAttr;
	}

	/**
	 * Set a value to parameter fakeAttr.
	 * @param fakeAttr
	 */
	public void setFakeAttr(final Float fakeAttr) {
		this.fakeAttr = fakeAttr;
	}

	/**
	 * Return source.
	 * @return source
	 */
	public Class011BI getSource() {
		return source;
	}

	/**
	 * Set a value to parameter source.
	 * @param source
	 */
	public void setSource(final Class011BI source) {
		this.source = source;
	}

	/** 
	 * Equality test based on identifiers.
	 * @param value Value to compare.
	 * @return Returns true if and only if given object is an instance of
     * Class011BIEND and the given object has the same PK as this
     * if the PK is not null or their ids are equal.
	 */
	public boolean equals(final Object other) {
	 	// Start of user code for equals
		if (this == other) {
			return true;
		}
		if (id==null) {
			return false;
		}
		if (!(other instanceof Class011BIEND)) {
			return false;
		}
		
		final Class011BIEND castedOther = (Class011BIEND) other;
		if (id != null && castedOther.getId() != null) {
			return id.equals(castedOther.getId());
		}
		return true;
		
		 // End of user code for equals
	}

	/** 
	 * HashTable code based on identifier hash codes.
	 * @return hash value.
	 */
	public int hashCode() {
		return id==null ? System.identityHashCode(this) : id.hashCode();
	}
   
	// Start of user code for private methods
	// TODO Remove this line and add your private methods here
	// End of user code
   
}