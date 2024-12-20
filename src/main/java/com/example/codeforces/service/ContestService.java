package com.example.codeforces.service;

import com.example.codeforces.db.Contest;
import com.example.codeforces.repository.ContestRepository;
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

    public List<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    public Optional<Contest> getContestById(Long id) {
        return contestRepository.findById(id);
    }

    public Contest createContest(Contest contest) {
        return contestRepository.save(contest);
    }

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

    public boolean deleteContest(Long id) {
        if (contestRepository.existsById(id)) {
            contestRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
