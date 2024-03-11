package bip.online.biplio2023.controller;



import bip.online.biplio2023.entity.BookEntity;
import bip.online.biplio2023.responce.BaseResponse;
import bip.online.biplio2023.responce.DataResponse;
import bip.online.biplio2023.responce.ListResponse;

import bip.online.biplio2023.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@AllArgsConstructor
public class BookController {
    private final BookService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(
                new ListResponse<BookEntity>(true, "Список книг", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
        return ResponseEntity.ok(
                new DataResponse<BookEntity>(true, "Найден следующий нига",
                        service.findById(id).orElseThrow()));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }

    @GetMapping("/get")
    public  ResponseEntity<BaseResponse> getBookname(@RequestParam String bookName){
        return ResponseEntity.ok(new ListResponse(service.getBookName(bookName)));
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody BookEntity books) {
        try {
            return ResponseEntity.ok(
                    new DataResponse<BookEntity>(true, "Книга сохранена", service.save(books)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));
        }
    }
        @PutMapping
        public ResponseEntity<BaseResponse> update (@RequestBody BookEntity books){
            try {
        service.update(books);
            return ResponseEntity.ok(
                    new BaseResponse(true, "Книга сохранен"));
        } catch (RuntimeException e) {
        return ResponseEntity.ok(
                new BaseResponse(false, e.getMessage()));
    }
}
    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.ok(
                    new BaseResponse(true, "Книга удален"));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(
                    new BaseResponse(false, e.getMessage()));

        }
    }
}
