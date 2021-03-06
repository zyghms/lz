package com.zygh.lz.service.impl;

import com.zygh.lz.constant.SystemCon;
import com.zygh.lz.dao.NoticeMapper;
import com.zygh.lz.dao.SectionMapper;
import com.zygh.lz.dao.StaffMapper;
import com.zygh.lz.entity.Notice;
import com.zygh.lz.entity.Section;
import com.zygh.lz.service.NoticeService;
import com.zygh.lz.util.ResultUtil;
import com.zygh.lz.vo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NoticeSerciceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private StaffMapper staffMapper;

    /**
     * 新增
     * @param notice
     * @return
     */
    @Override
    public ResultBean addNotice(Notice notice) {
        notice.setNoticeTime(new Date());
        return ResultUtil.execOp(noticeMapper.insert(notice),"新增");
    }

    /**
     * 修改
     * @param notice
     * @return
     */
    @Override
    public ResultBean updaNotice(Notice notice) {
        return ResultUtil.execOp(noticeMapper.updateByPrimaryKeySelective(notice),"修改");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public ResultBean delNotice(Integer id) {
        return ResultUtil.execOp(noticeMapper.deleteByPrimaryKey(id),"删除");
    }

    /**
     * 根据部门查询所有公告
     * @return
     */
    @Override
    public ResultBean selectAllNotice(Integer sectionId) {
        List<Notice> notice = noticeMapper.selectNotrce();
        List<Notice> notices=new ArrayList<>();
        String sectionname = "";
        for (int k=0;k<notice.size();k++){
            String sectionid = notice.get(k).getNoticeAccept();
            if (sectionid != null) {
                String[] secs = sectionid.split(",");
                for (int j = 0; j < secs.length; j++) {
                    int subsysid = Integer.valueOf(secs[j]);
                    if(subsysid==sectionId){
                        Section section = sectionMapper.selectByPrimaryKey(sectionId);
                        notices= noticeMapper.selectAllNotice(sectionId);
                        sectionname = sectionname + section.getSectionName() + " ";

                    }

                }

            }

        }

        if(notices.size() > 0||notices.size()==0){
            return ResultUtil.setOK("success",notices);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"errot",null);
    }

    /**
     * 查询所有公告
     * @return
     */
    @Override
    public ResultBean selectNotice() {
        List<Notice> notice = noticeMapper.selectNotrce();
        for (int i = 0; i < notice.size(); i++) {
            String sectionname = "";
            //接受大队
            String sectionid = notice.get(i).getNoticeAccept();
            if (sectionid != null) {
                String[] secs = sectionid.split(",");
                for (int j = 0; j < secs.length; j++) {
                    int subsysid = Integer.valueOf(secs[j]);
                    Section section = sectionMapper.selectByPrimaryKey(subsysid);
                    sectionname = sectionname + section.getSectionName() + " ";
                }
            }
            notice.get(i).setNoticeSectionName(sectionname);

        }
        if(notice.size()>0||notice.size()==0){
            return ResultUtil.setOK("success",notice);
        }
        return ResultUtil.setError(SystemCon.RERROR1,"error",null);
    }
}
