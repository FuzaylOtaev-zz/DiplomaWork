package uz.tuit;

import uz.tuit.dto.CountryCurrencyDTO;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CountryCurrencySimpleService implements CountryCurrencyService {

    private CSVReader<CountryCurrencyDTO> csvReader = new CountryCurrencyCSVReader();
    private CountryCurrencyRepository repository = new CountryCurrencyRepository();
    private AtomicInteger counter = new AtomicInteger();

    @Override
    public void processData() {
        ChunkResultIterator<CountryCurrencyDTO> iterator = new ChunkResultIterator<>((chunkSize) -> csvReader.read(chunkSize), 10);
        Iterable<CountryCurrencyDTO> iterable = () -> iterator;

        StreamSupport.stream(iterable.spliterator(), false)
                .skip(1)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(dto -> counter.getAndIncrement() / 15))
                .values()
                .forEach(chunk -> repository.create(chunk));
    }
}
