package de.tum.in.aics.thesis.project.daos.implementations;


import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.tum.in.aics.thesis.project.daos.interfaces.PreferencesDao;
import de.tum.in.aics.thesis.project.models.UsersLocation;
import de.tum.in.aics.thesis.project.models.UsersPreferences;
@SuppressWarnings("unchecked")
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

	@Override
	public List<UsersPreferences> getCurrentPrefernces(UsersLocation location) {
		String queryString = "FROM UsersPreferences up WHERE up.location = ?";
		List<UsersPreferences> currentPreferences = (List<UsersPreferences>) hibernateTemplate.find(queryString, location);
		return currentPreferences;
	}
	
}