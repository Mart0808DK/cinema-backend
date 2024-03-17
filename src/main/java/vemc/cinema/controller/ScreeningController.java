package vemc.cinema.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vemc.cinema.dto.ScreeningDto;
import vemc.cinema.service.ScreeningService;

import java.util.List;

@CrossOrigin(origins = "https://cinema-frontend-nine.vercel.app/")
@RestController
@RequestMapping("screenings")
public class ScreeningController {

    private final ScreeningService screeningService;

    public ScreeningController(ScreeningService screeningService){
        this.screeningService = screeningService;
    }

    /**
     * Get all screenings
     * @return List of all screenings
     */
    @GetMapping
    public ResponseEntity<List<ScreeningDto>> getAllScreenings(){
        var screenings = this.screeningService.findAll();
        if(screenings != null){
            return ResponseEntity.ok(screenings);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Create a new screening
     * @param screeningDto Screening to create
     * @return Created screening
     */
    @PostMapping
    public ResponseEntity<ScreeningDto> createScreening(@RequestBody ScreeningDto screeningDto) {
        ScreeningDto createdScreening = screeningService.createScreening(screeningDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScreening);
    }

    /**
     * Get screening by id
     * @param id Id of screening
     * @return Screening with given id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScreeningDto> getAllScreeningsById(@PathVariable Long id){
        var screening = this.screeningService.findById(id);
        if(screening != null){
            return ResponseEntity.ok(screening);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Update a screening
     * @param id Id of screening
     * @param screeningDto Screening to update
     * @return Updated screening
     */
    @PutMapping("/{id}")
    public ResponseEntity<ScreeningDto> updateScreening(@PathVariable Long id, @RequestBody ScreeningDto screeningDto) {
        ScreeningDto updatedScreening = screeningService.updateScreening(id, screeningDto);
        return ResponseEntity.ok(updatedScreening);
    }

    /**
     * Delete a screening
     * @param id Id of screening
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScreening(@PathVariable Long id) {
        screeningService.deleteScreening(id);
        return ResponseEntity.noContent().build();
    }
}
