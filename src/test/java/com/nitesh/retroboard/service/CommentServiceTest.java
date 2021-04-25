package com.nitesh.retroboard.service;

import com.nitesh.retroboard.model.Comment;
import com.nitesh.retroboard.model.CommentType;
import com.nitesh.retroboard.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentService commentService;

    @Test
    void getAllCommentsForToday_HappyPath_ShouldReturn1Comment() {
        // Given
        Comment comment = new Comment();
        comment.setComment("Test");
        comment.setType(CommentType.PLUS);
        comment.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        List<Comment> comments = Arrays.asList(comment);
        LocalDate now = LocalDate.now();
        when(commentRepository.findByCreatedYearAndMonthAndDay(now.getYear(),
                now.getMonth().getValue(),
                now.getDayOfMonth())).thenReturn(comments);
        // When
        List<Comment> actualComments =
                commentService.getAllCommentsForToday();
        // Then
        verify(commentRepository,
                times(1)).findByCreatedYearAndMonthAndDay(now.getYear(),
                now.getMonth().getValue(), now.getDayOfMonth());
        assertThat(comments).isEqualTo(actualComments);
    }

    @Test
    void saveAll_HappyPath_ShouldSave2Comments() {
        // Given
        Comment comment = new Comment();
        comment.setComment("Test Plus");
        comment.setType(CommentType.PLUS);
        comment.setCreatedBy("Shazin");
        comment.setCreatedDate(new
                Timestamp(System.currentTimeMillis()));
        Comment comment2 = new Comment();
        comment2.setComment("Test Star");
        comment2.setType(CommentType.STAR);
        comment2.setCreatedBy("Shahim");
        comment2.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        List<Comment> comments = Arrays.asList(comment, comment2);
        when(commentRepository.saveAll(comments)).thenReturn(comments);
        // When
        List<Comment> saved = commentService.saveAll(comments);
        // Then
        assertThat(saved).isNotEmpty();
        verify(commentRepository, times(1)).saveAll(comments);
    }
}
