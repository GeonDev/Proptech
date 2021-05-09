package com.apt.proptech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 *  @author : SON
 *  @since 2021. 05. 08
 *  @version 1.0
 *  @see  :
 *  @revision :
 *  @Description : 지도에 영역을 표시하기위해 사용하는 도메인
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocatePos {
    Long id;
    Double xPos;
    Double yPos;
    Double zPos;

}
