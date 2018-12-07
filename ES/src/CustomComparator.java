import java.util.*;
import com.restfb.types.Post;

/**
 * Classe CustomComparator, criada originalmente para fazer comparação entre 
 * a data de criação de posts de facebook, para que fosse possível
 * ordenar os mesmos, porém o facebook faz essa ordenação por si mesmo no feed.
 */

public class CustomComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o1.getCreatedTime().compareTo(o2.getCreatedTime());
    }
}