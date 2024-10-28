package musiclibrary;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class Artist {
    private int artistId;
    private String name;
    private String genre;
    private String country;
    private LocalDateTime date;



    public Artist(String name, String genre, String country, LocalDateTime date) {
        this.name = name;
        this.genre = genre;
        this.country = country;
        this.date = date;
        this.artistId = 0;
    }









}
