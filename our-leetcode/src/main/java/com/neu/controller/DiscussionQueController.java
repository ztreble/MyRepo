package com.neu.controller;

import com.neu.common.Response;
import com.neu.common.Utils;
import com.neu.dto.request.CreateDiscussionQueRequest;
import com.neu.dto.request.EditDiscussionQueControllerRequest;
import com.neu.entity.DiscussionQue;
import com.neu.exception.BaseException;
import com.neu.exception.UnknownException;
import com.neu.exception.general.FormValidatorException;
import com.neu.exception.general.PermissionDeniedException;
import com.neu.exception.general.ResourceNotExistException;
import com.neu.service.DiscussionQueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
* @author lzs
* @Date 2020/4/4
*/
@RestController
@RequestMapping("/discussion")
public class DiscussionQueController {
    @Autowired
    private DiscussionQueService discussionQueService;

    /**
     * 创建讨论
     * @param request
     * @param bindingResult
     * @return
     * @throws BaseException
     */
    @PostMapping("/create")
    public Response create(@RequestBody @Valid CreateDiscussionQueRequest request,
                           BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        /**
         * 基于用户信息, 得到用户id
         */

        DiscussionQue discussionQue = new DiscussionQue();
        discussionQue.setCreatorId(0);
        discussionQue.setTitle(request.getTitle());
        discussionQue.setMessage(request.getMessage());
        discussionQue.setTopicId(request.getTopicId());
        discussionQue.setInitializeTime(Utils.currentTime());
        discussionQue.setEditTime(Utils.currentTime());

        int newId = discussionQueService.createDiscussionQue(discussionQue);

        return new Response(0, newId);
    }

    /**
     * 修改讨论
     * @param editRequest
     * @param bindingResult
     * @return
     * @throws BaseException
     */
    @PostMapping("/edit")
    public Response edit(@RequestBody @Valid EditDiscussionQueControllerRequest editRequest,
                         BindingResult bindingResult) throws BaseException {
        if(bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        /**
         * 获取到用户的id
         * id = 0
         */
        int userId = 0;

        /**
         * 验证用户与讨论的所属关系
         */
        DiscussionQue origin = discussionQueService.getById(editRequest.getId());

        if (origin == null) {
            throw new ResourceNotExistException("讨论");
        }

        if(origin.getCreatorId() != userId) {
            throw new PermissionDeniedException();
        }
        origin.setTitle(editRequest.getTitle());
        origin.setMessage(editRequest.getMessage());
        origin.setEditTime(Utils.currentTime());
        if(! discussionQueService.EditDiscussionQue(origin)) {
            throw new UnknownException("修改保存失败");
        }
        return new Response(0, "修改成功");
    }

    /**
     * 删除讨论
     * @param id
     * @return
     * @throws BaseException
     */
    @DeleteMapping("/{id}")
    public Response del(@PathVariable int id) throws BaseException {
        /**
         * 获取的到用户id
         */
        int userId = 0;

        DiscussionQue origin = discussionQueService.getById(id);
        if (origin == null) {
            throw new ResourceNotExistException("讨论");
        }
        if(origin.getCreatorId() != userId) {
            throw new PermissionDeniedException();
        }
        if(!discussionQueService.delDiscussionQue(id)) {
            throw new UnknownException("未知错误导致删除失败");
        }
        return new Response(0, "删除成功");
    }
}
