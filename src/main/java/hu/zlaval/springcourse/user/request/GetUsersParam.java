package hu.zlaval.springcourse.user.request;

import lombok.Data;

import java.util.List;

@Data
public class GetUsersParam {

    String email;
    Long page;
    Long size;
    List<Integer> filter;
}
