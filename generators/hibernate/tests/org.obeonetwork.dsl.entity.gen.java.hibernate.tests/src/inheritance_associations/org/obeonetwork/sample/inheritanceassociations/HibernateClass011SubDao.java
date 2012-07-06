package org.obeonetwork.sample.inheritanceassociations;

// Start of user code for import
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import org.obeonetwork.fwk.dao.exception.DaoException;
import org.obeonetwork.fwk.dao.hibernate.HibernateUtil;
import org.obeonetwork.sample.inheritanceassociations.Class011Sub;
import org.obeonetwork.sample.inheritanceassociations.IClass011SubDao;

// End of user code for import

/**
 * Implementation of DAO interface IClass011SubDao based on the hibernate
 * persistence framework.
 * @see org.obeonetwork.sample.inheritanceassociations.IClass011SubDao
 */
public class HibernateClass011SubDao implements IClass011SubDao {

	/**
     * The logger of this class.
     */
	private final static Log LOG = LogFactory.getLog(HibernateClass011SubDao.class);

	/**
	 * Create a new element
	 * @param class_01_1_Sub Element to create.
	 * @throws DaoException If an HibernateException occurs.
     * @see org.obeonetwork.sample.inheritanceassociations.IClass011SubDao.createClass_01_1_Sub
     */
	public void createClass011Sub(final Class011Sub class011Sub) throws DaoException {
		LOG.debug("Create a new Class011Sub entity");
		try {
			Session session = HibernateUtil.currentSession();
			session.save(class011Sub);
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Update an existing element
	 * @param class_01_1_Sub
     *            Element to update. If the element has an id,it may use it. 
	 * @throws DaoException If an HibernateException occurs.
     * @see org.obeonetwork.sample.inheritanceassociations.IClass011SubDao.updateClass_01_1_Sub
     */
	public void updateClass011Sub(Class011Sub class011Sub) throws DaoException {
		LOG.debug("Update the entity Class_01_1_Sub with id =" + class011Sub.getId());
		try {
			Session session = HibernateUtil.currentSession();
			session.update(class011Sub);
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Delete an element
	 * Only id can be used to find which element must be deleted.
	 * @param class_01_1_Sub Element which will be deleted. 
	 * @throws DaoException If an HibernateException occurs.
     * @see org.obeonetwork.sample.inheritanceassociations.IClass011SubDao.deleteClass_01_1_Sub
   	 */
	public void deleteClass011Sub(Class011Sub class011Sub) throws DaoException {
		LOG.debug("Delete the entity Class_01_1_Sub with id =" + class011Sub.getId());
		try {
			Session session = HibernateUtil.currentSession();
			session.delete(class011Sub);
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Find all elements.
	 * @return A list with all elements, without any filter. 
	 * @throws DaoException If an HibernateException occurs.
     * @see org.obeonetwork.sample.inheritanceassociations.IClass011SubDao.findAllClass_01_1_Subs
     */
	public Collection<Class011Sub> findAllClass011Subs() throws DaoException {
		LOG.debug("Find all instance of Class_01_1_Sub entity");
		try {
			Session session = HibernateUtil.currentSession();
			Criteria criteria = session.createCriteria(Class011Sub.class);
			Collection<Class011Sub> resultList = criteria.list();
			
			LOG.debug("Found " + resultList.size() + " instances of Class_01_1_Sub entity");
			return resultList;
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * Find one entity by its primary key.
     * @param id The PK of the entity
	 * @return The entity found. 
	 * @throws DaoException If an HibernateException occurs.
     * @see org.obeonetwork.sample.inheritanceassociations.IClass011SubDao.findClass_01_1_SubById
     */
	public Class011Sub findClass011SubById(String id) throws DaoException {
		LOG.debug("Find one instance of Class_01_1_Sub entity by id : " + id);
		try {
			Session session = HibernateUtil.currentSession();
			Criteria criteria = session.createCriteria(Class011Sub.class)
				.add(Restrictions.eq("id", id));
			Class011Sub result = (Class011Sub)criteria.uniqueResult();
			
			return result;
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}
	

	//Start of user code
	//End of user code
}

