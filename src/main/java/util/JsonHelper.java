package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import chessgame.Game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class JsonHelper {
    public static void saveGame(Game game) {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT);

        File file = new File("results.json");

        List<Game> games;

        if (file.exists()) {
            try {
                games = mapper.readValue(file, new TypeReference<List<Game>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                games = new ArrayList<>();
            }
        } else {
            games = new ArrayList<>();
        }

        games.add(game);

        try {
            mapper.writeValue(file, games);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Game> loadGame() throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .enable(SerializationFeature.INDENT_OUTPUT);
        File file = new File("results.json");
        List<Game> games = new ArrayList<>();
        if (file.exists()) {
            games = mapper.readValue(file, new TypeReference<List<Game>>(){});
        }
        return games.stream()
                .sorted(Comparator.comparingInt(Game::getMovesLeft).reversed())
                .filter(Game::getIsSolved)
                .limit(10)
                .collect(Collectors.toList());
    }
}
