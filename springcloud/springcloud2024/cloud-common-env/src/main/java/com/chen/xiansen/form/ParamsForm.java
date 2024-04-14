package com.chen.xiansen.form;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParamsForm implements Serializable {
    private String password;

    private String username;
}
