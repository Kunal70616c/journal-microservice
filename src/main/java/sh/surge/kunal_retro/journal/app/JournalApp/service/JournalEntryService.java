package sh.surge.kunal_retro.journal.app.JournalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sh.surge.kunal_retro.journal.app.JournalApp.entity.JournalEntry;
import sh.surge.kunal_retro.journal.app.JournalApp.repository.JournalEntryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    //Injecting Dependency
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    //Create
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }
    //Find all
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    // Find Specific
    public Optional<JournalEntry> getEntryById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }
    // Delete
    public void deleteById(ObjectId myId){
        journalEntryRepository.deleteById(myId);
    }
    //Update

}
