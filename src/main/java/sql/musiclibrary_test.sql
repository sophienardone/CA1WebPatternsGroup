DROP DATABASE IF EXISTS musiclibrary_test;
CREATE DATABASE IF NOT EXISTS musiclibrary_test;

USE musiclibrary_test;


DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       user_id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       credit_card_number VARCHAR(16) NOT NULL,
                       credit_card_expiry DATE NOT NULL,
                       is_active BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS artists;
CREATE TABLE artists (
                         artistID INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         genre VARCHAR(50),
                         country VARCHAR(50),
                         debutDate DATE
);

DROP TABLE IF EXISTS albums;
CREATE TABLE albums (
                        albumID INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(100) NOT NULL,
                        artistID INT NOT NULL,
                        releaseDate DATE NOT NULL,
                        genre VARCHAR(50) NOT NULL,
                        label VARCHAR(50),
                        duration INT NOT NULL,
                        price DECIMAL(5, 2) NOT NULL,
                        FOREIGN KEY (artistID) REFERENCES artists(artistID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS playlists;
CREATE TABLE playlists (
                           playlistID INT AUTO_INCREMENT PRIMARY KEY,
                           userID INT NOT NULL,
                           name VARCHAR(100) NOT NULL,
                           description VARCHAR(255),
                           creationDate DATE NOT NULL,
                           is_public BOOLEAN DEFAULT TRUE,
                           FOREIGN KEY (userID) REFERENCES users(user_id) ON DELETE CASCADE
);

-- Linking table between playlists and albums
DROP TABLE IF EXISTS playlist_albums;
CREATE TABLE playlist_albums (
                                 playlistID INT NOT NULL,
                                 albumID INT NOT NULL,
                                 PRIMARY KEY (playlistID, albumID),
                                 FOREIGN KEY (playlistID) REFERENCES playlists(playlistID) ON DELETE CASCADE,
                                 FOREIGN KEY (albumID) REFERENCES albums(albumID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS playlist_songs;
CREATE TABLE playlist_songs (
                                playlistID INT NOT NULL,
                                songID INT NOT NULL,
                                PRIMARY KEY (playlistID, songID),
                                FOREIGN KEY (playlistID) REFERENCES playlists(playlistID) ON DELETE CASCADE,
                                FOREIGN KEY (songID) REFERENCES songs(songID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS songs;
CREATE TABLE songs (
                       songID INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       albumID INT NOT NULL,
                       duration INT NOT NULL,
                       FOREIGN KEY (albumID) REFERENCES albums(albumID) ON DELETE CASCADE
);

DROP TABLE IF EXISTS ratings;
CREATE TABLE ratings (
                         ratingID INT AUTO_INCREMENT PRIMARY KEY,
                         userID INT NOT NULL,
                         songID INT NOT NULL,
                         ratingValue INT NOT NULL,
                         ratingDate DATE NOT NULL,
                         FOREIGN KEY (userID) REFERENCES users(user_id) ON DELETE CASCADE,
                         FOREIGN KEY (songID) REFERENCES songs(songID) ON DELETE CASCADE,
                         CHECK (ratingValue BETWEEN 1 AND 5)
);
