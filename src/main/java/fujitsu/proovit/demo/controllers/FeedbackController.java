package fujitsu.proovit.demo.controllers;

import fujitsu.proovit.demo.entities.Feedback;
import fujitsu.proovit.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Method to get all feedbacks from repository.
    @GetMapping(value = "/feedbacks")
    public List<Feedback> getFeedbacks(){
        return feedbackService.getAllFeedbacks();
    }

    // Method to post new feedbacks to repository.
    @PostMapping(value = "/feedbacks")
    public void createFeedback(@RequestBody Feedback feedback){
        feedbackService.insert(feedback);
    }
}