package com.process.shop;

import com.process.shop.exceptions.AlreadyExistsException;
import com.process.shop.exceptions.NotFoundException;
import com.process.shop.model.Article;
import com.process.shop.model.enums.ErrorMessages;
import com.process.shop.repository.ArticleRepository;
import com.process.shop.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class ShopApplicationTests {

	@Autowired
	private ArticleService articleService;

	@MockBean
	private ArticleRepository articleRepository;

	private Article article;

	@BeforeEach
	void setUp() {
		article = new Article();
		article.setId(1L);
		article.setName("Sample Article");
		article.setCode("ART123");
		article.setDescription("Sample Description");
		article.setPrice(100.0);
		article.setStock(10);
	}


	@Test
	void testCreateArticle() {
		when(articleRepository.findByCode(any(String.class))).thenReturn(Optional.empty());
		when(articleRepository.save(any(Article.class))).thenReturn(article);

		Article createdArticle = articleService.createArticle(article);

		assertThat(createdArticle).isNotNull();
		assertThat(createdArticle.getName()).isEqualTo("Sample Article");
	}

	@Test
	void testCreateArticleThrowsAlreadyExistsException() {
		when(articleRepository.findByCode(any(String.class))).thenReturn(Optional.of(article));

		assertThatThrownBy(() -> articleService.createArticle(article))
				.isInstanceOf(AlreadyExistsException.class)
				.hasMessage(ErrorMessages.ARTICLE_CODE_EXISTS.getMessage());
	}

	@Test
	void testUpdateArticle() {
		when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));
		when(articleRepository.save(any(Article.class))).thenReturn(article);

		Article updatedArticle = articleService.updateArticle(article, 1L);

		assertThat(updatedArticle).isNotNull();
		assertThat(updatedArticle.getName()).isEqualTo("Sample Article");
	}

	@Test
	void testUpdateArticleThrowsNotFoundException() {
		when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> articleService.updateArticle(article, 1L))
				.isInstanceOf(NotFoundException.class)
				.hasMessage(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
	}

	@Test
	void testGetArticleById() {
		when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));

		Article foundArticle = articleService.getArticleById(1L);

		assertThat(foundArticle).isNotNull();
		assertThat(foundArticle.getName()).isEqualTo("Sample Article");
	}

	@Test
	void testGetArticleByIdThrowsNotFoundException() {
		when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> articleService.getArticleById(1L))
				.isInstanceOf(NotFoundException.class)
				.hasMessage(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
	}

	@Test
	void testFindAllArticles() {
		List<Article> articles = Arrays.asList(article);
		when(articleRepository.findAll()).thenReturn(articles);

		List<Article> foundArticles = articleService.findAllArticles();

		assertThat(foundArticles).isNotEmpty();
		assertThat(foundArticles.size()).isEqualTo(1);
		assertThat(foundArticles.get(0).getName()).isEqualTo("Sample Article");
	}

	@Test
	void testDeleteArticle() {
		when(articleRepository.findById(anyLong())).thenReturn(Optional.of(article));
		List<Article> articles = Arrays.asList();
		when(articleRepository.findAll()).thenReturn(articles);

		List<Article> remainingArticles = articleService.deleteArticle(1L);

		verify(articleRepository, times(1)).deleteById(1L);
		assertThat(remainingArticles).isEmpty();
	}

	@Test
	void testDeleteArticleThrowsNotFoundException() {
		when(articleRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThatThrownBy(() -> articleService.deleteArticle(1L))
				.isInstanceOf(NotFoundException.class)
				.hasMessage(ErrorMessages.ARTICLE_NOT_FOUND.getMessage());
	}
}
