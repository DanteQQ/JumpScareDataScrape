import org.jsoup.Jsoup;
import java.io.IOException;
import java.util.Arrays;

public class JumpScareDataScrape {
    public static void main(String[] args) throws IOException {
        getMovieList();
    }
    public static void getMovieList() throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect("https://wheresthejump.com/full-movie-list/").get();
        org.jsoup.select.Elements rows = doc.select("tr");
        for(org.jsoup.nodes.Element row :rows)
        {
            StringBuilder rowText = new StringBuilder();
            rowText.append(row.select("a").attr("href"));
            org.jsoup.select.Elements columns = row.select("td");
            for (org.jsoup.nodes.Element column:columns)
            {
                rowText.append("|");
                rowText.append(column.text());
            }
            if (!rowText.isEmpty()) {
                String[] splittedRow = rowText.toString().split("\\|");
                System.out.println(Arrays.toString(splittedRow));
                //Movie movie = new Movie(splittedRow[0], splittedRow[1], splittedRow[2], Integer.parseInt(splittedRow[3]), Integer.parseInt(splittedRow[4]));
            }
            System.out.println();
        }
    }
}