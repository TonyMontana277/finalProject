package com.homework.finalProject.rest;

import com.homework.finalProject.Service.VisitorService;
import com.homework.finalProject.domain.Visitor;
import com.homework.finalProject.dto.VisitorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;
    @GetMapping("/visitors")
    public ResponseEntity<List<VisitorDto>> findAll(){
        return ResponseEntity.ok(visitorService.findAll());
    }

    @GetMapping("/visitors/{id}")
    public ResponseEntity<VisitorDto> findVisitorById(@PathVariable Long id){

        return visitorService.findVisitorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/visitors/name/{name}")
    public ResponseEntity<Visitor> findVisitorByName(@PathVariable String name){
        return visitorService.findVisitorByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/visitors/passport/{passportId}")
    public ResponseEntity<VisitorDto> findVisitorByPassportId(@PathVariable Long passportId){
        return visitorService.findVisitorByPassportId(passportId)
                .map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/visitors")
    public Visitor addVisitor(@RequestBody Visitor visitor){
        return visitorService.addVisitor(visitor);
    }


    @PutMapping("/visitors/update/{id}")
    public ResponseEntity<VisitorDto> updateVisitorInformation(@PathVariable Long id, @RequestBody Visitor visitor) {
        visitorService.updateVisitorInformation(id, visitor);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

//    @PostMapping
//    public void transferVisitorToRoom(){
//
//    }

}
