package com.likelion.seminar;

import com.likelion.seminar.entity.Author;
import com.likelion.seminar.entity.Board;
import com.likelion.seminar.entity.Comment;
import com.likelion.seminar.entity.Post;
import com.likelion.seminar.repository.AuthorRepository;
import com.likelion.seminar.repository.BoardRepository;
import com.likelion.seminar.repository.CommentRepository;
import com.likelion.seminar.repository.PostRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardMappingTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    void boardMappingTest() {
        // 작성자와 게시판 저장
        Author author = authorRepository.save(new Author("작성자"));
        Board board = boardRepository.save(new Board("자유게시판"));

        // 게시글에 작성자와 게시판 관계 설정 후 저장
        Post post = new Post("게시글 제목", "게시글 내용");
        post.setAuthor(author);
        post.setBoard(board);
        postRepository.save(post);

        // 댓글에 작성자와 게시글 관계 설정 후 저장
        Comment comment = new Comment("댓글 내용");
        comment.setAuthor(author);
        comment.setPost(post);
        commentRepository.save(comment);

        Long authorId = author.getId();
        Long boardId = board.getId();
        Long postId = post.getId();
        Long commentId = comment.getId();

        // DB에 반영한 뒤 영속성 컨텍스트를 비우고 다시 조회
        entityManager.flush();
        entityManager.clear();

        Author savedAuthor = authorRepository.findById(authorId).orElseThrow();
        Board savedBoard = boardRepository.findById(boardId).orElseThrow();
        Post savedPost = postRepository.findById(postId).orElseThrow();
        Comment savedComment = commentRepository.findById(commentId).orElseThrow();

        // 게시판 1:N 게시글 관계 테스트
        assertThat(savedBoard.getPosts()).hasSize(1);
        assertThat(savedPost.getBoard().getName()).isEqualTo("자유게시판");

        // 작성자 1:N 게시글 관계 테스트
        assertThat(savedAuthor.getPosts()).hasSize(1);
        assertThat(savedPost.getAuthor().getName()).isEqualTo("작성자");

        // 게시글 1:N 댓글 관계 테스트
        assertThat(savedPost.getComments()).hasSize(1);
        assertThat(savedComment.getPost().getTitle()).isEqualTo("게시글 제목");

        // 작성자 1:N 댓글 관계 테스트
        assertThat(savedAuthor.getComments()).hasSize(1);
        assertThat(savedComment.getAuthor().getName()).isEqualTo("작성자");

    }
}
