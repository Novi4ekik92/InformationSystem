package org.obeonetwork.sample.badpractives;

// Start of user code for import 
import java.util.Collection;

import org.obeonetwork.fwk.dao.exception.DaoException;
import org.obeonetwork.sample.badpractives.Public_;

// End of user code

/**
 * This class provides the data access layer to the public entity class.<br/>
 * This is the interface which represent the contrat of the DAO access.
 */
public interface IPublic_Dao {

	/**
	 * Create a new element.
	 * @param public_ Element to create.
	 	 * @throws DaoException If a Dao problem occurs.
	 */
	public void createpublic_(Public_ public_) throws DaoException;

	/**
	 * Update an existing element.
	 * @param public_ Element to update. 
     *            If the element has an id, it may use it.
     	 * @throws DaoException If a Dao problem occurs.
	 */
	public void updatepublic_(Public_ public_) throws DaoException;

	/**
	 * Delete an element.
     * Only id can be used to find which element must be deleted.
	 * @param public_ Element which will be delete. 
	 	 * @throws DaoException If a Dao problem occurs.
	 */
	public void deletepublic_(Public_ public_) throws DaoException;

	/**
	 * Find all elements.
	 * @return A list with all elements, without any filter.
	 	 * @throws DaoException If a Dao problem occurs.
	 */
	public Collection<Public_> findAllpublic_s() throws DaoException;

	/**
	 * Find one entity by its primary key.
     * @param id The PK of the entity.
	 * @return The entity found.
	 	 * @throws DaoException If a Dao problem occurs.
	 */
	public Public_ findpublic_ById(String id) throws DaoException;

	//Start of user code for technicals dao access api
	//End of user code
}
