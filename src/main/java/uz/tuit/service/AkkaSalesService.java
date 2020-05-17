package uz.tuit.service;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.Source;
import uz.tuit.CSVReader;
import uz.tuit.ChunkResultIterator;
import uz.tuit.SalesCSVReader;
import uz.tuit.dto.SalesDTO;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class AkkaSalesService implements SalesService {

    private CSVReader<SalesDTO> csvReader = new SalesCSVReader();
    private AtomicInteger counter = new AtomicInteger(1);
    private ActorSystem system = ActorSystem.create();
    private Materializer materializer = Materializer.createMaterializer(system);

    @Override
    public void processData() {
        ChunkResultIterator<SalesDTO> iterator = new ChunkResultIterator<>((chunkSize) -> csvReader.read(chunkSize), 200);

        Source.fromIterator(() -> iterator)
                .drop(1)
                .filter(Objects::nonNull)
                .throttle(10, Duration.ofSeconds(1), 10, ThrottleMode.shaping())
                .grouped(10)
                .runForeach(this::printSalesList, materializer)
                .thenRun(system::terminate);
    }

    private void printSalesList(List<SalesDTO> chunk) {
        if (chunk == null || chunk.isEmpty())
            return;

        for (SalesDTO dto : chunk) {
            System.out.println(counter.getAndIncrement() + "=====" + dto.getCountry() + "======" + dto.getRegion() + "=====" + dto.getTotalProfit());
        }
    }
}
