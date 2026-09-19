package com.twg.spring.mydiary.business;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; // Added this import

import com.twg.spring.mydiary.dao.EntryDaoInterface;
import com.twg.spring.mydiary.entities.Entry;

@Service
public class EntryBusinessInterfaceImpl implements EntryBusinessInterface {

    // Injects the DAO layer to handle database operations
    @Autowired
    private EntryDaoInterface entryDaoInterface;

   
    @Transactional // Allows saving to the database
    public void save(Entry entry) {
        entryDaoInterface.save(entry);
    }

   
    @Transactional // Allows updating the database
    public void update(Entry entry) {
        entryDaoInterface.update(entry);
    }

    
    @Transactional // Allows deleting from the database
    public void delete(Entry entry) {
        entryDaoInterface.delete(entry);
    }
  
    
    @Transactional(readOnly = true)
    public Entry findById(int id) {
        return entryDaoInterface.findById(id);
    }

   
    @Transactional(readOnly = true)
    public List<Entry> findAll() {
        return entryDaoInterface.findAll();
    }

   
    @Transactional(readOnly = true)
    // FIXED: Changed Integer to int to match your interface perfectly
    public List<Entry> findByUserId(int userId) {
        return entryDaoInterface.findByUserId(userId);
    }
}
