package com.orderservice.app;

import com.orderservice.app.dto.OrderRequest;
import com.orderservice.app.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
public class OrderServiceMainController {

    private final FileStorageService fileStorageService;

    @Autowired
    public OrderServiceMainController(FileStorageService fileStorageService){
        this.fileStorageService = fileStorageService;
    }

    @PostMapping("/orders")
    public ResponseEntity<String> orders(@RequestBody OrderRequest request){
        fileStorageService.pushInvoce(request);
        fileStorageService.pushCreatedOrderKafka(request);
        fileStorageService.pushInDataBase(request);
        return ResponseEntity.ok("ok"); // потом на что-то разумное поправь
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderRequest> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(fileStorageService.getOrderById(id));
    }

}