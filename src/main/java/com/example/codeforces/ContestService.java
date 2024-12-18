package com.example.codeforces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContestService {

    private final ContestRepository contestRepository;

    @Autowired
    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    // Get all contests
    public List<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    // Get contest by ID
    public Optional<Contest> getContestById(Long id) {
        return contestRepository.findById(id);
    }

    // Create a new contest
    public Contest createContest(Contest contest) {
        return contestRepository.save(contest);
    }

    // Update an existing contest
    public Optional<Contest> updateContest(Long id, Contest updatedContest) {
        Optional<Contest> existingContest = contestRepository.findById(id);
        if (existingContest.isPresent()) {
            Contest contest = existingContest.get();
            contest.setName(updatedContest.getName());
            contest.setStartTime(updatedContest.getStartTime());
            contest.setDuration(updatedContest.getDuration());
            contest.setDifficulty(updatedContest.getDifficulty());
            contestRepository.save(contest);
            return Optional.of(contest);
        }
        return Optional.empty();
    }

    // Delete a contest by ID
    public boolean deleteContest(Long id) {
        if (contestRepository.existsById(id)) {
            contestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
