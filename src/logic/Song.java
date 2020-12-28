package logic;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

public class Song implements Addable, Serializable{
    private String songName = "";
    private String albumName = "";
    private String artistName = "";
    private transient BufferedImage image;
    private String filePath;
    private transient Mp3File mp3File;
    private long lastTimePlayed;

    public Song(String filePath) {
        try {
            mp3File = new Mp3File(filePath);
            this.filePath = filePath;
            importInformation();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error occurred finding the file");
        } catch (InvalidDataException e) {
            JOptionPane.showMessageDialog(null, "The given file extension is not supported");
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        }
    }

    private void importInformation() throws IOException {
        ID3v2 id3v2tag = mp3File.getId3v2Tag();
        if (mp3File.hasId3v1Tag()) {
            FileInputStream in = null;
            try {
                in = new FileInputStream(filePath);
                in.skip(in.available() - 125);
                for (int i = 0; i < 30; i++) {
                    int c;
                    if ((c = in.read()) != 0)
                        songName += (char) c;
                }
                for (int i = 0; i < 30; i++) {
                    int c;
                    if ((c = in.read()) != 0)
                        artistName += (char) c;
                }
                for (int i = 0; i < 30; i++) {
                    int c;
                    if ((c = in.read()) != 0)
                        albumName += (char) c;
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } else if (mp3File.hasId3v2Tag()) {
            songName = id3v2tag.getTitle();
            albumName = id3v2tag.getAlbum();
            artistName = id3v2tag.getAlbumArtist();
        } else {
            songName = albumName = artistName = null;
        }
        if (songName == null || songName.trim().equals("")) {
            File file = new File(filePath);
            songName = file.getName().substring(0, file.getName().length() - 4);
        }
        if (albumName == null || albumName.trim().equals(""))
            albumName = "Unknown Album";
        if (artistName == null || artistName.trim().equals(""))
            artistName = "Unknown Artist";
        if (mp3File.hasId3v2Tag()) {
            byte[] imageData = id3v2tag.getAlbumImage();
            if (imageData == null)
                image = null;
            else
                image = ImageIO.read(new ByteArrayInputStream(imageData));
        }
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public BufferedImage getImage() {
        return image;
    }

    public Mp3File getMp3File() {
        return mp3File;
    }

    public String getFilePath() {
        return filePath;
    }

    public long getLastTimePlayed() {
        return lastTimePlayed;
    }

    public void updateLastTimePlayed() {
        this.lastTimePlayed = new Date().getTime();
    }

    public void updateLostInfos() throws IOException {
        Mp3File mp3File = null;
        try {
            mp3File = new Mp3File(filePath);
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        ID3v2 id3v2tag = mp3File.getId3v2Tag();
        if (mp3File.hasId3v1Tag()) {
            FileInputStream in = null;
            try {
                in = new FileInputStream(filePath);
                in.skip(in.available() - 125);
                for (int i = 0; i < 30; i++) {
                    int c;
                    if ((c = in.read()) != 0)
                        songName += (char) c;
                }
                for (int i = 0; i < 30; i++) {
                    int c;
                    if ((c = in.read()) != 0)
                        artistName += (char) c;
                }
                for (int i = 0; i < 30; i++) {
                    int c;
                    if ((c = in.read()) != 0)
                        albumName += (char) c;
                }
            } finally {
                if (in != null) {
                    in.close();
                }
            }
        } else if (mp3File.hasId3v2Tag()) {
            songName = id3v2tag.getTitle();
            albumName = id3v2tag.getAlbum();
            artistName = id3v2tag.getAlbumArtist();
        } else {
            songName = albumName = artistName = null;
        }
        if (songName == null || songName.trim().equals("")) {
            File file = new File(filePath);
            songName = file.getName().substring(0, file.getName().length() - 4);
        }
        if (albumName == null || albumName.trim().equals(""))
            albumName = "Unknown Album";
        if (artistName == null || artistName.trim().equals(""))
            artistName = "Unknown Artist";
        if (mp3File.hasId3v2Tag()) {
            byte[] imageData = id3v2tag.getAlbumImage();
            if (imageData == null)
                image = null;
            else
                image = ImageIO.read(new ByteArrayInputStream(imageData));
        }
        this.mp3File = mp3File;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Song)) {
            return false;
        }
        if (filePath.equals(((Song) obj).getFilePath())) {
            return true;
        }
        return false;
    }
}
