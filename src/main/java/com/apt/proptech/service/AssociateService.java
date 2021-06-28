package com.apt.proptech.service;


import com.apt.proptech.domain.Associate;
import com.apt.proptech.domain.User;
import com.apt.proptech.domain.dto.AssociateDto;
import com.apt.proptech.domain.dto.Pagination;
import com.apt.proptech.domain.dto.UserDto;
import com.apt.proptech.repository.AssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssociateService extends BaseService<Associate>{

    @Autowired
    private AssociateRepository associateRepository;


    @Override
    public Associate addItem(Associate object) {

        Associate associate=  Associate.builder()
                .name(object.getName())
                .operateFeeRatio(object.getOperateFeeRatio())
                .endExpectDate(object.getEndExpectDate())
                .city(object.getCity())
                .state(object.getState())
                .address(object.getAddress())
                .build();

        baseRepository.save(associate);

        return associate;
    }

    @Override
    public Associate getItem(Long id) {
        return baseRepository.findById(id).orElse(null);
    }

    @Override
    public Associate updateItem(Associate object) {
        return null;
    }

    @Override
    public Associate deleteItem(Long id) {

        Associate associate = baseRepository.findById(id).orElse(null);

        if(associate != null ){
            baseRepository.delete(associate );
        }
        return associate;
    }

    @Override
    public Pagination getItemList(Pageable pageable, String type, String value) {

        Page<Associate> associatesPages = null;

        if(type.equals("Round") ){
            associatesPages = associateRepository.findByRound(value, pageable);
        }else if(type.equals("Name")){
            associatesPages = associateRepository.findByNameContaining(value, pageable );
        }else if(type.equals("Address")){
            associatesPages = associateRepository.findByAssociateAddress(value, pageable);
        }else{
            associatesPages = baseRepository.findAll(pageable);
        }

        Pagination<AssociateDto> items = Pagination.<AssociateDto>builder()
                .isFirstPage(associatesPages.isFirst())
                .isLastPage(associatesPages.isLast())
                .totalPages(associatesPages.getTotalPages())
                .totalElements(associatesPages.getTotalElements())
                .currentPage(associatesPages.getNumber()+1)
                .currentElements(associatesPages.getNumberOfElements()+1)
                .contents(convertDomain(associatesPages.getContent()))
                .pageNumbers(setPageNumber(associatesPages.getNumber(), associatesPages.getSize(), associatesPages.getTotalPages()))
                .prePageNum(setPrePageNum(associatesPages.getNumber(), associatesPages.isFirst()) )
                .nextPageNum(setNextPageNum(associatesPages.getNumber(), associatesPages.isLast()))
                .searchType(setSearchType())
                .build();

        return items;
    }

    private List<AssociateDto> convertDomain( List<Associate> data){
        List<AssociateDto> result = new ArrayList<AssociateDto>();

        for(Associate info : data ){
            AssociateDto temp = new AssociateDto(info);
            result.add(temp);
        }
        return  result;
    }


    private List<String> setSearchType(){
        List<String> temp = new ArrayList<>();
        temp.add("All");
        temp.add("Round");
        temp.add("Address");
        temp.add("Name");
        return temp;
    }

}
