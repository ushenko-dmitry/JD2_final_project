package ru.mail.dimaushenko.webmodule.controller.api;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dimaushenko.service.ArticleService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddArticleDTO;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.ArticleDTO;
import ru.mail.dimaushenko.service.model.ArticlePreviewDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@RestController
@RequestMapping("/api/articles")
public class ArticleApiController {

    private final ArticleService articleService;
    private final UserService userService;

    public ArticleApiController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity addArticle(
            @Valid @RequestBody AddArticleDTO addArticleDTO,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());
        addArticleDTO.setUserId(user.getId());
        ArticleDTO articleDTO = articleService.addArticle(addArticleDTO);
        return new ResponseEntity(articleDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public List<ArticlePreviewDTO> getAtricles() {
        return articleService.getArticlePreviews();
    }

    @GetMapping("/{id}")
    public ArticleDTO getArticle(@PathVariable(name = "id") String idStr) {
        Long id = Long.parseLong(idStr);
        return articleService.getArticle(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteArticle(@PathVariable(name = "id") String idStr) {
        Long id = Long.parseLong(idStr);
        boolean isDelete = articleService.deleteArticle(id);
        if (!isDelete) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }


}
