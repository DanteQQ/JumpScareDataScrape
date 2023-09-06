import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class JumpScareDataScrapeTest {

    @Test
    void splitMovieRows() {
        // Arrange
        String movieList = "\n" +
                "https://wheresthejump.com/jump-scares-in-cat-people-1942/|Cat People|Jacques Tourneur|1942|2|1.0|No\n" +
                "https://wheresthejump.com/jump-scares-in-caveat-2021/|Caveat|Damian McCarthy|2021|9|2.5|No\n" +
                "https://wheresthejump.com/jump-scares-in-cell-2016/|Cell|Tod Williams|2016|5|2.0|No";

        String[][] expectedSplitMovieList = {
                {"https://wheresthejump.com/jump-scares-in-cat-people-1942/", "Cat People", "Jacques Tourneur", "1942", "2"},
                {"https://wheresthejump.com/jump-scares-in-caveat-2021/", "Caveat", "Damian McCarthy", "2021", "9"},
                {"https://wheresthejump.com/jump-scares-in-cell-2016/", "Cell", "Tod Williams", "2016", "5"}
        };

        // Act
        String[][] actualSplitMovieList = JumpScareDataScrape.splitMovieRows(movieList);

        // Assert
        assertArrayEquals(expectedSplitMovieList, actualSplitMovieList);
    }

    @Test
    void splitMovieJumpScareTimes() {
        // Arrange
        String jumpScareTimesList =
                "00:18:00 – A body falls down from the stairwell next to a group of people on the ground floor.\n" +
                        "00:19:54 – Someone runs through the door space.\n" +
                        "01:05:51 – The camera spins around in the attic where a boy’s face appears. – Strong";

        String[][] expectedSplitJumpScareTimesList = {
                {"00:18:00", "A body falls down from the stairwell next to a group of people on the ground floor.", null},
                {"00:19:54", "Someone runs through the door space.", null},
                {"01:05:51", "The camera spins around in the attic where a boy’s face appears.", "Strong"}
        };

        // Act
        String[][] actualSplitMovieList = JumpScareDataScrape.splitMovieJumpScareTimes(jumpScareTimesList);

        // Assert
        assertArrayEquals(expectedSplitJumpScareTimesList, actualSplitMovieList);
    }
}