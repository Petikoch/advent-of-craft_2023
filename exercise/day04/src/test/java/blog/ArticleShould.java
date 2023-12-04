package blog;

import org.junit.jupiter.api.Test;

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
    void it_should_add_a_comment_with_the_given_author() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        var author = "Pablo Escobar";
        article.addComment("Amazing article !!!", author);

        assertThat(article.getComments())
                .hasSize(1)
                .anyMatch(comment -> comment.author().equals(author));
    }

    @Test
    void it_should_add_a_comment_with_the_date_of_the_day() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        article.addComment("Amazing article !!!", "Pablo Escobar");
    }

    @Test
    void it_should_throw_an_exception_when_adding_existing_comment() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        article.addComment("Amazing article !!!", "Pablo Escobar");

        assertThatThrownBy(() -> {
            article.addComment("Amazing article !!!", "Pablo Escobar");
        }).isInstanceOf(CommentAlreadyExistException.class);
    }
}
