package ru.gb.myspringdemo.repository;

import org.springframework.stereotype.Repository;
import ru.gb.myspringdemo.model.Issue;
import ru.gb.myspringdemo.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class IssueRepository {

    private final List<Issue> issues;

    public IssueRepository() {
        this.issues = new ArrayList<>();
    }

    public void save(Issue issue) {
        issues.add(issue);
    }

    public Issue getIssueById(long id) {
        return issues.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }
}
