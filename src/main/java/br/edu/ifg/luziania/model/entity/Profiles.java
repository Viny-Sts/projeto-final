package br.edu.ifg.luziania.model.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"})
})
public class Profiles {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Boolean permissionLevel1;
    private Boolean permissionLevel2;
    private Boolean permissionLevel3;
    private Boolean permissionLevel4;
    private Boolean permissionLevel5;
    private Boolean permissionLevel6;
    private Boolean permissionLevel7;
    private Boolean permissionLevel8;
    private Boolean permissionLevel9;
    private Boolean permissionLevel10;
    private Boolean permissionLevel11;
    private Boolean permissionLevel12;

    public Profiles() {

    }

    public Profiles(Boolean permissionLevel1, Boolean permissionLevel2, Boolean permissionLevel3,
                    Boolean permissionLevel4, Boolean permissionLevel5, Boolean permissionLevel6,
                    Boolean permissionLevel7, Boolean permissionLevel8, Boolean permissionLevel9,
                    Boolean permissionLevel10, Boolean permissionLevel11, Boolean permissionLevel12) {
        this.permissionLevel1 = permissionLevel1;
        this.permissionLevel2 = permissionLevel2;
        this.permissionLevel3 = permissionLevel3;
        this.permissionLevel4 = permissionLevel4;
        this.permissionLevel5 = permissionLevel5;
        this.permissionLevel6 = permissionLevel6;
        this.permissionLevel7 = permissionLevel7;
        this.permissionLevel8 = permissionLevel8;
        this.permissionLevel9 = permissionLevel9;
        this.permissionLevel10 = permissionLevel10;
        this.permissionLevel11 = permissionLevel11;
        this.permissionLevel12 = permissionLevel12;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getPermissionLevel1() {
        return permissionLevel1;
    }

    public void setPermissionLevel1(Boolean permissionLevel1) {
        this.permissionLevel1 = permissionLevel1;
    }

    public Boolean getPermissionLevel2() {
        return permissionLevel2;
    }

    public void setPermissionLevel2(Boolean permissionLevel2) {
        this.permissionLevel2 = permissionLevel2;
    }

    public Boolean getPermissionLevel3() {
        return permissionLevel3;
    }

    public void setPermissionLevel3(Boolean permissionLevel3) {
        this.permissionLevel3 = permissionLevel3;
    }

    public Boolean getPermissionLevel4() {
        return permissionLevel4;
    }

    public void setPermissionLevel4(Boolean permissionLevel4) {
        this.permissionLevel4 = permissionLevel4;
    }

    public Boolean getPermissionLevel5() {
        return permissionLevel5;
    }

    public void setPermissionLevel5(Boolean permissionLevel5) {
        this.permissionLevel5 = permissionLevel5;
    }

    public Boolean getPermissionLevel6() {
        return permissionLevel6;
    }

    public void setPermissionLevel6(Boolean permissionLevel6) {
        this.permissionLevel6 = permissionLevel6;
    }

    public Boolean getPermissionLevel7() {
        return permissionLevel7;
    }

    public void setPermissionLevel7(Boolean permissionLevel7) {
        this.permissionLevel7 = permissionLevel7;
    }

    public Boolean getPermissionLevel8() {
        return permissionLevel8;
    }

    public void setPermissionLevel8(Boolean permissionLevel8) {
        this.permissionLevel8 = permissionLevel8;
    }

    public Boolean getPermissionLevel9() {
        return permissionLevel9;
    }

    public void setPermissionLevel9(Boolean permissionLevel9) {
        this.permissionLevel9 = permissionLevel9;
    }

    public Boolean getPermissionLevel10() {
        return permissionLevel10;
    }

    public void setPermissionLevel10(Boolean permissionLevel10) {
        this.permissionLevel10 = permissionLevel10;
    }

    public Boolean getPermissionLevel11() {
        return permissionLevel11;
    }

    public void setPermissionLevel11(Boolean permissionLevel11) {
        this.permissionLevel11 = permissionLevel11;
    }

    public Boolean getPermissionLevel12() {
        return permissionLevel12;
    }

    public void setPermissionLevel12(Boolean permissionLevel12) {
        this.permissionLevel12 = permissionLevel12;
    }
}
