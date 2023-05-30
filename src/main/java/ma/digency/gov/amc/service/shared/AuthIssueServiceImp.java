package ma.digency.gov.amc.service.shared;

import lombok.RequiredArgsConstructor;
import ma.digency.gov.amc.repository.AuthIssueRepository;
import ma.digency.gov.amc.repository.entity.shared.AuthIssue;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthIssueServiceImp implements AuthIssueService {
    private final AuthIssueRepository authIssueRepository;

    @Override
    public void createIssue(AuthIssue issue) {
        authIssueRepository.save(issue);
    }
}
