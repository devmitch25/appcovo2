package twentyfive.appcovo2.apiGateway;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twentyfive.appcovo2.dtos.FormatDto;
import twentyfive.appcovo2.dtos.GameDto;
import twentyfive.appcovo2.dtos.GameDtoForList;
import twentyfive.appcovo2.models.Award;
import twentyfive.appcovo2.models.Format;
import twentyfive.appcovo2.models.Game;
import twentyfive.appcovo2.requests.CreateGameRequest;
import twentyfive.appcovo2.requests.CreateMoreFormatsRequest;
import twentyfive.appcovo2.services.AwardService;
import twentyfive.appcovo2.services.FormatService;
import twentyfive.appcovo2.services.GameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ApiGatewayService {

    @Autowired
    private GameService gameService;
    @Autowired
    private FormatService formatService;
    @Autowired
    private AwardService awardService;

    public List<GameDtoForList> getAllGames() {
        List<Game> games = gameService.getAll();
        List<GameDtoForList> gamesDto = new ArrayList<>();
        for (Game game : games) {
            gamesDto.add(new GameDtoForList(game.getId(), game.getName(), game.getImage()));
        }
        return gamesDto;
    }

    @Transactional
    public void createGsme(CreateGameRequest request) {
        Game game = gameService.save(
                Game.builder()
                        .name(request.getName())
                        .image(request.getImage())
                        .build()
                );
        List<Format> formats = new ArrayList<>();
        for(String f : request.getFormats()) {
            Format format = formatService.save(
                    Format.builder()
                            .name(f).
                            game(game)
                            .build());
            formats.add(format);
        }
        game.setFormats(formats);
        gameService.save(game);
    }


    public void deleteGsmeById(Long id) {
        gameService.delete(id);
    }

    public GameDto getGameById(Long id) {
        Optional<Game> g = gameService.getById(id);
        if (g.isPresent()) {
            Game game = g.get();
            List<FormatDto> formats = new ArrayList<>();
            for (Format f : game.getFormats()) {
                formats.add(new FormatDto(f.getId(), f.getName()));
            }
            return new GameDto(game.getId(), game.getName(), game.getImage(), formats);
        }
        else return null;
    }

    public void deleteFormatById(Long id) {
        formatService.delete(id);
    }

    public void createFormats(Long gameId, CreateMoreFormatsRequest request) {
        Optional<Game> g = gameService.getById(gameId);
        if (g.isPresent()) {
            for (String f : request.getFormats()) {
                formatService.save(Format.builder().name(f).game(g.get()).build());
            }
        }
    }

    public void createAward(Award award) {
        awardService.save(award);
    }

    public void editAward(Long id, Award award) {
        if(awardService.existsAward(id)) {
            award.setId(id);
            awardService.save(award);
        }
    }
}
