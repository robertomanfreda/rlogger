package com.robertomanfreda.rlogger.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
class Mask {
    private String id;
    private String regex;
    private String replacement;
    private boolean sensitive;
}

