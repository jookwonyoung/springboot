package spring.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import spring.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor  //기본 생성자 자동 추가
@Entity //테이블과 링크될 클래스 어노테이션
//Entity 클래스에서는 절대 Setter 메소드 생성하지 않음
public class Posts extends BaseTimeEntity {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //pk 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false) //기본값 사용x, 변경값 사용(500)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    //빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content =content;
    }

}

