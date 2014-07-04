package de.tum.in.aics.thesis.project.daos.implementations;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.tum.in.aics.thesis.project.daos.interfaces.LocationsDao;
import de.tum.in.aics.thesis.project.models.UsersLocation;

@SuppressWarnings("unchecked")
@Repository("locationDao")
@Transactional
public class LocationsDaoImpl implements LocationsDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	private HibernateTemplate hibernateTemplate;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}

	@Override
	public int saveLocation(UsersLocation location) {
		hibernateTemplate.save(location);
		return location.getLocationId();
	}

	@Override
	public List<UsersLocation> findCurrentLocation(int locationId) {
		String queryString = "FROM UsersLocation ul WHERE ul.locationId = ?";
		List<UsersLocation> currentLocation = (List<UsersLocation>) hibernateTemplate.find(queryString, locationId);
		return currentLocation;
	}
	
}