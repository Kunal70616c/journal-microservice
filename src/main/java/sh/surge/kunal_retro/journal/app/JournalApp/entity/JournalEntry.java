package sh.surge.kunal_retro.journal.app.JournalApp.entity;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Getter
@Setter
public class JournalEntry {

    @Id
    private ObjectId id;
    private String name;
    private String content;
    private LocalDateTime date;


}
