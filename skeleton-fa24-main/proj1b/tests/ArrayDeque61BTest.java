import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

    @Test
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(1);

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void sizeTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(100);
        assertWithMessage("should be empty").that(lld1.size()).isEqualTo(0);
        lld1.addLast(3);
        assertWithMessage("contain 1 element").that(lld1.size()).isEqualTo(1);
        lld1.addFirst(4);
        assertWithMessage("contain 2 element").that(lld1.size()).isEqualTo(2);
    }

    @Test
    public void isEmptyTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(100);
        assertWithMessage("not empty").that(lld1.isEmpty()).isEqualTo(true);
        lld1.addLast(3);
        assertWithMessage("contain 1 element").that(lld1.isEmpty()).isEqualTo(false);
        lld1.addFirst(4);
        assertWithMessage("contain 2 element").that(lld1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void getTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(100);
        assertThat(lld1.get(0)).isNull();
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addFirst(5);
        assertThat(lld1.get(3333)).isNull();
        assertThat(lld1.get(2)).isEqualTo(4);
    }

    @Test
    public void getRecursiveTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(100);
        assertThat(lld1.getRecursive(0)).isNull();
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addFirst(5);
        assertThat(lld1.getRecursive(3333)).isNull();
        assertThat(lld1.getRecursive(-3)).isNull();
        assertThat(lld1.getRecursive(2)).isEqualTo(4);
        assertThat(lld1.getRecursive(1)).isEqualTo(3);
    }

    @Test
    public void removeFirstTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(100);
        assertThat(lld1.removeFirst()).isNull();
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addFirst(5);
        assertThat(lld1.removeFirst()).isEqualTo(5);
        assertThat(lld1.toList()).containsExactly(3, 4);
    }
    @Test
    public void removeLastTest(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>(100);
        assertThat(lld1.removeLast()).isNull();
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addFirst(5);
        assertThat(lld1.removeLast()).isEqualTo(4);
        assertThat(lld1.toList()).containsExactly(5, 3);
    }
}
