package org.zhaoyangli.ravenote.tools;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zhaoyangli.ravenote.mapper.PageMapper;

import javax.annotation.Resource;

@Controller
public class PageTool {

    @Resource
    PageMapper pageMapper;

    // Need restart to activate
    @RequestMapping("/page_auto_generate")
    public String PageAutoGenerator(){
        for (int i=0;i<=26;i++){
            pageMapper.insertPage("CM50262", "1B", i,"slide\\CM50262\\1B\\CM50262_1B_"+ i +".jpg");
        }
        return "success";
    }

}
