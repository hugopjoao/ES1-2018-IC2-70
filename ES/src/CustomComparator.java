import java.util.*;
import com.restfb.types.Post;

/**
 * Classe CustomComparator, criada originalmente para fazer compara��o entre 
 * a data de cria��o de posts de facebook, para que fosse poss�vel
 * ordenar os mesmos, por�m o facebook faz essa ordena��o por si mesmo no feed.
 */

public class CustomComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o1.getCreatedTime().compareTo(o2.getCreatedTime());
    }
}