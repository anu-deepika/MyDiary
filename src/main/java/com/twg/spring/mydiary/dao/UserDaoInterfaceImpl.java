package com.twg.spring.mydiary.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.sun.xml.fastinfoset.util.DuplicateAttributeVerifier.Entry;
import com.twg.spring.mydiary.entities.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import java.util.List;
@Repository
public class UserDaoInterfaceImpl implements UserDaoInterface {
    
    @Autowired
    private HibernateTemplate hibernateTemplate;
    
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	public void save(User user) {
	  hibernateTemplate.save(user);

	}

	
	public void update(User user) {
	hibernateTemplate.update(user);

	}

	
	public void delete(User user) {
		hibernateTemplate.delete(user);

	}

	
	public User findById(int id) {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(User.class,id);
	}

	
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(User.class);
	}
	@Override
    public User findByUsername(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));
        
        // hibernateTemplate is already used in your save() method, so use it here
        List<User> users = (List<User>) hibernateTemplate.findByCriteria(criteria);
        
        if (users != null && !users.isEmpty()) {
            return users.get(0); // Return the matched user
        }
        
        return null; // Return null if user does not exist
    }
}
