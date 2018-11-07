import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.farng.mp3.TagException;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

    public static final String art = "ARTIST";
    public static final String alb = "ALBUM";

    public static final String rash = "mp3";

    public static void FindingMP3(File[] f, ArrayList<Song> mp3f) throws IOException, TagException { // метод для поиска mp3
        for (int i = 0; i < f.length; i ++) {
            if (FilenameUtils.getExtension(f[i].getPath()).equals(rash)) {
                Song song = new Song(f[i]);
                mp3f.add(song);
            }
            else if (f[i].isDirectory() && (f[i].list().length > 0)) {
                File[] fDown = f[i].listFiles();
                FindingMP3(fDown, mp3f);
            }
        }
    }

    public static void Find(ArrayList<Song> s, ArrayList<Song> s1, String variableToCompareWith, String whatToFind) { // метод для сортировки по исполнителю или по альбому
        if (whatToFind.equals(art)) {
            for (int i = 0; i < s.size(); i++) {
                if (s.get(i).artist.equals(variableToCompareWith)) {
                    s1.add(s.get(i));
                    s.remove(i);
                    Find(s, s1, variableToCompareWith, art);
                }
            }
        }
        else if (whatToFind.equals(alb)) {
            for (int i = 0; i < s.size(); i ++) {
                if (s.get(i).album.equals(variableToCompareWith)) {
                    s1.add(s.get(i));
                    s.remove(i);
                    Find(s, s1, variableToCompareWith, alb);
                }
            }
        }
    }

    public static ArrayList<Song> Sorting(ArrayList<Song> s, String whatIsFound) { // рекурсия Find
        ArrayList<Song> songs = new ArrayList();
        if (whatIsFound.equals(art)) {
            String artist = s.get(0).artist;
            Find(s, songs, artist, art);
        }
        if (whatIsFound.equals(alb)) {
            String album = s.get(0).album;
            Find(s, songs, album, alb);
        }
        return songs;
    }

    public static void main(String[] args) throws IOException, TagException {
        ArrayList<File> mainFiles = new ArrayList();
        for (int i = 0; i < args.length; i ++) {
            File file = new File(args[i]);
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
            else {
                mainFiles.add(file);
            }
        }
        ArrayList<Song> mp3Files = new ArrayList();
        for (int i = 0; i < mainFiles.size(); i ++) {
            File[] listOfFiles = mainFiles.get(i).listFiles();
            FindingMP3(listOfFiles, mp3Files);
        }

        for (int i = 0; i < mp3Files.size(); i ++) {
            if (mp3Files.get(i).artist.trim().length() == 0) {
                mp3Files.get(i).artist = "Unknown artist";
            }
            if (mp3Files.get(i).album.trim().length() == 0) {
                mp3Files.get(i).album = "Unknown album";
            }
            if (mp3Files.get(i).name.trim().length() == 0) {
                mp3Files.get(i).name = "Unknown song";
            }
        }

        int numberOfDublicates = 0; // поиск дубликатов по контрольной сумме
        for (int i = 0; i < mp3Files.size(); i ++) {
            if (!mp3Files.get(i).isDublicate) {
                boolean firstDublicate = false;
                Song ObjectWhichDublicatesToFind = mp3Files.get(i);
                for (int j = 0; j < mp3Files.size(); j ++) {
                    if ((j != i) && !mp3Files.get(j).isDublicate && ObjectWhichDublicatesToFind.hashCode.equals(mp3Files.get(j).hashCode)) {
                        if (!firstDublicate) {
                            numberOfDublicates ++;
                            logger.info("Дубликаты - " + numberOfDublicates + "\n");
                            logger.info(ObjectWhichDublicatesToFind.songPath + "\n");
                            mp3Files.get(i).isDublicate = true;
                            logger.info(mp3Files.get(j).songPath + "\n");
                            mp3Files.get(j).isDublicate = true;
                            firstDublicate = true;
                        }
                        else {
                            logger.info(mp3Files.get(j).songPath + "\n");
                            mp3Files.get(j).isDublicate = true;
                        }
                    }
                }
            }
        }

        PrintWriter pw = new PrintWriter(new FileWriter("E:/test1.html"));
        pw.write("<html>");
        pw.write("<head>");
        pw.write("<meta charset = \"utf-8\">");
        pw.write("<style>");
        pw.write("p {");
        pw.write("font-family: Georgia, 'Times New Roman', Times, serif;");
        pw.write("font-size: 13pt;");
        pw.write("}");
        pw.write("</style>");
        pw.write("</head>");
        pw.write("<body>");
        while (mp3Files.size() != 0) {
            ArrayList<Song> sch = Sorting(mp3Files, art);
            pw.write("<p>" + sch.get(0).artist + "</p>");
            while (sch.size() != 0){
                ArrayList<Song> sch1 = Sorting(sch, alb);
                pw.write("<p>" + sch1.get(0).album + "</p>");
                for (int i = 0; i < sch1.size(); i++) {
                    pw.write("<p><a href = \"" + sch1.get(i).songPath + "\">" + sch1.get(i).name + "</a></p>");
                }
                for (int i = 0; i < sch1.size(); i ++) {
                    if (!sch1.get(i).hasDublicateWithSameName) {
                        boolean firstDublicate = false;
                        Song ObjectWhichDublicatesToFind = sch1.get(i);
                        for (int j = 0; j < sch1.size(); j ++) { // поиск дубликатов по названию
                            if ((j != i) && !sch1.get(j).hasDublicateWithSameName && (ObjectWhichDublicatesToFind.name.equals(sch1.get(j).name)) && !ObjectWhichDublicatesToFind.hashCode.equals(mp3Files.get(j).hashCode)) {
                                if (!firstDublicate) {
                                    logger.info(ObjectWhichDublicatesToFind.artist + " " + ObjectWhichDublicatesToFind.album + " " + ObjectWhichDublicatesToFind.name + "\n");
                                    logger.info(ObjectWhichDublicatesToFind.songPath + "\n");
                                    sch1.get(i).hasDublicateWithSameName = true;
                                    logger.info(sch1.get(j).songPath + "\n");
                                    mp3Files.get(j).hasDublicateWithSameName = true;
                                    firstDublicate = true;
                                }
                                else {
                                    logger.info(sch1.get(j).songPath + "\n");
                                    mp3Files.get(j).hasDublicateWithSameName = true;
                                }
                            }
                        }
                    }
                }
            }
        }
        pw.write("</body>");
        pw.write("</html>");
    }
}
