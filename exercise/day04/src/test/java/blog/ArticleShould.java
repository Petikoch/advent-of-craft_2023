package blog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleShould {

    @Test
    void addValidComment() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        assertThat(article.getComments()).hasSize(0);

        String commentText = "Amazing article !!!";
        String commentAuthor = "Pablo Escobar";
        article.addComment(commentText, commentAuthor);

        assertThat(article.getComments()).hasSize(1);
        var actualComment = article.getComments().getFirst();
        assertThat(actualComment.text()).isEqualTo(commentText);
        assertThat(actualComment.author()).isEqualTo(commentAuthor);
    }

    @Test
    void addCommentWithDateOfTodayByDefault() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        article.addComment("Amazing article !!!", "Pablo Escobar");

        assertThat(article.getComments()).hasSize(1);
        var actualComment = article.getComments().getFirst();
        assertThat(actualComment.creationDate()).isEqualTo(LocalDate.now()); // flaky at midnight ⚡️
    }

    @Test
    void throwExceptionWhenAddingSameComment() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        String commentText = "Amazing article !!!";
        String commentAuthor = "Pablo Escobar";
        article.addComment(commentText, commentAuthor);

        assertThatThrownBy(
                () -> article.addComment(commentText, commentAuthor)
        ).isInstanceOf(CommentAlreadyExistException.class);
    }
}
