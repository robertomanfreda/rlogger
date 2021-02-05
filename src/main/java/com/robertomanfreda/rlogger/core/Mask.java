package com.robertomanfreda.rlogger.core;

import lombok.*;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Mask {
    private String target;
}

