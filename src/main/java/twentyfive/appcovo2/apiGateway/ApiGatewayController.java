package twentyfive.appcovo2.apiGateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import twentyfive.appcovo2.dtos.GameDto;
import twentyfive.appcovo2.dtos.GameDtoForList;
import twentyfive.appcovo2.models.Award;
import twentyfive.appcovo2.requests.CreateGameRequest;
import twentyfive.appcovo2.requests.CreateMoreFormatsRequest;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {

    @Autowired
    private ApiGatewayService apiGatewayService;

    @GetMapping
    public ResponseEntity<List<GameDtoForList>> getAllGames() {
        List<GameDtoForList> games = apiGatewayService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @PostMapping("/saveGame")
    public ResponseEntity<Void> createGame(@RequestBody CreateGameRequest request) {
        apiGatewayService.createGsme(request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteGame/{id}")
    public ResponseEntity<Void> deleteGameById(@PathVariable Long id) {
        apiGatewayService.deleteGsmeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getGame/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable Long id) {
        GameDto gameDto = apiGatewayService.getGameById(id);
        if (gameDto!=null) return ResponseEntity.ok(gameDto);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteFormat/{id}")
    public ResponseEntity<Void> deleteFormatById(@PathVariable Long id) {
        apiGatewayService.deleteFormatById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/saveMoreFormats/{id}")
    public ResponseEntity<Void> createFormats(@PathVariable Long id, @RequestBody CreateMoreFormatsRequest request) {
        apiGatewayService.createFormats(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/saveAward")
    public ResponseEntity<Award> createAward(@RequestBody Award award) {
        apiGatewayService.createAward(award);
        return ResponseEntity.noContent().build();
    }

    //TODO non è una put?
    @PostMapping("/saveAward/{id}")
    public ResponseEntity<Award> editAward(@PathVariable Long id, @RequestBody Award award) {
        apiGatewayService.editAward(id, award);
        return ResponseEntity.noContent().build();
    }







}
