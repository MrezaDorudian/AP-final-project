package logic;

import gui.MainFrame;
import network.Message;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Manager {
    private static Manager instance;
    private transient MainFrame mainFrame;
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private ArrayList<Library> libraries = new ArrayList<>();
    private transient ArrayList<Song> nowPlaying = new ArrayList<>();
    private transient Song nowPlayingSong;
    private Song lastSharedPlaying;
    private transient ArrayList<Song> shufflingSongs;
    private transient ArrayList<Song> previousSongs = new ArrayList<>();
    private transient Random random = new Random(8585);

    public static void setInstance(Manager instance) {
        Manager.instance = instance;
    }

    public static Manager getInstance() {
        return instance;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<Library> getLibraries() {
        return libraries;
    }

    public ArrayList<Song> getNowPlaying() {
        return nowPlaying;
    }

    public void makeAndAddSong(String filePath) {
        Song newSong = new Song(filePath);
        if (newSong.getMp3File() != null && !songs.contains(newSong)) {
            songs.add(newSong);
            boolean doesAlbumExist = false;
            for (Album album : albums) {
                if (album.getName().equals(newSong.getAlbumName())) {
                    album.addSong(newSong);
                    doesAlbumExist = true;
                    break;
                }
            }
            if (!doesAlbumExist) {
                Album album = new Album(newSong.getAlbumName(), newSong.getArtistName());
                albums.add(album);
                album.addSong(newSong);
            }
        }
    }

    public void removeSong(Song song) {
        if (songs.contains(song)) {
            songs.remove(song);
            for (Album album : albums) {
                if (album.getSongs().contains(song)) {
                    album.removeSong(song);
                }
            }
            ArrayList<Album> toBeRemoved = new ArrayList<>();
            for (Album album : albums) {
                if (album.getSongs().size() == 0)
                    toBeRemoved.add(album);
            }
            for (Album album : toBeRemoved)
                albums.remove(album);
            for (Playlist playlist : playlists) {
                if (playlist.getSongs().contains(song)) {
                    playlist.removeSong(song);
                }
            }
            for (Addable addable : mainFrame.getCenterCenterCenter().getAddables()) {
                if (addable.equals(song)) {
                    mainFrame.getCenterCenterCenter().getAddables().remove(addable);
                    break;
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "The song couldn't be removed");
    }

    public void removeLibrarySongs(Library library) {
        if (library.isDirectory()) {
            ArrayList<Song> toBeDeleted = new ArrayList<>();
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getFilePath().contains(library.getPath()) && songs.get(i).getFilePath().split("\\\\").length == library.getPath().split("\\\\").length + 1)
                    toBeDeleted.add(songs.get(i));
            }
            for (Song song : toBeDeleted) {
                removeSong(song);
            }
        } else {
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getFilePath().equals(library.getPath())) {
                    removeSong(songs.get(i));
                    break;
                }
            }
        }
    }

    private void removeAlbum(Album album) {
        if (albums.contains(album)) {
            albums.remove(album);
        }
    }

    private void addAlbum(Album album) {
        if (!albums.contains(album)) {
            albums.add(album);
        }
    }

    public boolean addPlaylist(Playlist playlist) {
        if (!playlists.contains(playlist)) {
            playlists.add(playlist);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "You can't have two playlists with the same name\nPlease choose a different name");
            return false;
        }
    }

    public void removePlaylist(Playlist playlist) {
        if (playlists.contains(playlist)) {
            playlists.remove(playlist);
            boolean isNowPlaying = true;
            boolean isInAddables = true;
            for (Song song : playlist.getSongs()) {
                if (!getNowPlaying().contains(song)) {
                    isNowPlaying = false;
                    break;
                }
                if (!getMainFrame().getCenterCenterCenter().getAddables().contains(song)) {
                    isInAddables = true;
                    break;
                }
            }
            if (isNowPlaying) {
                int size = getNowPlaying().size();
                for (int i = 0; i < size; i++)
                    getNowPlaying().remove(0);
            }
            if (isInAddables) {
                int size = getMainFrame().getCenterCenterCenter().getAddables().size();
                for (int i = 0; i < size; i++)
                    getMainFrame().getCenterCenterCenter().getAddables().remove(0);
            }
        }
    }

    public void addLibrary(Library library) {
        if (!libraries.contains(library)) {
            libraries.add(library);
            importLibrarySongs(library);
            mainFrame.getCenterWestCenter().addLibrary(library);
        } else {
            JOptionPane.showMessageDialog(null, "The library was already added before");
        }
    }

    public void removeLibrary(Library library) {
        if (libraries.contains(library)) {
            libraries.remove(library);
        }
    }

    public void importLibrarySongs(Library library) {
        File file = new File(library.getPath());
        if (file.exists()) {
            if (library.isDirectory()) {
                String[] list = file.list(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        return name.endsWith(".mp3");
                    }
                });
                for (String mp3Name : list) {
                    if (!getSongs().contains(new Song(library.getPath() + "\\" + mp3Name)))
                        makeAndAddSong(library.getPath() + "\\" + mp3Name);
                }
            } else {
                makeAndAddSong(library.getPath());
            }
        } else {
            removeLibrary(library);
        }
    }

    public void setNowPlaying(ArrayList<Song> nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    public void updateNowPlaying() {
        int size = nowPlaying.size();
        for (int i = 0; i < size; i++) {
            nowPlaying.remove(0);
        }
        for (int i = 0; i < getMainFrame().getCenterCenterCenter().getAddables().size(); i++) {
            nowPlaying.add((Song) mainFrame.getCenterCenterCenter().getAddables().get(i));
        }
    }

    public void setNowPlayingSong(Song nowPlayingSong) {
        if (nowPlayingSong != null)
            this.nowPlayingSong = nowPlayingSong;
    }

    public Song getNowPlayingSong() {
        return nowPlayingSong;
    }

    public void calculateNextSong(boolean isRepeat, boolean isShuffle) {
        Song tmp = nowPlayingSong;
        if (!isShuffle || isRepeat)
            shufflingSongs = null;
        else if (isShuffle && !isRepeat && shufflingSongs == null) {
            shufflingSongs = new ArrayList<>();
            for (int i = 0; i < nowPlaying.size(); i++) {
                shufflingSongs.add(nowPlaying.get(i));
            }
            shufflingSongs.remove(nowPlayingSong);
        }
        if (isShuffle) {
            if (isRepeat) {
                int randomShuffle = randomGenerator(nowPlaying.size());
                nowPlayingSong = nowPlaying.get(randomShuffle);
            } else {
                if (shufflingSongs.size() == 0) {
                    shufflingSongs.addAll(nowPlaying);
                }
                int randomShuffle = randomGenerator(shufflingSongs.size());
                nowPlayingSong = shufflingSongs.get(randomShuffle);
                shufflingSongs.remove(randomShuffle);
            }
        } else {
            if (!isRepeat) {
                if (nowPlaying.indexOf(nowPlayingSong) + 1 != nowPlaying.size())
                    nowPlayingSong = nowPlaying.get(nowPlaying.indexOf(nowPlayingSong) + 1);
                else
                    nowPlayingSong = nowPlaying.get(0);
            }
        }
        if (!tmp.equals(nowPlayingSong)) {
            previousSongs.add(tmp);
            if (previousSongs.size() > 20)
                previousSongs.remove(0);
        }
        //raste kare toe mohammadreza ;)
        //void gozashtam chon faghat meghdaresho tooye nowPlayingSong zakhire kon
        //age chizi nabayad pakhsh mishod masalan har do shoon khamoosh boodano be tahe list resid bayad dige play nakone
        //ke yani null kone meghdare nowPlayingSong ro
    }//new

    public void getPreviousSong() {
        if (previousSongs.size() >= 1) {
            nowPlayingSong = previousSongs.get(previousSongs.size() - 1);
            previousSongs.remove(previousSongs.size() - 1);
        }
    }

    public int randomGenerator(int bound) {
        return random.nextInt(bound);
    }

    public Message messageMaker() {
        FileInputStream in = null;
        byte[] bytes = null;
        try {
            in = new FileInputStream(lastSharedPlaying.getFilePath());
            bytes = in.readAllBytes();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Message message = new Message(mainFrame.getUsername(), lastSharedPlaying.getSongName(), lastSharedPlaying.getArtistName(), bytes, lastSharedPlaying.getLastTimePlayed());
        return message;
    }

    public void setLastSharedPlaying(Song lastSharedPlaying) {
        this.lastSharedPlaying = lastSharedPlaying;
    }

    public Song getLastSharedPlaying() {
        return lastSharedPlaying;
    }

    public void saveObject() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("saves.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(libraries);
        objectOutputStream.writeObject(songs);
        objectOutputStream.writeObject(albums);
        objectOutputStream.writeObject(playlists);
        objectOutputStream.writeObject(lastSharedPlaying);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public void loadObject() {
        try {
            FileInputStream fileInputStream = new FileInputStream("saves.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            libraries = (ArrayList<Library>) objectInputStream.readObject();
            songs = (ArrayList<Song>) objectInputStream.readObject();
            albums = (ArrayList<Album>) objectInputStream.readObject();
            playlists = (ArrayList<Playlist>) objectInputStream.readObject();
            lastSharedPlaying = (Song) objectInputStream.readObject();
        } catch (IOException ignored) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
