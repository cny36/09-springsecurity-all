package com.cny.entity;

import lombok.*;

/**
 * @author : chennengyuan
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private int id;

    private String name;

    private int age;
}
