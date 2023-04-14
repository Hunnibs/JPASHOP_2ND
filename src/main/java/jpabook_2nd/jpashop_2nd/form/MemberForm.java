package jpabook_2nd.jpashop_2nd.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    private Long id;
    @NotEmpty(message = "이름은 필수입력입니다.")
    private String name;

    private String city;
    private String street;
    private String zipcode;
}
