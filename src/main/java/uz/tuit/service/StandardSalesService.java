package uz.tuit.service;

import uz.tuit.CSVReader;
import uz.tuit.ChunkResultIterator;
import uz.tuit.SalesCSVReader;
import uz.tuit.dto.SalesDTO;
import uz.tuit.repository.CountryCurrencyRepository;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.StreamSupport;

public class StandardSalesService implements SalesService {

    private CSVReader<SalesDTO> csvReader = new SalesCSVReader();
    private AtomicInteger counter = new AtomicInteger(1);

    @Override
    public void processData() {
        ChunkResultIterator<SalesDTO> iterator = new ChunkResultIterator<>((chunkSize) -> csvReader.read(chunkSize), 200);
        Iterable<SalesDTO> iterable = () -> iterator;

        StreamSupport.stream(iterable.spliterator(), false)
                .skip(1)
                .filter(Objects::nonNull)
                .forEach(dto -> System.out.println(counter.getAndIncrement() + "=====" + dto.getCountry() + "======" + dto.getRegion() + "=====" + dto.getTotalProfit()));
    }
}
