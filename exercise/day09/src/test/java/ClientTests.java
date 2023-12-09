import account.Client;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static java.lang.System.lineSeparator;
import static org.assertj.core.api.Assertions.assertThat;

class ClientTests {
    private final Client client = new Client(new LinkedHashMap<>() {{
        put("Tenet Deluxe Edition", 45.99);
        put("Inception", 30.50);
        put("The Dark Knight", 30.50);
        put("Interstellar", 23.98);
    }});
    private static final double EXPECTED_TOTAL_AMOUNT = 130.97;
    private static final String EXPECTED_STATEMENT = "Tenet Deluxe Edition for 45.99€" + lineSeparator() +
                                                     "Inception for 30.5€" + lineSeparator() +
                                                     "The Dark Knight for 30.5€" + lineSeparator() +
                                                     "Interstellar for 23.98€" + lineSeparator() +
                                                     "Total : " + EXPECTED_TOTAL_AMOUNT + "€";

    @Test
    void client_should_return_total_amount() {
        assertThat(client.getTotalAmount()).isEqualTo(EXPECTED_TOTAL_AMOUNT);
    }

    @Test
    void client_should_return_statement() {
        String statement = client.toStatement();

        assertThat(statement).isEqualTo(EXPECTED_STATEMENT);
    }

    @Test
    void client_should_return_same_statement_second_time() {
        //noinspection UnusedAssignment
        String statement = client.toStatement();
        statement = client.toStatement();

        assertThat(statement).isEqualTo(EXPECTED_STATEMENT);
    }
}