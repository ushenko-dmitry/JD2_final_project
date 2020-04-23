package ru.mail.dimaushenko.service.impl;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.service.CommentService;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ConverterFacade converterFacade;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            ConverterFacade converterFacade
    ) {
        this.commentRepository = commentRepository;
        this.converterFacade = converterFacade;
    }

    @Override
    public List<CommentDTO> getComments(PaginationDTO paginationDTO) {
        PaginationConverter paginationConverter = converterFacade.getPaginationConverter();
        Pagination pagination = paginationConverter.getObjectFromDTO(paginationDTO);
        List<Comment> comments = commentRepository.getComments(pagination);
        CommentConverter commentConverter = converterFacade.getCommentConverter();
        List<CommentDTO> commentDTOs = commentConverter.getDTOFromObject(comments);
        return commentDTOs;
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id);
        comment.setUser(null);
        comment.setArticle(null);
        commentRepository.remove(comment);
    }

    @Override
    public Integer getAmountComments() {
        return commentRepository.getAmountElements();
    }

    @Override
    public CommentDTO getCommentById(Long commentId) {
        Comment comment = commentRepository.findById(commentId);
        return converterFacade.getCommentConverter().getDTOFromObject(comment);
    }

}
