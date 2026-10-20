package com.example.assetmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.assetmanagement.entity.Asset;

public interface AssetRepository  extends JpaRepository<Asset,Long> {

}
