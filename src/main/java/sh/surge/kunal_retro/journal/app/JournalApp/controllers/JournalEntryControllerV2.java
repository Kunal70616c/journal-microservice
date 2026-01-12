package sh.surge.kunal_retro.journal.app.JournalApp.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.surge.kunal_retro.journal.app.JournalApp.entity.JournalEntry;
import sh.surge.kunal_retro.journal.app.JournalApp.service.JournalEntryService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    // Creating instance by spring
    @Autowired
    JournalEntryService journalEntryService;

    //List of Get API

    // Get All Entries
    @GetMapping("/view")
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get Specific Entries
    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
        Optional<JournalEntry> journalEntry= journalEntryService.getEntryById(myId); // Optional
        if(journalEntry.isPresent()){
            return new ResponseEntity<JournalEntry>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
    }

    //List of Post API
    //Create an Entry
    @PostMapping("/add-entry")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry newEntry) {
        try {
            newEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(newEntry);
            return new ResponseEntity<JournalEntry>(newEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
        }
    }

    // List Of PUT API
    // Update An Entry
    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateJournalById(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
        JournalEntry oldEntry = journalEntryService.getEntryById(myId).orElse(null);
        if(oldEntry !=null){
            oldEntry.setName(myEntry.getName() !=null && !myEntry.getName().equals("")? myEntry.getName() : oldEntry.getName());
            oldEntry.setContent(myEntry.getContent()!= null && !myEntry.getContent().equals("") ? myEntry.getContent(): oldEntry.getContent());
            journalEntryService.saveEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);

    }

    //List Of Delete API
    // Delete By Id
    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalById(@PathVariable ObjectId myId){
        journalEntryService.deleteById(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
