package sh.surge.kunal_retro.journal.app.JournalApp.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sh.surge.kunal_retro.journal.app.JournalApp.entity.JournalEntry;
import sh.surge.kunal_retro.journal.app.JournalApp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    // Creating instance by spring
    @Autowired
    JournalEntryService journalEntryService;

    //List of Get API

    // Get All Entries
    @GetMapping("/view")
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    // Get Specific Entries
    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myId) {
        return journalEntryService.getEntryById(myId).orElse(null);
    }

    //List of Post API
    //Create an Entry
    @PostMapping("/add-entry")
    public JournalEntry createEntry(@RequestBody JournalEntry newEntry) {
        newEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(newEntry);
        return newEntry;
    }

    // List Of PUT API
    // Update An Entry
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
        JournalEntry oldEntry = journalEntryService.getEntryById(myId).orElse(null);
        if(oldEntry !=null){
            oldEntry.setName(myEntry.getName() !=null && !myEntry.getName().equals("")? myEntry.getName() : oldEntry.getName());
            oldEntry.setContent(myEntry.getContent()!= null && !myEntry.getContent().equals("") ? myEntry.getContent(): oldEntry.getContent());
        }
        journalEntryService.saveEntry(oldEntry);
        return oldEntry;
    }

    //List Of Delete API
    // Delete By Id
    @DeleteMapping("/id/{myId}")
    public boolean deleteJournalById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return true;
    }

}
