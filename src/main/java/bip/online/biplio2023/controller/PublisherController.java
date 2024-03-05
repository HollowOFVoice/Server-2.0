package bip.online.biplio2023.controller;


import bip.online.biplio2023.entity.PublisherEntity;
import bip.online.biplio2023.responce.BaseResponse;
import bip.online.biplio2023.responce.DataResponse;
import bip.online.biplio2023.responce.ListResponse;




import bip.online.biplio2023.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/publisher")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(
                new ListResponse<PublisherEntity>(true, "Список Издательств", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
        return ResponseEntity.ok(
                new DataResponse<PublisherEntity>(true, "Найден следующий издатель", service.findById(id).orElseThrow()));
    }catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));

        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody PublisherEntity publisher) {
        try{
        return ResponseEntity.ok(
                new DataResponse<PublisherEntity>(true, "Издатель сохранен", service.save(publisher)));
    }catch (RuntimeException e) {
        return ResponseEntity.ok(
                new BaseResponse(false, e.getMessage()));

    }
}

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody PublisherEntity publisher) {
       try{
        service.update(publisher);
        return ResponseEntity.ok(
                new BaseResponse(true, "Издатель сохранен"));
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
                    new BaseResponse(true, "Издатель удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));

        }
    }
}