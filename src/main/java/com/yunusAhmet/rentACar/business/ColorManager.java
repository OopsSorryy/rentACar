package com.yunusAhmet.rentACar.business;

import com.yunusAhmet.rentACar.core.exception.ColorNotFoundException;
import com.yunusAhmet.rentACar.core.constant.Constant;
import com.yunusAhmet.rentACar.dataAccess.ColorDao;
import com.yunusAhmet.rentACar.entity.Color;
import org.springframework.stereotype.Service;

@Service
public class ColorManager {

    private final ColorDao colorDao;


    public ColorManager(ColorDao colorDao) {
        this.colorDao = colorDao;
    }
    protected Color getColorByColorId(int colorId){
        return colorDao.findById(colorId).orElseThrow(() -> new ColorNotFoundException(Constant.COLOR_NOT_FOUND));
    }

}
