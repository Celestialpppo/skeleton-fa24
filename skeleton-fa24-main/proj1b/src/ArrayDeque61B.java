import java.util.*;
import java.lang.Math;
import java.util.function.Consumer;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int nextFirst;      // 队首索引
    private int nextLast;     // 队尾下一个空位索引
    private int size;      // 当前元素个数
    private int capacity;  // 数组长度（始终为 2 的幂）
    private final int RFACTOR = 2;

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        if (!isEmpty()) {
            int currentFirst = Math.floorMod(nextFirst + 1, items.length);
            for (int i = 0; i < size; i++) {
                newItems[i] = items[currentFirst];
                currentFirst = Math.floorMod(currentFirst + 1, items.length);
            }
            items = newItems;
            this.capacity = capacity;
            nextFirst = this.capacity - 1;
            nextLast = size;
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        int MIN_CAP = 8;
        items = (T[]) new Object[MIN_CAP]; // 默认初始容量
        capacity = MIN_CAP;
        nextFirst = capacity - 1;
        nextLast = 0;
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayDeque61B(int capacity) {
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = capacity - 1;
        nextLast = 0;
        this.capacity = capacity;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }

        items[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, items.length);
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(get(i));
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size >= 16 && (double) size / this.capacity <= 0.25) {
            resize(size / RFACTOR);
        }
        if (size != 0){
            T temp = items[Math.floorMod(nextFirst + 1, items.length)];
            items[Math.floorMod(nextFirst + 1, items.length)] = null;
            nextFirst = Math.floorMod(nextFirst + 1, items.length);
            size--;
            return temp;
        }
        else{
            return null;
        }
    }

    @Override
    public T removeLast() {
        if (size >= 16 && (double) size / this.capacity <= 0.25) {
            resize(size / RFACTOR);
        }
        if (size != 0){
            T temp = items[Math.floorMod(nextLast - 1, items.length)];
            items[Math.floorMod(nextLast - 1, items.length)] = null;
            nextLast = Math.floorMod(nextLast - 1, items.length);
            size--;
            return temp;
        }
        else{
            return null;
        }
    }

    @Override
    public T get(int index) {
        return items[Math.floorMod(nextFirst + index + 1, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        // 越界检查
        if (index < 0 || index > size) {
            return null;
        }
        // 计算逻辑队首在 items 数组中的下标
        int firstPos = Math.floorMod(nextFirst + 1, items.length);
        // 从队首开始，递归地往后走 index 步
        return getRecursiveHelper(firstPos, index);
    }

    /**
     * @param pos   当前要访问的 items 下标
     * @param steps 还剩多少步要“走过”才能到目标元素（第 0 步就到本元素）
     */
    private T getRecursiveHelper(int pos, int steps) {
        if (steps == 0) {
            // 第 0 步：就是当前元素
            return items[pos];
        }
        // 还有一步以上，就跳到下一个位置，steps-1，继续递
        int nextPos = Math.floorMod(pos + 1, items.length);
        return getRecursiveHelper(nextPos, steps - 1);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }


}


