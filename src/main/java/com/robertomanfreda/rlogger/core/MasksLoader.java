package com.robertomanfreda.rlogger.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class MasksLoader {
    private List<Mask> masks;
}
