package com.homework.finalProject.Service;

import com.homework.finalProject.domain.Visitor;
import com.homework.finalProject.dto.VisitorDto;
import com.homework.finalProject.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorService {
    private final VisitorRepository visitorRepository;

    private static VisitorDto buildVisitorDto(Visitor visitor){
        return VisitorDto.builder()
                .id(visitor.getId())
                .name(visitor.getName())
                .passportId(visitor.getPassportId())
                .build();
    }

    public List<VisitorDto> findAll() {
        return visitorRepository.findAll()
                .stream()
                .map(VisitorService::buildVisitorDto)
                .collect(Collectors.toList());
    }

    public Visitor addVisitor(Visitor visitor){
        visitorRepository.save(visitor);
        return visitor;
    }

    public Optional<Visitor> findVisitorByName(String name){
        return visitorRepository.findVisitorByName(name);

    }

    public Optional<VisitorDto> findVisitorById(Long id){
        return visitorRepository.findById(id).map(VisitorService::buildVisitorDto);
    }

    public Optional<VisitorDto> findVisitorByPassportId(Long id){
        return visitorRepository.findVisitorByPassportId(id).map(VisitorService::buildVisitorDto);
    }

}
