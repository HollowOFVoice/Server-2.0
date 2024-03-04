package bip.online.biplio2023.controller;



import bip.online.biplio2023.entity.CityEntity;
import bip.online.biplio2023.responce.BaseResponse;
import bip.online.biplio2023.responce.DataResponse;
import bip.online.biplio2023.responce.ListResponse;


import bip.online.biplio2023.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
@AllArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {

        return ResponseEntity.ok(
                new ListResponse<CityEntity>(true, "Список городов", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
        return ResponseEntity.ok(
                new DataResponse<CityEntity>(true, "Найден следующий город", service.findById(id).orElseThrow()));
    } catch (RuntimeException e) {
        return ResponseEntity.ok(
                new BaseResponse(false, e.getMessage()));
    }
}

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody CityEntity city) {
        try {
        return ResponseEntity.ok(
                new DataResponse<CityEntity>(true, "Город сохранен", service.save(city)));
    }catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody CityEntity city) {
        try {
        service.update(city);
        return ResponseEntity.ok(
                new BaseResponse(true, "город сохранен"));
    }catch (RuntimeException e) {
        return ResponseEntity.ok(
                new BaseResponse(false, e.getMessage()));
    }
}
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true, "Город удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));

        }
    }
}