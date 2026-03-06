package Library_Management.Springboot.service;

import Library_Management.Springboot.entity.Member;
import Library_Management.Springboot.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final NotificationService notificationService; // Added

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member addMember(Member member) {
        Member savedMember = memberRepository.save(member);
        notificationService.createNotification("New member added: " + savedMember.getName());
        return savedMember;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot delete. Member not found with id: " + id));

        String name = member.getName();
        memberRepository.deleteById(id);
        notificationService.createNotification("Removed member: " + name);
    }

    public Member updateMember(Long id, Member memberDetails) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        if (memberDetails.getName() != null && !memberDetails.getName().isEmpty()) {
            member.setName(memberDetails.getName());
        }

        if (memberDetails.getEmail() != null && !memberDetails.getEmail().isEmpty()) {
            member.setEmail(memberDetails.getEmail());
        }


        if (memberDetails.getPhone() != null && !memberDetails.getPhone().isEmpty()) {
            member.setPhone(memberDetails.getPhone());
        }

        // 5. Save the modified 'member' object
        Member updatedMember = memberRepository.save(member);
        notificationService.createNotification("Updated member profile: " + updatedMember.getName());
        return updatedMember;
    }
}
