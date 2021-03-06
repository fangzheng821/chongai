package io.renren.modules.app.controller.friend;

import io.renren.common.exception.RRException;
import io.renren.common.utils.R;
import io.renren.modules.oss.cloud.OSSFactory;
import io.renren.modules.oss.entity.SysOssEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author xukaijun
 * @email 383635738@qq.com
 * @date 2019-03-02 09:38:43
 */
@RestController
@RequestMapping("app/image")
@Api(tags="图片上传")
public class ImageController {

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @ApiOperation("图片上传")
    public R upload(@RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new RRException("上传文件不能为空");
        }

        //上传文件
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

        return R.ok().put("url", url);
    }
}
