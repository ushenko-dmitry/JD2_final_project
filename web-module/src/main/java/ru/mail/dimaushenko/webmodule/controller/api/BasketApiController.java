package ru.mail.dimaushenko.webmodule.controller.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mail.dimaushenko.service.BasketService;
import ru.mail.dimaushenko.service.model.BasketDTO;

@RestController
@RequestMapping("/api/baskets")
public class BasketApiController {

    private final BasketService basketService;

    public BasketApiController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public List<BasketDTO> getBaskets() {
        return basketService.getBaskets();
    }

    @GetMapping("/{id}")
    public BasketDTO getArticle(@PathVariable(name = "id") Long id) {
        return basketService.getBasket(id);
    }

}
