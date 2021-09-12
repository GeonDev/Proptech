package com.apt.api.service;


import com.apt.api.config.TableColumnConfig;
import com.apt.api.domain.Associate;
import com.apt.api.domain.dto.AssociateDto;
import com.apt.api.domain.dto.ColumnTitle;
import com.apt.api.domain.dto.Pagination;
import com.apt.api.domain.enums.AssociateRound;
import com.apt.api.repository.AssociateRepository;
import com.apt.api.repository.support.AssociateRepositorySupport;
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

    @Autowired
    private AssociateRepositorySupport associateRepositorySupport;

    @Autowired
    private TableColumnConfig tableConfig;


    @Override
    public Associate addItem(Associate object) {

        Associate associate = Associate.builder()
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


    public Pagination getItemList(Pageable pageable, String type, String value, String startDate, String endDate) {

        Page<Associate> associatesPages = associateRepositorySupport.findUserTypeAndDatePage(type, value, startDate, endDate, pageable);

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
                .columnTitles(setColumns())
                .build();

        return items;
    }

    public String getTotalTaskPercent(int round){
        return  associateRepository.findAssociateTaskPercent(round);
    }


    public List<AssociateDto> getExcelDate( String type, String value, String startDate, String endDate ){
        List<Associate> temp = associateRepositorySupport.findAssociateTypeAndDate(type, value, startDate, endDate);
        List <AssociateDto> items = new ArrayList<>();
        temp.forEach( o->{items.add( new AssociateDto(o)); });

        return  items;
    }


    public int getAssociateExceptRoundCount(AssociateRound round){
        return  associateRepository.findByAssociateRoundNot(round).size();
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

        List<String> column = tableConfig.getAssociateSearch();

        for(int i =0; i< column.size(); i++ ){
            temp.add(column.get(i));
        }

        return temp;
    }

    private List<ColumnTitle> setColumns(){
        List<ColumnTitle> temp = new ArrayList<>();

        List<String> column = tableConfig.getAssociateColumn();

        for(int i =0; i< column.size(); i++ ){
            temp.add(new ColumnTitle(column.get(i), "c"+i ) );
        }

        return temp;
    }




}