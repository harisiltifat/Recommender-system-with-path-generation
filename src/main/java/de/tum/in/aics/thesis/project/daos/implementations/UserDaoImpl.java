package de.tum.in.aics.thesis.project.daos.implementations;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.tum.in.aics.thesis.project.daos.interfaces.UserDao;
import de.tum.in.aics.thesis.project.models.User;
@SuppressWarnings("unchecked")
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Transactional(readOnly = false)
	public void saveUser(User user) {
		//hibernateTemplate.saveOrUpdate(user);
		hibernateTemplate.save(user);
	}

	@Transactional(readOnly = false)
	public void deleteUser(User user) {
		hibernateTemplate.delete(user);

	}

	public List<User> getAllUser(User user) {
		return (List<User>) hibernateTemplate.find("from "
				+ User.class.getName());
	}

	public User getUserById(Integer userId) {
		return hibernateTemplate.get(User.class, userId);
	}

	@Override
	public List<User> findUser(long userId) {
		/*Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.eq("validatedId", socialId));
	    criteria.setProjection(Projections.rowCount());
	    int count = (Integer) criteria.uniqueResult();
	    session.getTransaction().commit();
	    return count;*/
		String queryString = "FROM User u WHERE u.userId = ?";
		List<User> users =  (List<User>) hibernateTemplate.find(queryString, userId);
		return users;
	}
}