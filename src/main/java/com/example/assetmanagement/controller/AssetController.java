package com.example.assetmanagement.controller;


import com.example.assetmanagement.dto.AssetDto;
import com.example.assetmanagement.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.assetmanagement.entity.Asset;
import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {


    @Autowired
    private AssetService assetService;

    @PostMapping("/createAsset")
    public String createAssetValue( @RequestBody AssetDto dto) {
   return assetService.createAssetValue(dto);

    }


    @GetMapping("/all")
    public List<Asset> getAssetValue(){
        return  assetService.getAssetValue();
    }



    @DeleteMapping("/{id}")
    public String deleteAssetValue(@PathVariable Long id){
        return  assetService.deleteAssetValue(id);
    }

    @PutMapping("/updateAsset/{id}")
    public String updateAssetValue(@PathVariable Long id,  @RequestBody AssetDto dto) {
        return assetService.updateAssetValue(id,dto);

    }

}
