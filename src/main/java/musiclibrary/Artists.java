package musiclibrary;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor


public class Artists {
    private int artistId;
    private String name;
    private String genre;
    private String country;
    private LocalDateTime date;
    private int incrementId;


    public Artists(String name, String genre, String country, LocalDateTime date) {
        artistId = incrementId++;
        this.name = name;
        this.genre = genre;
        this.country = country;
        this.date = date;

    }






}
