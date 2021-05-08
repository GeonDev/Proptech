package com.apt.proptech.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
