package xyz.hugme.hugmebackend.domain.counselor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CounselorService {
    private final CounselorRepository counselorRepository;

    @Transactional
    public Counselor save(Counselor counselor) {
        return counselorRepository.save(counselor);
    }

    public List<Counselor> findAll(){
        return counselorRepository.findAll();
    }
}