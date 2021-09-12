package com.apt.api.domain.dto;

import com.apt.api.domain.Message;
import com.apt.api.util.CommonUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDto {
    String name;
    String profile;
    String message;
    String sandDate;

    public MessageDto(Message message){
        this.name = message.getSendUser().getName();
        this.profile = message.getSendUser().getProfileImg();
        this.message = message.getMessage();
        this.sandDate = CommonUtil.toDateStr(message.getSandDate());
    }

}
