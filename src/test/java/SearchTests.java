import org.junit.Assert;
import org.junit.Test;

public class SearchTests extends SearchTestUtils {

    @Test
    public void compareSearchResults() {
        String ımdbId = getImdbIdByMovieName("Harry Potter");
        String movieName = getMovieNameByImdbId(ımdbId);
        Assert.assertEquals("Harry Potter and the Sorcerer's Stone", movieName);
    }

}
