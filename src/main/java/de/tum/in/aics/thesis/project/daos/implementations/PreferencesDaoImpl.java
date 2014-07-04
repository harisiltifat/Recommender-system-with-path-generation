package de.tum.in.aics.thesis.project.daos.implementations;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import de.tum.in.aics.thesis.project.daos.interfaces.PreferencesDao;
import de.tum.in.aics.thesis.project.models.UsersPreferences;

@Repository("preferencesDao")
@Transactional
public class PreferencesDaoImpl implements PreferencesDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public void savePreferences(UsersPreferences preferences) {
		hibernateTemplate.save(preferences);
	}
	
}