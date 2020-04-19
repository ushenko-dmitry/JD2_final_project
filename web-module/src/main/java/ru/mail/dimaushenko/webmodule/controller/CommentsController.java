package ru.mail.dimaushenko.webmodule.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.CommentService;
import ru.mail.dimaushenko.service.model.CommentDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.ViewCommentsDTO;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_COMMENTS_PER_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;

@Controller
@RequestMapping("/comments")
@SessionAttributes(types = ViewCommentsDTO.class)
public class CommentsController {

    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String showComments(
            ViewCommentsDTO viewCommentDTO,
            Model model) {
        PaginationDTO pagination = setPagination(viewCommentDTO.getPagination());
        viewCommentDTO.setPagination(pagination);
        List<CommentDTO> comments = commentService.getComments(pagination);
        viewCommentDTO.setComments(comments);
        model.addAttribute("viewComments", viewCommentDTO);
        return "comments";
    }

    @PostMapping("/{id}/delete")
    public String deleteComment(
            @PathVariable Long id
    ) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }

    private PaginationDTO setPagination(PaginationDTO pagination) {
        if (pagination == null) {
            pagination = new PaginationDTO();
        }
        if (pagination.getCurrentPage() == null) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        if (pagination.getElementsPerPage() == null) {
            pagination.setElementsPerPage(DEFAULT_COMMENTS_PER_PAGE);
        }
        Integer amountComments = commentService.getAmountComments();
        Integer amountPages = amountComments / pagination.getElementsPerPage();
        if (amountComments % pagination.getElementsPerPage() > 0) {
            amountPages++;
        }
        if (amountPages == 0) {
            amountPages++;
        }
        pagination.setPageNumbers(new ArrayList<>());
        for (int i = 1; i <= amountPages; i++) {
            pagination.getPageNumbers().add(i);
        }
        if (pagination.getPageNumbers().size() < pagination.getCurrentPage()) {
            pagination.setCurrentPage(1);
        }
        return pagination;
    }

}
