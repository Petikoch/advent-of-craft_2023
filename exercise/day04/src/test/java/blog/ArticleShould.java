package blog;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleShould {

    public static final Article TESTEE = new Article(
            "Lorem Ipsum",
            "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
    );

    public static final String COMMENT_TEXT = "Amazing article !!!";
    public static final String COMMENT_AUTHOR = "Pablo Escobar";

    @Test
    void addValidComment() throws CommentAlreadyExistException {
        assertThat(TESTEE.getComments()).hasSize(0);

        TESTEE.addComment(COMMENT_TEXT, COMMENT_AUTHOR);

        assertThat(TESTEE.getComments()).hasSize(1);
        var actualComment = TESTEE.getComments().getFirst();
        assertThat(actualComment.text()).isEqualTo(COMMENT_TEXT);
        assertThat(actualComment.author()).isEqualTo(COMMENT_AUTHOR);
    }

    @Test
    void addCommentWithDateOfTodayByDefault() throws CommentAlreadyExistException {
        TESTEE.addComment("Amazing article !!!", "Pablo Escobar");

        assertThat(TESTEE.getComments()).hasSize(1);
        var actualComment = TESTEE.getComments().getFirst();
        assertThat(actualComment.creationDate()).isEqualTo(LocalDate.now()); // flaky at midnight ⚡️
    }

    @Test
    void throwExceptionWhenAddingSameComment() throws CommentAlreadyExistException {
        TESTEE.addComment(COMMENT_TEXT, COMMENT_AUTHOR);

        assertThatThrownBy(
                () -> TESTEE.addComment(COMMENT_TEXT, COMMENT_AUTHOR)
        ).isInstanceOf(CommentAlreadyExistException.class);
    }
}
