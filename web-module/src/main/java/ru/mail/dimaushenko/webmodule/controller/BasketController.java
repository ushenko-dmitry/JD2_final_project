package ru.mail.dimaushenko.webmodule.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.mail.dimaushenko.service.BasketService;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.BasketDTO;
import ru.mail.dimaushenko.service.model.BasketPreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.ViewBasketDTO;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_BASKETS_PER_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;

@Controller
@RequestMapping("/baskets")
@SessionAttributes(types = ViewBasketDTO.class)
public class BasketController {

    private final BasketService basketService;
    private final ItemService itemService;
    private final UserService userService;

    public BasketController(
            BasketService basketService,
            ItemService itemService,
            UserService userService
    ) {
        this.basketService = basketService;
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public String getBaskets(
            ViewBasketDTO viewBasketDTO,
            Model model,
            Authentication authentication
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());

        PaginationDTO pagination = setPagination(user, viewBasketDTO.getPagination());
        viewBasketDTO.setPagination(pagination);

        List<BasketPreviewDTO> baskets = basketService.getPreviewBaskets(user, pagination);
        viewBasketDTO.setBaskets(baskets);
        model.addAttribute("viewBaskets", viewBasketDTO);
        return "baskets";
    }

    @GetMapping("/{id}")
    public String getBasket(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        model.addAttribute("basket", basketService.getBasket(id));
        return "basket";
    }

    @GetMapping("/{id}/edit")
    public String getBasketForEdit(
            @PathVariable(name = "id") Long id,
            Model model
    ) {
        model.addAttribute("basket", basketService.getBasket(id));
        return "basket_edit";
    }

    @PostMapping("/{id}/edit")
    public String updateBasket(
            @PathVariable(name = "id") Long id,
            @ModelAttribute(name = "basket") BasketDTO updatedBasket,
            Model model
    ) {
        BasketDTO basket = basketService.getBasket(id);
        basket.setOrderStatus(updatedBasket.getOrderStatus());
        basketService.updateBasket(basket);
        return "redirect:/baskets/" + id;
    }

    private PaginationDTO setPagination(UserDTO user, PaginationDTO pagination) {
        if (pagination == null) {
            pagination = new PaginationDTO();
        }
        if (pagination.getCurrentPage() == null) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        if (pagination.getElementsPerPage() == null) {
            pagination.setElementsPerPage(DEFAULT_BASKETS_PER_PAGE);
        }
        Integer amountBaskets = basketService.getAmountBaskets(user);
        Integer amountPages = amountBaskets / pagination.getElementsPerPage();
        if (amountBaskets % pagination.getElementsPerPage() > 0) {
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
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        return pagination;
    }
}
