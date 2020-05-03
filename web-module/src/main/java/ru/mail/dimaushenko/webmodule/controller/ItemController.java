package ru.mail.dimaushenko.webmodule.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static java.util.UUID.fromString;
import javax.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import ru.mail.dimaushenko.service.ItemService;
import ru.mail.dimaushenko.service.UserService;
import ru.mail.dimaushenko.service.model.AddBasketDTO;
import ru.mail.dimaushenko.service.model.AppUser;
import ru.mail.dimaushenko.service.model.ItemDTO;
import ru.mail.dimaushenko.service.model.ItemPreviewDTO;
import ru.mail.dimaushenko.service.model.PaginationDTO;
import ru.mail.dimaushenko.service.model.UserDTO;
import ru.mail.dimaushenko.service.model.ViewItemsDTO;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_CURRENT_PAGE;
import static ru.mail.dimaushenko.webmodule.constants.PaginationConstants.DEFAULT_ITEMS_PER_PAGE;

@Controller
@RequestMapping("/items")
@SessionAttributes(types = ViewItemsDTO.class)
public class ItemController {

    private final ItemService itemService;
    private final UserService userService;

    public ItemController(
            ItemService itemService,
            UserService userService
    ) {
        this.itemService = itemService;
        this.userService = userService;
    }

    @GetMapping
    public String getItems(
            ViewItemsDTO viewItemsDTO,
            Model model
    ) {
        PaginationDTO pagination = setPagination(viewItemsDTO.getPagination());
        viewItemsDTO.setPagination(pagination);
        List<ItemPreviewDTO> items = itemService.getItemPreviews(pagination);
        viewItemsDTO.setItems(items);
        model.addAttribute("viewItems", viewItemsDTO);
        return "items";
    }

    @GetMapping("/{uuid}")
    public String getItem(
            @PathVariable(name = "uuid") String uuidStr,
            Model model
    ) {
        model.addAttribute("item", itemService.getItem(UUID.fromString(uuidStr)));
        model.addAttribute("new_basket", new AddBasketDTO());
        return "item";
    }

    @PostMapping("/{uuid}/copy")
    public String copyItem(
            @PathVariable(name = "uuid") String uuidSrt,
            Authentication authentication,
            Model model
    ) {
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());
        ItemDTO item = itemService.getItem(UUID.fromString(uuidSrt));
        item.setUser(user);
        ItemDTO copyItem = itemService.copyItem(item);
        model.addAttribute("item", copyItem);
        return "item_edit";
    }

    @PostMapping("/{uuid}/save")
    public String saveItem(
            @PathVariable(name = "uuid") String uuidSrt,
            @Valid @ModelAttribute ItemDTO itemDTO,
            BindingResult bindingResult,
            Model model
    ) {
        itemDTO.setUuid(fromString(uuidSrt));
        if (bindingResult.hasErrors()) {
            return "item_edit";
        }
        ItemDTO item = itemService.updateItem(itemDTO);
        return "redirect:/items/{uuid}";
    }

    @PostMapping("/{uuid}/delete")
    public String deleteItem(
            @PathVariable(name = "uuid") String uuidSrt
    ) {
        itemService.deleteItem(UUID.fromString(uuidSrt));
        return "redirect:/items";
    }

    @PostMapping("/{uuid}/buskets")
    public String addToBasket(
            @Valid @ModelAttribute(name = "new_basket") AddBasketDTO addBusket,
            @PathVariable(name = "uuid") String uuidStr,
            BindingResult bindingResult,
            Authentication authentication
    ) {
        ItemDTO item = itemService.getItem(UUID.fromString(uuidStr));
        addBusket.setItem(item);
        AppUser appUser = (AppUser) authentication.getPrincipal();
        UserDTO user = userService.getUserByEmail(appUser.getUsername());
        addBusket.setUser(user);
        itemService.addToBusket(addBusket);
        return "redirect:/items/{uuid}";
    }

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            Authentication authentication
    ) {
        if (file.getContentType().equals("text/xml")) {
            AppUser appUser = (AppUser) authentication.getPrincipal();
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    String filename = file.getOriginalFilename();
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filename)));
                    stream.write(bytes);
                    stream.close();
                    System.out.println("Вы удачно загрузили " + filename);
                    itemService.addItemFromFile(appUser.getUsername(), filename);
                } catch (Exception e) {
                    System.out.println("Вам не удалось загрузить " + file.getOriginalFilename() + " => " + e.getMessage());
                }
            } else {
                System.out.println("Вам не удалось загрузить " + file.getOriginalFilename() + " потому что файл пустой.");
            }
        }
        return "redirect:/items";
    }

    private PaginationDTO setPagination(PaginationDTO pagination) {
        if (pagination == null) {
            pagination = new PaginationDTO();
        }
        if (pagination.getCurrentPage() == null) {
            pagination.setCurrentPage(DEFAULT_CURRENT_PAGE);
        }
        if (pagination.getElementsPerPage() == null) {
            pagination.setElementsPerPage(DEFAULT_ITEMS_PER_PAGE);
        }
        Integer amountItems = itemService.getAmountItems();
        Integer amountPages = amountItems / pagination.getElementsPerPage();
        if (amountItems % pagination.getElementsPerPage() > 0) {
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
