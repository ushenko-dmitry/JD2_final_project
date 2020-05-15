package ru.mail.dimaushenko.service.impl;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.mail.dimaushenko.repository.ArticleRepository;
import ru.mail.dimaushenko.repository.CommentRepository;
import ru.mail.dimaushenko.repository.UserRepository;
import ru.mail.dimaushenko.repository.model.Article;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.repository.model.Pagination;
import ru.mail.dimaushenko.repository.model.User;
import ru.mail.dimaushenko.service.CommentService;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.ConverterFacade;
import ru.mail.dimaushenko.service.converter.PaginationConverter;
import ru.mail.dimaushenko.service.model.AddCommentDTO;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;
    private final ConverterFacade converterFacade;

    public CommentServiceImpl(
            CommentRepository commentRepository,
            UserRepository userRepository,
            ArticleRepository articleRepository,
            ConverterFacade converterFacade
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
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

    @Override
    public void addComment(AddCommentDTO addCommentDTO) {
        Comment comment = new Comment();
        comment.setComment(addCommentDTO.getComment());
        User user = userRepository.findById(addCommentDTO.getUserId());
        Article article = articleRepository.findById(addCommentDTO.getArticleId());
        comment.setUser(user);
        comment.setArticle(article);
        comment.setCreationDate(new Date(System.currentTimeMillis()));
        comment.setIsVisible(true);
        commentRepository.persist(comment);
    }

}
