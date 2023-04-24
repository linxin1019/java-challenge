package jp.co.axa.apidemo.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ApiModel(value = "employee object")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {

    @ApiModelProperty(value = "id of employee, required when update, not required when create")
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "name of the employee", required = true, allowEmptyValue = false)
    @Getter
    @Setter
    @Column(name="EMPLOYEE_NAME")
    private String name;

    @ApiModelProperty(value = "salary of the employee", required = true, allowEmptyValue = false, dataType = "integer")
    @Getter
    @Setter
    @Column(name="EMPLOYEE_SALARY")
    private Integer salary;

    @ApiModelProperty(value = "department of the employee", required = true, allowEmptyValue = false)
    @Getter
    @Setter
    @Column(name="DEPARTMENT")
    private String department;

}
