package xyz.hugme.hugmebackend.api.counselorlist.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.counselorlist.dto.ListDTO;
import xyz.hugme.hugmebackend.api.counselorlist.service.ListService;

@RestController
@RequiredArgsConstructor
public class ListController{

    private final ListService listService;

    @GetMapping("/counselors/list")
    public ResponseEntity<Slice<ListDTO>> listsList(@PageableDefault(size=5) Pageable pageable){
        Slice<ListDTO> listDTOS = listService.findAll(pageable);
        if(listDTOS.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listDTOS);
    }

}
