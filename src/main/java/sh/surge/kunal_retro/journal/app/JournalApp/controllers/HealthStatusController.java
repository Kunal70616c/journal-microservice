package sh.surge.kunal_retro.journal.app.JournalApp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthStatusController {
    //General Health Status API
    @GetMapping("/health")
    public String healthStatus(){
        return "Alive";
    }
}
