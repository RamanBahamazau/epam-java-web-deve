import com.bahamazau.composite.common.TextElementMother;
import com.bahamazau.dao.TextReader;
import com.bahamazau.parser.TextParserBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;

public class TextReaderText {

    private TextReader textReader = new TextReader();

    @Test
    public void should_whenReadText_given() throws IOException {
        // given
        String expectedValue = "    It has survived - not only (five) centuries, but also the leap into electronic " +
                "typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) " +
                "with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and " +
                "more recently with desktop publishing software like Aldus PageMaker Faclon9 including " +
                "versions of Lorem Ipsum!\r\n" +
                "    It is a long a!=b established fact that a reader will be distracted by the readable " +
                "content of a page when looking at its layout. The point of using Ipsum is that it has a " +
                "more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), " +
                "content here's, making it look like readable English?\r\n" +
                "    It is a established fact that a reader will be of a page when looking at its layout...\r\n" +
                "    Bye бандерлоги.";
        Optional<String> expected = Optional.of(expectedValue);
        // when
        Optional<String> actual = textReader.readText("textExample.txt");
        // then
        assertEquals(expected, actual);

        actual.ifPresent(act -> {
            TextElementMother list = new TextParserBuilder().build().parse(act);
        });
    }

}
