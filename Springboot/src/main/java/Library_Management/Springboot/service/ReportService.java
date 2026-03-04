package Library_Management.Springboot.service;

import Library_Management.Springboot.entity.Book;
import Library_Management.Springboot.entity.Member;
import Library_Management.Springboot.repository.BookRepository;
import Library_Management.Springboot.repository.BorrowRepository;
import Library_Management.Springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    public Map<String, Object> getGeneralReport() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalBorrowed", borrowRepository.count());

        List<Long> memberIds = borrowRepository.findTopMemberIds(PageRequest.of(0, 1));
        stats.put("topMember", memberIds.isEmpty() ? "None" :
                memberRepository.findById(memberIds.get(0)).map(Member::getName).orElse("Unknown"));

        List<Long> bookIds = borrowRepository.findTopBookIds(PageRequest.of(0, 1));
        stats.put("topBook", bookIds.isEmpty() ? "None" :
                bookRepository.findById(bookIds.get(0)).map(Book::getTitle).orElse("Unknown"));

        stats.put("totalMembers", memberRepository.count());
        stats.put("totalBooks", bookRepository.count());

        return stats;
    }
}
