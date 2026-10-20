package com.example.assetmanagement.service;

import com.example.assetmanagement.dto.AssetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.assetmanagement.repository.AssetRepository;
import com.example.assetmanagement.entity.Asset;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
public class AssetService {

    @Autowired
    private AssetRepository assetRepository;

    public String createAssetValue(AssetDto dto){

        Asset asset = new Asset();

        asset.setAssetName(dto.getAssetName());
        asset.setAssetTag(dto.getAssetTag());
        asset.setCategory(dto.getCategory());
        asset.setLocation(dto.getLocation());
        asset.setPurchaseDate(dto.getPurchaseDate());
        asset.setStatus(dto.getStatus());
        asset.setAssetValue(dto.getAssetValue());
        assetRepository.save(asset);
        return "Asset saved ";

    }
    public List<Asset> getAssetValue() {
      return   assetRepository.findAll();
    }


    public String deleteAssetValue( Long id) {
       if(assetRepository.existsById(id)){
           assetRepository.deleteById(id);
           return "Asset " + id+ "deleted ";
       }else{

           return "error in deleting";
       }

    }

    public String updateAssetValue(Long id ,AssetDto dto) {

    if(assetRepository.existsById(id)){
        Asset asset = assetRepository.findById(id).get();

        asset.setAssetName(dto.getAssetName());
        asset.setAssetTag(dto.getAssetTag());
        asset.setCategory(dto.getCategory());
        asset.setLocation(dto.getLocation());
        asset.setPurchaseDate(dto.getPurchaseDate());
        asset.setStatus(dto.getStatus());
        asset.setAssetValue(dto.getAssetValue());
        assetRepository.save(asset);
        return "Updated the asset";
    }else{

        return "id doesnt exist ";
    }

    }
}
