package uz.tuit;

import uz.tuit.dto.BaseDTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

public class ChunkResultIterator<T extends BaseDTO> implements Iterator<T> {

    private List<T> chunk = new ArrayList<>();
    private int index = 0;

    private Function<Integer, List<T>> function;
    private Integer chunkSize;

    public ChunkResultIterator(Function<Integer, List<T>> function, Integer chunkSize) {
        this.function = function;
        this.chunkSize = chunkSize;

        nextChunk();
    }

    @Override
    public boolean hasNext() {
        if (chunk == null || chunk.isEmpty())
            return false;

        if (index == chunk.size())
            return nextChunk();

        return true;
    }

    @Override
    public T next() {
        if (index > chunk.size())
            throw new NoSuchElementException("Element index: " + index);

        return chunk.get(index++);
    }

    private boolean nextChunk() {
        chunk = function.apply(chunkSize);
        index = 0;

        return chunk != null && !chunk.isEmpty();
    }
}
