package com.example.springproje.service;

import com.example.springproje.bean.Talk;
import com.example.springproje.dto.CollectionDTO;
import com.example.springproje.dto.LikeInfoDTO;
import com.example.springproje.dto.TalkDTO;

import java.io.IOException;
import java.util.List;

public interface PythonService {
    public String modelpredict(List<LikeInfoDTO> like_talk, List<CollectionDTO> collect_talk, List<TalkDTO> all_talk) throws IOException;

    public void modelpredicttest(List<LikeInfoDTO> like_talk, List<CollectionDTO> collect_talk, List<TalkDTO> all_talk) throws IOException;
}
