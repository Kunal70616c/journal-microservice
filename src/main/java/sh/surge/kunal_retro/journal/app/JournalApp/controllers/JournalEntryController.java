package sh.surge.kunal_retro.journal.app.JournalApp.controllers;

import org.springframework.web.bind.annotation.*;
import sh.surge.kunal_retro.journal.app.JournalApp.entity.JournalEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;

@RestController
public class JournalEntryController {

    // Storing data in MAP as DB
    // Not Persistent
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    //List of Get API

    // Get All Entries
    @GetMapping("/view-all")
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    // Get Specific Entries
    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId) {
        return journalEntries.get(myId);
    }

    //List of Post API
    //Create an Entry
    @PostMapping("/add-entry")
    public boolean createEntry(@RequestBody JournalEntry newEntry) {
        journalEntries.put(newEntry.getId(), newEntry);
        return true;
    }

    // List Of PUT API
    // Update An Entry
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalById(@PathVariable long myId, @RequestBody JournalEntry myEntry) {
        return journalEntries.put(myId, myEntry);
    }
    //List Of Delete API
    // Delete By Id
    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalById(@PathVariable long myId){
        return journalEntries.remove(myId);
    }

}
