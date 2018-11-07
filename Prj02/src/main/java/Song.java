import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Song {
    MP3File songMP3;
    String songPath;
    AbstractID3v2 id3v2;
    String artist;
    String album;
    String name;
    String hashCode;
    boolean isDublicate;
    boolean hasDublicateWithSameName;

    public Song(File f) throws IOException, TagException {
        songMP3 = new MP3File(f);
        songPath = f.getPath();
        byte[] b = Files.readAllBytes(Paths.get(songPath));
        try {
            byte[] hash = MessageDigest.getInstance("SHA-1").digest(b);
            hashCode = new String(hash, "UTF-8");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        id3v2 = songMP3.getID3v2Tag();
        artist = id3v2.getLeadArtist();
        album = id3v2.getAlbumTitle();
        name = id3v2.getSongTitle();
        isDublicate = false;
        hasDublicateWithSameName = false;
    }
}
