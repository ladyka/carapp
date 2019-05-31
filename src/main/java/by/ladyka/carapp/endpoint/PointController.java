package by.ladyka.carapp.endpoint;

import by.ladyka.carapp.dto.PointCreateRequestDto;
import by.ladyka.carapp.dto.PointDto;
import by.ladyka.carapp.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/point")
public class PointController {

    @Autowired
    private PointService pointService;

    @GetMapping(value = "/{id}")
    public PointDto getById(@PathVariable("id") Long id) {
        return pointService.getById(id);
    }

    @PostMapping
    public PointDto updatePoint(@RequestBody PointDto pointDto) {
        return pointService.update(pointDto);
    }

    @PutMapping
    public PointDto createPoint(@RequestBody PointCreateRequestDto pointDto) {
        return pointService.create(pointDto);
    }
}
