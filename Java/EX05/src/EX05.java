import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Ragnar on 24.02.2016.
 * EX05.
 */
public class EX05 {
    /**
     * The normal size of the split array of a line.
     * Goddammit you magic number.
     */
    public static final int LINES_SIZE = 4;
    /**
     * The idx for the description part.
     * Why is checkstyle so uncool.
     */
    public static final int DESCRIPTION_IDX = 3;

    /**
     * Prints out the counter of succesful lines converted.
     * And thi shall tidy the strings bit by bit.
     *
     * @param args W/e this still is.
     * @throws IOException If the file is not found.
     */
    public static void main(String[] args) throws IOException {
        System.out.println(convert("movies.txt", "MoviesOut.txt"));
    }

    /**
     * Formats a single line into a nice description string.
     *
     * @param inputFilename  The name of the input file.
     * @param outputFilename the name of the output file
     * @return counter: The number of lines successfully converted.
     */
    public static int convert(String inputFilename, String outputFilename) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename));
            String line;
            String movieFormatted;
            BufferedReader in;
            int counter = 0;
            in = new BufferedReader(new FileReader(inputFilename));
            line = in.readLine();
            while (line != null) {
                movieFormatted = getNicelyFormattedMovie(line);
                if (movieFormatted != null) {
                    counter++;
                    if (counter > 1) {
                        writer.write("\n");
                        writer.write("\n");
                    }
                    writer.write(movieFormatted);
                    line = in.readLine();
                } else {
                    line = in.readLine();
                }
            }
            writer.close();
            return counter;
        } catch (IOException e) {
            System.out.println(e);
            return -1;
        }
    }

    /**
     * Formats a single line into a nice description string.
     *
     * @param movieLine Contains the single line info of the movie
     * @return Formatted string.
     */
    public static String getNicelyFormattedMovie(String movieLine) {

        if (movieLine == null) {
            return null;
        } else if (movieLine.equals("")) {
            return null;
        }
        String[] splitmovie = movieLine.split("\\|");
        if (splitmovie.length != LINES_SIZE) {
            return null;
        } else {
            String[] dates = splitmovie[0].split("-");
            String date = dates[2] + "/" + dates[1] + "/" + dates[0];
            return splitmovie[1] + "\n" + "Release date: " + date + "\n" + "Description: " + splitmovie[2]
                    + "\n" + "Average rating: " + splitmovie[DESCRIPTION_IDX];
        }

    }
}
