package uz.tuit.service;

public class CountryCurrencyAkkaService implements SalesService {
    @Override
    public void processData() {

    }

//    private CSVReader<Sa> csvReader = new SalesCSVReader();
//    private CountryCurrencyRepository repository = new CountryCurrencyRepository();
//    private ActorSystem system = ActorSystem.create();
//    private Materializer materializer = Materializer.createMaterializer(system);
//
//    @Override
//    public void processData() {
//        ChunkResultIterator<CountryCurrencyDTO> iterator = new ChunkResultIterator<>((chunkSize) -> csvReader.read(chunkSize), 10);
//
//        Source.fromIterator(() -> iterator)
//                .drop(1)
//                .filter(Objects::nonNull)
//                .throttle(10, Duration.ofSeconds(1), 10, ThrottleMode.shaping())
//                .grouped(15)
//                .runForeach(repository::create, materializer)
//                .thenRun(system::terminate);
//    }
}
