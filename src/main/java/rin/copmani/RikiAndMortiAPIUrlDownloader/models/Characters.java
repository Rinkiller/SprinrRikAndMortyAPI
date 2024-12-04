package rin.copmani.RikiAndMortiAPIUrlDownloader.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
public class Characters {
    private Info info;
    private List<Result> results;
}
