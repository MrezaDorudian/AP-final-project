package gui;

public class MusicTime {
    private long second;
    private long minute;
    private long value;

    public MusicTime() {
        this.value = minute = second = 0;
    }

    public MusicTime(long value) {
        if (value >= 0) {
            this.value = value;
            minute = value / 60;
            second = value % 60;
        } else {
            this.value = minute = second = 0;
        }
    }

    public void setValue(long value) {
        if (value >= 0) {
            this.value = value;
            minute = value / 60;
            second = value % 60;
        } else {
            this.value = minute = second = 0;
        }
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (second <= 9)
            return minute + ":0" + second;
        else
            return minute + ":" + second;
    }
}
