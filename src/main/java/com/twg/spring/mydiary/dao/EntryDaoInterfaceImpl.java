package com.twg.spring.mydiary.dao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.twg.spring.mydiary.entities.Entry;
@Component

public class EntryDaoInterfaceImpl implements EntryDaoInterface{

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(Entry entry) {
		hibernateTemplate.save(entry);
		
	}

	@Override
	public void update(Entry entry) {
		hibernateTemplate.update(entry);
		
	}

	@Override
	public void delete(Entry entry) {
		hibernateTemplate.delete(entry);
		
	}

	@Override
	public Entry findById(int id) {
				return hibernateTemplate.get(Entry.class, id);
	}

	@Override
	public List<Entry> findAll() {
		// TODO Auto-generated method stub
		return hibernateTemplate.loadAll(Entry.class);
	}

	@SuppressWarnings("unchecked")
	public List<Entry> findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return (List<Entry>) hibernateTemplate.findByNamedParam("from Entry where userid = :uid", "uid", userId);
	}

}
