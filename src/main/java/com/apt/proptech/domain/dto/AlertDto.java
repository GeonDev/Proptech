package com.apt.proptech.domain.dto;

import com.apt.proptech.domain.Alert;
import com.apt.proptech.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertDto {

    String message;
    String sandDate;

    public AlertDto(Alert alert){
        this.message = alert.getMessage();
        this.sandDate = CommonUtil.toDateStr(alert.getSandDate());
    }
}
