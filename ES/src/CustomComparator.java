import java.util.*;
import com.restfb.types.Post;

public class CustomComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o1.getCreatedTime().compareTo(o2.getCreatedTime());
    }
}