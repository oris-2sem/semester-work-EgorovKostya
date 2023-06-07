package kpfu.itis.api;

import kpfu.itis.dto.request.TitleRegistrationRequest;
import kpfu.itis.model.TitleEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping("/titles")
public interface TitleApi {

    @GetMapping
    ModelAndView getTitlesByName(
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "9") Integer size);

    @GetMapping("{titleId}")
    ModelAndView getTitle(@PathVariable UUID titleId);

    @DeleteMapping("{titleId}")
    ModelAndView deleteTitle(@PathVariable UUID titleId);

    @PostMapping
    ModelAndView addTitle(@Valid @ModelAttribute("title") TitleRegistrationRequest request, BindingResult bindingResult);


    @GetMapping("/popular")
    TitleEntity getPopular();
}
