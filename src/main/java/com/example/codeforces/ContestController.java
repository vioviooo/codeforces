//package com.example.codeforces;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/contests")
//public class ContestController {
//
//    @Autowired
//    private ContestRepository contestRepository;
//
//    // Get all contests
//    @GetMapping
//    public ResponseEntity<List<Contest>> getAllContests() {
//        List<Contest> contests = contestRepository.findAll();
//        return new ResponseEntity<>(contests, HttpStatus.OK);
//    }
//
//    // Get a contest by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Contest> getContestById(@PathVariable Long id) {
//        Optional<Contest> contest = contestRepository.findById(id);
//        return contest.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    // Create a new contest
//    @PostMapping
//    public ResponseEntity<Contest> createContest(@RequestBody Contest contest) {
//        Contest savedContest = contestRepository.save(contest);
//        return new ResponseEntity<>(savedContest, HttpStatus.CREATED);
//    }
//
//    // Update an existing contest
//    @PutMapping("/{id}")
//    public ResponseEntity<Contest> updateContest(@PathVariable Long id, @RequestBody Contest updatedContest) {
//        Optional<Contest> existingContest = contestRepository.findById(id);
//        if (existingContest.isPresent()) {
//            Contest contest = existingContest.get();
//            contest.setName(updatedContest.getName());
//            contest.setStartTime(updatedContest.getStartTime());
//            contest.setDuration(updatedContest.getDuration());
//            contest.setDifficulty(updatedContest.getDifficulty());
//            Contest savedContest = contestRepository.save(contest);
//            return new ResponseEntity<>(savedContest, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    // Delete a contest by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteContest(@PathVariable Long id) {
//        if (contestRepository.existsById(id)) {
//            contestRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//}

package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;

    @Autowired
    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    // Get all contests
    @GetMapping
    public ResponseEntity<List<Contest>> getAllContests() {
        List<Contest> contests = contestService.getAllContests();
        return new ResponseEntity<>(contests, HttpStatus.OK);
    }

    // Get contest by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contest> getContestById(@PathVariable Long id) {
        Optional<Contest> contest = contestService.getContestById(id);
        return contest.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new contest
    @PostMapping
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest) {
        Contest savedContest = contestService.createContest(contest);
        return new ResponseEntity<>(savedContest, HttpStatus.CREATED);
    }

    // Update an existing contest
    @PutMapping("/{id}")
    public ResponseEntity<Contest> updateContest(@PathVariable Long id, @RequestBody Contest updatedContest) {
        Optional<Contest> updated = contestService.updateContest(id, updatedContest);
        return updated.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete a contest by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContest(@PathVariable Long id) {
        boolean deleted = contestService.deleteContest(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
