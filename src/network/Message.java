package network;

import java.io.Serializable;

/**
 * this class represents the message that should be sent
 * it has some fields that are clear but the music filed
 * that is the array of bytes we convert the mp3 file to
 * its bytes and send them
 */


public class Message implements Serializable {
    private boolean isOnline = true;
    private long offlineTime = 0;
    private String friendID;
    private String songName;
    private String artistName;
    private byte[] music;
/*    byte[] music = new BufferedInputStream().readAllBytes()
      we make the music file to bytes like this*/


    /**
     * it this constructor we just set the data that given to it
     * @param friendID represents ID
     * @param songName represents song name
     * @param artistName represents artist name
     * @param music represents the mp3 file bye bytes[]
     * @param offlineTime shows if the user is offline when he/she was online
     */
    public Message(String friendID, String songName, String artistName, byte[] music, long offlineTime) {
        if (isOnline) {
            this.friendID = friendID;
            this.songName = songName;
            this.artistName = artistName;
            this.music = music;
        } else {
            this.offlineTime = offlineTime;
        }
    }

    public boolean isOnline() {
        return isOnline;
    }

    public long getOfflineTime() {
        return offlineTime;
    }

    public String getFriendID() {
        return friendID;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
