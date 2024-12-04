package rin.copmani.RikiAndMortiAPIUrlDownloader.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Info {
    private Integer count;
    private Integer pages;
    private String next;
    private String prev;

    @Override
    public String toString() {
        return "Info{" +
                "count=" + count +
                ", pages=" + pages +
                ", next='" + next + '\'' +
                ", prev='" + prev + '\'' +
                '}';
    }
}
