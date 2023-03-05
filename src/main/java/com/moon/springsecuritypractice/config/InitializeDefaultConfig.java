package com.moon.springsecuritypractice.config;

import com.moon.springsecuritypractice.note.NoteService;
import com.moon.springsecuritypractice.notice.NoticeService;
import com.moon.springsecuritypractice.user.User;
import com.moon.springsecuritypractice.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@RequiredArgsConstructor
@Profile(value = "!test") // test 에서는 제외
public class InitializeDefaultConfig {

    private final UserService userService;
    private final NoteService noteService;
    private final NoticeService noticeService;

    /**
     * 유저등록, note 4개 등록
     */
    @Bean
    public void initializeDefaultUser() {
        User user = userService.signup("user", "user");
        // 개인 노트를 작성한다.
        noteService.saveNote(user, "테스트", "테스트입니다.");
        noteService.saveNote(user, "테스트2", "테스트2입니다.");
        noteService.saveNote(user, "테스트3", "테스트3입니다.");
        noteService.saveNote(user, "여름 여행계획", "여름 여행계획 작성중...");
    }

    /**
     * 어드민등록, 공지사항 2개 등록
     */
    @Bean
    public void initializeDefaultAdmin() {
        userService.signupAdmin("admin", "admin");
        noticeService.saveNotice("환영합니다.", "환영합니다 여러분");
        noticeService.saveNotice("노트 작성 방법 공지", "1. 회원가입\n2. 로그인\n3. 노트 작성\n4. 저장\n* 본인 외에는 게시글을 볼 수 없습니다.");
    }
}
