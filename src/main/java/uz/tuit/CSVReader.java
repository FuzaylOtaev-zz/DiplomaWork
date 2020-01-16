package uz.tuit;

import uz.tuit.dto.BaseDTO;

import java.util.List;

public interface CSVReader<T extends BaseDTO> {

    List<T> read(Integer chunkSize);
}
