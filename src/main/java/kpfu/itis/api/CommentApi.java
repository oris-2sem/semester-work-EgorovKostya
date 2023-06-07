package kpfu.itis.api;

import kpfu.itis.dto.request.CommentRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequestMapping("/comments")
public interface CommentApi {

    @PostMapping("/{titleId}")
    ModelAndView addComment(@PathVariable UUID titleId, @RequestParam String value);

}
