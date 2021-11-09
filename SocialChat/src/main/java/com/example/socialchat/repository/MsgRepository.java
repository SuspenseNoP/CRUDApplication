package com.example.socialchat.repository;

import com.example.socialchat.domain.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MsgRepository extends CrudRepository<Message,Long> {

    List<Message> findByTag(String tag);
}
