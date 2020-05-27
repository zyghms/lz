package com.zygh.lz.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asset implements Serializable {
    private Integer sysAssetId;

    private Integer sysSectionId;

    private Integer sysStaffId;

    private String assetName;

    private String assetType;

    private String assetGpsx;

    private String assetGpsy;
}