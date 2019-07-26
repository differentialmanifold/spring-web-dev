package com.example.platform.module.mybatis.proj.rest;


import com.example.platform.module.common.enumtype.ResultEnum;
import com.example.platform.module.common.response.ResponseResult;
import com.example.platform.module.mybatis.entity.TGroup;
import com.example.platform.module.mybatis.proj.service.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Job Group
 */
@Controller
@RequestMapping(value = "/groups")
public class GroupAction {

    private static final Logger logger = LoggerFactory.getLogger(GroupAction.class);

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {

        return "It's OK , process is running !";
    }

//    @RequestMapping(value = {"/list"}, method = {RequestMethod.GET})
//    public ModelAndView getList(Country country,
//                                @RequestParam(required = false, defaultValue = "1") int page,
//                                @RequestParam(required = false, defaultValue = "10") int rows) {
//        ModelAndView result = new ModelAndView(page_list);
//        List<Country> countryList = countryService.selectByCountry(country, page, rows);
//        result.addObject("pageInfo", new PageInfo<Country>(countryList));
//        result.addObject("queryParam", country);
//        result.addObject("page", page);
//        result.addObject("rows", rows);
//        return result;
//    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult getGroupById(@PathVariable Integer id) {
        TGroup group = null;
        if (id != null) {
            group = groupService.selectByKey(id);
        }
        return new ResponseResult<TGroup>(ResultEnum.SUCCESS, group);

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult save(@RequestBody TGroup group) {
        if (group.getId() != null) {
            groupService.updateAll(group);
        } else {
            groupService.save(group);
        }
        return ResponseResult.success("保存成功");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer id) {
        groupService.delete(id);
        return ResponseResult.success("删除成功");
    }
}
