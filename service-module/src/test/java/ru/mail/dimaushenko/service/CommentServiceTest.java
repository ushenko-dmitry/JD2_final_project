package ru.mail.dimaushenko.service;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.impl.CommentServiceImpl;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @Mock
    private CommentConverter commentConverter;
    @Mock
    private PaginationConverter paginationConverter;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ConverterFacade converterFacade;

    private CommentService commentService;

    @Before
    public void setup() {
        commentService = new CommentServiceImpl(commentRepository, converterFacade);
    }

    @Test
    public void testGetComments() {
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setCurrentPage(1);
        paginationDTO.setElementsPerPage(10);
        doReturn(paginationConverter).when(converterFacade).getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        doReturn(new ArrayList<Comment>()).when(commentRepository).getComments(pagination);
        doReturn(commentConverter).when(converterFacade).getCommentConverter();
        List<CommentDTO> commentDTOs = commentService.getComments(paginationDTO);
        assertThat(commentDTOs).isNotNull();
        assertThat(commentDTOs).isEqualTo(anyList());
    }

    @Test
    public void testDeleteComment() {
        Long id = 1L;
        Comment comment = new Comment();
        doReturn(comment).when(commentRepository).findById(id);
        commentService.deleteComment(id);
        verify(commentRepository, times(1)).remove(comment);
    }

    @Test
    public void testGetAmountComments() {
        commentService.getAmountComments();
        verify(commentRepository, times(1)).getAmountElements();
        when(commentService.getAmountComments()).
                thenReturn(anyInt());
    }
}
