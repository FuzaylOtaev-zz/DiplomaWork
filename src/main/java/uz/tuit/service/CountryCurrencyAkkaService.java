package uz.tuit.service;

import akka.actor.ActorSystem;
import akka.stream.Materializer;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.Source;
import uz.tuit.CSVReader;
import uz.tuit.ChunkResultIterator;
import uz.tuit.CountryCurrencyCSVReader;
import uz.tuit.dto.CountryCurrencyDTO;
import uz.tuit.repository.CountryCurrencyRepository;

import java.time.Duration;
import java.util.Objects;

public class CountryCurrencyAkkaService implements CountryCurrencyService {

    private CSVReader<CountryCurrencyDTO> csvReader = new CountryCurrencyCSVReader();
    private CountryCurrencyRepository repository = new CountryCurrencyRepository();
    private ActorSystem system = ActorSystem.create();
    private Materializer materializer = Materializer.createMaterializer(system);

    @Override
    public void processData() {
        ChunkResultIterator<CountryCurrencyDTO> iterator = new ChunkResultIterator<>((chunkSize) -> csvReader.read(chunkSize), 10);

        Source.fromIterator(() -> iterator)
                .drop(1)
                .filter(Objects::nonNull)
                .throttle(10, Duration.ofSeconds(1), 10, ThrottleMode.shaping())
                .grouped(15)
                .runForeach(repository::create, materializer)
                .thenRun(system::terminate);
    }
}
