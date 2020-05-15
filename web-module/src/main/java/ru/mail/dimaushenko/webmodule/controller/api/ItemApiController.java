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
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddItemDTO;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.UserDTO;

@RestController
@RequestMapping("/api/items")
public class ItemApiController {

    private final ItemService itemService;
    private final UserService userService;

    public ItemApiController(ItemService itemService, UserService userService) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public List<ItemDTO> getItems() {
        List<ItemDTO> items = itemService.getItems();
        return items;
    }

    @PostMapping
    public ResponseEntity addItem(
            @Valid @RequestBody AddItemDTO addItemDTO,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());
        addItemDTO.setUser(user);
        ItemDTO itemDTO = itemService.addItem(addItemDTO);
        return new ResponseEntity(itemDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ItemDTO getItem(@PathVariable(name = "id") Long id) {
        return itemService.getItem(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteItem(@PathVariable(name = "id") Long id) {
        Boolean isDeleteItem = itemService.deleteItem(id);
        if (isDeleteItem) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_GATEWAY);
        }
    }

}
