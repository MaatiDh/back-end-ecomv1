package com.back.ecom.tp.dto.objHandle;

import lombok.Data;

@Data
public class ChangePassword {

    private String currentPassword;

    private String newPassword;
}
