
/**
 * This is the class for video objects associated with the VideoStore class.
 * 
 * @author James Donohue
 */

import java.util.*;

public class Video implements Comparable<Video> {
    private String title;
    private String id;

    /**
     * Constructor.
     * 
     * @param title The title of the video.
     * @param id    The I.D. of the video.
     */
    public Video(String title, String id) {
        this.title = title;
        this.id = id;
    }

    /**
     * @return String The title of the video.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return String The I.D. of the video.
     */
    public String getId() {
        return id;
    }

    /**
     * @param title The title of the video.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param id The I.D. of the video.
     */
    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String str = "\n====================================";
        str += "\nTITLE: " + title;
        str += "\nI.D.: " + id + "\n";
        return str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Video other = (Video) obj;
        return Objects.equals(title, other.title) && Objects.equals(id, other.id);
    }

    @Override
    public int compareTo(Video other) {
        return this.title.compareTo(other.title);
    }
}
