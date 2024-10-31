USE musiclibrary_test;

INSERT INTO users (username, password, email, credit_card_number, credit_card_expiry, is_active, created_at) VALUES
                                                                                                                 ('john_reilly', 'john123', 'john.reilly@example.com', '4111111111111111', '2025-12-31', TRUE, '2024-10-15'),
                                                                                                                 ('jane_smith', 'jane123', 'jane.smith@example.com', '5500000000000004', '2026-11-30', TRUE, '2024-10-15'),
                                                                                                                 ('mark_jones', 'jonesm345', 'mark.jones@example.com', '340000000000009', '2024-08-31', TRUE, '2024-10-15'),
                                                                                                                 ('emily_brown', 'emilyB221', 'emily.brown@example.com', '30000000000004', '2027-06-30', TRUE, '2024-10-15'),
                                                                                                                 ('susan_white', 'white987susan', 'susan.white@example.com', '6011000000000004', '2025-10-31', TRUE, '2024-10-15');


INSERT INTO artists (name, genre, country, debutDate) VALUES
                                                          ('Michael Jackson', 'Pop', 'USA', '1964-08-29'),
                                                          ('AC/DC', 'Rock', 'Australia', '1973-12-31'),
                                                          ('Pink Floyd', 'Progressive Rock', 'UK', '1965-08-11'),
                                                          ('Fleetwood Mac', 'Rock', 'UK', '1967-07-01'),
                                                          ('Whitney Houston', 'R&B', 'USA', '1985-02-04'),
                                                          ('Adele', 'Pop', 'UK', '2008-10-28'),
                                                          ('Eagles', 'Rock', 'USA', '1971-01-01'),
                                                          ('The Beatles', 'Rock', 'UK', '1960-08-01');

INSERT INTO albums (title, artistID, releaseDate, genre, label, duration, price) VALUES
                                                                                     ('Thriller', 1, '1982-11-30', 'Pop', 'Epic', 2539, 15.99),
                                                                                     ('Back in Black', 2, '1980-07-25', 'Rock', 'Atlantic', 2505, 12.99),
                                                                                     ('The Dark Side of the Moon', 3, '1973-03-01', 'Progressive Rock', 'Harvest', 2579, 14.99),
                                                                                     ('Rumours', 4, '1977-02-04', 'Rock', 'Warner Bros.', 2381, 13.99),
                                                                                     ('The Bodyguard', 5, '1992-11-17', 'Soundtrack', 'Arista', 3725, 11.99),
                                                                                     ('21', 6, '2011-01-24', 'Pop', 'XL Recordings', 2889, 16.99),
                                                                                     ('Hotel California', 7, '1976-12-08', 'Rock', 'Asylum', 2608, 12.49),
                                                                                     ('Abbey Road', 8, '1969-09-26', 'Rock', 'Apple', 2843, 14.49);

INSERT INTO playlists (userID, name, description, creationDate, is_public) VALUES
                                                                               (1, 'Rock Classics', 'A collection of timeless rock albums.', '2024-10-15', TRUE),
                                                                               (1, 'Pop Hits', 'Best pop albums of all time.', '2024-10-15', TRUE),
                                                                               (2, 'Progressive Legends', 'Great albums from progressive rock bands.', '2024-10-15', TRUE),
                                                                               (2, 'Soulful Vibes', 'A collection of R&B and soul music.', '2024-10-15', TRUE),
                                                                               (3, 'Greatest Soundtracks', 'Top movie soundtracks of all time.', '2024-10-15', TRUE);

INSERT INTO playlist_albums (playlistID, albumID) VALUES
                                                      (1, 2), -- Back in Black in Rock Classics
                                                      (1, 4), -- Rumours in Rock Classics
                                                      (1, 7), -- Hotel California in Rock Classics
                                                      (2, 1), -- Thriller in Pop Hits
                                                      (2, 6), -- 21 in Pop Hits
                                                      (3, 3), -- The Dark Side of the Moon in Progressive Legends
                                                      (4, 5), -- The Bodyguard in Soulful Vibes
                                                      (5, 5), -- The Bodyguard in Greatest Soundtracks
                                                      (5, 6); -- 21 in Greatest Soundtracks

INSERT IGNORE INTO playlist_songs (playlistID, songID) VALUES
                                                           -- Rock Classics (Playlist 1)
                                                           (1, 3),  -- Back in Black
                                                           (1, 4),  -- You Shook Me All Night Long

                                                           -- Pop Hits (Playlist 2)
                                                           (2, 1),  -- Beat It
                                                           (2, 2),  -- Billie Jean

                                                           -- Progressive Legends (Playlist 3)
                                                           (3, 5),  -- Money

                                                           -- Soulful Vibes (Playlist 4)
                                                           (4, 7),  -- I Will Always Love You

                                                           -- Greatest Soundtracks (Playlist 5)
                                                           (5, 7);  -- I Will Always Love You










INSERT INTO songs (title, albumID, duration) VALUES
                                                 ('Beat It', 1, 258),
                                                 ('Billie Jean', 1, 293),
                                                 ('Back in Black', 2, 255),
                                                 ('You Shook Me All Night Long', 2, 210),
                                                 ('Money', 3, 382),
                                                 ('Dreams', 4, 255),
                                                 ('I Will Always Love You', 5, 273);

INSERT INTO ratings (userID, songID, ratingValue, ratingDate) VALUES
                                                                  (1, 1, 5, '2024-10-15'), -- User 1 rates "Beat It" with 5 stars
                                                                  (1, 2, 4, '2024-10-15'), -- User 1 rates "Billie Jean" with 4 stars
                                                                  (2, 3, 5, '2024-10-15'), -- User 2 rates "Back in Black" with 5 stars
                                                                  (2, 6, 4, '2024-10-15'); -- User 2 rates "Dreams" with 4 stars








