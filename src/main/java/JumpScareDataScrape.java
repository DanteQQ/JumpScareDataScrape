import com.google.gson.Gson;
import org.jsoup.Jsoup;

import java.io.IOException;

public class JumpScareDataScrape {
    public static void main(String[] args) throws IOException {
        //System.out.println(getMovieList("https://wheresthejump.com/full-movie-list/"));
        //splitMovieRows(getMovieList("https://wheresthejump.com/full-movie-list/"));
        //System.out.println(getMovieJumpScareTimes("https://wheresthejump.com/jump-scares-in-rec-2007/"));
        //splitMovieJumpScareTimes(getMovieJumpScareTimes("https://wheresthejump.com/jump-scares-in-rec-2007/"));
        //System.out.println(jumpScareTimesToJson(splitMovieJumpScareTimes(getMovieJumpScareTimes("https://wheresthejump.com/jump-scares-in-rec-2007/"))));
    }

    public static String getMovieList(String url) throws IOException {
        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        org.jsoup.select.Elements rows = doc.select("tr");
        StringBuilder movieList = new StringBuilder();
        for (org.jsoup.nodes.Element row : rows) {
            StringBuilder rowText = new StringBuilder();
            rowText.append(row.select("a").attr("href"));
            org.jsoup.select.Elements columns = row.select("td");
            if (columns.size() > 1) {
                for (org.jsoup.nodes.Element column : columns) {
                    rowText.append("|");
                    rowText.append(column.text());
                }
                movieList.append(rowText);
                movieList.append("\n");
            }
        }
        return movieList.toString();
    }

    public static String[][] splitMovieRows(String movieList) {
        String[] rows = movieList.replaceFirst("\n", "").split("\n");
        String[][] splitMovieList = new String[rows.length][5];
        for (int i = 0; i < rows.length; i++) {
            if (!rows[i].isEmpty()) {
                String[] splitRow = rows[i].split("\\|");
                splitMovieList[i][0] = splitRow[0];
                splitMovieList[i][1] = splitRow[1];
                splitMovieList[i][2] = splitRow[2];
                splitMovieList[i][3] = splitRow[3];
                splitMovieList[i][4] = splitRow[4];
            }
        }
        return splitMovieList;
    }

    public static String getMovieJumpScareTimes(String url) throws IOException {
        StringBuilder jumpScareTimes = new StringBuilder();
        org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
        org.jsoup.select.Elements rows = doc.select("div.entry-content p:has(span)");
        for (org.jsoup.nodes.Element row : rows) {
            jumpScareTimes.append(Jsoup.parse(String.valueOf(row)).text());
            if (row.toString().contains("<strong>")) {
                jumpScareTimes.append(" – Strong");
            }
            jumpScareTimes.append("\n");
        }
        return jumpScareTimes.toString().replace(" [Video].", "");
    }

    public static String[][] splitMovieJumpScareTimes(String jumpScareTimes) {
        String[] rows = jumpScareTimes.split("\n");
        String[][] splitJumpScareList = new String[rows.length][3];
        for (int i = 0; i < rows.length; i++) {
            if (!rows[i].isEmpty()) {
                String[] splitRow = rows[i].split(" – ");
                splitJumpScareList[i][0] = splitRow[0];
                splitJumpScareList[i][1] = splitRow[1];
                if (splitRow.length == 3) {
                    splitJumpScareList[i][2] = splitRow[2];
                } else {
                    splitJumpScareList[i][2] = null;
                }
            }
        }
        return splitJumpScareList;
    }

    public static String jumpScareTimesToJson(String[][] jumpScareArray) {
        Gson gson = new Gson();
        return gson.toJson(jumpScareArray);
    }
}