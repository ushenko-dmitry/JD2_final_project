package ru.mail.dimaushenko.service.converter.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ru.mail.dimaushenko.repository.model.Comment;
import ru.mail.dimaushenko.service.converter.CommentConverter;
import ru.mail.dimaushenko.service.converter.UserConverter;
import ru.mail.dimaushenko.service.model.CommentArticleDTO;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.UserDetailsDTO;

@Component
public class CommentConverterImpl implements CommentConverter {

    private final UserConverter userConverter;

    public CommentConverterImpl(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public CommentDTO getDTOFromObject(Comment model) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(model.getId());
        commentDTO.setComment(model.getComment());
        commentDTO.setCreationDate(model.getCreationDate());
        commentDTO.setIsVisible(model.isIsVisible());
        UserDTO userDTO = userConverter.getDTOFromObject(model.getUser());
        commentDTO.setUser(userDTO);
        return commentDTO;
    }

    @Override
    public List<CommentDTO> getDTOFromObject(List<Comment> comments) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            commentDTOs.add(getDTOFromObject(comment));
        }
        return commentDTOs;
    }

    @Override
    public Comment getObjectFromDTO(CommentDTO modelDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comment> getObjectFromDTO(List<CommentDTO> modelDTOs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CommentArticleDTO getCommentArticleFromObject(Comment comment) {
        CommentArticleDTO commentArticleDTO = new CommentArticleDTO();
        commentArticleDTO.setId(comment.getId());
        commentArticleDTO.setComment(comment.getComment());
        commentArticleDTO.setCreationDate(comment.getCreationDate());
        commentArticleDTO.setIsVisible(comment.isIsVisible());

        UserDTO userDTO = userConverter.getDTOFromObject(comment.getUser());
        UserDetailsDTO userDetailsDTO = userDTO.getUserDetails();
        commentArticleDTO.setUserDetails(userDetailsDTO);
        return commentArticleDTO;
    }

    @Override
    public List<CommentArticleDTO> getCommentArticleFromObject(List<Comment> comments) {
        List<CommentArticleDTO> commentArticleDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            commentArticleDTOs.add(getCommentArticleFromObject(comment));
        }
        return commentArticleDTOs;
    }

}
