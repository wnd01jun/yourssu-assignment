package yourssu.assignment.domain.article.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import yourssu.assignment.domain.article.dto.ArticleRequestDTO;
import yourssu.assignment.domain.article.dto.ArticleResponseDTO;
import yourssu.assignment.domain.article.service.ArticleService;
import yourssu.assignment.domain.article.validation.ExistArticle;

@RestController
@RequiredArgsConstructor
public class ArticleController {

     private final ArticleService articleService;

     @PostMapping("/articles")
     public ArticleResponseDTO.ArticlePostResponse postArticle(
             @RequestBody @Valid ArticleRequestDTO.ArticlePostRequest dto
     ) {
         return articleService.postArticle(dto);
     }

     @PutMapping("/articles/{articleId}")
     public ArticleResponseDTO.ArticleModifyResponse modifyArticle(
             @RequestBody @Valid ArticleRequestDTO.ArticleModifyRequest dto,
             @PathVariable @ExistArticle @Valid Long articleId
     ) {
         return articleService.modifyArticle(dto, articleId);
     }

     @DeleteMapping("/articles/{articleId}")
    public String deleteArticle(
            ArticleRequestDTO.ArticleWithdrawRequest dto,
            @PathVariable @ExistArticle @Valid Long articleId
     ) {
         articleService.withdrawArticle(dto, articleId);
         return "ok";
     }


}
